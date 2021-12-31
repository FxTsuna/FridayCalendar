import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../../src/views/ConnexionView.vue')

  },
  {
    path: '/connexion',
    name: 'Connexion',
    component: () => import('../../src/views/ConnexionView.vue')
  },
  {
    path: '/createUser',
    name: 'CreateUser',
    component: () => import('../../src/views/CreateUserView.vue')
  },
  {
    path: '/dashboard',
    name: 'DashBoard',
    component: () => import('../../src/views/DashBoard.vue')
  },
  {
    path: '/fullcalendar',
    name: 'FullCalendar',
    component: () => import('../../src/views/FullCalendar.vue')
  },
  {
    path: '/eventModify',
    name: 'EventModify',
    component: () => import('../../src/views/EventModifyView.vue')
  },
  {
    path: '/updatePassword',
    name: 'UpdatePassword',
    component: () => import('../../src/views/UpdatePasswordView.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
