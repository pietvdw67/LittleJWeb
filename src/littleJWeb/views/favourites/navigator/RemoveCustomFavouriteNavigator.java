package littleJWeb.views.favourites.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItem;
import littleJ.database.DBScene;
import littleJ.hardware.dto.ItemDTO;
import littleJ.views.dto.SceneDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class RemoveCustomFavouriteNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		String id = getParm(req, "id");
		String type = getParm(req, "type");
		
		Connection conn = getConnection();
		try {
			DBItem dbitem = new DBItem(conn);
			if (type.equalsIgnoreCase("item")){
				ItemDTO itemDTO = dbitem.getItem(Integer.parseInt(id));
				itemDTO.setFavourite(false);
				dbitem.updateItem(itemDTO);
			} else if (type.equalsIgnoreCase("scene")){
				SceneDTO sceneDTO = new DBScene(conn).getItem(Integer.parseInt(id));
				sceneDTO.setFavourite(false);
				new DBScene(conn).updateItem(sceneDTO);
			}

		} catch (SQLException e) {
			setErrorMessage("Could not update favourite");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("cancel");

		return true;

	}

}

