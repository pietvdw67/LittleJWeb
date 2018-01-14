package littleJWeb.setup.hardware.itemTypes.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItemType;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class DeleteItemTypeNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idItemType = Integer.parseInt(getParm(req,"idItemType"));
		
		Connection conn = getConnection();		
		try {
			new DBItemType(conn).deleteItem(idItemType);
		} catch (SQLException e) {
			setErrorMessage("Could not delete item type id:" + idItemType);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("setup.hardware.itemTypes.navigator.ViewItemType");
				
		return true;
				
	}

}

