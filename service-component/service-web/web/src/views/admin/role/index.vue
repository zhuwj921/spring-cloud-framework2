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
		getRoleData,
		roleSave,
		roleUpdate,
		roleDelete
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
							prop: 'roleName',
							rules: [{
								required: true,
								message: "请输入用户名",
								trigger: "blur"
							}]
						},
						{
							label: '角色编码',
							prop: 'roleCode'
						},
						{
							label: '角色描述',
							prop: 'roleDesc'
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
				getRoleData(this.page)
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
				roleSave(form)
					.then(result => {
						this.$message.success(result);
						this.handleList()
						done();
					});
			},
			handleUpdate(form, index, done, loading) {
				loading();
				roleUpdate(form)
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
						roleDelete({
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
