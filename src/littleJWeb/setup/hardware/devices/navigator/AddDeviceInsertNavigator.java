package littleJWeb.setup.hardware.devices.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDevice;
import littleJ.hardware.dto.DeviceDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddDeviceInsertNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		DeviceDTO deviceDTO = new DeviceDTO();
		deviceDTO.setDescription(getParm(req,"description"));
		deviceDTO.setIp(getParm(req,"ip"));
		deviceDTO.setIdDeviceType(Integer.parseInt(getParm(req,"iddeviceType")));

		Connection conn = getConnection();		
		try {
			new DBDevice(conn).addItem(deviceDTO);
		} catch (SQLException e) {
			setErrorMessage("Could not add device: " + deviceDTO.getDescription());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("setup.hardware.devices.navigator.ViewDevice");
		
		return true;
		
	}

}

