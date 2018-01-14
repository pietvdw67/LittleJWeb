<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>Edit Schedule</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">
			<div class="container">
				<div class="row main-row">	
					<div class="6u">
						<input type="hidden" name="nav" value = "views.schedule.navigator.EditScheduleUpate" />
						<input type="hidden" name="idschedule" value = "${scheduleDTO.idSchedule}" />
						
						<div align="right">
							<base:delete targetUrl="Controller.do?nav=views.schedule.navigator.DeleteSchedule&idschedule=${scheduleDTO.idSchedule}" />
						</div>
					
						<h2>Edit schedule</h2>
						
						<base:inputField  description="Description" name="description" type="text" value="${scheduleDTO.description}" />
						<base:checkbox ticked="${doTickActive}" label="Is Active" id="isAcive" name="isAcive" />
						<br>
						
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