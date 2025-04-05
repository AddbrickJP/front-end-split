<template>
  <div>
    <el-button type="primary" @click="getlistInventory" circle icon="el-icon-search"></el-button>
    <div id="main" style="width: 1000px;height:400px;"></div>

    <el-dialog title="库存列表" :visible.sync="dialogVisible" width="80%" :before-close="handleClose">
      <el-table :data="tableDataINVENTORY" style="width: 100%" height="500">
        <el-table-column fixed prop="name" label="商品名字" :show-overflow-tooltip="true" width="150">
        </el-table-column>
        <el-table-column prop="variation" label="variation" width="120">
        </el-table-column>
        <el-table-column prop="diy" label="diy" width="120">
        </el-table-column>
        <el-table-column prop="buy" label="buy" :show-overflow-tooltip="true" width="120">
        </el-table-column>
        <el-table-column prop="sell" label="sell" :show-overflow-tooltip="true" width="120">
        </el-table-column>
        <el-table-column prop="color1" label="color1" width="120">
        </el-table-column>
        <el-table-column prop="color2" label="color2" width="120">
        </el-table-column>
        <el-table-column prop="size" label="size" width="120">
        </el-table-column>
        <el-table-column prop="milesPrice" label="milesPrice" width="120">
        </el-table-column>
        <el-table-column prop="source" label="source" width="120">
        </el-table-column>
        <el-table-column prop="seasonalAvailability" label="seasonalAvailability" width="120">
        </el-table-column>
        <el-table-column prop="mannequinPiece" label="mannequinPiece" width="120">
        </el-table-column>
        <el-table-column prop="version" label="version" width="120">
        </el-table-column>
        <el-table-column prop="style" label="style" width="120">
        </el-table-column>
        <el-table-column prop="labelThemes" label="labelThemes" width="120">
        </el-table-column>
        <el-table-column prop="type" label="type" width="120">
        </el-table-column>
        <el-table-column prop="villagerEquippable" label="villagerEquippable" width="120">
        </el-table-column>
        <el-table-column prop="catalog" label="catalog" width="120">
        </el-table-column>
        <el-table-column prop="filename" label="filename" width="120">
        </el-table-column>
        <el-table-column prop="internalId" label="internalId" width="120">
        </el-table-column>
        <el-table-column prop="uniqueEntryId" label="uniqueEntryId" width="120">
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
//  导入inventory api
import { listInventory, getInventory } from '@/api/inventory'

const rawData = [
  [100, 302, 301, 334, 390],
  [320, 132, 101, 134, 90],
];
const totalData = [];
for (let i = 0; i < rawData[0].length; ++i) {
  let sum = 0;
  for (let j = 0; j < rawData.length; ++j) {
    sum += rawData[j][i];
  }
  totalData.push(sum);
}
const grid = {
  left: 100,
  right: 100,
  top: 50,
  bottom: 50
};
const series = [
  '买',
  '卖',
].map((name, sid) => {
  return {
    name,
    type: 'bar',
    stack: 'total',
    barWidth: '60%',
    label: {
      show: true,
      formatter: (params) => Math.round(params.value * 1000) / 10 + '%'
    },
    data: rawData[sid].map((d, did) =>
      totalData[did] <= 0 ? 0 : d / totalData[did]
    )
  };
});
export default {
  data() {
    return {
      dialogVisible: false,
      // 表格数据
      tableDataINVENTORY: [],
      //
      myChart: null,
      option: {
        title: {
          text: '分类库存',
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
      getInventory().then(response => {
        console.log(response);
      //取对象出来，组合成数组对象
      let length = response.name.length
       
        for (let i = 0; i < length; i++) {
          this.option.series[0].data.push({
            value: response.value[i],
            name: response.name[i]
          })
        }

           // 绘制图表
       this.myChart.setOption(this.option)
        // console.log(this.option.series[0].data);
        
      })

   
    },

    getlistInventory() {
      listInventory().then(response => {
        console.log(response)
        this.tableDataINVENTORY = response
        // 打开对话框
        this.dialogVisible = true
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => { });
    },
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
#main {
  margin: 20px auto;
}
</style>