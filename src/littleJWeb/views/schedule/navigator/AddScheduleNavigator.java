package littleJWeb.views.schedule.navigator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddScheduleNavigator extends Navigator{
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		return true;
	}

}
