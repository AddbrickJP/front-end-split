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
            chartData: {
                xAxisData: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子'],
                seriesData: [5, 20, 36, 10, 10, 20]
            },
            chartOptions: {
                title: {
                    text: 'ECharts 入门示例'
                },
                tooltip: {},
                xAxis: {
                    data: []
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: []
                }]
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
            
            // 设置数据
            this.chartOptions.xAxis.data = this.chartData.xAxisData
            this.chartOptions.series[0].data = this.chartData.seriesData
            
            // 绘制图表
            this.myChart.setOption(this.chartOptions)
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