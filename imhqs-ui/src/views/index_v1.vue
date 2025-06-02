<template>
  <div class="dashboard-editor-container">
    <el-row style="background:#fff; margin-bottom:32px;">
      <el-col :sm="66" :lg="24" class="content-col">

          <h1>心理健康知识趣味竞答系统</h1>
          <p>
            欢迎使用心理健康知识趣味竞答系统，点击下方按钮开始竞答！预祝取得好成绩！
          </p>
        <!-- 添加按钮 -->
        <el-button type="primary" class="start-button" @click="startExam">开始竞答</el-button>
      </el-col>
    </el-row>


    <el-row :gutter="32">
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <raddar-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <pie-chart />
        </div>
      </el-col>
      <el-col :xs="24" :sm="24" :lg="8">
        <div class="chart-wrapper">
          <bar-chart />
        </div>
      </el-col>
    </el-row>


  </div>
</template>

<script>
import PanelGroup from './dashboard/PanelGroup'
import LineChart from './dashboard/LineChart'
import RaddarChart from './dashboard/RaddarChart'
import PieChart from './dashboard/PieChart'
import BarChart from './dashboard/BarChart'

const lineChartData = {
  newVisitis: {
    expectedData: [100, 120, 161, 134, 105, 160, 165],
    actualData: [120, 82, 91, 154, 162, 140, 145]
  },
  messages: {
    expectedData: [200, 192, 120, 144, 160, 130, 140],
    actualData: [180, 160, 151, 106, 145, 150, 130]
  },
  purchases: {
    expectedData: [80, 100, 121, 104, 105, 90, 100],
    actualData: [120, 90, 100, 138, 142, 130, 130]
  },
  shoppings: {
    expectedData: [130, 140, 141, 142, 145, 150, 160],
    actualData: [120, 82, 91, 154, 162, 140, 130]
  }
}

export default {
  name: 'Index',
  components: {
    PanelGroup,

    RaddarChart,
    PieChart,
    BarChart
  },
  data() {
    return {
      lineChartData: lineChartData.newVisitis
    }
  },
  methods: {

     startExam() {
      console.log("开始竞答按钮被点击"); // 添加日志输出，确认方法被调用
      // 跳转到竞答页面
      this.$router.push('/my/exam');
    },
  }
}
</script>

<style lang="scss" scoped>
.dashboard-editor-container {
  padding: 32px;
  background-color: rgb(240, 242, 245);
  position: relative;

  .chart-wrapper {
    background: #fff;
    padding: 16px 16px 0;
    margin-bottom: 32px;
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    text-align: center;
  }
}
 .content-row {
    width: 100%;
    max-width: 1200px;
    margin: 0 auto;

  }

  .content-col {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 20px;

    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    text-align: center;

  }



  h1 {
    font-size: 36px;
    font-weight: bold;
    color: #333;
    margin-bottom: 20px;
  }

  p {
    font-size: 18px;
    color: #666;
    line-height: 1.5;
    margin-bottom: 30px;
  }

  .start-button {
    padding: 15px 30px;
    font-size: 18px;
    background-color: #f88b06;
    border-color: #f88b06;
    color: #fff;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s ease;

    &:hover {
      background-color: #f88b06;
      border-color: #f88b06;
    }
  }

@media (max-width:1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>
