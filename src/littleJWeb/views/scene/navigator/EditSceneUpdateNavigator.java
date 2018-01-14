package littleJWeb.views.scene.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBScene;
import littleJ.views.dto.SceneDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditSceneUpdateNavigator extends Navigator {
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {

		SceneDTO sceneDTO = new SceneDTO();
		sceneDTO.setIdScene(Integer.parseInt(getParm(req,"idscene")));
		sceneDTO.setDescription(getParm(req,"description"));
		
		String isFavouriteParm = getParm(req,"isFavourite");
		if (isFavouriteParm != null && isFavouriteParm.trim().length()>0){
			sceneDTO.setFavourite(true);
		}

		Connection conn = getConnection();
		try {
			new DBScene(conn).updateItem(sceneDTO);			
		} catch (SQLException e) {
			setErrorMessage("Could not update scene id:" + sceneDTO.getIdScene());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("views.scene.navigator.SceneList");
		return true;
	}

}
