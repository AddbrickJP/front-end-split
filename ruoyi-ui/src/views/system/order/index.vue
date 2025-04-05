<template>
  <div>
    <el-button type="primary" @click="getOrderList" circle icon="el-icon-search"></el-button>
    <div id="main" style="width: 600px;height:400px;"></div>
    <el-dialog title="订单列表" :visible.sync="dialogVisible" width="80%" :before-close="handleClose">
      <el-table :data="tableData" style="width: 100%" height="500">
        <el-table-column fixed prop="orderNo" label="orderNo" :show-overflow-tooltip="true" width="150">
        </el-table-column>
        <el-table-column prop="orderDate" label="orderDate" width="120">
        </el-table-column>
        <el-table-column prop="buyer" label="buyer" width="120">
        </el-table-column>
        <el-table-column prop="shipCity" label="shipCity" :show-overflow-tooltip="true" width="120">
        </el-table-column>
        <el-table-column prop="shipState" label="shipState" :show-overflow-tooltip="true" width="150">
        </el-table-column>
        <el-table-column prop="sku" label="sku" width="120">
        </el-table-column>
        <el-table-column prop="description" label="description" :show-overflow-tooltip="true" width="120">
        </el-table-column>
        <el-table-column prop="quantity" label="quantity" width="120">
        </el-table-column>
        <el-table-column prop="itemTotal" label="itemTotal" width="120">
        </el-table-column>
        <el-table-column prop="shippingFee" label="shippingFee" width="120">
        </el-table-column>
        <el-table-column prop="cod" label="cod" width="120">
        </el-table-column>
        <el-table-column prop="orderStatus" label="orderStatus" width="120">
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
// 导入接口
import { listOrder } from '../../../../src/api/system/order.js'

export default {
  data() {
    return {
      dialogVisible: false,
      // 表格数据
      tableData: [],

      myChart: null,

      option: {
        title: {
          text: '不同时间订单量'
        },
        xAxis: {
          type: 'category',
          // 英文月份
          data: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            // 还差5个月数据
            data: [150, 230, 224, 218, 135, 147, 260, 150, 230, 224, 218, 135, 147, 260],
            type: 'line'
          }
        ]
      }
    }
  },
  created() {
    listOrder().then(response => {
      console.log(response);
    })
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
      // 绘制图表
      this.myChart.setOption(this.option)
    },
    getOrderList() {
      listOrder().then(response => {
        console.log(1111);
        console.log(response);
        this.tableData = response
        this.dialogVisible = true
      })
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
  },


}
</script>

<style scoped>
#main {
  margin: 20px auto;
}
</style>