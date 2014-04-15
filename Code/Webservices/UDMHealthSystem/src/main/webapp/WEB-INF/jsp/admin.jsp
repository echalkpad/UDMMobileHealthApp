<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
	<head>
		<title>Personal Information</title>
		<link type="text/css" href="<c:url value="/resources/css/displayTagStyle.css"/>" rel="stylesheet" />	
		<script type="text/javascript" src="<c:url value="/resources/js/utils.js" />"></script>
		<script type="text/javascript" src="<c:url value="/resources/js/picnet.table.filter.min.js" />"></script>
		
		<script type="text/javascript">
var contextPath = "${pageContext.servletContext.contextPath}";
		
$(function() {
	$(document).ready(function() {
		$( "#personalInformationContainer" ).show( "shake", null, 500, null );
	});
});


		</script>
	</head>
	<body>
		<div class="toggler">
		  <div id="personalInformationContainer" class="ui-widget-content ui-corner-all">
		    <h3 class="ui-widget-header ui-corner-all">Personal Information</h3>
		    	<div class="divRow">
			    	<table>
			    		<tr>
			    			<td>First Name:</td>
			    			<td>${user.firstName}</td>
			    			<td>Last Name:</td>
			    			<td>${user.lastName}</td>
			    		</tr>
			    		<tr>
			    			<td><label for="dob">Date of Birth: </label></td>
			    			<td>${user.dateOfBirth}</td>
			    			<td><label for="email">Email: </label></td>
			    			<td>${user.email}</td>
			    		</tr>
			    		<tr>
			    			<td><label for="phoneNumber">Phone Number: </label></td>
			    			<td>${user.phoneNumber}</td>
			    			<td><label for="ssn">SSN: </label></td>
			    			<td>${user.ssn}</td>
			    		</tr>
			    		<tr>
			    			<td><label for="street">Street: </label></td>
			    			<td>${user.street}</td>
			    			<td><label for="city">City: </label></td>
			    			<td>${user.city}</td>
			    		</tr>
			    		<tr>
			    			<td><label for="zipCode">Zip Code: </label></td>
			    			<td>${user.zipCode}</td>
			    			<td><label for="state">State: </label></td>
			    			<td>${user.state}</td>
			    		</tr>
			    	</table>
		    	</div>
		  </div>
		</div>

	</body>
</html>