<template>
  <section class="bay-gallery" :data-per-path="model.path">
    <div class="perIsEditAndEmpty" v-if="isEditAndEmpty">No gallery defined</div>
    <div class="bay-container" v-else>
      <div class="bay-gallery__text" v-if="model.text" v-html="model.text" :data-per-inline="`model.text`"></div>
      <div class="bay-gallery__mosaic" :class="{ 'bay-gallery__mosaic--swap': model.swap === 'true' || model.swap === true }" v-if="model.images && model.images.length">
        <img class="bay-gallery__big" v-if="model.images[0]" :src="$bayImg(model.images[0].image, 1000)" :srcset="$baySrcset(model.images[0].image, [400, 800, 1200, 1600])"
             sizes="(max-width: 820px) 100vw, 58vw" :alt="model.images[0].imagealt">
        <div class="bay-gallery__side">
          <img class="bay-gallery__small" v-for="img in model.images.slice(1, 5)" :key="img.image" :src="$bayImg(img.image, 600)" :srcset="$baySrcset(img.image, [250, 500, 750])"
               sizes="(max-width: 820px) 50vw, 29vw" :alt="img.imagealt">
        </div>
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

// zzBayImg.js used to install these as a Vue 2 global mixin. Vue 3 has no
// `Vue.mixin()` global API, and a global mixin is the wrong shape for two pure
// functions anyway, so each component that needs them binds them explicitly.
const $bayImg = (path, width) => window.BayImg.url($helper.pathToUrl(path), width)
const $baySrcset = (path, widths) => window.BayImg.srcset($helper.pathToUrl(path), widths)

const isEditAndEmpty = computed(() => {
    if (!$peregrineApp.isAuthorMode()) return false
    return $helper.areAllEmpty(props.model.text, props.model.images)
})
</script>

<style>
.bay-gallery {
  background: var(--bay-white);
  padding: clamp(40px, 6vw, 72px) 0;
}

.bay-gallery__text {
  max-width: 44ch;
  margin-left: auto;
  font-size: 1.05rem;
  margin-bottom: clamp(28px, 4vw, 48px);
}

.bay-gallery__text p {
  margin: 0;
}

.bay-gallery__mosaic {
  display: grid;
  grid-template-columns: 58fr 42fr;
  gap: 14px;
}

.bay-gallery__big {
  width: 100%;
  height: 100%;
  min-height: 420px;
  object-fit: cover;
}

.bay-gallery__side {
  display: grid;
  grid-template-columns: minmax(0, 1fr) minmax(0, 1fr);
  gap: 14px;
}

.bay-gallery__small {
  width: 100%;
  height: 100%;
  min-height: 160px;
  object-fit: cover;
}

@media (max-width: 820px) {
  .bay-gallery__mosaic {
    grid-template-columns: minmax(0, 1fr);
  }
  .bay-gallery__big {
    min-height: 260px;
  }
}

/* Swapped variant: mirror the two columns (desktop only) */
@media (min-width: 821px) {
  .bay-gallery__mosaic--swap {
    grid-template-columns: 42fr 58fr;
  }
  .bay-gallery__mosaic--swap > :first-child {
    order: 2;
  }
}
</style>
