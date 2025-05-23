<template>
    <el-row style="background-color: #FFFFFF;padding: 5px 0;border-radius: 5px;">
        <el-row style="padding: 10px 5px;margin: 0 5px;box-sizing: border-box;">
            <el-row>
                <span style="display: inline-block;font-size: 26px;font-weight: 800;padding-left: 15px;">景点信息</span>
                <span style="float: right;">
                    <el-select @change="fetchFreshData" size="small" v-model="scenicQueryDto.categoryId"
                        style="margin-left: 5px;" placeholder="所属类别">
                        <el-option v-for="item in categories" :key="item.id" :label="item.name" :value="item.id">
                        </el-option>
                    </el-select>
                    <el-date-picker size="small" style="width: 220px;margin-left: 5px;" v-model="searchTime"
                        type="daterange" range-separator="至" start-placeholder="创建开始" end-placeholder="创建结束">
                    </el-date-picker>
                    <el-input size="small" style="width: 188px;margin-left: 5px;margin-right: 6px;"
                        v-model="scenicQueryDto.name" placeholder="景点名" clearable @clear="handleFilterClear">
                        <el-button slot="append" @click="handleFilter" icon="el-icon-search"></el-button>
                    </el-input>
                </span>
            </el-row>
        </el-row>
        <el-row style="margin: 0 15px;border-top: 1px solid rgb(245,245,245);">
            <el-row v-if="tableData.length === 0">
                <el-empty description="暂无景点信息"></el-empty>
            </el-row>
            <el-row v-else class="scenic-item">
                <el-col :span="6" @click.native="scenicClick(scenic)" v-for="(scenic, index) in tableData" :key="index">
                    <div class="item" style="box-sizing: border-box;">
                        <img :src="scenic.cover" alt="" srcset="">
                        <div class="name">{{ scenic.name }}</div>
                        <div>
                            <span class="address">
                                <i class="el-icon-location"></i>
                                {{ scenic.address }}
                            </span>
                        </div>
                        <div class="info">
                            <span class="time">{{ timeAgo(scenic) }}</span>
                            <span class="save">收藏{{ strDeal(scenic.saveIds) }}</span>
                            <span class="view">浏览{{ strDeal(scenic.viewIds) }}</span>
                        </div>
                        <div class="extra-info">
                            <span><i class="el-icon-school"></i>{{ scenic.vendorName }}</span>
                            <span>{{ scenic.categoryName }}</span>
                        </div>
                    </div>
                </el-col>
            </el-row>
            <el-pagination style="margin:10px 0;" @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page="currentPage" :page-sizes="[8]" :page-size="pageSize"
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
            pageSize: 8,
            totalItems: 0,
            tableData: [],
            scenicQueryDto: {}, // 搜索条件
            categories: [],
            searchTime: [],
        };
    },
    created() {
        this.fetchFreshData();
        this.fetchCategories();
    },
    methods: {
        scenicClick(scenic) {
            sessionStorage.setItem('scenicInfo', JSON.stringify(scenic));
            // 跳转至景点详情页
            this.$router.push('/scenicDetail');
        },
        // 收藏与浏览的处理方法
        strDeal(str) {
            if (str === null) {
                return '(0)'
            }
            const saveIdsAry = str.split(',');
            return '(' + saveIdsAry.length + ')';
        },
        timeAgo(scenic) {
            return timeAgo(scenic.createTime);
        },
        // 获取景点分类
        fetchCategories() {
            this.$axios.post('/scenicCategory/query', {}).then(res => {
                if (res.data.code === 200) {
                    this.categories = res.data.data;
                }
            }).catch(error => {
                console.error("查询景点分类信息异常：", error);
            });
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
                const response = await this.$axios.post('/scenic/query', params);
                const { data } = response;
                this.tableData = data.data;
                this.totalItems = data.total;
            } catch (error) {
                console.error('查询景点信息异常:', error);
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
        padding: 20px 10px;
        border-radius: 6px;
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
            max-height: 190px;
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