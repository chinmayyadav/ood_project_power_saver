import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '../views/HomePage.vue'
import Register from '../components/Register.vue'
// import LoginView from '../components/Login.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'login',
    component: HomePage
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../layouts/DashboardLayout.vue'),
    children: [
      {
        path: '/home',
        name: 'dashboard-home',
        component: () => import(/* webpackChunkName: "dashboard-home" */ '../components/Dashboard.vue'),
      },
      {
        path: 'add-credit-card',
        name: 'add-credit-card',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/AddCreditCard.vue'),
      },
      {
        path: 'add-bank-account',
        name: 'add-bank-account',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/AddCreditCard.vue'),
      },
      {
        path: 'user-addresses',
        name: 'UserAddresses',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/UserAddresses.vue'),
      },
      {
        path: 'add-addresses',
        name: 'AddAddresses',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/AddAddress.vue'),
      },
      {
        path: 'view-appliances-per-address',
        name: 'ViewAppliancesPerAddress',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/ViewAppliancesPerAddress.vue'),
      },
      {
        path: 'add-appliance',
        name: 'AddAppliance',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/AddAppliance.vue'),
      },
      {
        path: 'edit-address',
        name: 'EditAddress',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/EditAddress.vue'),
      },
      {
        path: 'view-cards',
        name: 'ViewCards',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/ViewCards.vue'),
      },
      {
        path: 'notification-preferences',
        name: 'NotificationPreferences',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/NotificationPreferences.vue'),
      },
      {
        path: 'profile',
        name: 'Profile',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/Profile.vue'),
      },
      {
        path: 'view-bills',
        name: 'ViewBills',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/ViewBills.vue'),
      },
      {
        path: 'unpaid-bills',
        name: 'UnpaidBills',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/UnpaidBills.vue'),
      },
      {
        path: 'insights',
        name: 'Insights',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/Insights.vue'),
      },
    ],


  },
]

const router = new VueRouter({
  mode: process.env.IS_ELECTRON ? 'hash' : 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
