<template>
  <header class="bay-header" :class="variantClass" :data-per-path="model.path">
    <div class="perIsEditAndEmpty" v-if="isEditAndEmpty">No header content defined</div>
    <div class="bay-container bay-header__bar" v-else>
      <a class="bay-header__brand" :href="$helper.pathToUrl(model.brandlink)">
        <img class="bay-header__logo" v-if="model.logo" :src="$bayImg(model.logo, 320)" :srcset="$baySrcset(model.logo, [160, 320, 480])"
             sizes="(max-width: 820px) 120px, 300px"
             width="1101" height="900"
             :alt="model.logoalt || model.brand">
        <span class="bay-header__wordmark" v-if="model.brand">{{ model.brand }}</span>
      </a>
      <nav class="bay-header__nav" ref="nav" v-if="navItems.length">
        <a v-for="(item, i) in navItems" :key="item.text" :ref="setNavLink"
          v-show="i < visibleCount" :href="$helper.pathToUrl(item.link)"
          :class="{ 'bay-header__nav--member': item.member }"
          :data-noroute="item.member ? '' : null">{{ item.text }}</a>
        <div class="bay-header__more" v-if="overflowItems.length">
          <button type="button" class="bay-header__morebtn" :aria-expanded="moreOpen ? 'true' : 'false'"
            aria-haspopup="true" @click.stop="moreOpen = !moreOpen">More
            <svg viewBox="0 0 12 8" aria-hidden="true"><path d="M1 1 L6 6 L11 1" fill="none" stroke="currentColor" stroke-width="2"/></svg>
          </button>
          <div class="bay-header__morepanel" v-show="moreOpen">
            <a v-for="item in overflowItems" :key="item.text" :href="$helper.pathToUrl(item.link)"
              :class="{ 'bay-header__nav--member': item.member }"
              :data-noroute="item.member ? '' : null">{{ item.text }}</a>
          </div>
        </div>
      </nav>
      <button type="button" class="bay-header__burger" :class="{ 'bay-header__burger--open': menuOpen }"
        :aria-expanded="menuOpen ? 'true' : 'false'" aria-label="Menu"
        v-if="navItems.length" @click.stop="menuOpen = !menuOpen">
        <span></span><span></span><span></span>
      </button>
    </div>
    <div class="bay-header__mobilepanel" v-show="menuOpen">
      <a v-for="item in navItems" :key="item.text" :href="$helper.pathToUrl(item.link)"
        :class="{ 'bay-header__nav--member': item.member }"
        :data-noroute="item.member ? '' : null"
        @click="menuOpen = false">{{ item.text }}</a>
    </div>
  </header>
</template>

<script setup>
import { ref, computed, watch, onMounted, onBeforeUnmount, nextTick } from 'vue'

// The renderer exposes these on `window`; `<script setup>` is a module, so
// unlike a Vue 2 component it cannot pick them up from the global scope.
const $peregrineApp = window.$peregrineApp
const $helper = $peregrineApp.helpers

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

// zzBayImg.js used to install these as a Vue 2 global mixin; see
// gallery/template.vue for why they are now bound per component.
const $bayImg = (path, width) => window.BayImg.url($helper.pathToUrl(path), width)
const $baySrcset = (path, widths) => window.BayImg.srcset($helper.pathToUrl(path), widths)

const scrolled = ref(false)
const menuOpen = ref(false)
const moreOpen = ref(false)
const visibleCount = ref(999)
const linkWidths = ref([])
const loggedIn = ref(false)

// `ref="nav"` still resolves by name under <script setup>...
const nav = ref(null)
// ...but a `ref` inside v-for no longer collects an array in Vue 3, so the
// links are gathered through a function ref instead. It is reset before each
// measuring pass because Vue calls it again for every re-render.
const navlinks = ref([])
function setNavLink(el) {
  if (el) navlinks.value.push(el)
}

/*
 * The authored nav plus, optionally, the internal-area link.
 *
 * Which label shows is decided CLIENT-SIDE from a readable hint cookie set
 * by the members Worker alongside the real (HttpOnly) session. It has to be
 * client-side: public pages are edge-cached and identical for every
 * visitor, so a server-rendered logged-in nav would be served to everyone.
 *
 * The cookie is a hint, never a permission. Worst case it is stale and the
 * link leads to the login form — which is exactly what should happen.
 */
const navItems = computed(() => {
  var items = (props.model.navitems || []).slice()
  var link = props.model.internlink
  if (!link) return items
  items.push({
    text: loggedIn.value
      ? (props.model.internlabel || 'Members')
      : (props.model.loginlabel || 'Login'),
    link: link,
    member: true
  })
  return items
})

