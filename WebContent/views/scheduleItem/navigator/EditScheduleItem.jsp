<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	
	<script>
		$(document).ready(function() {
			$("#idItem").change(function(){
				 $.ajax({
			        url: 'Controller.do?nav=ajax.navigator.ActionDropdown',
			        dataType: "html",
			        data : {
				        actionValue : $(this).val(),
			        },
			        success: function(responseText){
			        	$('#ajaxActionDropdown').html(responseText);
			        }
				});
			});
			
		});
	</script>
	
	<title>edit an item for: ${scheduleDTO.description}</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">
			<div class="container">
				<div class="row main-row">	
					<div class="6u">
						<input type="hidden" name="idscheduleitem" value = "${scheduleItemDTO.idScheduleItem}" />
						<input type="hidden" name="idschedule" value = "${scheduleItemDTO.idSchedule}" />
						<input type="hidden" name="nav" value = "views.scheduleItem.navigator.EditScheduleItemUpdate" />
					
						<h2>Edit schedule item</h2>		
						
						<p>Item</p>
						<p>
							<select id="idItem" name="idItem" >
								<c:forEach items="${itemDropdownList}" var="itemDropdownListItem">
									<c:choose>
										<c:when test="${scheduleItemDTO.idItem != null && itemDropdownListItem.name == scheduleItemDTO.idItem}">
											<option value="${itemDropdownListItem.name}" selected="${itemDropdownListItem.name}">${itemDropdownListItem.text} </option>	
										</c:when>
										<c:otherwise>
											<option value="${itemDropdownListItem.name}">${itemDropdownListItem.text}</option>
										</c:otherwise>										
									</c:choose>									
								</c:forEach>
							</select>
						</p>
						
						<div id="ajaxActionDropdown">
							<p> Item Action :</p>
							<p>
								<select name="action" >
									<c:forEach items="${itemActionDropdownList}" var="itemActionDropdownListItem">
										<c:choose>
											<c:when test="${scheduleItemDTO.action != null && itemActionDropdownListItem.name == scheduleItemDTO.action}">
												<option value="${itemActionDropdownListItem.name}" selected="${itemActionDropdownListItem.name}" >${itemActionDropdownListItem.text}</option>
											</c:when>
											<c:otherwise>
												<option value="${itemActionDropdownListItem.name}">${itemActionDropdownListItem.text}</option>
											</c:otherwise>
										</c:choose>												
									</c:forEach>
								</select>
							</p>
						</div>				
						
						<base:inputField  description="Time" name="scheduleTimeText" type="text" value="${scheduleTimeText}" />
						<p>Days on which this schedule should activate</p>
							
						<base:checkbox ticked="${checkMonday}" label="Monday" id='isMonday' name="isMonday" />
						<base:checkbox ticked="${checkTuesday}" label="Tuesday" id='isTuesday' name="isTuesday" />
						<base:checkbox ticked="${checkWednesday}" label="Wednesday" id='isWednesday' name="isWednesday" />
						<base:checkbox ticked="${checkThursday}" label="Thursday" id='isThursday' name="isThursday" />
						<base:checkbox ticked="${checkFriday}" label="Friday" id='isFriday' name="isFriday" />
						<base:checkbox ticked="${checkSaturday}" label="Saturday" id='isSaturday' name="isSaturday" />
						<base:checkbox ticked="${checkSunday}" label="Sunday" id='isSunday' name="isSunday" />


						<br>
												
						<br>
						
						<base:submit /> 
						<base:cancelButton />
					
					</div>
				</div>
				
			</div>
		</div>
	</form>	
	
	<jsp:directive.include file="/footer.jsp" />
</body>	
</html>