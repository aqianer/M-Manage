import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
//修改 Vue 分隔符避免与 Thymeleaf 冲突（在 main.js 中）
new Vue({
  delimiters: ['${', '}'],  // 替换默认 {{ }} 为 ${ }
  render: h => h(App)
}).$mount('#app')
