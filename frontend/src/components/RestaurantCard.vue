<template>
  <q-item
    @click="itemClickedHandler"
    clickable
    tag="div"
  >

    <q-item-section>
      <q-item-label>{{ restaurant.name }}</q-item-label>
      <q-item-label caption>
        {{ restaurant.restaurantCategory.name }}
      </q-item-label>
    </q-item-section>
    <q-item-section
      avatar>
      <q-icon color="primary" name="arrow_right" size="40px" />
    </q-item-section>
  </q-item>
</template>

<script>
import { defineComponent } from 'vue'

export default defineComponent({
  name: 'RestaurantCard',
  props: {
    restaurant: {
      type: Object,
      required: true
    }
  },
  methods: {
    itemClickedHandler () {
      this.$store.dispatch('restaurant/commitValue', this.restaurant)
      this.$router.push('/private/restaurant/menu')
    }
  }
})
</script>
<style lang="scss">

</style>
