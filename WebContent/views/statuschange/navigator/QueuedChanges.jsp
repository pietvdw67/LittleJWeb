<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>Queued status changes</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
	<div id="main">
		<div class="container">
			<div class="row main-row">
				<div class="6u">
				
					<h2>Queued status changes</h2>		
					<TABLE>	
						<tr>
							<td>
								Item
							</td>
							<td>
								Action
							</td>
							<td>
								Process Time
							</td>
							<td />
						</tr>					
						<c:forEach items="${statusChangeDTOlist}" var="statusChangeDTOlistItem">
							<tr>			
								<td>
									${statusChangeDTOlistItem.itemDTO.description}
								</td>
								<td>
									${statusChangeDTOlistItem.action}
								</td>
								<td>
									${statusChangeDTOlistItem.processtime}
								</td>
								<td>
									<base:delete targetUrl="Controller.do?nav=views.statuschange.navigator.Removechange&idstatuschange=${statusChangeDTOlistItem.idstatuschange}"></base:delete>
								</td>
							</tr>
						</c:forEach>
					</TABLE>		
				</div>							
			</div>
		</div>	
	</div>

	<jsp:directive.include file="/footer.jsp" />
</body>
</html>