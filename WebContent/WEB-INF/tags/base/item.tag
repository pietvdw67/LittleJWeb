<%@ attribute name="item" required="true" type="littleJ.hardware.dto.ItemDTO" %>
<%@ attribute name="showLabel" required="true" %>
<%@ attribute name="showImage" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a href="Controller.do?nav=processor.statuschange.navigator.StatusChangeUpdate&iditem=${item.idItem}&status=${item.status}">
	<c:if test="${showImage eq true}">
		<c:choose>
			<c:when test="${item.status eq '1'}">
				<image src="${item.itemTypeDTO.imagePathOn}" alt="${item.description}" width="40" height="40" />
			</c:when>
			<c:otherwise>
				<image src="${item.itemTypeDTO.imagePathOff}" alt="${item.description}" width="40" height="40" />
			</c:otherwise>
		</c:choose>	
	</c:if>
	<c:if test="${showLabel eq true}">
		${item.description}
	</c:if>
</a>
