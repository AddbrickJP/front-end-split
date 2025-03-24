<template>
    <div>
        <div id="main" style="width: 1000px;height:400px;"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
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
            myChart: null,
            option : {
  legend: {
    selectedMode: false
  },
  color: ['#003366', '#006699'],
  grid,
  yAxis: {
    type: 'value'
  },
  xAxis: {
    type: 'category',
    data: ['3D glasses', 'bandage', 'beak', 'birthday shades', 'bottom-rimmed glasses',]
    // data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  },
  series
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