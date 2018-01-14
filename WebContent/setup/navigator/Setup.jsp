<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>Setup</title>

</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
		<div id="main">
			<div class="container">
				<div class="row main-row">	
				<div class="6u">
						<h2>Device Setup</h2>
						<ul class="link-list">
							<li><a href="Controller.do?nav=views.statuschange.navigator.QueuedChanges">Queued changes</a></li>
							<li><a href="Controller.do?nav=setup.hardware.items.navigator.ViewItem">Items</a></li>							
							<li><a href="Controller.do?nav=setup.hardware.itemTypes.navigator.ViewItemType">Item Types</a></li>
							<li><a href="Controller.do?nav=setup.hardware.zones.navigator.ViewZones">Zones</a></li>
							<li><a href="Controller.do?nav=setup.hardware.devices.navigator.ViewDevice">Devices</a></li>
							<li><a href="Controller.do?nav=setup.hardware.deviceTypes.navigator.ViewDeviceTypes">Device Types</a></li>
							<li><a href="Controller.do?nav=setup.hardware.pi4jPin.navigator.ViewPi4jPin">Raspberry Pi4jPins</a></li>
						</ul>
					</div>
				</div>				
			</div>
		</div>
		
	<jsp:directive.include file="/footer.jsp" />
</body>	
</html>