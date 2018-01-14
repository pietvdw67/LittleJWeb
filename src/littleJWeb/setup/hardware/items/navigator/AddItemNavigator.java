package littleJWeb.setup.hardware.items.navigator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;
import littleJWeb.web.service.DropdownService;

@SuppressWarnings("serial")
public class AddItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		List<DropdownDTO> zoneDropdownList = new ArrayList<>();
		List<DropdownDTO> itemTypeDropdownList = new ArrayList<>();
		List<DropdownDTO> deviceDropdownList = new ArrayList<>();
		List<DropdownDTO> pi4jPinDropdownList = new ArrayList<>();
				
		Connection conn = getConnection();		
		try{
			zoneDropdownList = DropdownService.getDropdownZone(conn); 
			itemTypeDropdownList = DropdownService.getDropdownItemType(conn);
			deviceDropdownList = DropdownService.getDropdownDevice(conn);
			pi4jPinDropdownList = DropdownService.getDropdownPi4jPin(conn);
		
		} catch (Exception e) {
			setErrorMessage("Could not retrieve dropdown values");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
	
		storeObject("zoneDropdownList",zoneDropdownList);
		storeObject("itemTypeDropdownList",itemTypeDropdownList);
		storeObject("deviceDropdownList",deviceDropdownList);
		storeObject("pi4jPinDropdownList",pi4jPinDropdownList);
		
		return true;
	}

}

