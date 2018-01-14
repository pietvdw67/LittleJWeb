package littleJWeb.views.statuschange.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBStatusChange;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class RemovechangeNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idstatuschange = Integer.parseInt(getParm(req,"idstatuschange"));
		
		Connection conn = getConnection();		
		try {
			new DBStatusChange(conn).deleteItem(idstatuschange);
		} catch (SQLException e) {
			setErrorMessage("Could not delete status change item id:" + idstatuschange);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("cancel");
				
		return true;
		
		
	}

}




