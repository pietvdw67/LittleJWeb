package littleJWeb.setup.hardware.items.navigator;


import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItem;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class DeleteItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idItem = Integer.parseInt(getParm(req,"iditem"));
		
		Connection conn = getConnection();		
		try {
			new DBItem(conn).deleteItem(idItem);
		} catch (SQLException e) {
			setErrorMessage("Could not delete item id:" + idItem);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("cancel");
				
		return true;
		
		
	}

}
