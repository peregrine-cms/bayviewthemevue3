<template>
  <div class="bay-form" :class="'bay-form--' + (model.style || 'light')" :data-per-path="model.path">
    <div class="bay-form__error" v-if="schemaError || uischemaError">
      <p v-if="schemaError">{{ schemaError }}</p>
      <p v-if="uischemaError">{{ uischemaError }}</p>
    </div>
    <form v-if="isReady" @submit.prevent.stop="onSubmit">
      <component :is="JsonForms" :key="jsonFormsKey" :data="form" :schema="schema" :uischema="uischema"
        :renderers="renderers" :validation-mode="validationMode" @change="onChange"></component>
      <button class="bay-form__submit" :class="'bay-form__submit--' + (model.submitstyle || 'black')"
        type="submit">{{ model.submittext || 'Send' }}</button>
      <p class="bay-form__success" v-if="successText">{{ successText }}</p>
      <p class="bay-form__failure" v-if="failureText">{{ failureText }}</p>
    </form>
  </div>
</template>

<script setup>
import { ref, computed, watch, shallowRef, markRaw } from 'vue'

// The renderer exposes these on `window`; `<script setup>` is a module, so
// unlike a Vue 2 component it cannot pick them up from the global scope.
const $peregrineApp = window.$peregrineApp
const $helper = $peregrineApp.helpers

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const form = ref({})
const formErrors = ref([])
const validationMode = ref('ValidateAndHide')
const schema = ref({})
const uischema = ref({})
const schemaError = ref(null)
const uischemaError = ref(null)
const failureText = ref('')
const successText = ref('')

/*
 * JSON Forms wiring, Vue 3 edition.
 *
 * The Vue 2 build registered <json-forms> globally with `Vue.component()` and
 * read `Vue.options.components` to avoid doing it twice. Both are gone in Vue 3
 * -- component registration belongs to an app instance, which a felib cannot
 * reach. Rendering the imported component through `<component :is>` instead
 * keeps it local to this component and needs no registration at all.
 *
 * `shallowRef`/`markRaw`: neither the component definition nor the renderer
 * list should be made reactive. Vue 3's proxy-based reactivity would walk the
 * whole renderer graph, which is pure overhead and breaks identity checks JSON
 * Forms does internally. (Vue 2's Object.defineProperty walk was shallower,
 * so `Object.freeze` was enough there.)
 */
const JsonForms = shallowRef(null)
const renderers = shallowRef(null)

const isReady = computed(() => !!(JsonForms.value && renderers.value))

const jsonFormsKey = computed(() =>
  JSON.stringify(schema.value).length + '-' + JSON.stringify(uischema.value).length)

function initJsonForms() {
  const core = window.JSONFormsCore
  const vue = window.JSONFormsVue3
  const vanilla = window.JSONFormsVue3Vanilla
  if (!core || !vue || !vanilla || JsonForms.value) return

  JsonForms.value = markRaw(vue.JsonForms)

  // Option-bar renderer for enums (the bay signature look) instead of
  // the vanilla <select> dropdown.
  const optionBars = markRaw({
    name: 'bay-option-bars',
    props: vue.rendererProps(),
    setup(rendererProps) {
      return vue.useJsonFormsControl(rendererProps)
    },
    template: '<div class="control bay-form__optiongroup" v-if="control.visible">' +
      '<label>{{ control.label }}<span v-if="control.required">*</span></label>' +
      '<button type="button" class="bay-form__option" v-for="opt in control.schema.enum" :key="opt"' +
      ' :class="{ \'bay-form__option--active\': control.data === opt }"' +
      ' @click="handleChange(control.path, opt)">{{ opt }}</button>' +
      '</div>'
  })

  const rs = vanilla.vanillaRenderers.slice()
  rs.push({ tester: core.rankWith(20, core.isEnumControl), renderer: optionBars })
  renderers.value = markRaw(rs)
}

function onChange(ev) {
  form.value = ev.data
  formErrors.value = ev.errors || []
}

function updateSchema() {
  if (!props.model.schema) return
  window.axios.get($helper.pathToUrl(props.model.schema))
    .then(function (r) { schema.value = r.data; schemaError.value = null })
    .catch(function (e) { schemaError.value = 'Schema: ' + e.message })
}

function updateUischema() {
  if (!props.model.uischema) return
  window.axios.get($helper.pathToUrl(props.model.uischema))
    .then(function (r) { uischema.value = r.data; uischemaError.value = null })
    .catch(function (e) { uischemaError.value = 'UI-Schema: ' + e.message })
}

