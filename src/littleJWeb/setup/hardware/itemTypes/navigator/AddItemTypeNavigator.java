package littleJWeb.setup.hardware.itemTypes.navigator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import littleJ.LittleJUtils;
import littleJWeb.navigator.Navigator;
import littleJWeb.web.DTO.DropdownDTO;

@SuppressWarnings("serial")
public class AddItemTypeNavigator extends Navigator {
	@Override
	public boolean performTask(HttpServletRequest req, HttpServletResponse resp) {
		List<DropdownDTO> dropdownList = new ArrayList<>();
		@SuppressWarnings("deprecation")
		String imagesPath = req.getRealPath("/") + "images/itemtype/";		
		File[] imageFiles = new File(imagesPath).listFiles();
		List<String> filenames = new ArrayList<>();
		for (File f : imageFiles){
			String fileToShow = getFileWithoutExcetion(LittleJUtils.getImageBaseName(f.getName()));
			if (filenames.contains(fileToShow)){
				continue;
			}
			DropdownDTO dropdownDTO = new DropdownDTO();
			dropdownDTO.setName(LittleJUtils.getImageBaseName(f.getName()));
			dropdownDTO.setText(getFileWithoutExcetion(LittleJUtils.getImageBaseName(f.getName())));
			
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
