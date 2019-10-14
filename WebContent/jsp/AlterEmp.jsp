<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>欢迎访问</title>
<link rel="shortcut icon" type="image/x-icon" href="jsp/admin/images/Logo.ico" media="screen" />
<link rel="stylesheet" href="css/AlterEmp.css">
<link rel="stylesheet" href="js/layui/css/modules/laydate/laydate.css">
<link rel="stylesheet" href="js/layui/css/layui.css">
<link rel="stylesheet" href="js/layui/css/modules/code.css">
<link rel="stylesheet" href="js/layer/skin/default/layer.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<script src="js/jquery-1.10.2.js"></script>
<script src="js/layui/lay/dest/layui.all.js"></script>
<script src="js/jquery.mousewheel.js"></script>
<script src="js/distpicker.data.js"></script>
<script src="js/distpicker.js"></script>
</head>
<body>
<div id="main1">
  <div id="src-left-nav" class="src_src">
    <ul>
      <li id="seditInfo"><i class="fa fa-comments-o"></i>沟通记录</li>
      <li id="seditPassword"><i class="fa fa-paper-plane"></i>个人信息</li>
      <li id="sevaTeacher"><i class="fa fa-map"></i>教育背景</li>
      <li id="tselectEva"><i class="fa fa-phone"></i>联系方式</li>
      <li id="teditPassword"><i class="fa fa-edit"></i>在职情况</li>
      <li id="tselectStuInfo"><i class="fa fa-graduation-cap"></i>服务客户</li>
      <li id="uploadFile"><i class="fa fa-upload"></i>添加附件</li>
    </ul>
    	<p id="xg8">修改</p>
  </div>
  <div id="con">
    <div id="dowebok">
      <div class="section">
        <h3><i class="fa fa-comments-o"></i>&nbsp;沟通记录</h3>
        <div>
          <ul>
            <li>联系日期</li>
            <li>HR</li>
            <li>最快入职时间</li>
            <li>是否投递</li>
          </ul>
          <ul>
            <li>
              <input class="layui-input" placeholder="请选择日期" id="date_1" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
            </li>
            <li>
              <select id="hr_sel" style="color: #777;" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
              </select>
            </li>
            <li>
              <input id="rz_time" class="layui-input" placeholder="请选择日期" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
            </li>
            <li>
              <div><input type="radio" id="is" name="td" value="is"/>&nbsp;<label for="is">是</label></div>
              &nbsp;&nbsp;
              <div><input type="radio" id="no" name="td" value="no"/>&nbsp;<label for="no">否</label></div>
            </li>
          </ul>
        </div>
      </div>
      <div class="section">
        <h3><i class="fa fa-paper-plane"></i>&nbsp;个人信息</h3>
        <div>
          <ul>
            <li>姓名</li>
            <li>性别</li>
            <li>身份证号</li>
            <li>年龄</li>
            <li>民族</li>
            <li>招聘类型</li>
            <li>技术方向</li>
            <li>语言能力</li>
            <li>期望薪资</li>
            <li>工作地点</li>
          </ul>
          <ul>
            <li>
              <input type="text" id="city"/>
            </li>
            <li>
              <div><input type="radio" id="man" name="sex" value="TT"/>&nbsp;<label for="man">男</label></div>
              &nbsp;&nbsp;
              <div><input type="radio" id="woman" name="sex" value="FF"/>&nbsp;<label for="woman">女</label></div>
            </li>
            <li>
              <input type="text" id="idno"/>
            </li>
            <li>
              <select id="age" style="color: #777;" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
                <option value="defult">--请选择--</option>
              </select>
            </li>
            <li>
              <select id="nations" style="color: #777;" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
                <option value="defult">--请选择--</option>
              </select>
            </li>
            <li>
              <select id="rz_type" style="color: #777;" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
                <option value="defult">----请选择----</option>
                <option>自招</option>
                <option>挂靠</option>
                <option>其他</option>
              </select>
            </li>
            <li>
              <select id="skills" style="color: #777;" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
                <option value="defult">----请选择----</option>
                <option>C/C++</option>
                <option>Java </option>
                <option>.net</option>
                <option>PHP</option>
                <option>前端开发</option>
                <option>产品需求</option>
                <option>维护工程师</option>
                <option>UI设计</option>
                <option>软件测试</option>
                <option>项目管理</option>
                <option>翻译</option>
                <option>讲师</option>
                <option>其他</option>
              </select>
            </li>
            <li>
              <select id="languages" style="color: #777;" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
                <option value="defult">--请选择语言--</option>
                <option>英语</option>
                <option>日语</option>
                <option>韩语</option>
                <option>俄语</option>
                <option>德语</option>
                <option>西班牙语</option>
                <option>阿拉伯语</option>
              </select>
              &nbsp;&nbsp;&nbsp;&nbsp;
              <select id="langa_lev" style="color: #777;" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
                <option value="defult">--请选择等级--</option>
                <option>一级</option>
                <option>二级</option>
                <option>三级</option>
                <option>四级</option>
                <option>五级</option>
                <option>六级</option>
                <option>七级</option>
                <option>八级</option>
                <option>九级</option>
                <option>十级</option>
              </select>
            </li>
            <li>
              <input type="text" id="hopes"/>
            </li>
            <li>
            	<div data-toggle="distpicker">
				  <select id="w1"></select>
				  <select id="w2"></select>
				  <select id="w3"></select>
				</div>
			</li>
          </ul>
        </div>
      </div>
      <div class="section">
        <h3><i class="fa fa-map"></i>&nbsp;教育背景</h3>
        <div>
          <ul>
            <li>毕业时间</li>
            <li>毕业院校</li>
            <li>专业</li>
            <li>学历</li>
          </ul>
          <ul>
            <li>
              <input id="bg_time" class="layui-input" placeholder="请选择日期" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
            </li>
            <li>
              <input id="bg_school" type="text"/>
            </li>
            <li>
              <input id="majors" type="text"/>
            </li>
            <li>
              <select id="edu_sel" style="color: #777;" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
                <option value="defult">----请选择----</option>
                <option>高中</option>
                <option>中专</option>
                <option>大专</option>
                <option>本科</option>
                <option>硕士</option>
                <option>博士</option>
              </select>
            </li>
          </ul>
        </div>
      </div>
      <div class="section">
        <h3><i class="fa fa-phone"></i>&nbsp;联系方式</h3>
        <div>
          <ul>
            <li>电话号码</li>
            <li>邮箱</li>
            <li>其他联系方式</li>
          </ul>
          <ul>
            <li>
              <input id="tels" type="text"/>
            </li>
            <li>
              <input id="eml" type="text"/>
            </li>
            <li>
              <input id="other_s" type="text"/>
            </li>
          </ul>
        </div>
      </div>
      <div class="section">
        <h3><i class="fa fa-edit"></i>&nbsp;在职情况</h3>
        <div>
          <ul>
            <li>是否入职</li>
            <li>入职日期</li>
            <li>离职时间</li>
            <li>试用期限</li>
            <li>合同续签</li>
            <li>福利</li>
            <li>合同期限</li>
            <li>离职原因</li>
          </ul>
          <ul>
            <li>
              <div><input type="radio" id="isli" name="tdli" value="is"/>&nbsp;<label for="isli">是</label></div>
              &nbsp;&nbsp;
              <div><input type="radio" id="noli" name="tdli" value="no"/>&nbsp;<label for="noli">否</label></div>
            </li>
            <li>
            	<input id="rz-time" class="layui-input" placeholder="请选择日期" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
            </li>
            <li>
            	<input id="lz-time" class="layui-input" placeholder="请选择日期" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
            </li>
            <li>
            	<select id="try_time" style="color: #777;" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
                <option value="defult">----请选择----</option>
                <option>无</option>
                <option>1个月</option>
                <option>2个月</option>
                <option>3个月</option>
                <option>6个月</option>
              </select>
            </li>
            <li><input id="hetong" type="text"/></li>
            <li><input id="fuli" type="text"/></li>
            <li>
            	<select id="pro_ht" style="color: #777;" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
                <option value="defult">----请选择----</option>
                <option>项目合同</option>
                <option>1年</option>
                <option>2年</option>
                <option>3年</option>
              </select>
            </li>
            <li><input id="reasons" type="text"/></li>
          </ul>
        </div>
      </div>
      <div class="section">
        <h3><i class="fa fa-graduation-cap"></i>&nbsp;服务客户</h3>
        <div>
        	<div class="table_con">
        		<table id="table_1">
        			<thead>
        				<tr>
        					<td>公司</td>
        					<td>部门</td>
        					<td>到岗时间</td>
        					<td>离岗时间</td>
        					<td>单金</td>
        					<td>薪资</td>
        					<td>操作</td>
        				</tr>
        			</thead>
        			<tbody>
        				
        			</tbody>
        		</table>
        		<div class="input">
        			<ul>
        				<li>到岗客户</li>
        				<li>到岗时间</li>
        				<li>离岗时间</li>
        				<li>单金</li>
        				<li>薪资</li>
        			</ul>
        			<ul>
        				<li>
						 <select id="gs" style="color: #777;" autocomplete="off" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
						  </select>
						  &nbsp;&nbsp;&nbsp;&nbsp;
						  <select id="bm" style="color: #777;" autocomplete="off" onChange="if($(this).val()!='defult'){$(this).css({'color':'#000'});}else{$(this).css({'color':'#777'});}">
							<option value="defult">--请选择部门--</option>
							<option value="外交部">外交部</option>
							<option value="公关部">公关部</option>
							<option value="餐饮部">餐饮部</option>
						  </select>
        				</li>
        				<li>
        					<input id="gsfirstdate" class="layui-input" placeholder="请选择日期" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
        				</li>
        				<li>
        					<input id="gsenddate" class="layui-input" placeholder="请选择日期" onclick="layui.laydate({elem: this, istime: true, format: 'YYYY-MM-DD'})">
        				</li>
        				<li><input id="dz" type="text"/></li>
        				<li><input id="zj" type="text"/></li>
        				<li><p>保存到列表</p></li>
        			</ul>
        		</div>
        	</div>
		</div>
      </div>
      <div class="section">
        <h3><i class="fa fa-upload"></i>&nbsp;添加附件</h3>
        <div>
        <h3>如果有多个文件需要上传，请压缩成RAR后再进行上传！</h3>
        <p><input id="enclosure2" type="file" name="file" lay-type="file" ></p>
        </div>
      </div>
    </div>
  </div>
</div>
<script src="js/AlterEmp.js"></script> 
<script src="js/AddEmp.js"></script> 
</body>
</html>