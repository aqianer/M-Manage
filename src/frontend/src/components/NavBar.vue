<template>
  <nav class="navbar">
    <div class="navbar-left">
      <div class="navbar-logo">实验室管理系统</div>
      <ul class="navbar-tabs">
        <li class="navbar-tab dashborad-tab" :class="{active: activeTab === 'dashborad'}" @click="selectTab('dashborad')">
          首页
        </li>
        <li class="navbar-tab chemical-tab" :class="{active: activeTab === 'chemical'}" @click="selectTab('chemical')">
          药品管理
        </li>
        <li class="navbar-tab equipment-tab" :class="{active: activeTab === 'equipment'}" @click="selectTab('equipment')">
          器材管理
        </li>
        <li class="navbar-tab reserved-tab" :class="{active: activeTab === 'reserved'}" @click="selectTab('reserved')">
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
import { defineProps, defineEmits } from 'vue'
const props = defineProps({
  activeTab: String
})
const emit = defineEmits(['update:activeTab'])
function selectTab(tab) {
  emit('update:activeTab', tab)
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
.dashborad-tab {
  color: #1976d2;
}
.chemical-tab {
  color: #43a047;
}
.equipment-tab {
  color: #ff9800;
}
.reserved-tab {
  color: #8e24aa;
}
</style> 