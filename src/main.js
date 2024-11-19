import { createApp } from 'vue'
import { createPinia } from 'pinia'
import piniaPluginPersistedstate from "pinia-plugin-persistedstate";

import App from './App.vue'
import router from './router'
import BootstrapVue3 from 'bootstrap-vue-3'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'
import { useKakao } from 'vue3-kakao-maps/@utils';

const app = createApp(App)
const { VITE_KAKAO_MAP_SERVICE_KEY } = import.meta.env;
const pinia = createPinia();

pinia.use(piniaPluginPersistedstate);
app.use(pinia)
app.use(router)
app.use(BootstrapVue3)
useKakao(VITE_KAKAO_MAP_SERVICE_KEY);

router.isReady().then(() => {
    app.mount("#app");
  });
