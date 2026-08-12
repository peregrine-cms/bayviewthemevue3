<template>
  <table class="bay-blktable" :data-per-path="model.path">
    <thead>
      <tr>
        <th>{{ model.datelabel || 'Datum' }}</th>
        <th>{{ model.eventlabel || 'Event' }}</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(item, i) in model.items" :key="item.date + item.event">
        <td :data-per-inline="`model.items.${i}.date`">{{ item.date }}</td>
        <td :data-per-inline="`model.items.${i}.event`">{{ item.event }}</td>
      </tr>
    </tbody>
  </table>
</template>

<script setup>
defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})
</script>

<style>
.bay-blktable {
  width: 100%;
  border-collapse: collapse;
}

.bay-blktable th {
  font-family: var(--bay-font-heading);
  font-size: 0.95rem;
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 0.06em;
  text-align: left;
  border-bottom: 1px solid var(--bay-line);
  padding: 0 12px 12px 0;
}

.bay-blktable td {
  font-size: 0.92rem;
  padding: 13px 12px 13px 0;
  border-bottom: 1px solid rgba(16, 16, 16, 0.15);
  overflow-wrap: anywhere;
}

.bay-blktable td:first-child {
  white-space: nowrap;
  width: 34%;
}

/* Narrow phones (Z Fold cover screen ~344px, iPhone SE 320px): a nowrap first
   column forces the table's min-content width past the viewport and the whole
   page scrolls sideways. Let it wrap instead. */
@media (max-width: 440px) {
  .bay-blktable td:first-child {
    white-space: normal;
    width: 38%;
  }
}
</style>
