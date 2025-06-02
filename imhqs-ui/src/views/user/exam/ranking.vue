<template>
  <div class="ranking-container">
    <h1 class="ranking-title">排行榜</h1>

    <div class="ranking-filters">
      <div class="date-range">
        <span>时间范围: </span>
        <el-date-picker
          v-model="dateRange"
          type="daterange"
          range-separator="至"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          @change="handleDateChange"
        />
      </div>

      <div class="ranking-tabs">
        <button @click="activeTab = 'global'" :class="{ active: activeTab === 'global' }" class="tab-button">
          全局排名
        </button>
        <button @click="activeTab = 'daily'" :class="{ active: activeTab === 'daily' }" class="tab-button">
          每日排名
        </button>
      </div>
    </div>

    <div class="ranking-table-container">
      <table class="ranking-table">
        <thead>
          <tr class="ranking-header">
            <th class="ranking-position-header">排名</th>
            <th class="ranking-avatar-header">头像</th>
            <th class="ranking-name-header">用户名</th>
            <th class="ranking-time-header">最近答题时间</th>
            <th class="ranking-score-header">总积分</th>
          </tr>
        </thead>
        <tbody>
          <tr
            v-for="(user, index) in currentRanking"
            :key="user.id"
            class="ranking-row"
            :class="{ 'top-three': index < 3 }"
          >
            <td class="ranking-position-cell">{{ index + 1 }}</td>
            <td class="ranking-avatar-cell">
              <div class="avatar-container">
                <img
                  :src="getAvatarPath(user.id)"
                  alt="用户头像"
                  class="avatar"
                  @click="toggleZoom(user.id)"
                  :style="{ cursor: 'pointer' }"
                />
                <div v-if="zoomedAvatar === user.id" class="zoomed-avatar">
                  <img :src="getAvatarPath(user.id)" alt="放大头像" class="zoomed-image" />
                </div>
              </div>
            </td>
            <td class="ranking-name-cell">{{ user.userName }}</td>
            <td class="ranking-time-cell">{{ formatTime(user.answerTime) }}</td>
            <td class="ranking-score-cell">{{ user.totalScore }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  name: "RankingList",
  data() {
    return {
      activeTab: "global", // 当前活跃的标签页
      dateRange: null, // 用户选择的时间范围
      zoomedAvatar: null, // 当前放大的头像
      users: [
        {
          id: 1,
          userName: "userA",
          answerTime: "2025-03-15 10:00:00",
          totalScore: 50, // 总积分
          isRedeemed: false // 是否已兑换奖品
        },
        {
          id: 2,
          userName: "userB",
          answerTime: "2025-03-15 09:30:00",
          totalScore: 48,
          isRedeemed: false
        },
        {
          id: 3,
          userName: "admin",
          answerTime: "2025-03-15 09:15:00",
          totalScore: 45,
          isRedeemed: false
        },
        {
          id: 4,
          userName: "ry",
          answerTime: "2025-03-15 08:45:00",
          totalScore: 42,
          isRedeemed: false
        },
        {
          id: 5,
          userName: "123",
          answerTime: "2025-03-15 08:30:00",
          totalScore: 40,
          isRedeemed: false
        },
        {
          id: 6,
          userName: "AdminA",
          answerTime: "2025-03-14 14:00:00",
          totalScore: 38,
          isRedeemed: false
        },
        {
          id: 7,
          userName: "Admin2",
          answerTime: "2025-03-14 13:30:00",
          totalScore: 35,
          isRedeemed: false
        },
        {
          id: 8,
          userName: "用户H",
          answerTime: "2025-03-13 11:00:00",
          totalScore: 30,
          isRedeemed: false
        }
      ]
    };
  },
  computed: {
    // 当前显示的排名列表
    currentRanking() {
      if (this.activeTab === "global") {
        // 全局排名按总积分降序排序
        return [...this.users].sort((a, b) => b.totalScore - a.totalScore);
      } else {
        // 每日排名按选定日期和总积分降序排序
        if (!this.dateRange) return [];
        const [startDate, endDate] = this.dateRange;
        return [...this.users]
          .filter(user => {
            const userDate = new Date(user.answerTime).toDateString();
            const start = new Date(startDate).toDateString();
            const end = new Date(endDate).toDateString();
            return userDate >= start && userDate <= end;
          })
          .sort((a, b) => b.totalScore - a.totalScore);
      }
    }
  },
  methods: {
    // 格式化时间显示
    formatTime(timeString) {
      return new Date(timeString).toLocaleString();
    },
    // 处理日期范围变化
    handleDateChange(newDateRange) {
      // 可以在这里添加额外的处理逻辑
    },
    // 获取用户头像路径
    getAvatarPath(userId) {
      return `@/assets/user-icon/${userId}.jpg`;
    },
    // 切换头像缩放状态
    toggleZoom(userId) {
      this.zoomedAvatar = this.zoomedAvatar === userId ? null : userId;
    }
  }
};
</script>

<style scoped>
.ranking-container {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px;
  font-family: Arial, sans-serif;
  background-color: #f4f4f4;
}

.ranking-title {
  text-align: center;
  font-size: 28px;
  color: #333;
  margin-bottom: 30px;
}

.ranking-filters {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}

.date-range {
  display: flex;
  align-items: center;
}

.ranking-tabs {
  display: flex;
}

.tab-button {
  padding: 10px 20px;
  margin-left: 5px;
  background-color: #f0f0f0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.tab-button.active {
  background-color: #42b983;
  color: white;
}

.ranking-table-container {
  background-color: #fff;
  border-radius: 8px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
}

.ranking-table {
  width: 100%;
  border-collapse: collapse;
}

.ranking-header {
  background-color: #f8f8f8;
  font-weight: bold;
}

.ranking-row {
  border-bottom: 1px solid #eee;
}

.ranking-row:last-child {
  border-bottom: none;
}

.ranking-row:hover {
  background-color: #f9f9f9;
}

.ranking-position-cell,
.ranking-avatar-cell,
.ranking-name-cell,
.ranking-time-cell,
.ranking-score-cell {
  padding: 12px 15px;
  text-align: left;
}

.ranking-position-cell {
  font-weight: bold;
  color: #666;
}

.ranking-avatar-cell {
  width: 70px;
  position: relative;
}

.avatar-container {
  display: flex;
  align-items: center;
  position: relative;
}

.avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  object-fit: cover;
  margin-right: 10px;
  transition: transform 0.3s;
}

.avatar:hover {
  transform: scale(1.1);
}

.zoomed-avatar {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.8);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.zoomed-image {
  width: 200px;
  height: 200px;
  border-radius: 50%;
  object-fit: cover;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.5);
}

.top-three {
  background-color: rgba(255, 103, 0, 0.1);
  color: #ff6700;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .ranking-table-container {
    overflow-x: auto;
  }

  .ranking-position-cell,
  .ranking-avatar-cell,
  .ranking-name-cell,
  .ranking-time-cell,
  .ranking-score-cell {
    padding: 10px 8px;
    font-size: 14px;
  }

  .ranking-filters {
    flex-direction: column;
    align-items: flex-start;
  }

  .date-range,
  .ranking-tabs {
    margin-bottom: 10px;
  }
}
</style>
