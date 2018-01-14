package littleJWeb.views.scene.navigator;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBScene;
import littleJ.views.dto.SceneDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class SceneListNavigator extends Navigator{
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		setMainPopulatingNavigator("views.scene.navigator.SceneList");
		List<SceneDTO> sceneDTOlist = null;
		
		Connection conn = getConnection();	
		String sceneImagesPath = "images/base/";
		try {
			sceneDTOlist = new DBScene(conn).getAllItems();		
			
			for (SceneDTO scenDTO : sceneDTOlist ){
				scenDTO.setImagePath(sceneImagesPath + "scene.png");
			}
			
		}catch (Exception e) {
			setErrorMessage("Exception occurred");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("sceneDTOlist",sceneDTOlist);
		
		return true;
	}

}


