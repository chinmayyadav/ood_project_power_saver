import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import 'bootstrap' 
import 'bootstrap/dist/css/bootstrap.css'
import axios from 'axios'
// import 'roboto-fontface/css/roboto/roboto-fontface.css'
import '@mdi/font/css/materialdesignicons.css'
import '@/assets/scss/style.scss'

const userMicroserviceInstance = axios.create({
  baseURL: 'http://localhost:8080/'
});
 

Vue.prototype.$userHttp = userMicroserviceInstance
Vue.config.productionTip = false

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
