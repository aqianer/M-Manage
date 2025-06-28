<template>
    <div class="container py-4">
        <div class="row g-3">
            <div class="col-md-4">
                <div class="card bg-light">
                    <div class="card-body">
                        <h6 class="card-subtitle mb-2 text-muted">今日入库</h6>
                        <h3 class="card-title">12</h3>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card bg-light">
                    <div class="card-body">
                        <h6 class="card-subtitle mb-2 text-muted">待处理申请</h6>
                        <h3 class="card-title">5</h3>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card bg-light">
                    <div class="card-body">
                        <h6 class="card-subtitle mb-2 text-muted">库存预警</h6>
                        <h3 class="card-title">3</h3>
                    </div>
                </div>
            </div>
        </div>

        <div class="card mt-4">
            <div class="card-header">最近操作记录</div>
            <div class="card-body p-0">
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">[10:30] 张三 提交药品领用申请</li>
                    <li class="list-group-item">[09:15] 李四 完成器材归还登记</li>
                </ul>
            </div>
        </div>
    </div>
</template>

<script setup>
// 该组件无额外逻辑，仅需声明为Vue组件
</script>