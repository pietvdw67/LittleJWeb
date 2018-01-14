package littleJWeb.setup.hardware.items.navigator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItem;
import littleJ.hardware.dto.ItemDTO;
import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;
import littleJWeb.web.service.DropdownService;

@SuppressWarnings("serial")
public class EditItemNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idItem = Integer.valueOf(getParm(req,"idItem"));
		ItemDTO itemDTO = null;
		List<DropdownDTO> zoneDropdownList = new ArrayList<>();
		List<DropdownDTO> itemTypeDropdownList = new ArrayList<>();
		List<DropdownDTO> deviceDropdownList = new ArrayList<>();
		List<DropdownDTO> pi4jPinDropdownList = new ArrayList<>();
		String doTick="no";
		String doTickFavourite="no";

		Connection conn = getConnection();
		try {
			itemDTO = new DBItem(conn).getItem(idItem);
			zoneDropdownList = DropdownService.getDropdownZone(conn);
			itemTypeDropdownList = DropdownService.getDropdownItemType(conn);
			deviceDropdownList = DropdownService.getDropdownDevice(conn);
			pi4jPinDropdownList = DropdownService.getDropdownPi4jPin(conn);
		} catch (Exception e) {
			setErrorMessage("Could not retrieve item id:" + idItem);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		if (itemDTO.isAtive()){
			doTick = "yes";
		}
		if (itemDTO.isFavourite()){
			doTickFavourite = "yes";
		}
		
		storeObject("isActive", doTick);
		storeObject("isFavourite", doTickFavourite);
		storeObject("itemDTO", itemDTO);
		storeObject("zoneDropdownList", zoneDropdownList);
		storeObject("itemTypeDropdownList", itemTypeDropdownList);
		storeObject("deviceDropdownList", deviceDropdownList);
		storeObject("pi4jPinDropdownList", pi4jPinDropdownList);

		return true;
	}

}
