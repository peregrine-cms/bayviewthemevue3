<template>
  <div class="bay-blktiles" :data-per-path="model.path">
    <a class="bay-blktiles__tile" v-for="s in model.items" :key="s.name" :href="s.link || '#'" target="_blank" rel="noopener">
      <svg class="bay-blktiles__mark" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="1.6" aria-hidden="true">
        <path d="M12 3 L20 7.5 V16.5 L12 21 L4 16.5 V7.5 Z"/>
      </svg>
      <span>{{ s.name }}</span>
    </a>
  </div>
</template>

<script setup>
defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})
</script>

<style>
.bay-blktiles {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(150px, 1fr));
  gap: var(--bay-gap);
  margin-top: clamp(24px, 4vw, 44px);
}

.bay-blktiles__tile {
  border: 1px solid currentColor;
  min-height: 130px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: space-between;
  gap: 14px;
  padding: 16px;
  text-decoration: none;
  color: inherit;
  font-family: var(--bay-font-heading);
  font-size: 0.8rem;
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  transition: opacity 0.15s ease;
}

.bay-blktiles__tile:hover {
  opacity: 0.75;
}

.bay-blktiles__mark {
  width: 26px;
  height: 26px;
}
</style>
