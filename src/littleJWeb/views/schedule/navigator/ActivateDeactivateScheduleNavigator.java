package littleJWeb.views.schedule.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSchedule;
import littleJ.views.dto.ScheduleDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ActivateDeactivateScheduleNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idSchedule = Integer.parseInt(getParm(req, "idschedule"));
		ScheduleDTO scheduleDTO = null;
		Connection conn = getConnection();		
		try {
			scheduleDTO = new DBSchedule(conn).getItem(idSchedule);
			scheduleDTO.setActive(!scheduleDTO.getActive());
			scheduleDTO.setPopulated(false);
			new DBSchedule(conn).updateItem(scheduleDTO);
		} catch (SQLException e) {
			setErrorMessage("Could not update schedule: " + scheduleDTO.getDescription());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("cancel");
		
		return true;
		
	}

}


