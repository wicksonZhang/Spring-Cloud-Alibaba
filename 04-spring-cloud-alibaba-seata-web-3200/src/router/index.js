import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/business',
    name: 'business',
    component: () => import( '../views/BusinessView')
  }
]

const router = new VueRouter({
  routes
})

export default router
