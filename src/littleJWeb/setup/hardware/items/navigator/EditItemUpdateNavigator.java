package littleJWeb.setup.hardware.items.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItem;
import littleJ.hardware.dto.ItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditItemUpdateNavigator extends Navigator {
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {

		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setIdItem(Integer.parseInt(getParm(req,"idItem")));
		itemDTO.setDescription(getParm(req,"description"));
		itemDTO.setIdItemType(Integer.parseInt(getParm(req,"idItemType")));
		itemDTO.setIdZone(Integer.parseInt(getParm(req,"idZone")));
		itemDTO.setIdDevice(Integer.parseInt(getParm(req,"idDevice")));
		itemDTO.setIdPi4jPin(Integer.parseInt(getParm(req,"idPi4jPin")));
		
		String isActiveParm = getParm(req,"isActive");
		if (isActiveParm != null && isActiveParm.trim().length()>0){
			itemDTO.setAtive(true);
		}
		
		String isFavouriteParm = getParm(req,"isFavourite");
		if (isFavouriteParm != null && isFavouriteParm.trim().length()>0){
			itemDTO.setFavourite(true);
		}

		Connection conn = getConnection();
		try {
			new DBItem(conn).updateItem(itemDTO);
		} catch (SQLException e) {
			setErrorMessage("Could not update item id:" + itemDTO.getIdItem());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("cancel");
		return true;
	}

}

