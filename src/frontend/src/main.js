import { createApp } from 'vue'
import './style.css'
import App from './App.vue'

// 创建 Vue 3 应用
const app = createApp(App)

// 挂载到 #app 元素
app.mount('#app')
//修改 Vue 分隔符避免与 Thymeleaf 冲突（在 main.js 中）
//new Vue({
//  delimiters: ['${', '}'],  // 替换默认 {{ }} 为 ${ }
//  render: h => h(App)
//}).$mount('#app')
