function isName(obj){
	$.ajax({
		url:"admin_isEmpName.action",
		type:"POST",
		timeout:5000,
		data:{
			"empName":obj.val().trim()
		},
		dataType:"text",
		success:function(data){
			if(data==="1"){
				layer.msg('该用户已经存在！', {icon : 5,shade: 0.3});
				obj.attr("is","isEmpName");
			}else {
				obj.attr("is","true");
			}
		},
		error : function(xhr, textStatus) {
			layer.msg('服务器错误，请稍后再试！', {
				icon : 5
			});
		}
	});
}

var PcWidth;
var PcHeight;
var _timer = {};
var nations = ["汉族", "蒙古族", "回族", "藏族", "维吾尔族", "苗族", "彝族", "壮族", "布依族", "朝鲜族", "满族", "侗族", "瑶族", "白族", "土家族", "哈尼族", "哈萨克族", "傣族", "黎族", "傈僳族", "佤族", "畲族", "高山族", "拉祜族", "水族", "东乡族", "纳西族", "景颇族", "柯尔克孜族", "土族", "达斡尔族", "仫佬族", "羌族", "布朗族", "撒拉族", "毛南族", "仡佬族", "锡伯族", "阿昌族", "普米族", "塔吉克族", "怒族", "乌孜别克族", "俄罗斯族", "鄂温克族", "德昂族", "保安族", "裕固族", "京族", "塔塔尔族", "独龙族", "鄂伦春族", "赫哲族", "门巴族", "珞巴族", "基诺族"];

//获取屏幕的宽和高
$(window).resize(function () {
	"use strict";
	PcWidth = $(this).width();
	PcHeight = $(this).height();
});

