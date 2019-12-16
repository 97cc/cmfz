<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<script>
    $(function () {
        function showPicture(cellvalue){
            return "<img src='${path}/album/" +cellvalue + "' height='50' width='50'/>";
        }
        $('#tttt').jqGrid({
            styleUI:'Bootstrap',// 用于整合bootstrap的样式
            url:'${path}/Aldum/queryPage',
            datatype:'json',
            colNames:['id','标题','封面','集数','评分','作者','播音','内容','状态','发布日期','添加时间'],
            cellEdit:true,
            colModel:[
                {name:'id'},
                {name:'title',editable:true},
                {
                    name:'cover',
                    align:'center',
                    editable: true,
                    formatter:showPicture,
                    edittype:'file',
                    editoptions:{enctype:"multipart/form-data"}
                },
                {name:'amount',editable:true},
                {name:'score'},
                {name:'author',editable:true},
                {name:'broadcaster',editable:true},
                {name:'content',editable:true},
                {name:'status',editable:true},
                {name:'publishDate',editable:true,edittype:'date'},
                {name:'createDate'}
            ],
            editurl:'${path}/Aldum/edit',
            autowidth:true,            // 自动计算列宽（依据父容器大小）
            pager:'#page',             // 指定分页工具栏
            rowList:[5,10,20,50,100],  // 每页显示条数的选项
            rowNum:10,                 // 默认每页显示的条数
            page:1,     // 默认显示第几页
            viewrecords:true,   // 是否显示信息的总记录数
            height:500, // 设置表格高度
            rownumbers:true,  // 显示行号
            multiselect:true,
            caption : "专辑详细信息",
            subGrid:true, //开启二级表格
            subGridRowExpanded : function(subgrid_id, id) {
                $("#subgrid_table_id").trigger("reloadGrid")
                var subgrid_table_id = subgrid_id + "_t";
                var pager_id = "p_" + subgrid_table_id;
                $("#" + subgrid_id).html(
                    "<table id='" + subgrid_table_id + "' class='scroll'></table>" +
                    "<div id= '"+pager_id + "' class='scroll'></div>");
                $("#" + subgrid_table_id).jqGrid(
                    {
                        url : "${path}/Chapter/queryPage?albumId=" + id,
                        datatype : "json",
                        colNames : [ 'id', '名称', '大小','时长', '音频路径','状态','创建时间','最后一次修改时间'],
                        cellEdit:true,
                        caption : "章节详细信息",
                        colModel : [
                            {name : "id", hidden:true},
                            {name : "title" ,width : 70,editable:true},
                            {name : "size",width : 50,},
                            {name : "duration",width : 50,},
                            {
                                name: "audioPath",
                                align: 'center',
                                editable: true,
                                formatter:function(value,options,rows){
                                    return "<audio controls style='height:30px'>" +
                                        " <source src='${path}/play/"+value+"'" +"type='audio/mpeg'> " +
                                        "</audio>"
                                },
                                edittype: 'file',
                            },
                            {name : "status",width : 50,editable:true},
                            {name : "createDate",width : 100,editable:true,edittype:'date'},
                            {name : "lastUpdateDate",hidden:true}
                        ],
                        styleUI:'Bootstrap',
                        editurl:'${path}/Chapter/editChapter?albumId='+ id,
                        autowidth:true,            // 自动计算列宽（依据父容器大小）
                        rowNum : 10,
                        rowList:[5,10,20,50,100],  // 每页显示条数的选项
                        page:1,                    // 默认显示第几页
                        pager : pager_id,
                        viewrecords:true,          // 是否显示信息的总记录数
                        rownumbers:true,           // 显示行号
                        multiselect:true,          //单选框
                        //sortname : 'num',
                        //sortorder : "asc",
                        height : '100%'
                    });
                $("#" + subgrid_table_id).jqGrid('navGrid',"#" + pager_id, {edit : true, add : true, del : true},
                    {
                        // 编辑工具修饰
                        closeAfterAdd:true,
                        afterSubmit:function(result){
                            alert("----"+result);
                            console.log(result);
                            $.ajaxFileUpload({
                                fileElementId:'audioPath',
                                url:'${path}/Chapter/upluad',
                                data:{id:result.responseJSON.id},
                                type:'post',
                                dataType:'json',
                                secureuri:false , //是否启用安全提交
                                async: true,      //异步提交
                                success:function () {
                                    $("#subgrid_table_id").trigger("reloadGrid")
                                    alert("qweqwe")
                                }
                            });
                            return "www";
                        }

                    },
                    {
                        // 添加工具的修饰
                        closeAfterAdd:true,
                        afterSubmit:function(result){
                            alert("----"+result);
                            console.log(result);
                            $.ajaxFileUpload({
                                fileElementId:'audioPath',
                                url:'${path}/Chapter/upluad',
                                data:{id:result.responseJSON.id},
                                type:'post',
                                dataType:'json',
                                //secureuri:false ,//是否启用安全提交
                                //async: true,   //异步提交
                                success:function () {
                                    alert("qweqwe")
                                    $("#tttt").trigger("reloadGrid")
                                    alert("qweqwe")
                                },
                                error:function () {
                                    alert("qweqwe")
                                    $("#subgrid_table_id").trigger("reloadGrid")
                                    alert("qweqwe")
                                }
                            });
                            return "qqq";
                        }

                    },
                    {
                        closeAfterAdd:true,
                    });
            },

        }).navGrid('#page',{add:true,edit:true,del:true},
            {
                // 编辑工具修饰
               closeAfterAdd:true,
                afterSubmit:function(result){
                    //alert("----");
                    console.log(result);
                    $.ajaxFileUpload({
                        fileElementId:'cover',
                        url:'${path}/Aldum/upluad',
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
            },{
            // 添加工具的修饰
                // /*在提交表单的同时，发送额外的请求参数， 如果请求参数的key与表单提交数据中的key相同他会覆盖掉表单原始传递的数据 */
                closeAfterAdd:true,
                afterSubmit:function(result){
                    //alert("----");
                    console.log(result);
                    $.ajaxFileUpload({
                        fileElementId:'cover',
                        url:'${path}/Aldum/upluad',
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
            },{
                // 删除工具的修饰
            },{
                // 搜索工具的修饰
                closeAfterSearch:true
        });
    });



    //导出
    function albumExcel() {
        alert("下载 专辑表")
        window.location.href="${path}/Aldum/albumExcel";
    }

    //导入
    function albumModal() {
        $("#excelModal").modal("show");
    }



</script>

<div class="modal fade" id="excelModal" role="dialog" >
    <div class="modal-dialog" role="document" style="width:500px;">
        <div class="modal-content" >
            <div class="modal-header">
                <h4 class="modal-title" id="exampleModalLabel">添加新的Excel表</h4>
            </div>
            <div class="modal-body">
                <form id="formUpluadExcel" action="${path}/Aldum/uploadExcel" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="exampleInputFile">Excel表:</label>
                        <input type="file" id="exampleInputFile" name="file">
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-group" >提交</button>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="uploadExcel()">保存</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>




<ul class="nav nav-tabs">
    <li role="presentation"><a onclick="albumExcel()"><b>导出Excel表</b></a></li>
    <li role="presentation"><a onclick="albumModal()"><b>上传Excel表</b></a></li>
</ul>
<div>
    <table id="tttt"></table>
    <div id="page" style="height: 40px"></div>
</div>

