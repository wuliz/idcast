<%--
  Created by IntelliJ IDEA.
  User: bin_beyond
  Date: 2019-6-26
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="<%=basePath%>/js/jquery-3.4.1.js"></script>
<script type="text/javascript">
function _hzy(){
	//alert("123");
	//获取img元素
	var img=document.getElementById("img_verifycode");
	img.src="/idcast/VerifyCodeServlet?action="+new Date().getTime();
}
</script>
<body>
<form action="/idcast/LoginServlet" method="post">
<input type="hidden" name="method" value="login">
    用户名：<input type="text" name="username" id="username">
    密  码：<input type="password" name="pwd" id="pwd">
    验证码：<input type="text" name="code" id="code"><img src="/idcast/VerifyCodeServlet" id="img_verifycode" >
   
    <a href="javascript:_hzy()" >换一张</a>
     <input type="submit" value="提交">
</form>
</body>
</html>
