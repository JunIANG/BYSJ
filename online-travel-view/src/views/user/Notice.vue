<template>
    <el-row style="width: 700px;margin: 0 auto;background-color: #FFFFFF;padding: 5px 0;border-radius: 5px;">
        <el-row style="padding: 10px;margin: 0 5px;">
            <el-row>
                <el-date-picker size="small" style="width: 220px;margin-left: 5px;" v-model="searchTime"
                    type="daterange" range-separator="至" start-placeholder="创建开始" end-placeholder="创建结束">
                </el-date-picker>
                <el-input size="small" style="width: 188px;margin-left: 5px;margin-right: 6px;"
                    v-model="vendorQueryDto.title" placeholder="标题" clearable @clear="handleFilterClear">
                    <el-button slot="append" @click="handleFilter" icon="el-icon-search"></el-button>
                </el-input>
            </el-row>
        </el-row>
        <el-row style="margin: 0 15px;border-top: 1px solid rgb(245,245,245);">
            <el-row style="margin-block: 20px;">
                <div class="notice-item" @click="noticeSelected(notice)" v-for="(notice, index) in tableData"
                    :key="index">
                    <div class="title">{{ notice.title }}</div>
                    <div class="time">创建时间：{{ notice.createTime }}</div>
                </div>
            </el-row>
            <el-pagination style="margin:10px 0;" @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="currentPage" :page-sizes="[10, 20]" :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="totalItems"></el-pagination>
        </el-row>
        <!-- 操作面板 -->
        <el-dialog :show-close="false" :visible.sync="dialogOperation" width="32%">
            <div slot="title">
                <p class="dialog-title">{{ !isOperation ? '新增公告' : '修改公告信息' }}</p>
            </div>
            <div style="padding:0 20px;">
                <el-row style="margin-bottom: 20px;">
                    <span class="dialog-hover">标题</span>
                    <input class="dialog-input" v-model="data.title" placeholder="请输入" />
                    <div>
                        <Editor height="300px" :receiveContent="data.content" @on-receive="onReceiveContent" />
                    </div>
                </el-row>
            </div>
            <span slot="footer" class="dialog-footer">
                <el-button size="small" v-if="!isOperation"
                    style="background-color: rgb(96, 98, 102);color: rgb(247,248,249);border: none;" class="customer"
                    type="info" @click="addOperation()">新增</el-button>
                <el-button size="small" v-else
                    style="background-color: rgb(96, 98, 102);color: rgb(247,248,249);border: none;" class="customer"
                    type="info" @click="updateOperation()">修改</el-button>
                <el-button class="customer" size="small" style="background-color: rgb(211, 241, 241);border: none;"
                    @click="cannel()">取消</el-button>
            </span>
        </el-dialog>
    </el-row>
</template>

