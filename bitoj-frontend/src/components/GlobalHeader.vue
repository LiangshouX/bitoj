<template>
  <a-row
    id="globalHeader"
    class="globalHeader"
    align="center"
    style="margin-bottom: 16px"
    :wrap="false"
  >
    <a-col flex="100px">
      <div>100px</div>
    </a-col>
    <a-col flex="auto">
      <div>
        <a-menu
          mode="horizontal"
          :selected-keys="selectedKeys"
          @menu-item-click="doMenuClick"
        >
          <a-menu-item
            key="0"
            :style="{ padding: 0, marginRight: '38px' }"
            disabled
          >
            <div class="title-bar">
              <img class="logo" src="../assets/logo.png" />
              <div class="title">BIT OJ</div>
            </div>
          </a-menu-item>

          <a-menu-item v-for="item in visibleRoutes" :key="item.path">
            {{ item.name }}
          </a-menu-item>

          <!--      <a-menu-item key="1">Home</a-menu-item>-->
          <!--      <a-menu-item key="2">Solution</a-menu-item>-->
          <!--      <a-menu-item key="3">Cloud Service</a-menu-item>-->
          <!--      <a-menu-item key="4">Cooperation</a-menu-item>-->
        </a-menu>
      </div>
    </a-col>
    <a-col flex="100px">
      <div>{{ store.state.user?.loginUser?.userName ?? "未登录" }}</div>
    </a-col>
  </a-row>
</template>

<script setup lang="ts">
import { routes } from "@/router/routes";
import { useRouter } from "vue-router";
import { computed, ref } from "vue";
import { useStore } from "vuex";
import checkAccess from "@/access/checkAccess";
import ACCESS_ENUM from "@/access/accessEnum";

const router = useRouter();
const store = useStore();

// 展示在菜单的路由数组
const visibleRoutes = computed(() => {
  return routes.filter((item, index) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    // 根据权限过滤菜单
    return checkAccess(
      store.state.user.loginUser,
      item?.meta?.access as string
    );
  });
});

// 默认主页
const selectedKeys = ref(["/"]);

// 路由跳转后，更新选中的菜单项
router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
});

console.log();

setTimeout(() => {
  store.dispatch("user/getLoginUser", {
    userName: "管理员",
    userRole: ACCESS_ENUM.ADMIN,
  });
}, 3000);

const doMenuClick = (key: string) => {
  router.push({
    path: key,
  });
};
</script>

<style scoped>
.globalHeader {
  box-sizing: border-box;
  width: 100%;
  padding: 1px;
  background-color: var(--color-neutral-2);
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
}

.title-bar {
  display: flex;
  align-items: center;
}

.title {
  color: black;
  margin-left: 16px;
}

.logo {
  height: 32px;
}
</style>
