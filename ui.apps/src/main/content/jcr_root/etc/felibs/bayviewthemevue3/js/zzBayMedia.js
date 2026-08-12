;/*
 * <bay-media> — the shared media slot for components that show a large image.
 *
 * Renders the image exactly as before, unless a Vimeo URL/ID is set, in which
 * case the same image becomes the poster for a click-to-load video. That means
 * an editor turns any media slot into a video by pasting one URL; focus point,
 * zoom and the Cloudflare srcset keep working either way.
 *
 * Deliberately a FACADE: no Vimeo iframe (and therefore no Vimeo cookies or
 * third-party JS) until the visitor actually clicks play. That matters on a
 * club site with children, and it keeps the player off the critical path.
 * `dnt=1` asks Vimeo not to track once it does load.
 *
 * The renderer ships the FULL Vue build, so the string template below is
 * compiled in the browser and needs no build step.
 *
 * Still written in the Options API: Vue 3 supports it unchanged, and for a
 * component defined as a plain object in a felib (no SFC, no compiler) it stays
 * the most readable form. Only the REGISTRATION had to change -- see the bottom
 * of this file.
 */
(function () {
  if (!window.$peregrineApp || window.__bayMediaRegistered) return;
  window.__bayMediaRegistered = true;

  /* Parses id AND the unlisted privacy hash. The hash is NOT optional for
     unlisted videos: without `h=<hash>` the player returns "because of its
     privacy settings this video cannot be played". Share links look like
     https://vimeo.com/1216771818/45035e2104, embed links like
     https://player.vimeo.com/video/1216771818?h=45035e2104 */
  function parseVimeo(value) {
    if (!value) return { id: '', hash: '' };
    var v = String(value).trim();
    if (/^\d+$/.test(v)) return { id: v, hash: '' };
    var id = '', hash = '';
    var m = v.match(/vimeo\.com\/(?:video\/)?(\d+)(?:\/([0-9a-z]+))?/i);
    if (m) { id = m[1]; hash = m[2] || ''; }
    else {
      m = v.match(/^(\d+)[/?]([0-9a-z]+)/i);
      if (m) { id = m[1]; hash = m[2]; }
    }
    var hp = v.match(/[?&]h=([0-9a-z]+)/i);   // ?h= wins if both are present
    if (hp) hash = hp[1];
    return { id: id, hash: hash };
  }

  function vimeoId(value) { return parseVimeo(value).id; }

  window.BayMedia = { vimeoId: vimeoId, parseVimeo: parseVimeo };

  /* The renderer resolves a component name to a window global by camel-casing
     it with a `cmp` prefix, so `bay-media` is looked up as `window.cmpBayMedia`
     -- the same contract the compiled component bundles use. */
  window.cmpBayMedia = {
    props: {
      src: String,
      alt: String,
      video: String,
      focusx: [String, Number],
      focusy: [String, Number],
      zoom: [String, Number],
      sizes: String,
      widths: Array,
      imgClass: String,
      autoplay: [String, Boolean],
      // Set on the LCP image ONLY (the hero). fetchpriority=high on several
      // images cancels itself out — the point is to rank one above the rest.
      // Anything not marked priority is lazy-loaded instead.
      priority: [String, Boolean],
      // Names the consequence, so the click is informed consent rather than a
      // bare play button that silently loads a third party.
      playLabel: { type: String, default: 'Video abspielen – lädt Vimeo (setzt Cookies)' }
    },
    data: function () {
      return { playing: false };
    },
    mounted: function () {
      // Opt-in per component. Autoplay is ALWAYS muted + looped (browsers block
      // sound-on autoplay anyway) and is skipped for visitors who asked for
      // reduced motion — they get the poster, which is the point of the facade.
      if (!this.autoplayOn || !this.videoId) return;
      var reduce = false;
      try {
        reduce = window.matchMedia &&
                 window.matchMedia('(prefers-reduced-motion: reduce)').matches;
      } catch (e) {}
      if (reduce) return;
      // Phones fall back to click-to-play: the click is an explicit, informed
      // action before any Vimeo cookie is set, and it spares mobile data.
      // 820px matches the theme's layout breakpoint.
      var wide = true;
      try {
        wide = !window.matchMedia || window.matchMedia('(min-width: 821px)').matches;
      } catch (e) {}
      if (!wide) return;
      // Let the poster paint first — the player is never the thing worth
      // blocking first paint on. This does NOT change the third-party-cookie
      // audit (the cookies are still set, just later); it only keeps Vimeo off
      // the critical path.
      var self = this;
      var start = function () { self.playing = true; };
      if ('requestIdleCallback' in window) {
        window.requestIdleCallback(start, { timeout: 1500 });
      } else {
        window.setTimeout(start, 1200);
      }
    },
    computed: {
      parsed: function () { return parseVimeo(this.video); },
      videoId: function () { return this.parsed.id; },
      mediaStyle: function () {
        var fp = (this.focusx || 50) + '% ' + (this.focusy || 50) + '%';
        var s = { objectPosition: fp };
        var z = parseInt(this.zoom || '100', 10);
        if (z > 100) {
          s.transform = 'scale(' + (z / 100) + ')';
          s.transformOrigin = fp;
        }
        return s;
      },
      resolved: function () {
        return this.$helper ? this.$helper.pathToUrl(this.src) : this.src;
      },
      imgSrc: function () {
        return window.BayImg ? window.BayImg.url(this.resolved, 1200) : this.resolved;
      },
      imgSrcset: function () {
        return window.BayImg ? window.BayImg.srcset(this.resolved, this.widths) : '';
      },
      autoplayOn: function () {
        return this.autoplay === true || this.autoplay === 'true';
      },
      isPriority: function () {
        return this.priority === true || this.priority === 'true';
      },
      embedUrl: function () {
        var u = 'https://player.vimeo.com/video/' + this.videoId +
                '?dnt=1&title=0&byline=0&portrait=0&autoplay=1';
        // background=1 gives muted + looped + no chrome, which is what an
        // autoplaying hero wants; muted=1 is repeated for older player builds.
        if (this.autoplayOn) u += '&background=1&muted=1&loop=1';
        if (this.parsed.hash) u += '&h=' + this.parsed.hash;
        return u;
      }
    },
    methods: {
      play: function () { this.playing = true; }
    },
    /* The poster stays in the DOM and the iframe LAYERS OVER it. Swapping the
       image out left a blank box until Vimeo painted — on an autoplay hero that
       is a flash of empty colour, and it also robbed the browser of an early
       LCP candidate. Keeping the image means something is visible instantly. */
    template:
      '<div class="bay-media" :class="{ \'bay-media--video\': !!videoId, \'bay-media--playing\': playing }">' +
        '<img :class="imgClass" class="bay-media__img" :src="imgSrc" :srcset="imgSrcset" ' +
          ':sizes="sizes" :alt="alt" :style="mediaStyle" ' +
          ':fetchpriority="isPriority ? \'high\' : null" ' +
          ':loading="isPriority ? \'eager\' : \'lazy\'" decoding="async">' +
        '<iframe v-if="playing" class="bay-media__frame" :src="embedUrl" ' +
          'frameborder="0" allow="autoplay; fullscreen; picture-in-picture" ' +
          'allowfullscreen :title="alt"></iframe>' +
        '<button v-if="videoId && !playing" type="button" class="bay-media__play" ' +
          '@click="play" :aria-label="playLabel">' +
          '<svg viewBox="0 0 24 24" width="30" height="30" aria-hidden="true">' +
            '<path d="M8 5.5v13l11-6.5z" fill="currentColor"/>' +
          '</svg>' +
        '</button>' +
      '</div>'
  };

  /* Vue 2 registered this globally with `Vue.component()`, which Vue 3 removed.
     loadComponent() puts it in the renderer's registry instead: called before
     the app exists (theme felibs load ahead of loadContentFrom) it is queued
     and registered at createApp; called later it registers immediately. */
  window.$peregrineApp.loadComponent('bay-media');
})();