<script>
import Editor from "@/components/Editor"
export default {
    components: { Editor },
    data() {
        return {
            data: {},
            filterText: '',
            currentPage: 1,
            pageSize: 10,
            totalItems: 0,
            dialogOperation: false, // 开关
            isOperation: false, // 开关-标识新增或修改
            tableData: [],
            selectedRows: [],
            vendorQueryDto: {}, // 搜索条件
            users: [],
            searchTime: [],
            isAuditList: [{ value: null, label: '全部' }, { value: 0, label: '未审核' }, { value: 1, label: '已审核' }],
            statusList: [{ value: null, label: '全部' }, { value: 0, label: '不可用' }, { value: 1, label: '可用' }],
        };
    },
    created() {
        this.fetchFreshData();
        this.fetchUsers();
    },
    methods: {
        noticeSelected(notice) {
            // 存储公告
            sessionStorage.setItem('noticeInfo', JSON.stringify(notice));
            this.$router.push('/noticeDetail');
        },
        onReceiveContent(content) {
            this.data.content = content;
        },
        // 获取法人用户信息
        fetchUsers() {
            this.$axios.post('/user/query', {}).then(res => {
                if (res.data.code === 200) {
                    this.users = res.data.data;
                }
            }).catch(error => {
                console.error("查询法人信息异常：", error);
            });
        },
        // 置位
        cannel() {
            this.data = {};
            this.dialogOperation = false;
            this.isOperation = false;
        },
        // 批量删除数据
        async batchDelete() {
            if (!this.selectedRows.length) {
                this.$message(`未选中任何数据`);
                return;
            }
            const confirmed = await this.$swalConfirm({
                title: '删除公告数据',
                text: `删除后不可恢复，是否继续？`,
                icon: 'warning',
            });
            if (confirmed) {
                try {
                    let ids = this.selectedRows.map(entity => entity.id);
                    const response = await this.$axios.post(`/notice/batchDelete`, ids);
                    if (response.data.code === 200) {
                        this.$swal.fire({
                            title: '删除提示',
                            text: response.data.msg,
                            icon: 'success',
                            showConfirmButton: false,
                            timer: 2000,
                        });
                        this.fetchFreshData();
                        return;
                    }
                } catch (e) {
                    this.$swal.fire({
                        title: '错误提示',
                        text: e,
                        icon: 'error',
                        showConfirmButton: false,
                        timer: 2000,
                    });
                    console.error(`公告信息删除异常：`, e);
                }
            }
        },
        // 修改信息
        async updateOperation() {
            try {
                const response = await this.$axios.put('/notice/update', this.data);
                this.clearFormData();
                this.$swal.fire({
                    title: '公告信息修改',
                    text: response.data.msg,
                    icon: response.data.code === 200 ? 'success' : 'error',
                    showConfirmButton: false,
                    timer: 1000,
                });
                if (response.data.code === 200) {
                    this.cannel();
                    this.fetchFreshData();
                }
            } catch (error) {
                console.error('提交表单时出错:', error);
                this.$message.error('提交失败，请稍后再试！');
            }
        },
        // 信息新增
        async addOperation() {
            try {
                const response = await this.$axios.post('/notice/save', this.data);
                this.$message[response.data.code === 200 ? 'success' : 'error'](response.data.msg);
                if (response.data.code === 200) {
                    this.cannel();
                    this.fetchFreshData();
                }
            } catch (error) {
                console.error('提交表单时出错:', error);
                this.$message.error('提交失败，请稍后再试！');
            }
        },
        clearFormData() {
            this.data = {};
        },
        async fetchFreshData() {
            try {
                let startTime = null;
                let endTime = null;
                if (this.searchTime != null && this.searchTime.length === 2) {
                    const [startDate, endDate] = await Promise.all(this.searchTime.map(date => date.toISOString()));
                    startTime = `${startDate.split('T')[0]}T00:00:00`;
                    endTime = `${endDate.split('T')[0]}T23:59:59`;
                }
                // 请求参数
                const params = {
                    current: this.currentPage,
                    size: this.pageSize,
                    key: this.filterText,
                    startTime: startTime,
                    endTime: endTime,
                    ...this.vendorQueryDto
                };
                const response = await this.$axios.post('/notice/query', params);
                const { data } = response;
                this.tableData = data.data;
                this.totalItems = data.total;
            } catch (error) {
                console.error('查询公告信息异常:', error);
            }
        },
        add() {
            this.dialogOperation = true;
        },
        handleFilter() {
            this.currentPage = 1;
            this.fetchFreshData();
        },
        handleFilterClear() {
            this.filterText = '';
            this.handleFilter();
        },
        handleSizeChange(val) {
            this.pageSize = val;
            this.currentPage = 1;
            this.fetchFreshData();
        },
        handleCurrentChange(val) {
            this.currentPage = val;
            this.fetchFreshData();
        },
        handleEdit(row) {
            this.dialogOperation = true;
            this.isOperation = true;
            this.data.content = row.content;
            this.data = { ...row }
        },
        handleDelete(row) {
            this.selectedRows.push(row);
            this.batchDelete();
        }
    },
};
</script>
<style scoped lang="scss">
.notice-item:hover {
    background-color: rgb(246, 246, 246);
}

.notice-item {
    padding: 16px 6px;
    border-bottom: 1px solid rgb(246, 246, 246);
    border-radius: 4px;
    cursor: pointer;

    .title {
        font-size: 26px;
        font-weight: 800;
        margin-bottom: 10px;
    }

    .time {
        font-size: 14px;
    }
}
</style>