const overflowItems = computed(() => {
  var items = navItems.value
  return visibleCount.value >= items.length ? [] : items.slice(visibleCount.value)
})

const variantClass = computed(() => {
  var v = props.model.variant || 'default'
  var c = v === 'default' ? [] : ['bay-header--' + v]
  var shrinkOn = (props.model.shrink === 'true' || props.model.shrink === true) &&
    (v === 'sticky' || v === 'badge')
  if (shrinkOn && scrolled.value) c.push('bay-header--shrunk')
  return c
})

const isEditAndEmpty = computed(() => {
  if (!$peregrineApp.isAuthorMode()) return false
  return $helper.areAllEmpty(props.model.logo, props.model.brand, props.model.navitems)
})

// Priority+ nav: measure link widths once (all links rendered), then
// fit as many as the bar allows; the rest go into the "Mehr" dropdown.
function measureNav() {
  visibleCount.value = 999
  navlinks.value = []
  nextTick(function () {
    // nav is display:none on mobile - widths would measure 0
    if (!nav.value || nav.value.offsetParent === null) {
      linkWidths.value = []
      return
    }
    var ws = navlinks.value.map(function (l) { return l.offsetWidth + 24 })
    if (ws.length === 0 || ws.some(function (w) { return w <= 24 })) {
      linkWidths.value = []
      return
    }
    linkWidths.value = ws
    fitNav()
  })
}

function fitNav() {
  var items = props.model.navitems || []
  if (!nav.value) return
  if (!linkWidths.value.length) {
    measureNav()
    return
  }
  var bar = nav.value.parentElement
  var brand = bar.querySelector('.bay-header__brand')
  var avail = bar.clientWidth - (brand ? brand.offsetWidth : 0) - 60
  var total = linkWidths.value.reduce(function (a, b) { return a + b }, 0)
  if (total <= avail) {
    visibleCount.value = items.length
    return
  }
  var moreWidth = 90
  var sum = 0
  var count = 0
  for (var i = 0; i < linkWidths.value.length; i++) {
    if (sum + linkWidths.value[i] + moreWidth > avail) break
    sum += linkWidths.value[i]
    count++
  }
  visibleCount.value = count
}

function onScroll() {
  scrolled.value = window.scrollY > 40
}

function onResize() {
  fitNav()
}

function onDocClick() {
  moreOpen.value = false
  menuOpen.value = false
}

function onKey(e) {
  if (e.key === 'Escape') {
    moreOpen.value = false
    menuOpen.value = false
  }
}

watch(navItems, measureNav)

onMounted(() => {
  window.addEventListener('scroll', onScroll, { passive: true })
  onScroll()
  window.addEventListener('resize', onResize)
  document.addEventListener('click', onDocClick)
  document.addEventListener('keydown', onKey)

  // Read the hint cookie after mount. Not during render: server-side and
  // first-paint HTML must stay identical for everyone, or the cached copy
  // would leak one visitor's state to the next.
  loggedIn.value = document.cookie.split(';').some(function (c) {
    return c.trim().indexOf('tkdb_member=1') === 0
  })

  measureNav()
})

// Vue 2's beforeDestroy; renamed in Vue 3.
onBeforeUnmount(() => {
  window.removeEventListener('scroll', onScroll)
  window.removeEventListener('resize', onResize)
  document.removeEventListener('click', onDocClick)
  document.removeEventListener('keydown', onKey)
})
</script>

<style>
.bay-header {
  background: var(--bay-white);
  border-bottom: 1px solid rgba(16, 16, 16, 0.08);
}

.bay-header__bar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding-top: 12px;
  padding-bottom: 12px;
}

.bay-header__brand {
  font-family: var(--bay-font-heading);
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.06em;
  font-size: 1.05rem;
  text-decoration: none;
  display: flex;
  align-items: center;
  gap: 14px;
  white-space: nowrap;
  flex-shrink: 0;
}

.bay-header__logo {
  height: 48px;
  width: auto;
  display: block;
}

.bay-header__nav {
  display: flex;
  align-items: center;
  gap: 24px;
}

.bay-header__nav a {
  font-family: var(--bay-font-heading);
  font-size: 0.8rem;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  text-decoration: none;
}

/* The internal-area link is deliberately quieter than the real navigation —
   it is a door for a handful of trainers, not a section of the site. */
