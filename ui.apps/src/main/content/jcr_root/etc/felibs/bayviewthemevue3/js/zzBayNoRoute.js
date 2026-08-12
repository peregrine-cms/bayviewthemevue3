;/*
 * Opt a link out of Peregrine's SPA router: <a href="..." data-noroute>.
 *
 * WHY THIS IS NEEDED: pagerendervue installs `window.onclick`, and its
 * getClickable() walks up from the click target to the nearest <A> — so EVERY
 * same-origin anchor becomes a virtual navigation. It calls
 * loadContent(path), which fetches `<path>.data.json` and swaps the page model.
 *
 * That is right for Peregrine pages and wrong for anything else living on the
 * same host. The members area is served by a Cloudflare Worker, so clicking
 * "Abmelden" (href="/logout") made the SPA request /logout.data.json, which is
 * not a Peregrine resource — Sling answered 403 and the app logged
 * "error getting /logout.data.json". The user never got logged out.
 *
 * pagerendervue's only built-in opt-out is a `download` attribute, which would
 * make the browser save the response instead of navigating — not usable here.
 *
 * A CAPTURE-phase listener on document runs before window's bubble-phase
 * handler, so stopping propagation there prevents the SPA hijack while leaving
 * the browser's own navigation completely intact (we never preventDefault).
 *
 * Use for: Worker-handled routes (/logout, /auth/*), and any link that must be
 * a real page load rather than a model swap.
 */
(function () {
  if (window.__bayNoRouteInstalled) return;
  window.__bayNoRouteInstalled = true;

  document.addEventListener('click', function (e) {
    var el = e.target;
    // Mirror getClickable(): walk up to the nearest anchor.
    while (el && el !== document) {
      if (el.tagName === 'A') {
        if (el.hasAttribute('data-noroute')) e.stopPropagation();
        return;
      }
      el = el.parentNode;
    }
  }, true);
})();
