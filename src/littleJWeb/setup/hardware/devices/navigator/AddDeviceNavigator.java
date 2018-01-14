package littleJWeb.setup.hardware.devices.navigator;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;
import littleJWeb.web.service.DropdownService;

@SuppressWarnings("serial")
public class AddDeviceNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		List<DropdownDTO> deviceTypeDropdown = null;
		
		Connection conn = getConnection();		
		try {
			deviceTypeDropdown = DropdownService.getDropdownDeviceType(conn);
		} catch (Exception e) {
			setErrorMessage("Could not retrieve dropdown items");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("deviceTypeDropdown",deviceTypeDropdown);	
		
		
		return true;
	}

}
