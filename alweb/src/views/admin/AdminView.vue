<template>
  <div class="common-layout">
    <el-container>
      <el-header style="background-color: #368;line-height: 60px;">
        <h1 style="font-size: 30px;color: white;margin: 0">后台管理系统
          <span style="float: right;font-size: 20px">欢迎{{user.nickname}}回来!
            <el-button @click="logout()">退出登录</el-button>
          </span>
        </h1>
      </el-header>
      <el-container>
        <el-aside width="200px">
          <el-menu router active-text-color="orange" default-active="/admin/user">
<!--        资源路径中的第一个/代表的是工程根路径, http://localhost:8080/imgs/icon.png
如果不是以/开头 代表的是一个相对路径,相对于当前浏览器地址里中的路径
    如果浏览器的地址是http://localhost:8080/admin/user   则src='imgs/icon.png'
    代表的是http://localhost:8080/admin/imgs/icon.png 这样就找不到图片了 ,推荐使用绝对路径
      -->
            <img src="/imgs/icon.png" style="width: 150px" >
            <el-menu-item index="/admin/user">
             <el-icon><User/></el-icon> 用户管理
            </el-menu-item>
            <el-menu-item index="/admin/banner">
              <el-icon><Picture/></el-icon> 轮播图管理
            </el-menu-item>
            <el-menu-item index="/admin/content">
              <el-icon><ChatDotSquare /></el-icon> 内容管理
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main>
          <router-view/>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import {ref} from "vue";
import router from "@/router";

const user = ref(localStorage.user?JSON.parse(localStorage.user):null);
const logout = ()=>{
  if (confirm("您确认退出登录吗?")){
    localStorage.clear();
    router.push('/login');
  }
}
</script>
