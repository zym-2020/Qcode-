import { createApp } from 'vue'
// import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'

import * as turf from '@turf/turf'




const Vue = createApp(App).use(store).use(router).use(ElementPlus)

Vue.config.globalProperties.$turf = turf
Vue.mount('#app')