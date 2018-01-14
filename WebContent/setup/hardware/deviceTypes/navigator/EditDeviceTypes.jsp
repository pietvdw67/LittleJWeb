<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />

	<title>Edit Device Type</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">			
			<div class="container">
				<div class="row main-row">	
					<div class="6u">	

						<div align="right">
							<base:delete targetUrl="Controller.do?nav=setup.hardware.deviceTypes.navigator.DeleteDeviceTypes&iddevicetype=${deviceTypeDTO.idDeviceType}" />
						</div>
				
						<h2>Edit Device Type</h2>

						<input type='hidden' name='idDeviceType' value='${deviceTypeDTO.idDeviceType}' >
						<input type='hidden' name='nav' value='setup.hardware.deviceTypes.navigator.EditDeviceTypesUpdate' >

						<base:inputField  description="Description" name="description" type="text" value="${deviceTypeDTO.description}" />			
												
						<br><br>
						
						<span style="display:inline-block; width:2em;"></span>						
						<base:submit />						
						<base:cancel  targetUrl="Controller.do?nav=setup.hardware.deviceTypes.navigator.ViewDeviceTypes" />
					</div>
				</div>
			</div>
		</div>
	</form>
		
	<jsp:directive.include file="/footer.jsp" />
</body>
</html>
	
	