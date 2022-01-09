<template>
  <q-page class="q-pa-md">
    <q-card dark class="bg-lighter1">
     <q-card-section class="text-center">
       <span class="restaurant-name">{{ restaurant?.name }}</span>
     </q-card-section>
     <q-card-section>
        <div>
          <strong>Endereço: </strong>
          <span>{{ getRestaurantAddress() }}</span>
        </div>
        <div>
          <strong>Telefone: </strong>
          <span>{{ getRestaurantPhone() }}</span>
        </div>
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
import NumberUtils from '../utils/NumberUtils'

export default defineComponent({
  name: 'Restaurants',
  data () {
    return {
      searchText: '',
      restaurants: []
    }
  },
  mounted () {
    this.$api.get('/restaurant')
      .then(({ data }) => { this.restaurants = data })
      .catch(err => console.error(err))
  },
  components: {
    'restaurant-card': RestaurantCard
  },
  computed: {
    restaurant () {
      return this.$store.state.restaurant.value
    }
  },
  methods: {
    getRestaurantAddress () {
      if (!this.restaurant) {
        return ''
      } else {
        return `${this.restaurant.street}, ${this.restaurant.addressNumber} - ${this.restaurant.neighborhood}`
      }
    },
    getRestaurantPhone () {
      if (!this.restaurant) {
        return ''
      } else {
        return NumberUtils.maskPhone(this.restaurant.phone)
      }
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
.q-list--dark.q-list--separator > .q-item-type + .q-item-type:last-child {
  border-bottom: 1px solid rgba(0, 0, 0, 0.12);
  border-bottom-color: $primary;
}
.restaurant-name {
  text-align: center;
  font-weight: bold;
}
</style>
