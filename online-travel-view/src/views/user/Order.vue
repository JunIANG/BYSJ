<template>
    <el-row style="background-color: #FFFFFF;padding: 5px 30px;border-radius: 5px;">
        <!-- 门票订单详情 -->
        <el-row>
            <h1 style="margin-inline: 20px;">门票订单详情</h1>
            <el-row style="padding: 10px;margin: 0 5px;">
                <el-row>
                    <el-select @change="fetchTicketFreshData" size="small" v-model="scenicTicketOrderQueryDto.payStatus"
                        style="margin-left: 5px;" placeholder="支付状态">
                        <el-option v-for="item in statusList" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                    <el-date-picker size="small" style="width: 220px;margin-left: 5px;" v-model="ticketSearchTime"
                        type="daterange" range-separator="至" start-placeholder="创建开始" end-placeholder="创建结束">
                    </el-date-picker>
                    <el-input size="small" style="width: 0px;">
                        <el-button slot="append" @click="fetchTicketFreshData" icon="el-icon-search"></el-button>
                    </el-input>
                </el-row>
            </el-row>
            <el-row style="margin: 0 15px;border-top: 1px solid rgb(245,245,245);">
                <el-table :data="ticketTableData" style="width: 100%">
                    <el-table-column prop="scenicId" sortable width="120" label="景点ID" v-if="false"></el-table-column>
                    <el-table-column prop="scenicName" label="景点名称"></el-table-column>
                    <el-table-column prop="concatPerson" width="120" label="联系人"></el-table-column>
                    <el-table-column prop="concatPhone" width="120" label="联系电话"></el-table-column>
                    <el-table-column prop="buyNumber" sortable width="120" label="门票数"></el-table-column>
                    <el-table-column prop="amount" sortable width="120" label="金额"></el-table-column>
                    <el-table-column prop="payTime" sortable width="168" label="支付时间"></el-table-column>
                    <el-table-column prop="createTime" sortable width="168" label="创建时间"></el-table-column>
                    <el-table-column prop="useStatus" width="98" label="支付状态">
                        <template slot-scope="scope">
                            <i v-if="!scope.row.payStatus" style="margin-right: 5px;" class="el-icon-warning"></i>
                            <i v-else style="margin-right: 5px;color: rgb(253, 199, 50);" class="el-icon-success"></i>
                            <el-tooltip v-if="!scope.row.payStatus" class="item" effect="dark" content="未支付，不能重新下单"
                                placement="bottom-end">
                                <span style="text-decoration: underline;text-decoration-style: dashed;">未支付</span>
                            </el-tooltip>
                            <span v-else>已支付</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" width="110">
                        <template slot-scope="scope">
                            <span class="text-button" v-if="!scope.row.payStatus"
                                @click="handleTicketPay(scope.row)">支付</span>
                            <span class="text-button" @click="handleTicketDelete(scope.row)">删除</span>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination style="margin:10px 0;" @size-change="handleTicketSizeChange"
                    @current-change="handleTicketCurrentChange" :current-page="ticketCurrentPage" :page-sizes="[10, 20]"
                    :page-size="ticketPageSize" layout="total, sizes, prev, pager, next, jumper"
                    :total="ticketTotalItems"></el-pagination>
            </el-row>
        </el-row>
        <!-- 酒店订单详情 -->
        <el-row>
            <h1 style="margin-inline: 20px;">酒店订单详情</h1>
            <el-row style="padding: 10px;margin: 0 5px;">
                <el-row>
                    <el-select @change="fetchHotelFreshData" size="small" v-model="hotelOrderQueryDto.payStatus"
                        style="margin-left: 5px;" placeholder="支付状态">
                        <el-option v-for="item in statusList" :key="item.value" :label="item.label" :value="item.value">
                        </el-option>
                    </el-select>
                    <el-date-picker size="small" style="width: 220px;margin-left: 5px;" v-model="hotelSearchTime"
                        type="daterange" range-separator="至" start-placeholder="创建开始" end-placeholder="创建结束">
                    </el-date-picker>
                    <el-input size="small" style="width: 0px;">
                        <el-button slot="append" @click="fetchTicketFreshData" icon="el-icon-search"></el-button>
                    </el-input>
                </el-row>
            </el-row>
            <el-row style="margin: 0 15px;border-top: 1px solid rgb(245,245,245);">
                <el-table :data="hotelTableData" style="width: 100%">
                    <el-table-column prop="hotelName" label="酒店"></el-table-column>
                    <el-table-column prop="roomName" width="120" label="房间名"></el-table-column>
                    <el-table-column prop="concatPerson" width="120" label="联系人"></el-table-column>
                    <el-table-column prop="concatPhone" width="120" label="联系电话"></el-table-column>
                    <el-table-column prop="amount" sortable width="120" label="金额"></el-table-column>
                    <el-table-column prop="payTime" sortable width="168" label="支付时间"></el-table-column>
                    <el-table-column prop="createTime" sortable width="168" label="创建时间"></el-table-column>
                    <el-table-column prop="useStatus" width="98" label="支付状态">
                        <template slot-scope="scope">
                            <i v-if="!scope.row.payStatus" style="margin-right: 5px;" class="el-icon-warning"></i>
                            <i v-else style="margin-right: 5px;color: rgb(253, 199, 50);" class="el-icon-success"></i>
                            <el-tooltip v-if="!scope.row.payStatus" class="item" effect="dark" content="未支付，不能重新下单"
                                placement="bottom-end">
                                <span style="text-decoration: underline;text-decoration-style: dashed;">未支付</span>
                            </el-tooltip>
                            <span v-else>已支付</span>
                        </template>
                    </el-table-column>
                    <el-table-column label="操作" width="110">
                        <template slot-scope="scope">
                            <span class="text-button" v-if="!scope.row.payStatus"
                                @click="handleHotelPay(scope.row)">支付</span>
                            <span class="text-button" @click="handleHotelDelete(scope.row)">删除</span>
                        </template>
                    </el-table-column>
                </el-table>
                <el-pagination style="margin:10px 0;" @size-change="handleHotelSizeChange"
                    @current-change="handleHotelCurrentChange" :current-page="hotelCurrentPage" :page-sizes="[10, 20]"
                    :page-size="hotelPageSize" layout="total, sizes, prev, pager, next, jumper"
                    :total="hotelTotalItems"></el-pagination>
            </el-row>
        </el-row>
    </el-row>
