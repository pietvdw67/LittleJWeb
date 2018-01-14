<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />

	<title>Edit Item Type</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">			
			<div class="container">
				<div class="row main-row">	
					<div class="6u">	

						<div align="right">
							<base:delete targetUrl="Controller.do?nav=setup.hardware.itemTypes.navigator.DeleteItemType&idItemType=${itemTypeDTO.idItemType}" />
						</div>
				
						<h2>Edit ItemType</h2>						

						<input type='hidden' name='idItemType' value='${itemTypeDTO.idItemType}' />
						<input type='hidden' name='nav' value='setup.hardware.itemTypes.navigator.EditItemTypeUpdate' />
														
						<base:inputField  description="Item type name" name="itemtypename" type="text" value="${itemTypeDTO.itemTypeName}" />
						<base:dropdown label="Image" dropdownList="${dropdownList}" name="itemtypeimage" defaultvalue="${itemTypeDTO.itemTypeImage}" />
						<base:checkbox label="Is output" id="isoutput" name="isoutput" ticked="${itemTypeDTO.isOutputChecked}" />
						<base:checkbox label="Is audio" id="isaudio" name="isaudio" ticked="${itemTypeDTO.isAudioChecked}" />
	
						<br><br>
						
						<span style="display:inline-block; width:2em;"></span>						
						<base:submit />						
						<base:cancel  targetUrl="Controller.do?nav=setup.hardware.itemTypes.navigator.ViewItemType" />
					</div>
				</div>
			</div>
		</div>
	</form>
		
	<jsp:directive.include file="/footer.jsp" />
</body>
</html>
	
	