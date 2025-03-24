<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>CSV文件上传</span>
      </div>
      
<!-- 居中 -->
            <el-upload
              class="upload-demo"
              drag
              action="#"
              :on-change="handleFileChange"
              :before-upload="beforeUpload"
              :auto-upload="false"
              :limit="1"
              accept=".csv"
              :on-exceed="handleExceed"
              ref="upload">
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">
                <div>只能上传 CSV 文件，且文件大小不超过 10MB</div>
            
              </div>
            </el-upload>
         

      <div class="operation-area" v-if="tableData.length">
        <el-button type="primary" @click="confirmImport">确认导入</el-button>
        <el-button @click="cancelImport">取消</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'FileUpload',
  data() {
    return {
      tableData: [],
      file: null
    }
  },
  methods: {
    // 
    handleFileChange(file) {
      if (!file) return
      this.file = file
      const reader = new FileReader()
      reader.onload = (e) => {
        try {
          const text = e.target.result

        } catch (error) {
          this.$message.error('文件解析失败，请检查文件格式是否正确')
        }
      }
      reader.readAsText(file.raw)
    },
    beforeUpload(file) {
      console.log(2);
      const isCSV = file.type === 'text/csv' || file.name.endsWith('.csv')
      const isLt10M = file.size / 1024 / 1024 < 10

      if (!isCSV) {
        this.$message.error('只能上传 CSV 文件!')
        return false
      }
      if (!isLt10M) {
        this.$message.error('文件大小不能超过 10MB!')
        return false
      }
      return true
    },
    handleExceed() {
      this.$message.warning('只能上传一个文件')
    },
    // parseCSV(csv) {
    //   const lines = csv.split('\n')
    //   const data = []

    //   // 跳过标题行，从第二行开始解析
    //   for (let i = 1; i < lines.length; i++) {
    //     if (lines[i].trim()) {
    //       const [name, value] = lines[i].split(',')
    //       if (name && value) {
    //         data.push({
    //           name: name.trim(),
    //           value: parseFloat(value.trim()) || 0
    //         })
    //       }
    //     }
    //   }

    //   if (data.length === 0) {
    //     this.$message.error('文件内容为空或格式不正确')
    //     return
    //   }

    //   this.tableData = data
    //   this.$message.success('文件解析成功')
    // },

    confirmImport() {
      // 这里可以发送数据到父组件或者直接处理数据
      this.$emit('on-import', this.tableData)
      this.$message.success('数据导入成功')

      
    },
    cancelImport() {
      this.tableData = []
      this.file = null
      this.$refs.upload.clearFiles()
    }
  }
}
</script>

<style scoped>
.app-container {
  padding: 20px;
}
.upload-area {
  text-align: center;
}
.el-upload {
  width: 100%;
  display: block;
}
.el-upload-dragger {
  width: 100%;
  max-width: 600px;
  margin: 0 auto;
}
.template-download {
  margin-top: 10px;
  text-align: center;
}
.data-preview {
  padding: 0 20px;
}
.data-preview h4 {
  margin-top: 0;
  margin-bottom: 15px;
}
.operation-area {
  margin-top: 20px;
  text-align: center;
}
</style> 