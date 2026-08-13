<template>
  <component :is="model.level || 'h2'" class="bay-blkheading" :class="headingClasses"
    :data-per-path="model.path" data-per-inline="model.text"
    :data-bay-hint="isEdit && !model.text ? 'Heading — type here or open the properties' : null"
    >{{ model.text }}</component>
</template>

<script setup>
import { computed, inject } from 'vue'

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const peregrineApp = inject('peregrineApp', null)
const isEdit = computed(() => peregrineApp?.isAuthorMode?.() ?? false)

const headingClasses = computed(() => {
    var c = ['bay-blkheading--' + (props.model.size || 'band')]
    if (props.model.overlay === 'true' || props.model.overlay === true) c.push('bay-blkheading--overlay')
    return c
})
</script>

<style>
.bay-blkheading {
  margin: 0 0 0.5em;
}

.bay-blkheading--display { font-size: var(--bay-display); }
.bay-blkheading--band { font-size: var(--bay-band); }
.bay-blkheading--item {
  font-size: 1.05rem;
  max-width: 10ch;
  margin: 26px 0 18px;
}

.bay-blkheading--overlay {
  position: absolute;
  z-index: 2;
  top: 28px;
  left: 28px;
  color: var(--bay-on-teal);
  max-width: 8ch;
}
</style>
