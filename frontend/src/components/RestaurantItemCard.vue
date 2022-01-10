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
      style="min-height: 40px; height: inherit;"
      side>
      <span class="text-primary item-price h-100">{{ getFormattedPrice(restaurantItem.price) }}</span>
    </q-item-section>
  </q-item>
</template>

<script>
import { defineComponent } from 'vue'
import NumberUtils from '../utils/NumberUtils'

export default defineComponent({
  name: 'RestaurantItemCard',
  props: {
    restaurantItem: {
      type: Object,
      required: true
    }
  },
  methods: {
    getFormattedPrice (price) {
      return NumberUtils.formatMoneyBr(price)
    }
  }
})
</script>
<style lang="scss">
  .item-price {
    font-size: 20px;
  }
</style>
