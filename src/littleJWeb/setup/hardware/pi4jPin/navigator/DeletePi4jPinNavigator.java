package littleJWeb.setup.hardware.pi4jPin.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBPi4jPin;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class DeletePi4jPinNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idpi4jpin = Integer.parseInt(getParm(req,"idpi4jpin"));
		
		Connection conn = getConnection();		
		try {
			new DBPi4jPin(conn).deleteItem(idpi4jpin);
		} catch (SQLException e) {
			setErrorMessage("Could not delete pi4jpin id:" + idpi4jpin);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("setup.hardware.pi4jPin.navigator.ViewPi4jPin");
				
		return true;
		
		
	}

}

