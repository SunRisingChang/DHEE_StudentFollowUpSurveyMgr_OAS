//修改公司名称方法
function gs_edit_fun(e) {
	var i = $(e);
	var li = i.addClass("edit").closest("li").addClass("show_edit");
	layer.prompt({
		title: '请输入公司名称！',
		end: function () {
			i.removeClass("edit").closest("li").removeClass("show_edit");
		}
	}, function (val, index) {
		if (val.trim() != "") {
			$.ajax({
				url: 'admin_editCompany.action',
				type: 'POST',
				data: {
					"comId": $(e).closest("li").attr("comId"),
					"comName": val
				},
				timeout: 5000,
				dataType: 'text',
				success: function (data, textStatus, jqXHR) {
					if(data==110){
						layer.msg('检测到异常访问，3秒后跳转到登陆页！', {icon: 5,shade: 0.3});
						setTimeout(function(){
							location.href = $("base").attr("href")+"login.jsp";
						}, 3000);
					}else {
						i.removeClass("edit").closest("li").removeClass("show_edit");
						li.html(val + "<i class='fa fa-chevron-circle-right'></i><i class='fa fa-pencil'></i><i class='fa fa-minus-square'></i>");
						layer.close(index);
					}
				},
				error: function (xhr, textStatus) {
					layer.msg('服务器错误，请稍后再试！', {
						icon: 5
					});
				}
			});
		} else {
			layer.msg('请输入内容！');
		}

	});
};
//删除公司名称
function gs_jian_fun(e) {
	$.ajax({
		url: 'admin_deleteCompany.action',
		type: 'POST',
		data: {
			"comId": $(e).closest("li").attr("comId")
		},
		timeout: 5000,
		dataType: 'text',
		success: function (data, textStatus, jqXHR) {
			$(e).closest("li").fadeOut(300, function () {
				$(e).closest("li").remove();
			});
		},
		error: function (xhr, textStatus) {
			layer.msg('服务器错误，请稍后再试！', {
				icon: 5
			});
		}
	});
}
//添加部门
function addBM() {
	layer.prompt({
		title: '请输入要添加的部门名称！',
	}, function (val, index) {
		if (val.trim() != "") {
			$.ajax({
				url: 'admin_addJob.action',
				type: 'POST',
				data: {
					"comId": $("#main2 #con>ul:nth-child(1)").find("li[class='show_edit']").attr("comid"),
					"jobName": val
				},
				timeout: 5000,
				dataType: 'text',
				success: function (data, textStatus, jqXHR) {
					$("#main2 #con>.ul2").append('<li comid="' + $("#main2 #con>ul:nth-child(1)").find("li[class='show_edit']").attr("comid") + '">' + val + '<i class="fa fa-pencil"></i><i class="fa fa-minus-square"></i></li>');
					layer.close(index);
				},
				error: function (xhr, textStatus) {
					layer.msg('服务器错误，请稍后再试！', {
						icon: 5
					});
				}
			});
		} else {
			layer.msg('请输入内容！');
		}
	});
}
var src_json;
$(document).ready(function () {
	var gs_list = $("#main2 #con>ul:nth-child(1)");
	var bm_list = $("#main2 #con>.ul2");
	var add_gs = $("#main2 #con .jiags");
	var add_bm = $("#main2 #con .jiabm");
	var hr_page = $("#main2 #src-left-nav #hrme");
	var gs_page = $("#main2 #src-left-nav #sysme");
	//*********************************************分页****************************
	hr_page.click(function () {
		$("#main2 #con").fadeOut(500, function () {
			$("#main2 #con2").fadeIn(500);
		});
		$("#main2 h3").fadeOut(500, function () {
			$("#main2 h3").html('<i class="fa fa-fire"></i>&nbsp;&nbsp;HR管理');
			$("#main2 h3").fadeIn(500);
		});
	});
	gs_page.click(function () {
		$("#main2 #con2").fadeOut(500, function () {
			$("#main2 #con").fadeIn(500);
		});
		$("#main2 h3").fadeOut(500, function () {
			$("#main2 h3").html('<i class="fa fa-cogs"></i>&nbsp;&nbsp;系统参数');
			$("#main2 h3").fadeIn(500);
		});
	});
	//*********************************************系统参数管理页*************************
	//动态加载全部的公司
	$.ajax({
		url: 'admin_getAllCompany.action',
		type: 'POST',
		data: {},
		timeout: 5000,
		dataType: 'json',
		success: function (data, textStatus, jqXHR) {
			if(data==110){
				layer.msg('检测到异常访问，3秒后跳转到登陆页！', {icon: 5,shade: 0.3});
				setTimeout(function(){
					location.href = $("base").attr("href")+"login.jsp";
				}, 3000);
			}else {
				$.each(data, function (index, value) {
					gs_list.append('<li comId="' + value.id + '">' + value.name + '<i class="fa fa-chevron-circle-right"></i><i class="fa fa-pencil"></i><i class="fa fa-minus-square"></i></li>');
				});
			}
		},
		error: function (xhr, textStatus) {
			layer.msg('服务器错误，请稍后再试！', {
				icon: 5
			});
		}
	});

	//对公司减
	gs_list.on("click", "li>i:nth-child(3)", function () {
		gs_jian_fun(this);
	});
	//对部门减
	bm_list.on("click", "li>i:nth-child(2)", function () {
		var i = $(this);
		$.ajax({
			url: 'admin_deleteJob.action',
			type: 'POST',
			data: {
				"CompanyId": $(this).closest("li").attr("comid"),
				"jobName": $(this).closest("li").text().trim()
			},
			timeout: 5000,
			dataType: 'text',
			success: function (data, textStatus, jqXHR) {
				i.closest("li").fadeOut(300, function () {
					i.closest("li").remove();
				});
			},
			error: function (xhr, textStatus) {
				layer.msg('服务器错误，请稍后再试！', {
					icon: 5
				});
			}
		});

	});
	//添加公司
	add_gs.click(function () {
		layer.prompt({
			title: '请输入要添加的公司名称！',
		}, function (val, index) {
			if (val.trim() != "") {
				$.ajax({
					url: 'admin_addCompany.action',
					type: 'POST',
					data: {
						"CompanyName": val
					},
					timeout: 5000,
					dataType: 'text',
					success: function (data, textStatus, jqXHR) {
						gs_list.append("<li comid='" + data + "'>" + val + "<i class='fa fa-chevron-circle-right'></i><i class='fa fa-pencil'></i><i class='fa fa-minus-square'></i></li>");
						layer.close(index);
					},
					error: function (xhr, textStatus) {
						layer.msg('服务器错误，请稍后再试！', {
							icon: 5
						});
					}
				});
			} else {
				layer.msg('请输入内容！');
			}
		});
	});

	//添加部门
	add_bm.click(function () {
		//alert(gs_list.find("li[class='show_edit']").lenght());
		if (gs_list.find("li[class='show_edit']").size()!=0) {
			addBM();
		}
	});

	//处理滚动条出现导致页面变形，美化滚动条
	bm_list.niceScroll({
		cursorcolor: "#ccc", //#CC0071 光标颜色 
		cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0 
		touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备 
		cursorwidth: "5px", //像素光标的宽度 
		cursorborder: "0", //     游标边框css定义 
		cursorborderradius: "5px", //以像素为光标边界半径 
		autohidemode: true //是否隐藏滚动条 
	});

	gs_list.niceScroll({
		cursorcolor: "#ccc", //#CC0071 光标颜色 
		cursoropacitymax: 1, //改变不透明度非常光标处于活动状态（scrollabar“可见”状态），范围从1到0 
		touchbehavior: false, //使光标拖动滚动像在台式电脑触摸设备 
		cursorwidth: "5px", //像素光标的宽度 
		cursorborder: "0", //     游标边框css定义 
		cursorborderradius: "5px", //以像素为光标边界半径 
		autohidemode: true //是否隐藏滚动条 
	});
	//修改公司
	gs_list.on("click", "li>i:nth-child(2)", function () {
		gs_edit_fun(this)
	});
	//修改部门
	bm_list.on("click", "li>i:nth-child(1)", function () {
		var i = $(this);
		var li = i.addClass("edit2").closest("li").addClass("show_edit2");
		var oldValue = i.closest("li").text();
		layer.prompt({
			title: '请输入公司名称！',
			end: function () {
				i.removeClass("edit2").closest("li").removeClass("show_edit2");
			}
		}, function (val, index) {
			$.ajax({
				url: 'admin_editJob.action',
				type: 'POST',
				data: {
					"CompanyId": i.closest("li").attr("comid"),
					"oldValue": oldValue,
					"jobName": val
				},
				timeout: 5000,
				dataType: 'text',
				success: function (data, textStatus, jqXHR) {
					i.removeClass("edit2").closest("li").removeClass("show_edit2");
					li.html(val + "</i><i class='fa fa-pencil'></i><i class='fa fa-minus-square'></i>");
				},
				error: function (xhr, textStatus) {
					layer.msg('服务器错误，请稍后再试！', {
						icon: 5
					});
				}
			});
			layer.close(index);
		});
	});
	//查看公司包含的部门
	gs_list.on("click", "li>i:nth-child(1)", function () {
		$(".tishi").fadeOut(300);
		$(this).closest("ul").find("li").css({
			"z-index": "1",
			"cursor": "Default"
		});
		$(this).closest("ul").find("li").removeClass("show_edit").find("i").removeClass("show_xq");
		$(this).addClass("show_xq").closest("li").addClass("show_edit");
		$(this).closest("li").css({
			"z-index": "-1",
			"cursor": "not-allowed"
		});
		var i = $(this);
		bm_list.fadeOut(300, function () {
			$.ajax({
				url: 'admin_showCompanyJobs.action',
				type: 'POST',
				data: {
					"comId": i.closest("li").attr("comId")
				},
				timeout: 5000,
				dataType: 'json',
				success: function (data, textStatus, jqXHR) {
					bm_list.find("li").remove();
					$.each(data, function (index, value) {
						bm_list.append('<li comid="' + value.comid + '">' + value.job + '<i class="fa fa-pencil"></i><i class="fa fa-minus-square"></i></li>');
					});
				},
				error: function (xhr, textStatus) {
					layer.msg('服务器错误，请稍后再试！', {
						icon: 5
					});
				}
			});
			bm_list.fadeIn(300);
		});
	});
	//*********************************************HR管理***********************************
	var add_hr = $("#main2 #con2 #top>ul>li:nth-child(4)");
	var UandP = $("#reg>.login_con>input");
	var confirm = $("#reg>.login_con>.loginin");
	var back = $("#reg>.login_con>.reg");
	var username = UandP.eq(0);
	var password = UandP.eq(1);
	var Realname = UandP.eq(2);
	var dbo=999;
	var xiugaiID;

	//添加HR弹出层
	UandP.focus(function () {
		if (this.value === this.defaultValue) {
			this.value = "";
			$(this).css("color", "#000");
		}
	});

	UandP.blur(function () {
		if (this.value === "") {
			this.value = this.defaultValue;
			$(this).css("color", "#999");
		} else {
			if (this.defaultValue === "User name") {
				if (this.value.length > 15) {
					layer.tips("不能超过15位！", this, {
						tips: [1, '#78BA32']
					});
				} else {
					if (dbo===1) {
						$.ajax({
							url: 'admin_isHrName.action',
							type: 'post',
							data: {
								"hrName": this.value,
							},
							timeout: 5000,
							dataType: 'text',
							success: function (data, textStatus, jqXHR) {
								if (data == 1) {
									layer.tips("用户名已经存在", username, {
										tips: [1, '#78BA32']
									});
									username.attr("is", "false");
								} else {
									username.attr("is", "true");
								}
							},
							error: function (xhr, textStatus) {
								layer.msg('服务器错误，请稍后再试！', {
									icon: 5
								});
							}
						});
					}else {
						username.attr("is", "true");
					}
				}
			} else if (this.defaultValue === "Real name") {
				if (this.value.length > 15) {
					layer.tips("不能超过15位！", this, {
						tips: [1, '#78BA32']
					});
				} else {
					if (dbo===1) {
						$.ajax({
							url: 'admin_isHrRealName.action',
							type: 'post',
							data: {
								"hrRealName": this.value,
							},
							timeout: 5000,
							dataType: 'text',
							success: function (data, textStatus, jqXHR) {
								if (data == 1) {
									layer.tips("该HR已经存在", Realname, {
										tips: [1, '#78BA32']
									});
									Realname.attr("is", "false");
								} else {
									Realname.attr("is", "true");
								}
							},
							error: function (xhr, textStatus) {
								layer.msg('服务器错误，请稍后再试！', {
									icon: 5
								});
							}
						});
					}else {
						Realname.attr("is", "true");
					}
				}
			} else if (this.defaultValue === "Password") {
				if (this.value.length > 15) {
					layer.tips("不能超过15位！", this, {
						tips: [1, '#78BA32']
					});
				}
			}
		}
	});

	confirm.click(function () {
		username.blur();
		password.blur();
		Realname.blur();
		if (username.val() === "User name") {
			layer.tips("用户名不能为空！", username, {
				tips: [1, '#78BA32']
			});
		} else {
			if (username.val().length > 15) {
				layer.tips("不能超过15位！", username, {
					tips: [1, '#78BA32']
				});
			} else {
				if (password.val() === "Password") {
					layer.tips("密码不能为空！", password, {
						tips: [1, '#78BA32']
					});
				} else {
					if (password.val().length > 15) {
						layer.tips("不能超过15位！", password, {
							tips: [1, '#78BA32']
						});
					} else {
						if (username.attr("is") === "false") {
							layer.tips("该用户已存在，请更改用户名！", username, {
								tips: [1, '#78BA32']
							});
						} else {
							if (Realname.attr("is") === "false") {
								layer.tips("该HR已存在，请更改HR！", Realname, {
									tips: [1, '#78BA32']
								});
							} else {
								if (dbo===1) {
									$.ajax({
										url: 'admin_saveHR.action',
										type: 'post',
										data: {
											"username": username.val(),
											"password": password.val(),
											"Realname": Realname.val(),
										},
										timeout: 5000,
										dataType: 'text',
										success: function (data, textStatus, jqXHR) {
											if (data == 1) {
												layer.msg('操作成功！', {
													icon: 6,
													shade: 0.3
												});
												username.val("User name");
												password.val("Password");
												 Realname.val("Real name");
											} else {
												layer.msg('操作失败！', {
													icon: 5,
													shade: 0.3
												});
											}
										},
										error: function (xhr, textStatus) {
											layer.msg('服务器错误，请稍后再试！', {
												icon: 5,
												shade: 0.3
											});
										}
									});
									layer.closeAll();
								}else if(dbo===0) {
									$.ajax({
										url: 'admin_updateHR.action',
										type: 'post',
										data: {
											"updateId":xiugaiID,
											"username": username.val(),
											"password": password.val(),
											"Realname": Realname.val(),
										},
										timeout: 5000,
										dataType: 'text',
										success: function (data, textStatus, jqXHR) {
											if (data == 1) {
												layer.msg('操作成功！', {
													icon: 6,
													shade: 0.3
												});
											} else {
												layer.msg('操作失败！', {
													icon: 5,
													shade: 0.3
												});
											}
										},
										error: function (xhr, textStatus) {
											layer.msg('服务器错误，请稍后再试！', {
												icon: 5,
												shade: 0.3
											});
										}
									});
									layer.closeAll();
								}
							}
						}
					}
				}
			}
		}
	});

	back.click(function () {
		layer.closeAll();
	});

	//添加HR提示框dbo 1
	add_hr.click(function () {
		layer.open({
			type: 1,
			title: false,
			closeBtn: 0,
			area: ['360px', '264px'],
			scrollbar: false,
			shadeClose: false,
			content: $('#reg'),
			success: function (layero) {
				dbo=1;
				$('#reg .login_head>h1').html("请添加HR信息！");
			},
			end: function () {
				dbo=999;
				$('#reg').css({
					"display": "none"
				});
			}
		});
	});
	//修改HR信息
	$("#main2 #con2 #center>table").on("click", "tr td i:nth-child(1)", function () {
		var temp=$(this);
		xiugaiID=$(this).attr("deleteId");
		username.css({
			"color": "#000"
		}).val($(this).closest("tr").find("td").eq(2).html());
		password.css({
			"color": "#000"
		}).val($(this).closest("tr").find("td").eq(3).html());
		Realname.css({
			"color": "#000"
		}).val($(this).closest("tr").find("td").eq(1).html());
		layer.open({
			type: 1,
			title: false,
			closeBtn: 0,
			area: ['360px', '264px'],
			scrollbar: false,
			shadeClose: false,
			content: $('#reg'),
			end: function () {
				dbo=999;
				temp.closest("tr").find("td").eq(2).html(username.val());
				temp.closest("tr").find("td").eq(3).html(password.val());
				temp.closest("tr").find("td").eq(1).html(Realname.val());
				$('#reg').css({
					"display": "none"
				});
				username.css({
					"color": "#999"
				}).val("User name");
				password.css({
					"color": "#999"
				}).val("Password");
				Realname.css({
					"color": "#999"
				}).val("Real name");
			},
			success: function (layero) {
				dbo=0;
				$('#reg .login_head>h1').html("请编辑HR信息！");
			},
		});
	});

	//删除HR信息
	$("#main2 #con2 #center>table").on("click", "tr td i:nth-child(2)", function () {
		var temp=$(this);
		$.ajax({
			url: 'admin_deleteHR.action',
			type: 'post',
			data: {
				"deleteId": $(this).attr("deleteId"),
			},
			timeout: 5000,
			dataType: 'text',
			success: function (data, textStatus, jqXHR) {
				if (data!= 1) {
					layer.msg('删除失败！', {
						icon: 6,
						shade: 0.3
					});
				} else {
					temp.closest("tr").remove();
				}
			},
			error: function (xhr, textStatus) {
				layer.msg('服务器错误，请稍后再试！', {
					icon: 5,
					shade: 0.3
				});
			}
		});
	});
	
	var tableHead=$("#center");
	var tableFoot=$("#tablefoot");
	var appendCon=$("#center>table>tbody");
	var realName=$("#con2 #concon>input");
	var uppage=$("#tablefoot #uppage");
	var downpage=$("#tablefoot #downpage");
	var sumpage=$("#tablefoot #sumpage");
	var sumrow=$("#tablefoot #sumrow");
	var nowpage=$("#tablefoot #nowpage>input");
	var hrefpag=$("#tablefoot #hrefpag");
	
	//HR搜索
	$("#con2 #sou").click(function(){
		//alert(realName.val());
		$.ajax({
			url: 'admin_selectHR.action',
			type: 'post',
			data: {
				"Realname": realName.val().trim(),
				"nowPage":0,
			},
			timeout: 5000,
			dataType: 'json',
			success: function (data, textStatus, jqXHR) {
				if(data[0].allrow==0){
					layer.msg('您查找的信息不存在！');
				}else {
					tableHead.stop().fadeOut(100);
					tableFoot.stop().fadeOut(100);
					realName.attr("name",data[0].realName);
					uppage.attr("is",data[0].isup);
					downpage.attr("is",data[0].isdown);
					sumpage.html("共"+data[0].allpage+"页");
					sumpage.attr("sumpage",data[0].allpage);
					sumrow.html("共"+data[0].allrow+"条记录");
					nowpage.val(data[0].nowpage+1);
					tableHead.attr("pages",data[0].nowpage+1);
					appendCon.find("tr").remove();
					$.each(data[0].pagelist,function(index,value) {
						appendCon.append('<tr><td>'+(data[0].nowpage*10+1+index)+'</td><td>'+value.realname+'</td><td>'+value.username+'</td><td>'+value.userpassword+'</td><td><i class="fa fa-pencil" deleteId="'+value.id+'"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-minus-square" deleteId="'+value.id+'"></i></td></tr>');	
					})
					src_json=data;
					tableHead.stop().fadeIn(100);
					tableFoot.stop().fadeIn(100);
				}	
			},
			error: function (xhr, textStatus) {
				layer.msg('服务器错误，请稍后再试！', {
					icon: 5,
					shade: 0.3
				});
			}
		});
	});
	
	//点击上一页
	uppage.click(function(){
		if ($(this).attr("is")==="true") {
			$.ajax({
				url : 'admin_selectHR.action',
				type : 'POST',
				data : {
					"Realname": realName.val().trim(),
					"nowPage":tableHead.attr("pages")-2,
				},
				timeout : 5000, 
				dataType : 'json', 
				success : function(data, textStatus, jqXHR) {
					if(data==110){
						layer.msg('检测到异常访问，3秒后跳转到登陆页！', {icon:5,shade: 0.3});
						setTimeout(function(){
								location.href = $("base").attr("href")+"teacher_login.jsp";
						}, 3000);
					}else if(data[0].allrow==0){
						layer.msg('您查找的信息不存在！');
					}else {
						tableHead.stop().fadeOut(100);
						tableFoot.stop().fadeOut(100);
						realName.attr("name",data[0].realName);
						uppage.attr("is",data[0].isup);
						downpage.attr("is",data[0].isdown);
						sumpage.html("共"+data[0].allpage+"页");
						sumpage.attr("sumpage",data[0].allpage);
						sumrow.html("共"+data[0].allrow+"条记录");
						nowpage.val(data[0].nowpage+1);
						tableHead.attr("pages",data[0].nowpage+1);
						appendCon.find("tr").remove();
						$.each(data[0].pagelist,function(index,value) {
							appendCon.append('<tr><td>'+(data[0].nowpage*10+1+index)+'</td><td>'+value.realname+'</td><td>'+value.username+'</td><td>'+value.userpassword+'</td><td><i class="fa fa-pencil" deleteId="'+value.id+'"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-minus-square" deleteId="'+value.id+'"></i></td></tr>');	
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
	downpage.click(function(){
		if ($(this).attr("is")==="true") {
			$.ajax({
				url : 'admin_selectHR.action',
				type : 'POST',
				data : {
					"Realname": realName.val().trim(),
					"nowPage":tableHead.attr("pages"),
				},
				timeout : 5000, 
				dataType : 'json', 
				success : function(data, textStatus, jqXHR) {
					if(data==110){
						layer.msg('检测到异常访问，3秒后跳转到登陆页！', {icon:5,shade: 0.3});
						setTimeout(function(){
								location.href = $("base").attr("href")+"teacher_login.jsp";
						}, 3000);
					}else if(data[0].allrow==0){
						layer.msg('您查找的信息不存在！');
					}else {
						tableHead.stop().fadeOut(100);
						tableFoot.stop().fadeOut(100);
						realName.attr("name",data[0].realName);
						uppage.attr("is",data[0].isup);
						downpage.attr("is",data[0].isdown);
						sumpage.html("共"+data[0].allpage+"页");
						sumpage.attr("sumpage",data[0].allpage);
						sumrow.html("共"+data[0].allrow+"条记录");
						nowpage.val(data[0].nowpage+1);
						tableHead.attr("pages",data[0].nowpage+1);
						appendCon.find("tr").remove();
						$.each(data[0].pagelist,function(index,value) {
							appendCon.append('<tr><td>'+(data[0].nowpage*10+1+index)+'</td><td>'+value.realname+'</td><td>'+value.username+'</td><td>'+value.userpassword+'</td><td><i class="fa fa-pencil" deleteId="'+value.id+'"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-minus-square" deleteId="'+value.id+'"></i></td></tr>');	
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
	hrefpag.click(function(){
		if (Number($.trim(nowpage.val()))<=Number(sumpage.attr("sumpage"))&&Number($.trim(nowpage.val()))>0) {
			$.ajax({
				url : 'admin_selectHR.action',
				type : 'POST',
				data : {
					"Realname": realName.val().trim(),
					"nowPage":Number($.trim(nowpage.val()))-1,
				},
				timeout : 5000, 
				dataType : 'json', 
				success : function(data, textStatus, jqXHR) {
					if(data==110){
						layer.msg('检测到异常访问，3秒后跳转到登陆页！', {icon:5,shade: 0.3});
						setTimeout(function(){
								location.href = $("base").attr("href")+"teacher_login.jsp";
						}, 3000);
					}else if(data[0].allrow==0){
						layer.msg('您查找的信息不存在！');
					}else {
						tableHead.stop().fadeOut(100);
						tableHead.stop().fadeOut(100);
						Realname.attr("name",data[0].Realname);
						uppage.attr("is",data[0].isup);
						downpage.attr("is",data[0].isdown);
						sumpage.html("共"+data[0].allpage+"页");
						sumrow.html("共"+data[0].allrow+"条记录");
						sumpage.attr("sumpage",data[0].allpage);
						nowpage.val(data[0].nowpage+1);
						tableHead.attr("pages",data[0].nowpage+1);
						appendCon.find("tr").remove();
						$.each(data[0].pagelist,function(index,value) {
							appendCon.append('<tr><td>'+(data[0].nowpage*10+1+index)+'</td><td>'+value.realname+'</td><td>'+value.username+'</td><td>'+value.userpassword+'</td><td><i class="fa fa-pencil" deleteId="'+value.id+'"></i>&nbsp;&nbsp;&nbsp;&nbsp;<i class="fa fa-minus-square" deleteId="'+value.id+'"></i></td></tr>');	
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

});
