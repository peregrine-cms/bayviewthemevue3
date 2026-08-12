<template>
  <div class="bay-blklogobox" :class="boxClasses"
    :data-per-path="model.path">
    <span v-for="(word, i) in words" :key="i">{{ word }}</span>
  </div>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const words = computed(() => {
    return (props.model.text || '').split(' ').filter(Boolean)
})

const boxClasses = computed(() => {
    var c = []
    if (props.model.dark === 'true' || props.model.dark === true) c.push('bay-blklogobox--dark')
    if (props.model.overlay === 'true' || props.model.overlay === true) c.push('bay-blklogobox--overlay')
    return c
})
</script>

<style>
.bay-blklogobox {
  display: inline-flex;
  flex-direction: column;
  border: 1px solid #ffffff;
  color: #ffffff;
  font-family: var(--bay-font-heading);
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  font-size: 1.05rem;
  line-height: 1.25;
  padding: 18px 22px;
  text-align: left;
}

.bay-blklogobox--dark {
  border-color: var(--bay-ink);
  color: var(--bay-ink);
}

.bay-blklogobox--overlay {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 2;
}
</style>
