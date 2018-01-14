package littleJWeb.views.sceneItem.navigator;

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
public class AddCustomSceneItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		String type = this.getParm(req, "type");
		int id = Integer.parseInt(this.getParm(req, "id"));

		ItemDTO itemDTO = new ItemDTO();
		List<DropdownDTO> sceneDropdownList = new ArrayList<>();
		List<DropdownDTO> itemActionDropdownList = new ArrayList<>();
				
		Connection conn = getConnection();		
		try{
			sceneDropdownList = DropdownService.getDropdownScenes(conn);
			itemActionDropdownList = DropdownService.getDropdownActions();
			if (type.equalsIgnoreCase("item")){
				itemDTO = new DBItem(conn).getItem(id);
			}
		} catch (Exception e) {
			setErrorMessage("Could not retrieve dropdown values");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("type",type);
		storeObject("id",id);
		storeObject("itemDTO",itemDTO);
		storeObject("sceneDropdownList",sceneDropdownList);
		storeObject("itemActionDropdownList",itemActionDropdownList);
		
		return true;
	}

}



