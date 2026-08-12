<template>
  <div class="bay-classtimes" :data-per-path="model.path">
    <span class="bay-classtimes__label" v-if="model.label">{{ model.label }}</span>
    <span class="bay-classtimes__state" v-if="loading">Loading &hellip;</span>
    <span class="bay-classtimes__state" v-if="error">{{ error }}</span>
    <ul class="bay-classtimes__list" v-if="times.length">
      <li v-for="t in times" :key="t">{{ t }}</li>
    </ul>
    <span class="bay-classtimes__state" v-if="!loading && !error && !times.length">No regular sessions right now.</span>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const DAY_NAMES = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday']

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const times = ref([])
const loading = ref(true)
const error = ref(null)

onMounted(() => {
  if (!window.BayCalendar || !props.model.classname) {
    loading.value = false
    if (!window.BayCalendar) error.value = 'Calendar not loaded.'
    return
  }
  var start = new Date()
  var end = new Date(start.getTime())
  end.setDate(end.getDate() + 7)
  window.BayCalendar.classesForRange('/bin/bayviewthemevue3/calendar', start, end)
    .then(function (classes) {
      var name = props.model.classname.trim().toLowerCase()
      var seen = {}
      var out = []
      classes.forEach(function (c) {
        if (c.name.trim().toLowerCase() !== name) return
        var key = c.start.getDay() + '-' + c.start.getHours() + ':' + c.start.getMinutes()
        if (seen[key]) return
        seen[key] = true
        out.push({
          day: c.start.getDay() === 0 ? 7 : c.start.getDay(),
          text: DAY_NAMES[c.start.getDay()] + ' ' +
            c.start.getHours() + ':' + ('0' + c.start.getMinutes()).slice(-2) + '–' +
            c.end.getHours() + ':' + ('0' + c.end.getMinutes()).slice(-2)
        })
      })
      out.sort(function (a, b) { return a.day - b.day })
      times.value = out.map(function (o) { return o.text })
      loading.value = false
    })
    .catch(function () {
      loading.value = false
      error.value = 'Could not load session times.'
    })
})
</script>

<style>
.bay-classtimes {
  margin: 18px 0;
}

.bay-classtimes__label {
  display: block;
  font-family: var(--bay-font-heading);
  font-size: 0.78rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  margin-bottom: 8px;
}

.bay-classtimes__list {
  list-style: none;
  margin: 0;
  padding: 0;
}

.bay-classtimes__list li {
  font-size: 0.95rem;
  padding: 6px 0;
  border-bottom: 1px solid rgba(16, 16, 16, 0.12);
}

.bay-classtimes__list li:last-child {
  border-bottom: 0;
}

.bay-classtimes__state {
  font-size: 0.85rem;
  opacity: 0.7;
}
</style>
