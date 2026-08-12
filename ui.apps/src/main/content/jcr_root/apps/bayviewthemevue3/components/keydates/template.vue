<template>
  <section class="bay-keydates" :data-per-path="model.path">
    <div class="perIsEditAndEmpty" v-if="isEditAndEmpty">No dates defined</div>
    <div class="bay-container" v-else>
      <h2 class="bay-keydates__title" v-if="model.title" data-per-inline="model.title">{{ model.title }}</h2>
      <div class="bay-keydates__grid" :class="{ 'bay-keydates__grid--swap': model.swap === 'true' || model.swap === true }">
        <table class="bay-keydates__table" v-if="isIcal || (model.items && model.items.length)">
          <thead>
            <tr>
              <th>{{ model.datelabel || 'Datum' }}</th>
              <th>{{ model.eventlabel || 'Event' }}</th>
            </tr>
          </thead>
          <tbody v-if="!isIcal">
            <tr v-for="(item, i) in model.items" :key="item.date + item.event">
              <td :data-per-inline="`model.items.${i}.date`">{{ item.date }}</td>
              <td :data-per-inline="`model.items.${i}.event`">{{ item.event }}</td>
            </tr>
          </tbody>
          <tbody v-else>
            <tr v-if="icalLoading"><td colspan="2">Termine werden geladen &hellip;</td></tr>
            <tr v-if="icalError"><td colspan="2">{{ icalError }}</td></tr>
            <tr v-for="ev in icalEvents" :key="ev.title + ev.start.getTime()">
              <td>{{ fmtRange(ev) }}</td>
              <td>{{ ev.title }}</td>
            </tr>
          </tbody>
        </table>
        <div class="bay-keydates__imgwrap" v-if="model.image">
          <bay-media img-class="bay-keydates__img"
                     :src="model.image" :alt="model.imagealt" :video="model.videourl"
                     :autoplay="model.videoautoplay"
                     :focusx="model.focusx" :focusy="model.focusy" :zoom="model.zoom"
                     :widths="[300, 600, 900, 1200]"
                     sizes="(max-width: 820px) 100vw, 38vw"></bay-media>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref, computed, watch, onMounted } from 'vue'

// The renderer exposes these on `window`; `<script setup>` is a module, so
// unlike a Vue 2 component it cannot pick them up from the global scope.
const $peregrineApp = window.$peregrineApp
const $helper = $peregrineApp.helpers

const MONTHS = ['Januar', 'Februar', 'M\u00e4rz', 'April', 'Mai', 'Juni',
  'Juli', 'August', 'September', 'Oktober', 'November', 'Dezember']

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const icalEvents = ref([])
const icalLoading = ref(false)
const icalError = ref(null)

const isIcal = computed(() => props.model.source === 'ical')

const mediaStyle = computed(() => {
  var fp = (props.model.focusx || 50) + '% ' + (props.model.focusy || 50) + '%'
  var s = { objectPosition: fp }
  var z = parseInt(props.model.zoom || '100', 10)
  if (z > 100) {
    s.transform = 'scale(' + (z / 100) + ')'
    s.transformOrigin = fp
  }
  return s
})

const isEditAndEmpty = computed(() => {
  if (!$peregrineApp.isAuthorMode()) return false
  return $helper.areAllEmpty(props.model.title, props.model.items)
})

function loadIcal() {
  if (!window.BayCalendar) {
    icalError.value = 'Kalender-Bibliothek nicht geladen.'
    return
  }
  icalLoading.value = true
  icalError.value = null
  var days = parseInt(props.model.daysahead || '120', 10)
  window.BayCalendar.upcomingGatherings('/bin/bayviewthemevue3/calendar', new Date(), days)
    .then(function (events) {
      var max = parseInt(props.model.maxitems || '0', 10)
      icalEvents.value = max > 0 ? events.slice(0, max) : events
      icalLoading.value = false
    })
    .catch(function () {
      icalLoading.value = false
      icalError.value = 'Termine konnten nicht geladen werden.'
    })
}

function fmtRange(ev) {
  var s = ev.start
  var e = ev.end
  var sameDay = s.getFullYear() === e.getFullYear() &&
    s.getMonth() === e.getMonth() && s.getDate() === e.getDate()
  if (sameDay) return s.getDate() + '. ' + MONTHS[s.getMonth()]
  if (s.getMonth() === e.getMonth()) return s.getDate() + '.\u2013' + e.getDate() + '. ' + MONTHS[s.getMonth()]
  return s.getDate() + '. ' + MONTHS[s.getMonth()] + ' \u2013 ' + e.getDate() + '. ' + MONTHS[e.getMonth()]
}

// Vue 2 watched the string path 'model.source'; Vue 3 takes a getter instead.
watch(() => props.model.source, () => {
  if (isIcal.value) loadIcal()
})

onMounted(() => {
  if (isIcal.value) loadIcal()
})
</script>

<style>
.bay-keydates {
  background: var(--bay-cream);
  padding: clamp(40px, 6vw, 72px) 0;
}

.bay-keydates__title {
  font-size: var(--bay-band);
  margin-bottom: clamp(28px, 4vw, 48px);
}

.bay-keydates__grid {
  display: grid;
  grid-template-columns: minmax(0, 1fr) minmax(280px, 38%);
  gap: clamp(24px, 4vw, 48px);
  align-items: start;
}

.bay-keydates__table {
  width: 100%;
  border-collapse: collapse;
}

.bay-keydates__table th {
  font-family: var(--bay-font-heading);
  font-size: 0.95rem;
  font-weight: 500;
  text-transform: uppercase;
  letter-spacing: 0.06em;
  text-align: left;
  border-bottom: 1px solid var(--bay-line);
  padding: 0 12px 12px 0;
}

.bay-keydates__table td {
  font-size: 0.92rem;
  padding: 13px 12px 13px 0;
  border-bottom: 1px solid rgba(16, 16, 16, 0.15);
  overflow-wrap: anywhere;
}

.bay-keydates__table td:first-child {
  white-space: nowrap;
  width: 34%;
}

.bay-keydates__imgwrap {
  width: 100%;
  height: 100%;
  min-height: 320px;
  overflow: hidden;
}

.bay-keydates__img {
  width: 100%;
  height: 100%;
  min-height: 320px;
  object-fit: cover;
  display: block;
}

@media (max-width: 820px) {
  .bay-keydates__grid {
    grid-template-columns: minmax(0, 1fr);
  }
  .bay-keydates__img {
    min-height: 220px;
  }
}

/* Narrow phones (Z Fold cover screen ~344px, iPhone SE 320px): a nowrap date
   column forces the table's min-content width past the viewport and the whole
   page scrolls sideways. Let the date wrap instead. */
@media (max-width: 440px) {
  .bay-keydates__table td:first-child {
    white-space: normal;
    width: 38%;
  }
}

/* Swapped variant: mirror the two columns (desktop only) */
@media (min-width: 821px) {
  .bay-keydates__grid--swap {
    grid-template-columns: minmax(280px, 38%) 1fr;
  }
  .bay-keydates__grid--swap > :first-child {
    order: 2;
  }
}
</style>
