package com.dhee.tools;

import java.util.List;

import com.dhee.vo.HrUser;

public class HrUserPage {
	private List<HrUser> pagelist;
	private int nowpage;
	private int n;
	private String realName;
	private int allrow;
	private int allpage;
	private boolean isfirst;
	private boolean islast;
	private boolean isup;
	private boolean isdown;

	public HrUserPage(int nowpage, int n, String realName) {
		super();
		this.nowpage = nowpage;
		this.n = n;
		this.realName = realName;
	}

	public void setPagelist(List<HrUser> pagelist) {
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

	public List<HrUser> getPagelist() {
		return pagelist;
	}

	public int startrow() {
		return (nowpage * n);
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public int getN() {
		return n;
	}

	public void setAllrow(int allrow) {
		this.allrow = allrow;
	}

	@Override
	public String toString() {
		return "HrUserPage [allrow=" + getAllrow() + ", allpage=" + getAllpage() + ", nowpage=" + nowpage + ", n=" + n
				+ ", isfirst=" + isIsfirst() + ", islast=" + isIslast() + ", isup=" + isIsup() + ", isdown="
				+ isIsdown() + ", realName=" + realName + "]";
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

	public void setStudentName(String realName) {
		this.realName = realName;
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

	public HrUserPage(List<HrUser> pagelist, int nowpage, int n, String realName, int allrow, int allpage, boolean isfirst, boolean islast, boolean isup, boolean isdown) {
		super();
		this.pagelist = pagelist;
		this.nowpage = nowpage;
		this.n = n;
		this.realName = realName;
		this.allrow = allrow;
		this.allpage = allpage;
		this.isfirst = isfirst;
		this.islast = islast;
		this.isup = isup;
		this.isdown = isdown;
	}

	public HrUserPage() {
		super();
	}
}
