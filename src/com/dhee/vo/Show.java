package com.dhee.vo;

import java.util.Date;

public class Show {

	private int id;
	
	private String company;//
	private String name;
	private String sex;
	private String skill;
	private String workplace;//
	private String recruitmenttype;
	private String hr;
	private String telphone;
	private Double hopesalary;
	private String graduateschool;
	private Date graduatetime;
	private String education;
	
	private Date entrytime;
	private Date contractperiod;
	
	private String enclosure;

	public String getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(String enclosure) {
		this.enclosure = enclosure;
	}

	public Double getHopesalary() {
		return hopesalary;
	}

	public void setHopesalary(Double hopesalary) {
		this.hopesalary = hopesalary;
	}

	public String getIsFile() {
		return enclosure;
	}

	public void setIsFile(String isFile) {
		this.enclosure = isFile;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getWorkplace() {
		return workplace;
	}

	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getRecruitmenttype() {
		return recruitmenttype;
	}

	public void setRecruitmenttype(String recruitmenttype) {
		this.recruitmenttype = recruitmenttype;
	}

	public String getHr() {
		return hr;
	}

	public void setHr(String hr) {
		this.hr = hr;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getGraduateschool() {
		return graduateschool;
	}

	public void setGraduateschool(String graduateschool) {
		this.graduateschool = graduateschool;
	}

	public Date getGraduatetime() {
		return graduatetime;
	}

	public void setGraduatetime(Date graduatetime) {
		this.graduatetime = graduatetime;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

}
