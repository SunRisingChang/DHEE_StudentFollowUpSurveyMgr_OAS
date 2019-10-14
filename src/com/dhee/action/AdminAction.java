package com.dhee.action;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.dhee.daofactory.DaoFactory;
import com.dhee.tools.EmpUserPage;
import com.dhee.tools.ExportExcel;
import com.dhee.tools.HrUserPage;
import com.dhee.vo.AdminUser;
import com.dhee.vo.Compary;
import com.dhee.vo.Customer;
import com.dhee.vo.Emp;
import com.dhee.vo.HrUser;
import com.dhee.vo.Jobs;
import com.dhee.vo.Show;
import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;

public class AdminAction extends SuperAction {
	private DaoFactory daoFactory = new DaoFactory();

	// 查看是否有超级用户名
	public void isAdminName() {
		try {
			AdminUser au = new AdminUser();
			au.setUsername(request.getParameter("adminName"));
			AdminUser au1 = daoFactory.getAdminImpl().selectAdminUser(au);
			if (au1 == null) {
				response.getWriter().print("0");
			} else {
				response.getWriter().print("1");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("isAdminName方法出错！");
			e.printStackTrace();
		}
	}

	public void isUser() {
		try {
			AdminUser temp = new AdminUser();
			temp.setUsername(request.getParameter("adminName"));
			AdminUser au1 = daoFactory.getAdminImpl().selectAdminUser(temp);
			if (au1 == null) {
				response.getWriter().print("0");
			} else {
				if (request.getParameter("adminPassword").equals(au1.getUserpassword())) {
					session.setAttribute("nowName", au1.getUsername());
					session.setAttribute("type", "1");
					response.getWriter().print("1");
				} else {
					response.getWriter().print("0");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("isUser方法出错！");
			e.printStackTrace();
		}
	}

	// 获取全部的HR用于下拉框
	public void getAllHrName() {
		try {
			List<HrUser> list = daoFactory.getAdminImpl().getAllHr();
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().print(new JSONArray().fromObject(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("getAllHrName方法出错！");
			e.printStackTrace();
		}
	}

	public void selectComp() {
		String id = request.getParameter("id");
		List<Customer> list = new DaoFactory().getAdminImpl().Comparyid(id);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor() {
			private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
				return value == null ? "" : sd.format(value);
			}

			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				return null;
			}
		});
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().println(JSONArray.fromObject(list, jsonConfig).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// hr动态
	public void selecthr() {
		List<HrUser> hr = new DaoFactory().getAdminImpl().getAllHr();
		JSONArray jArray = JSONArray.fromObject(hr);
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().print(jArray);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 服务客户下拉表
	public void selectcompany() {
		List<Compary> company = new DaoFactory().getAdminImpl().getAllCompary();
		JSONArray jArray = JSONArray.fromObject(company);
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().print(jArray.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 删除员工信息
	public void deleteimp() {
		String deleted = request.getParameter("deleted");
		int i = new DaoFactory().getAdminImpl().deleteEmp(deleted);
		try {
			response.getWriter().print(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 详细信息
	public void xiangqing() {
		String id = request.getParameter("id");
		try {
			List<Object> list = new DaoFactory().getAdminImpl().selectEmp(id);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor() {
				private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

				public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
					return value == null ? "" : sd.format(value);
				}

				public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					return null;
				}
			});
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().println(JSONArray.fromObject(list, jsonConfig).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 以id对customer的查询
	public void selectcustomers() {
		String id = request.getParameter("id");
		String company = request.getParameter("company");
		List<Customer> list = new DaoFactory().getAdminImpl().selectcustomer(id, company);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor() {
			private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

			public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
				return value == null ? "" : sd.format(value);
			}

			public Object processArrayValue(Object value, JsonConfig jsonConfig) {
				return null;
			}
		});
		try {
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().println(JSONArray.fromObject(list, jsonConfig).toString());
			System.out.println(JSONArray.fromObject(list, jsonConfig).toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 修改信息操作
	public void alter() throws IOException {
		int q = 0;
		String id = request.getParameter("id");
		String contactdate = request.getParameter("contactdate");
		String hr = request.getParameter("hr");
		String fastentrytime = request.getParameter("fastentrytime");
		String post = request.getParameter("post");
		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String idno = request.getParameter("idno");
		String age = request.getParameter("age").equals("defult") ? "0" : request.getParameter("age");
		String nation = request.getParameter("nation");
		String recruitmenttype = request.getParameter("recruitmenttype");
		String skill = request.getParameter("skill");
		String language = request.getParameter("language");
		String languagelevel = request.getParameter("languagelevel");
		String hopesalary = request.getParameter("hopesalary").equals("") ? "0.0" : request.getParameter("hopesalary");
		String workplace = request.getParameter("workspace");
		String graduatetime = request.getParameter("graduatetime");
		String graduateschool = request.getParameter("graduateschool");
		String major = request.getParameter("major");
		String education = request.getParameter("eduation");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String other = request.getParameter("other");
		String entryedornot = request.getParameter("entryedornot");
		String entrytime = request.getParameter("entrytime");
		String contractperiod = request.getParameter("contractperiod");
		String uesperiod = request.getParameter("uesperiod");
		String contractrenewal = request.getParameter("contractrenewal");
		String welfare = request.getParameter("welfare");
		String departuretime = request.getParameter("departuretime");
		String departurereasons = request.getParameter("departurereasons");
		String jS = request.getParameter("table_json");
		String enclosure = request.getParameter("enclosure");
		Emp emp = new Emp();
		Customer customer = new Customer();
		try {
			emp.setId(Integer.parseInt(id));
			emp.setContactdate(new SimpleDateFormat("yyyy-MM-dd").parse(contactdate.equals("") ? "0001-01-01" : contactdate));
			emp.setHr(hr);
			emp.setFastentrytime(new SimpleDateFormat("yyyy-MM-dd").parse(fastentrytime.equals("") ? "0001-01-01" : fastentrytime));
			emp.setPost(post);
			emp.setName(name);
			emp.setSex(sex);
			emp.setIdno(idno);
			emp.setAge(Integer.parseInt(age));
			emp.setNation(nation);
			emp.setRecruitmenttype(recruitmenttype);
			emp.setSkill(skill);
			emp.setLanguage(language);
			emp.setWorkplace(workplace);
			emp.setLanguageability(languagelevel);
			emp.setHopesalary(Double.parseDouble(hopesalary));
			emp.setGraduatetime(new SimpleDateFormat("yyyy-MM-dd").parse(graduatetime.equals("") ? "0001-01-01" : graduatetime));
			emp.setGraduateschool(graduateschool);
			emp.setMajor(major);
			emp.setEducation(education);
			emp.setTelphone(tel);
			emp.setEmail(email);
			emp.setOthercontact(other);
			emp.setEntryedornot(entryedornot);
			emp.setContractperiod(new SimpleDateFormat("yyyy-MM-dd").parse(contractperiod.equals("") ? "0001-01-01" : contractperiod));
			emp.setEntrytime(new SimpleDateFormat("yyyy-MM-dd").parse(entrytime.equals("") ? "0001-01-01" : entrytime));
			emp.setUesperiod(uesperiod);
			emp.setContractrenewal(contractrenewal);
			emp.setWelfare(welfare);
			emp.setDeparturetime(departuretime);
			emp.setDeparturereasons(departurereasons);
			emp.setEnclosure(enclosure);

			JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(new String[] { "yyyy-MM-dd" }));
			if (!jS.equals("]")) {
				JSONArray jsonArray = JSONArray.fromObject(jS);
				List<Customer> list = (List<Customer>) jsonArray.toCollection(jsonArray, Customer.class);
				daoFactory.getAdminImpl().deleteCustomer(id);
				for (Customer c : list) {
					c.setEmpId(Integer.parseInt(id));
					daoFactory.getAdminImpl().updataCustomer(c);
				}
			}
			new DaoFactory().getAdminImpl().updateEmp(emp);
			response.getWriter().println(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 打印
	public void downloadfj() {
		try {
			String select_mz = request.getParameter("select_mz");
			String select_jsfx = request.getParameter("select_jsfx");
			String select_gzdd = request.getParameter("select_gzdd");
			Date select_rzsj = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("select_rzsj") == "" ? "0001-01-01" : request.getParameter("select_rzsj"));
			Date select_lzsj = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("select_lzsj") == "" ? "0001-01-01" : request.getParameter("select_lzsj"));
			String select_zplx = request.getParameter("select_zplx");
			String select_xl = request.getParameter("select_xl");
			String select_sfrz = request.getParameter("select_sfrz");
			String select_dgkh = request.getParameter("select_dgkh");

			EmpUserPage empUserPage2 = daoFactory.getAdminImpl().getShowAll(new EmpUserPage(0, 10, select_mz, select_jsfx, select_gzdd, select_rzsj, select_lzsj, select_zplx, select_xl, select_sfrz, select_dgkh));
			HSSFWorkbook wb = new ExportExcel().execute(empUserPage2);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-download");
			String filedisplay = "员工表 .xls";
			filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename=" + filedisplay);
			OutputStream out = response.getOutputStream();
			wb.write(out);
			out.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取公司的下拉框
	public void getAllCompany() {
		try {
			List<Compary> list = daoFactory.getAdminImpl().getAllCompary();
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().print(new JSONArray().fromObject(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("getAllCompany方法出错！");
			e.printStackTrace();
		}
	}

	// 获取搜索的公司部门
	public void getAllJob() {
		try {
			List<Jobs> list = daoFactory.getAdminImpl().getAllJobs(new Compary(Integer.parseInt(request.getParameter("comName")), ""));
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().print(new JSONArray().fromObject(list));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("getAllJob方法出错！");
			e.printStackTrace();
		}
	}

	// 员工的录入
	public void saveEmp() {
		try {
			Date contactdate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("contactdate") == "" ? "0001-01-01" : request.getParameter("contactdate"));
			String hr_Select = request.getParameter("hr_Select");
			Date fastentrytime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fastentrytime") == "" ? "0001-01-01" : request.getParameter("fastentrytime"));
			String td = request.getParameter("td");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String idno = request.getParameter("idno");
			int age = Integer.parseInt(request.getParameter("age").equals("defult") ? "0" : request.getParameter("age"));
			String nations = request.getParameter("nations");
			String recruitmenttype = request.getParameter("recruitmenttype");
			String skill = request.getParameter("skill");
			String language = request.getParameter("language");
			String languageability = request.getParameter("languageability");
			Double hopesalary = Double.parseDouble(request.getParameter("hopesalary").equals("") ? "0" : request.getParameter("hopesalary"));
			String workplace = request.getParameter("workplace");
			Date graduatetime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("graduatetime") == "" ? "0001-01-01" : request.getParameter("graduatetime"));
			String graduateschool = request.getParameter("graduateschool");
			String major = request.getParameter("major");
			String education = request.getParameter("education");
			String telphone = request.getParameter("telphone");
			String email = request.getParameter("email");
			String othercontact = request.getParameter("othercontact");
			String tdli = request.getParameter("tdli");
			Date entrytime = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("entrytime") == "" ? "0001-01-01" : request.getParameter("entrytime"));
			Date contractperiod = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("contractperiod") == "" ? "0001-01-01" : request.getParameter("contractperiod"));
			String uesperiod = request.getParameter("uesperiod");
			String contractrenewal = request.getParameter("contractrenewal");
			String welfare = request.getParameter("welfare");
			String departuretime = request.getParameter("departuretime");
			String departurereasons = request.getParameter("departurereasons");
			String enclosure = request.getParameter("enclosure");

			String table_json = request.getParameter("table_json");
			Emp tEmp = new Emp(contactdate, hr_Select, fastentrytime, td, name, sex, idno, age, nations, recruitmenttype, skill, language, languageability, hopesalary, workplace, graduatetime, graduateschool, major, education, telphone, email, othercontact, tdli, entrytime, contractperiod, uesperiod, contractrenewal, welfare, departuretime, departurereasons, enclosure);
			daoFactory.getAdminImpl().saveEmp(tEmp);
			int maxID = daoFactory.getAdminImpl().getEmpMAX_ID();
			if (!table_json.equals("]")) {
				JSONArray jsonArray = JSONArray.fromObject(table_json);
				List<Customer> list = (List<Customer>) jsonArray.toCollection(jsonArray, Customer.class);
				for (Customer c : list) {
					c.setEmpId(maxID);
					daoFactory.getAdminImpl().saveCustomer(c);
				}
			}
			response.getWriter().print("1");
		} catch (Exception e) {
			System.out.println("saveEmp方法出错！");
			e.printStackTrace();
		}
	}

	// 删除公司名称
	public void deleteCompany() {
		try {
			daoFactory.getAdminImpl().deleteCompary(new Compary(Integer.parseInt(request.getParameter("comId")), ""));
			response.getWriter().print("1");
		} catch (Exception e) {
			System.out.println("deleteCompany方法出错！");
			e.printStackTrace();
		}
	}

	// 添加公司名称
	public void addCompany() {
		try {
			daoFactory.getAdminImpl().saveCompary(new Compary(0, request.getParameter("CompanyName")));
			response.getWriter().print(daoFactory.getAdminImpl().getComparyMAX_ID());
		} catch (Exception e) {
			System.out.println("addCompany方法出错！");
			e.printStackTrace();
		}
	}

	// 修改公司名称
	public void editCompany() {
		try {
			daoFactory.getAdminImpl().updateCompary(new Compary(Integer.parseInt(request.getParameter("comId")), request.getParameter("comName")));
			response.getWriter().print("1");
		} catch (Exception exception) {
			System.out.println("editCompany方法出错！");
			exception.printStackTrace();
		}
	}

	// 查看公司包含的部门信息
	public void showCompanyJobs() {
		try {
			List<Jobs> list = daoFactory.getAdminImpl().getAllJobs(new Compary(Integer.parseInt(request.getParameter("comId")), ""));
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().print(JSONArray.fromObject(list).toString());
		} catch (Exception e) {
			System.out.println("showCompanyJobs方法出错！");
			e.printStackTrace();
		}
	}

	// 删除公司部门
	public void deleteJob() {
		try {
			String CompanyId = request.getParameter("CompanyId");
			String jobName = request.getParameter("jobName");
			Jobs jobs = new Jobs();
			jobs.setComid(Integer.parseInt(CompanyId));
			jobs.setJob(jobName);
			daoFactory.getAdminImpl().deleteJobs(jobs);
			response.getWriter().print("1");
		} catch (Exception e) {
			System.out.println("deleteJob方法出错！");
			e.printStackTrace();
		}
	}

	// 修改部门
	public void editJob() {
		try {
			String CompanyId = request.getParameter("CompanyId");
			String oldValue = request.getParameter("oldValue");
			String jobName = request.getParameter("jobName");
			daoFactory.getAdminImpl().deleteJobs(new Jobs(0, oldValue));
			daoFactory.getAdminImpl().savejobs(new Jobs(Integer.parseInt(CompanyId), jobName));
			response.getWriter().print("1");
		} catch (Exception e) {
			System.out.println("editJob方法出错！");
			e.printStackTrace();
		}
	}

	// 添加job
	public void addJob() {
		try {
			daoFactory.getAdminImpl().savejobs(new Jobs(Integer.parseInt(request.getParameter("comId")), request.getParameter("jobName")));
			response.getWriter().print("1");
		} catch (Exception e) {
			System.out.println("addJob方法出错！");
			e.printStackTrace();
		}
	}

	// 是否有该HR用户名
	public void isHrName() {
		try {
			List<HrUser> list = daoFactory.getAdminImpl().getAllHr();
			for (HrUser h : list) {
				if (h.getUsername().equals(request.getParameter("hrName"))) {
					response.getWriter().print("1");
					return;
				}
			}
			response.getWriter().print("0");
		} catch (Exception e) {
			System.out.println("isHrName方法出错！");
			// TODO: handle exception
		}
	}

	// 是否有该HR的真实姓名
	public void isHrRealName() {
		try {
			List<HrUser> list = daoFactory.getAdminImpl().getAllHr();
			for (HrUser h : list) {
				if (h.getRealname().equals(request.getParameter("hrRealName"))) {
					response.getWriter().print("1");
					return;
				}
			}
			response.getWriter().print("0");
		} catch (Exception e) {
			System.out.println("isHrRealName方法出错！");
			// TODO: handle exception
		}
	}

	// 保存HR用户
	public void saveHR() {
		try {
			HrUser hrUser = new HrUser(0, request.getParameter("username"), request.getParameter("password"), request.getParameter("Realname"));
			daoFactory.getAdminImpl().addHrUser(hrUser);
			response.getWriter().print("1");
		} catch (Exception e) {
			System.out.println("saveHR方法出错！");
			e.printStackTrace();
		}
	}

	// 通过HR真实姓名搜索HR
	public void selectHR() {
		try {
			String tempStr = request.getParameter("Realname");
			HrUserPage page;
			if (tempStr.equals("")) {
				page = daoFactory.getAdminImpl().selectHrUser(new HrUserPage(Integer.parseInt(request.getParameter("nowPage")), 10, "%"));
			} else {
				page = daoFactory.getAdminImpl().selectHrUser(new HrUserPage(Integer.parseInt(request.getParameter("nowPage")), 10, tempStr));
			}
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().print(JSONArray.fromObject(page).toString());
		} catch (Exception e) {
			System.out.println("selectHR方法出错！");
			e.printStackTrace();
		}
	}

	// 删除hr
	public void deleteHR() {
		try {
			daoFactory.getAdminImpl().deletHrUser(new HrUser(Integer.parseInt(request.getParameter("deleteId")), "", "", ""));
			response.getWriter().print("1");
		} catch (Exception e) {
			System.out.println("deleteHR方法出错！");
			e.printStackTrace();
		}
	}

	// 更新HR信息
	public void updateHR() {
		try {
			daoFactory.getAdminImpl().updateHrUser(new HrUser(Integer.parseInt(request.getParameter("updateId")), request.getParameter("username"), request.getParameter("password"), request.getParameter("Realname")));
			response.getWriter().print("1");
		} catch (Exception e) {
			System.out.println("updateHR方法出错！");
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void isEmpName() {
		try {
			String empName = request.getParameter("empName");
			if (daoFactory.getAdminImpl().isEmpName(empName)) {
				response.getWriter().print("1");
			} else {
				response.getWriter().print("0");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void selectShow() {
		try {
			String select_mz = request.getParameter("select_mz");
			String select_jsfx = request.getParameter("select_jsfx");
			String select_gzdd = request.getParameter("select_gzdd");
			Date select_rzsj = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("select_rzsj") == "" ? "0001-01-01" : request.getParameter("select_rzsj"));
			Date select_lzsj = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("select_lzsj") == "" ? "0001-01-01" : request.getParameter("select_lzsj"));
			String select_zplx = request.getParameter("select_zplx");
			String select_xl = request.getParameter("select_xl");
			String select_sfrz = request.getParameter("select_sfrz");
			String select_dgkh = request.getParameter("select_dgkh");
			int nowPage = Integer.parseInt(request.getParameter("nowPage").equals("") ? "0" : request.getParameter("nowPage"));

			EmpUserPage list = daoFactory.getAdminImpl().getShow(new EmpUserPage(nowPage, 10, select_mz, select_jsfx, select_gzdd, select_rzsj, select_lzsj, select_zplx, select_xl, select_sfrz, select_dgkh));
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new JsonValueProcessor() {
				private SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

				public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
					return value == null ? "" : sd.format(value);
				}

				public Object processArrayValue(Object value, JsonConfig jsonConfig) {
					return null;
				}
			});
			response.setHeader("Content-type", "text/html;charset=UTF-8");
			response.getWriter().println(JSONArray.fromObject(list, jsonConfig).toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
