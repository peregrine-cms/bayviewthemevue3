<template>
  <div class="bay-blkimage" :class="imgClasses" :data-per-path="model.path" v-if="model.image">
    <bay-media img-class="bay-blkimage__img"
               :src="model.image" :alt="model.imagealt" :video="model.videourl"
               :autoplay="model.videoautoplay"
               :focusx="model.focusx" :focusy="model.focusy" :zoom="model.zoom"
               :widths="[400, 800, 1200, 1600]"
               sizes="(max-width: 820px) 100vw, 50vw"></bay-media>
  </div>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const imgClasses = computed(() => {
    var c = ['bay-blkimage--' + (props.model.height || 'medium')]
    if (props.model.fullbleed === 'true' || props.model.fullbleed === true) c.push('bay-blkimage--fullbleed')
    return c
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
</script>

<style>
.bay-blkimage {
  display: block;
  width: 100%;
  overflow: hidden;
}

.bay-blkimage__img {
  display: block;
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.bay-blkimage--auto .bay-blkimage__img { height: auto; }
.bay-blkimage--small { height: 230px; }
.bay-blkimage--tile { height: 265px; }
.bay-blkimage--medium { height: 340px; }
.bay-blkimage--large { height: 480px; }
.bay-blkimage--banner { height: clamp(240px, 34vw, 420px); }
.bay-blkimage--fill { height: 100%; min-height: 320px; }

.bay-blkimage--fullbleed {
  width: 100vw;
  max-width: 100vw;
  margin-left: calc(50% - 50vw);
}
</style>
