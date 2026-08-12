<template>
  <div class="bay-blkspacer" :class="'bay-blkspacer--' + (model.size || 'medium')" :data-per-path="model.path"></div>
</template>

<script setup>
defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})
</script>

<style>
.bay-blkspacer--tiny { height: 14px; }
.bay-blkspacer--small { height: 16px; }
.bay-blkspacer--medium { height: 40px; }
.bay-blkspacer--large { height: 80px; }
</style>
