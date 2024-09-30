<script setup>

import { Lock, User } from "@element-plus/icons-vue";
</script>



<template>
  <div class="login-wrap">
    <div class="ms-login">
      <div class="ms-title">CRMシステム</div>
      <el-form
        :model="loginForm"
        :rules="rules"
        ref="login"
        label-width="0px"
        class="ms-content"
      >
        <el-form-item prop="username">
          <el-input v-model="loginForm.username" placeholder="ユーザー名">
            <template #prepend>
              <el-button :icon="User"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            type="password"
            placeholder="パスワード"
            v-model="loginForm.password"
            @keyup.enter="handleLogin()"
          >
            <template #prepend>
              <el-button :icon="Lock"></el-button>
            </template>
          </el-input>
        </el-form-item>
        <div class="login-btn">
          <el-button type="primary" @click="handleLogin()"
            >ログイン</el-button
          >
        </div>
        <el-checkbox
          class="login-tips"
          label="パスワードを記憶する"
          size="large"
        />
        <p class="login-tips">
          Tips : ユーザー名とパスワードは適当に入力してください。
        </p>
      </el-form>
    </div>
  </div>
</template>

<script>
import { useUserStore } from '@/stores/module/user'
import {  ElMessageBox } from 'element-plus'

const userStore = useUserStore()



export default {
  mounted:function(){
    /*
    var arr = ['bg_1.jpg','bg_2.jpg','bg_3.jpg'];

    var i = 0;
    setInterval(function(){
        if(i == arr.length - 1){
            i = 0;
        }else{
            i++;
        }
        var img = 'url('+arr[i]+')';
        $(".full-bg").css('background-image',img);


    }, 4000)
    */
  },

  data:function() {
    return {
      loginForm: {
        username: '',
        password: ''
      },
      passwordType: 'password',
    }
  },
  methods: {
    handleLogin() {

      var url="/api/login/login";
      /*
      var params={
          email:"test@test.com",
          password:"111111",
      }
      */

      var params=this.loginForm;

      var self=this
      this.$api.post(url,params).then((response) => {

        if (response.code != "SUCCESS") {
          //alert(response.message);
          ElMessageBox.alert(response.message, 'Warning', {
            // if you want to disable its autofocus
            // autofocus: false,
            confirmButtonText: 'OK',
          })
        } else {
          //console.log(response.data);
          userStore.setUser(response.data.id,response.data.name,response.data.token)





          self.$router.push({path: '/dashboard'})
        }
      })
    }}}
</script>

<!--<script setup lang="ts">
import { ref, reactive } from "vue";
// import { useTagsStore } from '../store/tags';
import { ApiResult, POST } from "../api/index";
import { usePermissStore } from "../store/permiss";
import { useRouter } from "vue-router";
import { ElMessage } from "element-plus";
import type { FormInstance, FormRules } from "element-plus";


interface LoginInfo {
  username: string;
  password: string;
}

const lgStr = localStorage.getItem("login-param");
const defParam = lgStr ? JSON.parse(lgStr) : null; //オブジェクトに変換して比較
const checked = ref(lgStr ? true : false); //レスポンシブデータを作成、チェックボックスが選択されているかどうかを判断

const router = useRouter();
const param = reactive<LoginInfo>({
  //オブジェクトのレスポンシブプロキシを作成  データ連携レンダリングの鍵
  username: defParam ? defParam.username : "",
  password: defParam ? defParam.password : "",
});

const rules: FormRules = {
  username: [
    {
      required: true,
      message: "名前を入力してください",
      trigger: "blur",
    },
  ],
  password: [{ required: true, message: "パスワードを入力してください", trigger: "blur" }],
};
const permiss = usePermissStore();
const login = ref<FormInstance>();
const submitForm = (formEl: FormInstance | undefined) => {
  if (!formEl) return;
  formEl.validate(async (valid: boolean) => {
    if (valid) {
      // try {
      //   // 非同期リクエスト関数 POST を使用してログインリクエストを送信する
      //   const response: ApiResult<any> = await POST("/api/login", param);
      //   if (response.code === 200) {
      //     ElMessage.success("ログイン成功");
      //     localStorage.setItem("ms_username", param.username);
      //     const keys =
      //       permiss.defaultList[param.username === "admin" ? "admin" : "user"];
      //     permiss.handleSet(keys);
      //     localStorage.setItem("ms_keys", JSON.stringify(keys));
      //     router.push("/");

      //     if (checked.value) {
      //       localStorage.setItem("login-param", JSON.stringify(param));
      //     } else {
      //       localStorage.removeItem("login-param");
      //     }
      //   } else {
      //     ElMessage.error(`ログイン失敗: ${response.message}`);
      //   }
      // } catch (error) {
      //   console.error("ログインリクエスト失敗:", error);
      //   ElMessage.error("ログイン失敗、しばらくしてから再試行してください。");
      // }

      ElMessage.success('ログイン成功');
      localStorage.setItem('ms_username', param.username);
      const keys = permiss.defaultList[param.username == 'admin' ? 'admin' : 'user'];
      permiss.handleSet(keys);
      localStorage.setItem('ms_keys', JSON.stringify(keys));
      router.push('/');
      if (checked.value) {
          localStorage.setItem('login-param', JSON.stringify(param));
      } else {
          localStorage.removeItem('login-param');
      }
    } else {
      ElMessage.error("ログイン失敗");
      return false;
    }
  });
};

// const tags = useTagsStore();
// tags.clearTags();
</script>-->

<style scoped>
.login-wrap {
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 100%;
  background-image: url(../assets/img/login-bg.jpg);
  background-size: 100%;
}
.ms-title {
  line-height: 50px;
  text-align: center;
  font-size: 20px;
  color: #333;
  font-weight: bold;
  padding-top: 10px;
}
.ms-login {
  width: 350px;
  border-radius: 5px;
  background: #fff;
}
.ms-content {
  padding: 10px 30px 30px;
}
.login-btn {
  text-align: center;
}
.login-btn button {
  width: 100%;
  height: 36px;
  margin-bottom: 10px;
}
.login-tips {
  font-size: 12px;
  line-height: 30px;
  color: #333;
}
</style>
