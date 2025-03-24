<template>
    <div>
        <div id="main" style="width: 600px;height:400px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
    data() {
        return {
            myChart: null,

            option :{
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
#main {
    margin: 20px auto;
}
</style>