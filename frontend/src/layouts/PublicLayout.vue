<template>
  <q-layout view="lHh Lpr lFf">
    <q-header class="bg-default" elevated>
      <q-toolbar>

        <q-toolbar-title :class="this.$q.screen.xs ? 'text-center' : 'text-left'">
          QUEM TEM BOCA
        </q-toolbar-title>

      </q-toolbar>
    </q-header>

    <q-page-container class="bg-default">
      <router-view />
    </q-page-container>
  </q-layout>
</template>

<script>

import { defineComponent } from 'vue'

export default defineComponent({
  name: 'PublicLayout',
  setup () {
    return {
    }
  }
})
</script>
