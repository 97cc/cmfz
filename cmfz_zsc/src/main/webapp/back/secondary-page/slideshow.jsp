<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<script>
    $(function () {

        function showPicture(cellvalue){
            return "<img src='${pageContext.request.contextPath}/img/" +cellvalue + "' height='50' width='50'/>";
        }
        $('#tttt').jqGrid({
            styleUI:'Bootstrap',// 用于整合bootstrap的样式
            url:'${path}/slideshow/queryAll',
            datatype:'json',
            colNames:['id','标题','图片','添加时间','状态'],
            cellEdit:true,
            colModel:[
                {name:'id'},
                {name:'title',align:'center',editable:true},
                {
                    name:'cover',
                    align:'center',
                    editable: true,
                    formatter:showPicture,
                    edittype:'file',
                    editoptions:{enctype:"multipart/form-data"}
                    // 自定义单元格展示的数据                        
                },
                {name:'createDate'},
                {name:'status',align:'center',editable:true,editable:true}
            ],
            editurl:'${path}/slideshow/edit',
            autowidth:true,  // 自动计算列宽（依据父容器大小）
            pager:'#page',     // 指定分页工具栏
            rowList:[5,10,20,50,100],  // 每页显示条数的选项
            rowNum:5,   // 默认每页显示的条数
            page:1,     // 默认显示第几页
            viewrecords:true,   // 是否显示信息的总记录数
            toolbar:['true','top'],   // 指定工具栏
            caption:'轮播图信息展示',   // 设置表格标题
            height:340, // 设置表格高度
            rownumbers:true,  // 显示行号
            multiselect:true,
        }).navGrid('#page',{refresh:false},
        {// 编辑工具修饰
            closeAfterEdit:true,

        },{ // 添加工具的修饰
            // /*在提交表单的同时，发送额外的请求参数， 如果请求参数的key与表单提交数据中的key相同他会覆盖掉表单原始传递的数据 */
            closeAfterAdd:true,
            afterSubmit:function(result){
                console.log(result);
                $.ajaxFileUpload({
                    fileElementId:'cover',
                    url:'${path}/slideshow/upluad',
                    data:{id:result.responseJSON.id},
                    type:'post',
                    dataType:'json',
                    secureuri:false ,//是否启用安全提交
                    async: true,   //异步提交
                    success:function () {
                        $("#tttt").trigger("reloadGrid")
                    }
                });
                return "sdad";
            }

            //editData:{"id":''}
        },{
            // 删除工具的修饰
            closeAfterSearch:true
        },{ // 搜索工具的修饰
            closeAfterSearch:true
        });
    });


    function exportExcel() {
        alert("下载")
        window.location.href="${path}/slideshow/exportExcel";
    }
    function excelModal(){
        $("#excelEModal").modal("show");
    }

    $(document).ready(function () {
        $("#btn_UpFile").click(function () {
            var p = $('#file_pic').var();
            $('span').html(p)
        })
    });


    function uploadExcel() {
       var  file = $("#formUpluadExcel").serialize();
            alert(file)
        $.ajax({
            url: '${path}/slideshow/uploadExcel',
            method: "post",
            data: file,
            success: function () {
            }
        });


    }


</script>
<div class="modal fade" id="excelEModal" role="dialog" >
    <div class="modal-dialog" role="document" style="width:500px;">
        <div class="modal-content" >
            <div class="modal-header">
                <h4 class="modal-title" id="exampleModalLabel">添加新的Excel表</h4>
            </div>
            <div class="modal-body">
                <form id="formUpluadExcel" action="${path}/slideshow/uploadExcel" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="exampleInputFile">Excel表:</label>
                        <input type="file" id="exampleInputFile" name="file">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-group" >提交</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>


<ul class="nav nav-tabs">
    <li role="presentation"><a onclick="exportExcel()"><b>导出Excel表</b></a></li>
    <li role="presentation"><a onclick="excelModal()"><b>上传Excel表</b></a></li>
</ul>
<div>
    <table id="tttt"></table>
    <div id="page" style="height: 40px"></div>
</div>
