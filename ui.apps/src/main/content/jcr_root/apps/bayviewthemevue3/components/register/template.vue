<template>
  <section class="bay-register" :data-per-path="model.path">
    <div class="perIsEditAndEmpty" v-if="isEditAndEmpty">No register form defined</div>
    <div class="bay-container bay-register__grid" :class="{ 'bay-register__grid--swap': model.swap === 'true' || model.swap === true }" v-else>
      <div class="bay-register__left">
        <h2 class="bay-register__title" v-if="model.title" data-per-inline="model.title">{{ model.title }}</h2>
        <div class="bay-register__text" v-if="model.text" v-html="model.text" :data-per-inline="`model.text`"></div>
      </div>
      <form class="bay-register__form" :action="model.action || '#'" method="post" @submit="onSubmit">
        <label class="bay-register__label" for="bay-reg-name">{{ model.namelabel || 'Name' }}</label>
        <input class="bay-register__input" id="bay-reg-name" name="name" type="text" v-model="name">
        <label class="bay-register__label" for="bay-reg-age">{{ model.agelabel || 'Alter' }}</label>
        <!-- type=number brings up the numeric keypad on mobile; the range is
             wide on purpose (Tiger training starts at 4, Masters has no top). -->
        <input class="bay-register__input" id="bay-reg-age" name="age" type="number"
               inputmode="numeric" min="3" max="99" v-model="age">
        <label class="bay-register__label" for="bay-reg-email">{{ model.emaillabel || 'E-Mail' }}</label>
        <input class="bay-register__input" id="bay-reg-email" name="email" type="email" v-model="email">
        <template v-if="model.leagues && model.leagues.length">
          <p class="bay-register__label">{{ model.leaguelabel || 'Training wählen' }}</p>
          <button type="button" class="bay-register__option" :class="{ 'bay-register__option--active': league === opt.text }"
                  v-for="opt in model.leagues" :key="opt.text" @click="league = opt.text">{{ opt.text }}</button>
        </template>
        <template v-if="model.levels && model.levels.length">
          <p class="bay-register__label">{{ model.levellabel || 'Niveau' }}</p>
          <button type="button" class="bay-register__option" :class="{ 'bay-register__option--active': level === opt.text }"
                  v-for="opt in model.levels" :key="opt.text" @click="level = opt.text">{{ opt.text }}</button>
        </template>
        <!-- league/level are chosen with buttons, not inputs, so FormData would
             not carry them to the endpoint. Mirror them into hidden fields. -->
        <input type="hidden" name="training" :value="league">
        <input type="hidden" name="niveau" :value="level">
        <input type="hidden" name="_subject" :value="'Anmeldung ' + (name || 'Website')">
        <!-- Honeypot: bots fill every field they find. Real users never see it,
             and Formspree drops the submission when it arrives non-empty. -->
        <input type="text" name="_gotcha" tabindex="-1" autocomplete="off" aria-hidden="true" class="bay-hp">
        <button class="bay-register__submit" type="submit" :disabled="status === 'sending'">{{ status === 'sending' ? (model.sendingtext || 'Wird gesendet…') : (model.buttontext || 'Anmelden') }}</button>
        <p class="bay-register__status" role="status" aria-live="polite" v-if="status === 'ok' || status === 'error'">{{ status === 'ok' ? (model.successtext || 'Danke! Wir melden uns bei Dir.') : (model.errortext || 'Das hat leider nicht geklappt. Bitte versuche es erneut oder schreib uns direkt.') }}</p>
        <p class="bay-register__note" v-if="model.note">{{ model.note }}</p>
      </form>
    </div>
  </section>
</template>

<script setup>
import { ref, computed } from 'vue'

// The renderer exposes these on `window`; `<script setup>` is a module, so
// unlike a Vue 2 component it cannot pick them up from the global scope.
const $peregrineApp = window.$peregrineApp
const $helper = $peregrineApp.helpers

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const name = ref('')
const age = ref('')
const email = ref('')
const league = ref('')
const level = ref('')
const status = ref('')

const isEditAndEmpty = computed(() => {
  if (!$peregrineApp.isAuthorMode()) return false
  return $helper.areAllEmpty(props.model.title, props.model.action)
})

/*
 * Anything that is not a mailto: is treated as a JSON form endpoint
 * (Formspree and friends). A plain HTML POST would work too, but it
 * navigates the visitor to the provider's thank-you page and off the site;
 * `Accept: application/json` makes Formspree answer with JSON instead so we
 * can stay put and render our own confirmation.
 */
function postToEndpoint(e, action) {
  e.preventDefault()
  var form = e.target
  status.value = 'sending'
  fetch(action, {
    method: 'POST',
    body: new FormData(form),
    headers: { Accept: 'application/json' }
  }).then(function (r) {
    if (!r.ok) throw new Error('HTTP ' + r.status)
    status.value = 'ok'
    form.reset()
    resetFields()
  }).catch(function () {
    // Never leave the visitor stuck on "sending" — the note under the form
    // still points at the club's e-mail address as a fallback.
    status.value = 'error'
  })
}

