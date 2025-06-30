<template>
  <nav class="navbar">
    <div class="navbar-left">
      <div class="navbar-logo">实验室管理系统</div>
      <ul class="navbar-tabs">
        <li class="navbar-tab" :class="{active: activeTab === 'chemical'}" @click="selectTab('chemical')">
          药品管理
        </li>
        <li class="navbar-tab" :class="{active: activeTab === 'equipment'}" @click="selectTab('equipment')">
          器材管理
        </li>
        <li class="navbar-tab" :class="{active: activeTab === 'reserved'}" @click="selectTab('reserved')">
          预留功能
        </li>
      </ul>
    </div>
    <div class="navbar-right">
      <div class="user-info">
        <img class="avatar" src="/src/assets/default-avatar.png" alt="用户头像" />
        <span class="username">用户名</span>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { ref } from 'vue'
const activeTab = ref('chemical')
function selectTab(tab) {
  activeTab.value = tab
  // 可在此处添加路由跳转或事件
}
</script>

<style scoped>
.navbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 60px;
  background: #fff;
  box-shadow: 0 2px 8px rgba(0,0,0,0.05);
  padding: 0 32px;
  position: sticky;
  top: 0;
  z-index: 100;
}
.navbar-left {
  display: flex;
  align-items: center;
}
.navbar-logo {
  font-size: 1.3rem;
  font-weight: bold;
  color: #2d3a4b;
  margin-right: 32px;
}
.navbar-tabs {
  display: flex;
  list-style: none;
  margin: 0;
  padding: 0;
}
.navbar-tab {
  padding: 0 20px;
  height: 60px;
  display: flex;
  align-items: center;
  cursor: pointer;
  font-size: 1rem;
  color: #555;
  transition: color 0.2s, background 0.2s;
}
.navbar-tab.active, .navbar-tab:hover {
  color: #1976d2;
  background: #f0f7ff;
}
.navbar-right {
  display: flex;
  align-items: center;
}
.user-info {
  display: flex;
  align-items: center;
  gap: 10px;
}
.avatar {
  width: 36px;
  height: 36px;
  border-radius: 50%;
  object-fit: cover;
  border: 1px solid #eee;
}
.username {
  font-size: 1rem;
  color: #333;
}
</style> 