function onSubmit() {
  failureText.value = ''
  successText.value = ''
  if (formErrors.value.length > 0) {
    validationMode.value = 'ValidateAndShow'
    return
  }
  var fn = props.model.submitfunction
  if (fn && fn !== '' && fn !== 'onSubmit') {
    var objs = fn.split('.')
    var parent = window
    var obj = objs.shift()
    while (obj && parent[obj]) {
      if (objs.length === 0) {
        try {
          var result = parent[obj](props.model, form.value)
          if (result === false) failureText.value = props.model.failmessage
          else if (result === true && props.model.successpage) $peregrineApp.loadContent(props.model.successpage)
          else if (result === true) successText.value = props.model.successmessage || ''
        } catch (err) {
          failureText.value = props.model.failmessage
        }
        return
      }
      parent = parent[obj]
      obj = objs.shift()
    }
    failureText.value = props.model.failmessage
    return
  }
  // Default: JSON POST to the endpoint (Formspree-compatible)
  window.axios.post(props.model.endpointurl, form.value, {
    headers: { 'Accept': 'application/json' }
  }).then(function () {
    if (props.model.successpage) {
      $peregrineApp.loadContent(props.model.successpage)
    } else {
      form.value = {}
      successText.value = props.model.successmessage || 'OK'
    }
  }).catch(function () {
    failureText.value = props.model.failmessage
  })
}

// `immediate` runs this before mount, which is where the Vue 2 version did its
// `created()` setup. zzBayDeps.js guarantees the three bundles are loaded
// before any page containing a form renders.
watch(() => props.model, () => {
  initJsonForms()
  updateSchema()
  updateUischema()
}, { deep: true, immediate: true })
</script>

<style>
.bay-form {
  border: 1px solid var(--bay-line);
  padding: clamp(20px, 3vw, 36px);
}

/* JSON Forms vanilla renderer mapping to the bay look */
.bay-form .vertical-layout,
.bay-form .horizontal-layout {
  display: flex;
  flex-direction: column;
}

.bay-form .control {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.bay-form .control label {
  font-family: var(--bay-font-heading);
  font-size: 0.78rem;
  font-weight: 600;
  letter-spacing: 0.02em;
  margin: 0 0 8px;
}

.bay-form .control .wrapper {
  display: flex;
  flex-direction: column;
}

.bay-form .control input,
.bay-form .control textarea,
.bay-form .control select {
  width: 100%;
  box-sizing: border-box;
  background: transparent;
  border: 1px solid rgba(16, 16, 16, 0.4);
  color: var(--bay-ink);
  font: inherit;
  padding: 10px 12px;
}

.bay-form .control textarea {
  min-height: 100px;
  resize: vertical;
}

.bay-form .control .description,
.bay-form .control .validation {
  font-size: 0.75rem;
  margin-top: 4px;
}

.bay-form .control .description:empty,
.bay-form .control .validation:empty {
  display: none;
}

.bay-form__optiongroup {
  display: flex;
  flex-direction: column;
  margin-bottom: 20px;
}

.bay-form__option {
  background: rgba(16, 16, 16, 0.08);
  border: 0;
  color: inherit;
  font: inherit;
  font-size: 0.9rem;
  text-align: left;
  padding: 10px 12px;
  margin-bottom: 8px;
  cursor: pointer;
}

.bay-form__option--active {
  background: var(--bay-teal);
  color: var(--bay-on-teal);
}

.bay-form .control .validation_error {
  color: #b3261e;
}

.bay-form__submit {
  font: inherit;
  border: 0;
  padding: 14px 26px;
  margin-top: 8px;
  width: 100%;
  cursor: pointer;
}

.bay-form__submit--black {
  background: #0d0d0d;
  color: #ffffff;
  font-size: 1.05rem;
}

.bay-form__submit--black:hover {
  background: #2a2a2a;
}

.bay-form__submit--white {
  background: #ffffff;
  color: var(--bay-ink);
  font-family: var(--bay-font-heading);
  font-weight: 600;
  font-size: 0.9rem;
  border-radius: 999px;
}

.bay-form__submit--white:hover {
  background: var(--bay-cream);
}

.bay-form__success,
.bay-form__failure {
  font-size: 0.85rem;
  text-align: center;
  margin: 12px 0 0;
}

.bay-form__failure {
  color: #b3261e;
}

.bay-form__error {
  color: #b3261e;
  font-size: 0.85rem;
}

/* On-teal variant */
.bay-form--teal {
  border-color: rgba(255, 255, 255, 0.9);
}

.bay-form--teal .control input,
.bay-form--teal .control textarea,
.bay-form--teal .control select {
  background: rgba(255, 255, 255, 0.12);
  border-color: rgba(255, 255, 255, 0.55);
  color: var(--bay-on-teal);
}

.bay-form--teal .bay-form__option {
  background: rgba(255, 255, 255, 0.16);
}

.bay-form--teal .bay-form__option:hover {
  background: rgba(255, 255, 255, 0.28);
}

.bay-form--teal .bay-form__option--active {
  background: rgba(255, 255, 255, 0.9);
  color: var(--bay-ink);
}

.bay-form--teal .bay-form__failure,
.bay-form--teal .bay-form__error {
  color: #ffd7d2;
}
</style>
