<template>
    <div class="container">
                <div id="bin" style="width: 40%;height:400px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
data() {
    return {
        myChart: null,
        option : {
  title: {
    text: '4分在品牌中的占比',
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
        { value: 1048, name: 'Samsung' },
        { value: 735, name: 'SanDisk' },
        { value: 580, name: 'MI Xiaomi' },
        { value: 484, name: 'Amazon Basics' },
        { value: 300, name: 'TP-Link' }
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
            this.myChart = echarts.init(document.getElementById('bin'))
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

</style>
