<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />	
	<title>Add item to schedule</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">
			<div class="container">
				<div class="row main-row">	
					<div class="6u">
						<input type="hidden" name="type" value = "${type}" />
						<input type="hidden" name="id" value = "${id}" />						
						<input type="hidden" name="nav" value = "views.scheduleItem.navigator.AddCustomeScheduleItemInsert" />
					
						<c:if test="${type eq 'item'}">
							<H2>Add item ${itemDTO.description} to a existing or new scene</H2>
						</c:if>
						<br>
						<p> Either enter a new schedule description to create a new schedule or select from an existing schedule to add this item to it
						</p>						
						<base:inputField  description="Schedule" name="scheduleDescription" type="text" />
						<base:dropdown label="Schedule" dropdownList="${scheduleDropdownList}" name="idSchedule" />
						<base:dropdown label="Item Action" dropdownList="${itemActionDropdownList}" name="action" />
						
						<base:inputField  description="Time" name="scheduletime" type="text" />
						<p>Days on which this schedule should activate</p>
						<base:checkbox ticked="no" label="Monday" id="isMonday" name="isMonday" />
						<base:checkbox ticked="no" label="Tuesday" id="isTuesday" name="isTuesday" />
						<base:checkbox ticked="no" label="Wednesday" id="isWednesday" name="isWednesday" />
						<base:checkbox ticked="no" label="Thursday" id="isThursday" name="isThursday" />
						<base:checkbox ticked="no" label="Friday" id="isFriday" name="isFriday" />
						<base:checkbox ticked="no" label="Saturday" id="isSaturday" name="isSaturday" />
						<base:checkbox ticked="no" label="Sunday" id="isSunday" name="isSunday" />

						<br>
												
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