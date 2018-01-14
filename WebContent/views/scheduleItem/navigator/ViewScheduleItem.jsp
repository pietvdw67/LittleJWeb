<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>View Schedule Items</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
	<div id="main">
		<div class="container">
			<div class="row main-row">
				<div class="6u">
				
					<h2>Schedule Items for: ${scheduleDTO.description} </h2>		
					<TABLE>
						<TR>
							<TH>Description</TH>
							<TH>Action</TH>
							<TH>Time</TH>
							<TH>Options</TH>
						</TR>
						
						<c:forEach items="${scheduleItemDTOlist}" var="scheduleItemDTOlistItem">
							<tr>			
								<td>								
									${scheduleItemDTOlistItem.itemDTO.description}
								</td>
								<td>								
									${scheduleItemDTOlistItem.actionText}
								</td>
								<td>								
									${scheduleItemDTOlistItem.scheduleTime}
								</td>
								<td>
									<base:setup targetUrl="Controller.do?nav=views.scheduleItem.navigator.EditScheduleItem&idsheduleitem=${scheduleItemDTOlistItem.idScheduleItem}"></base:setup>
									<base:delete targetUrl="Controller.do?nav=views.scheduleItem.navigator.DeleteScheduleItem&idsheduleitem=${scheduleItemDTOlistItem.idScheduleItem}"></base:delete>																		
								</td>
							</tr>
						</c:forEach>
					</TABLE>
					
					<br>
					
					<base:add targetUrl="Controller.do?nav=views.scheduleItem.navigator.AddScheduleItem&idshedule=${scheduleDTO.idSchedule}" />
					<base:cancelButtonMain />				
				</div>							
			</div>
		</div>	
	</div>

	<jsp:directive.include file="/footer.jsp" />
</body>
</html>