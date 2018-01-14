package littleJWeb.views.schedule.navigator;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSchedule;
import littleJ.views.dto.ScheduleDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ViewSceduleNavigator extends Navigator{
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		setMainPopulatingNavigator("views.schedule.navigator.ViewScedule");
		List<ScheduleDTO> scheduleDTOlist = null;
		
		Connection conn = getConnection();	
		try {
			scheduleDTOlist = new DBSchedule(conn).getAllItems();		
			
		}catch (Exception e) {
			setErrorMessage("Exception occurred");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("scheduleDTOlist",scheduleDTOlist);
		
		return true;
	}

}