.bay-header__nav--member {
  opacity: 0.75;
}

.bay-header__nav--member:hover {
  opacity: 1;
}

.bay-header__nav a:hover {
  color: var(--bay-teal-deep);
}

/* Sticky: pins to the top while scrolling. The pagerendervue container
   wraps each component in its own div, so the wrapper must be the sticky
   element (a sticky child can't escape a wrapper of its own height). */
.bay-header--sticky {
  position: sticky;
  top: 0;
  z-index: 100;
}

div:has(> .bay-header--sticky),
div:has(> .bay-header--badge) {
  position: sticky;
  top: 0;
  z-index: 100;
}

/* Badge: tall pinned bar that fits the full crest; the logo scales down
   once the page is scrolled (shrink switch, on by default in the sample). */
.bay-header--badge {
  position: sticky;
  top: 0;
  z-index: 100;
}

.bay-header--badge .bay-header__logo {
  height: 110px;
}

.bay-header__logo {
  transition: height 0.25s ease;
}

.bay-header--badge.bay-header--shrunk .bay-header__logo {
  height: 48px;
}

.bay-header--sticky.bay-header--shrunk .bay-header__logo {
  height: 36px;
}

/* Centered: crest above a centered nav row */
.bay-header--centered .bay-header__bar {
  flex-direction: column;
  justify-content: center;
  gap: 12px;
  padding-top: 20px;
  padding-bottom: 16px;
}

.bay-header--centered .bay-header__logo {
  height: 84px;
}

/* Priority+ overflow dropdown */
.bay-header__more {
  position: relative;
  display: flex;
  align-items: center;
}

.bay-header__morebtn {
  background: none;
  border: 0;
  cursor: pointer;
  color: var(--bay-ink);
  font-family: var(--bay-font-heading);
  font-size: 0.8rem;
  line-height: inherit;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  display: inline-flex;
  align-items: center;
  gap: 6px;
  padding: 0;
}

.bay-header__morebtn svg {
  width: 10px;
  height: 7px;
}

.bay-header__morepanel {
  position: absolute;
  top: calc(100% + 14px);
  right: 0;
  z-index: 120;
  background: var(--bay-white);
  border: 1px solid rgba(16, 16, 16, 0.12);
  box-shadow: 0 8px 24px rgba(16, 16, 16, 0.12);
  min-width: 200px;
  display: flex;
  flex-direction: column;
}

.bay-header__morepanel a {
  font-family: var(--bay-font-heading);
  font-size: 0.8rem;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  text-decoration: none;
  padding: 13px 18px;
  border-bottom: 1px solid rgba(16, 16, 16, 0.06);
}

.bay-header__morepanel a:last-child {
  border-bottom: 0;
}

.bay-header__morepanel a:hover {
  background: var(--bay-cream);
}

/* Burger (mobile only) */
.bay-header__burger {
  display: none;
  background: none;
  border: 0;
  cursor: pointer;
  padding: 8px;
  flex-direction: column;
  justify-content: center;
  gap: 5px;
}

.bay-header__burger span {
  display: block;
  width: 22px;
  height: 2px;
  background: var(--bay-ink);
  transition: transform 0.2s ease, opacity 0.2s ease;
}

.bay-header__burger--open span:nth-child(1) {
  transform: translateY(7px) rotate(45deg);
}

.bay-header__burger--open span:nth-child(2) {
  opacity: 0;
}

.bay-header__burger--open span:nth-child(3) {
  transform: translateY(-7px) rotate(-45deg);
}

.bay-header__mobilepanel {
  display: none;
  flex-direction: column;
  background: var(--bay-white);
  border-top: 1px solid rgba(16, 16, 16, 0.08);
}

.bay-header__mobilepanel a {
  font-family: var(--bay-font-heading);
  font-size: 0.85rem;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  text-decoration: none;
  padding: 15px var(--bay-content-padding);
  border-bottom: 1px solid rgba(16, 16, 16, 0.06);
}

@media (max-width: 700px) {
  .bay-header__nav {
    display: none;
  }

  .bay-header__burger {
    display: flex;
  }

  .bay-header__mobilepanel {
    display: flex;
  }
  .bay-header__logo {
    height: 38px;
  }
  .bay-header__wordmark {
    display: none;
  }
  .bay-header--badge .bay-header__logo {
    height: 72px;
  }
  .bay-header--badge.bay-header--shrunk .bay-header__logo {
    height: 38px;
  }
  .bay-header--centered .bay-header__logo {
    height: 64px;
  }
}
</style>
