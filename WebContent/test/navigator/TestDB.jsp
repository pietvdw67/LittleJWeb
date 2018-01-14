<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Testing DB</title>
</head>
<body>	
		<div id="main">
			<div class="container">
				<div class="row main-row">	
					<div class="6u">
							<h2>Testing DB</h2>
					</div>				
				</div>
				
				<div class="row main-row">	
					<div class="6u">
						<h2>Db Connection parameters</h2>
						<p>DB Server: ${dbURL}</p>
						<p>DB Username:${dbUser}</p>
						<p>DB Password:${dbPassword}</p>
					</div>				
				</div>
				
				<div class="row main-row">	
					<div class="6u">
						<h2>Connection test</h2>
						<p>${connectionMessage}</p>
					</div>				
				</div>
				
			</div>
		</div>
</body>	
</html>