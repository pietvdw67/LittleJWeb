package littleJWeb.setup.hardware.deviceTypes.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDeviceType;
import littleJ.hardware.dto.DeviceTypeDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditDeviceTypesUpdateNavigator extends Navigator {
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {

		DeviceTypeDTO deviceTypeDTO = new DeviceTypeDTO();
		deviceTypeDTO.setIdDeviceType(Integer.parseInt(getParm(req,"idDeviceType")));
		deviceTypeDTO.setDescription(getParm(req,"description"));

		Connection conn = getConnection();
		try {
			new DBDeviceType(conn).updateItem(deviceTypeDTO);
		} catch (SQLException e) {
			setErrorMessage("Could not update device Type id:" + deviceTypeDTO.getIdDeviceType());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("setup.hardware.deviceTypes.navigator.ViewDeviceTypes");
		return true;
	}

}
