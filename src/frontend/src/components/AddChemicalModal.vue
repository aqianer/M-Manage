<template>
    <div class="modal fade" id="addChemicalModal" tabindex="-1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">新增药品</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <div class="mb-3">
                        <label for="chemicalSelect" class="form-label">选择药品</label>
                        <select class="form-select" id="chemicalSelect" v-model="selectedChemical">
                            <option selected value="">请选择药品...</option>
                            <option v-for="item in chemicalDictionary" :value="item.id">
                                {{ item.name }} (CAS: {{ item.casNumber }})
                            </option>
                        </select>
                    </div>
                    <div v-if="alert.show" :class="['alert', 'alert-'+alert.type]" role="alert">
                        {{ alert.message }}
                        <button type="button" class="btn-close" @click="alert.show=false"></button>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                    <button type="button" class="btn btn-primary" @click="handleSubmit" :disabled="loading">
                        <span v-if="loading" class="spinner-border spinner-border-sm me-2"></span>
                        {{ loading ? '提交中...' : '确认添加' }}
                    </button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, defineEmits } from 'vue';

const props = defineProps({ 
    chemicalDictionary: { type: Array, required: true } 
});
const emits = defineEmits(['submit', 'alert']);

const selectedChemical = ref(null);
const loading = ref(false);
const alert = ref({ show: false, message: '', type: 'primary' });

const handleSubmit = () => {
    if (!selectedChemical.value) {
        emits('alert', { message: '请选择药品', type: 'danger' });
        return;
    }
    loading.value = true;
    // 模拟API提交
    setTimeout(() => {
        emits('submit', selectedChemical.value);
        emits('alert', { message: '药品添加成功', type: 'success' });
        loading.value = false;
    }, 1500);
};
</script>