<template>
  <section class="bay-legends" :data-per-path="model.path">
    <div class="perIsEditAndEmpty" v-if="isEditAndEmpty">No content defined</div>
    <div class="bay-legends__grid" :class="{ 'bay-legends__grid--swap': model.swap === 'true' || model.swap === true }" v-else>
      <div class="bay-legends__panel">
        <h2 class="bay-legends__title" v-if="model.title" data-per-inline="model.title">{{ model.title }}</h2>
        <div class="bay-legends__text" v-if="model.text" v-html="model.text" :data-per-inline="`model.text`"></div>
      </div>
      <div class="bay-legends__media">
        <bay-media v-if="model.image" img-class="bay-legends__img"
                   :src="model.image" :alt="model.imagealt" :video="model.videourl"
                   :autoplay="model.videoautoplay"
                   :focusx="model.focusx" :focusy="model.focusy" :zoom="model.zoom"
                   :widths="[400, 700, 1000, 1400]"
                   sizes="(max-width: 820px) 100vw, 50vw"></bay-media>
      </div>
    </div>
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
    return $helper.areAllEmpty(props.model.title, props.model.text, props.model.image)
})
</script>

<style>
.bay-legends__grid {
  display: grid;
  grid-template-columns: minmax(0, 1fr) minmax(0, 1fr);
  min-height: 520px;
}

.bay-legends__panel {
  background: var(--bay-cream);
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  padding: clamp(28px, 5vw, 56px);
}

.bay-legends__title {
  font-size: var(--bay-band);
  max-width: 12ch;
}

.bay-legends__text {
  max-width: 40ch;
  font-size: 0.9rem;
  margin-top: 60px;
}

.bay-legends__text p {
  margin: 0 0 1em;
}

.bay-legends__media {
  position: relative;
  min-height: 340px;
  overflow: hidden;
}

.bay-legends__img {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

@media (max-width: 820px) {
  .bay-legends__grid {
    grid-template-columns: minmax(0, 1fr);
  }
}

/* Swapped variant: mirror the two columns (desktop only) */
@media (min-width: 821px) {
  .bay-legends__grid--swap {
    grid-template-columns: minmax(0, 1fr) minmax(0, 1fr);
  }
  .bay-legends__grid--swap > :first-child {
    order: 2;
  }
}
</style>
