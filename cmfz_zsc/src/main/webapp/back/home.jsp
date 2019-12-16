<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2019/12/4
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<!doctype html>
<html lang="en">
<head>
    <title>home</title>
    <!-- CSS -->
    <link rel="stylesheet" href="assets/css/style.css">
    <!--引入样式文件-->
    <link rel="stylesheet" href="static/bs/css/bootstrap.css" type="text/css">
    <link rel="stylesheet" href="static/jqgrid/css/trirand/ui.jqgrid-bootstrap.css" type="text/css">

    <!--引入js功能文件-->
    <script src="assets/js/jquery-2.2.1.min.js"></script>
    <script type="text/javascript" src="${path}/back/static/jqgrid/js/ajaxfileupload.js"></script>
    <script src="static/bs/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="static/jqgrid/js/trirand/i18n/grid.locale-cn.js" type="text/javascript"></script>
    <script src="static/jqgrid/js/trirand/jquery.jqGrid.min.js" type="text/javascript"> </script>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <%--<script src="assets/js/jquery.backstretch.min.js"></script>--%>
    <script src="assets/js/jquery.validate.min.js"></script>

    <script charset="utf-8" src="../back/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="../back/kindeditor/lang/zh-CN.js"></script>
    <!-- 引入 echarts.js 图像化报表-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/echarts/echarts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/echarts/china.js"></script>
    <%--引入长连接（动态加载）    远程加载--%>
    <script type="text/javascript" src="http://cdn.goeasy.io/goeasy-1.0.3.js"></script>

</head>
<body>

<%--头部--%>
<div>
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div class="container-fluid">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="#">
                            <img src="../back/assets/img/log1.jpg" alt="持明法州" class="img-circle" width="40">
                        </a>
                    </div>
                </div>

            </div>
            <h6 class="navbar-brand">持明法州后台管理系统</h6>
            <!-- Collect the nav links, forms, and sother content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">欢迎：${sessionScope.admin.name}</a></li>
                    <a type="button" class="btn btn-default navbar-btn" href="">安全退出</a>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> 账户管理 <span class="caret"></span></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a type="button" class="btn btn-default btn-sm" href="#" data-toggle="modal" data-target="#exampleModal" data-whatever="@fat"><h5>修改个人信息</h5></a>
                            </li>
                            <%--<li role="separator" class="divider"></li>--%>
                        </ul>
                    </li>
                </ul>
                <%--修改信息模态框--%>
                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="exampleModalLabel">信息管理</h4>
                            </div>
                            <div class="modal-body">
                                <form>
                                    <div class="form-group">
                                        <label for="recipient-name" class="control-label">账户:</label>
                                        <input type="text" class="form-control" id="recipient-name">
                                    </div>
                                    <div class="form-group">
                                        <label for="recipient-name" class="control-label">昵称:</label>
                                        <input type="text" class="form-control" id="recipient-nikcname">
                                    </div>
                                    <div class="form-group">
                                        <label for="recipient-name" class="control-label">新密码:</label>
                                        <input type="text" class="form-control" id="recipient-password">
                                    </div>
                                    <div class="form-group">
                                        <label for="recipient-name" class="control-label">确认密码:</label>
                                        <input type="text" class="form-control" id="recipient-newpasd">
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Send message</button>
                            </div>
                        </div>
                    </div>
                </div>
                <%--修改信息模态框结束--%>

            </div><!-- /.navbar-collapse -->
        </div><!-- /.container-fluid -->
    </nav>
</div>

<%--中部--%>
<div class="row">
    <%--左导航栏开始--%>
    <div class="panel-group col-md-2" id="accordion" role="tablist" aria-multiselectable="true">

        <%--轮播图管理--%>
        <div class="panel panel-default ">
        <div class="panel-heading" role="tab" id="headingOne">
            <h4 class="panel-title">
                <a role="button" id="luibotu" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                轮播图管理
            </a>
            </h4>
        </div>
        <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
            <div class="panel-body">
                <h4 class="panel-title">
                    <a role="button" href="javascript:$('#younei').load('${path}/back/secondary-page/slideshow.jsp')">
                        轮播图详情
                    </a>
                </h4>
            </div>
        </div>
    </div>

        <%--专辑管理--%>
        <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="headingThree">
            <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseTwo">
                    专辑管理
                </a>
            </h4>
        </div>
        <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
            <div class="panel-body">
                <h4 class="panel-title">
                    <a role="button" href="javascript:$('#younei').load('${path}/back/secondary-page/album.jsp')">
                        查看专辑
                    </a>
                </h4>
            </div>
        </div>
    </div>

    <%--文章管理--%>
    <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="essay">
            <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#essays" aria-expanded="false" aria-controls="collapseTwo">
                    文章管理
                </a>
            </h4>
        </div>
        <div id="essays" class="panel-collapse collapse" role="tabpanel" aria-labelledby="essay">
            <div class="panel-body">
                <h4 class="panel-title">
                    <a role="button" href="javascript:$('#younei').load('${path}/back/secondary-page/article.jsp')">
                        查看文章
                    </a>
                </h4>
            </div>

        </div>
    </div>

        <%--用户管理--%>
        <div class="panel panel-default">
        <div class="panel-heading" role="tab" id="headingTwo">
            <h4 class="panel-title">
                <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                    用户管理
                </a>
            </h4>
        </div>
        <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
            <div class="panel-body">
                <h4 class="panel-title">
                    <a role="button" href="javascript:$('#younei').load('${path}/back/secondary-page/user.jsp')">
                        用户信息
                    </a>
                </h4>
            </div>
            <div class="panel-body">
                <h4 class="panel-title">
                    <a role="button" href="javascript:$('#younei').load('${path}/back/secondary-page/echartsUser.jsp')">
                        <b>用户数据图形展示</b>
                    </a>
                </h4>
            </div>
        </div>
    </div>

    </div>

    <%--右边内容--%>
    <div class="panel-group col-md-10" id="younei">
        <div class="alert alert-warning" role="alert">
           <h3>持明法州后台管理系统</h3>
        </div>
        <img src="../back/assets/img/backgrounds/1.jpg" alt="持明法州" style="height: 400px;width: 1500px">

    </div>

</div>


<%--尾部--%>
<div style="background-color: #2aabd2">
      <%-- <h6>驰名法州后台系统</h6>--%>
        <p>@zsc.com</p>
</div>


<script>
    $(function () {
        $("#luibotu").click(function () {
            $("#younei").html("<div class=\"alert alert-warning\" role=\"alert\">\n" +
                "           <h3>持明法州后台管理系统</h3>\n" +
                "        </div>\n" +
                "        <img src=\"../back/assets/img/backgrounds/1.jpg\" alt=\"持明法州\"style=\"height: 400px;width: 1500px\">");
        })
    })

</script>
</body>
</html>
