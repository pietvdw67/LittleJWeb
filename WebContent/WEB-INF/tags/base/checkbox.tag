<%@ attribute name="name" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="ticked" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p>
	<input type="checkbox" name="${name}" value="${name}" id="${id}"
		<c:if test="${ticked == 'yes'}">			
			checked
		</c:if>
	>${label}<BR>
</p>