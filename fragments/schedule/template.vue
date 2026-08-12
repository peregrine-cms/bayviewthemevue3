<template>
  <section class="bay-schedule" :data-per-path="model.path">
    <div class="bay-container">
      <div class="bay-schedule__head">
        <h2 class="bay-schedule__title" v-if="model.title" data-per-inline="model.title">{{ model.title }}</h2>
        <div class="bay-schedule__nav">
          <button type="button" class="bay-schedule__navbtn" aria-label="Vorherige Woche" @click="weekOffset--">&lsaquo;</button>
          <button type="button" class="bay-schedule__quick" :class="{ 'bay-schedule__quick--active': weekOffset === 0 }"
            @click="weekOffset = 0">Diese Woche</button>
          <button type="button" class="bay-schedule__quick" :class="{ 'bay-schedule__quick--active': weekOffset === 1 }"
            @click="weekOffset = 1">N&auml;chste Woche</button>
          <button type="button" class="bay-schedule__navbtn" aria-label="N&auml;chste Woche" @click="weekOffset++">&rsaquo;</button>
        </div>
      </div>
      <p class="bay-schedule__weeklabel">{{ weekLabel }}</p>
      <p class="bay-schedule__state" v-if="loading">Wochenplan wird geladen &hellip;</p>
      <p class="bay-schedule__state" v-if="error">{{ error }}</p>
      <div class="bay-schedule__grid" v-if="!loading && !error">
        <div class="bay-schedule__day" v-for="day in visibleDays" :key="day.iso"
          :class="{ 'bay-schedule__day--today': day.isToday }">
          <div class="bay-schedule__dayhead">
            <span class="bay-schedule__dayname">{{ day.name }}</span>
            <span class="bay-schedule__daydate">{{ day.label }}</span>
          </div>
          <div class="bay-schedule__empty" v-if="!day.classes.length">&mdash;</div>
          <div class="bay-schedule__class" v-for="c in day.classes" :key="c.name + c.start.getTime()">
            <span class="bay-schedule__time">{{ fmtTime(c.start) }}&ndash;{{ fmtTime(c.end) }}</span>
            <span class="bay-schedule__name">{{ c.name }}</span>
            <span class="bay-schedule__instructor"
              v-if="c.instructor && (model.showinstructor === 'true' || model.showinstructor === true)">{{ c.instructor }}</span>
          </div>
        </div>
      </div>
    </div>
  </section>
</template>

<script setup>
import { ref, computed, watch, onMounted } from 'vue'

const DAY_NAMES = ['Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag', 'Sonntag']

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const weekOffset = ref(0)
const classes = ref([])
const loading = ref(true)
const error = ref(null)

function isoWeek(date) {
  var d = new Date(Date.UTC(date.getFullYear(), date.getMonth(), date.getDate()))
  var dayNum = d.getUTCDay() || 7
  d.setUTCDate(d.getUTCDate() + 4 - dayNum)
  var yearStart = new Date(Date.UTC(d.getUTCFullYear(), 0, 1))
  return Math.ceil((((d - yearStart) / 86400000) + 1) / 7)
}

function fmtTime(d) {
  return d.getHours() + ':' + ('0' + d.getMinutes()).slice(-2)
}

const weekStart = computed(() => {
  var now = new Date()
  var monday = new Date(now.getFullYear(), now.getMonth(), now.getDate())
  var dow = (monday.getDay() + 6) % 7 // Monday = 0
  monday.setDate(monday.getDate() - dow + weekOffset.value * 7)
  return monday
})

const weekLabel = computed(() => {
  var start = weekStart.value
  var end = new Date(start.getTime())
  end.setDate(end.getDate() + 6)
  var kw = isoWeek(start)
  return 'KW ' + kw + ' · ' + start.getDate() + '.' + (start.getMonth() + 1) + '.' +
    '–' + end.getDate() + '.' + (end.getMonth() + 1) + '.' + end.getFullYear()
})

const days = computed(() => {
  var out = []
  var today = new Date()
  for (var i = 0; i < 7; i++) {
    var d = new Date(weekStart.value.getTime())
    d.setDate(d.getDate() + i)
    var onThisDay = classes.value.filter(function (c) {
      return c.start.getFullYear() === d.getFullYear() &&
        c.start.getMonth() === d.getMonth() &&
        c.start.getDate() === d.getDate()
    })
    out.push({
      iso: d.toISOString().slice(0, 10),
      name: DAY_NAMES[i],
      label: d.getDate() + '.' + (d.getMonth() + 1) + '.',
      isToday: d.getFullYear() === today.getFullYear() &&
        d.getMonth() === today.getMonth() && d.getDate() === today.getDate(),
      classes: onThisDay
    })
  }
  return out
})

