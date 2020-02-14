<%--
  Created by IntelliJ IDEA.
  User: dddd
  Date: 2018/12/20
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
</head>
<script>
    function closeCurrentPage() {
        var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);//关闭当前页
    }
</script>
<body>

欢迎你：

<a href="javascript:closeCurrentPage();" >跳到主页</a>
</body>
</html>
