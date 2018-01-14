package littleJWeb.views.scheduleItem.navigator;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.LittleJUtils;
import littleJ.database.DBScheduleItem;
import littleJ.views.dto.ScheduleDTO;
import littleJ.views.dto.ScheduleItemDTO;
import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;
import littleJWeb.web.service.DropdownService;

@SuppressWarnings("serial")
public class EditScheduleItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {		
		int idScheduleItem = Integer.valueOf(getParm(req,"idsheduleitem"));	
		ScheduleItemDTO scheduleItemDTO = null;
		ScheduleDTO scheduleDTO = null;
		
		List<DropdownDTO> itemDropdownList = new ArrayList<>();
		List<DropdownDTO> itemActionDropdownList = new ArrayList<>();
				
		Connection conn = getConnection();		
		try{
			scheduleItemDTO = new DBScheduleItem(conn).getItem(idScheduleItem);
			scheduleDTO = scheduleItemDTO.getScheduleDTO();		
			itemDropdownList = DropdownService.getDropdownItems(conn);
			if (scheduleItemDTO.getItemDTO().getItemTypeDTO().isOutput()){
				itemActionDropdownList = DropdownService.getDropdownActions();
			} else {
				itemActionDropdownList = DropdownService.getDropdownActionsInput();
			}

		} catch (Exception e) {
			setErrorMessage("Could not retrieve dropdown values");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
			
		storeObject("scheduleItemDTO",scheduleItemDTO);
		storeObject("scheduleDTO",scheduleDTO);
		storeObject("itemDropdownList",itemDropdownList);
		storeObject("itemActionDropdownList",itemActionDropdownList);
		storeObject("scheduleTimeText",LittleJUtils.localTimeToString(scheduleItemDTO.getScheduleTime()));
		
		if (scheduleItemDTO.isMonday()){
			storeObject("checkMonday","yes");
		} else {
			storeObject("checkMonday","no");
		}
		if (scheduleItemDTO.isTuesday()){
			storeObject("checkTuesday","yes");
		} else {
			storeObject("checkTuesday","no");
		}
		if (scheduleItemDTO.isWednesday()){
			storeObject("checkWednesday","yes");
		} else {
			storeObject("checkWednesday","no");
		}
		if (scheduleItemDTO.isThursday()){
			storeObject("checkThursday","yes");
		} else {
			storeObject("checkThursday","no");
		}
		if (scheduleItemDTO.isFriday()){
			storeObject("checkFriday","yes");
		} else {
			storeObject("checkFriday","no");
		}
		if (scheduleItemDTO.isSaturday()){
			storeObject("checkSaturday","yes");
		} else {
			storeObject("checkSaturday","no");
		}
		if (scheduleItemDTO.isSunday()){
			storeObject("checkSunday","yes");
		} else {
			storeObject("checkSunday","no");
		}
		
		return true;
	}

}




