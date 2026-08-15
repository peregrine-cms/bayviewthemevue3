<template>
  <section class="bay-herosplit" :data-per-path="model.path">
    <div class="perIsEditAndEmpty" v-if="isEditAndEmpty">No hero content defined</div>
    <div class="bay-herosplit__grid" :class="{ 'bay-herosplit__grid--swap': model.swap === 'true' || model.swap === true }" v-else>
      <div class="bay-herosplit__media">
        <bay-media v-if="model.image" img-class="bay-herosplit__img"
                   :src="model.image" :alt="model.imagealt" :video="model.videourl"
                   :focusx="model.focusx" :focusy="model.focusy" :zoom="model.zoom"
                   :autoplay="model.videoautoplay"
                   :widths="[500, 900, 1300, 1800]"
                   sizes="(max-width: 820px) 100vw, 50vw"
                   priority="true"></bay-media>
        <!-- the logo box sits dead centre, exactly where the play button lands,
             so it steps aside when the slot is a video -->
        <div class="bay-herosplit__logobox" v-if="model.logotext && !model.videourl">
          <span v-for="(word, i) in logoWords" :key="i">{{ word }}</span>
        </div>
      </div>
      <div class="bay-herosplit__content">
        <p class="bay-herosplit__kicker" v-if="model.kicker" data-per-inline="model.kicker">{{ model.kicker }}</p>
        <h1 class="bay-herosplit__title" v-if="model.title" data-per-inline="model.title">{{ model.title }}</h1>
        <div class="bay-herosplit__ctarow">
          <a class="bay-pill" v-if="model.ctatext" :href="$helper.pathToUrl(model.ctalink)" data-per-inline="model.ctatext">{{ model.ctatext }}</a>
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

const logoWords = computed(() => {
    return (props.model.logotext || '').split(' ').filter(Boolean)
})

const isEditAndEmpty = computed(() => {
    if (!$peregrineApp.isAuthorMode()) return false
    return $helper.areAllEmpty(props.model.image, props.model.title)
})
</script>

<style>
.bay-herosplit__grid {
  display: grid;
  grid-template-columns: minmax(0, 1fr) minmax(0, 1fr);
  min-height: 540px;
}

.bay-herosplit__media {
  position: relative;
  background: var(--bay-teal);
  overflow: hidden;
}

.bay-herosplit__img {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.bay-herosplit__logobox {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border: 1px solid #ffffff;
  color: #ffffff;
  font-family: var(--bay-font-heading);
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  font-size: 1.05rem;
  line-height: 1.25;
  padding: 18px 22px;
  display: flex;
  flex-direction: column;
  text-align: left;
}

.bay-herosplit__content {
  display: flex;
  flex-direction: column;
  padding: 32px clamp(24px, 5vw, 64px) 56px;
}

.bay-herosplit__kicker {
  align-self: flex-end;
  max-width: 26ch;
  text-align: left;
  font-size: 0.85rem;
  margin: 0 0 60px;
}

.bay-herosplit__title {
  font-size: var(--bay-display);
  max-width: 11ch;
  margin: auto 0;
}

.bay-herosplit__ctarow {
  margin-top: auto;
}

@media (max-width: 820px) {
  .bay-herosplit__grid {
    grid-template-columns: minmax(0, 1fr);
  }
  .bay-herosplit__media {
    min-height: 340px;
  }
  .bay-herosplit__kicker {
    align-self: flex-start;
    margin-bottom: 30px;
  }
  .bay-herosplit__title {
    margin: 0 0 30px;
  }
}

/* Swapped variant: mirror the two columns (desktop only) */
@media (min-width: 821px) {
  .bay-herosplit__grid--swap {
    grid-template-columns: minmax(0, 1fr) minmax(0, 1fr);
  }
  .bay-herosplit__grid--swap > :first-child {
    order: 2;
  }
}
</style>
