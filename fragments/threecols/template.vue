<template>
  <section class="bay-threecols" :class="'bay-threecols--' + (model.background || 'white')" :data-per-path="model.path">
    <div class="bay-container">
      <h2 class="bay-threecols__title" v-if="model.title" data-per-inline="model.title">{{ model.title }}</h2>
      <div class="bay-threecols__grid">
        <div class="bay-threecols__cell" v-for="(child, i) in (model.children || []).slice(0, 3)" :key="child.path || i">
          <component :is="child.component" :model="child"></component>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})
</script>

<style>
.bay-threecols {
  padding: clamp(40px, 6vw, 72px) 0;
}

.bay-threecols--white { background: var(--bay-white); }
.bay-threecols--cream { background: var(--bay-cream); }
.bay-threecols--teal { background: var(--bay-teal); color: var(--bay-on-teal); }

.bay-threecols__title {
  font-size: var(--bay-band);
  margin-bottom: clamp(32px, 5vw, 64px);
}

.bay-threecols__grid {
  display: grid;
  grid-template-columns: repeat(3, minmax(0, 1fr));
  gap: clamp(24px, 3vw, 40px);
}

@media (max-width: 820px) {
  .bay-threecols__grid {
    grid-template-columns: minmax(0, 1fr);
  }
}
</style>
