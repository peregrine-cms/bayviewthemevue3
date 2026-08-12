<template>
  <section class="bay-sponsorband" :data-per-path="model.path">
    <div class="perIsEditAndEmpty" v-if="isEditAndEmpty">No sponsors defined</div>
    <template v-if="!isEditAndEmpty">
      <h2 class="bay-sponsorband__title" v-if="model.title" data-per-inline="model.title">{{ model.title }}</h2>
      <div class="bay-sponsorband__imgwrap" v-if="model.image">
        <bay-media img-class="bay-sponsorband__img"
                   :src="model.image" :alt="model.imagealt" :video="model.videourl"
                   :autoplay="model.videoautoplay"
                   :focusx="model.focusx" :focusy="model.focusy" :zoom="model.zoom"
                   :widths="[350, 700, 1050]"
                   sizes="(max-width: 820px) 100vw, 45vw"></bay-media>
      </div>
      <div class="bay-sponsorband__tiles" v-if="model.sponsors && model.sponsors.length">
        <a class="bay-sponsorband__tile" v-for="s in model.sponsors" :key="s.name" :href="s.link || '#'" target="_blank" rel="noopener">
          <svg class="bay-sponsorband__mark" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.6" aria-hidden="true">
            <path d="M12 3 L20 7.5 V16.5 L12 21 L4 16.5 V7.5 Z"/>
          </svg>
          <span>{{ s.name }}</span>
        </a>
      </div>
    </template>
  </section>
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

const isEditAndEmpty = computed(() => {
    if (!$peregrineApp.isAuthorMode()) return false
    return $helper.areAllEmpty(props.model.title, props.model.sponsors)
})
</script>

<style>
.bay-sponsorband {
  background: var(--bay-teal);
  color: var(--bay-on-teal);
  padding-bottom: clamp(24px, 4vw, 48px);
}

.bay-sponsorband__title {
  font-size: var(--bay-band);
  color: var(--bay-on-teal);
  padding: clamp(24px, 4vw, 44px) var(--bay-content-padding);
  max-width: var(--bay-content-max);
  margin: 0 auto;
}

.bay-sponsorband__imgwrap {
  width: 100%;
  height: clamp(240px, 34vw, 420px);
  overflow: hidden;
}

.bay-sponsorband__img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
}

.bay-sponsorband__tiles {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: var(--bay-gap);
  max-width: var(--bay-content-max);
  margin: clamp(24px, 4vw, 44px) auto 0;
  padding: 0 var(--bay-content-padding);
}

.bay-sponsorband__tile {
  border: 1px solid rgba(255, 255, 255, 0.8);
  min-height: 130px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-between;
  gap: 14px;
  padding: 16px;
  text-decoration: none;
  font-family: var(--bay-font-heading);
  font-size: 0.8rem;
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  transition: background 0.15s ease;
}

.bay-sponsorband__tile:hover {
  background: rgba(255, 255, 255, 0.1);
}

.bay-sponsorband__mark {
  width: 26px;
  height: 26px;
}
</style>
