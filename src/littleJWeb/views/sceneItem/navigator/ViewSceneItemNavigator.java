package littleJWeb.views.sceneItem.navigator;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBScene;
import littleJ.database.DBSceneItem;
import littleJ.views.dto.SceneDTO;
import littleJ.views.dto.SceneItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ViewSceneItemNavigator extends Navigator{
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {		
		int idScene = Integer.valueOf(getParm(req,"idscene"));
		addPopulatingNavigator("views.sceneItem.navigator.ViewSceneItem&idscene=" + idScene);
		List<SceneItemDTO> sceneItemDTOlist = null;
		SceneDTO sceneDTO = null;
		
		Connection conn = getConnection();		
		
		try {
			sceneItemDTOlist = new DBSceneItem(conn).getSceneItemsByScene(idScene);	
			sceneDTO = new DBScene(conn).getItem(idScene);
			
		}catch (Exception e) {
			setErrorMessage("Exception occurred");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("sceneItemDTOlist",sceneItemDTOlist);
		storeObject("sceneDTO",sceneDTO);
		
		return true;
	}

}


