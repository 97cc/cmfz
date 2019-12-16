<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath}"></c:set>

<head>


    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Login Form Template</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="assets/css/form-elements.css">
    <link rel="stylesheet" href="assets/css/style.css">
    <link rel="shortcut icon" href="assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="assets/ico/apple-touch-icon-57-precomposed.png">
    <script src="assets/js/jquery-2.2.1.min.js"></script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/jquery.backstretch.min.js"></script>
    <script src="assets/js/scripts.js"></script>
    <script src="assets/js/jquery.validate.min.js"></script>
    <script>

    </script>
</head>


<body>

<!-- Top content -->
<div class="top-content">
    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>CMFZ</strong> Login Form</h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>持明法州</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row" id="md">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top" style="width: 450px">
                        <div class="form-top-left">
                            <h3>登录到首页</h3>
                            <p>输入您的用户名和密码登录:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">
                        <form role="form" method="post" class="login-form" id="loginForm">
                            <%--信息提示--%>
                            <span id="msgDiv"></span>

                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" name="name" placeholder="请输入用户名..."
                                       class="form-username form-control" id="form-username" required>
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" name="password" placeholder="请输入密码..."
                                       minlength="2" class="form-password form-control" id="form-password" required>
                            </div>
                            <div class="form-group">
                                <img id="captchaImage" style="height: 48px ;width: 106px" class="captchaImage" src="${pageContext.request.contextPath}/Admin/create">
                                <input style="width: 289px;height: 50px;border:3px solid #ddd;border-radius: 4px;"
                                       type="test" name="enCode" id="form-code" placeholder="请输入验证码..." required>
                            </div>
                            <input type="button" style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;" id="loginButtonId" value="登录">

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    $(function () {
        //$('#md').modal({
        //  backdrop: 'static',
        //});
        //将登陆按钮加事件
        $('#loginButtonId').click(function () {
            //获取form表单中的值
            var admin = $("#loginForm").serialize()
            $.ajax({
                url: '${path}/Admin/login',
                method: "get",
                data: admin,
                success: function (data) {
                    alert(data)
                    if (data == "0") {//登陆成功
                        window.location.href = "${path}/back/home.jsp";
                    }else if (data == "1"){//账户错误
                        $("#msgDiv").html("<b><font style='color:red'>账户输入错误</font></b>");
                    }else if (data == "2"){//密码错误
                        //alert(2222222222)
                        $("#msgDiv").html("<b><font style='color:red'>密码输入错误</font></b>");
                    }else if (data == "3"){//密码错误
                        $("#msgDiv").html("<b><font style='color:red'>验证码输入错误</font></b>");
                    }
                }
            });
        });
    });
</script>


</body>

</html>
