<template>
  <div class="app-container">
    <!-- <el-dialog

      :show-close="false"
      title="考生诚信竞答承诺说明"
      :close-on-click-modal="false"
      :visible.sync="dialogVisible1"
      width="80%"
      :before-close="handleClose">
      <span style="font-size: large">我已认真阅读《国家教育竞答违规处理办法》以及省级教育招生竞答机构和招生单位发布的相关招考信息。我已清楚了解，根据《中华人民共和国刑法修正案（九）》第二百八十四条，在法律规定的国家竞答中，组织作弊的行为；为他人实施组织作弊提供作弊器材或者其他帮助的行为；为实施竞答作弊行为，向他人非法出售或者提供竞答的试题、答案的行为；代替他人或者让他人代替自己参加竞答的行为都将触犯刑法。根据2019年9月起施行的《最高人民法院、最高人民检察院关于办理组织竞答作弊等刑事案件适用法律若干问题的解释》，在竞答中“组织竞答作弊”“非法出售或者提供试题、答案”等情形，均应认定为刑法第二百八十四条规定之一的“情节严重”，将依法从重追究刑事责任。</span><br><br>
      <span style="font-size: large">我郑重承诺：</span><br><br>
      <span style="font-size: large">一、保证在网上报名和网上确认（现场确认）时，严格按照报考条件及相关政策要求选择填报志愿，如实、准确提交报考信息和各项材料。如提供虚假、错误信息或弄虚作假，本人承担由此造成的一切后果。</span><br><br>
      <span style="font-size: large">二、自觉服从竞答组织管理部门的统一安排，接受监考人员的管理、监督和检查。</span><br><br>
      <span style="font-size: large">三、自觉遵守相关法律和竞答纪律、考场规则，诚信竞答，不作弊。</span>
      <span slot="footer" class="dialog-footer">

  </span>
    </el-dialog> -->
    <el-row :gutter="24">

      <el-col :span="24" style="margin-bottom: 20px">

        <el-alert
          title="点击`开始竞答`后将自动进入竞答"

          type="error"
          style="margin-bottom: 10px"
        /><!-- ，请诚信竞答！"  -->

        <el-card class="pre-exam">

          <div><strong>竞答名称：</strong>{{ detailData.title }}</div>
          <div><strong>竞答时长：</strong>{{ detailData.totalTime }}分钟</div>
          <div><strong>试卷总分：</strong>{{ detailData.totalScore }}分</div>
         <!--  <div><strong>及格分数：</strong>{{ detailData.qualifyScore }}分</div> -->
          <div><strong>竞答描述：</strong>{{ detailData.content }}</div>

        </el-card>

      </el-col>

      <el-col :span="24">

        <el-button :loading="loading" type="primary" icon="el-icon-caret-right" @click="handleCreate">
          开始竞答
        </el-button>

        <el-button @click="handleBack">
          返回
        </el-button>

      </el-col>

    </el-row>
  </div>
</template>

<script>
import { fetchDetail } from '@/api/exam/exam'
import { createPaper } from '@/api/paper/exam'

export default {
  data() {
    return {
      dialogVisible1: false,
      detailData: {},
      postForm: {
        examId: '',
        password: ''
      },
      rules: {
        password: [
          { required: true, message: '竞答密码不能为空！' }
        ]
      },

      loading: false
    }
  },

  created() {
    this.postForm.examId = this.$route.params.examId
    this.fetchData()
  },

  methods: {



    fetchData() {
      fetchDetail(this.postForm.examId).then(response => {
        this.detailData = response.data
      })
    },

    async handleCreate() {
      this.dialogVisible1=true

      const that = this
      this.loading = true

      await createPaper(this.postForm).then(response => {
        console.log(response)

        if (response.code === 0) {
          setTimeout(function() {
            this.loading = false
            that.dialogVisible = false
            that.$router.push({ name: 'StartExam', params: { id: response.data.id }})
          }, 1000)

        }
        setTimeout(function () {
          this.dialogVisible1=false;
        },5000)

      }).catch(() => {
        this.loading = false
      })
    },

    handleBack() {
      this.$router.push({ name: 'ExamOnline' })
    }

  }
}
</script>

<style scoped>

  .pre-exam div {

    line-height: 42px;
    color: #555555;
  }


</style>