function resetFields() {
  name.value = ''
  age.value = ''
  email.value = ''
  league.value = ''
  level.value = ''
}

function onSubmit(e) {
  const action = props.model.action || ''
  if (action.indexOf('mailto:') === 0) {
    e.preventDefault()
    const subject = encodeURIComponent('Anmeldung ' + name.value)
    const body = encodeURIComponent(
      'Name: ' + name.value + '\nAlter: ' + age.value + '\nE-Mail: ' + email.value +
      '\nTraining: ' + league.value + '\nNiveau: ' + level.value)
    window.location.href = action + '?subject=' + subject + '&body=' + body
    return
  }
  if (action && action !== '#') postToEndpoint(e, action)
}
</script>

<style>
.bay-register {
  background: var(--bay-teal);
  color: var(--bay-on-teal);
}

.bay-register__grid {
  display: grid;
  grid-template-columns: 1fr minmax(320px, 46%);
  gap: clamp(28px, 5vw, 72px);
  padding-top: clamp(32px, 5vw, 64px);
  padding-bottom: clamp(32px, 5vw, 64px);
}

.bay-register__left {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.bay-register__title {
  font-size: var(--bay-band);
  color: var(--bay-on-teal);
}

.bay-register__text {
  max-width: 34ch;
  font-size: 0.95rem;
  margin-top: 80px;
}

.bay-register__text p {
  margin: 0;
}

.bay-register__form {
  border: 1px solid rgba(255, 255, 255, 0.9);
  padding: clamp(20px, 3vw, 36px);
  display: flex;
  flex-direction: column;
}

.bay-register__label {
  font-family: var(--bay-font-heading);
  font-size: 0.78rem;
  font-weight: 600;
  letter-spacing: 0.02em;
  margin: 0 0 8px;
}

/* Overlays are BLACK, not white. The panel is --bay-teal (#557d81), which
   gives white text 4.53:1 — only just over AA. Lightening it with a white
   overlay pushed the fields back under: the inputs landed on #698d90 (3.61:1)
   and the option buttons on #709295 (3.36:1), both flagged by axe. Darkening
   instead keeps white text well clear (5.6:1 / 6.0:1) and reads more like a
   recessed field anyway. Border alpha raised 0.55 -> 0.7 so the outline still
   separates from the now-darker fill (WCAG 1.4.11 non-text contrast). */
.bay-register__input {
  background: rgba(0, 0, 0, 0.12);
  border: 1px solid rgba(255, 255, 255, 0.7);
  color: var(--bay-on-teal);
  font: inherit;
  padding: 10px 12px;
  margin-bottom: 20px;
}

.bay-register__input:focus {
  outline: 2px solid #ffffff;
  outline-offset: 1px;
}

.bay-register__option {
  background: rgba(0, 0, 0, 0.16);
  border: 0;
  color: var(--bay-on-teal);
  font: inherit;
  font-size: 0.9rem;
  text-align: left;
  padding: 10px 12px;
  margin-bottom: 8px;
  cursor: pointer;
}

.bay-register__option:hover {
  background: rgba(0, 0, 0, 0.28);
}

.bay-register__option--active {
  background: rgba(255, 255, 255, 0.9);
  color: var(--bay-ink);
}

.bay-register__submit {
  background: #ffffff;
  color: var(--bay-ink);
  font-family: var(--bay-font-heading);
  font-weight: 600;
  font-size: 0.9rem;
  border: 0;
  border-radius: 999px;
  padding: 13px 26px;
  margin-top: 18px;
  cursor: pointer;
}

.bay-register__submit:hover {
  background: var(--bay-cream);
}

.bay-register__status {
  font-size: 0.85rem;
  font-weight: 500;
  margin: 8px 0 0;
}

/* Honeypot: off-screen rather than display:none, because some bots skip
   fields that are not rendered at all. */
.bay-hp {
  position: absolute;
  left: -9999px;
  width: 1px;
  height: 1px;
  opacity: 0;
}

.bay-register__note {
  font-size: 0.7rem;
  /* No opacity here. Faded white on teal computed to #e6ecec = 3.79:1 and
     failed AA at 11.2px. Opacity on text multiplies against whatever is behind
     it, so it silently eats contrast; full white on #557d81 is 4.53:1. */
  text-align: center;
  margin: 10px 0 0;
}

@media (max-width: 820px) {
  .bay-register__grid {
    grid-template-columns: minmax(0, 1fr);
  }
  .bay-register__text {
    margin-top: 24px;
  }
}

/* Swapped variant: mirror the two columns (desktop only) */
@media (min-width: 821px) {
  .bay-register__grid--swap {
    grid-template-columns: minmax(320px, 46%) 1fr;
  }
  .bay-register__grid--swap > :first-child {
    order: 2;
  }
}
</style>
