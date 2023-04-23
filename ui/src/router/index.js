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
        // UserProfile will be rendered inside User's <router-view>
        // when /user/:id/profile is matched
        path: 'add-credit-card',
        name: 'add-credit-card',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/AddCreditCard.vue'),
      },
      {
        // UserPosts will be rendered inside User's <router-view>
        // when /user/:id/posts is matched
        path: 'add-bank-account',
        name: 'add-bank-account',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/AddCreditCard.vue'),
      },
      {
        // UserPosts will be rendered inside User's <router-view>
        // when /user/:id/posts is matched
        path: 'user-addresses',
        name: 'UserAddresses',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/UserAddresses.vue'),
      },
      {
        // UserPosts will be rendered inside User's <router-view>
        // when /user/:id/posts is matched
        path: 'add-addresses',
        name: 'AddAddresses',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/AddAddress.vue'),
      },
      {
        // UserPosts will be rendered inside User's <router-view>
        // when /user/:id/posts is matched
        path: 'view-appliances-per-address',
        name: 'ViewAppliancesPerAddress',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/ViewAppliancesPerAddress.vue'),
      },
      {
        // UserPosts will be rendered inside User's <router-view>
        // when /user/:id/posts is matched
        path: 'add-appliance',
        name: 'AddAppliance',
        component: () => import(/* webpackChunkName: "add-credit-card" */ '../components/AddAppliance.vue'),
      },
    ],


  },
  // {
  //   path: '/login',
  //   name: 'login',
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () => import(/* webpackChunkName: "Login" */ '../componenets/Login.vue')
  // }
]

const router = new VueRouter({
  mode: process.env.IS_ELECTRON ? 'hash' : 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
