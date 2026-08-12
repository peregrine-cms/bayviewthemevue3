;/*
 * BayCalendar — shared iCal parsing for the schedule and eventlist
 * components. Ported from the tkdbernmaintvloop TV app (lib/calendar.js).
 * Classes are "Klassen (Name)" timed events (instructor = first description
 * line); gatherings/events are all-day entries. Requires window.ICAL
 * (dependencies/ical.min.js).
 */
(function () {
  if (window.BayCalendar) return
  var KLASSEN_RE = /^Klassen\s*\((.+)\)$/
  var fetchPromise = null

  function getParsed(url) {
    if (fetchPromise) return fetchPromise
    fetchPromise = window.axios.get(url).then(function (r) {
      var jcal = window.ICAL.parse(r.data)
      var root = new window.ICAL.Component(jcal)
      root.getAllSubcomponents('vtimezone').forEach(function (vtz) {
        var tz = new window.ICAL.Timezone(vtz)
        window.ICAL.TimezoneService.register(tz.tzid, tz)
      })
      return root.getAllSubcomponents('vevent').map(function (v) {
        return new window.ICAL.Event(v)
      })
    })
    return fetchPromise
  }

  function localMidnight(date) {
    return new window.ICAL.Time({
      year: date.getFullYear(), month: date.getMonth() + 1, day: date.getDate(),
      hour: 0, minute: 0, second: 0, isDate: false
    })
  }

  function eachOccurrenceInRange(event, rangeStart, rangeEnd, cb) {
    if (event.isRecurring()) {
      var iter = event.iterator()
      var next
      var safety = 0
      while ((next = iter.next())) {
        if (++safety > 5000) break
        if (next.compare(rangeEnd) >= 0) break
        if (next.compare(rangeStart) < 0) continue
        cb(next)
      }
    } else {
      var s = event.startDate
      if (s.compare(rangeStart) < 0 || s.compare(rangeEnd) >= 0) return
      cb(s)
    }
  }

  function toClass(event, occ, name) {
    var occEnd = occ.clone()
    occEnd.addDuration(event.duration)
    var desc = event.description || ''
    return {
      name: name,
      instructor: desc.split('\n')[0].trim(),
      start: new Date(occ.year, occ.month - 1, occ.day, occ.hour, occ.minute, occ.second),
      end: new Date(occEnd.year, occEnd.month - 1, occEnd.day, occEnd.hour, occEnd.minute, occEnd.second)
    }
  }

  function toGathering(event, occ) {
    var occEnd = occ.clone()
    occEnd.addDuration(event.duration)
    var start = new Date(occ.year, occ.month - 1, occ.day)
    var end = new Date(occEnd.year, occEnd.month - 1, occEnd.day)
    end.setMilliseconds(end.getMilliseconds() - 1)
    return {
      title: event.summary || '',
      description: event.description || '',
      location: event.location || '',
      start: start,
      end: end
    }
  }

  window.BayCalendar = {
    classesForRange: function (url, startDate, endDate) {
      return getParsed(url).then(function (events) {
        var start = localMidnight(startDate)
        var end = localMidnight(endDate)
        var out = []
        events.forEach(function (ev) {
          var m = (ev.summary || '').match(KLASSEN_RE)
          if (!m) return
          if (ev.startDate.isDate) return
          eachOccurrenceInRange(ev, start, end, function (occ) {
            out.push(toClass(ev, occ, m[1]))
          })
        })
        out.sort(function (a, b) {
          return (a.start - b.start) || a.name.localeCompare(b.name)
        })
        return out
      })
    },
    upcomingGatherings: function (url, fromDate, days) {
      return getParsed(url).then(function (events) {
        var start = localMidnight(fromDate)
        var end = start.clone()
        end.addDuration(new window.ICAL.Duration({ days: days || 60 }))
        var out = []
        events.forEach(function (ev) {
          if (!ev.startDate.isDate) return
          eachOccurrenceInRange(ev, start, end, function (occ) {
            out.push(toGathering(ev, occ))
          })
        })
        out.sort(function (a, b) { return a.start - b.start })
        return out
      })
    }
  }
})();
