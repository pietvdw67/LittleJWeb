package littleJWeb.setup.hardware.zones.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBZone;
import littleJ.hardware.dto.ZoneDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddZoneInsertNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		ZoneDTO zoneDTO = new ZoneDTO();
		zoneDTO.setZoneName(getParm(req,"zonename"));
		zoneDTO.setZoneImage(getParm(req,"zoneimage"));

		Connection conn = getConnection();		
		try {
			new DBZone(conn).addItem(zoneDTO);	
		} catch (SQLException e) {
			setErrorMessage("Could not add zone: " + zoneDTO.getZoneName());
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		setAlternativeNav("setup.hardware.zones.navigator.ViewZones");
		
		return true;
		
	}

}
