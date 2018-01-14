package littleJWeb.setup.hardware.pi4jPin.navigator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBPi4jPin;
import littleJ.hardware.dto.Pi4jPinDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ViewPi4jPinNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		addPopulatingNavigator("setup.hardware.pi4jPin.navigator.ViewPi4jPin");
		List<Pi4jPinDTO> pi4jPinDTOList = null;
		Connection conn = getConnection();		
		try {
			pi4jPinDTOList = new DBPi4jPin(conn).getAllItems();
		} catch (SQLException e) {
			setErrorMessage("Could not retrieve pi4jpins");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("pi4jPinDTOList",pi4jPinDTOList);
				
		return true;
	}
}
