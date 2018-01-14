package littleJWeb.views.items.navigator;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItem;
import littleJ.database.DBScene;
import littleJ.hardware.dto.ItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ItemListDisplayNavigator extends Navigator {
	@SuppressWarnings("unchecked")
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		List<ItemDTO> itemDTOlist = null;
		Connection conn = getConnection();

		String filterType = getParm(req, "filterType");
		
		int idItemType = 0;
		if (getParm(req,"iditemtype") != null && getParm(req,"iditemtype").length()>0){
			idItemType =  Integer.valueOf(getParm(req,"iditemtype"));
		}

		int idZone = 0;
		if (getParm(req,"idzone") != null && getParm(req,"idzone").length()>0){
			idZone =  Integer.valueOf(getParm(req,"idzone"));
		}


		try {
			if (filterType.equalsIgnoreCase("itemtype")) {				
				this.setMainPopulatingNavigator(
						"views.items.navigator.ItemFilter&filterType=itemtype&iditemtype=" + idItemType);
				itemDTOlist = new DBItem(conn).getOutputItemsByItemType(idItemType);
			} else if (filterType.equalsIgnoreCase("zone")) {				
				this.setMainPopulatingNavigator("views.items.navigator.ItemFilter&filterType=zone&idzone=" + idZone);
				itemDTOlist = new DBItem(conn).getOutputItemsByZone(idZone);
			} else if (filterType.equalsIgnoreCase("favourite")) {
				itemDTOlist = new DBItem(conn).getOutputItemsFavourites();
			} else {
				itemDTOlist = new DBItem(conn).getOutputItems();
			}
		} catch (Exception e) {
			setErrorMessage("Exception occurred");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		storeObject("itemDTOlist", itemDTOlist);
		storeObject("showImage", getParm(req, "showImage"));
		storeObject("showLabel", getParm(req, "showLabel"));
		storeObject("filterType", getParm(req, "filterType"));

		return true;
	}

}
