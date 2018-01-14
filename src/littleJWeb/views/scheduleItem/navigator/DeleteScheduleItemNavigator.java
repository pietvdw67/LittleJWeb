package littleJWeb.views.scheduleItem.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBScheduleItem;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class DeleteScheduleItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idScheduleItem = Integer.parseInt(getParm(req,"idsheduleitem"));
		
		Connection conn = getConnection();		
		try {
			new DBScheduleItem(conn).deleteItem(idScheduleItem);	
		} catch (SQLException e) {
			setErrorMessage("Could not delete schedule item id:" + idScheduleItem);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("cancel");
				
		return true;
		
		
	}

}



