<template>
  <div class="container">
    <el-button type="primary" @click="fetchProductData" circle icon="el-icon-search"> </el-button>
    <el-button type="primary" @click="getcategorySale">切换分类销量</el-button>
    <el-button type="primary" @click="fetchTop10">切换前10销量</el-button>

    <!-- <caculator></caculator> -->
    <div id="main" style="width: 60%;height:400px;"></div>
    <!-- <bin></bin> -->

    <el-dialog title="商品所有数据" :visible.sync="dialogVisible" width="80%" :before-close="handleClose">
      <el-table :data="tableData" style="width: 100%" height="500">
        <el-table-column fixed prop="name" label="商品名字" :show-overflow-tooltip="true" width="150">
        </el-table-column>
        <el-table-column prop="mainCategory" label="主分类" width="120">
        </el-table-column>
        <el-table-column prop="subCategory" label="次分类" width="120">
        </el-table-column>
        <el-table-column prop="image" label="image" :show-overflow-tooltip="true" width="120">
        </el-table-column>
        <el-table-column prop="link" label="link" :show-overflow-tooltip="true" width="300">
        </el-table-column>
        <el-table-column prop="ratings" label="评分" width="120">
        </el-table-column>
        <el-table-column prop="noOfRatings" label="评分数量" width="120">
        </el-table-column>
        <el-table-column prop="discountPrice" label="折扣价" width="120">
        </el-table-column>
        <el-table-column prop="actualPrice" label="实际价格" width="120">
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import FileUp from './components/fileup.vue'
import caculator from './components/caculator.vue'
import Bin from './components/bin.vue'
// 导入接口
import { testApi, listProduct, getTopList, getSales, getTop10, getMonthSales } from '../../../../src/api/system/product.js'

const colors = ['#5470C6', '#91CC75', '#EE6666'];
export default {
  components: {
    FileUp,
    Bin,
    caculator
  },
  data() {
    return {
      dialogVisible: false,
      // 表格数据=
      tableData: [],
      myChart: null,
      // colors : ['#5470C6', '#91CC75', '#EE6666'],
      option: {
        // 标题
        title: {
          text: '每月的销售额'
        },
        xAxis: {
          type: 'category',
          data: []
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: [],
            type: 'line'
          }
        ]
      },

      // 饼图option
      bingtOption: {
        title: {
          text: '分类销量',
          subtext: 'Fake Data',
          left: 'center'
        },
        tooltip: {
          trigger: 'item'
        },
        legend: {
          orient: 'vertical',
          left: 'left'
        },
        series: [
          {
            name: 'Access From',
            type: 'pie',
            radius: '50%',
            data: [

            ],
            emphasis: {
              itemStyle: {
                shadowBlur: 10,
                shadowOffsetX: 0,
                shadowColor: 'rgba(0, 0, 0, 0.5)'
              }
            }
          }
        ]
      },

      top10Option: {
        title: {
          text: '商品销售排行榜 Top10'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {},
        grid: {
          left: '3%',
          right: '4%',
          bottom: '3%',
          containLabel: true
        },
        xAxis: {
          type: 'value',
        },
        yAxis: {
          type: 'category',
          data: []
        },
        series: [
          {
            type: 'bar',
            data: []
          },
        ]
      }



    }
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart()
    })

  },
  methods: {
    initChart() {
      // 初始化图表
      this.myChart = echarts.init(document.getElementById('main'))
      // 获取数据
      getMonthSales().then(response => {
        console.log(response);
        this.option.xAxis.data = response.xAxis
        this.option.series[0].data = response.yAxis
        this.myChart.setOption(this.option)
      })

    },

    getcategorySale() {
      // 销毁图表实例，避免内存泄漏
      if (this.myChart) {
        this.myChart.dispose()
        this.myChart = null
      }
      // 初始化图表
      this.myChart = echarts.init(document.getElementById('main'))
      getSales().then(response => {
        this.bingtOption.series[0].data = response
        this.myChart.setOption(this.bingtOption)
      })

    },

    fetchTop10() {
      // 销毁图表实例，避免内存泄漏
      if (this.myChart) {
        this.myChart.dispose()
        this.myChart = null
      }
      // 初始化图表
      this.myChart = echarts.init(document.getElementById('main'))
      getTop10().then(response => {
        console.log(response);
        response.forEach(item => {
          this.top10Option.yAxis.data.push(item.name)
          this.top10Option.series[0].data.push(item.value)
        })
        this.myChart.setOption(this.top10Option)
      })
    },

    fetchProductData() {
      // 调用接口
      // testApi().then(response => {
      //   console.log(response);
      // })
      listProduct().then(response => {
        console.log(response);
        this.tableData = response
      })
      // 展示弹窗
      this.dialogVisible = true

      // Example: After getting data, update chart
      // this.option.series[0].data = newDataArray;
      // this.myChart.setOption(this.option);
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => { });
    }

  },



  beforeDestroy() {
    // 销毁图表实例，避免内存泄漏
    if (this.myChart) {
      this.myChart.dispose()
      this.myChart = null
    }
  }
}
</script>

<style scoped>
.container {
  padding: 20px;
}

.content-wrapper {
  max-width: 1200px;
  margin: 0 auto;
}

.chart-card {
  margin-top: 20px;
}

#main {
  margin: 20px auto;
}
</style>