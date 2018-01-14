<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />

	<title>Edit Zone</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">			
			<div class="container">
				<div class="row main-row">	
					<div class="6u">	

						<div align="right">
							<base:delete targetUrl="Controller.do?nav=setup.hardware.zones.navigator.DeleteZone&idZone=${zoneDTO.idZone}" />
						</div>
				
						<h2>Edit Zone</h2>
						

						<input type='hidden' name='idZone' value='${zoneDTO.idZone}' />
						<input type='hidden' name='nav' value='setup.hardware.zones.navigator.EditZoneUpdate' />
														
						<base:inputField  description="Zone Name" name="zonename" type="text" value="${zoneDTO.zoneName}" />
						<base:dropdown label="Zone" dropdownList="${dropdownList}" name="zoneimage" defaultvalue="${zoneDTO.zoneImage}" />
	
						<br><br>
	
						<span style="display:inline-block; width:2em;"></span>						
						<base:submit />						
						<base:cancel  targetUrl="Controller.do?nav=setup.hardware.zones.navigator.ViewZones" />
					</div>
				</div>
			</div>
		</div>
	</form>
		
	<jsp:directive.include file="/footer.jsp" />
</body>
</html>
	
	