<template>
  <div class="bay-eventlist" :data-per-path="model.path">
    <h2 class="bay-eventlist__title" v-if="model.title" data-per-inline="model.title">{{ model.title }}</h2>
    <p class="bay-eventlist__state" v-if="loading">Loading events &hellip;</p>
    <p class="bay-eventlist__state" v-if="error">{{ error }}</p>
    <p class="bay-eventlist__state" v-if="!loading && !error && !events.length">No upcoming events.</p>
    <table class="bay-eventlist__table" v-if="events.length">
      <thead>
        <tr>
          <th>Datum</th>
          <th>Event</th>
          <th v-if="model.showlocation === 'true' || model.showlocation === true">Ort</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="ev in events" :key="ev.title + ev.start.getTime()">
          <td>{{ fmtRange(ev) }}</td>
          <td>{{ ev.title }}</td>
          <td v-if="model.showlocation === 'true' || model.showlocation === true">{{ ev.location }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const MONTHS = ['January', 'February', 'March', 'April', 'May', 'June',
  'July', 'August', 'September', 'October', 'November', 'December']

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const events = ref([])
const loading = ref(true)
const error = ref(null)

onMounted(() => {
  if (!window.BayCalendar) {
    loading.value = false
    error.value = 'Calendar library not loaded.'
    return
  }
  var days = parseInt(props.model.daysahead || '120', 10)
  window.BayCalendar.upcomingGatherings('/bin/bayviewthemevue3/calendar', new Date(), days)
    .then(function (evts) {
      var max = parseInt(props.model.maxitems || '0', 10)
      events.value = max > 0 ? evts.slice(0, max) : evts
      loading.value = false
    })
    .catch(function () {
      loading.value = false
      error.value = 'Could not load events.'
    })
})

function fmtRange(ev) {
  var s = ev.start
  var e = ev.end
  var sameDay = s.getFullYear() === e.getFullYear() &&
    s.getMonth() === e.getMonth() && s.getDate() === e.getDate()
  if (sameDay) {
    return s.getDate() + '. ' + MONTHS[s.getMonth()]
  }
  if (s.getMonth() === e.getMonth()) {
    return s.getDate() + '.–' + e.getDate() + '. ' + MONTHS[s.getMonth()]
  }
  return s.getDate() + '. ' + MONTHS[s.getMonth()] + ' – ' +
    e.getDate() + '. ' + MONTHS[e.getMonth()]
}
</script>

<style>
.bay-eventlist__title {
  font-size: var(--bay-band);
  margin-bottom: clamp(28px, 4vw, 48px);
}

.bay-eventlist__state {
  font-size: 0.95rem;
}

.bay-eventlist__table {
  width: 100%;
  border-collapse: collapse;
}

.bay-eventlist__table th {
  font-family: var(--bay-font-heading);
  font-size: 0.95rem;
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 0.06em;
  text-align: left;
  border-bottom: 1px solid var(--bay-line);
  padding: 0 12px 12px 0;
}

.bay-eventlist__table td {
  font-size: 0.92rem;
  padding: 13px 12px 13px 0;
  border-bottom: 1px solid rgba(16, 16, 16, 0.15);
  vertical-align: top;
  overflow-wrap: anywhere;
}

.bay-eventlist__table td:first-child {
  white-space: nowrap;
  width: 28%;
}

/* Narrow phones (Z Fold cover screen ~344px, iPhone SE 320px): a nowrap date
   column forces the table's min-content width past the viewport and the whole
   page scrolls sideways. Let the date wrap instead. */
@media (max-width: 440px) {
  .bay-eventlist__table td:first-child {
    white-space: normal;
    width: 38%;
  }
}
</style>
