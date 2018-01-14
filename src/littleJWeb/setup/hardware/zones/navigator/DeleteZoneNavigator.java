package littleJWeb.setup.hardware.zones.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBZone;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class DeleteZoneNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idZone = Integer.parseInt(getParm(req,"idZone"));
		
		Connection conn = getConnection();		
		try {
			new DBZone(conn).deleteItem(idZone);;
		} catch (SQLException e) {
			setErrorMessage("Could not delete zone id:" + idZone);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("setup.hardware.zones.navigator.ViewZones");
				
		return true;
		
		
	}

}

