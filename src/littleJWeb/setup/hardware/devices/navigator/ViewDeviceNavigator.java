package littleJWeb.setup.hardware.devices.navigator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDevice;
import littleJ.hardware.dto.DeviceDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ViewDeviceNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		addPopulatingNavigator("setup.hardware.devices.navigator.ViewDevice");
		List<DeviceDTO> deviceDTOlist = null;
		Connection conn = getConnection();		
		try {
			deviceDTOlist = new DBDevice(conn).getAllItems();
		} catch (SQLException e) {
			setErrorMessage("Could not retrieve devices");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("deviceDTOlist",deviceDTOlist);
				
		return true;
	}
}
