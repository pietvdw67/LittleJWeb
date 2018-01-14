<%@ attribute name="scene" required="true" type="littleJ.views.dto.SceneDTO" %>
<%@ attribute name="showLabel" required="true" %>
<%@ attribute name="showImage" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a href="Controller.do?nav=processor.statuschange.navigator.StatusChangeSceneUpdate&idscene=${scene.idScene}">
	<c:if test="${showImage eq true}">			
		<image src="${scene.imagePath}" alt="${scene.description}" width="40" height="40" />	
	</c:if>
	<c:if test="${showLabel eq true}">
		${scene.description}
	</c:if>
</a>
