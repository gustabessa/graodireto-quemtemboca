<template>
  <q-page class="q-pa-md">
    <q-card class="bg-lighter1">
     <q-card-section>
       <q-input
        dark
        filled
        standout
        v-model="searchText"
        type="text"
        hint="Pesquise por nome de restaurante ou refeição"
        label="Pesquisar">
        <template v-slot:append>
          <q-icon
            name="search"
            class="cursor-pointer"
          />
        </template>
      </q-input>
     </q-card-section>
   </q-card>
  </q-page>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'Restaurants',
  data () {
    return {
      searchText: ''
    }
  }
})
</script>
