<template>
    <nav class="navbar navbar-expand-lg nav-custom navbar-dark mb-4">
        <div class="container-fluid">
            <a class="navbar-brand text-light d-flex align-items-center" href="#">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" class="me-2" style="width: 1.5rem; height: 1.5rem;">
                    <path fill="#63E6BE" d="M288 0L160 0 128 0C110.3 0 96 14.3 96 32s14.3 32 32 32l0 132.8c0 11.8-3.3 23.5-9.5 33.5L10.3 406.2C3.6 417.2 0 429.7 0 442.6C0 480.9 31.1 512 69.4 512l309.2 0c38.3 0 69.4-31.1 69.4-69.4c0-12.8-3.6-25.4-10.3-36.4L329.5 230.4c-6.2-10.1-9.5-21.7-9.5-33.5L320 64c17.7 0 32-14.3 32-32s-14.3-32-32-32L288 0zM192 196.8L192 64l64 0 0 132.8c0 23.7 6.6 46.9 19 67.1L309.5 320l-171 0L173 263.9c12.4-20.2 19-43.4 19-67.1z"/>
                </svg>
                实验室管理系统
            </a>

            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mainNav">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="mainNav">
                <ul class="navbar-nav mb-2 mb-lg-0">
                    <li class="nav-item mx-2">
                        <a class="nav-link" href="#" @click.prevent="onNavigate('/')">首页</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="nav-link" href="#" @click.prevent="onNavigate('/chemical')">药品管理</a>
                    </li>
                    <li class="nav-item mx-2">
                        <a class="nav-link" href="#" @click.prevent="onNavigate('/equipment')">器材管理</a>
                    </li>
                </ul>

                <div class="d-flex align-items-center user-panel position-relative gap-2 ms-auto">
                    <img src="https://via.placeholder.com/36" class="rounded-circle" style="width: 36px; height: 36px;">

                    <span class="d-none d-sm-inline text-white fw-medium">当前用户</span>

                    <div class="position-relative">
                        <i class="fas fa-bell text-white"></i>
                        <span v-if="notifications.total > 0" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
                            {{ notifications.total }}
                        </span>
                    </div>

                    <a class="text-white text-decoration-none" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <i class="fas fa-angle-down"></i>
                    </a>

                    <ul class="dropdown-menu dropdown-menu-end">
                        <li>
                            <a class="dropdown-item d-flex justify-content-between align-items-center" href="#">
                                个人中心
                                <span class="badge bg-danger rounded-pill">{{ notifications.profile }}</span>
                            </a>
                        </li>
                        <li>
                            <a class="dropdown-item d-flex justify-content-between align-items-center" href="#">
                                修改密码
                                <span class="badge bg-danger rounded-pill">{{ notifications.password }}</span>
                            </a>
                        </li>
                        <li><hr class="dropdown-divider"></li>
                        <li>
                            <a class="dropdown-item" href="#"><i class="fas fa-sign-out-alt me-2"></i>退出登录</a>
                        </li>
                    </ul>
                </div>

                <div class="ms-3" v-if="permissions.includes('create')">
                    <select v-model="currentRole" @change="handleRoleChange" class="form-select form-select-sm">
                        <option value="admin">管理员</option>
                        <option value="editor">编辑员</option>
                        <option value="viewer">观察员</option>
                    </select>
                </div>
            </div>
        </div>
    </nav>
</template>

<script setup>
import { defineProps, defineEmits } from 'vue';

// 定义props
const props = defineProps(['currentRole', 'notifications', 'permissions', 'onNavigate']);

// 定义emits（触发role-change事件）
const emits = defineEmits(['role-change']);

// 处理角色切换逻辑
const handleRoleChange = (e) => {
    emits('role-change', e.target.value);
};
</script>

<style scoped>
/* 保留原导航栏相关的局部样式 */
.nav-custom {
    background: linear-gradient(145deg, #161b22 0%, #0d1117 100%);
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}
.nav-link-custom {
    color: #e6e6e6 !important;
    padding: 12px 20px;
    transition: all 0.3s;
    border-left: 3px solid transparent;
}
</style>