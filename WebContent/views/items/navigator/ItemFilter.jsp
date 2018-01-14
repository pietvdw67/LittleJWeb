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
			setInterval(function() {
				$.ajax({
					url : 'Controller.do?nav=views.items.navigator.ItemListDisplay',
					dataType: "html",
					data : {					
						showImage : true,
						showLabel : true,
						filterType : '${filterType}',
						iditemtype : '${iditemtype}',
						idzone : '${idzone}',
					},
					success : function(responseText) {					
						$('#ajaxItem').html(responseText);
					}
				});
			}, ${pageRefreshInterval});
		});
	</script>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
	<div id="main">
		<div class="container">			
			<c:if test="${idzone > 0 }">
				<div class="12u">
					<div class="row">
						<h2>
							${zoneDTO.zoneName}
						</h2>	
					</div>	
				</div>	
				
				<c:if test="${zoneDTO.temperature > 0}">
					<div class="12u">
						<div class="row">
							<p>
								Temperature: ${zoneDTO.temperature}&#8451
							</p>
						</div>
					</div>	
				</c:if>
			</c:if>
		
			<div class="row main-row">
				<div class="6u">
					<div id="ajaxItem">
					</div>		
					<TABLE>
						<c:forEach items="${sceneDTOlist}" var="sceneDTOlistItem">
							<tr>											
								<td>
									<base:scene showLabel="true" showImage="true" scene="${sceneDTOlistItem}"></base:scene>
								<td>
									<base:setup targetUrl="Controller.do?nav=views.scene.navigator.EditScene&idscene=${sceneDTOlistItem.idScene}" />
									<base:listItems targetUrl="Controller.do?nav=views.sceneItem.navigator.ViewSceneItem&idscene=${sceneDTOlistItem.idScene}" />
									<c:choose>
										<c:when test="${filterType eq 'favourite' }">
											<base:favouriteRemove type="scene" id="${sceneDTOlistItem.idScene}"></base:favouriteRemove>
										</c:when>
										<c:otherwise>										
											<base:favouriteAdd type="scene" id="${sceneDTOlistItem.idScene}"></base:favouriteAdd>
										</c:otherwise>
									</c:choose>								
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