package littleJWeb.views.items.navigator;

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
public class AddInputTargetItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idItem = Integer.parseInt(getParm(req, "iditem"));		
		ItemDTO sourceItemDTO = null;		
		List<DropdownDTO> targetItemDropdownList = new ArrayList<>();
		List<DropdownDTO> itemActionDropdownList = new ArrayList<>();
				
		Connection conn = getConnection();		
		try{
			sourceItemDTO =  new DBItem(conn).getItem(idItem);
			targetItemDropdownList = DropdownService.getDropdownItems(conn);
			itemActionDropdownList = DropdownService.getDropdownActions();
		} catch (Exception e) {
			setErrorMessage("Could not retrieve dropdown values");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("sourceItemDTO",sourceItemDTO);
		storeObject("targetItemDropdownList",targetItemDropdownList);
		storeObject("itemActionDropdownList",itemActionDropdownList);
		
		return true;
	}

}



