import Vue from 'vue'
import App from './App.vue'
import router from './router/index'
import store from './store/index'
import axios from 'axios'

/* import the fontawesome core */
import { library } from '@fortawesome/fontawesome-svg-core'

/* import font awesome icon component */
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

/* import specific icons */
import { faHouse, faMapLocationDot, faPersonDigging, faUser, faMap, faMapLocation, faFlag, faRightToBracket, faUserGear, faArrowsUpDown, faCircleArrowLeft, faCopyright} from '@fortawesome/free-solid-svg-icons'

/* add icons to the library */
library.add(faHouse, faUser, faPersonDigging, faMapLocationDot, faMap, faMapLocation, faFlag, faRightToBracket, faUserGear, faArrowsUpDown, faCircleArrowLeft, faCopyright)

/* add font awesome icon component */
Vue.component('font-awesome-icon', FontAwesomeIcon)

Vue.config.productionTip = false

axios.defaults.baseURL = process.env.VUE_APP_REMOTE_API;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
