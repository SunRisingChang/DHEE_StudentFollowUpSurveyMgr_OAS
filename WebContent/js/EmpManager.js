var src_json;
var src_show_page;
$(document).ready(function(){
	var select_mz=$("#selectname");
	var select_jsfx=$("#js_fx");
	var select_gzdd1=$(".didian_2");
	var select_gzdd2=$(".didian_3");
	var select_gzdd3=$(".didian_4");
	var select_rzsj=$("#rz_sj");
	var select_lzsj=$("#lz_sj");
	var select_zplx=$("#zp_lx");
	var select_xl=$("#xl");
	var select_sfrz=$("#sf_rz");
	var select_dgkh=$("#fu_kh");
	var button_cx=$(".selectbutton");
	var button_upPag=$("#page_1>.page_up");
	var button_downPag=$("#page_2>.page_up");
	var show_sumPag=$("#sumye");
	var show_sumRow=$("#sumjl");
	var show_tiaozhuan=$("#tiaozhuanye #inpage");
	var buttin_tz=$("#page .page_bottom");
	var tableHead=$("#table_1");
	var tableFoot=$("#page");
	var tableCon=$("#table_1 #t1 tbody");
	var but2=$(".selectbutton2");
	var reset=$(".selectbutton3");
	//初始化到岗客户
	$.ajax({
		url:"admin_selectcompany.action",
		type:"POST",
		timeout:5000,
		dataType:"json",
		success:function(data){
			$.each(data,function(index,value){
				select_dgkh.append("<option value=\'"+value.name+"'\>"+value.name+"</option>");
			});
		},
		error : function(xhr, textStatus) {
			layer.msg('服务器错误，请稍后再试！', {
				icon : 5
			});
		}
	});
	
	//重置按钮
	reset.click(function(){
		select_mz.val("");
		select_jsfx.val("");
		select_gzdd1.val("");
		select_gzdd2.val("");
		select_gzdd3.val("");
		select_rzsj.val("");
		select_lzsj.val("");
		select_zplx.val("");
		select_xl.val("");
		select_sfrz.val("");
		select_dgkh.val("");
	});
	
	//点击查询
	button_cx.click(function(){
		$.ajax({
			url:"admin_selectShow.action",
			type:"POST",
			timeout:5000,
			data:{
				"select_mz":select_mz.val(),
				"select_jsfx":select_jsfx.val(),
				"select_gzdd":select_gzdd1.val()+"-"+select_gzdd2.val()+"-"+select_gzdd3.val(),
				"select_rzsj":select_rzsj.val(),
				"select_lzsj":select_lzsj.val(),
				"select_zplx":select_zplx.val(),
				"select_xl":select_xl.val(),
				"select_sfrz":select_sfrz.val(),
				"select_dgkh":select_dgkh.val(),
				"nowPage":0
			},
			dataType:"json",
			success:function(data){
				if(data[0].allrow==0){
					layer.msg('您查找的信息不存在！');
				}else {
				select_mz.attr("select_con",select_mz.val());
				select_jsfx.attr("select_con",select_jsfx.val());
				select_gzdd1.attr("select_con",select_gzdd1.val());
				select_gzdd2.attr("select_con",select_gzdd2.val());
				select_gzdd3.attr("select_con",select_gzdd3.val());
				select_rzsj.attr("select_con",select_rzsj.val());
				select_lzsj.attr("select_con",select_lzsj.val());
				select_zplx.attr("select_con",select_zplx.val());
				select_xl.attr("select_con",select_xl.val());
				select_sfrz.attr("select_con",select_sfrz.val());
				select_dgkh.attr("select_con",select_dgkh.val());
				tableHead.stop().fadeOut(100);
				tableFoot.stop().fadeOut(100);
				button_upPag.attr("is",data[0].isup);
				button_downPag.attr("is",data[0].isdown);
				show_sumPag.html("共"+data[0].allpage+"页");
				show_sumPag.attr("sumpage",data[0].allpage);
				show_sumRow.html("共"+data[0].allrow+"条记录");
				show_tiaozhuan.val(data[0].nowpage+1);
				tableHead.attr("pages",data[0].nowpage+1);
				tableCon.find("tr").remove();
				$.each(data[0].pagelist,function(index,value) {
					tableCon.append('<tr><td class="fl_p"><p class="q1" id="xq" rowid="'+index+'" lol="'+value.id+'">详情</p><p class="q1" id="sc" rowid="'+index+'" lol="'+value.id+'">删除</p><p class="q1" id="fjxz" rowid="'+value.id+'" file="'+value.isFile+'">附件下载</p></td><td>'+value.company+'</td><td>'+(data[0].nowpage*10+1+index)+'</td><td>'+value.name+'</td><td>'+((value.sex=="TT")?"男":"女")+'</td><td>'+((value.skill=="defult")?"":value.skill)+'</td><td>'+((value.workplace=="--")?"":value.workplace)+'</td><td>'+((value.recruitmenttype=="defult")?"":value.recruitmenttype)+'</td><td>'+((value.hr=="defult")?"":value.hr)+'</td><td>'+value.telphone+'</td><td>'+((value.hopesalary=="0")?"":value.hopesalary)+'</td><td>'+value.graduateschool+'</td><td>'+((value.graduatetime=="0001-01-01")?"":value.graduatetime)+'</td><td>'+((value.education=="defult")?"":value.education)+'</td><td>'+((value.entrytime=="0001-01-01")?"":value.entrytime)+'</td><td>'+((value.contractperiod=="0001-01-01")?"":value.contractperiod)+'</td><td>  </td></tr>');	
				})
				src_json=data;
				tableHead.stop().fadeIn(100);
				tableFoot.stop().fadeIn(100);
				}
			},
			error : function(xhr, textStatus) {
				layer.msg('服务器错误，请稍后再试！', {
					icon : 5
				});
			}
		});
	});
	
	//点击上一页
	button_upPag.click(function(){
		if ($(this).attr("is")==="true") {
			$.ajax({
				url : 'admin_selectShow.action',
				type : 'POST',
				data : {
					"select_mz":select_mz.attr("select_con"),                                                                           
					"select_jsfx":select_jsfx.attr("select_con"),                                                                         
					"select_gzdd":select_gzdd1.attr("select_con")+"-"+select_gzdd2.attr("select_con")+"-"+select_gzdd3.attr("select_con"),                                                                                                                                             
					"select_rzsj":select_rzsj.attr("select_con"),                                                                         
					"select_lzsj":select_lzsj.attr("select_con"),                                                                         
					"select_zplx":select_zplx.attr("select_con"),                                                                         
					"select_xl":select_xl.attr("select_con"),                                                                           
					"select_sfrz":select_sfrz.attr("select_con"),                                                                         
					"select_dgkh":select_dgkh.attr("select_con"), 
					"nowPage":tableHead.attr("pages")-2,
				},
				timeout : 5000, 
				dataType : 'json', 
				success : function(data, textStatus, jqXHR) {
					if(data[0].allrow==0){
						layer.msg('您查找的信息不存在！');
					}else {
						tableHead.stop().fadeOut(100);
						tableFoot.stop().fadeOut(100);
						button_upPag.attr("is",data[0].isup);
						button_downPag.attr("is",data[0].isdown);
						show_sumPag.html("共"+data[0].allpage+"页");
						show_sumPag.attr("sumpage",data[0].allpage);
						show_sumRow.html("共"+data[0].allrow+"条记录");
						show_tiaozhuan.val(data[0].nowpage+1);
						tableHead.attr("pages",data[0].nowpage+1);
						tableCon.find("tr").remove();
						$.each(data[0].pagelist,function(index,value) {
							tableCon.append('<tr><td class="fl_p"><p class="q1" id="xq" rowid="'+index+'" lol="'+value.id+'">详情</p><p class="q1" id="sc" rowid="'+index+'" lol="'+value.id+'">删除</p><p class="q1" id="fjxz" rowid="'+value.id+'" file="'+value.isFile+'">附件下载</p></td><td>'+value.company+'</td><td>'+(data[0].nowpage*10+1+index)+'</td><td>'+value.name+'</td><td>'+((value.sex=="TT")?"男":"女")+'</td><td>'+((value.skill=="defult")?"":value.skill)+'</td><td>'+((value.workplace=="--")?"":value.workplace)+'</td><td>'+((value.recruitmenttype=="defult")?"":value.recruitmenttype)+'</td><td>'+((value.hr=="defult")?"":value.hr)+'</td><td>'+value.telphone+'</td><td>'+((value.hopesalary=="0")?"":value.hopesalary)+'</td><td>'+value.graduateschool+'</td><td>'+((value.graduatetime=="0001-01-01")?"":value.graduatetime)+'</td><td>'+((value.education=="defult")?"":value.education)+'</td><td>'+((value.entrytime=="0001-01-01")?"":value.entrytime)+'</td><td>'+((value.contractperiod=="0001-01-01")?"":value.contractperiod)+'</td><td>  </td></tr>');	
						})
						src_json=data;
						tableHead.stop().fadeIn(100);
						tableFoot.stop().fadeIn(100);
					}	
				},
				error : function(xhr, textStatus) {
					layer.msg('服务器错误，请稍后再试！', {icon: 5});
				}
			});
		}else {
			layer.msg('亲！已经没有上一页啦！');
		}
	});
	
	//点击下一页
	button_downPag.click(function(){
		if ($(this).attr("is")==="true") {
			$.ajax({
				url : 'admin_selectShow.action',
				type : 'POST',
				data : {
					"select_mz":select_mz.attr("select_con"),                                                                           
					"select_jsfx":select_jsfx.attr("select_con"),                                                                         
					"select_gzdd":select_gzdd1.attr("select_con")+"-"+select_gzdd2.attr("select_con")+"-"+select_gzdd3.attr("select_con"),                                                                                                                                             
					"select_rzsj":select_rzsj.attr("select_con"),                                                                         
					"select_lzsj":select_lzsj.attr("select_con"),                                                                         
					"select_zplx":select_zplx.attr("select_con"),                                                                         
					"select_xl":select_xl.attr("select_con"),                                                                           
					"select_sfrz":select_sfrz.attr("select_con"),                                                                         
					"select_dgkh":select_dgkh.attr("select_con"), 
					"nowPage":tableHead.attr("pages"),
				},
				timeout : 5000, 
				dataType : 'json', 
				success : function(data, textStatus, jqXHR) {
					if(data[0].allrow==0){
						layer.msg('您查找的信息不存在！');
					}else {
						tableHead.stop().fadeOut(100);
						tableFoot.stop().fadeOut(100);
						button_upPag.attr("is",data[0].isup);
						button_downPag.attr("is",data[0].isdown);
						show_sumPag.html("共"+data[0].allpage+"页");
						show_sumPag.attr("sumpage",data[0].allpage);
						show_sumRow.html("共"+data[0].allrow+"条记录");
						show_tiaozhuan.val(data[0].nowpage+1);
						tableHead.attr("pages",data[0].nowpage+1);
						tableCon.find("tr").remove();
						$.each(data[0].pagelist,function(index,value) {
							tableCon.append('<tr><td class="fl_p"><p class="q1" id="xq" rowid="'+index+'" lol="'+value.id+'">详情</p><p class="q1" id="sc" rowid="'+index+'" lol="'+value.id+'">删除</p><p class="q1" id="fjxz" rowid="'+value.id+'" file="'+value.isFile+'">附件下载</p></td><td>'+value.company+'</td><td>'+(data[0].nowpage*10+1+index)+'</td><td>'+value.name+'</td><td>'+((value.sex=="TT")?"男":"女")+'</td><td>'+((value.skill=="defult")?"":value.skill)+'</td><td>'+((value.workplace=="--")?"":value.workplace)+'</td><td>'+((value.recruitmenttype=="defult")?"":value.recruitmenttype)+'</td><td>'+((value.hr=="defult")?"":value.hr)+'</td><td>'+value.telphone+'</td><td>'+((value.hopesalary=="0")?"":value.hopesalary)+'</td><td>'+value.graduateschool+'</td><td>'+((value.graduatetime=="0001-01-01")?"":value.graduatetime)+'</td><td>'+((value.education=="defult")?"":value.education)+'</td><td>'+((value.entrytime=="0001-01-01")?"":value.entrytime)+'</td><td>'+((value.contractperiod=="0001-01-01")?"":value.contractperiod)+'</td><td>  </td></tr>');	
						})
						src_json=data;
						tableHead.stop().fadeIn(100);
						tableFoot.stop().fadeIn(100);
					}	
				},
				error : function(xhr, textStatus) {
					layer.msg('服务器错误，请稍后再试！', {icon: 5});
				}
			});
		}else {
			layer.msg('亲！已经没有下一页啦！');
		}
	});
	
	//跳转到指定页
	buttin_tz.click(function(){
		if (Number($.trim(show_tiaozhuan.val()))<=Number(show_sumPag.attr("sumpage"))&&Number($.trim(show_tiaozhuan.val()))>0) {
			$.ajax({
				url : 'admin_selectShow.action',
				type : 'POST',
				data : {
					"select_mz":select_mz.attr("select_con"),                                                                           
					"select_jsfx":select_jsfx.attr("select_con"),                                                                         
					"select_gzdd":select_gzdd1.attr("select_con")+"-"+select_gzdd2.attr("select_con")+"-"+select_gzdd3.attr("select_con"),                                                                                                                                             
					"select_rzsj":select_rzsj.attr("select_con"),                                                                         
					"select_lzsj":select_lzsj.attr("select_con"),                                                                         
					"select_zplx":select_zplx.attr("select_con"),                                                                         
					"select_xl":select_xl.attr("select_con"),                                                                           
					"select_sfrz":select_sfrz.attr("select_con"),                                                                         
					"select_dgkh":select_dgkh.attr("select_con"), 
					"nowPage":Number($.trim(show_tiaozhuan.val()))-1,
				},
				timeout : 5000, 
				dataType : 'json', 
				success : function(data, textStatus, jqXHR) {
					if(data[0].allrow==0){
						layer.msg('您查找的信息不存在！');
					}else {
						tableHead.stop().fadeOut(100);
						tableHead.stop().fadeOut(100);
						button_upPag.attr("is",data[0].isup);
						button_downPag.attr("is",data[0].isdown);
						show_sumPag.html("共"+data[0].allpage+"页");
						show_sumRow.html("共"+data[0].allrow+"条记录");
						show_sumPag.attr("sumpage",data[0].allpage);
						show_tiaozhuan.val(data[0].nowpage+1);
						tableHead.attr("pages",data[0].nowpage+1);
						tableCon.find("tr").remove();
						$.each(data[0].pagelist,function(index,value) {
							tableCon.append('<tr><td class="fl_p"><p class="q1" id="xq" rowid="'+index+'" lol="'+value.id+'">详情</p><p class="q1" id="sc" rowid="'+index+'" lol="'+value.id+'">删除</p><p class="q1" id="fjxz" rowid="'+value.id+'" file="'+value.isFile+'">附件下载</p></td><td>'+value.company+'</td><td>'+(data[0].nowpage*10+1+index)+'</td><td>'+value.name+'</td><td>'+((value.sex=="TT")?"男":"女")+'</td><td>'+((value.skill=="defult")?"":value.skill)+'</td><td>'+((value.workplace=="--")?"":value.workplace)+'</td><td>'+((value.recruitmenttype=="defult")?"":value.recruitmenttype)+'</td><td>'+((value.hr=="defult")?"":value.hr)+'</td><td>'+value.telphone+'</td><td>'+((value.hopesalary=="0")?"":value.hopesalary)+'</td><td>'+value.graduateschool+'</td><td>'+((value.graduatetime=="0001-01-01")?"":value.graduatetime)+'</td><td>'+((value.education=="defult")?"":value.education)+'</td><td>'+((value.entrytime=="0001-01-01")?"":value.entrytime)+'</td><td>'+((value.contractperiod=="0001-01-01")?"":value.contractperiod)+'</td><td>  </td></tr>');	
						})
						src_json=data;
						tableHead.stop().fadeIn(100);
						tableFoot.stop().fadeIn(100);
					}	
				},
				error : function(xhr, textStatus) {
					layer.msg('服务器错误，请稍后再试！', {icon: 5});
				}
			});
		}else {
			layer.msg('没有该页！');
		}
	});
	
	//删除
	$("#t1").on("click","#sc",function(){
		var deletdom=$(this);
		layer.confirm('是否删除该条数据',{
			btn:['是','不是'],
		},function(){
			$.ajax({
				url : 'admin_deleteimp.action',
				type : 'POST',
				data : {
					"deleted":deletdom.attr("lol"),
				},
				timeout : 5000, 
				dataType : 'text', 
				success : function(data, textStatus, jqXHR) {
					if(data==1){
						deletdom.closest("tr").remove();
						layer.msg('删除成功！');
					}else {
						layer.msg('删除失败！');
					}	
				}
			});
		});
	});
	
	//详细信息
	$("#t1").on("click","#xq",function(){
		src_show_page=$(this).attr("rowid");
		layer.open({
			  type: 2,
			  area: ['100%', '100%'],
			  title :'员工详细信息',
			  fixed: true, //不固定
			  maxmin: false,
			  content:'jsp/AlterEmp.jsp',
			  end:function(){
				  $.ajax({
						url : 'admin_selectShow.action',
						type : 'POST',
						data : {
							"select_mz":select_mz.attr("select_con"),                                                                           
							"select_jsfx":select_jsfx.attr("select_con"),                                                                         
							"select_gzdd":select_gzdd1.attr("select_con")+"-"+select_gzdd2.attr("select_con")+"-"+select_gzdd3.attr("select_con"),                                                                                                                                             
							"select_rzsj":select_rzsj.attr("select_con"),                                                                         
							"select_lzsj":select_lzsj.attr("select_con"),                                                                         
							"select_zplx":select_zplx.attr("select_con"),                                                                         
							"select_xl":select_xl.attr("select_con"),                                                                           
							"select_sfrz":select_sfrz.attr("select_con"),                                                                         
							"select_dgkh":select_dgkh.attr("select_con"), 
							"nowPage":Number($.trim(show_tiaozhuan.val()))-1,
						},
						timeout : 5000, 
						dataType : 'json', 
						success : function(data, textStatus, jqXHR) {
							if(data[0].allrow==0){
								layer.msg('您查找的信息不存在！');
							}else {
								tableHead.stop().fadeOut(100);
								tableHead.stop().fadeOut(100);
								button_upPag.attr("is",data[0].isup);
								button_downPag.attr("is",data[0].isdown);
								show_sumPag.html("共"+data[0].allpage+"页");
								show_sumRow.html("共"+data[0].allrow+"条记录");
								show_sumPag.attr("sumpage",data[0].allpage);
								show_tiaozhuan.val(data[0].nowpage+1);
								tableHead.attr("pages",data[0].nowpage+1);
								tableCon.find("tr").remove();
								$.each(data[0].pagelist,function(index,value) {
									tableCon.append('<tr><td class="fl_p"><p class="q1" id="xq" rowid="'+index+'" lol="'+value.id+'">详情</p><p class="q1" id="sc" rowid="'+index+'" lol="'+value.id+'">删除</p><p class="q1" id="fjxz" rowid="'+value.id+'" file="'+value.isFile+'">附件下载</p></td><td>'+value.company+'</td><td>'+(data[0].nowpage*10+1+index)+'</td><td>'+value.name+'</td><td>'+((value.sex=="TT")?"男":"女")+'</td><td>'+((value.skill=="defult")?"":value.skill)+'</td><td>'+((value.workplace=="--")?"":value.workplace)+'</td><td>'+((value.recruitmenttype=="defult")?"":value.recruitmenttype)+'</td><td>'+((value.hr=="defult")?"":value.hr)+'</td><td>'+value.telphone+'</td><td>'+((value.hopesalary=="0")?"":value.hopesalary)+'</td><td>'+value.graduateschool+'</td><td>'+((value.graduatetime=="0001-01-01")?"":value.graduatetime)+'</td><td>'+((value.education=="defult")?"":value.education)+'</td><td>'+((value.entrytime=="0001-01-01")?"":value.entrytime)+'</td><td>'+((value.contractperiod=="0001-01-01")?"":value.contractperiod)+'</td><td>  </td></tr>');	
								})
								src_json=data;
								tableHead.stop().fadeIn(100);
								tableFoot.stop().fadeIn(100);
							}	
						},
						error : function(xhr, textStatus) {
							layer.msg('服务器错误，请稍后再试！', {icon: 5});
						}
					});
			  }
			});	
	});
	
	//点击下载附件
	$("#t1").on("click","#fjxz",function(){
		var download1=$(this);
		layer.confirm('是否下载附件',{
			btn:['是','不是'],
		},function(index){	
			if (download1.attr("file")!="") {
				var form = $("<form>");
				form.attr("style","display:none");
				form.attr("target","");
				form.attr("method","post");
				form.attr("action","download.action");
				var input1 = $("<input>");
				input1.attr("type","hidden");
				input1.attr("name","download");
				input1.attr("value",download1.attr("rowid"));
				$("body").append(form);
				form.append(input1);
				form.submit();
				form.remove();
				layer.close(index);
			}else {
				layer.msg('该用户没有附件！');
				layer.close(index);
			}
		});
	});
	
	//打印
	but2.on("click",function(){
		var form = $("<form>");
		form.attr("style","display:none");
		form.attr("target","");
		form.attr("method","post");
		form.attr("action","admin_downloadfj.action");
		
		var input0 = $("<input>");
		input0.attr("type","hidden");
		input0.attr("name","select_mz");
		input0.attr("value",select_mz.val().trim());
		
		var input1 = $("<input>");
		input1.attr("type","hidden");
		input1.attr("name","select_jsfx");
		input1.attr("value",select_jsfx.val());
		var input2 = $("<input>");
		input2.attr("type","hidden");
		input2.attr("name","select_gzdd");
		input2.attr("value",select_gzdd1.val()+"-"+select_gzdd2.val()+"-"+select_gzdd3.val());
		var input3 = $("<input>");
		input3.attr("type","hidden");
		input3.attr("name","select_rzsj");
		input3.attr("value",select_rzsj.val());
		var input4 = $("<input>");
		input4.attr("type","hidden");
		input4.attr("name","select_lzsj");
		input4.attr("value",select_lzsj.val());
		var input5 = $("<input>");
		input5.attr("type","hidden");
		input5.attr("name","select_zplx");
		input5.attr("value",select_zplx.val());
		var input6 = $("<input>");
		input6.attr("type","hidden");
		input6.attr("name","select_xl");
		input6.attr("value",select_xl.val());
		var input7 = $("<input>");
		input7.attr("type","hidden");
		input7.attr("name","select_sfrz");
		input7.attr("value",select_sfrz.val());
		var input8 = $("<input>");
		input8.attr("type","hidden");
		input8.attr("name","select_dgkh");
		input8.attr("value",select_dgkh.val());
		$("body").append(form);
		form.append(input0);
		form.append(input1);
		form.append(input2);
		form.append(input3);
		form.append(input4);
		form.append(input5);
		form.append(input6);
		form.append(input7);
		form.append(input8);
		form.submit();
		form.remove();
	});
	
	
})