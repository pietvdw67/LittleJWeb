package littleJWeb.views.sceneItem.navigator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBScene;
import littleJ.views.dto.SceneDTO;
import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;
import littleJWeb.web.service.DropdownService;

@SuppressWarnings("serial")
public class AddSceneItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idScene = Integer.valueOf(getParm(req,"idscene"));
		
		SceneDTO sceneDTO = null;
		List<DropdownDTO> itemDropdownList = new ArrayList<>();
		List<DropdownDTO> itemActionDropdownList = new ArrayList<>();
				
		Connection conn = getConnection();		
		try{
			sceneDTO = new DBScene(conn).getItem(idScene);
			itemDropdownList = DropdownService.getDropdownItems(conn);
			itemActionDropdownList = DropdownService.getDropdownActions();
		} catch (Exception e) {
			setErrorMessage("Could not retrieve dropdown values");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
	
		storeObject("sceneDTO",sceneDTO);
		storeObject("itemDropdownList",itemDropdownList);
		storeObject("itemActionDropdownList",itemActionDropdownList);
		
		return true;
	}

}


