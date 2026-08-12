;/*
 * Per-breakpoint component visibility (theme convention).
 * Any peregrine component whose model carries visibility=desktop|mobile gets a
 * bay-only-* class on its root element; base.css media queries hide it on the
 * other breakpoint. In author mode the component is dimmed instead of hidden so
 * it stays editable.
 *
 * Vue 2 registered this with the global `Vue.mixin()`. Vue 3 has no global Vue
 * API -- mixins belong to an app instance, and the renderer owns the only one --
 * so it goes in through registerAppExtension() instead.
 */
(function () {
  if (!window.$peregrineApp || window.__bayVisibilityMixinInstalled) return
  window.__bayVisibilityMixinInstalled = true

  function apply(vm) {
    var m = vm.model
    var el = vm.$el
    // Vue 3 allows fragment roots, where $el is a text placeholder node with no
    // classList. Those components have no single root to mark, so skip them.
    if (!m || !el || !el.classList) return
    el.classList.remove('bay-only-mobile', 'bay-only-desktop', 'bay-vis-author')
    if (m.visibility !== 'mobile' && m.visibility !== 'desktop') return
    el.classList.add(m.visibility === 'mobile' ? 'bay-only-mobile' : 'bay-only-desktop')
    var author = false
    try { author = window.$peregrineApp && window.$peregrineApp.isAuthorMode() } catch (e) {}
    if (author) el.classList.add('bay-vis-author')
  }

  window.$peregrineApp.registerAppExtension(function (app) {
    app.mixin({
      mounted: function () { apply(this) },
      updated: function () { apply(this) }
    })
  })
})();