const visibleDays = computed(() => {
  if (props.model.hideempty === 'true' || props.model.hideempty === true) {
    return days.value.filter(function (d) { return d.classes.length > 0 })
  }
  return days.value
})

function loadWeek() {
  if (!window.BayCalendar) {
    loading.value = false
    error.value = 'Kalender-Bibliothek nicht geladen.'
    return
  }
  loading.value = true
  error.value = null
  var start = weekStart.value
  var end = new Date(start.getTime())
  end.setDate(end.getDate() + 7)
  window.BayCalendar.classesForRange('/bin/bayviewthemevue3/calendar', start, end)
    .then(function (found) {
      classes.value = found
      loading.value = false
    })
    .catch(function () {
      loading.value = false
      error.value = 'Wochenplan konnte nicht geladen werden.'
    })
}

watch(weekOffset, loadWeek)

onMounted(loadWeek)
</script>

<style>
.bay-schedule {
  background: var(--bay-white);
  padding: clamp(40px, 6vw, 72px) 0;
}

.bay-schedule__head {
  display: flex;
  align-items: flex-start;
  justify-content: space-between;
  gap: 20px;
  flex-wrap: wrap;
}

.bay-schedule__title {
  font-size: var(--bay-band);
  margin: 0;
}

.bay-schedule__nav {
  display: flex;
  align-items: center;
  gap: 8px;
}

.bay-schedule__navbtn {
  background: none;
  border: 1px solid var(--bay-line);
  width: 36px;
  height: 36px;
  font-size: 1.2rem;
  line-height: 1;
  cursor: pointer;
  color: var(--bay-ink);
}

.bay-schedule__navbtn:hover {
  background: var(--bay-cream);
}

.bay-schedule__quick {
  background: none;
  border: 1px solid var(--bay-line);
  font-family: var(--bay-font-heading);
  font-size: 0.75rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  padding: 9px 14px;
  cursor: pointer;
  color: var(--bay-ink);
}

.bay-schedule__quick--active {
  background: var(--bay-teal);
  border-color: var(--bay-teal);
  color: var(--bay-on-teal);
}

.bay-schedule__weeklabel {
  font-family: var(--bay-font-heading);
  font-size: 0.8rem;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  color: rgba(16, 16, 16, 0.55);
  margin: 14px 0 clamp(20px, 3vw, 36px);
}

.bay-schedule__state {
  font-size: 0.95rem;
  margin: 20px 0;
}

.bay-schedule__grid {
  display: grid;
  grid-template-columns: repeat(7, minmax(0, 1fr));
  gap: 1px;
  background: var(--bay-line);
  border: 1px solid var(--bay-line);
}

.bay-schedule__day {
  background: var(--bay-white);
  padding: 14px 12px 18px;
  min-height: 140px;
}

.bay-schedule__day--today {
  background: var(--bay-cream);
}

.bay-schedule__dayhead {
  display: flex;
  justify-content: space-between;
  align-items: baseline;
  border-bottom: 1px solid var(--bay-line);
  padding-bottom: 8px;
  margin-bottom: 10px;
}

.bay-schedule__dayname {
  font-family: var(--bay-font-heading);
  font-size: 0.75rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.08em;
}

.bay-schedule__daydate {
  font-size: 0.75rem;
  color: rgba(16, 16, 16, 0.55);
}

.bay-schedule__empty {
  color: rgba(16, 16, 16, 0.3);
  font-size: 0.85rem;
}

.bay-schedule__class {
  display: flex;
  flex-direction: column;
  margin-bottom: 12px;
}

.bay-schedule__time {
  font-family: var(--bay-font-heading);
  font-size: 0.72rem;
  font-weight: 600;
  letter-spacing: 0.04em;
  color: var(--bay-teal-deep, #4d7174);
}

.bay-schedule__name {
  font-size: 0.88rem;
  font-weight: 600;
}

.bay-schedule__instructor {
  font-size: 0.78rem;
  color: rgba(16, 16, 16, 0.55);
}

@media (max-width: 1100px) {
  .bay-schedule__grid {
    grid-template-columns: repeat(2, minmax(0, 1fr));
  }
}

@media (max-width: 640px) {
  .bay-schedule__grid {
    grid-template-columns: minmax(0, 1fr);
  }
  .bay-schedule__day {
    min-height: 0;
  }
}
</style>
