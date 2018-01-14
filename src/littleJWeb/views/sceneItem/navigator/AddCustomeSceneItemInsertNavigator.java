package littleJWeb.views.sceneItem.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBScene;
import littleJ.database.DBSceneItem;
import littleJ.views.dto.SceneDTO;
import littleJ.views.dto.SceneItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddCustomeSceneItemInsertNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int action = Integer.parseInt(getParm(req, "action"));
		String id = getParm(req, "id");
		String type = getParm(req, "type");
		String sceneDescription = getParm(req, "sceneDescription");
		String idScene = getParm(req, "idScene");
		SceneDTO sceneDTO = null;

		Connection conn = getConnection();
		try {
			
			// Create scene and retrieve the id
			if (sceneDescription.length() > 0) {
				sceneDTO = new SceneDTO();
				sceneDTO.setDescription(sceneDescription);
				new DBScene(conn).addItem(sceneDTO);
				sceneDTO = new DBScene(conn).getSceneByDescription(sceneDescription);
				idScene = String.valueOf(sceneDTO.getIdScene());
			} 

			SceneItemDTO sceneItemDTO = new SceneItemDTO();
			sceneItemDTO.setIdScene(Integer.valueOf(idScene));
			if (type.equalsIgnoreCase("item")){
				sceneItemDTO.setIdItem(Integer.valueOf(id));
			}
			sceneItemDTO.setDelay(getParm(req, "delay"));
			sceneItemDTO.setAction(action);
			new DBSceneItem(conn).addItem(sceneItemDTO);

		} catch (SQLException e) {
			setErrorMessage("Could not add scene item");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("cancel");

		return true;

	}

}
