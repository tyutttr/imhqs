<template>
  <div class="dashboard-container">
    <!-- 数据分析看板标题 -->
    <div class="dashboard-header">
      <h2 class="dashboard-title">趣味竞答数据分析</h2>
    </div>

    <!-- 三个模块一行布局 -->
    <el-row :gutter="20" class="chart-row">
      <!-- 正确率趋势图表 -->
      <el-col :span="8">
        <el-card class="chart-card">
          <div class="chart-title">正确率趋势</div>
          <div ref="trendChart" style="height: 300px;"></div>
          <el-table :data="trendData" border size="mini" style="margin-top: 10px;">
            <el-table-column prop="date" label="考试日期" width="120">
              <template #default="{row}">
                <el-date-picker
                  v-model="row.date"
                  type="date"
                  value-format="yyyy-MM-dd"
                  size="mini"
                  @change="updateTrendChart"/>
              </template>
            </el-table-column>
            <el-table-column label="正确率(%)">
              <template #default="{row}">
                <el-input-number
                  v-model="row.rate"
                  :min="0"
                  :max="100"
                  controls-position="right"
                  size="mini"
                  @change="updateTrendChart"/>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>

      <!-- 难度分布 -->
      <el-col :span="8">
        <el-card class="chart-card">
          <div class="chart-title">难度分布</div>
          <div ref="difficultyChart" style="height: 300px;"></div>
          <el-table :data="difficultyData" border size="mini" style="margin-top: 10px;">
            <el-table-column label="考试日期" width="120">
              <template #default="{row}">
                <span>{{ row.date }}</span>
              </template>
            </el-table-column>
            <el-table-column label="简单题数">
              <template #default="{row}">
                <el-input-number
                  v-model="row.easy"
                  :min="0"
                  size="mini"
                  @change="updateDifficultyChart"/>
              </template>
            </el-table-column>
            <el-table-column label="中等题数">
              <template #default="{row}">
                <el-input-number
                  v-model="row.medium"
                  :min="0"
                  size="mini"
                  @change="updateDifficultyChart"/>
              </template>
            </el-table-column>
            <el-table-column label="困难题数">
              <template #default="{row}">
                <el-input-number
                  v-model="row.hard"
                  :min="0"
                  size="mini"
                  @change="updateDifficultyChart"/>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>

      <!-- 平均耗时 -->
      <el-col :span="8">
        <el-card class="chart-card">
          <div class="chart-title">平均耗时</div>
          <div ref="timeChart" style="height: 300px;"></div>
          <el-table :data="timeData" border size="mini" style="margin-top: 10px;">
            <el-table-column prop="date" label="考试日期" width="120"/>
            <el-table-column label="平均耗时(秒)">
              <template #default="{row}">
                <el-input-number
                  v-model="row.time"
                  :min="0"
                  size="mini"
                  @change="updateTimeChart"/>
              </template>
            </el-table-column>
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  data() {
    return {
      // 6次考试模拟数据
      trendData: [
        { date: '2024-03-10', rate: 85 },
        { date: '2024-03-11', rate: 78 },
        { date: '2024-03-12', rate: 92 },
        { date: '2024-03-13', rate: 88 },
        { date: '2024-03-14', rate: 76 },
        { date: '2024-03-15', rate: 90 }
      ],
      difficultyData: [
        { date: '2025-03-10', easy: 10, medium: 15, hard: 5 },
        { date: '2025-03-11', easy: 8, medium: 12, hard: 7 },
        { date: '2025-03-12', easy: 12, medium: 18, hard: 3 },
        { date: '2025-03-13', easy: 9, medium: 14, hard: 6 },
        { date: '2025-03-14', easy: 11, medium: 16, hard: 4 },
        { date: '2025-03-15', easy: 10, medium: 17, hard: 5 }
      ],
      timeData: [
        { date: '2025-03-10', time: 15 },
        { date: '2025-03-11', time: 10 },
        { date: '2025-03-12', time: 8 },
        { date: '2025-03-13', time: 12 },
        { date: '2025-03-14', time: 5 },
        { date: '2025-03-15', time: 4 }
      ],
      chartColors: [
        '#5470C6', '#91CC75', '#FAC858',
        '#EE6666', '#73C0DE', '#3BA272'
      ]
    }
  },
  mounted() {
    this.initCharts()
  },
  methods: {
    initCharts() {
      this.trendChart = echarts.init(this.$refs.trendChart)
      this.difficultyChart = echarts.init(this.$refs.difficultyChart)
      this.timeChart = echarts.init(this.$refs.timeChart)
      this.updateAllCharts()
    },
    updateAllCharts() {
      this.updateTrendChart()
      this.updateDifficultyChart()
      this.updateTimeChart()
    },
    updateTrendChart() {
      const option = {
        color: ['#5470C6'],
        tooltip: {
          trigger: 'axis',
          formatter: '日期：{b0}<br/>正确率：{c0}%'
        },
        xAxis: {
          type: 'category',
          data: this.trendData.map(d => d.date)
        },
        yAxis: {
          type: 'value',
          min: 0,
          max: 100,
          axisLabel: { formatter: '{value}%' }
        },
        series: [{
          type: 'line',
          smooth: true,
          symbolSize: 8,
          data: this.trendData.map(d => d.rate),
          areaStyle: {
            color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
              { offset: 0, color: 'rgba(84, 112, 198, 0.1)' },
              { offset: 1, color: 'rgba(84, 112, 198, 0)' }
            ])
          }
        }]
      }
      this.trendChart.setOption(option)
    },
    updateDifficultyChart() {
      const option = {
        color: ['#91CC75', '#5470C6', '#EE6666'],
        tooltip: { trigger: 'item' },
        legend: {
          orient: 'vertical',
          right: 10,
          top: 20,
          data: ['简单', '中等', '困难']
        },
        dataset: {
          source: [
            ['product', '简单', '中等', '困难'],
            ...this.difficultyData.map(d => [
              d.date,
              d.easy,
              d.medium,
              d.hard
            ])
          ]
        },
        xAxis: { type: 'category' },
        yAxis: {},
        series: [
          { type: 'bar', stack: 'total' },
          { type: 'bar', stack: 'total' },
          { type: 'bar', stack: 'total' }
        ]
      }
      this.difficultyChart.setOption(option)
    },
    updateTimeChart() {
      const option = {
        color: ['#20B2AA'],
        tooltip: {
          trigger: 'axis',
          formatter: '日期：{b0}<br/>平均耗时：{c0}秒'
        },
        xAxis: {
          type: 'category',
          data: this.timeData.map(d => d.date)
        },
        yAxis: { type: 'value' },
        series: [{
          type: 'bar',
          barWidth: '60%',
          data: this.timeData.map(d => d.time)
        }]
      }
      this.timeChart.setOption(option)
    }
  }
}
</script>

<style scoped>
.dashboard-container {
  padding: 20px;
  background: #f8f9fa;
}

.dashboard-header {
  margin-bottom: 20px;
  text-align: center;
}

.dashboard-title {
  color: #2c3e50;
  font-weight: 600;
}

.chart-row {
  display: flex;
  justify-content: space-between;
}

.chart-card {
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.1);
  margin-bottom: 20px;
}

.chart-title {
  font-size: 16px;
  color: #2c3e50;
  font-weight: 600;
  margin-bottom: 15px;
  padding-left: 10px;
  border-left: 4px solid #5470C6;
}

::v-deep .el-table {
  --el-table-border-color: #e4e7ed;
  --el-table-header-bg-color: #f8f9fa;
  th { background-color: #f8f9fa; }
}

::v-deep .el-input-number {
  width: 100px;
}
</style>
