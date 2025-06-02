<template>
  <div class="app-container home">
    <el-row :gutter="20" class="content-row">
      <el-col :sm="66" :lg="24" class="content-col">
        <div class="welcome-text">
          <h1>心理健康知识趣味竞答系统</h1>
          <p>
            欢迎使用心理健康知识趣味竞答系统，点击下方按钮开始竞答！预祝取得好成绩！
          </p>
        </div>
        <!-- 添加按钮 -->
        <el-button type="primary" class="start-button" @click="startExam">开始竞答</el-button>
      </el-col>
    </el-row>
    <el-divider class="divider" />
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      // 假设题库id数组
      examIds: [1, 2, 3, 4, 5],
    };
  },
  methods: {
    startExam() {
      console.log("开始竞答按钮被点击"); // 添加日志输出，确认方法被调用
      // 跳转到竞答页面
      this.$router.push('/my/exam');
    },
  },
};
</script>

<style scoped lang="scss">
.home {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding: 50px;
  background-color: #f8f8f8;
  min-height: 100vh;
  box-sizing: border-box;

  .content-row {
    width: 100%;
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
    display: flex;
     
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    text-align: center;
  }

  .content-col {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    padding: 20px;
    background-color: #fff;
    border-radius: 10px;

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
    background-color: #409eff;
    border-color: #409eff;
    color: #fff;
    cursor: pointer;
    border-radius: 5px;
    transition: background-color 0.3s ease;

    &:hover {
      background-color: #66b1ff;
      border-color: #66b1ff;
    }
  }

  .divider {
    width: 100%;
    margin: 20px 0;
    border-color: #ddd;
  }
}
</style>
