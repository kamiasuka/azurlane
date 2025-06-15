import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from './router'
import store from './store'

//引入element框架相关
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
createApp(App).use(router).mount('#app')

//定义全局的路径
const BASE_URL =  'http://localhost:8080';
window.BASE_URL = BASE_URL;/*供script标签内部使用*/
const app = createApp(App)

app.use(ElementPlus)
app.mount('#app')
