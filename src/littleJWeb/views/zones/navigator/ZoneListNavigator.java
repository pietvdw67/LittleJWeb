package littleJWeb.views.zones.navigator;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBZone;
import littleJ.hardware.dto.ZoneDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class ZoneListNavigator extends Navigator{
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		setMainPopulatingNavigator("views.zones.navigator.ZoneList");
		List<ZoneDTO> zoneDTOlist = null;
		
		Connection conn = getConnection();		
		try {
			zoneDTOlist = new DBZone(conn).getAllItems();
			
			String zoneImagesPath = "images/zone/";
			for (ZoneDTO zoneDTO : zoneDTOlist){
				zoneDTO.setZoneImagePath(zoneImagesPath + zoneDTO.getZoneImage());
			}			
			
		}catch (Exception e) {
			setErrorMessage("Exception occurred");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("zoneDTOlist",zoneDTOlist);
		
		return true;
	}

}

