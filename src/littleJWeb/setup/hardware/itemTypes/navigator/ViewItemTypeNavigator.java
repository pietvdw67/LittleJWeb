package littleJWeb.setup.hardware.itemTypes.navigator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItemType;
import littleJ.hardware.dto.ItemTypeDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ViewItemTypeNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		addPopulatingNavigator("setup.hardware.itemTypes.navigator.ViewItemType");
		List<ItemTypeDTO> itemTypeDTOList = null;
		Connection conn = getConnection();		
		try {
			itemTypeDTOList = new DBItemType(conn).getAllItems();
						
		} catch (SQLException e) {
			setErrorMessage("Could not retrieve itemtypes");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("itemTypeDTOList",itemTypeDTOList);
				
		return true;
	}
}
