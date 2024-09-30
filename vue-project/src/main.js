import './assets/css/main.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue';
import 'element-plus/dist/index.css';
import './assets/css/icon.css';
import ElementPlus from 'element-plus'

import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'
import Api from './api/index'
import store from './stores'

const app = createApp(App)

app.config.globalProperties.$api=Api;

app.use(store)
app.use(router)
app.use(ElementPlus)

// ElementPlusアイコンを登録する
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component);
}

app.mount('#app')


