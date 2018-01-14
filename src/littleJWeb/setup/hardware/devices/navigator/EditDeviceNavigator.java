package littleJWeb.setup.hardware.devices.navigator;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDevice;
import littleJ.hardware.dto.DeviceDTO;
import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;
import littleJWeb.web.service.DropdownService;

@SuppressWarnings("serial")
public class EditDeviceNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idDevice = Integer.valueOf(getParm(req,"idDevice"));
		DeviceDTO deviceDTO = null;
		List<DropdownDTO> deviceTypeDropdown = null;
		
		Connection conn = getConnection();		
		try {
			deviceDTO = new DBDevice(conn).getItem(idDevice);
			deviceTypeDropdown = DropdownService.getDropdownDeviceType(conn);
		} catch (Exception e) {
			setErrorMessage("Exception occurred");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("deviceDTO",deviceDTO);		
		storeObject("deviceTypeDropdown",deviceTypeDropdown);
		
		return true;
	}

}
