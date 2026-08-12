<template>
  <section class="bay-splitrow" :class="rowClasses" :data-per-path="model.path">
    <div class="bay-splitrow__cell" :class="cellClasses('left')">
      <component v-if="model.children && model.children[0]" :is="model.children[0].component" :model="model.children[0]"></component>
    </div>
    <div class="bay-splitrow__cell" :class="cellClasses('right')">
      <component v-if="model.children && model.children[1]" :is="model.children[1].component" :model="model.children[1]"></component>
    </div>
  </section>
</template>

<script setup>
import { computed } from 'vue'

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const rowClasses = computed(() => {
  var c = ['bay-splitrow--r' + (props.model.ratio || '50-50').replace('-', '')]
  if (props.model.swap === 'true' || props.model.swap === true) c.push('bay-splitrow--swap')
  var mh = props.model.minheight || 'auto'
  if (mh !== 'auto') c.push('bay-splitrow--' + mh)
  var va = props.model.valign || 'center'
  if (va !== 'center') c.push('bay-splitrow--v' + va)
  var gap = props.model.gap
  if (gap === 'true' || gap === true || gap === 'normal') c.push('bay-splitrow--gap')
  else if (gap === 'small') c.push('bay-splitrow--gapsm')
  return c
})

// Stays a plain function rather than a computed: it is parameterised by side,
// and it is called twice per render off cheap property reads.
function cellClasses(side) {
  var c = ['bay-splitrow__cell--' + (props.model[side + 'bg'] || 'white')]
  var flush = props.model[side + 'flush']
  if (flush === 'true' || flush === true) c.push('bay-splitrow__cell--flush')
  return c
}
</script>

<style>
.bay-splitrow {
  display: grid;
  grid-template-columns: minmax(0, 1fr) minmax(0, 1fr);
}

.bay-splitrow--r5842 { grid-template-columns: 58fr 42fr; }
.bay-splitrow--r4258 { grid-template-columns: 42fr 58fr; }
.bay-splitrow--r6238 { grid-template-columns: 62fr 38fr; }
.bay-splitrow--r3862 { grid-template-columns: 38fr 62fr; }

.bay-splitrow--gap { gap: clamp(24px, 4vw, 48px); }
.bay-splitrow--gapsm { gap: 14px; }

.bay-splitrow--medium { min-height: 420px; }
.bay-splitrow--large { min-height: 520px; }
.bay-splitrow--tall { min-height: 540px; }

.bay-splitrow__cell {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: clamp(20px, 3.5vw, 48px);
}

.bay-splitrow__cell--flush {
  padding: 0;
}

/* The pagerendervue drop-zone container and its per-child wrappers sit
   between the cell and the content; stretch them so fill-images can take
   the cell's full height and valign flows through. */
.bay-splitrow__cell > .container {
  flex: 1 1 auto;
  display: flex;
  flex-direction: column;
  justify-content: inherit;
  min-height: 0;
}

.bay-splitrow__cell > .container > div {
  display: flex;
  flex-direction: column;
  min-height: 0;
}

.bay-splitrow__cell > .container > div:has(> .bay-blkimage--fill) {
  flex: 1 1 auto;
}

.bay-splitrow__cell .bay-blkimage--fill {
  flex: 1 1 auto;
  height: 100%;
}

.bay-splitrow__cell--none {
  background: transparent;
}

.bay-splitrow--vtop > .bay-splitrow__cell {
  justify-content: flex-start;
}

.bay-splitrow--vspread > .bay-splitrow__cell {
  justify-content: space-between;
}

.bay-splitrow__cell--white { background: var(--bay-white); }
.bay-splitrow__cell--cream { background: var(--bay-cream); }
.bay-splitrow__cell--teal { background: var(--bay-teal); color: var(--bay-on-teal); }

@media (min-width: 821px) {
  .bay-splitrow--swap > .bay-splitrow__cell:first-child { order: 2; }
}

@media (max-width: 820px) {
  .bay-splitrow,
  .bay-splitrow--r5842, .bay-splitrow--r4258,
  .bay-splitrow--r6238, .bay-splitrow--r3862 {
    grid-template-columns: minmax(0, 1fr);
  }
}
</style>
