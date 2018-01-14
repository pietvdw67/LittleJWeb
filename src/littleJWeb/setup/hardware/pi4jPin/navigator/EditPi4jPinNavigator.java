package littleJWeb.setup.hardware.pi4jPin.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBPi4jPin;
import littleJ.hardware.dto.Pi4jPinDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditPi4jPinNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idpi4jpin = Integer.valueOf(getParm(req,"idpi4jpin"));
		Pi4jPinDTO pi4jPinDTO = null;
		
		Connection conn = getConnection();		
		try {
			pi4jPinDTO = new DBPi4jPin(conn).getItem(idpi4jpin);
		} catch (SQLException e) {
			setErrorMessage("Could not retrieve pi4jpin id:" + idpi4jpin);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("pi4jPinDTO",pi4jPinDTO);		
		
		return true;
	}

}
