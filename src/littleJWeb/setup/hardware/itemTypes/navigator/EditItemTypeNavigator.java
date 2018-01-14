package littleJWeb.setup.hardware.itemTypes.navigator;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.LittleJUtils;
import littleJ.database.DBItemType;
import littleJ.hardware.dto.ItemTypeDTO;
import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;

@SuppressWarnings("serial")
public class EditItemTypeNavigator extends Navigator{
	
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		int idItemType = Integer.valueOf(getParm(req,"idItemType"));
		ItemTypeDTO itemTypeDTO = null;
		
		Connection conn = getConnection();		
		try {
			itemTypeDTO = new DBItemType(conn).getItem(idItemType);
		} catch (SQLException e) {
			setErrorMessage("Could not retrieve item type id:" + idItemType);
			setErrorException(e);
			return false;
		} finally {
			closeConnection(conn);
		}
		storeObject("itemTypeDTO",itemTypeDTO);	
		
		List<DropdownDTO> dropdownList = new ArrayList<>();
		@SuppressWarnings("deprecation")
		String zoneImagesPath = req.getRealPath("/") + "images/itemtype/";		
		File[] imageFiles = new File(zoneImagesPath).listFiles();
		List<String> filenames = new ArrayList<>();
		for (File f : imageFiles){
			String fileToShow = getFileWithoutExcetion(LittleJUtils.getImageBaseName(f.getName()));
			if (filenames.contains(fileToShow)){
				continue;
			}
			DropdownDTO dropdownDTO = new DropdownDTO();
			dropdownDTO.setName(LittleJUtils.getImageBaseName(f.getName()));
			dropdownDTO.setText(fileToShow);
			
			dropdownList.add(dropdownDTO);
			filenames.add(fileToShow);
		}
		
		storeObject("dropdownList",dropdownList);
		
	
		
		return true;
	}
	
	private String getFileWithoutExcetion(String file){
		int periodPos = file.lastIndexOf(".");
		if (periodPos == -1){
			return file;
		}
		return file.substring(0,periodPos);
	}

}


