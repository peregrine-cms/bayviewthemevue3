;/*
 * On-demand loading of the heavy third-party libraries.
 *
 * These used to be five unconditional <script> tags in renderer.html, so every
 * page paid for them: the jsonforms bundles are only used by the `form`
 * component on /mitglied, and ical.min.js only by the schedule components. On
 * a site where nothing paints until Vue mounts, that is a lot of script
 * sitting in front of LCP for pages that never touch either library.
 *
 * JSON Forms 3.x needs no separate lodash: @jsonforms/core inlines the few
 * lodash functions it uses (the Vue 2 line-up had to load lodash first).
 *
 * Peregrine is a SPA — it does NOT reload between pages — so inspecting the
 * page once at startup is not enough: navigating from the homepage to
 * /mitglied must pull jsonforms in before the form renders. Both entry points
 * are therefore wrapped:
 *   loadContentFrom()  initial render, model is inline in #perPage
 *   loadContent()      SPA navigation, model is fetched
 */
(function () {
  if (!window.$peregrineApp || window.__bayDepsInstalled) return;
  window.__bayDepsInstalled = true;

  var BASE = '/etc/felibs/bayviewthemevue3/dependencies/';
  var JSONFORMS = [BASE + 'jsonforms-core.js', BASE + 'jsonforms-vue3.js',
                   BASE + 'jsonforms-vue3-vanilla.js'];
  var ICAL = [BASE + 'ical.min.js'];

  // component suffix -> scripts it needs. keydates is conditional: the same
  // component renders either manual rows or the calendar feed.
  function needsFor(comp) {
    var name = String(comp && comp.component || '');
    if (/-form$/.test(name)) return JSONFORMS;
    if (/-(schedule|eventlist|classtimes)$/.test(name)) return ICAL;
    if (/-keydates$/.test(name) && comp.source === 'ical') return ICAL;
    return null;
  }

  function walk(node, out) {
    if (!node || typeof node !== 'object') return out;
    if (Array.isArray(node)) {
      for (var i = 0; i < node.length; i++) walk(node[i], out);
      return out;
    }
    var need = needsFor(node);
    if (need) for (var j = 0; j < need.length; j++) out[need[j]] = true;
    for (var k in node) {
      if (node[k] && typeof node[k] === 'object') walk(node[k], out);
    }
    return out;
  }

  // url -> Promise. Doubles as the "already loaded" set, so a second visit to
  // /mitglied does not inject the script twice and concurrent asks share one load.
  var loading = {};

  function loadScript(url) {
    if (loading[url]) return loading[url];
    loading[url] = new Promise(function (resolve) {
      var s = document.createElement('script');
      s.src = url;
      s.async = false;           // preserve order: core before vue3 before vanilla
      s.onload = function () { resolve(true); };
      s.onerror = function () {
        // never block navigation on a failed dependency — the components
        // already guard for a missing library and show their own message
        if (window.console) console.warn('[bayDeps] failed to load ' + url);
        resolve(false);
      };
      document.head.appendChild(s);
    });
    return loading[url];
  }

  function ensure(urls) {
    if (!urls.length) return Promise.resolve();
    return Promise.all(urls.map(loadScript));
  }

  function neededByModel(model) {
    return Object.keys(walk(model, {}));
  }

  function inlineModel() {
    try {
      var el = document.getElementById('perPage');
      return el ? JSON.parse(el.innerHTML) : null;
    } catch (e) { return null; }
  }

  function dataUrlFor(path) {
    return String(path || '').replace(/\.html($|\?)/, '.data.json$1');
  }

  var origFrom = window.$peregrineApp.loadContentFrom;
  var origLoad = window.$peregrineApp.loadContent;

  window.$peregrineApp.loadContentFrom = function (id, path, firstTime, fromPopState) {
    var self = this, args = arguments;
    var model = inlineModel();
    var urls = neededByModel(model);
    if (!urls.length) return origFrom.apply(self, args);   // common case: no delay

    // The base page template calls isPublicFacingSite() on the line RIGHT AFTER
    // loadContentFrom, to decide whether to register the service worker, and
    // that reads getPerView().page.domains. Normally loadContentFrom is
    // synchronous (loadContentImpl parses #perPage and assigns page before
    // returning), so the property is there. Deferring it behind the dependency
    // load left `page` undefined and threw
    //     TypeError: Cannot read properties of undefined (reading 'domains')
    // which killed that whole inline script — so the service worker never
    // registered on any page that needs a dependency (i.e. the homepage, via
    // keydates source=ical). Seeding `page` with the very model the impl is
    // about to parse restores the synchronous contract; loadContentImpl then
    // assigns it again for real.
    try {
      var view = window.$peregrineApp.getView ? window.$peregrineApp.getView() : window.$perView;
      if (view && !view.page && model) view.page = model;
    } catch (e) { /* never block the render on this */ }

    return ensure(urls).then(function () { return origFrom.apply(self, args); });
  };

  window.$peregrineApp.loadContent = function (path, firstTime, fromPopState) {
    var self = this, args = arguments;
    // The destination model has to be inspected before its components render.
    // This costs one small extra request on navigation; if it fails for any
    // reason we load everything rather than risk a component without its lib.
    return fetch(dataUrlFor(path), { credentials: 'same-origin' })
      .then(function (r) { return r.ok ? r.json() : null; })
      .then(function (model) {
        return ensure(model ? neededByModel(model) : JSONFORMS.concat(ICAL));
      })
      .catch(function () { return ensure(JSONFORMS.concat(ICAL)); })
      .then(function () { return origLoad.apply(self, args); });
  };

  window.BayDeps = { ensure: ensure, neededByModel: neededByModel, loading: loading };
})();
