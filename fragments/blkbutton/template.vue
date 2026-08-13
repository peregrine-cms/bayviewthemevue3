<template>
  <div class="bay-blkbutton" :data-per-path="model.path">
    <a :class="btnClass" :href="$helper.pathToUrl(model.link)">{{ model.text || (isEdit ? 'Button — set text in properties' : '') }}</a>
  </div>
</template>

<script setup>
import { computed, inject } from 'vue'

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const peregrineApp = inject('peregrineApp', null)
const isEdit = computed(() => peregrineApp?.isAuthorMode?.() ?? false)

const btnClass = computed(() => {
    var s = props.model.style || 'teal'
    if (s === 'white') return 'bay-blkbutton__btn bay-blkbutton__btn--white'
    if (s === 'black') return 'bay-blkbutton__btn bay-blkbutton__btn--black'
    return 'bay-pill'
})
</script>

<style>
.bay-blkbutton {
  margin: 10px 0;
}

.bay-blkbutton__btn {
  display: inline-block;
  font-family: var(--bay-font-heading);
  font-weight: 600;
  font-size: 0.9rem;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  text-decoration: none;
  padding: 13px 26px;
  cursor: pointer;
}

.bay-blkbutton__btn--white {
  background: #ffffff;
  color: var(--bay-ink);
  border-radius: 999px;
}

.bay-blkbutton__btn--white:hover {
  background: var(--bay-cream);
}

.bay-blkbutton__btn--black {
  background: #0d0d0d;
  color: #ffffff;
}

.bay-blkbutton__btn--black:hover {
  background: #2a2a2a;
}
</style>
