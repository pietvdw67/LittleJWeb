package littleJWeb.views.sceneItem.navigator;


import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSceneItem;
import littleJ.views.dto.SceneItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditSceneItemUpdateNavigator extends Navigator {
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {

		SceneItemDTO sceneItemDTO = new SceneItemDTO();
		sceneItemDTO.setIdSceneItem(Integer.parseInt(getParm(req,"idsceneitem")));
		sceneItemDTO.setIdItem(Integer.parseInt(getParm(req,"idItem")));
		sceneItemDTO.setIdScene(Integer.parseInt(getParm(req,"idscene")));
		sceneItemDTO.setAction(Integer.parseInt(getParm(req,"action")));
		sceneItemDTO.setDelay(getParm(req,"delay"));

		Connection conn = getConnection();
		try {
			new DBSceneItem(conn).updateItem(sceneItemDTO);			
		} catch (SQLException e) {
			setErrorMessage("Could not update sceneitem");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("views.scene.navigator.SceneList");
		return true;
	}

}

