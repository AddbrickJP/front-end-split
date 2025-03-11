<template>
    <div>
        <el-card class="box-card">
            <div class="upload-container">
                <el-upload
                    class="upload-demo"
                    action="#"
                    :on-change="handleFileChange"
                    :auto-upload="false"
                    accept=".csv">
                    <el-button type="primary">选择CSV文件</el-button>
                    <div slot="tip" class="el-upload__tip">只能上传 CSV 文件</div>
                </el-upload>
            </div>
        </el-card>
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
        },
        handleFileChange(file) {
            const reader = new FileReader()
            reader.onload = (e) => {
                const text = e.target.result
                this.parseCSV(text)
            }
            reader.readAsText(file.raw)
        },
        parseCSV(csv) {
            const lines = csv.split('\n')
            const xAxisData = []
            const seriesData = []

            // 跳过标题行，从第二行开始解析
            for (let i = 1; i < lines.length; i++) {
                if (lines[i]) {
                    const [name, value] = lines[i].split(',')
                    xAxisData.push(name)
                    seriesData.push(parseFloat(value))
                }
            }

            // 更新图表数据
            this.chartData.xAxisData = xAxisData
            this.chartData.seriesData = seriesData
            this.initChart()

            this.$message.success('CSV文件解析成功')
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
.upload-container {
    margin-bottom: 20px;
}
.el-upload__tip {
    color: #909399;
    margin-top: 8px;
}
</style>