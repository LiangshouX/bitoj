<template>
  <div id="basicLayout">
    <a-layout style="height: 400px">
      <a-layout-header class="header">
        <GlobalHeader />
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">
        <GlobalFooter />
      </a-layout-footer>
    </a-layout>
  </div>
</template>

<style scoped>
#basicLayout {
}

#basicLayout .header {
  //background: aqua; margin-bottom: 16px; padding: 16px; //position: absolute; //top: 0; //left: 0; //right: 0; //text-align: center;
}

#basicLayout .content {
  background: linear-gradient(to right, #aaa, #fff);
  margin-bottom: 16px;
}

#basicLayout .footer {
  background: aquamarine;
  padding: 12px;
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>

<script setup>
import GlobalHeader from "@/components/GlobalHeader.vue";
import GlobalFooter from "@/components/GlobalFooter.vue";
</script>
