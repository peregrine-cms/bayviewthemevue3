# Bayview Theme (Vue 3)

> Vue 3 port of `bayviewthemevue2`: same dialogs, models, content and CSS, with
> `<script setup>` components for the
> [pagerenderer-vue3](https://github.com/peregrine-cms/pagerenderer-vue3)
> renderer.

A muted-teal club theme for Peregrine CMS — a conversion of the "Bayview FC"
design study, carrying content and imagery from
[taekwondobern.ch](https://taekwondobern.ch/). Follows the capstheme
architecture (fragments + `percli hatch3 (dialogs/models only)` +
pagerendervue3).

## Design language

- Muted teal panels (`#7fa6aa`), cream bands (`#f1eee7`), white sections
- Uppercase Archivo headings, light Poppins body text
- Square corners, thin 1px outlined boxes and form frames, pill CTAs
- Tokens in `etc/felibs/bayviewthemevue3/css/variables.css` (`--bay-*`)

## Components (27)

`page`, `header`, `footer`, `pagenav`, `herosplit`, `splitrow`, `band`,
`threecols`, `legends`, `sponsorband`, `leagues`, `keydates`, `gallery`,
`contact`, `register`, `form`, `eventlist`, `schedule`, `classtimes`,
`blkheading`, `blktext`, `blkimage`, `blkbutton`, `blklogobox`, `blktable`,
`blktiles`, `blkspacer`

All content components are hand-maintained `template.vue` files (no hatch.js);
hatch3 generates dialogs, sample `.content.xml` and Sling models from
`model.json`/`sample.json`.

Not carried over from the Vue 2 theme: `instafeed` and its Instagram sync
tooling.

## Assets are placeholders

Every image under `content/bayviewthemevue3/assets/` — photographs, logos,
favicons and launcher icons — is a generated placeholder. The theme was built
against a real club's site, and that imagery is not ours to redistribute.

Placeholders keep the **original pixel dimensions**, because the components
carry `focusx`/`focusy` focal points and Cloudflare `srcset` widths, and CSS
that reacts to aspect ratio. Dropping in real photography at the same sizes
gives you the intended layout with no other changes.

The component thumbnails under `apps/` are abstract wireframes of each
component's layout, not photographs, so they are the originals.

## What the Vue 3 port changed

The Sling models, dialogs, `model.json`/`sample.json`, content and CSS are
**unchanged** — the port is confined to the component scripts and the felib
plumbing.

| Vue 2 | Vue 3 |
| --- | --- |
| `export default { props, data, computed, methods }` | `<script setup>` with `defineProps`/`ref`/`computed` |
| `this.$helper`, bare `$peregrineApp` in scripts | read off `window` (see any ported component's preamble) |
| `Vue.mixin()` in `zzPerVisibility.js` | `$peregrineApp.registerAppExtension(app => app.mixin(...))` |
| `Vue.component('bay-media')` in `zzBayMedia.js` | `window.cmpBayMedia` + `$peregrineApp.loadComponent('bay-media')` |
| `$bayImg`/`$baySrcset` via global mixin | bound per component off `window.BayImg` |
| `ref="navlinks"` inside `v-for` (auto-array) | function ref (`:ref="setNavLink"`) in `header` |
| `key` on the child of `<template v-for>` | `key` on the `<template>` itself |
| `@jsonforms/vue2` + separate lodash | `@jsonforms/vue` 3.x, lodash inlined by core |
| `rollup-plugin-vue` v4 | `@vitejs/plugin-vue` |

`$helper` still resolves inside **templates** — the renderer keeps it on
`config.globalProperties`. Only script blocks needed rebinding, because
`<script setup>` compiles to a module and has no `with(this)` fallback.

## Build & Deploy

```bash
mvn clean install
```

```bash
mvn clean install -PautoInstallPackage
```

After deploying: `http://localhost:8080/content/bayviewthemevue3/pages/index.html`

## Component Workflow

```bash
# 1. Edit fragments/<name>/{model.json, sample.json, template.vue}
# 2. Regenerate dialogs/models (percli checkout in ~/ws/percli):
node ~/ws/percli/bin/percli-hatch3 <name>     # or '*' for all
# 3. Sync the hand-maintained Vue template and rebuild the felib:
cp fragments/<name>/template.vue ui.apps/src/main/content/jcr_root/apps/bayviewthemevue3/components/<name>/
cd ui.apps && npm run build   # scripts/buildvue3.js (rollup + @vitejs/plugin-vue)
```

`fragments/` is the source of truth for templates; the copies under
`ui.apps/.../components/` are what the build compiles. Keep them in step.

## Rebuilding the JSON Forms bundles

Only needed when the `@jsonforms/*` versions change — the built files are
committed.

```bash
cd ui.apps && npm run build:jsonforms
```

JSON Forms 3.x ships ESM/CJS only (the 2.x packages shipped ready-made UMD
files), so `scripts/build-jsonforms.js` bundles the three globals the `form`
component expects. They are loaded on demand by `zzBayDeps.js`, only on pages
that actually contain a form.
