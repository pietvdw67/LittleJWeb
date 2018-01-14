package littleJWeb.setup.hardware.deviceTypes.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDeviceType;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class DeleteDeviceTypesNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idDeviceType = Integer.parseInt(getParm(req,"iddevicetype"));
		
		Connection conn = getConnection();		
		try {
			new DBDeviceType(conn).deleteItem(idDeviceType);
		} catch (SQLException e) {
			setErrorMessage("Could not delete device type id:" + idDeviceType);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("setup.hardware.deviceTypes.navigator.ViewDeviceTypes");
				
		return true;
		
		
	}

}
