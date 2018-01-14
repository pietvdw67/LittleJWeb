package littleJWeb.views.scene.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBScene;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class DeleteSceneNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idScene = Integer.parseInt(getParm(req,"idscene"));
		
		Connection conn = getConnection();		
		try {
			new DBScene(conn).deleteItem(idScene);			
		} catch (SQLException e) {
			setErrorMessage("Could not delete scene id:" + idScene);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("views.scene.navigator.SceneList");
				
		return true;
		
		
	}

}


