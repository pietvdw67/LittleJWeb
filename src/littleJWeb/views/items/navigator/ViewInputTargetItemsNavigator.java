package littleJWeb.views.items.navigator;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBInputTargetItem;
import littleJ.database.DBItem;
import littleJ.hardware.dto.ItemDTO;
import littleJ.views.dto.InputTargetItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ViewInputTargetItemsNavigator extends Navigator{
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {		
		int iditem = Integer.valueOf(getParm(req,"idItem"));
		addPopulatingNavigator("views.items.navigator.ViewInputTargetItems&idItem=" + iditem);
		List<InputTargetItemDTO> inputTargetItemDTOlist = null;
		ItemDTO itemDTO = null;
		
		Connection conn = getConnection();		
		
		try {
			inputTargetItemDTOlist = new DBInputTargetItem(conn).getItemsBySourceItem(iditem);
			itemDTO = new DBItem(conn).getItem(iditem);
		}catch (Exception e) {
			setErrorMessage("Exception occurred");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("inputTargetItemDTOlist",inputTargetItemDTOlist);
		storeObject("itemDTO",itemDTO);
		
		return true;
	}

}



