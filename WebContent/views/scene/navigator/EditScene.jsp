<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />

	<title>Edit Scene</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">			
			<div class="container">
				<div class="row main-row">	
					<div class="6u">	

						<div align="right">
							<base:delete targetUrl="Controller.do?nav=views.scene.navigator.DeleteScene&idscene=${sceneDTO.idScene}" />
						</div>
				
						<h2>Edit Scene</h2>

						<input type='hidden' name='idscene' value='${sceneDTO.idScene}' />
						<input type='hidden' name='nav' value='views.scene.navigator.EditSceneUpdate' />
							
						<base:inputField  description="Description" name="description" type="text" value="${sceneDTO.description}" />
						<base:checkbox ticked="${isFavourite}" label="Add to Favourites" id="isFavourite" name="isFavourite" />
	
						<br><br>
						
						<span style="display:inline-block; width:2em;"></span>						
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
	
	