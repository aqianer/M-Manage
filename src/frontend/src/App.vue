<template>
  <div id="app">
    <NavBar ref="navRef"
            :activeTab="activeTab"
            :tabs="tabs"
            @update:activeTab="activeTab = $event"
    />

    <!-- 主内容区域 -->
    <main class="content-area" :class="{ opened: navRef?.isMobileMenuOpen }">
      <component :is="currentComponent"/>
    </main>
  </div>
</template>

<script setup>
import NavBar from './components/NavBar.vue';
import Dashboard from './components/Dashboard.vue';
import ChemicalManagement from './components/ChemicalManagement.vue';
import EquipmentManagement from './components/EquipmentManagement.vue';
import ReservedFunction from './components/ReservedFunction.vue';

import {ref, computed} from 'vue';

const activeTab = ref('dashboard');

const tabs = [
  {key: 'dashboard', label: '首页'},
  {key: 'chemical', label: '药品管理'},
  {key: 'equipment', label: '器材管理'},
  {key: 'reserved', label: '预留功能'},
];

const componentsMap = {
  dashboard: Dashboard,
  chemical: ChemicalManagement,
  equipment: EquipmentManagement,
  reserved: ReservedFunction,
};

const currentComponent = computed(() => componentsMap[activeTab.value]);

const navRef = ref(null);
</script>

<style>
#app, html, body {
  width: 100%;
  min-height: 100vh;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
}

body {
  background-color: #f8f9fa;
}

.content-area {
  width: 100%;
  padding: 1.5rem;
  box-sizing: border-box;
  transition: margin-left 0.3s ease;
}

/* 侧边栏展开时，内容区域向右移动 */
@media (max-width: 768px) {
  .content-area {
    padding: 1rem;
  }

  /* 当侧边栏展开时，内容区域添加左外边距 */
  .content-area.opened {
    margin-left: 250px;
  }
}

</style>
