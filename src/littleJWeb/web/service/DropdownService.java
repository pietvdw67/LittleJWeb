package littleJWeb.web.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import littleJ.ItemActions;
import littleJ.database.DBDevice;
import littleJ.database.DBDeviceType;
import littleJ.database.DBItem;
import littleJ.database.DBItemType;
import littleJ.database.DBPi4jPin;
import littleJ.database.DBScene;
import littleJ.database.DBSchedule;
import littleJ.database.DBZone;
import littleJ.hardware.dto.DeviceDTO;
import littleJ.hardware.dto.DeviceTypeDTO;
import littleJ.hardware.dto.ItemDTO;
import littleJ.hardware.dto.ItemTypeDTO;
import littleJ.hardware.dto.Pi4jPinDTO;
import littleJ.hardware.dto.ZoneDTO;
import littleJ.views.dto.SceneDTO;
import littleJ.views.dto.ScheduleDTO;
import littleJWeb.web.DTO.DropdownDTO;

public class DropdownService {
	
	public static List<DropdownDTO> getDropdownActions() throws Exception {
		List<DropdownDTO> itemActionDropdownList = new ArrayList<>();
		
		DropdownDTO dropdownDTO = new DropdownDTO();
		dropdownDTO.setName(String.valueOf(ItemActions.ON.getValue()));
		dropdownDTO.setText("On");
		itemActionDropdownList.add(dropdownDTO);
		
		dropdownDTO = new DropdownDTO();
		dropdownDTO.setName(String.valueOf(ItemActions.OFF.getValue()));
		dropdownDTO.setText("Off");
		itemActionDropdownList.add(dropdownDTO);
		
		dropdownDTO = new DropdownDTO();
		dropdownDTO.setName(String.valueOf(ItemActions.TOGGLE.getValue()));
		dropdownDTO.setText("Toggle");
		itemActionDropdownList.add(dropdownDTO);
		
		return itemActionDropdownList;
	}
	
	public static List<DropdownDTO> getDropdownActionsInput() throws Exception {
		List<DropdownDTO> itemActionDropdownList = new ArrayList<>();
		
		DropdownDTO dropdownDTO = new DropdownDTO();
		dropdownDTO.setName(String.valueOf(ItemActions.ENABLE.getValue()));
		dropdownDTO.setText("Enable");
		itemActionDropdownList.add(dropdownDTO);
		
		dropdownDTO = new DropdownDTO();
		dropdownDTO.setName(String.valueOf(ItemActions.DISABLE.getValue()));
		dropdownDTO.setText("Disable");
		itemActionDropdownList.add(dropdownDTO);
	
		return itemActionDropdownList;
	}
	
	public static List<DropdownDTO> getDropdownScenes(Connection conn) throws Exception {
		List<DropdownDTO> sceneDropdownList = new ArrayList<>();

		List<SceneDTO> sceneDTOList = new ArrayList<>();

		sceneDTOList = new DBScene(conn).getAllItems();

		for (SceneDTO sceneDTO : sceneDTOList) {
			DropdownDTO dropdownDTO = new DropdownDTO();
			dropdownDTO.setName(String.valueOf(sceneDTO.getIdScene()));
			dropdownDTO.setText(sceneDTO.getDescription());

			sceneDropdownList.add(dropdownDTO);
		}

		return sceneDropdownList;
	}
	
	public static List<DropdownDTO> getDropdownSchedules(Connection conn) throws Exception {
		List<DropdownDTO> scheduleDropdownList = new ArrayList<>();

		List<ScheduleDTO> scheduleDTOlist = new ArrayList<>();

		scheduleDTOlist = new DBSchedule(conn).getAllItems();

		for (ScheduleDTO scheduleDTO : scheduleDTOlist) {
			DropdownDTO dropdownDTO = new DropdownDTO();
			dropdownDTO.setName(String.valueOf(scheduleDTO.getIdSchedule()));
			dropdownDTO.setText(scheduleDTO.getDescription());

			scheduleDropdownList.add(dropdownDTO);
		}

		return scheduleDropdownList;
	}
	
