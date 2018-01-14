package littleJWeb.views.statuschange.navigator;

import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBStatusChange;
import littleJ.processors.statuschange.dto.StatusChangeDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class QueuedChangesNavigator extends Navigator{
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		setMainPopulatingNavigator("views.statuschange.navigator.QueuedChanges");
		List<StatusChangeDTO> statusChangeDTOlist = null;
		
		Connection conn = getConnection();		
		try {
			statusChangeDTOlist = new DBStatusChange(conn).getAllItems();
			
		}catch (Exception e) {
			setErrorMessage("Exception occurred");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		
		storeObject("statusChangeDTOlist",statusChangeDTOlist);
		
		return true;
	}

}


