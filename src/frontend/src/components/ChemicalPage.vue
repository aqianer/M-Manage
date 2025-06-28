<template>
    <div class="container py-4">
        <div class="table-header d-flex justify-content-between mb-3">
            <div>
                <button v-if="permissions.includes('create')" @click="$emit('open-modal')" class="btn btn-sm btn-outline-primary me-2">
                    <i class="fas fa-plus me-1"></i>新增药品
                </button>
                <button class="btn btn-sm btn-outline-secondary">
                    <i class="fas fa-filter me-1"></i>筛选
                </button>
            </div>
        </div>

        <div class="table-responsive">
            <table class="table table-hover table-striped align-middle">
                <thead class="table-light">
                    <tr>
                        <th style="width: 50px"></th>
                        <th>药品名称</th>
                        <th>CAS号</th>
                        <th>库存数量</th>
                        <th>存储位置</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody v-if="!loading && !error">
                    <tr v-for="chem in chemicals" :key="chem.id">
                        <td>
                            <button @click="toggleExpand(chem.id)" class="btn btn-sm btn-outline-primary">
                                <i class="fas" :class="isExpanded(chem.id) ? 'fa-chevron-up' : 'fa-chevron-down'"></i>
                            </button>
                        </td>
                        <td class="fw-medium">{{ chem.name }}</td>
                        <td>{{ chem.casNumber }}</td>
                        <td><span class="badge bg-primary rounded-pill">{{ chem.quantity }}</span></td>
                        <td>{{ chem.storageLocation }}</td>
                        <td>
                            <div class="d-flex">
                                <button v-if="permissions.includes('update')" class="btn btn-sm btn-outline-warning me-2">
                                    <i class="fas fa-edit"></i>
                                </button>
                                <button v-if="permissions.includes('delete')" class="btn btn-sm btn-outline-danger me-2">
                                    <i class="fas fa-trash"></i>
                                </button>
                                <button v-if="permissions.includes('read')" class="btn btn-sm btn-outline-primary">
                                    <i class="fas fa-upload me-1"></i>上传申请
                                </button>
                            </div>
                        </td>
                    </tr>
                    <tr v-for="chem in chemicals" :key="chem.id + '-detail'">
                        <td colspan="6" class="p-0 border-0">
                            <div class="detail-content" :class="{ 'd-none': !isExpanded(chem.id) }">
                                <div class="d-flex p-3 bg-light">
                                    <div class="flex-grow-1">
                                        <h5 class="text-primary mb-3"><i class="fas fa-info-circle me-2"></i>基本属性</h5>
                                        <div class="info-item">
                                            <span class="info-label">CAS号：</span>
                                            <span class="badge bg-secondary">{{ chem.casNumber }}</span>
                                        </div>
                                        <div class="info-item">
                                            <span class="info-label">英文名称：</span>
                                            <span>{{ chem.englishName || '暂无' }}</span>
                                        </div>
                                        <div class="info-item">
                                            <span class="info-label">危险等级：</span>
                                            <span :class="'badge ' + getHazardBadgeClass(chem.hazardClass?.name)">
                                                {{ chem.hazardClass?.name || '未知' }}
                                                <span v-if="chem.hazardClass?.symbol">(符号: {{ chem.hazardClass.symbol }})</span>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="vertical-divider"></div>
                                <div class="flex-grow-1">
                                    <h5 class="text-primary mb-3"><i class="fas fa-flask me-2"></i>其他信息</h5>
                                    <div class="info-item">
                                        <span class="info-label">分子式：</span>
                                        <span>{{ chem.formula || '暂无' }}</span>
                                    </div>
                                    <div class="info-item">
                                        <span class="info-label">分子量：</span>
                                        <span>{{ chem.molecularWeight?.toFixed(2) || '暂无' }} g/mol</span>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
                <tbody v-else-if="loading">
                    <tr>
                        <td colspan="6" class="text-center py-4">
                            <div class="spinner-border text-primary" role="status">
                                <span class="visually-hidden">加载中...</span>
                            </div>
                        </td>
                    </tr>
                </tbody>
                <tbody v-else>
                    <tr>
                        <td colspan="6" class="text-center py-4 text-danger">
                            {{ error }}
                            <button @click="onUpdateChemicals" class="btn btn-sm btn-outline-danger mt-2">
                                <i class="fas fa-redo me-1"></i>重试
                            </button>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const props = defineProps(['chemicals', 'loading', 'error', 'currentRole', 'permissions', 'onUpdateChemicals']);
const emit = defineEmits(['open-modal']);

const expandedRows = ref([]);

const toggleExpand = (id) => {
    const index = expandedRows.value.indexOf(id);
    index === -1 ? expandedRows.value.push(id) : expandedRows.value.splice(index, 1);
};

const isExpanded = (id) => expandedRows.value.includes(id);

// 注意：原代码中的getHazardBadgeClass需从外部引入或在此组件内定义
// 如果该函数在list.html中全局存在，需同步迁移至此
</script>