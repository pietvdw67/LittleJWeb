package littleJWeb.views.schedule.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSchedule;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class DeleteScheduleNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idSchedule = Integer.parseInt(getParm(req,"idschedule"));
		
		Connection conn = getConnection();		
		try {
			new DBSchedule(conn).deleteItem(idSchedule);		
		} catch (SQLException e) {
			setErrorMessage("Could not delete schedule id:" + idSchedule);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("cancel");
				
		return true;
		
		
	}

}



