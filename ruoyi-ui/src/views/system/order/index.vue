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
import { listOrder,getOrder } from '../../../../src/api/system/order.js'
const uploadedDataURL = require(`../../../assets/static/china.json`);
const provinceMap = {
  // 标准拼音映射
  'Beijing': '北京',
  'tianjin': '天津',
  'hebei': '河北',
  'shanxi': '山西',
  'neimenggu': '内蒙古',
  'liaoning': '辽宁',
  'jilin': '吉林',
  'heilongjiang': '黑龙江',
  
  // 华东地区
  'Shanghai': '上海',
  'jiangsu': '江苏',
  'zhejiang': '浙江',
  'anhui': '安徽',
  'fujian': '福建',
  'jiangxi': '江西',
  'shandong': '山东',
  
  // 华中地区
  'henan': '河南',
  'hubei': '湖北',
  'hunan': '湖南',
  'Guangdong': '广东',
  'Guangzhou': '广东',
  'Shenzhen': '广东',
  'guangxi': '广西',
  'hainan': '海南',
  
  // 西南地区
  'chongqing': '重庆',
  'sichuan': '四川',
  'Chengdu':'四川',

  'guizhou': '贵州',
  'yunnan': '云南',
  'xizang': '西藏',
  
  // 西北地区
  'shanxi': '陕西',
  'gansu': '甘肃',
  'qinghai': '青海',
  'ningxia': '宁夏',
  'xinjiang': '新疆',
  
  // 特别行政区
  'xianggang': '香港',
  'aomen': '澳门',
  'taiwan': '台湾'
}

export default {
  data() {
    return {
      dialogVisible: false,
      // 表格数据
      tableData: [],

      myChart: null,

      option: {
        title: {
          text: "销售热力图",//表头
          x: "center",
        },
        // dataRange: {
        //   min: 0,
        //   max: 250,
        //   text: ["高", "低"],
        //   realtime: true,
        //   calculable: true,
        //   color: ["darkred" , "red", "orangered", "yellow"],//颜色
        // },
        // 改用新版 visualMap 配置
        visualMap: {
          type: 'continuous',
          min: 0,
          max: 250,
          text: ['高', '低'],
          realtime: true,
          calculable: true,
          inRange: {
            color: ['#FFFF00', '#FFA500', '#FF0000', '#8B0000'] // 颜色渐变
          }
        },
        series: [
          {
            name: "销售热力图",
            type: "map",
            map: "中国",
            roam: true, // 允许缩放拖动
            // label: {
            //   show: true // 显示省份名称
            // },
            mapLocation: {
              y: 0,
            },
            itemSytle: {
              emphasis: { label: { show: false } },
            },
            data: [
              { name: "湖南", value: 700 },
              { name: "广东", value: 1600 },
              { name: "湖北", value: 500 },
              { name: "广西", value: 256 },
              { name: "海南", value: 34 },
            ],
          },
        ],
      }
    }
  },
  created() {
    // listOrder().then(response => {
    //   console.log(response);
    // })
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
    
      echarts.registerMap("中国", uploadedDataURL);

      getOrder().then(response => {
        console.log(response);
        let data = []
        data = response
        data.forEach(item => {
          // console.log(item.name);
          // console.log(provinceMap["guangzhou"])
          item.name = provinceMap[item.name]
          // console.log(item.name);
          
        })
        console.log(data);
        
        this.option.series[0].data = data
        this.myChart.setOption(this.option)
      })
   
      // console.log(this.option.series[0].data);
      
    },
    getOrderList() {
      listOrder().then(response => {

        // console.log(response);
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