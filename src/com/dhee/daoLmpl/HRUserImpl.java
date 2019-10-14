package com.dhee.daoLmpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.dhee.dao.HRUserDao;
import com.dhee.dbConnection.DBO;
import com.dhee.vo.HrUser;

public class HRUserImpl implements HRUserDao {

	DBO dbo = new DBO();

	@Override
	public HrUser selectHrUser(HrUser hr) {
		HrUser hrUser = null;
		String mp = "select * FROM hr_user WHERE username='" + hr.getUsername() + "'";
		ResultSet resultSet = dbo.select(mp);
		try {
			while (resultSet.next()) {
				hrUser = new HrUser();
				hrUser.setId(Integer.parseInt(resultSet.getString(1)));
				hrUser.setUsername(resultSet.getString(2));
				hrUser.setUserpassword(resultSet.getString(3));
				hrUser.setRealname(resultSet.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hrUser;
	}

}
