package littleJWeb.setup.hardware.deviceTypes.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDeviceType;
import littleJ.hardware.dto.DeviceTypeDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditDeviceTypesNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idDeviceType = Integer.valueOf(getParm(req,"iddevicetype"));
		DeviceTypeDTO deviceTypeDTO = null;
		
		Connection conn = getConnection();		
		try {
			deviceTypeDTO = new DBDeviceType(conn).getItem(idDeviceType);
		} catch (SQLException e) {
			setErrorMessage("Could not retrieve device type id:" + idDeviceType);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("deviceTypeDTO",deviceTypeDTO);		
		
		return true;
	}

}
