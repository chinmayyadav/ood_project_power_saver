import Vue from 'vue'
import VueSession from 'vue-session'
import App from './App.vue'
import router from './router'
import store from './store'
import vuetify from './plugins/vuetify'
import 'bootstrap' 
import 'bootstrap/dist/css/bootstrap.css'
import axios from 'axios'
import Swal from 'sweetalert2'
// import 'roboto-fontface/css/roboto/roboto-fontface.css'
import '@mdi/font/css/materialdesignicons.css'
import '@/assets/scss/style.scss'


const userMicroserviceInstance = axios.create({
  baseURL: 'http://localhost:8080/'
});

// const swal = new Swal();
 

Vue.prototype.$userHttp = userMicroserviceInstance
Vue.prototype.$Swal = Swal;
Vue.config.productionTip = false;
Vue.use(VueSession)


new Vue({
  router,
  store,
  vuetify,
  render: h => h(App)
}).$mount('#app')
