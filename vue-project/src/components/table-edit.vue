<template>
  <el-form ref="formRef" :model="form" :rules="rules" label-width="100px">
    <!--
    <el-form-item label="ID" prop="id">
      <el-input v-model="form.id" :disabled="disabledStatus()"></el-input>
    </el-form-item>
    -->
    <el-form-item label="名前" prop="name">
      <el-input v-model="form.name" :disabled="disabledStatus()"></el-input>
    </el-form-item>
    <el-form-item label="性別" prop="sex">
      <el-input v-model.number="form.sex"></el-input>
    </el-form-item>
    <el-form-item label="電話番号" prop="mobile">
      <el-input v-model.trim="form.mobile"></el-input>
    </el-form-item>

    <el-form-item label="状態" prop="status">
      <el-switch
          v-model="form.status"
          :active-value="1"
          :inactive-value="0"
          active-text="正常"
          inactive-text="异常"
      ></el-switch>
    </el-form-item>
    <!-- <el-form-item label="アバターをアップロードする" prop="thumb">
      <el-upload
        class="avatar-uploader"
        action="https://run.mocky.io/v3/9d059bf9-4660-45f2-925d-ce80ad6c4d15"
        :show-file-list="false"
        :on-success="handleAvatarSuccess"
        :before-upload="beforeAvatarUpload"
      >
        <img v-if="form.thumb" :src="form.thumb" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>
    </el-form-item> -->
    <el-form-item>
      <el-button type="primary" @click="saveEdit()">保 存</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
import { ElMessage } from 'element-plus';

export default {
   props:[ "form_data","edit","update"],

  watch: {
    form_data(val) {
      //为什么第二次以后赋值就不会变更了?
      this.form = val

      console.log(this.form)
    }
  },

	mounted: function () {

	},

	data: function () {
		return {

        form:this.form_data,

		}
	},
	methods: {
    saveEdit: function () { 
        //props.update(form.value);


      if (this.form.id) {
        this.$api.postJson("/api/custom/update", this.form).then(function (response) {

          console.log(response);

          ElMessage.success('保存成功！');

          window.location.reload()
        });
      }
      else {
        this.$api.postJson("/api/custom/create", this.form).then(function (response) {

          console.log(response);

          ElMessage.success('保存成功！');

          window.location.reload()
        });
      }

    },
    disabledStatus: function () {
      if (this.edit) {
        return true
      } else {
        return false
      }

  },


	},
	components: {
	}


}


/*
const props = defineProps({
  data: {
    type: Object,
    required: true
  },
  edit: {
    type: Boolean,
    required: false
  },
  update: {
    type: Function,
    required: true
  }
});

const defaultData = {
  id: '',
  name: '',
  thumb: '',
  sex: 0,
  status: 0,
  mobile: '',
};

const form = ref({ ...(props.edit ? props.data : defaultData) });

disabledStatus();

console.log(disabledStatus())


const rules: FormRules = {
  name: [{ required: true, message: '名前', trigger: 'blur' }],
  id: [{ required: true, message: 'IDを入力してください', trigger: 'blur' }]
};
const formRef = ref<FormInstance>();
const handleAvatarSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
  form.value.thumb = URL.createObjectURL(uploadFile.raw!);
};

const beforeAvatarUpload: UploadProps['beforeUpload'] = rawFile => {
  if (rawFile.type !== 'image/jpeg') {
    ElMessage.error('Avatar picture must be JPG format!');
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error('Avatar picture size can not exceed 2MB!');
    return false;
  }
  return true;
};
*/
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>