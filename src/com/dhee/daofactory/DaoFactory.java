package com.dhee.daofactory;

import com.dhee.dao.AdminDao;
import com.dhee.dao.HRUserDao;
import com.dhee.daoLmpl.AdminImpl;
import com.dhee.daoLmpl.HRUserImpl;

public class DaoFactory {

	public AdminDao getAdminImpl() {
		return new AdminImpl();
	}

	public HRUserDao getHRUserImpl() {
		return new HRUserImpl();
	}

}
