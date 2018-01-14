<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />
	<title>View Scenes</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
	<div id="main">
		<div class="container">
			<div class="row main-row">
				<div class="6u">
				
					<h2>Scenes</h2>				
					<TABLE>						
						<c:forEach items="${sceneDTOlist}" var="sceneDTOlistItem">
							<tr>	
								<td>
									<base:scene showLabel="true" showImage="true" scene="${sceneDTOlistItem}"></base:scene>									
								</td>
								<td>
									<base:setup targetUrl="Controller.do?nav=views.scene.navigator.EditScene&idscene=${sceneDTOlistItem.idScene}" />									
									<base:listItems targetUrl="Controller.do?nav=views.sceneItem.navigator.ViewSceneItem&idscene=${sceneDTOlistItem.idScene}" />
									<base:favouriteAdd type="scene" id="${sceneDTOlistItem.idScene}"></base:favouriteAdd>
								</td>
							</tr>
						</c:forEach>
					</TABLE>
					
					<br>
					
					<base:add targetUrl="Controller.do?nav=views.scene.navigator.AddScene" />				
				</div>							
			</div>
		</div>	
	</div>

	<jsp:directive.include file="/footer.jsp" />
</body>
</html>