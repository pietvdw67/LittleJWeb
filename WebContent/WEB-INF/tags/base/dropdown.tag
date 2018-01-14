<%@ attribute name="dropdownList" required="true" type="java.util.List" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="defaultvalue" required="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p> ${label}:</p>
<p>
	<select name="${name}" >
		<c:forEach items="${dropdownList}" var="dropdownListItem">
			<c:if test="">
			</c:if>
			<c:choose>
				<c:when test="${defaultvalue != null && defaultvalue == dropdownListItem.name}">
					<option value="${dropdownListItem.name}" selected="${dropdownListItem.name}">${dropdownListItem.text} </option>
				</c:when>
				<c:otherwise>
					<option value="${dropdownListItem.name}">${dropdownListItem.text}</option>
				</c:otherwise>
			</c:choose>			
		</c:forEach>
	</select>
</p>
