<!--
	ヘッダーコンポーネント
-->
<template>
  <div class="header">
    <!-- 折りたたみボタン -->
    <div class="logo">CRMシステム</div>
    <div class="header-right">
      <div class="header-user-con">
        <!-- メッセージセンター -->
        <div class="btn-bell" @click="router.push('/tabs')">
          <el-tooltip
              effect="dark"
              :content="message ? `未読メッセージが${message}件あります` : `メッセージセンター`"
              placement="bottom"
          >
            <i class="el-icon-lx-notice"></i>
          </el-tooltip>
          <span class="btn-bell-badge" v-if="message"></span>
        </div>
        <!-- ユーザーのアバター -->
        <el-avatar class="user-avator" :size="30" :src="imgurl" />
        <!-- ユーザー名のドロップダウンメニュー -->
        <el-dropdown class="user-name" trigger="click" @command="handleCommand">
					<span class="el-dropdown-link">
						{{ username }}
						<el-icon class="el-icon--right">
							<arrow-down />
						</el-icon>
					</span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="user">ユーザセンター</el-dropdown-item>
              <el-dropdown-item divided command="loginout">ログアウト</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  mounted:function(){

  },

  data:function() {
    return {
      message:"",
      imgurl:"./src/assets/img/img.jpg",
      username:""
    }
  },
  methods: {


  }

}
</script>





<style scoped>
.header {
  position: relative;
  box-sizing: border-box;
  width: 100%;
  height: 70px;
  font-size: 22px;
  color: #fff;
  background-color:#242f42;
}
.collapse-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  float: left;
  padding: 0 21px;
  cursor: pointer;
}
.header .logo {
  float: left;
  width: 250px;
  line-height: 70px;
  color: #ffffff;
}
.header-right {
  float: right;
  padding-right: 50px;
}
.header-user-con {
  display: flex;
  height: 70px;
  align-items: center;
}
.btn-fullscreen {
  transform: rotate(45deg);
  margin-right: 5px;
  font-size: 24px;
}
.btn-bell,
.btn-fullscreen {
  position: relative;
  width: 30px;
  height: 30px;
  text-align: center;
  border-radius: 15px;
  cursor: pointer;
  display: flex;
  align-items: center;
}
.btn-bell-badge {
  position: absolute;
  right: 4px;
  top: 0px;
  width: 8px;
  height: 8px;
  border-radius: 4px;
  background: #f56c6c;
  color: #fff;
}
.btn-bell .el-icon-lx-notice {
  color: #fff;
}
.user-name {
  margin-left: 10px;
}
.user-avator {
  margin-left: 20px;
}
.el-dropdown-link {
  color: #fff;
  cursor: pointer;
  display: flex;
  align-items: center;
}
.el-dropdown-menu__item {
  text-align: center;
}
</style>