<%@ attribute name="type" required="true" %>
<%@ attribute name="id" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a href="Controller.do?nav=views.scheduleItem.navigator.AddCustomScheduleItem&type=${type}&id=${id}">
	<img border="0" alt="Add schedule" src="images/base/scheduleadd.png" width="40" height="40">
</a>