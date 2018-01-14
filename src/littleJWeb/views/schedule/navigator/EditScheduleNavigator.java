package littleJWeb.views.schedule.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSchedule;
import littleJ.views.dto.ScheduleDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditScheduleNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idSchedule = Integer.valueOf(getParm(req,"idschedule"));
		ScheduleDTO scheduleDTO = null;
		String doTickActive="no";		
		Connection conn = getConnection();		
		try {
			scheduleDTO = new DBSchedule(conn).getItem(idSchedule);
		} catch (SQLException e) {
			setErrorMessage("Could not retrieve schedule id:" + idSchedule);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		if (scheduleDTO.isActive()){
			doTickActive = "yes";
		}
		
		storeObject("scheduleDTO",scheduleDTO);	
		storeObject("doTickActive", doTickActive);
		
		return true;
	}

}

