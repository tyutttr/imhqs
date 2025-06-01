<template>
  <div class="admin-container">
    <!-- 商品管理 -->
    <div class="manage-section">
      <div class="header">
        <h2>商品管理</h2>
        <el-button type="primary" @click="showAddDialog">
          <el-icon><Plus /></el-icon>
          添加商品
        </el-button>
      </div>

      <el-table :data="products" stripe>
        <el-table-column label="封面" width="120">
          <template #default="{ row }">
            <el-image
              style="width: 80px; height: 100px"
              :src="row.image"
              fit="cover"
              :preview-src-list="[row.image]"
            />
          </template>
        </el-table-column>
        <el-table-column prop="name" label="商品名称" />
        <el-table-column prop="type" label="类型" width="100">
          <template #default="{ row }">
            <el-tag :type="row.type === '电子书' ? 'success' : 'warning'">
              {{ row.type }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="points" label="积分" width="100" />
        <el-table-column prop="downloadUrl" label="下载地址" />
        <el-table-column prop="address" label="奖品地址" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button type="text" @click="editProduct(row)">编辑</el-button>
            <el-button type="text" danger @click="deleteProduct(row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 添加商品对话框 -->
    <el-dialog v-model="dialogVisible" title="商品管理" width="600px">
      <el-form :model="currentProduct" label-width="100px">
        <el-form-item label="商品名称" required>
          <el-input v-model="currentProduct.name" />
        </el-form-item>
        <el-form-item label="商品类型" required>
          <el-select v-model="currentProduct.type">
            <el-option label="心理健康知识电子书" value="电子书" />
            <el-option label="实物奖品" value="实物" />
          </el-select>
        </el-form-item>
        <el-form-item label="所需积分" required>
          <el-input-number v-model="currentProduct.points" :min="1" />
        </el-form-item>
        <el-form-item label="下载地址" v-if="currentProduct.type === '电子书'" required>
          <el-input v-model="currentProduct.downloadUrl" />
        </el-form-item>
        <el-form-item label="奖品地址" v-if="currentProduct.type === '实物'" required>
          <el-input v-model="currentProduct.address" />
        </el-form-item>
        <el-form-item label="商品封面" required>
          <el-upload
            action="/api/upload"
            list-type="picture-card"
            :on-success="handleUploadSuccess"
            :file-list="currentProduct.image ? [{
              url: currentProduct.image
            }] : []"
          >
            <el-icon><Plus /></el-icon>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveProduct">保存</el-button>
      </template>
    </el-dialog>

      <el-table :data="products" stripe>
        <el-table-column prop="name" label="商品名称" />
        <el-table-column prop="type" label="商品类型" />
        <el-table-column prop="points" label="所需积分" />
        <el-table-column prop="downloadUrl" label="下载地址" />
        <el-table-column prop="address" label="奖品地址" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button type="text" @click="editProduct(row)">编辑</el-button>
            <el-button type="text" danger @click="deleteProduct(row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>


    <!-- 兑换记录管理 -->
    <div class="manage-section">
      <h2>兑换记录</h2>
      <div class="filter-bar">
        <el-input
          v-model="searchKey"
          placeholder="搜索用户名称或手机号"
          style="width: 300px"
        />
        <el-date-picker
          v-model="dateRange"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        />
      </div>

      <el-table :data="filteredRecords" stripe>
        <el-table-column prop="user" label="用户" />
        <el-table-column prop="phone" label="手机号" />
        <el-table-column prop="product" label="商品" />
        <el-table-column prop="points" label="消耗积分" />
        <el-table-column prop="date" label="兑换日期" />
        <el-table-column prop="downloadTime" label="下载时间" />
        <el-table-column label="状态">
          <template #default="{ row }">
            <el-select v-model="row.status">
              <el-option label="处理中" value="处理中" />
              <el-option label="已发货" value="已发货" />
              <el-option label="已完成" value="已完成" />
            </el-select>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="100">
          <template #default="{ row }">
            <el-button type="text" @click="deleteRecord(row.id)">
              删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div style="margin-top: 20px">
        <el-button type="primary" @click="showAddRecordDialog">
          添加兑换记录
        </el-button>
      </div>
    </div>

    <!-- 添加商品对话框 -->
    <el-dialog v-model="dialogVisible" title="商品管理">
      <el-form :model="currentProduct" label-width="100px">
        <el-form-item label="商品名称">
          <el-input v-model="currentProduct.name" />
        </el-form-item>
        <el-form-item label="商品类型">
          <el-select v-model="currentProduct.type">
            <el-option label="心理健康知识电子书" value="电子书" />
            <el-option label="实物奖品" value="实物" />
          </el-select>
        </el-form-item>
        <el-form-item label="所需积分">
          <el-input-number v-model="currentProduct.points" :min="1" />
        </el-form-item>
        <el-form-item label="下载地址" v-if="currentProduct.type === '电子书'">
          <el-input v-model="currentProduct.downloadUrl" />
        </el-form-item>
        <el-form-item label="奖品地址" v-if="currentProduct.type === '实物'">
          <el-input v-model="currentProduct.address" />
        </el-form-item>
        <el-form-item label="商品图片">
          <el-upload
            action="https://your-upload-api.com"
            list-type="picture-card"
          >
            <el-icon><Plus /></el-icon>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveProduct">保存</el-button>
      </template>
    </el-dialog>

    <!-- 添加兑换记录对话框 -->
    <el-dialog v-model="recordDialogVisible" title="添加兑换记录">
      <el-form :model="currentRecord" label-width="100px">
        <el-form-item label="用户">
          <el-input v-model="currentRecord.user" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="currentRecord.phone" />
        </el-form-item>
        <el-form-item label="商品">
          <el-select v-model="currentRecord.product">
            <el-option
              v-for="product in products"
              :key="product.id"
              :label="product.name"
              :value="product.name"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="消耗积分">
          <el-input-number v-model="currentRecord.points" :min="1" />
        </el-form-item>
        <el-form-item label="兑换日期">
          <el-date-picker v-model="currentRecord.date" type="date" />
        </el-form-item>
        <el-form-item label="下载时间">
          <el-date-picker v-model="currentRecord.downloadTime" type="datetime" />
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="currentRecord.status">
            <el-option label="处理中" value="处理中" />
            <el-option label="已发货" value="已发货" />
            <el-option label="已完成" value="已完成" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="recordDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="saveRecord">保存</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'

