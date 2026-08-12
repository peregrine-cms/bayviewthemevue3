<template>
  <section class="bay-leagues" :data-per-path="model.path">
    <div class="perIsEditAndEmpty" v-if="isEditAndEmpty">No leagues defined</div>
    <div class="bay-container" v-else>
      <h2 class="bay-leagues__title" v-if="model.title" data-per-inline="model.title">{{ model.title }}</h2>
      <div class="bay-leagues__grid" v-if="model.items && model.items.length">
        <div class="bay-leagues__item" v-for="(item, i) in model.items" :key="item.title">
          <img class="bay-leagues__img" v-if="item.image" :src="$bayImg(item.image, 600)" :srcset="$baySrcset(item.image, [240, 480, 720])"
               sizes="(max-width: 620px) 100vw, (max-width: 900px) 50vw, 25vw" :alt="item.imagealt">
          <h3 class="bay-leagues__itemtitle" :data-per-inline="`model.items.${i}.title`">{{ item.title }}</h3>
          <p class="bay-leagues__itemtext" :data-per-inline="`model.items.${i}.text`">{{ item.text }}</p>
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

// See gallery/template.vue - replaces the removed Vue 2 global mixin.
const $bayImg = (path, width) => window.BayImg.url($helper.pathToUrl(path), width)
const $baySrcset = (path, widths) => window.BayImg.srcset($helper.pathToUrl(path), widths)

const isEditAndEmpty = computed(() => {
    if (!$peregrineApp.isAuthorMode()) return false
    return $helper.areAllEmpty(props.model.title, props.model.items)
})
</script>

<style>
.bay-leagues {
  background: var(--bay-white);
  padding: clamp(40px, 6vw, 72px) 0;
}

.bay-leagues__title {
  font-size: var(--bay-band);
  margin-bottom: clamp(32px, 5vw, 64px);
}

.bay-leagues__grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
  gap: clamp(24px, 3vw, 40px);
}

.bay-leagues__img {
  width: 100%;
  height: 230px;
  object-fit: cover;
}

.bay-leagues__itemtitle {
  font-size: 1.05rem;
  max-width: 10ch;
  margin: 26px 0 18px;
}

.bay-leagues__itemtext {
  font-size: 0.95rem;
  margin: 0;
}

@media (max-width: 820px) {
  .bay-leagues__grid {
    grid-template-columns: minmax(0, 1fr);
  }
}
</style>
