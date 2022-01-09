<template>
  <q-layout view="lHh Lpr lFf">
    <q-header class="bg-default" elevated>
      <q-toolbar>

        <div>
          <q-btn
            flat
            dense
            round
            v-if="isDetailingRestaurant"
            icon="chevron_left"
            @click="goBack()"
          />
          <q-tooltip>
            Voltar
          </q-tooltip>
        </div>

        <q-toolbar-title :class="(this.$q.screen.xs ? 'text-center' : 'text-left') + ' text-primary'">
          <span>Bem-vindo, {{ userName }}</span>
        </q-toolbar-title>

        <div>
          <q-btn
            flat
            dense
            round
            v-if="isLogged"
            icon="logout"
            @click="logout()"
          />
          <q-tooltip>
            Logout
          </q-tooltip>
        </div>

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
  name: 'PrivateLayout',
  mounted () {
    this.navigateLoginIfUnauthorized()
  },
  methods: {
    logout () {
      this.$store.dispatch('user/commitName', '')
      this.$store.dispatch('user/commitAuthorization', '')
      this.$router.push('/')
    },
    navigateLoginIfUnauthorized () {
      if (!this.isLogged) {
        this.$router.push('/')
      }
    },
    goBack () {
      this.$router.back()
    }
  },
  computed: {
    userName () {
      return this.$store.state.user.name
    },
    isLogged () {
      return !!this.$store.state.user.authorization
    },
    isDetailingRestaurant () {
      return this.$router.currentRoute.value.fullPath === '/private/restaurant/menu'
    }
  }
})
</script>
