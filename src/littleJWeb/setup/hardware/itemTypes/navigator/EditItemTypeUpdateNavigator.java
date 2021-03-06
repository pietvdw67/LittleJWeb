package littleJWeb.setup.hardware.itemTypes.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItemType;
import littleJ.hardware.dto.ItemTypeDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditItemTypeUpdateNavigator extends Navigator {
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {

		ItemTypeDTO itemTypeDTO = new ItemTypeDTO();
		itemTypeDTO.setIdItemType(Integer.parseInt(getParm(req,"idItemType")));
		itemTypeDTO.setItemTypeName(getParm(req,"itemtypename"));
		itemTypeDTO.setItemTypeImage(getParm(req,"itemtypeimage"));
		
		String isOutputParm = getParm(req,"isoutput");
		if (isOutputParm != null && isOutputParm.trim().length()>0){
			itemTypeDTO.setOutput(true);
		}

		Connection conn = getConnection();
		try {
			new DBItemType(conn).updateItem(itemTypeDTO);
		} catch (SQLException e) {
			setErrorMessage("Could not update item type id:" + itemTypeDTO.getIdItemType());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("setup.hardware.itemTypes.navigator.ViewItemType");
		return true;
	}

}
