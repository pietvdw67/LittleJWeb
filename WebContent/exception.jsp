<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
	<div id="main">			
		<div class="container">
			<div class="row main-row">	
				<div class="6u">				
					<h2>${ErrorMessage}</h2>
					<br>
					<p>
						${ErrorText}
					</p>
				</div>
			</div>
		</div>
	</div>
</body>
</html>