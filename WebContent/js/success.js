//全局变量，为子页提供
var HR_NAME;
var HR_R;
$(document).ready(function () {
	//初始化全局变量
	HR_NAME = $("#src-nav").attr("hr_name");
	HR_R=$("#src-nav").attr("login_type");
	// 上导航区
	var home = $("#src-nav>h1"); // 主页按钮
	var empadd = $("#src-nav>.logout0"); // 员工录入
	var empgl = $("#src-nav>.logout1"); // 员工管理
	var empcs = $("#src-nav>.logout2"); // 系统参数
	var logOut = $("#src-nav>.logout3"); // 注销
	// 主页按钮
	var empadd2 = $("#con #index>ul:nth-child(1)>li:nth-child(1)"); // 员工录入
	var empgl2 = $("#con #index>ul:nth-child(1)>li:nth-child(2)"); // 员工管理
	var empcs2 = $("#con #index>ul:nth-child(1)>li:nth-child(3)"); // 系统参数
	// 内容装载区
	var con_main = $("#con #con-main"); // 外部加载区
	var index = $("#con #index"); // 主页显示内容

	// ************************************************上导航区************
	// 点击主页按钮
	home.click(function () {
		empadd.fadeOut(200, function () {
			empgl.fadeOut(200, function () {
				if (empcs.css("display") != "none") {
					empcs.fadeOut(200);
				}
				con_main.fadeOut(500, function () {
					index.fadeIn(500);
				});
			});
		});
	});
	// 点击员工录入按钮
	empadd.click(function () {
		con_main.fadeOut(500, function () {
			con_main.load("jsp/AddEmp.jsp", function () {
				con_main.fadeIn(500);
			});
		});
	});
	//点击员工管理按钮
	empgl.click(function () {
		con_main.fadeOut(500, function () {
			con_main.load("jsp/EmpManager.jsp", function () {
				con_main.fadeIn(500);
			});
		});
	});
	// 点击系统参数按钮
	empcs.click(function () {
		con_main.fadeOut(500, function () {
			con_main.load("jsp/EditSystem.jsp", function () {
				con_main.fadeIn(500);
			});
		});
	});
	// 点击注销
	logOut.click(function () {
		location.href = $("base").attr("href") + "login.jsp";
	});
	// ***************************************************************内容导航区**********
	// 点击主页员工录入
	empadd2.click(function () {
		index.fadeOut(500, function () {
			con_main.load("jsp/AddEmp.jsp", function () {
				con_main.fadeIn(500, function () {
					empadd.fadeIn(200, function () {
						empgl.fadeIn(200, function () {
							empcs.fadeIn(200);
						});
					});
				});
			});
		});
	});
	// 点击主页员工管理
	empgl2.click(function () {
		index.fadeOut(500, function () {
			con_main.load("jsp/EmpManager.jsp", function () {
				con_main.fadeIn(500, function () {
					empadd.fadeIn(200, function () {
						empgl.fadeIn(200, function () {
							empcs.fadeIn(200);
						});
					});
				});
			});
		});
	});
	// 点击主页系统参数
	empcs2.click(function () {
		index.fadeOut(500, function () {
			con_main.load("jsp/EditSystem.jsp", function () {
				con_main.fadeIn(500, function () {
					empadd.fadeIn(200, function () {
						empgl.fadeIn(200, function () {
							empcs.fadeIn(200);
						});
					});
				});
			});
		});
	});

});