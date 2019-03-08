<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.3.1/jquery.min.js"></script>
    <script src="//vuejs.org/js/vue.min.js"></script>
    <!-- import stylesheet -->
    <link rel="stylesheet" href="//unpkg.com/iview/dist/styles/iview.css">
    <!-- import iView -->
    <script src="//unpkg.com/iview/dist/iview.min.js"></script>
    <link rel="stylesheet" href="/css/stylesheet.css">
</head>
<body class="setbg">

<div class="intro-container">
<div class = "layout">
    <script src='/js/logjs/jquery.min.js'></script>
    <script src='/js/logjs/TweenMax.min.js'></script>
    <script src='/js/logjs/three.min.js'></script>
    <script src="/js/logjs/index.js"></script>
    <Layout class="layoutWrapper">
        <i-Header class="header">
            <Row type="flex"  align="bottom">
                <i-col span = "1" offset="1" >
                    <img :src="imgUrl" class="logo" onclick="goLogin()"/>
                </i-col>
                <i-col span = "5" class="name">
                    EMMM NOTE
                </i-col>
                <#--<i-col span = "18"></i-col>-->
            </Row>
        </i-Header>
        <i-content >
            <Row justify="center">
                <i-col span="18" offset="3">
                    <card :bordered="false" id="loginCard">
                        <Row type="flex" justify="center">
                            <i-col span="10">
                                <i-form action="/login" method="post" :mod5el="formItem" :label-width="80">
                                    <Form-Item label="Username">
                                        <i-Input name="username" type="text" placeholder="Username" v-model="formItem.username"></i-Input>
                                    </Form-Item>
                                    <Form-Item label="Password">
                                        <i-Input name="password" type="password" placeholder="Password" v-model="formItem.password"></i-Input>
                                    </Form-Item>
                                    <Form-Item >
                                        <i-Button type="primary" html-type="submit" class="card-button">Signin</i-Button>

                                        <i-Button @click="register() "class="card-button">Register</i-Button>

                                    </Form-Item>
                                </i-form>
                            </i-col>
                            <i-col span = "10" offset="2">
                                <Carousel autoplay v-model="value2" loop >
                                    <Carousel-Item>
                                        <div class="demo-carousel" >
                                            <img :src="imgr1" class="Carousel-img" />
                                           </div>
                                    </Carousel-Item>
                                    <Carousel-Item>
                                        <div class="demo-carousel" >
                                            <img :src="imgr2" class="Carousel-img"/>
                                         </div>
                                    </Carousel-Item>
                                    <Carousel-Item>
                                        <div class="demo-carousel" >
                                            <img :src="imgr3" class="Carousel-img"/>
                                            </div>
                                    </Carousel-Item>

                                </Carousel>
                            </i-col>
                        </Row>
                    </card>
                </i-col>
            </Row>
        </i-Content>
        <i-Footer class="layout-footer-center">2019 &copy; EMMM NOTE</i-Footer>
    </Layout>
</div>
<#--<#if loginError??>登录出错</#if>-->
<#--<#if user??>welcome ${user}</#if>-->
    <#--<form action="/login" method="post">-->
        <#--<input name="username">-->
        <#--<input name="password">-->
        <#--<button type="submit">login</button>-->
    <#--</form>-->
</div>
<script>
    function goLogin(){
        window.location.href="/login"
    }
    var vue = new Vue({
        el:'.layout',
        data:{
            imgUrl:'/image/logo.png',
            imgr1:'/image/free.jpg',
            imgr2:'/image/search.png',
            imgr3:'/image/teamwork.png',
            value2:0,
            formItem: {
                username: '',
                password:''
            }
        },
        methods:{
            register(){
                window.location.href='/reg';
            }
        }
    })
</script>
</body>
</html>