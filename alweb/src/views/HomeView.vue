<template>
  <div class="common-layout">
    <el-container>
      <el-header>
        <div style="width: 1200px;margin: 0 auto">
          <el-row :gutter="20">
            <el-col :span="6">
              <router-link to="/">
                <img src="/imgs/icon.png" style="width: 200px">
              </router-link>
            </el-col>
            <el-col :span="10">
              <el-menu router mode="horizontal" active-text-color="orange">
                <el-menu-item index="/list?type=1">食谱</el-menu-item>
                <el-menu-item index="/list?type=2">视频</el-menu-item>
                <el-menu-item index="/list?type=3">资讯</el-menu-item>
              </el-menu>
            </el-col>
            <el-col :span="6">
              <el-input v-model="wd" style="margin-top: 25px" placeholder="请输入搜索的关键字">
                <template #append>
<!--                  <el-button @click="search()" :icon="Search" />-->
                </template>
              </el-input>
            </el-col>
            <el-col :span="2">
              <el-popover v-if="user==null" placement="bottom" title="欢迎访问烘焙坊" :width="200">
                <template #reference>
                  <el-icon style="margin-top: 28px" size="25"><User/></el-icon>
                </template>
                <div style="text-align: center">
                  <el-button type="info" @click="router.push('/reg')">注册</el-button>
                  <el-button type="warning" @click="router.push('/login')">登录</el-button>
                </div>
              </el-popover>
              <el-popover v-else placement="bottom" title="欢迎访问烘焙坊" :width="200">
                <template #reference>
                  <el-icon style="margin-top: 28px" size="25"><User/></el-icon>
                </template>
                <div style="text-align: center">
<!--                 设置头像展示 -->
                  <el-avatar :src="BASE_URL+user.imgUrl"></el-avatar><br>

                  <el-button type="info" size="small" @click="router.push('/personal')">个人中心</el-button>
                  <el-button type="warning" size="small" @click="logout()">退出登录</el-button><br>
                  <el-button v-if="user.isAdmin==1" type="danger" size="small"
                             @click="router.push('/admin')">后台管理</el-button>
                </div>
              </el-popover>
            </el-col>
          </el-row>
        </div>
      </el-header>
      <el-main><router-view/></el-main>
      <el-footer style="background-color: rgb(50,50,50);color: #666;
      height: 300px;padding-top: 50px;text-align: center">
        <div style="width: 1200px;margin: 0 auto;">
          <el-row :gutter="20">
            <el-col :span="8">
              <img src="/imgs/icon.png" alt="">
              <p>教程灵感就看烘焙坊</p>
              <p>烘焙行业网络社区平台</p>
              <p>全国百城上千个职位等你来</p>
            </el-col>
            <el-col id="footer_center" :span="8">
              <el-row>
                <el-col :span="8">
                  <h3>关于我们</h3>
                  <p>烘焙学院</p>
                  <p>烘焙食谱</p>
                  <p>分类信息</p>
                  <p>求职招聘</p>
                  <p>社区交流</p>
                </el-col>
                <el-col :span="8">
                  <h3>服务与支持</h3>
                  <p>联系我们</p>
                  <p>广告投放</p>
                  <p>用户协议</p>
                  <p>友情链接</p>
                  <p>在线反馈</p>
                </el-col>
                <el-col :span="8">
                  <h3>底部导航</h3>
                  <p>Archiver</p>
                  <p>手机版</p>
                  <p>小黑屋</p>
                </el-col>
              </el-row>
            </el-col>
            <el-col :span="8">
              <p style="font-size: 60px;color: #666;margin: 10px 0">
                <span style="color: orange">烘焙</span>坊
              </p>
              <p>烘焙行业社区平台</p>
            </el-col>
          </el-row>
        </div>
      </el-footer>
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
