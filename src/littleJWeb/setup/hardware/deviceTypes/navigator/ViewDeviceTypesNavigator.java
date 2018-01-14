package littleJWeb.setup.hardware.deviceTypes.navigator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDeviceType;
import littleJ.hardware.dto.DeviceTypeDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ViewDeviceTypesNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		addPopulatingNavigator("setup.hardware.deviceTypes.navigator.ViewDeviceTypes");
		List<DeviceTypeDTO> deviceTypeDTOlist = null;
		Connection conn = getConnection();		
		try {
			deviceTypeDTOlist = new DBDeviceType(conn).getAllItems();
		} catch (SQLException e) {
			setErrorMessage("Could not retrieve device types");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("deviceTypeList",deviceTypeDTOlist);
				
		return true;
	}
}
