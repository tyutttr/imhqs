<template>
  <div class="points-container">

     <!-- 宣传横幅 -->
     <div class="banner">
      <el-image
        :src="require('@/assets/images/1.jpg')"
        fit="cover"
        class="banner-image"
      />
    </div>
    <!-- 个人信息区 -->
    <div class="user-info">
      <h2>我的积分</h2>
      <div class="points-display">
        <el-statistic :value="userPoints" title="当前积分" />
        <div class="badge">
          <el-icon><Trophy /></el-icon>
          <span>当前排名: #{{ userRank }}</span>
        </div>
      </div>
    </div>

    <!-- 电子书兑换区 -->
    <div class="exchange-area">
      <h3>心理健康知识库</h3>
      <el-table :data="ebooks" stripe style="width: 100%">
        <el-table-column label="电子书封面" width="200">
          <template #default="{ row }">
            <el-image
              :src="row.image"
              fit="cover"
              class="book-cover"
              :preview-src-list="[row.image]"
            />
          </template>
        </el-table-column>
        <el-table-column prop="name" label="书名">
          <template #default="{ row }">
            <div class="book-info">
              <h4>{{ row.name }}</h4>
              <div class="meta">
                <el-tag type="info">{{ row.author }}</el-tag>
                <span class="publisher">{{ row.publisher }}</span>
              </div>
            </div>
          </template>
        </el-table-column>
        <el-table-column prop="points" label="所需积分" width="120" />
        <el-table-column label="操作" width="180">
          <template #default="{ row }">
            <el-button
              type="primary"
              :disabled="userPoints < row.points"
              @click="handleEbookExchange(row)"
            >
              兑换下载 ({{ row.points }}积分)
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 实物兑换区 -->
    <div class="physical-goods">
      <h3>心理健康周边</h3>
      <el-row :gutter="20">
        <el-col
          v-for="item in physicalGoods"
          :key="item.id"
          :xs="12" :sm="8" :md="6" :lg="6"
        >
        <div class="goods-card">
            <el-image
              :src="item.image"
              fit="cover"
              class="goods-image"
              :preview-src-list="[item.image]"
            />
            <div class="goods-info">
              <h4>{{ item.name }}</h4>
              <p class="description">{{ item.description }}</p>
              <div class="goods-footer">
                <div class="points-badge">
                  <el-icon><Coin /></el-icon>
                  <span>{{ item.points }} 积分</span>
                </div>
                <el-button
                  type="success"
                  :disabled="userPoints < item.points || item.stock === 0"
                  @click="handlePhysicalExchange(item)"
                >
                  {{ item.stock > 0 ? '立即兑换' : '已兑完' }}
                </el-button>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>

    <!-- 兑换记录 -->
    <div class="exchange-records">
      <h3>兑换记录</h3>
      <el-table :data="records" stripe style="width: 100%">
        <el-table-column prop="date" label="日期" width="180" />
        <el-table-column prop="name" label="商品名称" />
        <el-table-column prop="points" label="消耗积分" />
        <el-table-column label="状态" width="150">
          <template #default="{ row }">
            <el-tag
              :type="statusTypeMap[row.status]"
              effect="plain"
            >
              {{ row.status }}
              <el-icon v-if="row.status === '已完成'" class="icon">
                <CircleCheck />
              </el-icon>
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作">
          <template #default="{ row }">
            <el-button
              v-if="row.type === 'ebook'"
              type="text"
              @click="downloadEbook(row)"
            >
              重新下载
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 电子书兑换弹窗 -->
    <el-dialog
      v-model="showEbookDialog"
      title="兑换确认"
      width="30%"
    >
      <div class="dialog-content">
        <el-image :src="selectedEbook.cover" fit="cover" class="preview-cover" />
        <h4>{{ selectedEbook.name }}</h4>
        <p class="description">{{ selectedEbook.description }}</p>
        <div class="action-buttons">
          <el-button @click="showEbookDialog = false">取消</el-button>
          <el-button
            type="primary"
            @click="confirmEbookExchange"
          >
            确认兑换 ({{ selectedEbook.points }}积分)
          </el-button>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref } from 'vue'

// 用户数据
const userPoints = ref(2560)
const userRank = ref(15)

