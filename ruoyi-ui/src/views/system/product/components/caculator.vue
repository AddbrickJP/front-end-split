<template>
    <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <div>
          <el-statistic
            group-separator=","
            :precision="2"
            :value="value2"
            :title="title"
          ></el-statistic>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
          <el-statistic title="主类别">
            <template slot="formatter">
            {{ types[1] }}
            </template>
          </el-statistic>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
          <el-statistic
            :value="value1"
            title="子类别"
          >
            <!-- <template slot="prefix">
              <i class="el-icon-s-flag" style="color: red"></i>
            </template>
            <template slot="suffix">
              <i class="el-icon-s-flag" style="color: blue"></i>
            </template> -->
          </el-statistic>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
          <el-statistic :value="like ? 521 : 520" title="Feedback">
            <template slot="suffix">
              <span @click="like = !like" class="like">
                <i
                  class="el-icon-star-on"
                  style="color:red"
                  v-show="!!like"
                ></i>
                <i class="el-icon-star-off" v-show="!like"></i>
              </span>
            </template>
          </el-statistic>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script>
// 导入接口
import { getProductType } from '../../../../../src/api/system/product.js'
  export default {
    mounted() {
        getProductType().then(response => {
            console.log(response)
            this.types = response
            this.value2 = this.types[0]
            this.value1 = this.types[2]
            
        })
        
    },
    data() {
      return {
        types:[],
        like: true,
        value1: 0,
        value2: 0,
        title: "商品",
      };
    },
  };
</script>
<style lang="scss">
  .like {
    cursor: pointer;
    font-size: 25px;
    display: inline-block;
  }
</style>