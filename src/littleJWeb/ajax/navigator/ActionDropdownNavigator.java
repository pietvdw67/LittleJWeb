package littleJWeb.ajax.navigator;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItem;
import littleJ.hardware.dto.ItemDTO;
import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;
import littleJWeb.web.service.DropdownService;

@SuppressWarnings("serial")
public class ActionDropdownNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		String dropdownvalue = getParm(req,"actionValue");
		
		Connection conn = getConnection();
		List<DropdownDTO> dropdownList = null;
		
		try {
			ItemDTO itemDTO = new DBItem(conn).getItem(Integer.parseInt(dropdownvalue));
			if (itemDTO.getItemTypeDTO().isOutput()){
				dropdownList = DropdownService.getDropdownActions();
			} else {
				dropdownList = DropdownService.getDropdownActionsInput();
			}
		} catch (Exception e) {
			setErrorMessage("Could not update esp status");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("dropdownList", dropdownList);
		
		return true;
	}

}
