<template>
  <div id="globalHeader">
    <a-row :wrap="false">
      <a-col flex="200px">
        <RouterLink to="/">
          <div class="title-bar">
            <img class="logo" src="../assets/logo1.png" alt="logo"/>
            <div class="title">智能AI云图库</div>
          </div>
        </RouterLink>
      </a-col>
      <a-col flex="auto">
        <a-menu v-model:selectedKeys="current" mode="horizontal" :items="menus" @click="doMenuClick"/>
      </a-col>
      <a-col flex="120px">
        <div class="user-login-status">
          <div v-if="loginUserStore.loginUser.id">
            <a-dropdown>
              <a-space>
                <a-avatar :src="loginUserStore.loginUser.userAvatar"/>
                {{ loginUserStore.loginUser.userName ?? '无名' }}
              </a-space>
              <template #overlay>
                <a-menu>
                  <a-menu-item>
                    <router-link to="/my_space">
                      <UserOutlined />
                      我的空间
                    </router-link>
                  </a-menu-item>
                  <a-menu-item @click="doLogout">
                    <LogoutOutlined/>
                    退出登录
                  </a-menu-item>
                </a-menu>
              </template>
            </a-dropdown>
          </div>
          <div v-else>
            <a-button type="primary" href="/user/login">登录</a-button>
          </div>
        </div>
      </a-col>
    </a-row>
  </div>
</template>
<script lang="ts" setup>
import {computed, h, ref} from 'vue'
import {HomeOutlined} from '@ant-design/icons-vue'
import {MenuProps, message} from 'ant-design-vue'
import {useRouter} from "vue-router";
import {useLoginUserStore} from '@/stores/useLoginUserStore.ts'
import {LogoutOutlined,UserOutlined} from '@ant-design/icons-vue';
import checkAccess from "@/access/checkAccess";
import {userLogoutUsingGet} from "@/api/userController";

const doLogout = async () => {
  const res = await userLogoutUsingGet()
  console.log(res)
  if (res.data.code === 0) {
    loginUserStore.setLoginUser({
      userName: '未登录',
    })
    message.success('退出登录成功')
    await router.push('/user/login')
  } else {
    message.error('退出登录失败，' + res.data.message)
  }
}

const loginUserStore = useLoginUserStore()

const router = useRouter();
// 路由跳转事件
const doMenuClick = ({key}: { key: string }) => {
  router.push({
    path: key,
  });
};
// 当前选中菜单
const current = ref<string[]>([]);
// 监听路由变化，更新当前选中菜单
router.afterEach((to, from, next) => {
  current.value = [to.path];
});

// 过滤路由项
const items = computed(() => {
  return router.getRoutes().filter((item) => {
      if (item.meta?.hideInMenu) {
        return false
      }
      // 根据权限过滤菜单，有权限则返回 true，则保留该菜单
      return checkAccess(loginUserStore.loginUser, item.meta?.access as string)
    }).sort((a, b) => a.meta.order - b.meta.order).map(menuToRouteItem)
})
// 把路由项转换为菜单项
const menuToRouteItem = (item: any) => {
  const isHome = item.path === '/'
  return {
    key: item.path,
    label: item.name,
    title: item.name,
    // icon: isHome ? h(item.meta?.icon ?? HomeOutlined) : undefined, // 仅在主页路径时显示 icon
  }
}
const menus = ref<MenuProps['items']>(items)

//测试后端接口健康状况
// import {healthUsingGet} from '@/api/mainController'
// import {userLogoutUsingGet} from "@/api/userController";
//
// healthUsingGet().then((res) => {
//   console.log(res)
// })

</script>

<style scoped>
#globalHeader .title-bar {
  display: flex;
  align-items: center;
}

.title {
  color: black;
  font-size: 18px;
  margin-left: 16px;
}

.logo {
  height: 48px;
}
</style>






