<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
		<title><sitemesh:write property='title'/></title>
		<link type="text/css" href="<c:url value="/resources/css/blitzer/jquery-ui-1.10.4.custom.css" />" rel="stylesheet" />	
		<link type="text/css" href="<c:url value="/resources/css/style.css" />" rel="stylesheet" />	
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-1.10.2.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.10.4.custom.min.js" />"></script>
		
		<script type="text/javascript">
$(function() {
	$(document).ready(function() {
		$('.headerLink').button();
		$('.button').button();
	});
});
		</script>
		
		<sitemesh:write property='head'/>
	</head>
	<body>
		<div id="header" class="ui-widget ui-helper-clearfix">
			<h1 id="bannerTitle">UDM Health System</h1>
			<sec:authorize access="isAuthenticated()">
				<ul>
					<sec:authorize access="hasRole('ADMIN')">
						<li><a class="headerLink" href="${pageContext.servletContext.contextPath}/admin/users">Web Users</a></li>
						<li><a class="headerLink" href="${pageContext.servletContext.contextPath}/admin/logs">Logs</a></li>
					</sec:authorize>
				</ul>
				<div class="right">
					<a href="<c:url value="/j_spring_security_logout" />" >Logout</a>
				</div>
			</sec:authorize>
		</div>
		<div id="body">
			<div id="content" class="ui-widget-content ui-corner-all ui-helper-clearfix">
				<sitemesh:write property='body'/>
			</div>	
		</div>
		<div id="footer">
<%-- 			<div id="version"><%= com.budco.bls.util.VersionUtil.getVersion() %></div> --%>
		</div>
	</body>
</html>