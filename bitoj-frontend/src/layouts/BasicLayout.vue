<template>
  <div id="basicLayout">
    <a-layout style="height: 400px;">

      <a-layout-header class="header">
        <GlobalHeader/>
      </a-layout-header>

      <a-layout-content class="content">
        Content

      </a-layout-content>

      <a-layout-footer class="footer">Programmed By: 南诏国小公主</a-layout-footer>
    </a-layout>
  </div>
</template>

<style scoped>
#basicLayout{

}

#basicLayout .header{
  background: aqua;
  margin-bottom: 16px;
  //position: absolute;
  //top: 0;
  //left: 0;
  //right: 0;
  //text-align: center;
}

#basicLayout .content{
  background: linear-gradient(to right, #aaa, #fff);
  margin-bottom: 16px;
}

#basicLayout .footer{
  background: aqua;
  padding: 16px;
  position: absolute;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>

<script setup>
import GlobalHeader from "@/components/GlobalHeader.vue";
</script>