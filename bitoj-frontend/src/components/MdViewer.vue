<template>
  <Viewer :value="value" :plugins="plugins" />
</template>

<script setup lang="ts">
import gfm from "@bytemd/plugin-gfm";
import highlight from "@bytemd/plugin-highlight";
import { Viewer } from "@bytemd/vue-next";
import gemoji from "@bytemd/plugin-gemoji";
import math from "@bytemd/plugin-math";
import { withDefaults } from "vue";

/**
 * 定义组件属性类型
 */
interface Props {
  value: string;
}

const plugins = [
  gfm({}),
  highlight(),
  // 中文显示
  math({}),
  gemoji(),
];

/**
 * 给组件指定初始值
 */
const props = withDefaults(defineProps<Props>(), {
  value: () => "",
});
</script>

<style></style>
