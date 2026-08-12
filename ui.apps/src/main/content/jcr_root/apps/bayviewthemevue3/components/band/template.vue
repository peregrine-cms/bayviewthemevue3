<template>
  <section class="bay-band" :class="bandClass" :data-per-path="model.path">
    <div :class="{ 'bay-container': model.contained === 'true' || model.contained === true }">
      <!-- Vue 3 wants the key on the <template> that carries v-for, not on the
           element inside it (Vue 2 accepted either). -->
      <template v-for="child in model.children" :key="child.path">
        <component :is="child.component" :model="child"></component>
      </template>
    </div>
  </section>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const bandClass = computed(() => {
    var c = ['bay-band--' + (props.model.background || 'white')]
    if (props.model.flush === 'true' || props.model.flush === true) c.push('bay-band--flush')
    if (props.model.padding === 'roomy') c.push('bay-band--roomy')
    return c
})
</script>

<style>
.bay-band {
  padding: clamp(24px, 4vw, 48px) 0;
}

.bay-band--white {
  background: var(--bay-white);
}

.bay-band--cream {
  background: var(--bay-cream);
}

.bay-band--teal {
  background: var(--bay-teal);
  color: var(--bay-on-teal);
}

.bay-band--flush {
  padding: 0;
}

.bay-band--roomy {
  padding: clamp(40px, 6vw, 72px) 0;
}
</style>
