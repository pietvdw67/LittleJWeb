package littleJWeb.setup.hardware.zones.navigator;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBZone;
import littleJ.hardware.dto.ZoneDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ViewZonesNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		addPopulatingNavigator("setup.hardware.zones.navigator.ViewZones");
		List<ZoneDTO> zoneDTOlist = null;
		Connection conn = getConnection();		
		try {
			zoneDTOlist = new DBZone(conn).getAllItems();
			//String zoneImagesPath = req.getRealPath("/") + "images/zone/";
			String zoneImagesPath = "images/zone/";
			for (ZoneDTO zoneDTO : zoneDTOlist){
				zoneDTO.setZoneImagePath(zoneImagesPath + zoneDTO.getZoneImage());
			}
			
		} catch (SQLException e) {
			setErrorMessage("Could not retrieve zones");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("zoneDTOlist",zoneDTOlist);
				
		return true;
	}
}
