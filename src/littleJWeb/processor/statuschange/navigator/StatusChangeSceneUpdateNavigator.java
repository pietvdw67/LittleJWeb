package littleJWeb.processor.statuschange.navigator;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.database.DBSceneItem;
import littleJ.database.DBStatusChange;
import littleJ.processors.statuschange.dto.StatusChangeDTO;
import littleJ.views.dto.SceneItemDTO;
import littleJWeb.navigator.Navigator;

@SuppressWarnings("serial")
public class StatusChangeSceneUpdateNavigator extends Navigator{

	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {			
		int isScene = Integer.parseInt(getParm(req,"idscene"));
		 		
		Connection conn = getConnection();
		try {
			DBStatusChange dbStatusChange = new DBStatusChange(conn);
			//SceneDTO sceneDTO = new DBScene().getScene(conn, isScene);
			List<SceneItemDTO> sceneItemDTOList = new DBSceneItem(conn).getSceneItemsByScene(isScene);
			for (SceneItemDTO sceneItem : sceneItemDTOList){
				StatusChangeDTO statusChangeDTO = new StatusChangeDTO();
				statusChangeDTO.setAction(sceneItem.getAction());
				statusChangeDTO.setChangeTimestamp(LocalDateTime.now());
				statusChangeDTO.setIdItem(sceneItem.getIdItem());
				statusChangeDTO.setIdschedule(-1);
				statusChangeDTO.setIdSourceItem(-1);
				LocalDateTime ldt = LocalDateTime.now();
				
				if (sceneItem.getDelay().toLowerCase().endsWith("h")){
					ldt = ldt.plusHours(Integer.parseInt(sceneItem.getDelay().substring(0,sceneItem.getDelay().length()-1)));
				} else if (sceneItem.getDelay().toLowerCase().endsWith("m")){
					ldt = ldt.plusMinutes(Integer.parseInt(sceneItem.getDelay().substring(0,sceneItem.getDelay().length()-1)));
				} else if (sceneItem.getDelay().toLowerCase().endsWith("s")){
					ldt = ldt.plusSeconds(Integer.parseInt(sceneItem.getDelay().substring(0,sceneItem.getDelay().length()-1)));
				} else {
					ldt = ldt.plusSeconds(Integer.parseInt(sceneItem.getDelay()));
				}
				
				statusChangeDTO.setProcesstime(ldt);
				
				dbStatusChange.addItem(statusChangeDTO);
			}

		} catch (SQLException e) {
			setErrorMessage("Could not schedule status change");
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}

		setAlternativeNav("cancelMain");
		
		return true;
	}

}

