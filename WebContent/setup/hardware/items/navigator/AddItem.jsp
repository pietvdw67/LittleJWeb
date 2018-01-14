<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>Add an item</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">
			<div class="container">
				<div class="row main-row">	
					<div class="6u">
						<input type="hidden" name="nav" value = "setup.hardware.items.navigator.AddItemInsert" />
					
						<h2>Add new Item</h2>
						
						<base:inputField  description="Description" name="description" type="text" />
						<base:dropdown label="Item Type" dropdownList="${itemTypeDropdownList}" name="idItemType" />
						<base:dropdown label="Zone" dropdownList="${zoneDropdownList}" name="idZone" />
						<base:checkbox ticked="no" label="Is Active" id="isActive" name="isActive" />
						<base:checkbox ticked="no" label="Add to Favourites" id="isFavourite" name="isFavourite" />
						<br>
						
						<h2>For audio file items only</h2>
						<base:dropdown label="Audio file" dropdownList="${audiodropdownList}" name="audiofile" />
				
						<br>
						
						<base:dropdown label="Device" dropdownList="${deviceDropdownList}" name="idDevice" />
						<h2>For Raspberry devices only</h2>						
						<base:dropdown label="Pin number" dropdownList="${pi4jPinDropdownList}" name="idPi4jPin" />
						<br>
												
						<br>
						
						<base:submit /> 
						<base:cancel  targetUrl="Controller.do?nav=setup.hardware.items.navigator.ViewItem" />
					
					</div>
				</div>
				
			</div>
		</div>
	</form>	
	
	<jsp:directive.include file="/footer.jsp" />
</body>	
</html>