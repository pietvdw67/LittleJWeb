<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>

<div id="header-wrapper">
	<div class="container">
		<div class="row">
			<div class="12u">						
				<header id="header">
					<h1><a href="#" id="logo">littleJ</a></h1>
					<nav id="nav">
						<c:forEach items="${headerBarDTOlist}" var="headerBarDTOlistItem">						
							<a href="${headerBarDTOlistItem.url}">							
								<img border="0" alt="${headerBarDTOlistItem.label}" src="${headerBarDTOlistItem.imageUrl}" width="40" height="40">
							</a>
						</c:forEach>
					</nav>
					<nav id="navMobile">
						<br>		
						<h1>littjeJ</h1>
						<br>					
						<c:forEach items="${headerBarDTOlist}" var="headerBarDTOlistItem">						
							<a href="${headerBarDTOlistItem.url}">							
								<img border="0"  alt="${headerBarDTOlistItem.label}" src="${headerBarDTOlistItem.imageUrl}" width="40" height="40">
								${headerBarDTOlistItem.label}
							</a><br>
						</c:forEach>						
					</nav>
				</header>					
			</div>			
		</div>
		<c:if test="${not empty alert}">
			<div class="row">
				<div class="12u">
					<h3>${alert}</h3>
				</div>
			</div>
		</c:if>
	</div>
</div>