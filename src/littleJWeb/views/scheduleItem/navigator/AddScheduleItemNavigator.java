package littleJWeb.views.scheduleItem.navigator;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSchedule;
import littleJ.views.dto.ScheduleDTO;
import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;
import littleJWeb.web.service.DropdownService;

@SuppressWarnings("serial")
public class AddScheduleItemNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		ScheduleDTO scheduleDTO  = null;
		int idSchedule = Integer.valueOf(getParm(req,"idshedule"));		
		
		List<DropdownDTO> itemDropdownList = new ArrayList<>();
		List<DropdownDTO> itemActionDropdownList = new ArrayList<>();
				
		Connection conn = getConnection();		
		try{
			scheduleDTO = new DBSchedule(conn).getItem(idSchedule);			
			itemDropdownList = DropdownService.getDropdownItems(conn);
			itemActionDropdownList = DropdownService.getDropdownActions();
		} catch (Exception e) {
			setErrorMessage("Could not retrieve dropdown values");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
	
		storeObject("scheduleDTO",scheduleDTO);
		storeObject("itemDropdownList",itemDropdownList);
		storeObject("itemActionDropdownList",itemActionDropdownList);
		
		return true;
	}

}



