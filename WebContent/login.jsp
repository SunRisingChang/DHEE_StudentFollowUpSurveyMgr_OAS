<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title>欢迎登录</title>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon" href="images/Logo.ico"
	media="screen" />
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="js/layer/skin/default/layer.css">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/layer/layer.js"></script>
<script type="text/javascript">layer.config({path:'layer/'});</script>
</head>
<body>
	<div id="login_student">
		<h1>DHEE华信</h1>
		<div class="login">
			<div class="login_head">
				<h1>请登录</h1>
			</div>
			<div class="login_con">
				<input type="text" value="Username" is="false" /> <input
					type="text" value="Password" />
				<div class="login_rad">
					<input type="radio" id="admin" name="admin" value="admin"
						class="login_rad1" /><label for="admin">超级管理员</label> <input
						type="radio" id="hr"  name="admin" value="hr" class="login_rad2"
						checked /><label for="hr">普通管理员</label>
				</div>
				<p class="loginin">登录</p>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/login.js"></script>
</body>
</html>