$(document).ready(function () {
	$(window).trigger("resize");
	var nav_left = $("#main1 #src-left-nav ul li");
	var saveToTable = $("#main1 #con #dowebok .section:nth-child(6)>div .table_con>.input>ul>li>p");

	//动态生成年龄
	for (var i = 0; i < 100; i++) {
		$("#age").append("<option>" + i + "</option>");
	}
	//动态生成民族
	for (var j = 0; j < nations.length; j++) {
		$("#nations").append("<option>" + nations[j] + "</option>");
	}
	//动态生成HR下拉框
	if (parent.HR_R === "1") {
		$.ajax({
			url: 'admin_getAllHrName.action',
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
						$("#hr_Select").append("<option value='" + value.realname + "'>" + value.realname + "</option>");
					});
				}
			},
			error: function (xhr, textStatus) {
				layer.msg('服务器错误，请稍后再试！', {
					icon: 5
				});
			}
		});
	} else {
		$("#hr_Select").append("<option value='" + parent.HR_NAME + "'>" + parent.HR_NAME + "</option>").val(parent.HR_NAME).attr("disabled", "disabled");
	}

	//动态生成公司名称
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
				$("#gs").append("<option com='" + value.name + "' value='" + value.id + "'>" + value.name + "</option>");
			});
			}
		},
		error: function (xhr, textStatus) {
			layer.msg('服务器错误，请稍后再试！', {
				icon: 5
			});
		}
	});
	//动态生成部门
	$("#gs").change(function () {
		if ($(this).val() != "defult") {
			$.ajax({
				url: 'admin_getAllJob.action',
				type: 'POST',
				data: {
					"comName": $(this).val()
				},
				timeout: 5000,
				dataType: 'json',
				success: function (data, textStatus, jqXHR) {
					if(data==110){
						layer.msg('检测到异常访问，3秒后跳转到登陆页！', {icon: 5,shade: 0.3});
						setTimeout(function(){
							location.href = $("base").attr("href")+"login.jsp";
						}, 3000);
					}else {
					$("#bm").children().remove();
					$("#bm").append('<option value="defult">--请选择部门--</option>');
					$.each(data, function (index, value) {
						$("#bm").append("<option value='" + value.job + "'>" + value.job + "</option>");
					});
					}
				},
				error: function (xhr, textStatus) {
					layer.msg('服务器错误，请稍后再试！', {
						icon: 5
					});
				}
			});
		} else {
			$("#bm").children().remove();
			$("#bm").append('<option value="defult">--请选择部门--</option>');
		}
	});

	//防止事件在短时间内再次执行
	function delay_till_last(id, fn, wait) {
		if (_timer[id]) {
			window.clearTimeout(_timer[id]);
			delete _timer[id];
		}

		return _timer[id] = window.setTimeout(function () {
			fn();
			delete _timer[id];
		}, wait);
	}

	//页面平滑
	nav_left.click(function () {
		$('#main1 #con #dowebok').css({
			"-webkit-transform": "translateY(" + $(this).index() * -100 + "%)"
		});
	});

	//按键平滑
	$(document).keydown(function (event) {
		$(window).trigger("resize");
		var translateY;
		var value;
		if (event.keyCode === 38) {
			delay_till_last('mykeydown', function () {
				translateY = $('#main1 #con #dowebok').css("-webkit-transform").match(/\d+/g)[5];
				value = parseInt(translateY) / parseInt(PcHeight) * 100;
				if (value != 0) {
					$('#main1 #con #dowebok').css({
						"-webkit-transform": "translateY(" + (-value + 100) + "%)"
					});
				}
			}, 500);
		} else if (event.keyCode === 40) {
			delay_till_last('mykeydown', function () {
				translateY = $('#main1 #con #dowebok').css("-webkit-transform").match(/\d+/g)[5];
				value = parseInt(translateY) / parseInt(PcHeight) * 100;
				if (value != 600) {
					$('#main1 #con #dowebok').css({
						"-webkit-transform": "translateY(" + (-value - 100) + "%)"
					});
				}
			}, 500);
		}
	});

	//鼠标滚轮平滑
	$('#main1 #con #dowebok').mousewheel(function (event, delta) {
		$(window).trigger("resize");
		var translateY;
		var value;
		if (delta === 1) {
			delay_till_last('mykeydown', function () {
				translateY = $('#main1 #con #dowebok').css("-webkit-transform").match(/\d+/g)[5];
				value = parseInt(translateY) / parseInt(PcHeight) * 100;
				if (value != 0) {
					$('#main1 #con #dowebok').css({
						"-webkit-transform": "translateY(" + (-value + 100) + "%)"
					});
				}
			}, 500);
		} else {
			delay_till_last('mykeydown', function () {
				translateY = $('#main1 #con #dowebok').css("-webkit-transform").match(/\d+/g)[5];
				value = parseInt(translateY) / parseInt(PcHeight) * 100;
				if (value != 600) {
					$('#main1 #con #dowebok').css({
						"-webkit-transform": "translateY(" + (-value - 100) + "%)"
					});
				}
			}, 500);
		}
	});

	//保存服务客户到列表中
	saveToTable.click(function () {
		var gs = $("#gs").find("option[value='" + $("#gs").val() + "']").text();
		var bm = $("#bm").val();
		var gsfirstdate = $("#gsfirstdate").val();
		var gsenddate = $("#gsenddate").val();
		var dz = $("#dz").val();
		var zj = $("#zj").val();
		$("#main1 #con #dowebok .section:nth-child(6)>div .table_con>table>tbody").append("<tr><td>" + gs + "</td><td>" + bm + "</td><td>" + gsfirstdate + "</td><td>" + gsenddate + "</td><td>" + dz + "</td><td>" + zj + "</td><td><i class='fa fa-minus-square'></i></td></tr>");
		$("#gs").val("defult");
		$("#bm").val("defult");
		$("#gsfirstdate").val("");
		$("#gsenddate").val("");
		$("#dz").val("");
		$("#zj").val("");
	});

	//从列表中移出指定的服务客户
	$("#main1 #con #dowebok .section:nth-child(6)>div .table_con>table>tbody").on("click", "tr>td:nth-child(7)", function () {
		$(this).closest("tr").remove();
	});

	//防止页面下滑冒泡
	$("#main1 #con #dowebok .section:nth-child(6)>div .table_con").mouseenter(function () {
		$('#main1 #con #dowebok').unmousewheel();
	}).mouseleave(function () {
		$('#main1 #con #dowebok').mousewheel(function (event, delta) {
			$(window).trigger("resize");
			var translateY;
			var value;
			if (delta === 1) {
				delay_till_last('mykeydown', function () {
					translateY = $('#main1 #con #dowebok').css("-webkit-transform").match(/\d+/g)[5];
					value = parseInt(translateY) / parseInt(PcHeight) * 100;
					if (value != 0) {
						$('#main1 #con #dowebok').css({
							"-webkit-transform": "translateY(" + (-value + 100) + "%)"
						});
					}
				}, 500);
			} else {
				delay_till_last('mykeydown', function () {
					translateY = $('#main1 #con #dowebok').css("-webkit-transform").match(/\d+/g)[5];
					value = parseInt(translateY) / parseInt(PcHeight) * 100;
					if (value != 600) {
						$('#main1 #con #dowebok').css({
							"-webkit-transform": "translateY(" + (-value - 100) + "%)"
						});
					}
				}, 500);
			}
		});
	});

	//添加上传文件支持
	layui.use('upload', function () {
		layui.upload({
			url: 'upload.action',
			elem: '#enclosure',
			method: 'post', //上传接口的http类型
			ext: 'rar|RAR',
			unwrap:true,
			success: function (res) {
				//JSON.stringify(res)
				var DBFileURLName = res.DBFileURLName;
				ajaxEmp(DBFileURLName);
			}
		});
	});

	//确认提交录入信息
	$("#qyInput").click(function () {
		var isOpen = true;
		$.each($("input[is='nonull']"), function (index, value) {
			if ($(value).val().trim() === "") {
				layer.msg('请补全录入信息！', {
					icon: 5,
					shade: 0.3
				});
				isOpen = false;
				return;
			}
		});
		$.each($("select[is='nonull']"), function (index, value) {
			if ($(value).val() === "defult") {
				layer.msg('请补全录入信息！', {
					icon: 5,
					shade: 0.3
				});
				isOpen = false;
				return;
			}
		});
		if (isOpen) {
			if ($("input[name='file']").val().trim() === "") {
				ajaxEmp("");
			} else {
				$("input[name='file']").parent("form").attr("action", "upload.action?EmpName=" + $("#name").val()).submit();
			}
		}
	});

	//员工信息体函数
	function ajaxEmp(DBFileURLName) {
		var table = $("#main1 #con #dowebok .section:nth-child(6)>div .table_con>table>tbody");
		var table_value = ["id", "company", "job", "startDate", "endDate", "salary", "singleGold"];
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
			url: 'admin_saveEmp.action',
			type: 'POST',
			data: {
				"contactdate": $("#contactdate").val(),
				"hr_Select": $("#hr_Select").val(),
				"fastentrytime": $("#fastentrytime").val(),
				"td": $('input:radio[name="td"]:checked').val(),
				"name": $("#name").val(),
				"sex": $('input:radio[name="sex"]:checked').val(),
				"idno": $("#idno").val(),
				"age": $("#age").val(),
				"nations": $("#nations").val(),
				"recruitmenttype": $("#recruitmenttype").val(),
				"skill": $("#skill").val(),
				"language": $("#language").val(),
				"languageability": $("#languageability").val(),
				"hopesalary": $("#hopesalary").val(),
				"workplace": $("#workplace1").val() + "-" + $("#workplace2").val() + "-" + $("#workplace3").val(),
				"graduatetime": $("#graduatetime").val(),
				"graduateschool": $("#graduateschool").val(),
				"major": $("#major").val(),
				"education": $("#education").val(),
				"telphone": $("#telphone").val(),
				"email": $("#email").val(),
				"othercontact": $("#othercontact").val(),
				"tdli": $('input:radio[name="tdli"]:checked').val(),
				"entrytime": $("#entrytime").val(),
				"contractperiod": $("#contractperiod").val(),
				"uesperiod": $("#uesperiod").val(),
				"contractrenewal": $("#contractrenewal").val(),
				"welfare": $("#welfare").val(),
				"departuretime": $("#departuretime").val(),
				"departurereasons": $("#departurereasons").val(),
				"table_json": table_json,
				"enclosure": DBFileURLName
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
					if (data === "1") {
						$.each($("input[is='nonull']"), function (index, value) {
							$(value).val("");
						});
						$.each($("select[is='nonull']"), function (index, value) {
							$(value).val("defult");
						});
						layer.msg('录入成功！', {
							icon: 6,
							shade: 0.3
						});
					} else {
						layer.msg('录入失败！', {
							icon: 5,
							shade: 0.3
						});
					}
				}
			},
			error: function (xhr, textStatus) {
				layer.msg('服务器错误，请稍后再试！', {
					icon: 5,
					shade: 0.3
				});
			}
		});
	}

});