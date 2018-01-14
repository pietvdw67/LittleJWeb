package littleJWeb.setup.hardware.devices.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDevice;
import littleJ.hardware.dto.DeviceDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditDeviceUpdateNavigator extends Navigator {
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {

		DeviceDTO deviceDTO = new DeviceDTO();
		deviceDTO.setIdDevice(Integer.parseInt(getParm(req,"idDevice")));
		deviceDTO.setDescription(getParm(req,"description"));
		deviceDTO.setIdDeviceType(Integer.parseInt(getParm(req,"iddeviceType")));
		String ip = getParm(req,"ip");
		if (ip != null){
			deviceDTO.setIp(ip);
		}

		Connection conn = getConnection();
		try {
			new DBDevice(conn).updateItem(deviceDTO);
		} catch (SQLException e) {
			setErrorMessage("Could not update device id:" + deviceDTO.getIdDevice());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("setup.hardware.devices.navigator.ViewDevice");
		return true;
	}

}
