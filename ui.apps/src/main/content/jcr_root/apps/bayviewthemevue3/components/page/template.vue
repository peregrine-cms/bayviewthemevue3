<template>
  <div class="bay-page" v-bind:data-per-path="model.path">
    <pagerendervue3-components-placeholder
      class="per-drop-target-empty"
      v-if="renderPlaceholderEmpty"
      v-bind:model="{ path: model.path, component: 'page: drop components here', location: 'into' }"></pagerendervue3-components-placeholder>
    <!--
      The header and footer components already render <header>/<footer>, but the
      content region was a plain container, so the page had no <main> landmark
      (flagged by Lighthouse: "Document does not have a main landmark").

      themeclean-flex solves this with a per-component "section" select in the
      advanced group, letting an author pick the wrapping element. That is more
      machinery than this theme needs: there must be exactly ONE <main> per page
      and it is always the content region, so the page template names it here
      rather than asking every author to get it right on every component.
    -->
    <!-- Vue 3 wants the key on the <template> carrying v-for, not on each
         branch inside it (Vue 2 accepted it on the branches). -->
    <template v-for="child in model.children" v-bind:key="child.path">
      <main
        v-if="child.name === 'main'"
        class="bay-page__main">
        <component
          v-bind:is="child.component"
          v-bind:model="child"></component>
      </main>
      <component
        v-else
        v-bind:is="child.component"
        v-bind:model="child"></component>
    </template>
  </div>
</template>

<script setup>
import { computed } from 'vue'
// The renderer exposes these on `window`; `<script setup>` is a module, so
// unlike a Vue 2 component it cannot pick them up from the global scope.
const $peregrineApp = window.$peregrineApp
const $helper = $peregrineApp.helpers

const props = defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const renderPlaceholderEmpty = computed(() => {
    if (!$peregrineApp.isAuthorMode()) return false
    return !props.model.children || props.model.children.length === 0
})
</script>
