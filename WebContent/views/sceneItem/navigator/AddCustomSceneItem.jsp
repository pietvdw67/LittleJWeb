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
			$("#note").hide();
			$('#noteImage').click(function() {
			 	$("#note").slideToggle();     
			});
		});
	</script>
	
	<title>Add item to scene</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">
			<div class="container">
				<div class="row main-row">	
					<div class="6u">
						<input type="hidden" name="type" value = "${type}" />
						<input type="hidden" name="id" value = "${id}" />						
						<input type="hidden" name="nav" value = "views.sceneItem.navigator.AddCustomeSceneItemInsert" />
					
						<c:if test="${type eq 'item'}">
							<H2>Add item ${itemDTO.description} to a existing or new scene</H2>
						</c:if>
						<br>
						<p> Either enter a new scene description to create a new scene or select from an existing scene to add this item to it
						</p>						
						<base:inputField  description="Scene" name="sceneDescription" type="text" />
						<base:dropdown label="Scene" dropdownList="${sceneDropdownList}" name="idScene" />
						<base:dropdown label="Item Action" dropdownList="${itemActionDropdownList}" name="action" />

						<p>
							<base:inputFieldNoBreak  description="Delay" name="delay" type="text"  />
							<img border="0" id="noteImage" alt="edit" src="images/base/question.png" width="20" height="20">
						</p>
								
						<div id="note" class="noteblock">
							<p> Add one of the following suffixes , s - seconds, m - minutes, h - hours </p>
							<p> Example 5m - For 5 minutes</p>
						</div>
				
						<br>
						
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