/**
 * Vue 3 Component Build Script
 *
 * Compiles this theme's SFCs into IIFE bundles for the Peregrine
 * pagerendervue3 renderer, one bundle per component, with each component's
 * <style> extracted to a real stylesheet.
 *
 * Usage: node scripts/buildvue3.js <felib-name>
 *
 * Differences from the Vue 2 build this replaces:
 *
 *  - No shared normalizeComponent. `rollup-plugin-vue` v4 inlined 2.5 kB of
 *    vue-runtime-helpers into every component, which the Vue 2 build worked
 *    around by externalising it into `aaVueRuntime.js`. @vitejs/plugin-vue
 *    emits no such helper, so that whole mechanism is gone.
 *
 *  - CSS extraction is the plugin's job. The Vue 2 build needed a hand-written
 *    interceptor because `rollup-plugin-vue` v4 quietly changed `css` from a
 *    path to a boolean, so passing a path meant "inline the CSS into the JS"
 *    -- the opposite of what it read like, and the reason themeclean's css/
 *    folder is empty upstream. rollup-plugin-postcss `extract` is explicit.
 *
 * Kept deliberately:
 *
 *  - ASCII escaping of the output. The felib servlet serves iso-8859-1, so any
 *    literal umlaut in a template or stylesheet arrives mojibaked.
 *
 *  - camelCase output filenames. css.txt is ordered static-first by testing for
 *    an uppercase letter in the name, and js.txt leans on readdirSync order,
 *    which is why the theme's own scripts are prefixed `zz` to sort last.
 */
import fs from 'fs'
import path from 'path'
import { rollup } from 'rollup'
import vue from '@vitejs/plugin-vue'
import { nodeResolve } from '@rollup/plugin-node-resolve'
import esbuild from 'rollup-plugin-esbuild'
import postcss from 'rollup-plugin-postcss'

const felibName = process.argv[2]
if (!felibName) {
  console.error('Usage: node scripts/buildvue3.js <felib-name>')
  process.exit(1)
}

console.log('========== Building Vue 3 Components ==========')
console.log('FeLib:', felibName)

const basePath = './src/main/content/jcr_root/apps'
const distBasePath = './target/classes/etc/felibs/' + felibName

fs.mkdirSync(distBasePath + '/js', { recursive: true })
fs.mkdirSync(distBasePath + '/css', { recursive: true })

function asciiEscape(text) {
  return text.replace(/[-￿]/g, (c) =>
    '\\u' + c.charCodeAt(0).toString(16).padStart(4, '0'))
}

// CSS cannot use \uXXXX JS escapes; use CSS escapes (6 hex digits) instead
function asciiEscapeCss(text) {
  return text.replace(/[-￿]/g, (c) =>
    '\\' + c.charCodeAt(0).toString(16).padStart(6, '0'))
}

function camelcase(s) {
  return s.replace(/-([a-z0-9])/g, (_, c) => c.toUpperCase())
}

function findVueFiles(dir, files = []) {
  for (const item of fs.readdirSync(dir)) {
    const full = path.join(dir, item)
    if (fs.statSync(full).isDirectory()) findVueFiles(full, files)
    else if (item.endsWith('.vue')) files.push(full)
  }
  return files
}

function componentNameOf(file) {
  const normalized = file.replace(/\\/g, '/')
  const afterApps = normalized.slice(normalized.indexOf('/apps/') + 6)
  const parts = afterApps.split('/')
  parts.pop()
  return parts.join('-').toLowerCase()
}

async function compile(file) {
  const name = componentNameOf(file)
  const nameCamel = camelcase(name)
  const moduleName = 'cmp' + nameCamel.charAt(0).toUpperCase() + nameCamel.slice(1)
  const cssFile = distBasePath + '/css/' + nameCamel + '.css'
  const jsFile = distBasePath + '/js/' + nameCamel + '.js'

  console.log('Compiling:', file)
  // rollup refuses to emit an asset outside the output file's own directory,
  // so the stylesheet is extracted next to the JS and moved to css/ below.
  const cssBeside = distBasePath + '/js/' + nameCamel + '.css'

  const bundle = await rollup({
    input: file,
    // `vue` resolves to the window.Vue global the renderer felib loads. Every
    // component sharing one runtime is not just a size win: two Vue copies
    // would have separate reactivity, and provide/inject would not cross
    // between them (which is how the renderer passes peregrineApp down).
    external: ['vue'],
    plugins: [
      vue({ template: { compilerOptions: { whitespace: 'condense' } } }),
      postcss({ extract: path.resolve(cssBeside) }),
      nodeResolve(),
      esbuild({ target: 'es2020', minify: process.env.NODE_ENV === 'production' })
    ],
    onwarn(warning, warn) {
      if (warning.code === 'THIS_IS_UNDEFINED') return
      if (warning.message && warning.message.includes('sourcemap')) return
      warn(warning)
    }
  })

  await bundle.write({
    format: 'iife',
    name: moduleName,
    file: jsFile,
    globals: { vue: 'Vue' },
    banner: '/* ' + name + ' */'
  })
  await bundle.close()

  if (fs.existsSync(cssBeside)) {
    fs.renameSync(cssBeside, cssFile)
  } else if (fs.existsSync(cssFile)) {
    // component lost its <style> - do not leave a stale file behind
    fs.unlinkSync(cssFile)
  }

  for (const f of [jsFile, cssFile]) {
    if (!fs.existsSync(f)) continue
    const src = fs.readFileSync(f, 'utf8')
    const esc = f.endsWith('.css') ? asciiEscapeCss(src) : asciiEscape(src)
    if (esc !== src) fs.writeFileSync(f, esc)
  }
  console.log('  ->', jsFile)
  return { name, moduleName }
}

function copyStatic(kind, ext) {
  const src = path.join(basePath, '..', 'etc/felibs', felibName, kind)
  if (!fs.existsSync(src)) return
  for (const f of fs.readdirSync(src)) {
    if (!f.endsWith(ext)) continue
    fs.copyFileSync(path.join(src, f), path.join(distBasePath, kind, f))
    console.log('Copied static ' + kind + ':', f)
  }
}

function listFiles(sub, ext) {
  const dir = path.join(distBasePath, sub)
  if (!fs.existsSync(dir)) return []
  return fs.readdirSync(dir).filter((f) => f.endsWith(ext)).map((f) => sub + '/' + f)
}

async function main() {
  const files = findVueFiles(basePath)
  let ok = 0
  let failed = 0
  for (const file of files) {
    try {
      await compile(file)
      ok++
    } catch (e) {
      console.error('  Error in', file, '-', e.message)
      failed++
    }
  }
  copyStatic('css', '.css')
  copyStatic('js', '.js')

  // static css first in css.txt (variables/base), then component css
  const allCss = listFiles('css', '.css')
  const staticCss = allCss.filter((f) => !/[A-Z]/.test(f))
  const compCss = allCss.filter((f) => /[A-Z]/.test(f))
  fs.writeFileSync(distBasePath + '/css.txt',
    '<!-- auto generated by build -->\n' + staticCss.concat(compCss).join('\n'))
  fs.writeFileSync(distBasePath + '/js.txt',
    '<!-- auto generated by build -->\n' + listFiles('js', '.js').join('\n'))

  console.log('========== Build Complete ==========')
  console.log('Successful:', ok, 'Failed:', failed)
  if (failed > 0) process.exit(1)
}

main().catch((e) => {
  console.error(e)
  process.exit(1)
})
