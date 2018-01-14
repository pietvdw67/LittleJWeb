package littleJWeb.views.items.navigator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItem;
import littleJ.database.DBScene;
import littleJ.hardware.dto.ItemDTO;
import littleJ.views.dto.SceneDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ItemFilterNavigator extends Navigator{
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		String filterType = getParm(req,"filterType");		
		
		this.setMainPopulatingNavigator("views.items.navigator.ItemFilter&filterType=" + filterType);
		
		int idItemType = 0;
		if (getParm(req,"iditemtype") != null && getParm(req,"iditemtype").length()>0){
			idItemType =  Integer.valueOf(getParm(req,"iditemtype"));
		}

		int idZone = 0;
		if (getParm(req,"idzone") != null && getParm(req,"idzone").length()>0){
			idZone =  Integer.valueOf(getParm(req,"idzone"));
		}
		
		List<ItemDTO> itemDTOlist = new ArrayList<>();
		List<SceneDTO> sceneDTOlist = new ArrayList<>();
		String imagesBasePath = "images/base/";
		
		Connection conn = getConnection();		
		try {
			if (filterType.equalsIgnoreCase("itemtype")){				
				this.setMainPopulatingNavigator("views.items.navigator.ItemFilter&filterType=itemtype&iditemtype=" + idItemType);				
				itemDTOlist = new DBItem(conn).getOutputItemsByItemType(idItemType);
			} else if (filterType.equalsIgnoreCase("zone")){				
				this.setMainPopulatingNavigator("views.items.navigator.ItemFilter&filterType=zone&idzone=" + idZone);
				itemDTOlist = new DBItem(conn).getOutputItemsByZone(idZone);
			} else if (filterType.equalsIgnoreCase("favourite")){
				itemDTOlist = new DBItem(conn).getOutputItemsFavourites();
				sceneDTOlist = new DBScene(conn).getScenesFavourites();
			}		
			else {
				itemDTOlist = new DBItem(conn).getOutputItems();
			}
			
			for (SceneDTO sceneDTO : sceneDTOlist){
				sceneDTO.setImagePath(imagesBasePath + "scene.png");	
			}			
			
		}catch (Exception e) {
			setErrorMessage("Exception occurred");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("pageRefreshInterval",getSettingsProperties().getPageRefresh());
		storeObject("filterType",filterType);
		storeObject("itemDTOlist",itemDTOlist);
		storeObject("sceneDTOlist",sceneDTOlist);	
		storeObject("iditemtype",idItemType);
		storeObject("idzone",idZone);
		
		return true;
	}

}
