import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    redirect:'/index',
    children:[
      {path:'/index',component:() => import( '../views/web/IndexView.vue')},
      {path:'/admin',component:() => import( '../views/admin/AdminView.vue')},
    ]
  },
  {
    path: '/login', component: () => import( '../views/admin/LoginView.vue'),
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
