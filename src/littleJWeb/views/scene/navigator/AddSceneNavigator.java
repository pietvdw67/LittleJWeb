package littleJWeb.views.scene.navigator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddSceneNavigator extends Navigator{
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		return true;
	}

}
