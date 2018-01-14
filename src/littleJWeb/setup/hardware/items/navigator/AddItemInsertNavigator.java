package littleJWeb.setup.hardware.items.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItem;
import littleJ.hardware.dto.ItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddItemInsertNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		ItemDTO itemDTO = new ItemDTO();
		itemDTO.setDescription(getParm(req,"description"));
		itemDTO.setIdItemType(Integer.parseInt(getParm(req,"idItemType")));
		itemDTO.setIdZone(Integer.parseInt(getParm(req,"idZone")));
		String isActive = getParm(req,"isActive");
		if(isActive != null && isActive.length()> 0){
			itemDTO.setAtive(true);
		}
		String isFavourite = getParm(req,"isFavourite");
		if(isFavourite != null && isFavourite.length()> 0){
			itemDTO.setFavourite(true);
		}
		itemDTO.setIdDevice(Integer.parseInt(getParm(req,"idDevice")));
		itemDTO.setIdPi4jPin(Integer.parseInt(getParm(req,"idPi4jPin")));

		Connection conn = getConnection();		
		try {
			new DBItem(conn).addItem(itemDTO);	
		} catch (SQLException e) {
			setErrorMessage("Could not add item: " + itemDTO.getDescription());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("setup.hardware.items.navigator.ViewItem");
		
		return true;
		
	}

}


