<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>View Item Types</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
	<div id="main">
		<div class="container">
			<div class="row main-row">
				<div class="6u">
				
					<h2>Item Types</h2>		
					<TABLE>						
						<c:forEach items="${itemTypeDTOList}" var="itemTypeDTOListItem">
							<tr>			
								<td>
									<base:itemtype itemType="${itemTypeDTOListItem}"  showImage="true" showLabel="true" ></base:itemtype>
								</td>
								<td>
									<base:setup targetUrl="Controller.do?nav=setup.hardware.itemTypes.navigator.EditItemType&idItemType=${itemTypeDTOListItem.idItemType}" />									
								</td>
							</tr>
						</c:forEach>
					</TABLE>
					
					<br>
					
					<base:add targetUrl="Controller.do?nav=setup.hardware.itemTypes.navigator.AddItemType" />
					<base:setup targetUrl="Controller.do?nav=setup.navigator.Setup" />				
				</div>							
			</div>
		</div>	
	</div>

	<jsp:directive.include file="/footer.jsp" />
</body>
</html>