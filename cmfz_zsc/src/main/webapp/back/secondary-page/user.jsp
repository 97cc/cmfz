<%@page isELIgnored="false" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set value="${pageContext.request.contextPath}" var="path"></c:set>
<script>
    $(function () {
        $('#article').jqGrid({
            styleUI:'Bootstrap',// 用于整合bootstrap的样式
            url:'${path}/User/queryPage',
            datatype:'json',
            colNames:['id','账户','法名','真实姓名','性别','省份','市','头像','状态','角色','创建时间','师傅ID'],
            cellEdit:true,
            colModel:[
                {name:'id'},
                {name:'phone',align:'center',editable:true},
                {name:'dharma',align:'center',editable:true},
                {name:'name',align:'center',editable:true},
                {name:'sex',align:'center',editable:true},
                {name:'province',align:'center',editable:true},
                {name:'city',align:'center',editable:true},
                {name:'headImg',align:'center',editable:true,edittype:'file',
                    formatter:function(value,options,rows){
                        return "<img src='${path}/userImg/" +value + "' height='50' width='50'/>";
                    },
                },
                {name:'status',align:'center',editable:true},
                {name:'role',align:'center',editable:true},
                {name:'createDate',align:'center'},
                {name:'guruId',align:'center',editable:true}
            ],
            editurl:'${path}/User/edit',
            autowidth:true,  // 自动计算列宽（依据父容器大小）
            pager:'#article_page',     // 指定分页工具栏
            rowList:[5,10,20,50,100],  // 每页显示条数的选项
            rowNum:5,   // 默认每页显示的条数
            page:1,     // 默认显示第几页
            viewrecords:true,   // 是否显示信息的总记录数
            //toolbar:['true','top'],   // 指定工具栏
            caption:'用户信息展示',   // 设置表格标题
            height:340, // 设置表格高度
            rownumbers:true,  // 显示行号
            multiselect:true,
        }).navGrid('#article_page',{edit : true, add : true, del : true},
            {// 编辑工具修饰
                closeAfterAdd:true,
                afterSubmit:function(result){
                    console.log(result);
                    $.ajaxFileUpload({
                        fileElementId:'headImg',
                        url:'${path}/User/upluad',
                        data:{id:result.responseJSON.id},
                        type:'post',
                        dataType:'json',
                        secureuri:false , //是否启用安全提交
                        async: true,      //异步提交
                        success:function () {
                            $("#article").trigger("reloadGrid")
                        }
                    });

                    return "qweqeq";
                }

            },{
                // 添加工具的修饰
                closeAfterAdd:true,
                afterSubmit:function(result){
                    console.log(result);
                    $.ajaxFileUpload({
                        fileElementId:'headImg',
                        url:'${path}/User/upluad',
                        data:{id:result.responseJSON.id},
                        type:'post',
                        dataType:'json',
                        //secureuri:false ,//是否启用安全提交
                        //async: true,   //异步提交
                        success:function () {
                            $("#article").trigger("reloadGrid")
                        },
                        error:function () {
                            $("#article").trigger("reloadGrid")
                        }
                    });
                    return "qweqweqw";
                }

            },{
                // 删除工具的修饰
                closeAfterSearch:true
            },{ // 搜索工具的修饰
                closeAfterSearch:true
            }
        );
    });
    
    
    
    function echartsUser() {





    }
    
    
    
    
    
    
    
    
    
    
    
</script>


<ul class="nav nav-tabs">
    <li role="presentation"><a onclick="echartsUser()"><b>用户数据图形展示</b></a></li>
    <li role="presentation"><a onclick="excelModal()"><b></b></a></li>
</ul>













<div>
    <table id="article"></table>
    <div id="article_page" style="height: 40px"></div>
</div>
