<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<jsp:directive.include file="/header.jsp" />	
	<title>Add an item type</title>
</head>
<body>
	<jsp:directive.include file="/headerBar.jsp" />
		
	<form action="Controller.do" method="POST">
		<div id="main">
			<div class="container">
				<div class="row main-row">	
					<div class="6u">
						<input type="hidden" name="nav" value = "setup.hardware.itemTypes.navigator.AddItemTypeInsert" />
					
						<h2>Add new Item type</h2>

						<base:inputField  description="Item Type Name" name="itemtypename" type="text" />
						<base:dropdown label="Image" dropdownList="${dropdownList}" name="itemtypeimage" />
						<base:checkbox label="Is output:" id="isoutput" name="isoutput" ticked="yes" />
						<base:checkbox label="Is audio:" id="isaudio" name="isaudio" ticked="no" />
						
						<br>
						
						<base:submit /> 
						<base:cancel  targetUrl="Controller.do?nav=setup.hardware.itemTypes.navigator.ViewItemType" />
					
					</div>
				</div>
				
			</div>
		</div>
	</form>	
	
	<jsp:directive.include file="/footer.jsp" />
</body>	
</html>