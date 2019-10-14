var id1
var hr_name
$("document").ready(function(){
	var edit_OPEN = true;
	var hr_OPEN=true;
	//hr下拉列表
	var hr_xiala=$("#hr_sel");
		$.ajax({
			url:"admin_selecthr.action",
			type:"POST",
			timeout:5000,
			dataType:"json",
			success:function(data){
				hr_xiala.append("<option value='admin'>超级管理员</option>");
				for(i=0;i<data.length;i++){
					hr_xiala.each(function(){
						var a=data[i].realname;
						hr_xiala.append("<option value=\'"+a+"'\>"+a+"</option>");
					})
				}
			},
			error : function(xhr, textStatus) {
				layer.msg('服务器错误，请稍后再试！', {
					icon : 5
				});
			}
		});
	//通过ID查询详情
	id1=parent.src_json[0].pagelist[parent.src_show_page].id;
	 $.ajax({
		  url:'admin_xiangqing.action',
			type:'POST',
			data:{
				"id":id1,
			},
			timeout:5000,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				value=data;
				$("#date_1").val(value[0].contactdate=="0001-01-01"?"":value[0].contactdate);
				$("#hr_sel").val(value[0].hr);
				$("#rz_time").val(value[0].fastentrytime=="0001-01-01"?"":value[0].fastentrytime);
				$('input:radio[name="td"]').eq(value[0].post=="is"?0:1).attr("checked",'checked');
				$("#city").val(value[0].name);
				$('input:radio[name="sex"]').eq(value[0].sex=="TT"?0:1).attr("checked",'checked');
				$("#idno").val(value[0].idno);
				$("#age").val(value[0].age=="0"?"defult":value[0].age);
				$("#nations").val(value[0].nation);
				$("#rz_type").val(value[0].recruitmenttype);
				$("#skills").val(value[0].skill);
				$("#languages").val(value[0].language);
				$("#langa_lev").val(value[0].languageability);
				$("#hopes").val(value[0].hopesalary=="0"?"":value[0].hopesalary);
				var str=value[0].workplace;
				var s=new Array(2);
				s=str.split("-");
				$("#w1").val(s[0]);
				$("#w2").append('<option value="'+s[1]+'">'+s[1]+'</option>').val(s[1]);
				$("#w3").append('<option value="'+s[2]+'">'+s[2]+'</option>').val(s[2]);
				$("#bg_time").val(value[0].graduatetime=="0001-01-01"?"":value[0].graduatetime);
				$("#bg_school").val(value[0].graduateschool);
				$("#majors").val(value[0].major);
				$("#edu_sel").val(value[0].education);
				$("#tels").val(value[0].telphone);
				$("#eml").val(value[0].email);
				$("#other_s").val(value[0].othercontact);
				$('input:radio[name="tdli"]').eq(value[0].entryedornot=="is"?0:1).attr("checked",'checked');
				$("#rz-time").val(value[0].entrytime=="0001-01-01"?"":value[0].entrytime);
				$("#lz-time").val(value[0].contractperiod=="0001-01-01"?"":value[0].contractperiod);
				$("#try_time").val(value[0].uesperiod);
				$("#hetong").val(value[0].contractrenewal);
				$("#fuli").val(value[0].welfare);
				$("#pro_ht").val(value[0].departuretime);
				$("#reasons").val(value[0].departurereasons);
				hr_name=value[0].hr;
				if(parent.HR_R==0){
					if(parent.HR_NAME==hr_name){
						 $("#xg8").attr("style","display:block");
						 hr_OPEN=false;
					 }else{
						 hr_OPEN=true;
						 $("#xg8").attr("style","display:none");
					 }
				 }
			},
			error : function(xhr, textStatus) {
				layer.msg('服务器错误，请稍后再试！', {
					icon : 5
				});
			}
	  });
	  //表
	 $.ajax({
		  url:'admin_selectComp.action',
			type:'POST',
			data:{
				"id":id1,
			},
			timeout:5000,
			dataType:'json',
			success : function(data, textStatus, jqXHR) {
				$.each(data,function(index,value) {
					$("#main1 #con #dowebok .section:nth-child(6)>div .table_con>table>tbody").append("<tr><td>"+value.company+"</td><td>"+value.job+"</td><td>"+value.startDate+"</td><td>"+value.endDate+"</td><td>"+value.salary+"</td><td>"+value.singleGold+"</td><td><i class='fa fa-minus-square'></i></td></tr>");
					});
			},
			error : function(xhr, textStatus) {
				layer.msg('服务器错误，请稍后再试！', {
					icon : 5
				});
			}
	 });
	//从列表中移出指定的服务客户
		$("#main1 #con #dowebok .section:nth-child(6)>div .table_con>table>tbody").on("click","tr>td:nth-child(7)",function(){
			$(this).closest("tr").remove();
		});
	 
	$("input,select").attr("disabled", "disabled");
	
	//文件上传
	layui.use('upload', function () {
		layui.upload({
			url: 'upload.action',
			elem: '#enclosure2',
			method: 'post', //上传接口的http类型
			ext: 'rar|RAR',
			unwrap:true,
			success: function (res) {
				//JSON.stringify(res)
				var DBFileURLName = res.DBFileURLName;
				ajaxEmp2(DBFileURLName);
			}
		});
	});
	
	//文件修改函数体
	function ajaxEmp2(DBFileURLName){
		var table = $("#main1 #con #dowebok .section:nth-child(6)>div .table_con>table>tbody");
		var table_value = ["empId", "company", "job", "startDate", "endDate", "salary", "singleGold"];
		var table_json = "[";
		$.each(table.children("tr"), function (index, value) {
			table_json = table_json + "{'" + table_value[0] + "':" + "'22',";
			$.each($(value).children("td"), function (index, value) {
				if (index < 6) {
					table_json = table_json + "'" + table_value[index + 1] + "':" + "'" + $(value).text() + "',"
				}
			});
			table_json = table_json.substring(0, table_json.length - 1);
			table_json = table_json + "},"
		});
		table_json = table_json.substring(0, table_json.length - 1);
		table_json = table_json + "]";
		$.ajax({
			url : 'admin_alter.action',
			type : 'POST',
			data : {
				"id":parent.src_json[0].pagelist[parent.src_show_page].id,
				"contactdate":$("#date_1").val(),
				"hr":$("#hr_sel").val(),
				"fastentrytime":$("#rz_time").val(),
				"post":$('input:radio[name="td"]:checked').val(),
				"name":$("#city").val(),
				"sex":$('input:radio[name="sex"]:checked').val(),
				"idno":$("#idno").val(),
				"age":$("#age").val(),
				"nation":$("#nations").val(),
				"recruitmenttype":$("#rz_type").val(),
				"skill":$("#skills").val(),
				"language":$("#languages").val(),
				"languagelevel":$("#langa_lev").val(),
				"hopesalary":$("#hopes").val(),
				"workspace":$("#w1").val()+"-"+$("#w2").val()+"-"+$("#w3").val(),
				"graduatetime":$("#bg_time").val(),
				"graduateschool":$("#bg_school").val(),
				"major":$("#majors").val(),
				"eduation":$("#edu_sel").val(),
				"tel":$("#tels").val(),
				"email":$("#eml").val(),
				"other":$("#other_s").val(),
				"entryedornot":$('input:radio[name="tdli"]:checked').val(),
				"entrytime":$("#rz-time").val(),
				"contractperiod":$("#lz-time").val(),
				"uesperiod":$("#try_time").val(),
				"contractrenewal":$("#hetong").val(),
				"welfare":$("#fuli").val(),
				"departuretime":$("#pro_ht").val(),
				"departurereasons":$("#reasons").val(),
				"table_json": table_json,
				"enclosure": DBFileURLName
			},
			timeout : 5000, 
			dataType : 'text', 
			success : function(data, textStatus, jqXHR) {
				if(data==1){
					$(".bottom").html("修改信息");
					parent.layer.msg('修改成功！', {shade: 0.3});
					$("input,select").attr("disabled", "disabled");
					edit_OPEN = true;
				}else {
					parent.layer.msg('服务器异常，修改失败', {icon: 5,shade: 0.3});
				}
			}
		});	
	}
	
	//确认修改
	$("#xg8").click(function () {
		if (edit_OPEN) {
			$("input,select").removeAttr("disabled");
			if (!hr_OPEN) {
				$("select[id='hr_sel']").attr("disabled","disabled");
			}
			$(this).html("确认修改");
			edit_OPEN = false;
		} else {
				if ($("input[name='file']").val().trim() != "") {
					$("input[name='file']").parent("form").attr("action", "upload.action?EmpName=" + $("#city").val()).submit();
				}else {
					ajaxEmp2("");
				}
				$(this).html("修改");
		}
	});

	
	//动态下拉
	var xiala=$("#gs");
	$.ajax({
		url:"admin_selectcompany.action",
		type:"POST",
		timeout:5000,
		dataType:"json",
		success:function(data){
			if(data!="null"){
				for(i=0;i<data.length;i++){
					xiala.each(function(){
						var a=data[i].name;
						xiala.append("<option value=\'"+a+"'\>"+a+"</option>");
					})
					
				}
			}
		},
		error : function(xhr, textStatus) {
			layer.msg('服务器错误，请稍后再试！', {
				icon : 5
			});
		}
	});
});