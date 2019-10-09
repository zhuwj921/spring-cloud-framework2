<template>
  <div>
    <basic-container>
      <el-container>
        <el-header class="menu-header">
          <el-button-group>
            <el-button type="primary"
                       icon="el-icon-plus"
                       size="small"
                       @click.native="handleAdd"
                       >新增</el-button>
            <el-button type="primary"
                       icon="el-icon-edit"
                       size="small"
                       @click.native="handleEdit"
                      >编辑</el-button>
            <el-button type="primary"
                       icon="el-icon-delete"
                       size="small"
                       @click.native="handleDel"
                      >删除</el-button>
          </el-button-group>
        </el-header>
      </el-container>
      <el-container>
        <el-aside width="300px">
          <el-tree :data="menuAll"
                   node-key="id"
                   highlight-current
                   default-expand-all
                   :expand-on-click-node="false"
                   @node-click="handleNodeClick"></el-tree>
        </el-aside>
        <el-main>
          <el-form ref="form"
                   :model="form"
                   label-width="80px">
            <el-form-item label="父节点ID">
              <el-input v-model="parentForm.id"
                        :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="父节点">
              <el-input v-model="parentForm.label"
                        :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="菜单名称">
              <el-input v-model="form.label"
                        :disabled="formGrade"></el-input>
            </el-form-item>
            <el-form-item label="菜单图标">
              <el-input v-model="form.icon"
                        :disabled="formGrade"></el-input>
            </el-form-item>
            <el-form-item label="菜单路径">
              <el-input v-model="form.path"
                        :disabled="formGrade"></el-input>
            </el-form-item>
			<el-form-item label="文件路径">
			  <el-input v-model="form.component"
			            :disabled="formGrade"></el-input>
			</el-form-item>
			<el-form-item label="菜单排序">
			  <el-input v-model="form.sort"
			            :disabled="formGrade"></el-input>
			</el-form-item>
			<el-form-item label="菜单类型">
				<el-select v-model="form.type" :disabled="formGrade" >
					  <el-option label="菜单" value="0"></el-option>
					   <el-option label="按钮" value="1"></el-option>
				  </el-select>
			</el-form-item>
            <el-form-item>
              <el-button type="primary"
                         @click="handleSubmit"
                         :disabled="formGrade"
                         v-if="formStatus=='add'">新增</el-button>
              <el-button type="primary"
                         @click="handleSubmit"
                         :disabled="formGrade"
                         v-if="formStatus=='edit'">修改</el-button>
            </el-form-item>
          </el-form>
        </el-main>
      </el-container>
    </basic-container>
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import { validatenull } from "@/util/validate";
import { findParent } from "@/util/util";
export default {
  name: "menu",
  data () {
    return {
      form: {},
      obj: {},
      parentForm: {},
      formGrade: true,
      formStatus: ""
    };
  },
  created () {
    this.$store.dispatch("GetMenuAll").then(() => { });
  },
  mounted () { },
  computed: {
    ...mapGetters(["permission", "menuAll"])
  },
  props: [],
  methods: {
    handleNodeClick (data) {
      this.parentForm = Object.assign({}, findParent(this.menuAll, data.id));
      this.formGrade = false;
      this.formStatus = "";
      this.obj = data;
      this.form = data;
    },
    handleAdd () {
      this.formGrade = false;
      this.formStatus = "add";
      this.form = {};
    },
    handleEdit () {
      if (validatenull(this.obj)) {
        this.$message({
          showClose: true,
          message: "请选择菜单",
          type: "warning"
        });
        return false;
      }
      this.form = Object.assign({}, this.obj);
      this.formStatus = "edit";
      this.formGrade = false;
    },
    handleDel () {
      this.$confirm(`是否确认删除序号为${this.form.label}`, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.$message({
            showClose: true,
            message: "删除成功",
            type: "success"
          });
        })
        .catch(() => { });
    },
    handleSubmit () { }
  }
};
</script>

<style lang="scss" scoped>
.menu-container {
  padding: 0 20px;
}
.menu-header {
  padding: 8px 0;
}
</style>
