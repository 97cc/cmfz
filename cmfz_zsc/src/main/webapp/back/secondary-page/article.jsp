<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<script>
    $(function () {

        $('#article').jqGrid({
            styleUI:'Bootstrap',// 用于整合bootstrap的样式
            url:'${path}/Article/queryPage',
            datatype:'json',
            colNames:['id','标题','作者','作者ID','内容','创建时间','操作'],
            cellEdit:true,
            colModel:[
                {name:'id'},
                {name:'title',align:'center'},
                {name:'author',align:'center'},
                {name:'guruId',align:'center'},
                {name:'content',align:'center'},
                {name:'publishDate',align:'center'},
                {name:'',width : 50,
                   formatter:function(value,options,rows){
                    //alert(rows);
                    //console.log(rows.valueOf().id);
                        return  "<button class='btn btn-warning btn-sm'  onclick=\"update('"+rows.id+"')\">修改</button>";
                    }
                }
            ],
            editurl:'${path}/Article/edit',
            autowidth:true,  // 自动计算列宽（依据父容器大小）
            pager:'#article_page',     // 指定分页工具栏*href='${path}/Article/update?article='"+rows+"*/
            rowList:[5,10,20,50,100],  // 每页显示条数的选项
            rowNum:5,   // 默认每页显示的条数
            page:1,     // 默认显示第几页
            viewrecords:true,   // 是否显示信息的总记录数
            toolbar:['true','top'],   // 指定工具栏
            caption:'轮播图信息展示',   // 设置表格标题
            height:340, // 设置表格高度
            rownumbers:true,  // 显示行号
            multiselect:true,
        }).navGrid('#article_page',{edit : false, add : false, del : true},
            {// 编辑工具修饰
                closeAfterEdit:true,
            },{

            },{
                // 删除工具的修饰
                closeAfterSearch:true
            },{ // 搜索工具的修饰
                closeAfterSearch:true
            });
    });


    KindEditor.create('#article_content',{
        resizeType:1,
        filterMode : true,
        allowFileManager:true,
        fileManagerJson:'${path}/Article/browser',//指定网络库中的路径
        fillDescAfterUploadImage:true,                            //本地添加跳转到编辑界面
        uploadJson:'${path}/Article/upluad',//指定上传文件的路径

       afterBlur:function () {
            this.sync();
        },
        afterChange:function () {//编辑器内容发生变化时执行
            this.sync();
       }

    });


    //修改
    function update(result) {

        document.getElementById("article_form");
        KindEditor.html("#article_content","");
        /*$("#article_form").reset();*/
        var jqGrid = $("#article").jqGrid('getRowData',result);//获取当前行数据信息
        $("#article_ids").val(jqGrid.id);//给ID框赋值
        $("#article_guruId").val(jqGrid.guruId); //跟作者ID赋值
        $("#article_title").val(jqGrid.title);
        $("#article_author").val(jqGrid.author);
        //$(document.getElementsByTagName("iframe")[0].contentWindow.document.body).html(jqGrid.content)
       // alert(jqGrid.content)
        KindEditor.html("#article_content",jqGrid.content);//跟文本框回显
        $("#articleModal").modal("show")  //弹出模态框

    }
    /*添加 与  修改 的 模态框*/
    function modale() {
      /*  $("#article_form").reset(); */   //jquer清form表单*/
       // document.getElementById("article_form");//js清form表单

        $("#article_ids").val(null);//给ID框赋值
        $("#article_guruId").val(null); //跟作者ID赋值
        $("#article_title").val(null);
        $("#article_author").val(null);
        KindEditor.html("#article_content",null);
        $("#articleModal").modal("show");
    }

    //添加
    function save() {
        $.ajax({
            url:"${path}/Article/edit",
            data:$("#article_form").serialize(),
            type:'post',
            datatype:'json',
            success:function () {
                $("#articleModal").modal("hide");//关闭模态框
                $("#article").trigger("reloadGrid");
            }
        })
    }
</script>

<div class="modal fade" id="articleModal" role="dialog" >
    <div class="modal-dialog" role="document" style="width:1000px;height:800px;">
        <div class="modal-content" >
            <div class="modal-header">
                <h4 class="modal-title" id="exampleModalLabel">添加新的文章</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id = "article_form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">文章标题：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="article_title" name = "title" value="" placeholder="请输入文章名字...">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">文章作者：</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="article_author" name = "author" value="" placeholder="请输入文章作者...">
                        </div>
                    </div>

                    <textarea id="article_content" name="content" value="" style="width:970px;height:400px;">

                    </textarea>
                    <input type="hidden" id="article_ids" name = "id" value="" /> <%--文章ID--%>
                    <input type="hidden" id="article_guruId" name = "guruId" value="" /><%--作者ID--%>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="save()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<ul class="nav nav-tabs">
    <li role="presentation" class="active"><a href="#"><b>所有文章</b></a></li>
    <li role="presentation"><a onclick="modale()"><b>添加文章</b></a></li>
</ul>
<div>
    <table id="article"></table>
    <div id="article_page" style="height: 40px"></div>
</div>
