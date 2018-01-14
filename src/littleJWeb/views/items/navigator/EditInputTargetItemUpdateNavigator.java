package littleJWeb.views.items.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBInputTargetItem;
import littleJ.views.dto.InputTargetItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class EditInputTargetItemUpdateNavigator extends Navigator {
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		
		InputTargetItemDTO inputTargetItemDTO = new InputTargetItemDTO();
		inputTargetItemDTO.setIdInputTargetItem(Integer.parseInt(getParm(req,"idinputtargetitem")));
		inputTargetItemDTO.setIdSourceItem(Integer.parseInt(getParm(req,"idsourceitem")));
		inputTargetItemDTO.setIdTargetItem(Integer.parseInt(getParm(req,"idtargetitem")));
		inputTargetItemDTO.setAction(Integer.parseInt(getParm(req,"action")));
		inputTargetItemDTO.setDelay(getParm(req,"delay"));

		Connection conn = getConnection();
		try {
			new DBInputTargetItem(conn).updateItem(inputTargetItemDTO);	
		} catch (SQLException e) {
			setErrorMessage("Could not update target item");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("cancel");
		return true;
	}

}


