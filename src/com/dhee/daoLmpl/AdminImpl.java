package com.dhee.daoLmpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dhee.dao.AdminDao;
import com.dhee.dbConnection.DBO;
import com.dhee.tools.EmpUserPage;
import com.dhee.tools.HrUserPage;
import com.dhee.vo.AdminUser;
import com.dhee.vo.Compary;
import com.dhee.vo.Customer;
import com.dhee.vo.Emp;
import com.dhee.vo.HrUser;
import com.dhee.vo.Jobs;
import com.dhee.vo.Show;

public class AdminImpl implements AdminDao {

	private DBO dbo;

	public AdminImpl() {
		dbo = new DBO();
	}

	@Override
	// 查询表中是否有该对象
	public AdminUser selectAdminUser(AdminUser au) {
		// TODO Auto-generated method stub
		AdminUser user = null;
		String sql = "select * from admin_user where username = '" + au.getUsername() + "'";
		ResultSet rs = dbo.select(sql);
		try {
			while (rs.next()) {
				user = new AdminUser();
				user.setUsername(rs.getString(2));
				user.setUserpassword(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public List<HrUser> getAllHr() {
		// TODO Auto-generated method stub
		List<HrUser> list = new ArrayList<HrUser>();
		String sql = "select * from hr_user";
		ResultSet rs = dbo.select(sql);
		try {
			while (rs.next()) {
				list.add(new HrUser(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Compary> getAllCompary() {
		// TODO Auto-generated method stub
		Compary com;
		List<Compary> list = new ArrayList<Compary>();
		String sql = "select * from compary";
		ResultSet rs = dbo.select(sql);
		try {
			while (rs.next()) {
				com = new Compary();
				com.setId(rs.getInt(1));
				com.setName(rs.getString(2));
				list.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public List<Customer> Comparyid(String id) {
		// TODO Auto-generated method stub
		Customer com;
		List<Customer> list = new ArrayList<Customer>();
		String sql = "select * from customer where emp_id='"+id+"'";
		ResultSet rs = dbo.select(sql);
		try {
			while (rs.next()) {
				com = new Customer();
				com.setEmpId(rs.getInt(1));
				com.setCompany(rs.getString(2));
				com.setJob(rs.getString(3));
				com.setStartDate(rs.getDate(4));
				com.setEndDate(rs.getDate(5));
				com.setSalary(rs.getDouble(6));
				com.setSingleGold(rs.getDouble(7));
				list.add(com);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Jobs> getAllJobs(Compary com) {
		// TODO Auto-generated method stub
		Jobs job;
		List<Jobs> list = new ArrayList<Jobs>();
		String sql = "select * from jobs where comid='" + com.getId() + "'";
		ResultSet rs = dbo.select(sql);
		try {
			while (rs.next()) {
				job = new Jobs();
				job.setComid(rs.getInt(1));
				job.setJob(rs.getString(2));
				list.add(job);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int saveEmp(Emp emp1) {
		// TODO Auto-generated method stub
		int temp = 0;
		String sql = "insert into emp (contactdate,HR,fastentrytime,post,`name`,sex,idno,age,nation,recruitmenttype,skill,`language`,languageability,hopesalary,workplace,graduatetime,graduateschool,major,education,telphone,email,othercontact,entryedornot,entrytime,contractperiod,uesperiod,contractrenewal,welfare,departuretime,departurereasons,enclosure) values ('" + new SimpleDateFormat("yyyy-MM-dd").format(emp1.getContactdate()) + "','" + emp1.getHr() + "','" + new SimpleDateFormat("yyyy-MM-dd").format(emp1.getFastentrytime()) + "','" + emp1.getPost() + "','" + emp1.getName() + "','" + emp1.getSex() + "','" + emp1.getIdno() + "','" + emp1.getAge() + "','" + emp1.getNation() + "','" + emp1.getRecruitmenttype() + "','" + emp1.getSkill() + "','" + emp1.getLanguage() + "','"
				+ emp1.getLanguageability() + "','" + emp1.getHopesalary() + "','" + emp1.getWorkplace() + "','" + new SimpleDateFormat("yyyy-MM-dd").format(emp1.getGraduatetime()) + "','" + emp1.getGraduateschool() + "','" + emp1.getMajor() + "','" + emp1.getEducation() + "','" + emp1.getTelphone() + "','" + emp1.getEmail() + "','" + emp1.getOthercontact() + "','" + emp1.getEntryedornot() + "','" + new SimpleDateFormat("yyyy-MM-dd").format(emp1.getEntrytime()) + "','" + new SimpleDateFormat("yyyy-MM-dd").format(emp1.getContractperiod()) + "','" + emp1.getUesperiod() + "','" + emp1.getContractrenewal() + "','" + emp1.getWelfare() + "','" + emp1.getDeparturetime() + "','" + emp1.getDeparturereasons() + "','" + emp1.getEnclosure() + "')";
		temp = dbo.save(sql);
		return temp;
	}

	@Override
	public int saveCompary(Compary compary) {
		int temp = 0;
		String sql = "insert into compary (name) values ('" + compary.getName() + "')";
		temp = dbo.save(sql);
		return temp;
	}

	@Override
	public int deleteCustomer(String id){
		String sql="DELETE FROM customer WHERE emp_id='"+id+"'";
		int temp=dbo.delete(sql);
		return temp;
		
	}
	@Override
	public List<Customer> selectcustomer(String id,String company){
		List<Customer> list=new ArrayList<Customer>();
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
		String sql="select company,job,start_date,max(end_date),salary,single_gold from customer where emp_id='"+id+"'and company like '"+ ((company != "") ? company : '%')+"' ORDER BY end_date DESC";
		ResultSet rs=dbo.select(sql);
		try {
			while (rs.next()) {
				Customer customer=new Customer();
				customer.setCompany(rs.getString(1));
				customer.setJob(rs.getString(2));
				customer.setStartDate(rs.getDate(3));
				customer.setEndDate(rs.getDate(4));
				customer.setSalary(rs.getDouble(5));
				customer.setSingleGold(rs.getDouble(6));
				list.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	@Override
	public List<Object> selectEmp(String id) {
		// TODO Auto-generated method stub
		List<Object> list = new ArrayList<Object>();
		String sql = "";
		sql = "SELECT * FROM dhee_2.emp e LEFT JOIN dhee_2.customer c ON e.id = c.emp_id WHERE e.id ='"+id+"' ORDER BY contractperiod DESC";
		ResultSet rs1 = dbo.select(sql);
		try {
			while (rs1.next()) {
				Emp emp=new Emp();
				Customer customer=new Customer();
				emp.setId(rs1.getInt(1));
				emp.setContactdate(rs1.getDate(2));
				emp.setHr(rs1.getString(3));
				emp.setFastentrytime(rs1.getDate(4));
				emp.setPost(rs1.getString(5));
				emp.setName(rs1.getString(6));
				emp.setSex(rs1.getString(7));
				emp.setIdno(rs1.getString(8));
				emp.setAge(rs1.getInt(9));
				emp.setNation(rs1.getString(10));
				emp.setRecruitmenttype(rs1.getString(11));
				emp.setSkill(rs1.getString(12));
				emp.setLanguage(rs1.getString(13));
				emp.setLanguageability(rs1.getString(14));
				emp.setHopesalary(rs1.getDouble(15));
				emp.setWorkplace(rs1.getString(16));
				emp.setGraduatetime(rs1.getDate(17));
				emp.setGraduateschool(rs1.getString(18));
				emp.setMajor(rs1.getString(19));
				emp.setEducation(rs1.getString(20));
				emp.setTelphone(rs1.getString(21));
				emp.setEmail(rs1.getString(22));
				emp.setOthercontact(rs1.getString(23));
				emp.setEntryedornot(rs1.getString(24));
				emp.setEntrytime(rs1.getDate(25));
				emp.setContractperiod(rs1.getDate(26));
				emp.setUesperiod(rs1.getString(27));
				emp.setContractrenewal(rs1.getString(28));
				emp.setWelfare(rs1.getString(29));
				emp.setDeparturetime(rs1.getString(30));
				emp.setDeparturereasons(rs1.getString(31));
				emp.setEnclosure(rs1.getString(32));
				customer.setCompany(rs1.getString(34));
				customer.setJob(rs1.getString(35));
				customer.setStartDate(rs1.getDate(36));
				customer.setEndDate(rs1.getDate(37));
				customer.setSalary(rs1.getDouble(38));
				customer.setSingleGold(rs1.getDouble(39));
				list.add(emp);
				list.add(customer);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public int savejobs(Compary compary, Jobs jobs) {
		int temp = 0;
		String sql = "insert into jobs (comid,job) values ('" + compary.getId() + "','" + jobs.getJob() + "')";
		temp = dbo.save(sql);
		return temp;
	}

	@Override
	public int updateEmp(Emp emp1) {
		// TODO Auto-generated method stub
		int temp = 0;
		String sql = "update emp set contactdate='" + new SimpleDateFormat("yyyy-MM-dd").format(emp1.getContactdate())+ "',hr='" + emp1.getHr()
				+ "',fastentrytime='" + new SimpleDateFormat("yyyy-MM-dd").format(emp1.getFastentrytime()) + "',post='" + emp1.getPost() + "',name='"
				+ emp1.getName() + "',sex='" + emp1.getSex() + "',age='" + emp1.getAge() + "',idno='" + emp1.getIdno()
				+ "',nation='" + emp1.getNation() + "',recruitmenttype='" + emp1.getRecruitmenttype() + "',skill='"
				+ emp1.getSkill() + "',language='" + emp1.getLanguage() + "',languageability='"
				+ emp1.getLanguageability() + "',hopesalary='" + emp1.getHopesalary()+ "',workplace='"
				+ emp1.getWorkplace() + "',graduatetime='" + new SimpleDateFormat("yyyy-MM-dd").format(emp1.getGraduatetime()) + "',graduateschool='"
				+ emp1.getGraduateschool() + "',major='" + emp1.getMajor() + "',education='" + emp1.getEducation()
				+ "',telphone='" + emp1.getTelphone() + "',email='" + emp1.getEmail()  + "',othercontact='"
				+ emp1.getOthercontact() + "',entryedornot='" + emp1.getEntryedornot() + "',entrytime='"
				+ new SimpleDateFormat("yyyy-MM-dd").format(emp1.getEntrytime()) + "',Contractperiod='" + new SimpleDateFormat("yyyy-MM-dd").format(emp1.getContractperiod()) + "',uesperiod='"
				+ emp1.getUesperiod() + "',contractrenewal='" + emp1.getContractrenewal() +"',welfare='"  + emp1.getWelfare() + "',departuretime='"
				+ emp1.getDeparturetime()+ "',departurereasons='"
				+ emp1.getDeparturereasons() + "',enclosure='" + emp1.getEnclosure()+"' where id='"+emp1.getId()+"'";
		temp = dbo.update(sql);
		return temp;
	}

	@Override
	public int deleteEmp(Emp emp) {
		// TODO Auto-generated method stub
		int temp = 0;
		String sql = "delete from emp where id='" + emp.getId() + "'";
		temp = dbo.delete(sql);
		return temp;
	}
	@Override
	public int deleteEmp(String id) {
		// TODO Auto-generated method stub
		int temp = 0;
		String sql = "delete from emp where id='" + id + "'";
		temp = dbo.delete(sql);
		return temp;
	}

	@Override
	public int deleteCompary(Compary compary) {
		// TODO Auto-generated method stub
		int temp = 0;
		String sql = "delete from compary where id='" + compary.getId() + "'";
		temp = dbo.delete(sql);
		return temp;
	}

	@Override
	public int saveCustomer(Customer customer) {
		// TODO Auto-generated method stub
		int temp = 0;
		String sql = "insert into customer (emp_id,company,job,start_date,end_date,salary,single_gold) values ('" + customer.getEmpId() + "','" + customer.getCompany() + "','" + customer.getJob() + "','" + new SimpleDateFormat("yyyy-MM-dd").format(customer.getStartDate()) + "','" + new SimpleDateFormat("yyyy-MM-dd").format(customer.getEndDate()) + "','" + customer.getSalary() + "','" + customer.getSingleGold() + "')";
		temp = dbo.save(sql);
		return temp;
	}

	@Override
	public HrUserPage selectHrUser(HrUserPage hrpage) {
		// TODO Auto-generated method stub
		List<HrUser> list = new ArrayList<HrUser>();
		String sql = "";
		String sqlnum = "";
		int sum = 0;
		if (!hrpage.getRealName().equals("")) {
			sql = "select * from hr_user where realname like '%" + hrpage.getRealName() + "%' limit " + hrpage.startrow() + "," + hrpage.getN() + "";
			sqlnum = "select count(*) from hr_user where realname like '%" + hrpage.getRealName() + "%'";
		}
		ResultSet rs1 = dbo.select(sql);
		ResultSet rs2 = new DBO().select(sqlnum);
		try {
			while (rs1.next()) {
				HrUser hr = new HrUser();
				hr.setId(Integer.parseInt(rs1.getString(1)));
				hr.setUsername(rs1.getString(2));
				hr.setUserpassword(rs1.getString(3));
				hr.setRealname(rs1.getString(4));
				list.add(hr);
			}
			hrpage.setPagelist(list);
			while (rs2.next()) {
				sum = rs2.getInt(1);
			}
			hrpage.setAllrow(sum);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hrpage;
	}

	public int getEmpMAX_ID() {
		// TODO Auto-generated method stub
		int sum = 0;
		Emp emp = new Emp();
		String sql = "select max(id) from emp";
		ResultSet rs = dbo.select(sql);
		try {
			while (rs.next()) {
				emp.setId(rs.getInt(1));
			}
			sum = emp.getId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}

	@Override
	public int getComparyMAX_ID() {
		// TODO Auto-generated method stub
		int sum = 0;
		Compary compary = new Compary();
		String sql = "select max(id) from compary";
		ResultSet rs = dbo.select(sql);
		try {
			while (rs.next()) {
				compary.setId(rs.getInt(1));
			}
			sum = compary.getId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sum;
	}

	@Override
	public int deleteJobs(Jobs jobs) {
		// TODO Auto-generated method stub
		int sum = 0;
		String sql = "delete from jobs where id='" + jobs.getComid() + "'";
		sum = dbo.delete(sql);
		return sum;
	}

	@Override
	public int savejobs(Jobs jobs) {
		// TODO Auto-generated method stub
		int sum = 0;
		String sql = "insert into jobs (comid,job) values ('" + jobs.getComid() + "','" + jobs.getJob() + "')";
		sum = dbo.save(sql);
		return sum;
	}

	@Override
	public int deletHrUser(HrUser hrUser) {
		// TODO Auto-generated method stub
		int sum = 0;
		String sql = "delete from hr_user where id='" + hrUser.getId() + "'";
		sum = dbo.delete(sql);
		return sum;
	}

	@Override
	public int addHrUser(HrUser hrUser) {
		// TODO Auto-generated method stub
		int sum = 0;
		String sql = "insert into hr_user (username,userpassword,realname) values('" + hrUser.getUsername() + "','" + hrUser.getUserpassword() + "','" + hrUser.getRealname() + "')";
		sum = dbo.save(sql);
		return sum;
	}

	@Override
	public int updateHrUser(HrUser hrUser) {
		// TODO Auto-generated method stub
		int sum = 0;
		String sql = "update hr_user set username='" + hrUser.getUsername() + "',userpassword='" + hrUser.getUserpassword() + "',realname='" + hrUser.getRealname() + "' where id=" + hrUser.getId() + "";
		sum = dbo.update(sql);
		return 0;
	}

	@Override
	public int updateCompary(Compary c) {
		// TODO Auto-generated method stub
		int sum = 0;
		String sql = "update compary set name='" + c.getName() + "' where id=" + c.getId() + "";
		sum = dbo.update(sql);
		return sum;
	}

	@Override
	public int updataCustomer(Customer customer){
		int sum=0;
		String sql="insert into customer (emp_id,company,job,start_date,end_date,salary,single_gold) values('"+customer.getEmpId()+"','"+customer.getCompany()+"','"+customer.getJob()+"','"+new SimpleDateFormat("yyyy-MM-dd").format(customer.getStartDate())+"','"+new SimpleDateFormat("yyyy-MM-dd").format(customer.getEndDate())+"','"+((customer.getSalary()==null)?"0":customer.getSalary())+"','"+((customer.getSingleGold()==null)?"0":customer.getSingleGold())+"')";
		sum = dbo.save(sql);
		return sum;	
	}

	@Override
	public boolean isEmpName(String name) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM emp WHERE `name`='"+name+"'";
		ResultSet resultSet=dbo.select(sql);
		try {
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public EmpUserPage getShow(EmpUserPage e) {
		String tempSQL="SELECT e.id, c.company, e. NAME, e.sex, e.skill, e.workplace, e.recruitmenttype, e.HR, e.telphone, e.hopesalary, e.graduateschool, e.graduatetime, e.education, e.entrytime, e.contractperiod, e.enclosure FROM dhee_2.emp AS e LEFT JOIN (SELECT * FROM dhee_2.customer AS a WHERE end_date = (SELECT max(b.end_date) FROM dhee_2.customer AS b WHERE a.emp_id = b.emp_id ) ) AS c ON c.emp_id = e.id WHERE e.`name` LIKE '%"+e.getName1()+"%' AND e.skill LIKE '%"+e.getSkill()+"%' AND e.workplace LIKE '%"+(e.getWorkplace().equals("--")?"":e.getWorkplace().replace("-", "%"))+"%' AND e.entrytime LIKE '%"+(new SimpleDateFormat("yyyy-MM-dd").format(e.getEntrytime()).equals("0001-01-01")?"":new SimpleDateFormat("yyyy-MM-dd").format(e.getEntrytime()))+"%' AND e.contractperiod LIKE '%"+(new SimpleDateFormat("yyyy-MM-dd").format(e.getContractperiod()).equals("0001-01-01")?"":new SimpleDateFormat("yyyy-MM-dd").format(e.getContractperiod()))+"%' AND e.recruitmenttype LIKE '%"+e.getRecruitmenttype()+"%' AND e.education LIKE '%"+e.getEducation()+"%' AND e.entryedornot LIKE '%"+e.getEntryedornot()+"%' ";
		String tempSQL2="SELECT count(*) FROM dhee_2.emp AS e LEFT JOIN (SELECT * FROM dhee_2.customer AS a WHERE end_date = (SELECT max(b.end_date) FROM dhee_2.customer AS b WHERE a.emp_id = b.emp_id ) ) AS c ON c.emp_id = e.id WHERE e.`name` LIKE '%"+e.getName1()+"%' AND e.skill LIKE '%"+e.getSkill()+"%' AND e.workplace LIKE '%"+(e.getWorkplace().equals("--")?"":e.getWorkplace().replace("-", "%"))+"%' AND e.entrytime LIKE '%"+(new SimpleDateFormat("yyyy-MM-dd").format(e.getEntrytime()).equals("0001-01-01")?"":new SimpleDateFormat("yyyy-MM-dd").format(e.getEntrytime()))+"%' AND e.contractperiod LIKE '%"+(new SimpleDateFormat("yyyy-MM-dd").format(e.getContractperiod()).equals("0001-01-01")?"":new SimpleDateFormat("yyyy-MM-dd").format(e.getContractperiod()))+"%' AND e.recruitmenttype LIKE '%"+e.getRecruitmenttype()+"%' AND e.education LIKE '%"+e.getEducation()+"%' AND e.entryedornot LIKE '%"+e.getEntryedornot()+"%' ";
		if (e.getCompany().equals("")) {
			tempSQL=tempSQL+"ORDER BY e.contractperiod limit "+e.startrow()+","+e.getN()+"";
		}else{
			tempSQL=tempSQL+"AND c.company LIKE '%"+e.getCompany()+"%' ORDER BY e.contractperiod limit "+e.startrow()+","+e.getN()+"";
			tempSQL2=tempSQL2+"AND c.company LIKE '%"+e.getCompany()+"%'";
		}
		ResultSet resultSet=dbo.select(tempSQL);
		ResultSet resultSet2=new DBO().select(tempSQL2);
		List<Show> list =new ArrayList<Show>();
		try {
			while(resultSet.next()){
				Show show=new Show();
				show.setId(resultSet.getInt(1));
				show.setCompany(resultSet.getString(2));
				show.setName(resultSet.getString(3));
				show.setSex(resultSet.getString(4));
				show.setSkill(resultSet.getString(5));
				show.setWorkplace(resultSet.getString(6));
				show.setRecruitmenttype(resultSet.getString(7));
				show.setHr(resultSet.getString(8));
				show.setTelphone(resultSet.getString(9));
				show.setHopesalary(resultSet.getDouble(10));
				show.setGraduateschool(resultSet.getString(11));
				show.setGraduatetime(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(12)));
				show.setEducation(resultSet.getString(13));
				show.setEntrytime(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(14)));
				show.setContractperiod(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(15)));
				show.setEnclosure(resultSet.getString(16));
				list.add(show);
			}
			e.setPagelist(list);
			while(resultSet2.next()){
				e.setAllrow(resultSet2.getInt(1));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}
	
	@Override
	public EmpUserPage getShowAll(EmpUserPage e) {
		String tempSQL="SELECT e.id, c.company, e. NAME, e.sex, e.skill, e.workplace, e.recruitmenttype, e.HR, e.telphone, e.hopesalary, e.graduateschool, e.graduatetime, e.education, e.entrytime, e.contractperiod, e.enclosure FROM dhee_2.emp AS e LEFT JOIN (SELECT * FROM dhee_2.customer AS a WHERE end_date = (SELECT max(b.end_date) FROM dhee_2.customer AS b WHERE a.emp_id = b.emp_id ) ) AS c ON c.emp_id = e.id WHERE e.`name` LIKE '%"+e.getName1()+"%' AND e.skill LIKE '%"+e.getSkill()+"%' AND e.workplace LIKE '%"+(e.getWorkplace().equals("--")?"":e.getWorkplace().replace("-", "%"))+"%' AND e.entrytime LIKE '%"+(new SimpleDateFormat("yyyy-MM-dd").format(e.getEntrytime()).equals("0001-01-01")?"":new SimpleDateFormat("yyyy-MM-dd").format(e.getEntrytime()))+"%' AND e.contractperiod LIKE '%"+(new SimpleDateFormat("yyyy-MM-dd").format(e.getContractperiod()).equals("0001-01-01")?"":new SimpleDateFormat("yyyy-MM-dd").format(e.getContractperiod()))+"%' AND e.recruitmenttype LIKE '%"+e.getRecruitmenttype()+"%' AND e.education LIKE '%"+e.getEducation()+"%' AND e.entryedornot LIKE '%"+e.getEntryedornot()+"%' ";
		if (e.getCompany().equals("")) {
			tempSQL=tempSQL+"ORDER BY e.contractperiod";
		}else{
			tempSQL=tempSQL+"AND c.company LIKE '%"+e.getCompany()+"%' ORDER BY e.contractperiod";
		}
		ResultSet resultSet=dbo.select(tempSQL);
		List<Show> list =new ArrayList<Show>();
		try {
			while(resultSet.next()){
				Show show=new Show();
				show.setId(resultSet.getInt(1));
				show.setCompany(resultSet.getString(2));
				show.setName(resultSet.getString(3));
				show.setSex(resultSet.getString(4));
				show.setSkill(resultSet.getString(5));
				show.setWorkplace(resultSet.getString(6));
				show.setRecruitmenttype(resultSet.getString(7));
				show.setHr(resultSet.getString(8));
				show.setTelphone(resultSet.getString(9));
				show.setHopesalary(resultSet.getDouble(10));
				show.setGraduateschool(resultSet.getString(11));
				show.setGraduatetime(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(12)));
				show.setEducation(resultSet.getString(13));
				show.setEntrytime(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(14)));
				show.setContractperiod(new SimpleDateFormat("yyyy-MM-dd").parse(resultSet.getString(15)));
				show.setEnclosure(resultSet.getString(16));
				list.add(show);
			}
			e.setPagelist(list);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return e;
	}
	

}
