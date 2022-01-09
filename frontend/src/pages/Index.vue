<template>
  <q-page class="flex flex-center q-px-md">
   <q-card class="bg-lighter1">
     <q-card-section class="catchphrase-area">
        <img
          alt="Logo"
          src="~assets/logo.png"
          style="width: 40px; height: 50px"
        >
        <span class="q-ml-sm catchphrase-text">Acesse seus restaurantes prediletos!</span>
     </q-card-section>
     <q-card-section>
       <q-input
        filled
        dark
        standout
        v-model="email"
        type="email"
        label="Email"
        class="q-mb-sm text-white" />
       <q-input
        dark
        filled
        standout
        v-model="password"
        :type="isPassword ? 'password' : 'text'"
        label="Password">
        <template v-slot:append>
          <q-icon
            :name="isPassword ? 'visibility_off' : 'visibility'"
            class="cursor-pointer"
            @click="isPassword = !isPassword"
          />
        </template>
      </q-input>
     </q-card-section>
     <q-card-actions align="center" class="q-mb-sm">
        <q-btn @click="login" color="primary" label="ENTRAR" class="w-50" />
     </q-card-actions>
   </q-card>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'PageIndex',
  data () {
    return {
      email: 'fred@graodireto.com.br',
      password: '123Fred',
      isPassword: true
    }
  },
  methods: {
    login () {
      const formData = new FormData()
      formData.append('email', this.email)
      formData.append('password', this.password)

      this.$api.post('/login', formData)
        .then(response => {
          if (response.headers.authorization) {
            this.$store.dispatch('user/commitName', response.data)
            this.$store.dispatch('user/commitAuthorization', response.headers.authorization)
            this.$router.push('/private/restaurants')
          }
        })
        .catch(err => console.error(err))
    }
  }
})
</script>
<style lang="scss">
.catchphrase-area {
  display: flex;
  align-items: center;
}
.catchphrase-text {
  text-align: center;
  font-size: 20px;
  color: white;
}
</style>
