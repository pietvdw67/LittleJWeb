package littleJWeb.setup.hardware.deviceTypes.navigator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddDeviceTypesNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		return true;
	}

}
