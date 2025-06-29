<template>
    <nav class="navbar navbar-expand-lg navbar-modern">
      <div class="container-fluid">
        <!-- 品牌Logo -->
        <a class="navbar-brand navbar-brand-modern" href="#">
          <svg class="brand-icon" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512">
            <path fill="#ffffff" d="M288 0L160 0 128 0C110.3 0 96 14.3 96 32s14.3 32 32 32l0 132.8c0 11.8-3.3 23.5-9.5 33.5L10.3 406.2C3.6 417.2 0 429.7 0 442.6C0 480.9 31.1 512 69.4 512l309.2 0c38.3 0 69.4-31.1 69.4-69.4c0-12.8-3.6-25.4-10.3-36.4L329.5 230.4c-6.2-10.1-9.5-21.7-9.5-33.5L320 64c17.7 0 32-14.3 32-32s-14.3-32-32-32L288 0zM192 196.8L192 64l64 0 0 132.8c0 23.7 6.6 46.9 19 67.1L309.5 320l-171 0L173 263.9c12.4-20.2 19-43.4 19-67.1z"/>
          </svg>
          实验室管理系统
        </a>
  
        <!-- 移动端折叠按钮 -->
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav">
          <span class="navbar-toggler-icon"></span>
        </button>
  
        <!-- 导航内容 -->
        <div class="collapse navbar-collapse" id="mainNav">
          <!-- 导航链接 -->
          <ul class="navbar-nav mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link nav-link-modern" href="#" @click.prevent="onNavigate('/')">
                <i class="fas fa-home me-2"></i>首页
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link nav-link-modern" href="#" @click.prevent="onNavigate('/chemical')">
                <i class="fas fa-pills me-2"></i>药品管理
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link nav-link-modern" href="#" @click.prevent="onNavigate('/equipment')">
                <i class="fas fa-tools me-2"></i>器材管理
              </a>
            </li>
          </ul>
  
          <!-- 用户面板 -->
          <div class="user-panel-modern ms-auto">
            <!-- 用户头像 -->
            <img src="https://via.placeholder.com/40" class="user-avatar" alt="用户头像">
  
            <!-- 用户名 -->
            <span class="user-name d-none d-sm-inline">当前用户</span>
  
            <!-- 通知图标 -->
            <div class="notification-icon">
              <i class="fas fa-bell"></i>
              <span v-if="notifications.total > 0" class="notification-badge">
                {{ notifications.total }}
              </span>
            </div>
  
            <!-- 用户下拉菜单 -->
            <div class="dropdown">
              <a class="text-white text-decoration-none" href="#" role="button" data-bs-toggle="dropdown">
                <i class="fas fa-angle-down"></i>
              </a>
              <ul class="dropdown-menu dropdown-menu-modern dropdown-menu-end">
                <li>
                  <a class="dropdown-item dropdown-item-modern" href="#">
                    <i class="fas fa-user me-2"></i>个人中心
                    <span class="badge rounded-pill">{{ notifications.profile }}</span>
                  </a>
                </li>
                <li>
                  <a class="dropdown-item dropdown-item-modern" href="#">
                    <i class="fas fa-key me-2"></i>修改密码
                    <span class="badge rounded-pill">{{ notifications.password }}</span>
                  </a>
                </li>
                <li><hr class="dropdown-divider"></li>
                <li>
                  <a class="dropdown-item dropdown-item-modern" href="#">
                    <i class="fas fa-sign-out-alt me-2"></i>退出登录
                  </a>
                </li>
              </ul>
            </div>
  
            <!-- 角色选择器 -->
            <div v-if="permissions.includes('create')" class="ms-2">
              <select :value="currentRole" @change="handleRoleChange" class="role-selector">
                <option value="admin">管理员</option>
                <option value="editor">编辑员</option>
                <option value="viewer">观察员</option>
              </select>
            </div>
          </div>
        </div>
      </div>
    </nav>
  </template>
  
  <script setup>
  import { defineProps, defineEmits } from 'vue';
  import '../css/NavBar.css'; // 导入样式文件
  
  // 定义 props - 接收父组件传递的数据
  const props = defineProps({
    currentRole: {
      type: String,
      default: 'admin'
    },
    notifications: {
      type: Object,
      default: () => ({
        total: 0,
        profile: 0,
        password: 0
      })
    },
    permissions: {
      type: Array,
      default: () => []
    },
    onNavigate: {
      type: Function,
      default: () => {}
    }
  });
  
  // 定义 emits - 向父组件发送事件
  const emits = defineEmits(['role-change']);
  
  // 处理角色切换逻辑
  const handleRoleChange = (e) => {
    const newRole = e.target.value;
    emits('role-change', newRole);
  };
  </script>