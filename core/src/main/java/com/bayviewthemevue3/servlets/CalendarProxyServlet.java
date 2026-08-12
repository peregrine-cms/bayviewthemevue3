package com.bayviewthemevue3.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Proxies the club's Google Calendar iCal feed so the schedule/eventlist
 * components can fetch it same-origin (Google serves no CORS headers).
 * The upstream URL stays server-side (it contains a private token) and the
 * response is cached in memory to keep page views cheap.
 */
@Component(
    service = Servlet.class,
    property = {
        "sling.servlet.paths=/bin/bayviewthemevue3/calendar",
        "sling.servlet.methods=GET",
        // allow anonymous access to the proxied (public-safe) feed
        "sling.auth.requirements=-/bin/bayviewthemevue3/calendar"
    }
)
public class CalendarProxyServlet extends SlingSafeMethodsServlet {

    private static final String DEFAULT_URL =
        "https://calendar.google.com/calendar/ical/c_64b0cb588a1a77e14fd8bd94d49c3005b66043d4cef52ba14b4b30b989f45ef4%40group.calendar.google.com/private-f09a86172f6f1cb98ac25e088b9b6c50/basic.ics";

    private static final Logger LOG = LoggerFactory.getLogger(CalendarProxyServlet.class);

    private volatile String calendarUrl;
    private volatile int cacheSeconds;
    private volatile String cachedBody;
    private volatile long cachedAt;

    @Activate
    protected void activate(Map<String, Object> config) {
        Object url = config.get("calendar.url");
        this.calendarUrl = url != null ? url.toString() : DEFAULT_URL;
        Object cache = config.get("cache.seconds");
        this.cacheSeconds = cache != null ? Integer.parseInt(cache.toString()) : 300;
        this.cachedBody = null;
        this.cachedAt = 0L;
    }

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String body = cachedBody;
        long now = System.currentTimeMillis();
        if (body == null || now - cachedAt > cacheSeconds * 1000L) {
            try {
                body = fetchUpstream();
                cachedBody = body;
                cachedAt = now;
            } catch (IOException e) {
                LOG.warn("Calendar fetch failed: {}", e.getMessage());
                if (body == null) {
                    response.sendError(502, "Calendar fetch failed");
                    return;
                }
                // serve stale cache on upstream failure
            }
        }
        response.setContentType("text/calendar");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Cache-Control", "public, max-age=300");
        response.getWriter().write(slim(body));
    }

    /**
     * Strips the feed down to what the site actually renders.
     *
     * The upstream feed is ~180 kB because it is the club's full history: 523 of
     * 535 events are already over, and roughly a third of the bytes are
     * bookkeeping properties nothing on the site reads (DTSTAMP, CREATED,
     * LAST-MODIFIED, SEQUENCE, STATUS, TRANSP, ...).
     *
     * This filters at the TEXT level and leaves parsing to ICAL.js on the
     * client. That is deliberate: recurring events carry RRULE/EXDATE, and
     * re-implementing recurrence expansion server-side would be a large and
     * bug-prone rewrite for no extra benefit. So: keep every VEVENT that
     * recurs (its DTSTART is in the past but it continues into the future),
     * keep non-recurring events that have not finished, drop the rest, and
     * drop the noise properties from whatever survives. VTIMEZONE blocks are
     * preserved because DTSTART may reference them by TZID.
     */
    static String slim(String ics) {
        if (ics == null || ics.isEmpty()) {
            return ics;
        }
        // unfold RFC 5545 continuation lines so each property is one line
        String unfolded = ics.replace("\r\n ", "").replace("\r\n\t", "")
                             .replace("\n ", "").replace("\n\t", "");
        String[] lines = unfolded.split("\\r?\\n");

        // a small grace window so an event that ended earlier today still shows
        String cutoff = LocalDate.now().minusDays(1).format(DateTimeFormatter.BASIC_ISO_DATE);

        StringBuilder out = new StringBuilder(ics.length() / 8);
        List<String> block = new ArrayList<>();
        boolean inEvent = false;
        boolean recurring = false;
        String latestDate = "";

        for (String line : lines) {
            if (line.startsWith("BEGIN:VEVENT")) {
                inEvent = true;
                recurring = false;
                latestDate = "";
                block.clear();
                block.add(line);
                continue;
            }
            if (!inEvent) {
                out.append(line).append("\r\n");
                continue;
            }
            if (line.startsWith("RRULE")) {
                // A recurrence that has already ENDED is not "forward-looking":
                // RRULE;...;UNTIL=20240614 means the series stopped in 2024, yet
                // its DTSTART is in the past so a naive "keep anything with an
                // RRULE" rule ships it forever — along with all its EXDATEs.
                // COUNT=n cannot be judged without expanding it, so those are kept.
                String until = valueOf(line, "UNTIL=");
                recurring = until.isEmpty() || until.compareTo(cutoff) >= 0;
            }
            if (line.startsWith("DTSTART") || line.startsWith("DTEND")) {
                String d = digits(line);
                if (d.length() >= 8) {
                    String day = d.substring(0, 8);
                    if (day.compareTo(latestDate) > 0) {
                        latestDate = day;
                    }
                }
            }
            if (line.startsWith("END:VEVENT")) {
                block.add(line);
                inEvent = false;
                boolean keep = recurring || latestDate.isEmpty()
                        || latestDate.compareTo(cutoff) >= 0;
                if (keep) {
                    for (String bl : block) {
                        if (!isNoise(bl)) {
                            out.append(bl).append("\r\n");
                        }
                    }
                }
                continue;
            }
            block.add(line);
        }
        return out.toString();
    }

    private static final String[] NOISE = {
        "DTSTAMP", "CREATED", "LAST-MODIFIED", "SEQUENCE", "STATUS", "TRANSP",
        "CLASS", "DESCRIPTION", "ATTENDEE", "ORGANIZER", "X-"
    };

    private static boolean isNoise(String line) {
        for (String p : NOISE) {
            if (line.startsWith(p)) {
                return true;
            }
        }
        return false;
    }

    /** The leading yyyyMMdd of a named RRULE part, e.g. UNTIL=20240614T2159Z. */
    private static String valueOf(String line, String key) {
        int i = line.indexOf(key);
        if (i < 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int j = i + key.length(); j < line.length(); j++) {
            char c = line.charAt(j);
            if (c >= '0' && c <= '9') {
                sb.append(c);
                if (sb.length() == 8) {
                    break;
                }
            } else {
                break;
            }
        }
        return sb.length() == 8 ? sb.toString() : "";
    }

    /** First run of digits in a property value, i.e. the yyyyMMdd of a date. */
    private static String digits(String line) {
        int colon = line.indexOf(':');
        String v = colon >= 0 ? line.substring(colon + 1) : line;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < v.length(); i++) {
            char c = v.charAt(i);
            if (c >= '0' && c <= '9') {
                sb.append(c);
            } else if (sb.length() > 0) {
                break;
            }
        }
        return sb.toString();
    }

    private String fetchUpstream() throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(calendarUrl).openConnection();
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(15000);
        conn.setInstanceFollowRedirects(true);
        int status = conn.getResponseCode();
        if (status != 200) {
            throw new IOException("upstream status " + status);
        }
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            char[] buf = new char[8192];
            int n;
            while ((n = reader.read(buf)) > 0) {
                sb.append(buf, 0, n);
            }
        }
        return sb.toString();
    }
}
