<template>
    <div class="container">
                <div id="main" style="width: 100%;height:400px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
import FileUp from './components/fileup.vue'
import { testApi } from '@/api/system/product'

export default {
    components: {
        FileUp
    },
    data() {
        return {
            data: ['Samsung', 'SanDisk', 'MI Xiaomi', 'Amazon Basics', 'TP-Link'],
            myChart: null,

            series :[
  {
    data: [120, 200, 150, 80, 70, 110, 130],
    type: 'bar',
    stack: 'a',
    name: 'a'
  },
  {
    data: [10, 46, 64, '-', 0, '-', 0],
    type: 'bar',
    stack: 'a',
    name: 'b'
  },
  {
    data: [30, '-', 0, 20, 10, '-', 0],
    type: 'bar',
    stack: 'a',
    name: 'c'
  },
  {
    data: [30, '-', 0, 20, 10, '-', 0],
    type: 'bar',
    stack: 'b',
    name: 'd'
  },
  {
    data: [10, 20, 150, 0, '-', 50, 10],
    type: 'bar',
    stack: 'b',
    name: 'e'
  }
],

            chartOptions: {
                title: {
                    text: '商品分类及数量'
                },
                tooltip: {},
                // x轴的字段
             xAxis: {
    type: 'category',
    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  },
  yAxis: {
    type: 'value'
  },
  series: []
            }
        }
    },
    mounted() {
        this.$nextTick(() => {
            this.initChart()
        })
    },
    methods: {
        test() {
            // console.log('test')
            testApi().then(res => {
                console.log('你好，访问到了')
                console.log(res)
            })
        },
        initChart() {
            // 初始化图表
            this.myChart = echarts.init(document.getElementById('main'))
            this.addstyle()
            
            // 设置数据
            // this.chartOptions.xAxis.data = this.chartData.xAxisData
            // 第一条的数据
            this.chartOptions.series = this.series
            
            // 绘制图表
            this.myChart.setOption(this.chartOptions)
        },
          // 添加样式
    addstyle(){
        const stackInfo = {};
        const series = this.series
for (let i = 0; i < series[0].data.length; ++i) {
  for (let j = 0; j < series.length; ++j) {
    const stackName = series[j].stack;
    if (!stackName) {
      continue;
    }
    if (!stackInfo[stackName]) {
      stackInfo[stackName] = {
        stackStart: [],
        stackEnd: []
      };
    }
    const info = stackInfo[stackName];
    const data = series[j].data[i];
    if (data && data !== '-') {
      if (info.stackStart[i] == null) {
        info.stackStart[i] = j;
      }
      info.stackEnd[i] = j;
    }
  }
}
for (let i = 0; i < series.length; ++i) {
  const data = series[i].data;
  const info = stackInfo[series[i].stack];
  for (let j = 0; j < series[i].data.length; ++j) {
    // const isStart = info.stackStart[j] === i;
    const isEnd = info.stackEnd[j] === i;
    const topBorder = isEnd ? 20 : 0;
    const bottomBorder = 0;
    data[j] = {
      value: data[j],
      itemStyle: {
        borderRadius: [topBorder, topBorder, bottomBorder, bottomBorder]
      }
    };
  }
}
this.series = series
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