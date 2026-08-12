;/*
 * Cloudflare image transformation helper (theme convention).
 *
 * Peregrine stores full-resolution MASTERS in the DAM — that is deliberate, the
 * club may want print-quality later and the Instagram CDN source expires within
 * days. Delivery-size optimisation therefore happens at the edge instead.
 *
 * On a public/beta/alpha domain (see `domains` on the root template) image URLs
 * are rewritten to Cloudflare's transformation prefix:
 *
 *     /cdn-cgi/image/width=600,quality=80,format=auto/content/<site>/assets/x.jpg
 *
 * Anywhere else — localhost, the authoring host, the standalone preview — the
 * path is returned untouched, because /cdn-cgi/ only exists in front of
 * Cloudflare and would 404. Authoring therefore keeps loading the full masters,
 * which is an accepted trade: correctness of the master beats page weight for
 * the handful of people who author.
 */
(function () {
  var DEFAULT_WIDTHS = [300, 600, 900, 1200];
  var QUALITY = 80;

  function isEdgeOptimised() {
    try {
      return $peregrineApp.isPublicFacingSite() ||
             $peregrineApp.isBetaSite() ||
             $peregrineApp.isAlphaSite();
    } catch (e) {
      return false;
    }
  }

  function toUrl(path, width) {
    if (!path) return path;
    // already absolute/external, or already transformed -> leave alone
    if (/^https?:\/\//.test(path) || path.indexOf('/cdn-cgi/') === 0) return path;
    if (!isEdgeOptimised()) return path;
    var opts = 'format=auto,quality=' + QUALITY;
    if (width) opts = 'width=' + width + ',' + opts;
    return '/cdn-cgi/image/' + opts + (path.charAt(0) === '/' ? path : '/' + path);
  }

  function srcset(path, widths) {
    if (!path || !isEdgeOptimised()) return '';
    return (widths || DEFAULT_WIDTHS)
      .map(function (w) { return toUrl(path, w) + ' ' + w + 'w'; })
      .join(', ');
  }

  window.BayImg = {
    /** Single transformed URL (or the plain path when not behind Cloudflare). */
    url: toUrl,
    /** srcset string; empty when not behind Cloudflare so the attr is omitted. */
    srcset: srcset,
    isEdgeOptimised: isEdgeOptimised,
    DEFAULT_WIDTHS: DEFAULT_WIDTHS
  };

  // The Vue 2 build installed $bayImg/$baySrcset here as a global mixin
  // (`Vue.mixin({ methods: ... })`). Vue 3 removed the global Vue API -- mixins
  // are per-app (`app.mixin`) and the renderer owns the app instance, so a
  // felib cannot reach it. Components now bind the two helpers themselves off
  // `window.BayImg` above, which is also the shape Vue 3 prefers: plain
  // functions over implicit `this` injection.
})();
