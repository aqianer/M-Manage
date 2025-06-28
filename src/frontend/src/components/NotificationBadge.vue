<template>
    <div class="position-relative">
        <i class="fas fa-bell text-white"></i>
        <span v-if="count > 0" class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger pulse">
            {{ count }}
            <span class="visually-hidden">未读消息</span>
        </span>
    </div>
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({ 
    count: { type: Number, required: true } 
});
</script>

<style scoped>
@keyframes pulse {
    0% { transform: scale(1); }
    50% { transform: scale(1.1); }
    100% { transform: scale(1); }
}
.pulse {
    animation: pulse 1.5s infinite;
}
</style>