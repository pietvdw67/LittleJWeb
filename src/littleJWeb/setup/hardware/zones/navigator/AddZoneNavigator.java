package littleJWeb.setup.hardware.zones.navigator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;

@SuppressWarnings("serial")
public class AddZoneNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		List<DropdownDTO> dropdownList = new ArrayList<>();
		@SuppressWarnings("deprecation")
		String zoneImagesPath = req.getRealPath("/") + "images/zone/";		
		File[] imageFiles = new File(zoneImagesPath).listFiles();
		for (File f : imageFiles){
			DropdownDTO dropdownDTO = new DropdownDTO();
			dropdownDTO.setName(f.getName());
			dropdownDTO.setText(getFileWithoutExcetion(f.getName()));
			
			dropdownList.add(dropdownDTO);
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
