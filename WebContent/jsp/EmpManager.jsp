<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<title></title>
<base href="<%=basePath%>">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
<script src="js/layer/layer2.js"></script>
<script src="js/layui/lay/dest/layui.all.js"></script>
<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script src="js/jquery.mousewheel.js"></script>
<script src="js/distpicker.data.js"></script>
<script src="js/distpicker.js"></script>
<link rel="stylesheet" type="text/css" href="css/EmpManager.css" />
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="js/layui/css/layui.css">
<link rel="stylesheet" href="js/layui/css/modules/laydate/laydate.css">
</head>
<body>
	<div id="global">
		<div id="select_1">
			<ul>
				<li>
					<div class="box">姓名</div> <input type="text" class="text1"
					id="selectname">
				</li>
				<li><div class="box">技术方向</div> <select class="text" id="js_fx">
						<option value="" selected>--请选择--</option>
						<option value="C/C++">C/C++</option>
						<option value="Java">Java</option>
						<option value=".net">.net</option>
						<option value="PHP">PHP</option>
						<option value="前端开发">前端开发</option>
						<option value="产品需求">产品需求</option>
						<option value="维护工程">维护工程</option>
						<option value="UI设计">UI设计</option>
						<option value="软件测试">软件测试</option>
						<option value="项目管理">项目管理</option>
						<option value="翻译">翻译</option>
						<option value="讲师">讲师</option>
						<option value="其他">其他</option>
				</select></li>
				<li class="li_dd"><div class="box">工作地点</div>
					<div data-toggle="distpicker" class="didian_1">
						<select class="didian_2"></select> <select class="didian_3"></select>
						<select class="didian_4"></select>
					</div></li>
				<li><div class="box">入职时间</div> <input id="rz_sj" class="text"
					type="text" placeholder="请选择日期"
					onClick="WdatePicker()" />
				</li>
				<li><div class="box">离职时间</div> <input id="lz_sj" class="text"
					type="text" placeholder="请选择日期"
					onClick="WdatePicker()" />
				</li>
				<li><div class="box">招聘类型</div> <select class="text" id="zp_lx">
						<option value="" selected>--请选择--</option>
						<option value="自招">自招</option>
						<option value="挂靠">挂靠</option>
						<option value="其他">其他</option>
				</select></li>
				<li><div class="box">学历</div> <select class="text1" id="xl">
						<option value="" selected>--请选择--</option>
						<option value="高中">高中</option>
						<option value="中专">中专</option>
						<option value="大专">大专</option>
						<option value="本科">本科</option>
						<option value="博士">博士</option>
						<option value="硕士">硕士</option>
				</select></li>
				<li><div class="box">是否入职</div> <select class="text" id="sf_rz">
						<option value="" selected>--请选择--</option>
						<option value="is">是</option>
						<option value="no">否</option>
				</select></li>
				<li><div class="box">到岗客户</div> <select class="text" id="fu_kh">
						<option value="" selected>--请选择--</option>
				</select></li>
				<li><p class="selectbutton">查询</p></li>
				<li><p class="selectbutton2">打印</p><p class="selectbutton3">重置</p></li>
			</ul>
		</div>
		<div id="div1">
			<div class="biti">
				<div class="biti1"></div>
				<i class="fa fa-fire"></i>&nbsp;员工管理
			</div>
			<div id="table_1">
				<table class="table table-hover" id="t1">
					<thead>
						<tr>
							<th class="fl_p">操作</th>
							<th>到岗客户</th>
							<th>序号</th>
							<th>姓名</th>
							<th>性别</th>
							<th>技术方向</th>
							<th>工作地点</th>
							<th>招聘类型</th>
							<th>HR</th>
							<th>电话号码</th>
							<th>薪资</th>
							<th>毕业院校</th>
							<th>毕业时间</th>
							<th>学历</th>
							<th>入职日期</th>
							<th>离职时间</th>
							<th class="fl_p2"></th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
			</div>
			<div id="page">
				<ul>
					<li id="page_1"><div class="page_up" is="">上一页</div></li>
					<li id="page_2"><div class="page_up" is="">下一页</div></li>
					<li><div class="page_3" id="sumye" sumpage="">共0页</div></li>
					<li><div class="page_3" id="sumjl">共0条记录</div></li>
					<li><div class="page_3" id="tiaozhuanye">
							到第<input type="text" class="page_2" value="" id="inpage" />页
						</div></li>
					<li><div class="page_bottom">确定</div></li>
				</ul>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/EmpManager.js"></script>
</body>
</html>