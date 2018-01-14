<%@ taglib prefix="base" tagdir="/WEB-INF/tags/base" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="footer-wrapper">
	<div class="container">
		<div class="row">
			<div class="12u">
				<hr>
			</div>
		</div>
		<div class="row">
			<div class="12u">
				<br>
				<base:refresh targetUrl="Controller.do?nav=cancelMain"></base:refresh>
				<div align="right">
					<base:input />
					<base:schedule />	
					<base:StopAudio />				
					<base:setup targetUrl="Controller.do?nav=setup.navigator.Setup" />
				</div>
			</div>
		</div>
	</div>
</div>	