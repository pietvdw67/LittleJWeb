package littleJWeb.setup.hardware.devices.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDevice;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class DeleteDeviceNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idDevice = Integer.parseInt(getParm(req,"idDevice"));
		
		Connection conn = getConnection();		
		try {
			new DBDevice(conn).deleteItem(idDevice);
		} catch (SQLException e) {
			setErrorMessage("Could not delete device id:" + idDevice);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("setup.hardware.devices.navigator.ViewDevice");
				
		return true;
		
		
	}

}
