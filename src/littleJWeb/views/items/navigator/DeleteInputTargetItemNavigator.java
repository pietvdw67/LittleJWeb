package littleJWeb.views.items.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBInputTargetItem;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class DeleteInputTargetItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idInputTargetItem = Integer.parseInt(getParm(req,"idinputtargetitem"));
		
		Connection conn = getConnection();		
		try {
			new DBInputTargetItem(conn).deleteItem(idInputTargetItem);	
		} catch (SQLException e) {
			setErrorMessage("Could not delete input target item id:" + idInputTargetItem);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("cancel");
				
		return true;
		
		
	}

}



