<template>
  <div class="common-layout">
    <el-container>
      <el-header>Header</el-header>
      <el-main><router-view/></el-main>
      <el-footer>Footer</el-footer>
    </el-container>
  </div>
</template>
<script setup>
import router from '@/router';
import {ref} from "vue";

const wd = ref('');
const search = ()=>{
  router.push('/list?wd='+wd.value);
}

const user = ref(localStorage.user?JSON.parse(localStorage.user):null);
const logout = ()=>{
  if (confirm("您确认退出登录吗?")){
    localStorage.clear();
    user.value=null;
    router.push('/login');
  }
}
</script>

<style>
  footer h3{
    color: white;
  }
  #footer_center p{
    margin: 5px 0;
  }
</style>
