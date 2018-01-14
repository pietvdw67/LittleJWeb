package littleJWeb.views.items.navigator;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBInputTargetItem;
import littleJ.views.dto.InputTargetItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class AddInputTargetItemInsertNavigator extends Navigator {

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int action = Integer.parseInt(getParm(req, "action"));
		int sourceIdItem = Integer.parseInt(getParm(req, "sourceiditem"));
		int idTargetItem  = Integer.parseInt(getParm(req, "idtargetitem"));

		Connection conn = getConnection();
		try {
			InputTargetItemDTO inputTargetItemDTO = new InputTargetItemDTO();
			inputTargetItemDTO.setIdSourceItem(sourceIdItem);
			inputTargetItemDTO.setIdTargetItem(idTargetItem);
			inputTargetItemDTO.setAction(action);
			inputTargetItemDTO.setDelay( getParm(req, "delay"));

			new DBInputTargetItem(conn).addItem(inputTargetItemDTO);

		} catch (SQLException e) {
			setErrorMessage("Could not add linked target item");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("cancel");

		return true;

	}

}
