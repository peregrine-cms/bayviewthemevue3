<template>
  <nav class="bay-pagenav" :data-per-path="model.path" v-if="prev || next || model.uplink">
    <a class="bay-pagenav__link bay-pagenav__link--prev" v-if="prev" :href="prev.url">
      <span class="bay-pagenav__arrow">&lsaquo;</span>
      <span class="bay-pagenav__title">{{ prev.title }}</span>
    </a>
    <span class="bay-pagenav__spacer" v-else></span>
    <a class="bay-pagenav__up" v-if="model.uplink" :href="$helper.pathToUrl(model.uplink)">{{ model.uplabel || 'Overview' }}</a>
    <a class="bay-pagenav__link bay-pagenav__link--next" v-if="next" :href="next.url">
      <span class="bay-pagenav__title">{{ next.title }}</span>
      <span class="bay-pagenav__arrow">&rsaquo;</span>
    </a>
    <span class="bay-pagenav__spacer" v-else></span>
  </nav>
</template>

<script setup>
import { ref, onMounted } from 'vue'

defineProps({
  model: { type: Object, required: true, default: () => ({}) }
})

const prev = ref(null)
const next = ref(null)

onMounted(() => {
  var path = window.location.pathname.replace(/\.html.*$/, '')
  var idx = path.lastIndexOf('/')
  if (idx <= 0) return
  var parent = path.slice(0, idx)
  var current = path.slice(idx + 1)
  window.axios.get(parent + '.harray.2.json').then(function (r) {
    var siblings = (r.data.__children__ || []).filter(function (c) {
      return c['jcr:primaryType'] === 'per:Page'
    }).map(function (c) {
      var content = (c.__children__ || []).filter(function (k) {
        return k.__name__ === 'jcr:content'
      })[0]
      return {
        name: c.__name__,
        title: (content && content['jcr:title']) || c.__name__,
        url: parent + '/' + c.__name__ + '.html'
      }
    })
    for (var i = 0; i < siblings.length; i++) {
      if (siblings[i].name === current) {
        if (i > 0) prev.value = siblings[i - 1]
        if (i < siblings.length - 1) next.value = siblings[i + 1]
        break
      }
    }
  }).catch(function () { /* nav stays hidden */ })
})
</script>

<style>
.bay-pagenav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  border-top: 1px solid var(--bay-line);
  padding: clamp(20px, 3vw, 32px) 0;
}

.bay-pagenav__link {
  display: inline-flex;
  align-items: center;
  gap: 10px;
  text-decoration: none;
  font-family: var(--bay-font-heading);
  font-size: 0.85rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.08em;
  color: var(--bay-ink);
  max-width: 40%;
}

.bay-pagenav__link:hover .bay-pagenav__title {
  text-decoration: underline;
}

.bay-pagenav__arrow {
  font-size: 1.5rem;
  line-height: 1;
}

.bay-pagenav__up {
  font-family: var(--bay-font-heading);
  font-size: 0.78rem;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.1em;
  text-decoration: none;
  color: var(--bay-ink);
  border: 1px solid var(--bay-line);
  padding: 10px 18px;
}

.bay-pagenav__up:hover {
  background: var(--bay-cream);
}

.bay-pagenav__spacer {
  width: 40%;
}

@media (max-width: 640px) {
  .bay-pagenav {
    flex-wrap: wrap;
  }
}
</style>
