package littleJWeb.views.scene.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBScene;
import littleJ.views.dto.SceneDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddSceneInsertNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		SceneDTO sceneDTO = new SceneDTO();
		sceneDTO.setDescription(getParm(req,"description"));
		
		String isFavourite = getParm(req,"isFavourite");
		if(isFavourite != null && isFavourite.length()> 0){
			sceneDTO.setFavourite(true);
		}

		Connection conn = getConnection();		
		try {
			new DBScene(conn).addItem(sceneDTO);			
		} catch (SQLException e) {
			setErrorMessage("Could not add scene: " + sceneDTO.getDescription());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("views.scene.navigator.SceneList");
		
		return true;
		
	}

}
