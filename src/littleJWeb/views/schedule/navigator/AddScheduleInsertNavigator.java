package littleJWeb.views.schedule.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSchedule;
import littleJ.views.dto.ScheduleDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddScheduleInsertNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		ScheduleDTO scheduleDTO = new ScheduleDTO();
		scheduleDTO.setDescription(getParm(req,"description"));
		
		String isActive = getParm(req,"isAcive");
		if(isActive != null && isActive.length()> 0){
			scheduleDTO.setActive(true);
		}

		Connection conn = getConnection();		
		try {
			new DBSchedule(conn).addItem(scheduleDTO);					
		} catch (SQLException e) {
			setErrorMessage("Could not add schedule: " + scheduleDTO.getDescription());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("cancel");
		
		return true;
		
	}

}

