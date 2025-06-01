import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'


/**
 * Note: 路由配置项
 *
 * hidden: true                     // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true                 // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                  // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                  // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                  // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect             // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'               // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * query: '{"id": 1, "name": "ry"}' // 访问路由的默认传递参数
 * roles: ['admin', 'common']       // 访问路由的角色权限
 * permissions: ['a:a:a', 'b:b:b']  // 访问路由的菜单权限
 * meta : {
    noCache: true                   // 如果设置为true，则不会被 <keep-alive> 缓存(默认 false)
    title: 'title'                  // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'                // 设置该路由的图标，对应路径src/assets/icons/svg
    breadcrumb: false               // 如果设置为false，则不会在breadcrumb面包屑中显示
    activeMenu: '/system/user'      // 当路由设置了该属性，则会高亮相对应的侧边栏。
  }
 */

// 公共路由
export const constantRoutes = [
  {
    path: '/redirect',
    component: Layout,
    hidden: true,
    children: [
      {
        path: '/redirect/:path(.*)',
        component: () => import('@/views/redirect')
      }
    ]
  },
  {
    path: '/login',
    component: () => import('@/views/login'),
    hidden: true
  },
  {
    path: '/register',
    component: () => import('@/views/register'),
    hidden: true
  },
  {
    path: '/404',
    component: () => import('@/views/error/404'),
    hidden: true
  },
  {
    path: '/401',
    component: () => import('@/views/error/401'),
    hidden: true
  },
  {
    path: '',
    component: Layout,
    redirect: 'index',
    children: [
      {
        path: 'index',
        component: () => import('@/views/index_v1'),
        name: 'Index',
        meta: { title: '首页', icon: 'dashboard', affix: true }
      }
    ]
  },
  {
    path: '/user',
    component: Layout,
    hidden: true,
    redirect: 'noredirect',
    children: [
      {
        path: 'profile',
        component: () => import('@/views/system/user/profile/index'),
        name: 'Profile',
        meta: { title: '个人中心', icon: 'user' }
      }
    ]
  },

   // 奖项管理模块
   {
    path: '/exam',
    component: Layout,
    meta: { staticRoute: true },
    redirect: '/exam/awardMa',
    meta: { title: '竞答王者', icon: 'exam' },
    children: [
      {
        path: 'awardMa',
        component: () => import('@/views/user/exam/awardMa.vue'),
        name: 'AwardMa',
        meta: {
          title: '积分兑换管理',
          icon: 'award',
          alwaysShow: true // 强制显示父级菜单
        }
      },
      {
        path: 'award',
        component: () => import('@/views/user/exam/award.vue'),
        name: 'Award',
        meta: {
          title: '我的奖励',
          icon: 'medal'
        }
      },
      {
        path: 'ranking',
        component: () => import('@/views/user/exam/ranking.vue'),
        name: 'Ranking',
        meta: {
          title: '排行榜',
          icon: 'rank'
        }
      },
      {
        path: 'trend',
        component: () => import('@/views/user/echarts/FeedbackView.vue'),
        name: 'AccuracyTrend',
        meta: {
          title: '答题情况分析',
          icon: 'trend-chart',
          noCache: true
        }
      }
    ]
  },
]


