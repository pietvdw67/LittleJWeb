package littleJWeb.views.sceneItem.navigator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSceneItem;
import littleJ.views.dto.SceneItemDTO;
import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;
import littleJWeb.web.service.DropdownService;

@SuppressWarnings("serial")
public class EditSceneItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idSceneItem = Integer.valueOf(getParm(req,"idsceneitem"));
		SceneItemDTO sceneItemDTO = null;
		List<DropdownDTO> itemDropdownList = new ArrayList<>();
		List<DropdownDTO> itemActionDropdownList = new ArrayList<>();

		Connection conn = getConnection();
		try {
			sceneItemDTO = new DBSceneItem(conn).getItem(idSceneItem);
			itemDropdownList = DropdownService.getDropdownItems(conn);
			if (sceneItemDTO.getItemDTO().getItemTypeDTO().isOutput()){
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

		storeObject("sceneItemDTO", sceneItemDTO);
		storeObject("itemDropdownList", itemDropdownList);
		storeObject("itemActionDropdownList", itemActionDropdownList);

		return true;
	}

}
