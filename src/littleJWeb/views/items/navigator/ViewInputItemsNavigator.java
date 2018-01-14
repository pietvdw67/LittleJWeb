package littleJWeb.views.items.navigator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItem;
import littleJ.hardware.dto.ItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ViewInputItemsNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		this.setMainPopulatingNavigator("views.items.navigator.ViewInputItems");
		List<ItemDTO> itemDTOlist = null;
		Connection conn = getConnection();		
		try {
			itemDTOlist =  new DBItem(conn).getInputItems();
			
		} catch (SQLException e) {
			setErrorMessage("Could not retrieve items");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("itemDTOlist",itemDTOlist);
				
		return true;
	}
}
