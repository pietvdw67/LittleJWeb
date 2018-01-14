<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />

	<title>Edit Item</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">			
			<div class="container">
				<div class="row main-row">	
					<div class="6u">	

						<div align="right">
							<base:delete targetUrl="Controller.do?nav=setup.hardware.items.navigator.DeleteItem&iditem=${itemDTO.idItem}" />
						</div>
				
						<h2>Edit Item</h2>						
						<h1> idItem: ${itemDTO.idItem}</h1> 
						<input type='hidden' name='idItem' value='${itemDTO.idItem}' />
						<input type='hidden' name='nav' value='setup.hardware.items.navigator.EditItemUpdate' />

						<base:inputField  description="Description" name="description" type="text" value='${itemDTO.description}' />
						<base:dropdown label="Item Type" dropdownList="${itemTypeDropdownList}" name="idItemType" defaultvalue="${itemDTO.idItemType}" />
						<base:dropdown label="Zone" dropdownList="${zoneDropdownList}" name="idZone" defaultvalue="${itemDTO.idZone}" />
						<base:checkbox ticked="${isActive}" label="Is Active" id="isActive" name="isActive" />
						<base:checkbox ticked="${isFavourite}" label="Add to Favourites" id="isFavourite" name="isFavourite" />
						<br>
						
						<h2>For audio file items only</h2>
						<base:dropdown label="Audio file" dropdownList="${audiodropdownList}" name="audiofile" defaultvalue="${itemDTO.audioFile}" />
						<br>
						
						<base:dropdown label="Device" dropdownList="${deviceDropdownList}" name="idDevice" defaultvalue="${itemDTO.idDevice}" />
						
						<h2>For Raspberry devices only</h2>
						<base:dropdown label="Pin number:" dropdownList="${pi4jPinDropdownList}" name="idPi4jPin" defaultvalue="${itemDTO.idPi4jPin}" />
						<br>
	
						<br><br>
						
						<span style="display:inline-block; width:2em;"></span>						
						<base:submit />
						<base:cancel  targetUrl="Controller.do?nav=cancel" />
					</div>
				</div>
			</div>
		</div>
	</form>
		
	<jsp:directive.include file="/footer.jsp" />
</body>
</html>
	
	