<template>
  <footer class="bay-footer" :data-per-path="model.path">
    <div class="perIsEditAndEmpty" v-if="isEditAndEmpty">No footer content defined</div>
    <div class="bay-footer__grid" :class="{ 'bay-footer__grid--swap': model.swap === 'true' || model.swap === true }" v-else>
      <div class="bay-footer__boxes">
        <div class="bay-footer__brandbox" v-if="model.brand">
          <span v-for="(word, i) in brandWords" :key="i">{{ word }}</span>
        </div>
        <div class="bay-footer__box" v-if="model.address" v-html="model.address" :data-per-inline="`model.address`"></div>
        <div class="bay-footer__box" v-if="model.tel || model.email || model.social">
          <p v-if="model.tel">Tel: {{ model.tel }}</p>
          <p v-if="model.email">E-Mail: <a :href="'mailto:' + model.email">{{ model.email }}</a></p>
          <p v-if="model.social">Social: {{ model.social }}</p>
        </div>
        <p class="bay-footer__copyright" v-if="model.copyright" data-per-inline="model.copyright">{{ model.copyright }}</p>
        <ul class="bay-footer__legal" v-if="model.legallinks && model.legallinks.length">
          <li v-for="l in model.legallinks" :key="l.link || l.name">
            <a :href="$helper.pathToUrl(l.link)">{{ l.name }}</a>
          </li>
        </ul>
      </div>
      <div class="bay-footer__media">
        <bay-media v-if="model.image" img-class="bay-footer__img"
                   :src="model.image" :alt="model.imagealt" :video="model.videourl"
                   :autoplay="model.videoautoplay"
                   :focusx="model.focusx" :focusy="model.focusy" :zoom="model.zoom"
                   :widths="[300, 600, 900, 1200]"
                   sizes="(max-width: 820px) 100vw, 38vw"></bay-media>
      </div>
    </div>
  </footer>
</template>

<script setup>
import { computed } from 'vue'
// The renderer exposes these on `window`; `<script setup>` is a module, so
// unlike a Vue 2 component it cannot pick them up from the global scope.
const $peregrineApp = window.$peregrineApp
const $helper = $peregrineApp.helpers

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const mediaStyle = computed(() => {
    var fp = (props.model.focusx || 50) + '% ' + (props.model.focusy || 50) + '%'
    var s = { objectPosition: fp }
    var z = parseInt(props.model.zoom || '100', 10)
    if (z > 100) {
      s.transform = 'scale(' + (z / 100) + ')'
      s.transformOrigin = fp
    }
    return s
})

const brandWords = computed(() => {
    return (props.model.brand || '').split(' ').filter(Boolean)
})

const isEditAndEmpty = computed(() => {
    if (!$peregrineApp.isAuthorMode()) return false
    return $helper.areAllEmpty(props.model.brand, props.model.address, props.model.image)
})
</script>

<style>
.bay-footer {
  background: var(--bay-white);
}

.bay-footer__grid {
  display: grid;
  grid-template-columns: minmax(280px, 38%) 1fr;
}

.bay-footer__boxes {
  padding: clamp(24px, 4vw, 48px);
  display: flex;
  flex-direction: column;
  gap: 22px;
}

.bay-footer__brandbox {
  border: 1px solid var(--bay-line);
  font-family: var(--bay-font-heading);
  font-weight: 500;
  text-transform: uppercase;
  font-size: clamp(1.8rem, 3vw, 2.6rem);
  line-height: 1.08;
  letter-spacing: 0.02em;
  padding: clamp(28px, 4vw, 56px);
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.bay-footer__box {
  border: 1px solid var(--bay-line);
  padding: 20px 24px;
  font-size: 0.95rem;
}

.bay-footer__box p {
  margin: 0;
}

.bay-footer__box a {
  color: inherit;
}

.bay-footer__legal {
  display: flex;
  flex-wrap: wrap;
  gap: 6px 18px;
  margin: 10px 0 0;
  padding: 0;
  list-style: none;
  font-size: 0.85rem;
}

.bay-footer__legal a {
  color: inherit;
  opacity: 0.8;
}

.bay-footer__legal a:hover {
  opacity: 1;
}

.bay-footer__copyright {
  font-size: 0.75rem;
  color: var(--bay-ink-soft);
  margin: 4px 0 0;
}

.bay-footer__media {
  position: relative;
  min-height: 420px;
  overflow: hidden;
}

.bay-footer__img {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

@media (max-width: 820px) {
  .bay-footer__grid {
    grid-template-columns: minmax(0, 1fr);
  }
  .bay-footer__media {
    min-height: 260px;
  }
}

/* Swapped variant: mirror the two columns (desktop only) */
@media (min-width: 821px) {
  .bay-footer__grid--swap {
    grid-template-columns: 1fr minmax(280px, 38%);
  }
  .bay-footer__grid--swap > :first-child {
    order: 2;
  }
}
</style>
