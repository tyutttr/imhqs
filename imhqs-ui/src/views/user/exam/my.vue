<template>

  <div>
    <data-table
      ref="pagingTable"
      :options="options"
      :list-query="listQuery"
    >
      <template slot="filter-content">

        <el-input v-model="listQuery.params.title" placeholder="搜索竞答名称" style="width: 200px;" class="filter-item" />

      </template>

      <template slot="data-columns">

        <el-table-column
          label="竞答名称"
          prop="title"
          show-overflow-tooltip
        />

        <el-table-column
          label="竞答次数"
          prop="tryCount"
          align="center"
        />

        <el-table-column
          label="最高分"
          prop="maxScore"
          align="center"
        />

       <!--  <el-table-column
          label="是否通过"
          align="center"
        >

          <template slot-scope="scope">
            <span v-if="scope.row.passed" style="color: #00ff00;">通过</span>
            <span v-else style="color: #ff0000;">未通过</span>
          </template>

        </el-table-column> -->
        <!-- 正确率 -->

        <el-table-column
          label="最后竞答时间"
          prop="updateTime"
          align="center"
        />


      </template>

    </data-table>


  </div>

</template>

<script>
import DataTable from '@/components/DataTable'
import MyPaperList from './paper'
import { mapGetters } from 'vuex'

export default {
  name: 'MyExamList',
  components: { MyPaperList, DataTable },
  data() {
    return {

      dialogVisible: false,
      examId: '',

      listQuery: {
        current: 1,
        size: 10,
        params: {
          title: ''
        }
      },

      options: {
        // 可批量操作
        multi: false,
        // 列表请求URL
        listUrl: '/exam/api/user/exam/my-paging'
      }
    }
  },
  computed: {
    ...mapGetters([
      'userId'
    ])
  },
  methods: {

    // 开始竞答
    handleExamDetail(examId) {
      this.examId = examId
      this.dialogVisible = true
    },


  }
}
</script>

<style scoped>

  .el-dialog-div{
    height: 60vh;
    overflow: auto;
    padding: 10px;
  }

</style>
