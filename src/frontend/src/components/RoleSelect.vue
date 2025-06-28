<template>
    <div class="ms-3">
        <select v-model="currentRole" @change="handleRoleChange" class="form-select form-select-sm">
            <option value="admin">管理员</option>
            <option value="editor">编辑员</option>
            <option value="viewer">观察员</option>
        </select>
    </div>
</template>

<script setup>
import { ref, defineProps, defineEmits } from 'vue';

const props = defineProps({ 
    currentRole: { type: String, required: true } 
});
const emits = defineEmits(['role-change']);

const handleRoleChange = (e) => {
    emits('role-change', e.target.value);
};
</script>