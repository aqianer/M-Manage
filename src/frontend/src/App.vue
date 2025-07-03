<template>
  <NavBar :activeTab="activeTab" @update:activeTab="activeTab = $event" />
  <div v-if="activeTab === 'dashborad'">
    <h1 class="text-center mt-5" style="color: #43a047;">欢迎使用实验室管理系统</h1>
    <p class="text-center text-muted" style="color: #43a047;">请通过导航栏访问不同模块。</p>
  </div>
  <div v-else-if="activeTab === 'chemical'" style="color: #43a047;">药品管理内容</div>
  <div v-else-if="activeTab === 'equipment'" style="color: #ff9800;">器材管理内容</div>
  <div v-else-if="activeTab === 'reserved'" style="color: #8e24aa;">预留功能内容</div>
</template>

<script setup>
import { ref } from 'vue'
import NavBar from './components/NavBar.vue'
const activeTab = ref('dashborad')
</script>

<style>
#app, html, body {
  width: 100%;
  min-width: 100vw;
  height: 100vh;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
body {
  margin: 0;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
  background-color: #f8f9fa;
}

.content-area {
  width: 100%;
  max-width: 100vw;
  box-sizing: border-box;
  flex-grow: 1;
  padding: 2rem;
}
</style>