package littleJWeb.views.sceneItem.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSceneItem;
import littleJ.views.dto.SceneItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddSceneItemInsertNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		SceneItemDTO sceneItemDTO = new SceneItemDTO();
		int idScene = Integer.parseInt(getParm(req,"idscene"));
		int idItem = Integer.parseInt(getParm(req,"idItem"));
		int action = Integer.parseInt(getParm(req,"action"));
		
		sceneItemDTO.setAction(action);
		sceneItemDTO.setDelay(getParm(req,"delay"));
		sceneItemDTO.setIdItem(idItem);
		sceneItemDTO.setIdScene(idScene);

		Connection conn = getConnection();		
		try {
			new DBSceneItem(conn).addItem(sceneItemDTO);
		} catch (SQLException e) {
			setErrorMessage("Could not add scene item");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("views.scene.navigator.SceneList");
		
		return true;
		
	}

}



