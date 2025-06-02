<template>
  <!-- 使用一个div元素包裹所有内容，使其成为唯一的根元素 -->
  <div>
    <el-table :data="questionStats">
      <el-table-column prop="difficulty" label="难度等级"/>
      <el-table-column prop="avgTime" label="平均耗时（秒）"/>
      <el-table-column prop="accuracy" label="正确率"/>
    </el-table>
    <echart :options="difficultyChart"/>
  </div>
</template>

<script>
export default {
  data() {
    return {
      questionStats: [],
      difficultyChart: {}
    };
  }
};
</script>
