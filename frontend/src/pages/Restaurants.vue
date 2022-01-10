<template>
  <q-page class="q-pa-md">
    <q-card class="bg-lighter1">
     <q-card-section>
       <q-input
        dark
        filled
        standout
        @keyup.enter="filterFunction"
        v-model="value"
        type="text"
        hint="Pesquise por nome de restaurante ou refeição"
        label="Pesquisar">
        <template v-slot:append>
          <q-icon
            @click="filterFunction"
            name="search"
            class="cursor-pointer"
          />
        </template>
      </q-input>
     </q-card-section>
   </q-card>

    <div class="q-pt-md">
      <q-list
        dark
        separator>
        <restaurant-card v-for="restaurant in restaurants" :restaurant="restaurant" :key="restaurant.id" />
      </q-list>
    </div>

  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import RestaurantCard from '../components/RestaurantCard.vue'

export default defineComponent({
  name: 'Restaurants',
  data () {
    return {
      value: '',
      restaurants: []
    }
  },
  mounted () {
    this.value = this.queryText
    this.$api.get('/restaurant')
      .then(({ data }) => { this.restaurants = data })
      .catch(err => console.error(err))
  },
  methods: {
    filterFunction () {
      this.$api.get('/restaurant/restaurantoritem?queryName=' + this.value)
        .then(({ data }) => { this.restaurants = data })
        .catch(err => console.error(err))
    }
  },
  components: {
    RestaurantCard
  },
  computed: {
    queryText () {
      return this.$store.state.restaurant.queryText
    }
  }
})
</script>
<style lang="scss">
.q-list--dark.q-list--separator > .q-item-type:first-child {
  border-top: 1px solid rgba(0, 0, 0, 0.12);
  border-top-color: $primary;
}
.q-list--dark.q-list--separator > .q-item-type + .q-item-type {
  border-top-color: $primary;
}
.q-list--dark.q-list--separator > .q-item-type:last-child {
  border-bottom: 1px solid rgba(0, 0, 0, 0.12);
  border-bottom-color: $primary;
}
</style>
