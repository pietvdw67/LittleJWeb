package littleJWeb.views.scheduleItem.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.LittleJUtils;
import littleJ.database.DBScheduleItem;
import littleJ.views.dto.ScheduleItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddScheduleItemInsertNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int isSchedule = Integer.parseInt(getParm(req, "idschedule"));
		int idItem = Integer.parseInt(getParm(req, "idItem"));
		int action = Integer.parseInt(getParm(req, "action"));
		String scheduleTimeString = getParm(req, "scheduletime");

		ScheduleItemDTO scheduleItemDTO = new ScheduleItemDTO();
		scheduleItemDTO.setIdSchedule(isSchedule);
		scheduleItemDTO.setIdItem(idItem);
		scheduleItemDTO.setAction(action);
		scheduleItemDTO.setScheduleTime(LittleJUtils.StringToLocalTime(scheduleTimeString));

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

		Connection conn = getConnection();
		try {
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
