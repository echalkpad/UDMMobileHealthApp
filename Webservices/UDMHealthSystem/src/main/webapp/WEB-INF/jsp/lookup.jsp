<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
	<head>
		<title>Available Patients</title>
		<link type="text/css" href="<c:url value="/resources/css/displayTagStyle.css"/>" rel="stylesheet" />	
		<script type="text/javascript" src="<c:url value="/resources/js/utils.js" />"></script>
		
		<script type="text/javascript">
var contextPath = "${pageContext.servletContext.contextPath}";
		
$(function() {
	$(document).ready(function() {
		// setup dialog boxes open and close
		

	});
});

function followUser(id) {
	window.location.href = contextPath + "/admin/lookup/" + id +"/add"; 
}

 		</script>
	</head>
	<body>
		<h1>Available Patients</h1>
		<display:table class="listTable" name="${userList}" defaultsort="3" pagesize="50" requestURI="${pageContext.servletContext.contextPath}/admin/lookup" decorator="com.udm.health.displaytag.UserAccessDecorator">
			<display:column property="firstName" title="First Name" sortable="true" />
			<display:column property="lastName" title="Last Name" sortable="true" />
			<display:column property="dateOfBirth" title="Date Of Birth" sortable="true" />
			<display:column property="street" title="Street" sortable="true"/>
			<display:column property="city" title="City" sortable="true"/>
			<display:column property="state" title="State" sortable="true"/>
			<display:column property="add" title="Add"/>
		</display:table>

	</body>
</html>