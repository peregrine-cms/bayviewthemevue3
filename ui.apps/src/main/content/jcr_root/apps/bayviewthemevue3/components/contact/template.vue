<template>
  <section class="bay-contact" :data-per-path="model.path">
    <div class="perIsEditAndEmpty" v-if="isEditAndEmpty">No contact form defined</div>
    <div class="bay-contact__grid" :class="{ 'bay-contact__grid--swap': model.swap === 'true' || model.swap === true }" v-else>
      <div class="bay-contact__media">
        <h2 class="bay-contact__title" v-if="model.title" data-per-inline="model.title">{{ model.title }}</h2>
        <bay-media v-if="model.image" img-class="bay-contact__img"
                   :src="model.image" :alt="model.imagealt" :video="model.videourl"
                   :autoplay="model.videoautoplay"
                   :focusx="model.focusx" :focusy="model.focusy" :zoom="model.zoom"
                   :widths="[300, 600, 900, 1200]"
                   sizes="(max-width: 820px) 100vw, 42vw"></bay-media>
      </div>
      <form class="bay-contact__form" :action="model.action || '#'" method="post" @submit="onSubmit">
        <label class="bay-contact__label" for="bay-ct-name">{{ model.namelabel || 'Name' }}</label>
        <input class="bay-contact__input" id="bay-ct-name" name="name" type="text" v-model="name">
        <label class="bay-contact__label" for="bay-ct-email">{{ model.emaillabel || 'E-Mail' }}</label>
        <input class="bay-contact__input" id="bay-ct-email" name="email" type="email" v-model="email">
        <label class="bay-contact__label" for="bay-ct-msg">{{ model.messagelabel || 'Nachricht' }}</label>
        <textarea class="bay-contact__input bay-contact__textarea" id="bay-ct-msg" name="message" v-model="message"></textarea>
        <input type="hidden" name="_subject" :value="'Kontaktanfrage von ' + (name || 'Website')">
        <!-- Honeypot: see register. Hidden from users, filled by bots. -->
        <input type="text" name="_gotcha" tabindex="-1" autocomplete="off" aria-hidden="true" class="bay-hp">
        <button class="bay-contact__submit" type="submit" :disabled="status === 'sending'">{{ status === 'sending' ? (model.sendingtext || 'Sending…') : (model.buttontext || 'Send') }}</button>
        <p class="bay-contact__status" role="status" aria-live="polite" v-if="status === 'ok' || status === 'error'">{{ status === 'ok' ? (model.successtext || 'Danke! Wir melden uns bei Dir.') : (model.errortext || 'Das hat leider nicht geklappt. Bitte versuche es erneut oder schreib uns direkt.') }}</p>
        <p class="bay-contact__note" v-if="model.note">{{ model.note }}</p>
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
const email = ref('')
const message = ref('')
const status = ref('')

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
  email.value = ''
  message.value = ''
}

function onSubmit(e) {
  const action = props.model.action || ''
  if (action.indexOf('mailto:') === 0) {
    e.preventDefault()
    const subject = encodeURIComponent('Kontaktanfrage von ' + name.value)
    const body = encodeURIComponent(message.value + '\n\n' + name.value + ' <' + email.value + '>')
    window.location.href = action + '?subject=' + subject + '&body=' + body
    return
  }
  if (action && action !== '#') postToEndpoint(e, action)
}
</script>

<style>
.bay-contact__grid {
  display: grid;
  grid-template-columns: minmax(280px, 42%) 1fr;
}

.bay-contact__media {
  position: relative;
  background: var(--bay-teal);
  color: var(--bay-on-teal);
  min-height: 480px;
  overflow: hidden;
}

.bay-contact__title {
  position: absolute;
  z-index: 1;
  top: 28px;
  left: 28px;
  color: var(--bay-on-teal);
  font-size: var(--bay-band);
  max-width: 8ch;
}

.bay-contact__img {
  position: absolute;
  inset: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  opacity: 0.9;
}

.bay-contact__form {
  border: 1px solid var(--bay-line);
  margin: clamp(24px, 4vw, 56px);
  padding: clamp(20px, 3vw, 40px);
  display: flex;
  flex-direction: column;
}

.bay-contact__label {
  font-size: 0.95rem;
  margin: 0 0 8px;
}

.bay-contact__input {
  background: transparent;
  border: 1px solid rgba(16, 16, 16, 0.4);
  font: inherit;
  color: var(--bay-ink);
  padding: 11px 12px;
  margin-bottom: 24px;
}

.bay-contact__input:focus {
  outline: 2px solid var(--bay-teal);
  outline-offset: 1px;
}

.bay-contact__textarea {
  min-height: 100px;
  resize: vertical;
}

.bay-contact__submit {
  background: #0d0d0d;
  color: #ffffff;
  font: inherit;
  font-size: 1.05rem;
  border: 0;
  padding: 14px 26px;
  cursor: pointer;
}

.bay-contact__submit:hover {
  background: #2a2a2a;
}

.bay-contact__status {
  font-size: 0.85rem;
  font-weight: 500;
  margin: 8px 0 0;
}

.bay-contact__note {
  font-size: 0.75rem;
  color: var(--bay-ink-soft);
  text-align: center;
  margin: 10px 0 0;
}

@media (max-width: 820px) {
  .bay-contact__grid {
    grid-template-columns: minmax(0, 1fr);
  }
  .bay-contact__media {
    min-height: 320px;
  }
}

/* Swapped variant: mirror the two columns (desktop only) */
@media (min-width: 821px) {
  .bay-contact__grid--swap {
    grid-template-columns: 1fr minmax(280px, 42%);
  }
  .bay-contact__grid--swap > :first-child {
    order: 2;
  }
}
</style>
