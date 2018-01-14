package littleJWeb.esp.navigator;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBDevice;
import littleJ.database.DBInputTargetItem;
import littleJ.database.DBItem;
import littleJ.database.DBStatusChange;
import littleJ.hardware.dto.DeviceDTO;
import littleJ.hardware.dto.ItemDTO;
import littleJ.processors.statuschange.dto.StatusChangeDTO;
import littleJ.views.dto.InputTargetItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EspInputNavigator extends Navigator  {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		String name =  getParm(req, "name");
		String returnMessage = "";
		
		Connection conn = getConnection();		
		try {
			DeviceDTO deviceDTO = new DBDevice(conn).getDeviceByDescription(name);
			if (deviceDTO != null){
				List<ItemDTO> itemsList = new DBItem(conn).getItemsByDevice(deviceDTO.getIdDevice());
				DBInputTargetItem dbInputTargetItem = new DBInputTargetItem(conn);				
				
				for (ItemDTO itemDTO : itemsList){
					if (!itemDTO.isAtive()){
						returnMessage += "<p>Item " + itemDTO.getDescription() + " is disabled, no action to be taken</p>";
						continue;
					}
					List<InputTargetItemDTO> inputTargetItemDTOlist =  dbInputTargetItem.getItemsBySourceItem(itemDTO.getIdItem());
					
					// First remove all items with existing idsourceitem values
					new DBStatusChange(conn).removeByIdSourceItem(itemDTO.getIdItem());
					
					for (InputTargetItemDTO inputTargetItemDTO : inputTargetItemDTOlist){
						StatusChangeDTO statusChangeDTO = new StatusChangeDTO();
						statusChangeDTO.setAction(inputTargetItemDTO.getAction());
						statusChangeDTO.setIdItem(inputTargetItemDTO.getIdTargetItem());
						statusChangeDTO.setChangeTimestamp(LocalDateTime.now());
						statusChangeDTO.setIdschedule(-1);
						statusChangeDTO.setIdSourceItem(inputTargetItemDTO.getIdSourceItem());
						LocalDateTime ldt = LocalDateTime.now();
						
						if (inputTargetItemDTO.getDelay().toLowerCase().endsWith("h")){
							ldt = ldt.plusHours(Integer.parseInt(inputTargetItemDTO.getDelay().substring(0,inputTargetItemDTO.getDelay().length()-1)));
						} else if (inputTargetItemDTO.getDelay().toLowerCase().endsWith("m")){
							ldt = ldt.plusMinutes(Integer.parseInt(inputTargetItemDTO.getDelay().substring(0,inputTargetItemDTO.getDelay().length()-1)));
						} else if (inputTargetItemDTO.getDelay().toLowerCase().endsWith("s")){
							ldt = ldt.plusSeconds(Integer.parseInt(inputTargetItemDTO.getDelay().substring(0,inputTargetItemDTO.getDelay().length()-1)));
						} else {
							ldt = ldt.plusSeconds(Integer.parseInt(inputTargetItemDTO.getDelay()));
						}						
						
						statusChangeDTO.setProcesstime(ldt);
						
						new DBStatusChange(conn).addItem(statusChangeDTO);						
					}
					
					returnMessage += "<p>Item " + itemDTO.getDescription() + " Executed, output items will be actioned</p>";
				}
				
				
			} else {
				returnMessage += "Item not found";
			}
			
		} catch (SQLException e) {
			setErrorMessage("Could not update esp status");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("returnMessage",returnMessage);
		
		return true;
	}

}

