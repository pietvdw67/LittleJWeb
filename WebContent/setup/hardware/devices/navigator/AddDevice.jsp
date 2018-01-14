<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>Add a Devics</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">
			<div class="container">
				<div class="row main-row">	
					<div class="6u">
						<input type="hidden" name="nav" value = "setup.hardware.devices.navigator.AddDeviceInsert" />
					
						<h2>Add new Device</h2>

						<base:inputField  description="Description" name="description" type="text" />
						<base:dropdown dropdownList="${deviceTypeDropdown}" label="Device Type" name="iddeviceType"></base:dropdown>
						<br>
						<p> Ip Address: for raspberry devices only</p>
						<base:inputField name="ip" type="text" description="Ip address" />
						<br>
						
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