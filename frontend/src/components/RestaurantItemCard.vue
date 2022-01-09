<template>
  <q-item
    tag="div"
  >

    <q-item-section>
      <q-item-label>{{ restaurantItem.name }}</q-item-label>
      <q-item-label caption>
        {{ restaurantItem.description }}
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
  name: 'RestaurantItemCard',
  props: {
    restaurantItem: {
      type: Object,
      required: true
    }
  }
})
</script>
<style lang="scss">

</style>