	public static List<DropdownDTO> getDropdownItems(Connection conn) throws Exception {
		List<DropdownDTO> zoneDropdownList = new ArrayList<>();

		List<ItemDTO> ItemDTOList = new ArrayList<>();

		ItemDTOList = new DBItem(conn).getAllItems();

		for (ItemDTO itemDTO : ItemDTOList) {
			DropdownDTO dropdownDTO = new DropdownDTO();
			dropdownDTO.setName(String.valueOf(itemDTO.getIdItem()));
			dropdownDTO.setText(itemDTO.getDescription());

			zoneDropdownList.add(dropdownDTO);
		}

		return zoneDropdownList;
	}
	
	public static List<DropdownDTO> getDropdownZone(Connection conn) throws Exception {
		List<DropdownDTO> zoneDropdownList = new ArrayList<>();

		List<ZoneDTO> zoneDTOList = new ArrayList<>();

		zoneDTOList = new DBZone(conn).getAllItems();

		for (ZoneDTO zoneDTO : zoneDTOList) {
			DropdownDTO dropdownDTO = new DropdownDTO();
			dropdownDTO.setName(String.valueOf(zoneDTO.getIdZone()));
			dropdownDTO.setText(zoneDTO.getZoneName());

			zoneDropdownList.add(dropdownDTO);
		}

		return zoneDropdownList;
	}
	
	public static List<DropdownDTO> getDropdownItemType(Connection conn) throws Exception {
		List<DropdownDTO> itemTypeDropdownList = new ArrayList<>();

		List<ItemTypeDTO> itemTypeDTOList = new ArrayList<>();

		itemTypeDTOList = new DBItemType(conn).getAllItems();

		for (ItemTypeDTO itemTypeDTO : itemTypeDTOList) {
			DropdownDTO dropdownDTO = new DropdownDTO();
			dropdownDTO.setName(String.valueOf(itemTypeDTO.getIdItemType()));
			dropdownDTO.setText(itemTypeDTO.getItemTypeName());

			itemTypeDropdownList.add(dropdownDTO);
		}

		return itemTypeDropdownList;
	}
	
	public static List<DropdownDTO> getDropdownDevice(Connection conn) throws Exception {
		List<DropdownDTO> zoneDropdownList = new ArrayList<>();

		List<DeviceDTO> deviceDTOList = new ArrayList<>();

		deviceDTOList = new DBDevice(conn).getAllItems();

		for (DeviceDTO deviceDTO : deviceDTOList) {
			DropdownDTO dropdownDTO = new DropdownDTO();
			dropdownDTO.setName(String.valueOf(deviceDTO.getIdDevice()));
			dropdownDTO.setText(deviceDTO.getDescription());

			zoneDropdownList.add(dropdownDTO);
		}

		return zoneDropdownList;
	}
	
	public static List<DropdownDTO> getDropdownDeviceType(Connection conn) throws Exception {
		List<DropdownDTO> zoneDropdownList = new ArrayList<>();

		List<DeviceTypeDTO> deviceTypeDTOList = new ArrayList<>();

		deviceTypeDTOList = new DBDeviceType(conn).getAllItems();

		for (DeviceTypeDTO deviceTypeDTO : deviceTypeDTOList) {
			DropdownDTO dropdownDTO = new DropdownDTO();
			dropdownDTO.setName(String.valueOf(deviceTypeDTO.getIdDeviceType()));
			dropdownDTO.setText(deviceTypeDTO.getDescription());

			zoneDropdownList.add(dropdownDTO);
		}

		return zoneDropdownList;
	}
	
	public static List<DropdownDTO> getDropdownPi4jPin(Connection conn) throws Exception {
		List<DropdownDTO> pi4jPinDropdownList = new ArrayList<>();

		List<Pi4jPinDTO> pi4jPinDTOList = new ArrayList<>();

		pi4jPinDTOList = new DBPi4jPin(conn).getAllItems();

		for (Pi4jPinDTO pi4jPinDTO : pi4jPinDTOList) {
			DropdownDTO dropdownDTO = new DropdownDTO();
			dropdownDTO.setName(String.valueOf(pi4jPinDTO.getIdpi4jpin()));
			dropdownDTO.setText(String.valueOf(pi4jPinDTO.getPinNumber()));

			pi4jPinDropdownList.add(dropdownDTO);
		}

		return pi4jPinDropdownList;
	}

}
