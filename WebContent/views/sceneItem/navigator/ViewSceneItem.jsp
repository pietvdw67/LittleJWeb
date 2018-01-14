<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>View Scene Items</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
	<div id="main">
		<div class="container">
			<div class="row main-row">
				<div class="6u">
				
					<h2>Scene Items for: ${sceneDTO.description} </h2>		
					<TABLE>
						<TR>
							<TH>Description</TH>
							<TH>Action</TH>
							<th>Delay</th>
							<TH>Options</TH>
						</TR>
						
						<c:forEach items="${sceneItemDTOlist}" var="sceneItemDTOlistItem">
							<tr>			
								<td>								
								${sceneItemDTOlistItem.itemDTO.description}</td>
								<td>${sceneItemDTOlistItem.actionText}</td>
								<td>${sceneItemDTOlistItem.delay}</td>
								<td>
									<base:setup targetUrl="Controller.do?nav=views.sceneItem.navigator.EditSceneItem&idsceneitem=${sceneItemDTOlistItem.idSceneItem}"></base:setup>
									<base:delete targetUrl="Controller.do?nav=views.sceneItem.navigator.DeleteSceneItem&idsceneitem=${sceneItemDTOlistItem.idSceneItem}"></base:delete>																		
								</td>
							</tr>
						</c:forEach>
					</TABLE>
					
					<br>
					
					<base:add targetUrl="Controller.do?nav=views.sceneItem.navigator.AddSceneItem&idscene=${sceneDTO.idScene}" />
					<base:setup targetUrl="Controller.do?nav=cancelMain" />				
				</div>							
			</div>
		</div>	
	</div>

	<jsp:directive.include file="/footer.jsp" />
</body>
</html>