//动态路由，基于用户权限动态去加载
export const dynamicRoutes = [
  {
    path: '/system/user-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:user:edit'],
    children: [
      {
        path: 'role/:userId(\\d+)',
        component: () => import('@/views/system/user/authRole'),
        name: 'AuthRole',
        meta: { title: '分配角色', activeMenu: '/system/user' }
      }
    ]
  },
  {
    path: '/system/role-auth',
    component: Layout,
    hidden: true,
    permissions: ['system:role:edit'],
    children: [
      {
        path: 'user/:roleId(\\d+)',
        component: () => import('@/views/system/role/authUser'),
        name: 'AuthUser',
        meta: { title: '分配用户', activeMenu: '/system/role' }
      }
    ]
  },
  {
    path: '/system/dict-data',
    component: Layout,
    hidden: true,
    permissions: ['system:dict:list'],
    children: [
      {
        path: 'index/:dictId(\\d+)',
        component: () => import('@/views/system/dict/data'),
        name: 'Data',
        meta: { title: '字典数据', activeMenu: '/system/dict' }
      }
    ]
  },
  {
    path: '/monitor/job-log',
    component: Layout,
    hidden: true,
    permissions: ['monitor:job:list'],
    children: [
      {
        path: 'index',
        component: () => import('@/views/monitor/job/log'),
        name: 'JobLog',
        meta: { title: '调度日志', activeMenu: '/monitor/job' }
      }
    ]
  },
  {
    path: '/tool/gen-edit',
    component: Layout,
    hidden: true,
    permissions: ['tool:gen:edit'],
    children: [
      {
        path: 'index/:tableId(\\d+)',
        component: () => import('@/views/tool/gen/editTable'),
        name: 'GenEdit',
        meta: { title: '修改生成配置', activeMenu: '/tool/gen' }
      }
    ]
  },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [
      {
        path: 'qu/view/:id',
        component: () => import('@/views/qu/qu/view'),
        name: 'ViewQu',
        meta: { title: '题目详情', noCache: true, activeMenu: '/manage/qu' },
        hidden: true
      }
    ]
  },

  {
    path: '/exam/start/:id',
    roles: ['student', 'admin'],
    component: () => import('@/views/paper/exam/exam'),
    name: 'StartExam',
    meta: { title: '开始竞答' },
    hidden: true
  },

  {
    path: '/my',
    component: Layout,
    redirect: '/my/exam',
    name: 'Online',
    roles: ['student', 'admin'],
    meta: {
      title: '在线竞答',
      icon: 'list',

    },
    children: [
      {
        path: 'exam',
        component: () => import('@/views/paper/exam/list'),
        name: 'ExamOnline',
        meta: { title: '在线竞答', noCache: true, icon: 'guide' }
      },

      {
        path: 'exam/prepare/:examId',
        component: () => import('@/views/paper/exam/preview'),
        name: 'PreExam',
        meta: { title: '准备竞答', noCache: true, activeMenu: '/my/exam' },
        hidden: true
      },
      {
        path: 'exam/result/:id',
        component: () => import('@/views/paper/exam/result'),
        name: 'ShowExam',
        meta: { title: '竞答结果', noCache: true, activeMenu: '/online/exam' },
        hidden: true
      },
      {
        path: 'exam/records',
        component: () => import('@/views/user/exam/my'),
        name: 'ListMyExam',
        meta: { title: '我的成绩', noCache: true, icon: 'results' }
      },



    ]
  },

  {
    path: '/exam',
    component: Layout,

    roles: ['admin'],
    redirect: '/exam/repo',
    name: 'Manage',
    meta: {
      title: '竞答管理',
      icon: 'example',
    },
    children: [

      {
        path: 'repo',
        component: () => import('@/views/qu/repo'),
        name: 'ListRepo',
        meta: { title: '题库管理', noCache: true, icon: 'repo' }
      },

      {
        path: 'repo/add',
        component: () => import('@/views/qu/repo/form'),
        name: 'AddRepo',
        meta: { title: '添加题库', noCache: true, activeMenu: '/exam/repo' },
        hidden: true
      },

      {
        path: 'repo/update/:id',
        component: () => import('@/views/qu/repo/form'),
        name: 'UpdateRepo',
        meta: { title: '题库详情', noCache: true, activeMenu: '/exam/repo' },
        hidden: true
      },

      {
        path: 'qu',
        component: () => import('@/views/qu/qu'),
        name: 'ListQu',
        meta: { title: '试题管理', noCache: true, icon: 'support' }
      },

      {
        path: 'qu/add',
        component: () => import('@/views/qu/qu/form'),
        name: 'AddQu',
        meta: { title: '添加试题', noCache: true, activeMenu: '/exam/qu' },
        hidden: true
      },

      {
        path: 'qu/update/:id',
        component: () => import('@/views/qu/qu/form'),
        name: 'UpdateQu',
        meta: { title: '修改试题', noCache: true, activeMenu: '/exam/qu' },
        hidden: true
      },

      {
        path: 'exam',
        component: () => import('@/views/exam/exam'),
        name: 'ListExam',
        meta: { title: '竞答管理', noCache: true, icon: 'log' }
      },

      {
        path: 'exam/add',
        component: () => import('@/views/exam/exam/form'),
        name: 'AddExam',
        meta: { title: '添加竞答', noCache: true, activeMenu: '/exam/exam' },
        hidden: true
      },

      {
        path: 'exam/update/:id',
        component: () => import('@/views/exam/exam/form'),
        name: 'UpdateExam',
        meta: { title: '修改竞答', noCache: true, activeMenu: '/exam/exam' },
        hidden: true
      },
      {
        path: 'exam/users/:examId',
        component: () => import('@/views/user/exam'),
        name: 'ListExamUser',
        meta: { title: '竞答人员', noCache: true, activeMenu: '/exam/exam' },
        hidden: true
      },
      {
        path: 'exam/paper/:examId',
        component: () => import('@/views/paper/paper'),
        name: 'ListPaper',
        meta: { title: '竞答记录', noCache: true, activeMenu: '/exam/exam' },
        hidden: true
      }
    ]
  },




 ]



// 防止连续点击多次路由报错
let routerPush = Router.prototype.push;
Router.prototype.push = function push(location) {
  return routerPush.call(this, location).catch(err => err)
}

export default new Router({
  mode: 'history', // 去掉url中的#
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRoutes
})
