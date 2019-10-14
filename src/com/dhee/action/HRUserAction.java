package com.dhee.action;

import com.dhee.daofactory.DaoFactory;
import com.dhee.vo.HrUser;

public class HRUserAction extends SuperAction {
	private DaoFactory daoFactory = new DaoFactory();

	// 查看是否有HR用户名
	public void isHrName() {
		try {
			HrUser au = new HrUser();
			au.setUsername(request.getParameter("hrName"));
			HrUser au1 = daoFactory.getHRUserImpl().selectHrUser(au);
			if (au1 == null) {
				response.getWriter().print("0");
			} else {
				response.getWriter().print("1");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("isHrName方法出错！");
			e.printStackTrace();
		}

	}

	public void isUser() {
		try {
			HrUser temp = new HrUser();
			temp.setUsername(request.getParameter("hrName"));
			HrUser au1 = daoFactory.getHRUserImpl().selectHrUser(temp);
			if (au1 == null) {
				response.getWriter().print("0");
			} else {
				if (request.getParameter("hrPassword").equals(au1.getUserpassword())) {
					session.setAttribute("nowName", au1.getUsername());
					session.setAttribute("type", "0");
					session.setAttribute("realName", au1.getRealname());
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
}
