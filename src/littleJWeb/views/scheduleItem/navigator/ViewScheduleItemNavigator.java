package littleJWeb.views.scheduleItem.navigator;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSchedule;
import littleJ.database.DBScheduleItem;
import littleJ.views.dto.ScheduleDTO;
import littleJ.views.dto.ScheduleItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ViewScheduleItemNavigator extends Navigator{
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {		
		int idSchedule = Integer.valueOf(getParm(req,"idschedule"));
		addPopulatingNavigator("views.scheduleItem.navigator.ViewScheduleItem&idschedule=" + idSchedule);
		List<ScheduleItemDTO> scheduleItemDTOlist = null;
		ScheduleDTO scheduleDTO = null;
		
		Connection conn = getConnection();		
		
		try {
			scheduleItemDTOlist = new DBScheduleItem(conn).getScheduleItemsBySchedule(idSchedule);
			scheduleDTO = new DBSchedule(conn).getItem(idSchedule);			
		}catch (Exception e) {
			setErrorMessage("Exception occurred");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("scheduleItemDTOlist",scheduleItemDTOlist);
		storeObject("scheduleDTO",scheduleDTO);
		
		return true;
	}

}



