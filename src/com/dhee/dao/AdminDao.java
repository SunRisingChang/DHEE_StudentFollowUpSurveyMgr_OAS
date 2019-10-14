package com.dhee.dao;

import java.util.List;
import com.dhee.tools.EmpUserPage;
import com.dhee.tools.HrUserPage;
import com.dhee.vo.AdminUser;
import com.dhee.vo.Compary;
import com.dhee.vo.Customer;
import com.dhee.vo.Emp;
import com.dhee.vo.HrUser;
import com.dhee.vo.Jobs;
import com.dhee.vo.Show;

public interface AdminDao {

	// 判断是否有该用户-登陆-是否有该用户名
	public AdminUser selectAdminUser(AdminUser au);

	// 查看所有HR-HR下拉框
	public List<HrUser> getAllHr();

	// 获取全部公司名称
	public List<Compary> getAllCompary();

	// 查看所有公司所有部门
	public List<Jobs> getAllJobs(Compary compary);

	// 添加emp信息-返回插入记录对应的ID
	public int saveEmp(Emp emp1);

	// 保存服务客户信息
	public int saveCustomer(Customer emp1);

	// 获取最大的EMP表ID值
	public int getEmpMAX_ID();

	// 更新emp信息
	public int updateEmp(Emp emp);

	// 删除emp信息
	public int deleteEmp(Emp emp);

	// 删除公司
	public int deleteCompary(Compary compary);

	// 删除部门
	public int deleteJobs(Jobs jobs);

	// 添加公司
	public int saveCompary(Compary compary);

	// 获取Compary表中最大的ID
	public int getComparyMAX_ID();

	// 添加部门
	public int savejobs(Jobs jobs);

	// 查询HR信息
	public HrUserPage selectHrUser(HrUserPage hrUserPage);

	// 删除HR信息
	public int deletHrUser(HrUser hrUser);

	// 添加HR用户
	public int addHrUser(HrUser hrUser);

	// 修改HR信息
	public int updateHrUser(HrUser hrUser);

	// 更新公司名称
	public int updateCompary(Compary c);
	
	//判断录入用户是否重名
	public boolean isEmpName(String name);
	
	public int deleteEmp(String id);

	public List<Object> selectEmp(String id);

	public int updataCustomer(Customer customer);

	public List<Customer> Comparyid(String id);

	public List<Customer> selectcustomer(String id, String company);

	public int deleteCustomer(String id);
	//员工--分页
	public EmpUserPage getShow(EmpUserPage empUserPage);
	//打印
	EmpUserPage getShowAll(EmpUserPage empUserPage);
	

}
