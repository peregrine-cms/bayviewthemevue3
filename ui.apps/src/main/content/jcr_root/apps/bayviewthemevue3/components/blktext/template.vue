<template>
  <div class="bay-blktext" :class="['bay-blktext--' + (model.align || 'left'), 'bay-blktext--size-' + (model.size || 'normal')]"
    :data-per-path="model.path" v-html="model.text"
    :data-bay-hint="isEdit && !model.text ? 'Text — type here or open the properties' : null"
    :data-per-inline="`model.text`"></div>
</template>

<script setup>
import { computed, inject } from 'vue'

defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const peregrineApp = inject('peregrineApp', null)
const isEdit = computed(() => peregrineApp?.isAuthorMode?.() ?? false)
</script>

<style>
.bay-blktext {
  font-size: 0.95rem;
  max-width: 44ch;
}

.bay-blktext p {
  margin: 0 0 1em;
}

.bay-blktext p:last-child {
  margin-bottom: 0;
}

.bay-blktext--right {
  align-self: flex-end;
  margin-left: auto;
}

.bay-blktext--center {
  align-self: center;
  margin-left: auto;
  margin-right: auto;
  text-align: center;
}

.bay-blktext--size-large {
  font-size: 1.05rem;
}
</style>