</template>

<script>
import LineChart from "@/components/LineChart"
export default {
    components: { LineChart },
    data() {
        return {
            filterText: '',
            // 门票订单相关数据
            ticketCurrentPage: 1,
            ticketPageSize: 10,
            ticketTotalItems: 0,
            ticketQueryDays: 365,
            ticketTableData: [],
            ticketSelectedRows: [],
            scenicTicketOrderQueryDto: {},
            ticketSearchTime: [],
            ticketValues: [],
            ticketDates: [],
            // 酒店订单相关数据
            hotelCurrentPage: 1,
            hotelPageSize: 10,
            hotelTotalItems: 0,
            hotelQueryDays: 365,
            hotelTableData: [],
            hotelSelectedRows: [],
            hotelOrderQueryDto: {},
            hotelSearchTime: [],
            hotelValues: [],
            hotelDates: [],
            statusList: [{ value: null, label: '全部' }, { value: 0, label: '未支付' }, { value: 1, label: '已支付' }],
        };
    },
    created() {
        this.fetchTicketFreshData();
        this.fetchHotelFreshData();
        // 数据太少，默认查365天
        this.selectedTicketData(this.ticketQueryDays);
        this.selectedHotelData(this.hotelQueryDays);
    },
    methods: {
        // 请求后端的门票金额成交数据
        selectedTicketData(time) {
            this.$axios.get(`/scenicTicketOrder/daysQueryUser/${time}`).then(response => {
                const { data } = response;
                if (data.code === 200) {
                    this.ticketValues = data.data.map(entity => entity.count);
                    this.ticketDates = data.data.map(entity => entity.name);
                }
            })
        },
        // 请求后端的酒店金额成交数据
        selectedHotelData(time) {
            this.$axios.get(`/hotelOrderInfo/daysQueryUser/${time}`).then(response => {
                const { data } = response;
                if (data.code === 200) {
                    this.hotelValues = data.data.map(entity => entity.count);
                    this.hotelDates = data.data.map(entity => entity.name);
                }
            })
        },
        // 置位
        cannel() {
            this.data = {};
            this.dialogOperation = false;
            this.isOperation = false;
        },
        // 批量删除门票订单数据
        async batchDeleteTicket() {
            if (!this.ticketSelectedRows.length) {
                this.$message(`未选中任何门票订单数据`);
                return;
            }
            const confirmed = await this.$swalConfirm({
                title: '删除门票订单数据',
                text: `删除后不可恢复，是否继续？`,
                icon: 'warning',
            });
            if (confirmed) {
                try {
                    let ids = this.ticketSelectedRows.map(entity => entity.id);
                    const response = await this.$axios.post(`/scenicTicketOrder/batchDelete`, ids);
                    if (response.data.code === 200) {
                        this.$swal.fire({
                            title: '删除提示',
                            text: response.data.msg,
                            icon: 'success',
                            showConfirmButton: false,
                            timer: 2000,
                        });
                        this.fetchTicketFreshData();
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
                    console.error(`门票订单信息删除异常：`, e);
                }
            }
        },
        // 批量删除酒店订单数据
        async batchDeleteHotel() {
            if (!this.hotelSelectedRows.length) {
                this.$message(`未选中任何酒店订单数据`);
                return;
            }
            const confirmed = await this.$swalConfirm({
                title: '删除酒店订单数据',
                text: `删除后不可恢复，是否继续？`,
                icon: 'warning',
            });
            if (confirmed) {
                try {
                    let ids = this.hotelSelectedRows.map(entity => entity.id);
                    const response = await this.$axios.post(`/hotelOrderInfo/batchDelete`, ids);
                    if (response.data.code === 200) {
                        this.$swal.fire({
                            title: '删除提示',
                            text: response.data.msg,
                            icon: 'success',
                            showConfirmButton: false,
                            timer: 2000,
                        });
                        this.fetchHotelFreshData();
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
                    console.error(`酒店订单信息删除异常：`, e);
                }
            }
        },
        // 刷新门票订单数据
        async fetchTicketFreshData() {
            try {
                let startTime = null;
                let endTime = null;
                if (this.ticketSearchTime != null && this.ticketSearchTime.length === 2) {
                    const [startDate, endDate] = await Promise.all(this.ticketSearchTime.map(date => date.toISOString()));
                    startTime = `${startDate.split('T')[0]}T00:00:00`;
                    endTime = `${endDate.split('T')[0]}T23:59:59`;
                }
                // 请求参数
                const params = {
                    current: this.ticketCurrentPage,
                    size: this.ticketPageSize,
                    key: this.filterText,
                    startTime: startTime,
                    endTime: endTime,
                    ...this.scenicTicketOrderQueryDto
                };
                const response = await this.$axios.post('/scenicTicketOrder/queryUser', params);
                const { data } = response;
                this.ticketTableData = data.data;
                this.ticketTotalItems = data.total;
            } catch (error) {
                console.error('查询门票订单信息异常:', error);
            }
        },
        // 刷新酒店订单数据
        async fetchHotelFreshData() {
            try {
                let startTime = null;
                let endTime = null;
                if (this.hotelSearchTime != null && this.hotelSearchTime.length === 2) {
                    const [startDate, endDate] = await Promise.all(this.hotelSearchTime.map(date => date.toISOString()));
                    startTime = `${startDate.split('T')[0]}T00:00:00`;
                    endTime = `${endDate.split('T')[0]}T23:59:59`;
                }
                // 请求参数
                const params = {
                    current: this.hotelCurrentPage,
                    size: this.hotelPageSize,
                    key: this.filterText,
                    startTime: startTime,
                    endTime: endTime,
                    ...this.hotelOrderQueryDto
                };
                const response = await this.$axios.post('/hotelOrderInfo/queryUser', params);
                const { data } = response;
                this.hotelTableData = data.data;
                this.hotelTotalItems = data.total;
            } catch (error) {
                console.error('查询酒店订单信息异常:', error);
            }
        },
        add() {
            this.dialogOperation = true;
        },
        handleTicketFilter() {
            this.ticketCurrentPage = 1;
            this.fetchTicketFreshData();
        },
        handleTicketFilterClear() {
            this.filterText = '';
            this.handleTicketFilter();
        },
        handleHotelFilter() {
            this.hotelCurrentPage = 1;
            this.fetchHotelFreshData();
        },
        handleHotelFilterClear() {
            this.filterText = '';
            this.handleHotelFilter();
        },
        handleTicketSizeChange(val) {
            this.ticketPageSize = val;
            this.ticketCurrentPage = 1;
            this.fetchTicketFreshData();
        },
        handleTicketCurrentChange(val) {
            this.ticketCurrentPage = val;
            this.fetchTicketFreshData();
        },
        handleHotelSizeChange(val) {
            this.hotelPageSize = val;
            this.hotelCurrentPage = 1;
            this.fetchHotelFreshData();
        },
        handleHotelCurrentChange(val) {
            this.hotelCurrentPage = val;
            this.fetchHotelFreshData();
        },
        // 处理门票支付的方法
        handleTicketPay(row) {
            const sceniTicketOrder = { id: row.id }
            this.$axios.post('/scenicTicketOrder/pay', sceniTicketOrder).then(res => {
                if (res.data.code === 200) {
                    this.$message.success('支付成功');
                    this.fetchTicketFreshData();
                    this.selectedTicketData(this.ticketQueryDays);
                }
            }).catch(error => {
                console.error("支付错误：", error);
            })
        },
        // 处理酒店支付的方法
        handleHotelPay(row) {
            const hotelOrderInfo = { id: row.id }
            this.$axios.post('/hotelOrderInfo/pay', hotelOrderInfo).then(res => {
                if (res.data.code === 200) {
                    this.$message.success('支付成功');
                    this.fetchHotelFreshData();
                    this.selectedHotelData(this.hotelQueryDays);
                }
            }).catch(error => {
                console.error("支付错误：", error);
            })
        },
        // 处理门票删除的方法
        handleTicketDelete(row) {
            this.ticketSelectedRows.push(row);
            this.batchDeleteTicket();
        },
        // 处理酒店删除的方法
        handleHotelDelete(row) {
            this.hotelSelectedRows.push(row);
            this.batchDeleteHotel();
        }
    },
};
</script>

<style scoped lang="scss"></style>    