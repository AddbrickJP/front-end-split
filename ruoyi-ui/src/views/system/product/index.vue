<template>
    <div class="container">
                <div id="main" style="width: 60%;height:400px;"></div>
                <!-- <bin></bin> -->
    </div>
</template>

<script>
import * as echarts from 'echarts';
import FileUp from './components/fileup.vue'
import Bin from './components/bin.vue'
const colors = ['#5470C6', '#91CC75', '#EE6666'];
export default {
    components: {
        FileUp,
        Bin
    },
    data() {
        return {
            myChart: null,
            // colors : ['#5470C6', '#91CC75', '#EE6666'],
            option : {
  color: colors,
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'cross'
    }
  },
  grid: {
    right: '20%'
  },
  toolbox: {
    feature: {
      dataView: { show: true, readOnly: false },
      restore: { show: true },
      saveAsImage: { show: true }
    }
  },
  legend: {
    data: ['折扣力度', '评论数量', '评分']
  },
  xAxis: [
    {
      type: 'category',
      axisTick: {
        alignWithLabel: true
      },
      // prettier-ignore
      data: ['sandisk1', 'sandisk2', 'sandisk3', 'sandisk4', 'sandisk5']
    }
  ],
  yAxis: [
    {
      type: 'value',
      name: '折扣力度',
      position: 'right',
      alignTicks: true,
      axisLine: {
        show: true,
        lineStyle: {
          color: colors[0]
        }
      },
      axisLabel: {
        formatter: '{value} '
      }
    },
    {
      type: 'value',
      name: '评论数量',
      position: 'right',
      alignTicks: true,
      offset: 80,
      axisLine: {
        show: true,
        lineStyle: {
          color: colors[1]
        }
      },
      axisLabel: {
        formatter: '{value}'
      }
    },
    {
      type: 'value',
      name: '评分',
      position: 'left',
      alignTicks: true,
      axisLine: {
        show: true,
        lineStyle: {
          color: colors[2]
        }
      },
      axisLabel: {
        formatter: '{value}'
      }
    }
  ],
  series: [
    {
      name: '折扣力度',
      type: 'bar',
      data: [
       135.6, 162.2, 132.6, 120.0, 116.4
      ]
    },
    {
      name: '评论数量',
      type: 'bar',
      yAxisIndex: 1,
      data: [
      71768, 21982, 68198, 215311, 68198, 
      ]
    },
    {
      name: '评分',
      type: 'line',
      yAxisIndex: 2,
      data: [4.4, 4.5, 4.4, 4.5, 4.4]
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
            // 绘制图表
            this.myChart.setOption(this.option)
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