// 模拟数据
const products = ref([
{
    id: 1,
    name: '非暴力沟通',
    type: '电子书',
    points: 300,
    image: require('@/assets/images/award6.jpg'),
    downloadUrl: '/books/nonviolent-communication.pdf'
  },
  {
    id: 2,
    name: '思考，快与慢',
    type: '电子书',
    points: 400,
    image: require('@/assets/images/award9.jpg'),
    downloadUrl: '/books/thinking-fast-and-slow.epub'
  },
  {
    id: 3,
    name: '心理健康手账本',
    type: '实物',
    points: 600,
    image: require('@/assets/images/award3.jpg'),
    address: '上海市静安区南京西路1234号'
  }
])
const allRecords = ref([
  {
    id: 1,
    user: '用户1',
    phone: '13800138000',
    product: '心理健康知识电子书1',
    points: 100,
    date: '2023-10-10',
    downloadTime: '2023-10-10 10:00:00',
    status: '已完成'
  },
  {
    id: 2,
    user: '用户2',
    phone: '13900139000',
    product: '实物奖品1',
    points: 300,
    date: '2023-10-11',
    downloadTime: '2023-10-12 14:30:00',
    status: '已发货'
  }
])
const searchKey = ref('')
const dateRange = ref([])
const dialogVisible = ref(false)
const recordDialogVisible = ref(false)
const currentProduct = ref({})
const currentRecord = ref({})

const filteredRecords = computed(() => {
  return allRecords.value.filter(record => {
    const dateValid = dateRange.value.length === 0 ||
      (new Date(record.date) >= dateRange.value[0] &&
       new Date(record.date) <= dateRange.value[1])
    return (
      record.user.includes(searchKey.value) ||
      record.phone.includes(searchKey.value)
    ) && dateValid
  })
})

const showAddDialog = () => {
  currentProduct.value = {}
  dialogVisible.value = true
}

const editProduct = (product) => {
  currentProduct.value = { ...product }
  dialogVisible.value = true
}

const saveProduct = () => {
  if (!currentProduct.value.name || !currentProduct.value.points || !currentProduct.value.type) {
    ElMessage.error('请填写完整信息')
    return
  }
  if (currentProduct.value.type === '电子书' && !currentProduct.value.downloadUrl) {
    ElMessage.error('请填写下载地址')
    return
  }
  if (currentProduct.value.type === '实物' && !currentProduct.value.address) {
    ElMessage.error('请填写奖品地址')
    return
  }
  if (currentProduct.value.id) {
    const index = products.value.findIndex(p => p.id === currentProduct.value.id)
    if (index !== -1) {
      products.value[index] = { ...currentProduct.value }
      ElMessage.success('更新成功')
    }
  } else {
    products.value.push({
      ...currentProduct.value,
      id: Date.now()
    })
    ElMessage.success('添加成功')
  }
  dialogVisible.value = false
}

const deleteProduct = (id) => {
  ElMessageBox.confirm('确定要删除该商品吗？', '提示', {
    type: 'warning'
  }).then(() => {
    products.value = products.value.filter(p => p.id !== id)
    ElMessage.success('删除成功')
  }).catch(() => {})
}

const showAddRecordDialog = () => {
  currentRecord.value = {}
  recordDialogVisible.value = true
}

const saveRecord = () => {
  if (!currentRecord.value.user || !currentRecord.value.phone || !currentRecord.value.product || !currentRecord.value.points || !currentRecord.value.date) {
    ElMessage.error('请填写完整信息')
    return
  }
  if (currentRecord.value.id) {
    const index = allRecords.value.findIndex(r => r.id === currentRecord.value.id)
    if (index !== -1) {
      allRecords.value[index] = { ...currentRecord.value }
      ElMessage.success('更新成功')
    }
  } else {
    allRecords.value.push({
      ...currentRecord.value,
      id: Date.now()
    })
    ElMessage.success('添加成功')
  }
  recordDialogVisible.value = false
}

const deleteRecord = (id) => {
  ElMessageBox.confirm('确定要删除该兑换记录吗？', '提示', {
    type: 'warning'
  }).then(() => {
    allRecords.value = allRecords.value.filter(r => r.id !== id)
    ElMessage.success('删除成功')
  }).catch(() => {})
}
</script>

<style scoped>
.admin-container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

.manage-section {
  background: white;
  padding: 20px;
  border-radius: 8px;
  margin-bottom: 30px;
  box-shadow: 0 2px 12px rgba(0,0,0,0.1);
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.filter-bar {
  display: flex;
  gap: 20px;
  margin-bottom: 20px;
}
</style>
