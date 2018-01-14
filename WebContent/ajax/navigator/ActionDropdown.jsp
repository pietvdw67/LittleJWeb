<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<p> Action :</p>
<p>
	<select name="action" >
		<c:forEach items="${dropdownList}" var="dropdownListItem">
			<option value="${dropdownListItem.name}">${dropdownListItem.text}</option>		
		</c:forEach>
	</select>
</p>