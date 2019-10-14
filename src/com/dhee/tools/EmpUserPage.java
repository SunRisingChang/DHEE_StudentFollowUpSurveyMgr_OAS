package com.dhee.tools;

import java.util.Date;
import java.util.List;
import com.dhee.vo.Show;

public class EmpUserPage {
	private List<Show> pagelist;
	private int nowpage;
	private int allrow;
	private int allpage;
	private boolean isfirst;
	private boolean islast;
	private boolean isup;
	private boolean isdown;
	private int n;

	private String name1;
	private String skill;
	private String workplace;
	private Date entrytime;
	private Date contractperiod;
	private String recruitmenttype;
	private String education;
	private String entryedornot;
	private String company;

	public EmpUserPage() {
		super();
	}

	public EmpUserPage(int nowpage, int n, String name1, String skill, String workplace, Date entrytime, Date contractperiod, String recruitmenttype, String education, String entryedornot, String company) {
		super();
		this.nowpage = nowpage;
		this.n = n;
		this.name1 = name1;
		this.skill = skill;
		this.workplace = workplace;
		this.entrytime = entrytime;
		this.contractperiod = contractperiod;
		this.recruitmenttype = recruitmenttype;
		this.education = education;
		this.entryedornot = entryedornot;
		this.company = company;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public Date getEntrytime() {
		return entrytime;
	}

	public void setEntrytime(Date entrytime) {
		this.entrytime = entrytime;
	}

	public Date getContractperiod() {
		return contractperiod;
	}

	public void setContractperiod(Date contractperiod) {
		this.contractperiod = contractperiod;
	}

	public String getRecruitmenttype() {
		return recruitmenttype;
	}

	public void setRecruitmenttype(String recruitmenttype) {
		this.recruitmenttype = recruitmenttype;
	}

	public void setPagelist(List<Show> pagelist) {
		this.pagelist = pagelist;
	}

	public int getAllrow() {
		return allrow;
	}

	public int getAllpage() {
		if (getAllrow() % n != 0) {
			return (int) (allrow / n) + 1;
		}
		return allrow / n;
	}

	public boolean isIsfirst() {
		if (nowpage == 0) {
			return true;
		}
		return false;
	}

	public boolean isIslast() {
		if (nowpage == getAllpage()) {
			return true;
		}
		return false;
	}

	public boolean isIsup() {
		if (nowpage == 0) {
			return false;
		}
		return true;
	}

	public boolean isIsdown() {
		if (nowpage == getAllpage() - 1) {
			return false;
		}
		return true;
	}

	public List<Show> getPagelist() {
		return pagelist;
	}

	public int startrow() {
		return (nowpage * n);
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEntryedornot() {
		return entryedornot;
	}

	public void setEntryedornot(String entryedornot) {
		this.entryedornot = entryedornot;
	}

	public int getN() {
		return n;
	}

	public void setAllrow(int allrow) {
		this.allrow = allrow;
	}

	public int getNowpage() {
		return nowpage;
	}

	public void setNowpage(int nowpage) {
		this.nowpage = nowpage;
	}

	public void setN(int n) {
		this.n = n;
	}

	public void setAllpage(int allpage) {
		this.allpage = allpage;
	}

	public void setIsfirst(boolean isfirst) {
		this.isfirst = isfirst;
	}

	public void setIslast(boolean islast) {
		this.islast = islast;
	}

	public void setIsup(boolean isup) {
		this.isup = isup;
	}

	public void setIsdown(boolean isdown) {
		this.isdown = isdown;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

}
