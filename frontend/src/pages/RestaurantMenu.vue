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
        <restaurant-item-card v-for="restaurantItem in restaurantItems" :restaurantItem="restaurantItem" :key="restaurantItem.id" />
      </q-list>
    </div>

  </q-page>
</template>

<script>
import { defineComponent } from 'vue'
import RestaurantItemCard from '../components/RestaurantItemCard.vue'
import NumberUtils from '../utils/NumberUtils'

export default defineComponent({
  name: 'RestaurantMenu',
  data () {
    return {
      restaurantItems: []
    }
  },
  mounted () {
    this.$api.get(`/restaurantitem/restaurant?restaurantId=${this.restaurant?.id}`)
      .then(({ data }) => { this.restaurantItems = data })
      .catch(err => console.error(err))
  },
  components: {
    RestaurantItemCard
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
.q-list--dark.q-list--separator >.q-item-type:last-child {
  border-bottom: 1px solid rgba(0, 0, 0, 0.12);
  border-bottom-color: $primary;
}
.restaurant-name {
  text-align: center;
  font-weight: bold;
}
</style>
