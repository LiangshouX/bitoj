<template>
  <div id="app">
    <template v-if="route.path.startsWith('/user')">
      <router-view />
    </template>
    <template v-else>
      <BasicLayout />
    </template>
  </div>
</template>

<style>
#app {
}
</style>
<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout.vue";
import { onMounted } from "vue";
import { useRoute } from "vue-router";

const route = useRoute();
/**
 * todo 全局初始化函数，有全局单次调用的代码，都可以写到这里
 */
const doInit = () => {
  console.log(
    "/*\n" +
      " *                        _oo0oo_\n" +
      " *                       o8888888o\n" +
      ' *                       88" . "88\n' +
      " *                       (| -_- |)\n" +
      " *                       0\\  =  /0\n" +
      " *                     ___/`---'\\___\n" +
      " *                   .' \\\\|     |// '.\n" +
      " *                  / \\\\|||  :  |||// \\\n" +
      " *                 / _||||| -:- |||||- \\\n" +
      " *                |   | \\\\\\  - /// |   |\n" +
      " *                | \\_|  ''\\---/''  |_/ |\n" +
      " *                \\  .-\\__  '-'  ___/-. /\n" +
      " *              ___'. .'  /--.--\\  `. .'___\n" +
      ' *           ."" \'<  `.___\\_<|>_/___.\' >\' "".\n' +
      " *          | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |\n" +
      " *          \\  \\ `_.   \\_ __\\ /__ _/   .-` /  /\n" +
      " *      =====`-.____`.___ \\_____/___.-`___.-'=====\n" +
      " *                        `=---='\n" +
      " * \n" +
      " * \n" +
      " *      ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" +
      " * \n" +
      " *            佛祖保佑       永不宕机     永无BUG\n" +
      " */"
  );
};

onMounted(() => {
  doInit();
});
</script>
