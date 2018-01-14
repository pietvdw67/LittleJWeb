package littleJWeb.esp.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDevice;
import littleJ.database.DBEspStatus;
import littleJ.esp.dto.EspStatusDTO;
import littleJ.hardware.dto.DeviceDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EspStatusNavigator extends Navigator  {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("name");
		String status = "0";
		
		Connection conn = getConnection();		
		try {
			DeviceDTO deviceDTO = new DBDevice(conn).getDeviceByDescription(name);
			if (deviceDTO != null){
				EspStatusDTO espStatusDTO = new DBEspStatus(conn).getByDevideId(deviceDTO.getIdDevice());
				if (espStatusDTO != null){
					status = String.valueOf(espStatusDTO.getStatus());
				}
			}			
		} catch (SQLException e) {
			setErrorMessage("Could not update esp status");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("status",status);
		
		return true;
	}

}


