<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />

	<title>Edit Device</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">			
			<div class="container">
				<div class="row main-row">	
					<div class="6u">	

						<div align="right">
							<base:delete targetUrl="Controller.do?nav=setup.hardware.devices.navigator.DeleteDevice&idDevice=${deviceDTO.idDevice}" />
						</div>
				
						<h2>Edit Device</h2>

						<input type='hidden' name='idDevice' value='${deviceDTO.idDevice}' >
						<input type='hidden' name='nav' value='setup.hardware.devices.navigator.EditDeviceUpdate' >

						<base:inputField  description="Description" name="description" type="text" value="${deviceDTO.description}" />
						<base:dropdown dropdownList="${deviceTypeDropdown}" label="Device Type" name="iddeviceType" defaultvalue="${deviceDTO.idDeviceType}"></base:dropdown>
						<br>
						<p> Ip Address: for raspberry devices only</p>
						<base:inputField name="ip" type="text" description="Ip address" value="${deviceDTO.ip}"  />
						<br>
						<p> Temperature:
							${deviceDTO.temperature}
						</p>		
						
						<br><br>
						
						<span style="display:inline-block; width:2em;"></span>						
						<base:submit />						
						<base:cancel  targetUrl="Controller.do?nav=setup.hardware.devices.navigator.ViewDevice" />
					</div>
				</div>
			</div>
		</div>
	</form>
		
	<jsp:directive.include file="/footer.jsp" />
</body>
</html>
	
	