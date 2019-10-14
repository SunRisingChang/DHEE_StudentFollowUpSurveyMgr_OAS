<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>欢迎访问</title>
<link rel="shortcut icon" type="image/x-icon" href="images/Logo.ico"
	media="screen" />
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/success.css">
<script src="js/jquery-1.10.2.js"></script>
</head>
<body>
	<div id="src-nav" login_type="<s:property value="#session.type" />" hr_name="<s:property value="#session.realName" />">
		<h1>DHEE华信</h1>
		<span class="nowName">欢迎<strong><s:property value="#session.nowName" /></strong>登陆！
		</span> <span class="logout0"><i class="fa fa-address-card-o"></i>员工录入</span>
		<span class="logout1" ><i class="fa fa-fire"></i>员工管理</span>
		<s:if test="#session.type==1">
			<span class="logout2" ><i class="fa fa-cogs"></i>系统参数</span>
		</s:if>
		<span class="logout3" ><i class="fa fa-power-off"></i>注销</span>
	</div>
	<div id="con">
		<div id="con-main"></div>
		<div id="index">
			<ul>
				<li><i class="fa fa-address-card-o"></i></li>
				<li><i class="fa fa-fire"></i></li>
				<s:if test="#session.type==1">
				<li><i class="fa fa-cogs"></i></li>
				</s:if>
			</ul>
			<ul>
				<li>员工录入</li>
				<li>员工编辑</li>
				<s:if test="#session.type==1">
				<li>系统参数</li>
				</s:if>
			</ul>
		</div>
	</div>
	<div id="reg" style="display: none;">
		<div class="login_head">
			<h1></h1>
		</div>
		<div class="login_con">
			<input type="text" value="User name" is="false" /> <input type="text"
				value="Password" /> <input type="text" value="Real name" is="false" />
			<p class="loginin">提交</p>
			<p class="reg">关闭</p>
		</div>
	</div>
	<script src="js/success.js"></script>
</body>
</html>