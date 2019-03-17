<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>人力资源管理系统</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <!-- Vue -->
    <script src="//vuejs.org/js/vue.min.js"></script>
    <link rel="stylesheet" href="//unpkg.com/iview/dist/styles/iview.css">
    <script src="//unpkg.com/iview/dist/iview.min.js"></script>
    <!--elementUI-->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <link rel="stylesheet" href="../static/css/index.css">
</head>
<body>
<div id="app">
    <div id="pics">
        <el-carousel :height="screenH+'px'">
            <el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="../static/res/index1.jpg"/> </el-carousel-item>
            <el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="../static/res/index2.jpg"/> </el-carousel-item>
            <el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="../static/res/index3.jpg"/> </el-carousel-item>
            <el-carousel-item><img :height="screenH+'px'" :width="screenW+'px'" src="../static/res/index4.jpg"/> </el-carousel-item>
        </el-carousel>
    </div>

    <el-form id="login" ref="form" :model="form" label-width="80px">
        <div class="formtitle">人力资源管理系统</div>
        <hr/>
        <el-form-item class="formitem" label="用户名">
            <el-input v-model="form.name" placeholder="请输入用户名" clearable></el-input>
        </el-form-item>
        <el-form-item class="formitem" label="密码">
            <el-input v-model="form.pswd" placeholder="请输入密码" show-password></el-input>
        </el-form-item>
        <el-form-item class="formitem">
            <el-button type="primary" @click="login">登录</el-button>
        </el-form-item>
    </el-form>
    <!--<el-row>-->
    <!--<el-col :span="10" offset="14"><el-card id="login">-->
    <!--<div slot="header" class="clearfix">-->
    <!--<span>人力资源管理系统</span>-->
    <!--</div>-->
    <!--<div class="login-content">-->
    <!--<el-row class="text-item">-->
    <!--<el-col :span="3" offset="2">用户名</el-col>-->
    <!--<el-col :span="17" offset="1"><el-input style="float: right" placeholder="请输入用户名" v-model="inputName" clearable></el-input></el-col>-->
    <!--</el-row>-->
    <!--<el-row class="text-item" glutter="30">-->
    <!--<el-col :span="3" offset="2">密码</el-col>-->
    <!--<el-col :span="17" offset="1"><el-input placeholder="请输入密码" v-model="inputPswd" show-password></el-input></el-col>-->
    <!--</el-row>-->
    <!--</div>-->
    <!--<hr/>-->
    <!--<el-row>-->
    <!--<el-col :span="4" offset="9"><el-button @click="login"  type="primary" plain>登录</el-button></el-col>-->
    <!--</el-row>-->
    <!--</el-card></el-col>-->
    <!--</el-row>-->
</div>
</body>
<script>
    var vue = new Vue({
        el:'#app',
        data:{
            form:{
                name:'',
                pswd:'',
            },
            screenW:document.documentElement.clientWidth,
            screenH:document.documentElement.clientHeight
        },
        methods:{
            imgLoad(){
                this.screenH=this.$refs.bannerHeight[0].height;
                cosole.log(this.screenH);
            },

            login() {
                console.log(this.form);

                $.ajax({
                    url:'/login',
                    data:{
                        form:this.form,
                    },
                    method:'post',
                    success(res){
                        console.log(res);
                        if(res.code=='200') {
                            alert('登录成功');
                        }
                    },
                    error(res){
                        console.log(res);
                        alert('登录失败');
                    }
                })
            },
        },
        // create:function () {
        //     this.setSize();
        // },
        watch: {
            screenW (val) {
                this.screenW = val;
                console.log(this.screenW);
            },
            screenH (val) {
                this.screenH = val;
            },
        },
        mounted:function () {
            // this.setSize();
            window.onresize = function () { // 定义窗口大小变更通知事件
                this.screenW = document.documentElement.clientWidth;
                this.screenH = document.documentElement.clientHeight;
            }
            console.log(this.screenW,this.screenH);
        }
    })
</script>
</html>