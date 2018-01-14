package littleJWeb.views.items.navigator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBInputTargetItem;
import littleJ.views.dto.InputTargetItemDTO;
import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;
import littleJWeb.web.service.DropdownService;

@SuppressWarnings("serial")
public class EditInputTargetItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idInputTargetItem = Integer.parseInt(getParm(req, "idinputtargetitem"));		
		InputTargetItemDTO inputTargetItemDTO = null;		
		List<DropdownDTO> targetItemDropdownList = new ArrayList<>();
		List<DropdownDTO> itemActionDropdownList = new ArrayList<>();
				
		Connection conn = getConnection();		
		try{
			inputTargetItemDTO = new DBInputTargetItem(conn).getItem(idInputTargetItem);
			targetItemDropdownList = DropdownService.getDropdownItems(conn);
			
			if (inputTargetItemDTO.getTargetItem().getItemTypeDTO().isOutput()){
				itemActionDropdownList = DropdownService.getDropdownActions();
			} else {
				itemActionDropdownList = DropdownService.getDropdownActionsInput();
			}
		} catch (Exception e) {
			setErrorMessage("Could not retrieve dropdown values");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("inputTargetItemDTO",inputTargetItemDTO);
		storeObject("targetItemDropdownList",targetItemDropdownList);
		storeObject("itemActionDropdownList",itemActionDropdownList);
		
		return true;
	}

}




