<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />

	<title>Edit Pi4jPin</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">			
			<div class="container">
				<div class="row main-row">	
					<div class="6u">	

						<div align="right">
							<base:delete targetUrl="Controller.do?nav=setup.hardware.pi4jPin.navigator.DeletePi4jPin&idpi4jpin=${pi4jPinDTO.idpi4jpin}" />
						</div>
				
						<h2>Edit Pi4jPin</h2>

						<input type='hidden' name='idpi4jpin' value='${pi4jPinDTO.idpi4jpin}' />
						<input type='hidden' name='nav' value='setup.hardware.pi4jPin.navigator.EditPi4jPinUpdate' />
							
						<base:inputField  description="Pin Number" name="pinnumber" type="number" value="${pi4jPinDTO.pinNumber}" />
	
						<br><br>
						
						<span style="display:inline-block; width:2em;"></span>						
						<base:submit />						
						<base:cancel  targetUrl="Controller.do?nav=setup.hardware.pi4jPin.navigator.ViewPi4jPin" />
					</div>
				</div>
			</div>
		</div>
	</form>
		
	<jsp:directive.include file="/footer.jsp" />
</body>
</html>
	
	