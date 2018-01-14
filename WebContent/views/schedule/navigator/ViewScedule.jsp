<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>View Schedules</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
	<div id="main">
		<div class="container">
			<div class="row main-row">
				<div class="6u">
				
					<h2>Schedules</h2>				
					<TABLE>						
						<c:forEach items="${scheduleDTOlist}" var="scheduleDTOlistItem">
							<tr>	
								<td>
									<a href="Controller.do?nav=views.schedule.navigator.ActivateDeactivateSchedule&idschedule=${scheduleDTOlistItem.idSchedule}">
										<c:choose>
											<c:when test = "${scheduleDTOlistItem.active eq true}">											
													<image src="${scheduleDTOlistItem.imagePathOn}" alt="Schedule Image on" border="0" width="40" height="40" />
											</c:when>
											<c:otherwise>
												<image src="${scheduleDTOlistItem.imagePathOff}" alt="Schedule Image off" border="0" width="40" height="40" />
											</c:otherwise>
										</c:choose>	
										${scheduleDTOlistItem.description}
									</a>
								</td>
								<td>
									<base:setup targetUrl="Controller.do?nav=views.schedule.navigator.EditSchedule&idschedule=${scheduleDTOlistItem.idSchedule}" />									
									<base:listItems targetUrl="Controller.do?nav=views.scheduleItem.navigator.ViewScheduleItem&idschedule=${scheduleDTOlistItem.idSchedule}" />
								</td>
							</tr>
						</c:forEach>
					</TABLE>
					
					<br>
					
					<base:add targetUrl="Controller.do?nav=views.schedule.navigator.AddSchedule" />				
				</div>							
			</div>
		</div>	
	</div>

	<jsp:directive.include file="/footer.jsp" />
</body>
</html>