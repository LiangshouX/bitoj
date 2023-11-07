<template>
  <div id="globalHeader" class="globalHeader">
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
      <a-menu-item
        v-for="item in routes"
        :key="item.path"
        @menu-item-click="doMenuClick"
      >
        {{ item.name }}
      </a-menu-item>
      <!--      <a-menu-item key="1">Home</a-menu-item>-->
      <!--      <a-menu-item key="2">Solution</a-menu-item>-->
      <!--      <a-menu-item key="3">Cloud Service</a-menu-item>-->
      <!--      <a-menu-item key="4">Cooperation</a-menu-item>-->
    </a-menu>
  </div>
</template>
<script setup lang="ts">
import { routes } from "@/router/routes";
import { useRouter } from "vue-router";
import { ref } from "vue";

const router = useRouter();

// 默认主页
const selectedKeys = ref(["/"]);

// 路由跳转后，更新选中的菜单项
router.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
  console.log(to.path);
});
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
