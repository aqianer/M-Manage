<script setup>
import { ref, reactive } from 'vue'
import NavBar from './components/NavBar.vue'
import HelloWorld from './components/HelloWorld.vue'
import viteLogo from './assets/vite.svg'
import vueLogo from './assets/vue.svg'

// 定义要传递给 NavBar 的数据
const currentRole = ref('admin')
const notifications = reactive({
  total: 3,
  profile: 2,
  password: 1
})
const permissions = ref(['create', 'read', 'update', 'delete'])

// 定义导航方法
const onNavigate = (path) => {
  console.log('导航到:', path)
  // 这里可以添加路由逻辑
}

// 定义角色切换方法
const handleRoleChange = (newRole) => {
  currentRole.value = newRole
  console.log('角色切换为:', newRole)
  
  // 根据角色更新权限
  switch(newRole) {
    case 'admin':
      permissions.value = ['create', 'read', 'update', 'delete']
      break
    case 'editor':
      permissions.value = ['create', 'read', 'update']
      break
    case 'viewer':
      permissions.value = ['read']
      break
  }
}
</script>

<template>
  <!-- 传递数据给 NavBar 子组件 -->
  <NavBar
    :currentRole="currentRole"
    :notifications="notifications"
    :permissions="permissions"
    :onNavigate="onNavigate"
    @role-change="handleRoleChange"
  />
  <!-- <div>
      <a href="https://vite.dev" target="_blank">
        <img src="/vite.svg" class="logo" alt="Vite logo" />
      </a>
      <a href="https://vuejs.org/" target="_blank">
        <img src="./assets/vue.svg" class="logo vue" alt="Vue logo" />
      </a>
   </div>
   <HelloWorld msg="Vite + Vue" /> -->

</template>

<style scoped>
.logo {
  height: 6em;
  padding: 1.5em;
  will-change: filter;
  transition: filter 300ms;
}
.logo:hover {
  filter: drop-shadow(0 0 2em #646cffaa);
}
.logo.vue:hover {
  filter: drop-shadow(0 0 2em #42b883aa);
}
</style>