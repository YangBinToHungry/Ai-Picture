import {createRouter, createWebHistory} from 'vue-router'
import UserManagePage from '../pages/admin/UserManagePage.vue'
import PictureManagePage from '../pages/admin/PictureManagePage.vue'
import SpaceManagePage from '../pages/admin/SpaceManagePage.vue'
import UserLoginPage from '../pages/user/UserLoginPage.vue'
import UserRegisterPage from '../pages/user/UserRegisterPage.vue'
import HomePage from '@/pages/HomePage.vue'
import AddPicturePage from '@/pages/picture/AddPicturePage.vue'
import AddPictureBatchPage from '@/pages/picture/AddPictureBatchPage.vue'
import PictureDetailPage from '@/pages/picture/PictureDetailPage.vue'
import AddSpacePage from '@/pages/space/AddSpacePage.vue'
import MySpacePage from '@/pages/space/MySpacePage.vue'
import SpaceDetailPage from '@/pages/space/SpaceDetailPage.vue'
import ACCESS_ENUM from "@/access/accessEnum";
import {HomeOutlined} from '@ant-design/icons-vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: '主页',
      component: HomePage,
      meta: {
        icon: HomeOutlined,
        order: 1
      },
    },
    {
      path: '/user/login',
      name: '用户登录',
      component: UserLoginPage,
      meta: {
        hideInMenu: true,
      }
    },
    {
      path: '/user/register',
      name: '用户注册',
      component: UserRegisterPage,
      meta: {
        hideInMenu: true,
      }
    },
    {
      path: '/add_picture',
      name: '创建图片',
      component: AddPicturePage,
      meta: {
        access: ACCESS_ENUM.ADMIN,
        order: 2
      },
    },
    {
      path: '/admin/userManage',
      name: '用户管理',
      component: UserManagePage,
      meta: {
        access: ACCESS_ENUM.ADMIN,
        order: 3
      },
    },
    {
      path: '/admin/pictureManage',
      name: '图片管理',
      component: PictureManagePage,
      meta: {
        access: ACCESS_ENUM.ADMIN,
        order: 4
      },
    },
    {
      path: '/picture/:id',
      name: '图片详情',
      component: PictureDetailPage,
      props: true,
      meta: {
        hideInMenu: true,
      }
    },
    {
      path: '/add_picture/batch',
      name: '批量创建图片',
      component: AddPictureBatchPage,
      meta: {
        hideInMenu: true,
      }
    },
    {
      path: '/admin/spaceManage',
      name: '空间管理',
      component: SpaceManagePage,
      meta: {
        access: ACCESS_ENUM.ADMIN,
        order: 5
      },
    },
    {
      path: '/add_space',
      name: '创建空间',
      component: AddSpacePage,
      meta: {
        hideInMenu: true,
      }
    },
    {
      path: '/my_space',
      name: '我的空间',
      component: MySpacePage,
      meta: {
        hideInMenu: true,
      }
    },
    {
      path: '/space/:id',
      name: '空间详情',
      component: SpaceDetailPage,
      props: true,
      meta: {
        hideInMenu: true,
      }
    },
  ],
})

export default router
