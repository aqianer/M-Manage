<script setup>
import { ref, reactive } from 'vue'
import NavBar from './components/NavBar.vue'
</script>

<template>
  <header>
    <NavBar />
  </header>
  <main class="content-area">
    <!-- 页面内容将在这里渲染 -->
    <h1 class="text-center mt-5">欢迎使用实验室管理系统</h1>
    <p class="text-center text-muted">请通过导航栏访问不同模块。</p>
  </main>
</template>

<style>
#app, html, body {
  width: 100%;
  min-width: 100vw;
  height: 100vh;
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
header, main {
  width: 100%;
  box-sizing: border-box;
}
/* 确保应用根元素充满屏幕且没有多余边距 */
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