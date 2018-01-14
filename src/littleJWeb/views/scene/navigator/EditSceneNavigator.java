package littleJWeb.views.scene.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBScene;
import littleJ.views.dto.SceneDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditSceneNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idScene = Integer.valueOf(getParm(req,"idscene"));
		SceneDTO sceneDTO = null;
		String doTickFavourite="no";
		
		Connection conn = getConnection();		
		try {
			sceneDTO = new DBScene(conn).getItem(idScene);
		} catch (SQLException e) {
			setErrorMessage("Could not retrieve scene id:" + idScene);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		if (sceneDTO.isFavourite()){
			doTickFavourite = "yes";
		}
		
		storeObject("sceneDTO",sceneDTO);	
		storeObject("isFavourite", doTickFavourite);
		
		return true;
	}

}
