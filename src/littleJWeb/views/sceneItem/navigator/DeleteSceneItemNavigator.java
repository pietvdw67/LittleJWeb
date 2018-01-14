package littleJWeb.views.sceneItem.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSceneItem;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class DeleteSceneItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idSceneItem = Integer.parseInt(getParm(req,"idsceneitem"));
		
		Connection conn = getConnection();		
		try {
			new DBSceneItem(conn).deleteItem(idSceneItem);		
		} catch (SQLException e) {
			setErrorMessage("Could not delete scene item id:" + idSceneItem);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		//setAlternativeNav("views.scene.navigator.SceneList");
		setAlternativeNav("cancel");
				
		return true;
		
		
	}

}


