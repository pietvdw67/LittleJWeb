<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>View intput items</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
	<div id="main">
		<div class="container">
			<div class="row main-row">
				<div class="6u">
				
					<h2>Input Items</h2>		
					<TABLE>						
						<c:forEach items="${itemDTOlist}" var="itemDTOlistItem">
							<tr>			
								<td>
									<base:item showLabel="true" showImage="true" item="${itemDTOlistItem}"></base:item>
								</td>
								<td>
									<base:setup targetUrl="Controller.do?nav=setup.hardware.items.navigator.EditItem&idItem=${itemDTOlistItem.idItem}" />
									<base:listItems targetUrl="Controller.do?nav=views.items.navigator.ViewInputTargetItems&idItem=${itemDTOlistItem.idItem}"></base:listItems>																	
								</td>
							</tr>
						</c:forEach>
					</TABLE>
					
					<br>			
									
				</div>							
			</div>
		</div>	
	</div>

	<jsp:directive.include file="/footer.jsp" />
</body>
</html>