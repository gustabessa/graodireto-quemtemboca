
const routes = [
  {
    path: '/',
    component: () => import('src/layouts/PublicLayout.vue'),
    children: [
      { path: '', component: () => import('pages/Index.vue') }
    ]
  },
  {
    path: '/private',
    component: () => import('src/layouts/PrivateLayout.vue'),
    children: [
      { path: 'restaurants', component: () => import('pages/Restaurants.vue') },
      { path: 'restaurant/menu', component: () => import('pages/RestaurantMenu.vue') }
    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '/:catchAll(.*)*',
    component: () => import('pages/Error404.vue')
  }
]

export default routes
