<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>kin</title>

    <script charset="utf-8" src="../back/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="../back/kindeditor/lang/zh-CN.js"></script>
    <script>
        var options = {
            filterMode : true,
            allowFileManager:true,
            fileManagerJson:'${pageContext.request.contextPath}/Article/browser',//指定网络库中的路径
            fillDescAfterUploadImage:true,                            //本地添加跳转到编辑界面
            uploadJson:'${pageContext.request.contextPath}/Article/upluad'//指定上传文件的路径
        };
        KindEditor.ready(function(K) {
            window.editor = K.create('#editor_id',options);
        });
    </script>
</head>
<body>


    <textarea id="editor_id" name="content" style="width:700px;height:300px;">
        &lt;strong&gt;HTML内容&lt;/strong&gt;
    </textarea>

</body>
</html>
