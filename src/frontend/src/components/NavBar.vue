<template>
  <nav class="navbar">
    <!-- 左侧：品牌 + 选项卡 -->
    <div class="left-section">
      <button class="menu-toggle" @click="toggleMobileMenu">☰</button>
      <div class="brand">实验室管理系统</div>
      <ul class="desktop-menu">
        <li
            v-for="tab in tabs"
            :key="tab.key"
            :class="{ active: activeTab === tab.key }"
            @click="$emit('update:activeTab', tab.key)"
        >
          {{ tab.label }}
        </li>
      </ul>
    </div>

    <!-- 右侧：用户信息 -->
    <div class="right-section">
      <div class="user-info">用户名</div>

    </div>
  </nav>

</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue';

const props = defineProps({
  activeTab: String,
  tabs: Array,
});

const emit = defineEmits(['update:activeTab']);

const isMobileMenuOpen = ref(false);

// 切换侧边栏状态
const toggleMobileMenu = () => {
  isMobileMenuOpen.value = !isMobileMenuOpen.value;
  updateScrollLock();
};

// 关闭侧边栏
const closeMobileMenu = () => {
  isMobileMenuOpen.value = false;
  updateScrollLock();
};

// 点击菜单项并关闭侧边栏
const selectTab = (tabKey) => {
  emit('update:activeTab', tabKey);
  closeMobileMenu();
};

// 更新页面滚动状态
const updateScrollLock = () => {
  if (isMobileMenuOpen.value) {
    document.body.style.overflow = 'hidden';
  } else {
    document.body.style.overflow = '';
  }
};

// 点击外部区域关闭侧边栏
const handleClickOutside = (event) => {
  const menu = document.querySelector('.mobile-menu');
  const button = document.querySelector('.menu-toggle');
  if (
      isMobileMenuOpen.value &&
      !menu?.contains(event.target) &&
      !button?.contains(event.target)
  ) {
    closeMobileMenu();
  }
};

onMounted(() => {
  window.addEventListener('click', handleClickOutside);
});

onUnmounted(() => {
  window.removeEventListener('click', handleClickOutside);
  document.body.style.overflow = '';
});

// 暴露状态给 App.vue 使用
defineExpose({ isMobileMenuOpen });
</script>

<style scoped>
.navbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem 2rem;
  background-color: #ffffff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  position: relative;
  z-index: 1000;
  width: 100%;
  box-sizing: border-box;
}

.left-section {
  display: flex;
  align-items: center;
  gap: 2rem;
}

.brand {
  font-size: 1.5rem;
  font-weight: bold;
  color: #43a047;
}

.desktop-menu {
  list-style: none;
  display: flex;
  gap: 1.5rem;
  margin: 0;
  padding: 0;
}

.desktop-menu li {
  cursor: pointer;
  color: #43a047;
  transition: color 0.3s ease;
}

.desktop-menu li.active {
  color: #ef5350;
  font-weight: bold;
}

.right-section {
  display: flex;
  align-items: center;
  gap: 1rem;
}

.user-info {
  font-size: 1rem;
  color: #333;
}

.menu-toggle {
  display: none;
  background: none;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: #43a047;
}

/* 移动端样式 */
@media (max-width: 768px) {
  .desktop-menu {
    display: none;
  }

  .menu-toggle {
    display: block;
  }

  .right-section {
    gap: 0;
  }

  .user-info {
    display: none;
  }
}

</style>
