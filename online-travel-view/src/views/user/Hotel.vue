<template>
    <el-row style="background-color: #FFFFFF;padding: 5px 0;border-radius: 5px;">
        <el-row style="padding: 10px 5px;margin: 0 5px;">
            <el-row>
                <span style="display: inline-block;font-size: 26px;font-weight: 800;padding-left: 15px;">酒店信息</span>
                <span style="float: right;">
                    <el-date-picker size="small" style="width: 220px;margin-left: 5px;" v-model="searchTime"
                        type="daterange" range-separator="至" start-placeholder="创建开始" end-placeholder="创建结束">
                    </el-date-picker>
                    <el-input size="small" style="width: 188px;margin-left: 5px;margin-right: 6px;"
                        v-model="scenicQueryDto.name" placeholder="酒店名" clearable @clear="handleFilterClear">
                        <el-button slot="append" @click="handleFilter" icon="el-icon-search"></el-button>
                    </el-input>
                </span>

            </el-row>
        </el-row>
        <el-row style="margin: 0 15px;border-top: 1px solid rgb(245,245,245);">
            <el-row v-if="tableData.length === 0">
                <el-empty description="暂无酒店信息"></el-empty>
            </el-row>
            <el-row v-else class="scenic-item">
                <el-col :span="4" @click.native="hotelClick(hotel)" v-for="(hotel, index) in tableData" :key="index">
                    <div class="item">
                        <img :src="hotel.cover" alt="" srcset="">
                        <div class="name">{{ hotel.name }}</div>
                        <div>
                            <span class="address">
                                <i class="el-icon-location"></i>
                                {{ hotel.address }}
                            </span>
                        </div>
                        <div class="info">
                            <span class="time">{{ timeAgo(hotel) }}</span>
                            <span>联系电话&nbsp;{{ hotel.concatPhone }}</span>
                        </div>
                        <div class="extra-info">
                            <span>{{ hotel.vendorName }}</span>
                        </div>
                    </div>
                </el-col>
            </el-row>
            <el-pagination style="margin:10px 0;" @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="currentPage" :page-sizes="[10, 20]" :page-size="pageSize"
                layout="total, sizes, prev, pager, next, jumper" :total="totalItems"></el-pagination>
        </el-row>
    </el-row>
</template>

<script>
import { timeAgo } from "@/utils/data"
export default {
    data() {
        return {
            data: {},
            filterText: '',
            currentPage: 1,
            pageSize: 10,
            totalItems: 0,
            tableData: [],
            scenicQueryDto: {}, // 搜索条件
            searchTime: [],
        };
    },
    created() {
        this.fetchFreshData();
    },
    methods: {
        hotelClick(hotel) {
            sessionStorage.setItem('hotelInfo', JSON.stringify(hotel));
            // 跳转至酒店详情页
            this.$router.push('/hotelDetail');
        },
        timeAgo(scenic) {
            return timeAgo(scenic.createTime);
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
                    ...this.scenicQueryDto
                };
                const response = await this.$axios.post('/hotel/query', params);
                const { data } = response;
                this.tableData = data.data;
                this.totalItems = data.total;
            } catch (error) {
                console.error('查询酒店信息异常:', error);
            }
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
    },
};
</script>
<style scoped lang="scss">
.scenic-item {
    .item:hover {
        background-color: rgb(248, 248, 248);
    }

    .item {
        margin-block: 20px;
        padding: 30px 10px;
        box-sizing: border-box;
        cursor: pointer;

        .extra-info {
            display: flex;
            justify-content: left;
            align-items: center;
            gap: 5px;
            font-size: 12px;

            span:last-child {
                display: inline-block;
                padding: 3px 6px;
                border-radius: 3px;
                background-color: aliceblue;
                color: rgb(84, 49, 223);
            }
        }

        img {
            width: 100%;
            min-height: 120px;
            max-height: 140px;
            border-radius: 5px;
        }

        .address {
            margin-top: 10px;
            display: inline-block;
            width: 100px;
            font-size: 12px;
            overflow: hidden;
            white-space: nowrap;
            text-overflow: ellipsis;
        }

        .name {
            font-size: 20px;
            font-weight: 800;
        }

        .info {
            display: flex;
            justify-content: left;
            gap: 5px;
            margin-block: 6px;

            span {
                font-size: 12px;
            }
        }
    }

}
</style>