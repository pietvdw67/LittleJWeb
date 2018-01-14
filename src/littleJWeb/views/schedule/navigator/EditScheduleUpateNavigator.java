package littleJWeb.views.schedule.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSchedule;
import littleJ.views.dto.ScheduleDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditScheduleUpateNavigator extends Navigator {
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		scheduleDTO.setIdSchedule(Integer.parseInt(getParm(req,"idschedule")));
		scheduleDTO.setDescription(getParm(req,"description"));
	
		String isActiveParm = getParm(req,"isAcive");
		if (isActiveParm != null && isActiveParm.trim().length()>0){
			scheduleDTO.setActive(true);
		}

		Connection conn = getConnection();
		try {
			new DBSchedule(conn).updateItem(scheduleDTO);			
		} catch (SQLException e) {
			setErrorMessage("Could not update schedule id:" + scheduleDTO.getIdSchedule());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("cancel");
		return true;
	}

}

