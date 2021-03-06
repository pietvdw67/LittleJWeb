package littleJWeb.setup.hardware.pi4jPin.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBPi4jPin;
import littleJ.hardware.dto.Pi4jPinDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditPi4jPinUpdateNavigator extends Navigator {
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {

		Pi4jPinDTO pi4jPinDTO = new Pi4jPinDTO();
		pi4jPinDTO.setIdpi4jpin(Integer.parseInt(getParm(req,"idpi4jpin")));
		pi4jPinDTO.setPinNumber(Integer.parseInt(getParm(req,"pinnumber")));

		Connection conn = getConnection();
		try {
			new DBPi4jPin(conn).updateItem(pi4jPinDTO);
		} catch (SQLException e) {
			setErrorMessage("Could not update pi4jpin id:" + pi4jPinDTO.getIdpi4jpin());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("setup.hardware.pi4jPin.navigator.ViewPi4jPin");
		return true;
	}

}
