package littleJWeb.views.scheduleItem.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.LittleJUtils;
import littleJ.database.DBSchedule;
import littleJ.database.DBScheduleItem;
import littleJ.views.dto.ScheduleDTO;
import littleJ.views.dto.ScheduleItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddCustomeScheduleItemInsertNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int action = Integer.parseInt(getParm(req, "action"));
		String id = getParm(req, "id");
		String type = getParm(req, "type");
		String scheduleDescription = getParm(req, "scheduleDescription");
		String idSchedule = getParm(req, "idSchedule");
		String scheduletime = getParm(req,"scheduletime");
		ScheduleDTO scheduleDTO = null;

		Connection conn = getConnection();
		try {
			
			// Create scene and retrieve the id
			if (scheduleDescription.length() > 0) {
				scheduleDTO = new ScheduleDTO();
				scheduleDTO.setDescription(scheduleDescription);
				scheduleDTO.setActive(true);
				new DBSchedule(conn).addItem(scheduleDTO);				
				scheduleDTO = new DBSchedule(conn).getScheduleByDescription(scheduleDescription);
				idSchedule = String.valueOf(scheduleDTO.getIdSchedule());
			} 

			ScheduleItemDTO scheduleItemDTO = new ScheduleItemDTO();
			scheduleItemDTO.setIdSchedule(Integer.valueOf(idSchedule));			
			if (type.equalsIgnoreCase("item")){
				scheduleItemDTO.setIdItem(Integer.valueOf(id));
			}
			scheduleItemDTO.setAction(action);
			scheduleItemDTO.setScheduleTime(LittleJUtils.StringToLocalTime(scheduletime));
			
			String isDay = getParm(req, "isMonday");
			if (isDay != null & isDay.length() > 0) {
				scheduleItemDTO.setMonday(true);
			} else {
				scheduleItemDTO.setMonday(false);
			}
			isDay = getParm(req, "isTuesday");
			if (isDay != null & isDay.length() > 0) {
				scheduleItemDTO.setTuesday(true);
			} else {
				scheduleItemDTO.setTuesday(false);
			}
			isDay = getParm(req, "isWednesday");
			if (isDay != null & isDay.length() > 0) {
				scheduleItemDTO.setWednesday(true);
			} else {
				scheduleItemDTO.setWednesday(false);
			}
			isDay = getParm(req, "isThursday");
			if (isDay != null & isDay.length() > 0) {
				scheduleItemDTO.setThursday(true);
			} else {
				scheduleItemDTO.setThursday(false);
			}
			isDay = getParm(req, "isFriday");
			if (isDay != null & isDay.length() > 0) {
				scheduleItemDTO.setFriday(true);
			} else {
				scheduleItemDTO.setFriday(false);
			}
			isDay = getParm(req, "isSaturday");
			if (isDay != null & isDay.length() > 0) {
				scheduleItemDTO.setSaturday(true);
			} else {
				scheduleItemDTO.setSaturday(false);
			}
			isDay = getParm(req, "isSunday");
			if (isDay != null & isDay.length() > 0) {
				scheduleItemDTO.setSunday(true);
			} else {
				scheduleItemDTO.setSunday(false);
			}
			
			new DBScheduleItem(conn).addItem(scheduleItemDTO);

		} catch (SQLException e) {
			setErrorMessage("Could not add schedule item");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("cancel");

		return true;

	}

}

