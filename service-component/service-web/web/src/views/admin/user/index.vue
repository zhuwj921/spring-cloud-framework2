<template>
	<div>
		<basic-container>
			<avue-crud :data="tableData" :option="option" :page="page" @size-change="sizeChange" @current-change="currentChange"
			 @row-save="handleSave" @row-update="handleUpdate" @row-del="handleDelete"></avue-crud>
		</basic-container>
	</div>
</template>
<script>
	import {
		getUserData,
		userSave,
		userUpdate,
		userDelete
	} from '@/api/auth'
	export default {
		data() {
			return {
				page: {
					pageSize: 10,
					currentPage: 1,
					total: 0,
				},
				tableData: [],
				option: {
					border: true,
					selection: false,
					menuBtn: true,
					menuAlign: 'center',
					refreshBtn: false,
					column: [{
							label: '用户名',
							prop: 'username',
							rules: [{
								required: true,
								message: "请输入用户名",
								trigger: "blur"
							}]
						},
						{
							label: '昵称',
							prop: 'nickname'
						},
						{
							label: '密码',
							prop: 'password',
							hide: true,
							editDisplay: false,
						},
						{
							label: '创建时间',
							prop: 'createTime',
							display: false,
						}
					]
				}
			}
		},
		created() {
			this.handleList()
		},
		methods: {
			handleList() {
				this.tableLoading = true;
				getUserData(this.page)
					.then(result => {
						setTimeout(() => {
							this.tableData = result.list;
							this.page.total = result.total;
							this.tableLoading = false;
						}, 1000);
					});
			},
			sizeChange(val) {
				this.page.currentPage = 1
				this.page.pageSize = val
				this.handleList()
			},
			currentChange(val) {
				this.page.currentPage = val
				this.handleList()
			},
			handleSave(form, done, loading) {
				loading();
				userSave(form)
					.then(result => {
						this.$message.success(result);
						this.handleList()
						done();
					});
			},
			handleUpdate(form, index, done, loading) {
				loading();
				userUpdate(form)
					.then(result => {
						this.$message.success(result);
						this.handleList()
						done();
					});
			},
			handleDelete(form, index) {
				this.$confirm(`是否确认删除`, "提示", {
						confirmButtonText: "确定",
						cancelButtonText: "取消",
						type: "warning"
					})
					.then(() => {
						let id = form.id;
						userDelete({
								id: id
							})
							.then(result => {
								this.$message.success(result);
								this.handleList()
							});
					})
					.catch(() => {});

			}

		}
	}
</script>
