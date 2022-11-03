<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="登录用户ID" prop="userId">
          <el-input
            v-model="queryParams.userId"
            placeholder="请输入登录用户ID"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="登录时间">
          <el-date-picker
            v-model="daterangeLoginTime"
            style="width: 240px"
            value-format="yyyy-MM-dd"
            type="daterange"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="登录IP" prop="loginIp">
          <el-input
            v-model="queryParams.loginIp"
            placeholder="请输入登录IP"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="登录状态" prop="status">
          <el-select v-model="queryParams.status" placeholder="请选择登录状态" clearable>
            <el-option
              v-for="dict in dict.type.mov_login_status"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['mov:userlogin:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['mov:userlogin:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['mov:userlogin:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['mov:userlogin:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="userloginList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="ID" width="100" align="center" prop="id" />
        <!-- <el-table-column label="登录用户ID" align="center" prop="userId" /> -->
        <el-table-column label="登录时间" align="center" prop="loginTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.loginTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="登录IP" width="150" align="center" prop="loginIp" />
        <el-table-column label="系统信息" align="center" prop="systemInfo" />
        <el-table-column label="登录状态" width="100" align="center" prop="status">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.mov_login_status" :value="scope.row.status"/>
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center" prop="remark" />
        <el-table-column label="操作" width="150" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['mov:userlogin:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['mov:userlogin:remove']"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
  
      <!-- 添加或修改网站用户登录信息对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="登录用户ID" prop="userId">
            <el-input v-model="form.userId" placeholder="请输入登录用户ID" />
          </el-form-item>
          <el-form-item label="登录时间" prop="loginTime">
            <el-date-picker clearable
              v-model="form.loginTime"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择登录时间">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="登录IP" prop="loginIp">
            <el-input v-model="form.loginIp" placeholder="请输入登录IP" />
          </el-form-item>
          <el-form-item label="系统信息" prop="systemInfo">
            <el-input v-model="form.systemInfo" type="textarea" placeholder="请输入内容" />
          </el-form-item>
          <el-form-item label="登录状态" prop="status">
            <el-select v-model="form.status" placeholder="请选择登录状态">
              <el-option
                v-for="dict in dict.type.mov_login_status"
                :key="dict.value"
                :label="dict.label"
  :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="删除标志" prop="delFlag">
            <el-input v-model="form.delFlag" placeholder="请输入删除标志" />
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { listUserlogin, getUserlogin, delUserlogin, addUserlogin, updateUserlogin } from "@/api/mov/userlogin";
  
  export default {
    name: "Userlogin",
    dicts: ['mov_login_status'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 网站用户登录信息表格数据
        userloginList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 备注时间范围
        daterangeLoginTime: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          userId: null,
          loginTime: null,
          loginIp: null,
          systemInfo: null,
          status: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          userId: [
            { required: true, message: "登录用户ID不能为空", trigger: "blur" }
          ],
          loginTime: [
            { required: true, message: "登录时间不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询网站用户登录信息列表 */
      getList() {
        this.loading = true;
        this.queryParams.params = {};
        if (null != this.daterangeLoginTime && '' != this.daterangeLoginTime) {
          this.queryParams.params["beginLoginTime"] = this.daterangeLoginTime[0];
          this.queryParams.params["endLoginTime"] = this.daterangeLoginTime[1];
        }
        listUserlogin(this.queryParams).then(response => {
          this.userloginList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          userId: null,
          loginTime: null,
          loginIp: null,
          systemInfo: null,
          status: null,
          delFlag: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          remark: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.daterangeLoginTime = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.id)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加网站用户登录信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const id = row.id || this.ids
        getUserlogin(id).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改网站用户登录信息";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.id != null) {
              updateUserlogin(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addUserlogin(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const ids = row.id || this.ids;
        this.$modal.confirm('是否确认删除网站用户登录信息编号为"' + ids + '"的数据项？').then(function() {
          return delUserlogin(ids);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('mov/userlogin/export', {
          ...this.queryParams
        }, `userlogin_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>