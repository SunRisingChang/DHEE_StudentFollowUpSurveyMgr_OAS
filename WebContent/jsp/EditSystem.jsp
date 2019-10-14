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
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>欢迎访问</title>
<link rel="shortcut icon" type="image/x-icon" href="images/Logo.ico"
	media="screen" />
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/EditSystem.css">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/asjquery-1.10.2.js"></script>
<script src="js/layer/layer.js"></script>
<script src="js/jquery.nicescroll.js"></script>
<link rel="stylesheet" href="js/layer/skin/default/layer.css">
</head>
<body>
	<div id="main2">
		<div id="src-left-nav">
			<ul>
				<li id="hrme"><i class="fa fa-fire"></i>HR管理</li>
				<li id="sysme"><i class="fa fa-cogs"></i>系统参数</li>
			</ul>
		</div>
		<h3>
			<i class="fa fa-fire"></i>&nbsp;&nbsp;HR管理
		</h3>
		<div id="con">
			<ul>
			</ul>
			<p class="jiags">
				<i class="fa fa-plus-square"></i>
			</p>
			<ul class="ul2">
			</ul>
			<p class="tishi">请选择要查看的公司！</p>
			<p class="jiabm">
				<i class="fa fa-plus-square"></i>
			</p>
		</div>
		<div id="con2">
			<div id="div1">
				<div id="top">
					<ul>
						<li>姓名</li>
						<li id="concon"><input type="text" /></li>
						<li id="sou">查询</li>
						<li>添加</li>
					</ul>
				</div>
				<div id="center">
					<table class="table table-hover" id="table1" pages="0">
						<thead>
							<tr>
								<th>#</th>
								<th>姓名</th>
								<th>用户名</th>
								<th>密码</th>
								<th style="text-align: center;">操作</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
				</div>
				<div id="tablefoot">
					<ul>
						<li id="uppage">上一页</li>
						<li id="downpage">下一页</li>
						<li id="sumpage">共225页</li>
						<li id="sumrow">共225条记录</li>
						<li>到第</li>
						<li id="nowpage"><input type="text" class="bage" /></li>
						<li>页</li>
						<li id="hrefpag" >确定</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
		
	<script src="js/EditSystem.js"></script>
</body>
</html>