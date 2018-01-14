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

			$("#idItem").change(function(){
				 $.ajax({
			        url: 'Controller.do?nav=ajax.navigator.ActionDropdown',
			        dataType: "html",
			        data : {
				        actionValue : $(this).val(),
			        },
			        success: function(responseText){
			        	$('#ajaxActionDropdown').html(responseText);
			        }
				});
			});
			
		});
	</script>

	<title>Add an item for: ${sceneDTO.description}</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">
			<div class="container">
				<div class="row main-row">	
					<div class="6u">
						<input type="hidden" name="idscene" value = "${sceneDTO.idScene}" />
						<input type="hidden" name="nav" value = "views.sceneItem.navigator.AddSceneItemInsert" />
					
						<h2>Add new Item</h2>	
						
						<p>Item</p>
						<p>
							<select id="idItem" name="idItem" >
								<c:forEach items="${itemDropdownList}" var="itemDropdownListItem">
									<option value="${itemDropdownListItem.name}">${itemDropdownListItem.text}</option>		
								</c:forEach>
							</select>
						</p>
						
						<div id="ajaxActionDropdown">
							<p> Item Action :</p>
							<p>
								<select name="action" >
									<c:forEach items="${itemActionDropdownList}" var="itemActionDropdownListItem">
										<option value="${itemActionDropdownListItem.name}">${itemActionDropdownListItem.text}</option>		
									</c:forEach>
								</select>
							</p>
						</div>					
						
						<p>
							<base:inputFieldNoBreak  description="Delay" name="delay" type="text" />
							<img border="0" id="noteImage" alt="edit" src="images/base/question.png" width="20" height="20">
						</p>
								
						<div id="note" class="noteblock">
							<p> Add one of the following suffixes , s - seconds, m - minutes, h - hours </p>
							<p> Example 5m - For 5 minutes</p>
						</div>
												
						<br>
						
						<base:submit /> 
						<base:cancel  targetUrl="Controller.do?nav=views.scene.navigator.SceneList" />
					
					</div>
				</div>
				
			</div>
		</div>
	</form>	
	
	<jsp:directive.include file="/footer.jsp" />
</body>	
</html>