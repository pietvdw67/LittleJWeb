<%@ attribute name="itemType" required="true" type="littleJ.hardware.dto.ItemTypeDTO" %>
<%@ attribute name="showLabel" required="true" %>
<%@ attribute name="showImage" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${showImage eq true}">
	<image src="${itemType.imagePathOff}" alt="${itemType.itemTypeName} icon" width="40" height="40" />
</c:if>
<c:if test="${showLabel eq true}">
	${itemType.itemTypeName}
</c:if>

