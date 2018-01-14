package littleJWeb.setup.hardware.pi4jPin.navigator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddPi4jPinNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		return true;
	}

}
