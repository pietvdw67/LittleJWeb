package littleJWeb.processor.statuschange.navigator;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBItem;
import littleJ.database.DBStatusChange;
import littleJ.hardware.dto.ItemDTO;
import littleJ.processors.statuschange.dto.StatusChangeDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class StatusChangeUpdateNavigator extends Navigator{

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		String alternativeNav = "cancelMain";
		int idItem = Integer.parseInt(getParm(req,"iditem"));
		int status = Integer.parseInt(getParm(req,"status"));
		
		if (status == 1){
			status =0;
		} else{
			status = 1;
		}
	
		StatusChangeDTO statusChangeDTO = new StatusChangeDTO();
		statusChangeDTO.setAction(status);
		statusChangeDTO.setChangeTimestamp(LocalDateTime.now());
		statusChangeDTO.setIdItem(idItem);
		statusChangeDTO.setIdschedule(-1);
		statusChangeDTO.setProcesstime(LocalDateTime.now());
		statusChangeDTO.setIdSourceItem(-1);
		
		Connection conn = getConnection();
		try {
			ItemDTO itemDTO = new DBItem(conn).getItem(idItem);
			if (itemDTO.getItemTypeDTO().isOutput()){
				new DBStatusChange(conn).addItem(statusChangeDTO);
			} else {
				alternativeNav = "esp.navigator.EspInput";	
				setAlternativeNavParms(new HashMap<>());
				getAlternativeNavParms().put("name", itemDTO.getDeviceDTO().getDescription());
			}
		} catch (SQLException e) {
			setErrorMessage("Could not schedule status change");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav(alternativeNav);
		
		return true;
	}

}