// 电子书数据（来自网页1、3、7、8）
const ebooks = ref([
{
    id: 1,
    name: '心理医生为什么没有告诉我',
    points: 350,
    image: require('@/assets/images/award2.jpg'),
    author: '【美】Edmund J. Bourne',
    publisher: '中国大百科全书出版社',
    downloadUrl: '/books/anxiety-phobia-workbook.pdf'
  },
  {
    id: 2,
    name: '自卑与超越',
    points: 280,
    image: require('@/assets/images/award8.jpg'),
    author: '【奥】阿尔弗雷德·阿德勒',
    publisher: '华东师范大学出版社',
    downloadUrl: '/books/what-life-could-mean.pdf'
  },
  // 更多电子书...
])

// 实物商品数据（参考网页9、10）
const physicalGoods = ref([
{
    id: 101,
    name: '正念冥想礼盒',
    points: 800,
    image: require('@/assets/images/award4.jpg'),
    stock: 5,
    description: '包含冥想香薰蜡烛和指导手册'
  },
  {
    id: 102,
    name: '情绪管理套装',
    points: 650,
    image: require('@/assets/images/award5.jpg'),
    stock: 8,
    description: '内含情绪卡片和减压玩具'
  },
  // 更多实物...
])

// 兑换记录
const records = ref([
  {
    date: '2025-03-15',
    name: '蛤蟆先生去看心理医生',
    points: 200,
    status: '已完成',
    type: 'ebook',
    downloadUrl: 'https://pan.quark.cn/s/d74ee562e77b'
  },
  // 更多记录...
])

// 电子书兑换相关状态
const showEbookDialog = ref(false)
const selectedEbook = ref({})

const statusTypeMap = {
  '处理中': 'warning',
  '已发货': '',
  '已完成': 'success'
}

// 电子书兑换流程
const handleEbookExchange = (book) => {
  selectedEbook.value = book
  showEbookDialog.value = true
}

const confirmEbookExchange = () => {
  userPoints.value -= selectedEbook.value.points
  records.value.unshift({
    date: new Date().toISOString().slice(0,10),
    name: selectedEbook.value.name,
    points: selectedEbook.value.points,
    status: '已完成',
    type: 'ebook',
    downloadUrl: selectedEbook.value.downloadUrl
  })
  showEbookDialog.value = false
  ElMessage.success('兑换成功！即将开始下载')
  // 模拟下载
  window.open(selectedEbook.value.downloadUrl, '_blank')
}

// 实物兑换
const handlePhysicalExchange = (item) => {
  ElMessageBox.confirm(
    `确认兑换 ${item.name} 将扣除 ${item.points} 积分`,
    '兑换确认',
    {
      confirmButtonText: '确认兑换',
      cancelButtonText: '取消',
      type: 'warning'
    }
  ).then(() => {
    userPoints.value -= item.points
    item.stock -= 1
    records.value.unshift({
      date: new Date().toISOString().slice(0,10),
      name: item.name,
      points: item.points,
      status: '处理中',
      type: 'physical'
    })
    ElMessage.success('兑换成功，奖品将在3个工作日内发出')
  })
}

// 重新下载
const downloadEbook = (record) => {
  window.open(record.downloadUrl, '_blank')
}
</script>

<style scoped>
/* 新增样式 */
.banner-image {
  width: 100%;
  height: 250px;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0,0,0,0.15);
}

.book-cover {
  width: 120px;
  height: 160px;
  border-radius: 4px;
  transition: transform 0.3s;
}

.goods-image {
  height: 200px;
  border-bottom: 1px solid #ebeef5;
}

.goods-info {
  padding: 15px;

  .description {
    color: #606266;
    font-size: 0.9em;
    margin: 10px 0;
    height: 40px;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;

    -webkit-box-orient: vertical;
  }

  .points-badge {
    display: flex;
    align-items: center;
    gap: 5px;
    color: #e6a23c;
    font-weight: bold;
  }
}

.ebook-info {
  display: flex;
  align-items: center;
  gap: 15px;
}

.book-cover {
  width: 80px;
  height: 110px;
  border-radius: 4px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
}

.meta-info {
  margin-top: 8px;
  display: flex;
  align-items: center;
  gap: 8px;
  color: #909399;
}

.goods-card {
  background: white;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 12px rgba(0,0,0,0.1);
}

.goods-image {
  width: 100%;
  height: 180px;
}

.goods-info {
  padding: 15px;
}

.goods-footer {
  margin-top: 15px;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.dialog-content {
  text-align: center;

  .preview-cover {
    width: 120px;
    height: 160px;
    margin-bottom: 20px;
    border-radius: 4px;
  }

  .description {
    color: #606266;
    line-height: 1.6;
    margin: 15px 0;
  }
}

.action-buttons {
  margin-top: 25px;
}
</style>
