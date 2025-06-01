<template>

  <data-table
    ref="pagingTable"
    :options="options"
    :list-query="listQuery"
  >
    <template slot="filter-content">


      <el-date-picker
        v-model="listQuery.params.startTime"
        class="filter-item"
        value-format="yyyy-MM-dd"
        type="date"
        placeholder="竞答开始时间"
      />

      <el-date-picker
        v-model="listQuery.params.endTime"
        class="filter-item"
        value-format="yyyy-MM-dd"
        type="date"
        placeholder="竞答结束时间"
      />

      <el-input v-model="listQuery.params.title" placeholder="搜索竞答名称" style="width: 200px;" class="filter-item" />

    </template>

    <template slot="data-columns">

      <el-table-column
        label="竞答名称"
        prop="title"
      />


      <el-table-column
        label="竞答时间"
        width="220px"
        align="center"
      >

        <template slot-scope="scope">
          <span v-if="scope.row.timeLimit">
            {{ scope.row.startTime }} ~ {{ scope.row.endTime }}
          </span>
          <span v-else>不限时</span>
        </template>

      </el-table-column>

      <el-table-column
        label="竞答总分"
        prop="totalScore"
        align="center"
      />

     <!--  <el-table-column
        label="及格线"
        prop="qualifyScore"
        align="center"
      /> -->

      <el-table-column
        label="状态"
        align="center"
      >

        <template slot-scope="scope">
          {{ scope.row.state | examStateFilter(scope.row.state) }}
        </template>

      </el-table-column>

      <el-table-column
        label="操作"
        align="center"
        width="220px"
      >
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-edit" @click="handleUpdateExam(scope.row.id)">修改</el-button>
          <el-button type="warning" size="mini" icon="el-icon-user" @click="handleExamDetail(scope.row.id)">竞答详情</el-button>
        </template>
      </el-table-column>

    </template>

  </data-table>

</template>

<script>
import DataTable from '@/components/DataTable'
import Sortable from 'sortablejs'; // 新增引入

export default {
  name: 'ExamList',
  components: { DataTable },
  data() {
    return {

      listQuery: {
        current: 1,
        size: 10,
        params: {
          title: ''
        }
      },

      options: {
        // 可批量操作
        multi: true,
        // 批量操作列表
        multiActions: [
          {
            value: 'delete',
            label: '删除'
          },
        ],
        // 列表请求URL
        listUrl: '/exam/api/exam/exam/paging',
        // 删除请求URL
        deleteUrl: '/exam/api/exam/exam/delete',
        // 删除请求URL
        stateUrl: '/exam/exam/state',
        addRoute: 'AddExam'
      }
    }
  },
  mounted() {
    this.initRowDrag();
  },
  methods: {

    handleExamDetail(examId) {
      this.$router.push({ name: 'ListExamUser', params: { examId: examId }})
    },

    handleUpdateExam(examId) {
      this.$router.push({ name: 'UpdateExam', params: { id: examId }})
    },

    // 新增方法：初始化行拖拽
    initRowDrag() {
      const tbody = document.querySelector('.el-table__body-wrapper tbody');
      if (tbody) {
        Sortable.create(tbody, {
          animation: 150, // 拖拽动画时长
          ghostClass: 'drag-ghost', // 拖拽占位符样式
          onEnd: (evt) => {
            // 获取拖拽前后索引
            const oldIndex = evt.oldIndex;
            const newIndex = evt.newIndex;

            // 更新数据顺序（假设数据存储在 this.$refs.pagingTable.list 中）
            const rows = Array.from(this.$refs.pagingTable.list);
            const movedRow = rows.splice(oldIndex, 1)[0];
            rows.splice(newIndex, 0, movedRow);

            // 触发 Vue 响应式更新
            this.$refs.pagingTable.list = rows;
          }
        });
      }
    },

  },
    filters: {
        examStateFilter(value){
            if(value===0) return "进行中"
            else if(value===2) return "尚未开放"
            else if(value===3) return "已结束"
        }
    }
}
</script>
<style scoped>
/* 拖拽占位符高亮 */
.drag-ghost {
  opacity: 0.5;
  background: #f8f8f8;
  border: 2px dashed #409EFF;
}

/* 拖拽手柄图标（可选） */
.el-table__row .el-icon-rank {
  cursor: move;
  color: #999;
}
</style>
