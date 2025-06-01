/* // src/api/feedbackApi.js
import axios from 'axios';

const apiClient = axios.create({
  baseURL: process.env.VUE_APP_API_BASE,
  timeout: 10000
});

export default {
  // 获取正确率趋势
  async getAccuracyTrend(params) {
    try {
      return await apiClient.get('/api/feedback/accuracy-trend', { params });
    } catch (error) {
      this.handleApiError(error);
    }
  },

  // 获取题目难度分布
  async getDifficultyStats(examId) {
    try {
      return await apiClient.get(`/api/feedback/difficulty-stats/${examId}`);
    } catch (error) {
      this.handleApiError(error);
    }
  },

  // 统一错误处理
  handleApiError(error) {
    if (error.response?.status === 401) {
      router.push('/login');
    }
    throw new Error(`API Error: ${error.message}`);
  }
}
 */
