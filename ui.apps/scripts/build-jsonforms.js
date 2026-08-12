/*
 * Bundles JSON Forms 3.x into browser globals for the `form` component.
 *
 * The Vue 2 theme could vendor these straight out of npm: @jsonforms/vue2 and
 * friends shipped prebuilt UMD files. The 3.x packages ship ESM and CJS only,
 * so the IIFE bundles have to be produced here.
 *
 * Output (into etc/felibs/<felib>/dependencies/, loaded on demand by
 * zzBayDeps.js only on pages that actually contain a form):
 *
 *   jsonforms-core.js          -> window.JSONFormsCore
 *   jsonforms-vue3.js          -> window.JSONFormsVue3
 *   jsonforms-vue3-vanilla.js  -> window.JSONFormsVue3Vanilla
 *
 * `vue` stays external and resolves to the window.Vue global the renderer
 * loads, so the form shares the one Vue runtime instead of embedding a second
 * copy -- two Vue instances would each have their own reactivity, and the
 * injected JSON Forms context would not cross between them.
 *
 * Run: node scripts/build-jsonforms.js
 * Requires the @jsonforms/* packages and rollup as devDependencies.
 */
import { rollup } from 'rollup'
import { nodeResolve } from '@rollup/plugin-node-resolve'
import commonjs from '@rollup/plugin-commonjs'
import json from '@rollup/plugin-json'
import terser from '@rollup/plugin-terser'
import path from 'path'
import { fileURLToPath } from 'url'

const HERE = path.dirname(fileURLToPath(import.meta.url))
const OUT = path.join(
  HERE, '..', 'src', 'main', 'content', 'jcr_root', 'etc', 'felibs',
  'bayviewthemevue3', 'dependencies')

const BUNDLES = [
  {
    input: '@jsonforms/core',
    file: 'jsonforms-core.js',
    name: 'JSONFormsCore',
    external: {},
  },
  {
    input: '@jsonforms/vue',
    file: 'jsonforms-vue3.js',
    name: 'JSONFormsVue3',
    external: { vue: 'Vue', '@jsonforms/core': 'JSONFormsCore' },
  },
  {
    input: '@jsonforms/vue-vanilla',
    file: 'jsonforms-vue3-vanilla.js',
    name: 'JSONFormsVue3Vanilla',
    external: {
      vue: 'Vue',
      '@jsonforms/core': 'JSONFormsCore',
      '@jsonforms/vue': 'JSONFormsVue3',
    },
  },
]

for (const b of BUNDLES) {
  const externals = Object.keys(b.external)
  const bundle = await rollup({
    input: b.input,
    external: externals,
    // Minified, unlike the Vue 2 theme's vendored UMD files: @jsonforms/core
    // 3.x inlines ajv and its lodash usages, so the raw bundle is ~700 kB.
    plugins: [nodeResolve({ browser: true }), commonjs(), json(), terser()],
    onwarn(w, warn) {
      // lodash and ajv are full of these and they are not actionable here
      if (w.code === 'CIRCULAR_DEPENDENCY' || w.code === 'THIS_IS_UNDEFINED') return
      warn(w)
    },
  })
  const { output } = await bundle.write({
    file: path.join(OUT, b.file),
    format: 'iife',
    name: b.name,
    globals: b.external,
    exports: 'named',
    banner: `/* ${b.input} bundled for Peregrine felib delivery -> window.${b.name} */`,
  })
  await bundle.close()
  const kb = Math.round(output[0].code.length / 1024)
  console.log(`  ${b.file.padEnd(26)} ${String(kb).padStart(4)} kB  -> window.${b.name}`)
}
