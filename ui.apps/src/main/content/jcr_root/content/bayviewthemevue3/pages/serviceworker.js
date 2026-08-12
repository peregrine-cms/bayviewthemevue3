/*
 * Service worker: fill the cache while online, serve it when offline.
 *
 * Deliberately one strategy for everything — NETWORK FIRST, fall back to cache.
 * Two consequences, both wanted:
 *   - online, the network always wins, so a theme deploy is never masked by the
 *     service worker (we have had enough trouble with stale layers already)
 *   - offline, whatever has been visited is still browsable
 *
 * JS and CSS are cached too. An earlier version skipped them to avoid masking
 * deploys, which quietly made offline browsing impossible: this is a Vue SPA,
 * so without the felib the page shell renders nothing. Network-first already
 * solves the staleness worry, so there is no reason to exclude them.
 *
 * No third-party imports. themeclean-flex's version pulls workbox from
 * storage.googleapis.com, which would add a third-party request to every page
 * load and undo the point of self-hosting the fonts.
 *
 * Bump CACHE to drop everything after a deploy.
 */
const CACHE = 'tkdbern-v2';   // bumped: v1 may hold a bad /mitglieder entry

self.addEventListener('install', () => self.skipWaiting());

self.addEventListener('activate', (event) => {
  event.waitUntil(
    caches.keys()
      .then((names) => Promise.all(names.filter((n) => n !== CACHE).map((n) => caches.delete(n))))
      .then(() => self.clients.claim())
  );
});

self.addEventListener('fetch', (event) => {
  const request = event.request;
  if (request.method !== 'GET') return;

  const url = new URL(request.url);

  // Other origins (Vimeo, Instagram CDN) are left to the browser: they are
  // opaque to us and caching them would be guesswork.
  if (url.origin !== self.location.origin) return;

  // Authoring surfaces must never be served from a cache.
  if (url.pathname.indexOf('/content/admin') === 0 ||
      url.pathname.indexOf('/perapi/') === 0 ||
      url.pathname.indexOf('/system/') === 0 ||
      url.pathname.indexOf('/bin/') === 0) return;

  /*
   * The members area and the auth routes are handled by a Cloudflare Worker and
   * MUST NOT be touched here.
   *
   * They answer with a redirect to a DIFFERENT ORIGIN (the auth service). A
   * service worker cannot hand a cross-origin redirect back for a navigation —
   * the browser refuses it ("Unsafe attempt to load URL ... from frame with URL
   * .../serviceworker.js") and the fetch rejects, which broke login entirely.
   * Returning nothing here lets the browser perform the navigation itself,
   * where a cross-origin redirect is perfectly normal.
   *
   * Caching them would be wrong anyway: one member's page must never be served
   * to anyone else, and a login screen must always be live.
   */
  // `indexOf(...) >= 0` on purpose: the members area is reachable both as
  // /mitglieder.html and as /content/<tenant>/pages/mitglieder.html.
  if (url.pathname.indexOf('/mitglieder') >= 0 ||
      url.pathname.indexOf('/auth/') === 0 ||
      url.pathname.indexOf('/logout') === 0) return;

  event.respondWith(
    fetch(request)
      .then((response) => {
        // only cache a real, complete response
        if (response && response.status === 200 && response.type === 'basic') {
          const copy = response.clone();
          caches.open(CACHE).then((cache) => cache.put(request, copy));
        }
        return response;
      })
      .catch(() => caches.match(request).then((hit) => {
        if (hit) return hit;
        // Offline and nothing cached for this exact URL. For a page navigation,
        // fall back to the cached home page — note it is stored under '/', NOT
        // '/index.html'; getting that wrong meant this resolved to undefined,
        // and returning undefined from respondWith throws
        // "Failed to convert value to 'Response'" — turning every offline miss
        // into a hard error instead of a fallback.
        if (request.mode === 'navigate') {
          return caches.match('/').then((home) => home || offlineResponse());
        }
        return offlineResponse();
      }))
  );
});

/* respondWith() REQUIRES a Response. Never let a rejection resolve to
   undefined — always hand back something real. */
function offlineResponse() {
  return new Response(
    '<!doctype html><meta charset="utf-8">' +
    '<title>Offline – Bayview Martial Arts</title>' +
    '<p style="font-family:system-ui,sans-serif;padding:2rem">' +
    'This page is not available offline.</p>',
    { status: 503, headers: { 'Content-Type': 'text/html;charset=utf-8' } });
}
