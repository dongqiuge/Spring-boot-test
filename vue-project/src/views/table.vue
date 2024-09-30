<script setup >
import { ElMessage, ElMessageBox } from 'element-plus';
import { Delete, Edit, Search, CirclePlusFilled, View } from '@element-plus/icons-vue';
</script>

<template>
	<div>
		<div class="container">
			<div class="search-box">
				<el-input v-model="query.name" placeholder="名前/ID" class="search-input mr10" clearable></el-input>
				<el-button type="primary" :icon="Search" @click="handleSearch">検索</el-button>
				<el-button type="warning" :icon="CirclePlusFilled" @click="visible = true">新規</el-button>
			</div>
			<el-table :data="items" border class="table" ref="multipleTable" header-cell-class-name="table-header">
				<el-table-column prop="id" label="ID" width="55" align="center"></el-table-column>
				<el-table-column prop="name" label="名前" align="center"></el-table-column>
				<el-table-column label="性別" align="center">
					<template #default="scope">{{ scope.row.sex }}</template>
				</el-table-column>
				<el-table-column prop="mobile" label="電話番号" align="center"></el-table-column>
				<el-table-column label="状態" align="center">
					<template #default="scope">
						<el-tag :type="scope.row.status ? 'success' : 'danger'">
							{{ scope.row.state ? '正常' : '異常' }}
						</el-tag>
					</template>
				</el-table-column>

				<el-table-column prop="createdAt" label="作成時間" align="center"></el-table-column>
				<el-table-column label="操作" width="280" align="center">
					<template #default="scope">
						<el-button type="warning" size="small" :icon="View" @click="handleView(scope.row)">
							参照
						</el-button>
						<el-button type="primary" size="small" :icon="Edit" @click="handleEdit(scope.$index, scope.row)"
							v-permiss="15">
							修正
						</el-button>
						<el-button type="danger" size="small" :icon="Delete" @click="handleDelete(scope.row.id)" v-permiss="16">
							削除
						</el-button>
					</template>
				</el-table-column>
			</el-table>
			<div class="pagination">
				<el-pagination background layout="total, prev, pager, next" :current-page="query.pageIndex"
					:page-size="query.pageSize" :total="query.pageTotal" @current-change="handlePageChange"></el-pagination>
			</div>
		</div>
		<el-dialog :title="idEdit ? '情報の修正' : '新規顧客の新規'" v-model="visible" width="500px" destroy-on-close
			:close-on-click-modal="false" @close="closeDialog">
			<TableEdit :form_data="rowData" :edit="idEdit" :update="updateData" />
		</el-dialog>
		<el-dialog title="顧客情報の詳細" v-model="visible1" width="700px" destroy-on-close>
			<TableDetail :data="rowData" />
		</el-dialog>
	</div>
</template>

<script>

import TableEdit from "@/components/table-edit.vue"
import TableDetail from "@/components/table-detail.vue"

export default {

	data: function () {
		return {
			"rowData": {},
			"visible1":false,
			"visible":false,
			"idEdit": false,

			"items": [],
			"query":{
				"name":"",
				"creatDate": '',
				"querySize":10,
				"pageIndex":1,
				"pageTotal":1,
			},
			/*
			idx: number = -1;
			idEdit = ref(false);
			rowData = ref({});
			updateData = (row: TableItem) => {
				idEdit.value ? (tableData.value[idx] = row) : tableData.value.unshift(row);
				console.log(tableData.value);
				closeDialog();
			};

			closeDialog : () => {
				visible.value = false;
				idEdit.value = false;
			},
			*/

			//visible1 : ref(false),

		}
	},
	methods: {
		closeDialog: function () { },
		handleView :function(row)
		 {
			this.rowData= row;
			this.visible1= true;
		},
		handleEdit: function (index, row) {
			//idx = index;
			this.rowData= row;
			this.idEdit= true;
			this.visible= true;
		},
		// 削除
		handleDelete :function(item_id )  {
			// 削除の二次確認
			ElMessageBox.confirm('削除してもよろしいですか？', 'Tip', {
				type: 'warning',
				confirmButtonText: '確認',
			cancelButtonText: 'キャンセル',
			})
				.then(() => {


					var url="/api/custom/delete"
					var params={"id":item_id}


					var self=this
					this.$api.post(url,params).then(function(response){

						self.getData()

					})




					ElMessage.success('削除成功');
					tableData.value.splice(index, 1);
				})
				.catch(() => { });
		},

		// 検索
		handleSearch :function ()  {
			this.query.pageIndex = 1;
			this.getData();
		},
		// ページネーションナビゲーション
		handlePageChange :function(val )  {
			this.query.pageIndex = val;
			this.getData();
		},

		getData:function(){

			var url="/api/custom/list"
			var params={
				"pageIndex":this.query.pageIndex,
				"name":this.query.name
			}

			var self=this
			this.$api.get(url,params).then(function(response){

					console.log(response)


					self.items= response.data.list
					self.query.pageIndex= response.data.pageNum
					self.query.pageSize= response.data.pageSize
					self.query.pageTotal= response.data.total

					console.log(self.query)
			})

		//tableData.value = res.list;
		//pageTotal.value = res.pageTotal || 50;
		},
	},
	components: {
			//"TableEdit":TableEdit,
			"TableDetail":TableDetail ,
	},
	mounted: function () {
		this.getData()

	},


}
</script>

<style scoped>
.search-box {
	margin-bottom: 20px;
}

.search-input {
	width: 200px;
}

.mr10 {
	margin-right: 10px;
}

.table-td-thumb {
	display: block;
	margin: auto;
	width: 40px;
	height: 40px;
}
</style>
