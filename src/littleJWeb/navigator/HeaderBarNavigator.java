package littleJWeb.navigator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDevice;
import littleJ.database.DBItem;
import littleJ.database.DBItemType;
import littleJ.hardware.dto.DeviceDTO;
import littleJ.hardware.dto.ItemDTO;
import littleJ.hardware.dto.ItemTypeDTO;
import littleJ.service.LittleJHandler;
import littleJWeb.DTO.HeaderBarDTO;

@SuppressWarnings("serial")
public class HeaderBarNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		Connection conn = getConnection();
		List<HeaderBarDTO> headerBarDTOlist = new ArrayList<>();
		
		try {
			// Raspberry temperature
			if (getSettingsProperties().getDeviceCode().length()>0){
				DeviceDTO deviceDTO = new DBDevice(conn).getDeviceByDescription(getSettingsProperties().getDeviceCode());
				if (deviceDTO != null){
					if (deviceDTO.getTemperature() > getSettingsProperties().getRaspberryMaxTemp()){
						String message = "The raspberry is exeeding it's max temperature. Currently at: " + deviceDTO.getTemperature();
						storeObject("alert",message);
					}
				}
			}			
			
			List<ItemTypeDTO> itemtypeDTOlist = new DBItemType(conn).getAllItems();
			String imagesPathBase = "images/base/";

			HeaderBarDTO headerBarDTO = new HeaderBarDTO();						

			headerBarDTO.setLabel("Favourites");
			headerBarDTO.setImageUrl(imagesPathBase + "favourites.png");
			headerBarDTO.setUrl("Controller.do?nav=views.items.navigator.ItemFilter&filterType=favourite");			
			headerBarDTOlist.add(headerBarDTO);
			
			headerBarDTO = new HeaderBarDTO();
			headerBarDTO.setLabel("Scene");
			headerBarDTO.setImageUrl(imagesPathBase + "scene.png");
			headerBarDTO.setUrl("Controller.do?nav=views.scene.navigator.SceneList");			
			headerBarDTOlist.add(headerBarDTO);
			
			headerBarDTO = new HeaderBarDTO();
			headerBarDTO.setLabel("Zones");
			headerBarDTO.setImageUrl(imagesPathBase + "zone.png");
			headerBarDTO.setUrl("Controller.do?nav=views.zones.navigator.ZoneList");	
			headerBarDTOlist.add(headerBarDTO);
			
			for (ItemTypeDTO itemTypeDTO : itemtypeDTOlist){
				// Check if an item of this type exist
				List<ItemDTO> itemDTOlist = new DBItem(conn).getItemsByItemType(itemTypeDTO.getIdItemType());
				if (itemDTOlist == null || itemDTOlist.size() == 0){
					continue;
				}
				
				// Do not add input items
				if (!itemTypeDTO.isOutput()){
					continue;
				}
				
				headerBarDTO = new HeaderBarDTO();
				headerBarDTO.setLabel(itemTypeDTO.getItemTypeName());				
				headerBarDTO.setImageUrl(itemTypeDTO.getImagePathOn());
				headerBarDTO.setUrl("Controller.do?nav=views.items.navigator.ItemFilter&filterType=itemtype&iditemtype=" + itemTypeDTO.getIdItemType());	
				headerBarDTOlist.add(headerBarDTO);
			}
			
		} catch (Exception e) {
			setErrorMessage("Exception occurred");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}		
		
		storeObject("headerBarDTOlist",headerBarDTOlist);
		
		return true;
	}

}
