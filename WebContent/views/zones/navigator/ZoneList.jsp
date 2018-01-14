<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>Zones</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
	<div id="main">
		<div class="container">
			<div class="row main-row">
				<div class="6u">
				
					<h2>Zones</h2>		
					<TABLE>						
						<c:forEach items="${zoneDTOlist}" var="zoneDTOlistItem">
							<tr>			
								<td>
								<a href="Controller.do?nav=views.items.navigator.ItemFilter&filterType=zone&idzone=${zoneDTOlistItem.idZone}">
									<image src="${zoneDTOlistItem.zoneImagePath}" alt="${zoneDTOlistItem.zoneImage}" border="0" width="40" height="40" /> 
									${zoneDTOlistItem.zoneName}
									<c:if test="${zoneDTOlistItem.temperature > 0}">
										<span style="display:inline-block; width:2em;"></span>
										${zoneDTOlistItem.temperature}&#8451
									</c:if>
								</a>

								</td>
							</tr>
						</c:forEach>
					</TABLE>	
					
					<BR>
					<base:add targetUrl="Controller.do?nav=setup.hardware.zones.navigator.AddZone" />		
				</div>							
			</div>
		</div>	
	</div>

	<jsp:directive.include file="/footer.jsp" />
</body>
</html>