package dhee_PJ2;

import org.junit.Test;

import com.dhee.daofactory.DaoFactory;
import com.dhee.vo.HrUser;

public class dome {
	@Test
	public void test1(){
		for(int i=0;i<50;i++){
			new DaoFactory().getAdminImpl().addHrUser(new HrUser(0, "src"+i, "admin"+i, "王莹"+i));
		}
	}
	
	

}
