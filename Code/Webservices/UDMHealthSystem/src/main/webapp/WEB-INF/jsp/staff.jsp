<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
	<head>
		<title>Medical Staff</title>
		
		<script type="text/javascript" src="<c:url value="/resources/js/utils.js" />"></script>
		
		<script type="text/javascript">
var contextPath = "${pageContext.servletContext.contextPath}";
		
$(function() {
	$(document).ready(function() {
		// setup dialog boxes open and close
		$('#passwordBox').dialog({ autoOpen: false, width: 300, modal: true });
		$('#messageBox').dialog({ autoOpen: false, width: 300, modal: true });
		$('#addBox').dialog({ autoOpen: false, width: 800, modal: true });
		
		$('#passwordCancel').click(function() {
			$('#passwordBox').dialog('close');
		});
		
		$('#messageOk').click(function() {
			$('#messageBox').dialog('close');
		});
		
		$('#newUserCancel').click(function() {
			$('#addBox').dialog('close');
		});
		
		// add user form setup
		$('#addUser').click(function() {
			$('#addBox input, #addBox select').each(function() {
				clearFormField(this);
			});
			$('.formError').detach();

			$.get(contextPath + "/admin/states/all",
				function(states) {
					$.each(states, function(index, value) {
						$('#addBox .states').append('<option>' + value + '</option>');
					});						

					$('#addBox').dialog('open');
			});
			
		});
		
		// update user password
		$('#passwordSave').click(function() {
			var newPassword = $('#passwordField').val();
			var userId = $('#passwordId').val();
			if (newPassword && userId) {
				$.get(contextPath + "/admin/staff/" + userId + "/changePassword/" + newPassword,
					function(response) {
						$('#passwordBox').dialog('close');
						$('#messageBox p').text(response);
						$('#messageBox').dialog('open');
				});
			}
		});
		
		
		// save new user
		$('#newUserSubmit').click(function(event) {
			$('#newUser').submit();
		});
		

	});
});

function resetPassword(id) {
	$('#passwordField').val('');
	$('#passwordId').val(id);
	$('#passwordBox').dialog('open');
	$('#passwordField').focus();
}

function deleteUser(id) {
	if (confirm('Are you sure you want to delete user?')) {
		window.location.href = contextPath + "/admin/staff/" + id +"/delete"; 
	}
}

// re-display add user form if there are validation errors
<spring:hasBindErrors name="newUser">
	$.get(contextPath + "/admin/states/all",
		function(states) {
			$.each(states, function(index, value) {
				$('#addBox .states').append('<option>' + value + '</option>');
			});						

			$('#addBox').dialog('open');
	});
</spring:hasBindErrors>

 		</script>
	</head>
	<body>
		<h1>Medical Staff</h1>
		<display:table class="listTable" name="${staffList}" defaultsort="3" pagesize="50" requestURI="${pageContext.servletContext.contextPath}/admin/staff" decorator="com.udm.health.displaytag.MedicalStaffDecorator">
			<display:column property="delete" title="" />
			<display:column property="name" title="First Name" sortable="true" />
			<display:column property="lastName" title="Last Name" />
			<display:column property="speciality" title="Speciality" sortable="true" />
			<display:column property="password" title="Password" />
		</display:table>
		<a href="#" id="addUser" class="button"><img src="${pageContext.servletContext.contextPath}/resources/images/add.png" /> Add</a>
		<div id="messageBox" title="Info">
			<p></p>
			<div class="dialogButtonBox">
				<input type="button" id="messageOk" value="Ok" />
			</div>
		</div>
		<div id="passwordBox" title="Reset Password">
			<form id="passwordForm" name="passwordForm">
				<label for="passwordField">Password: 
				<input type="password" id="passwordField" size="10" /></label>
				<input type="hidden" id="passwordId" value="" />
				<div class="dialogButtonBox">
					<input type="button" id="passwordSave" value="Save" />
					<input type="button" id="passwordCancel" value="Cancel" />
				</div>
			</form>
		</div>
		<div id="addBox" title="New Medical Staff">
			<form:form commandName="newUser" action="${pageContext.servletContext.contextPath}/admin/staff/new" method="POST">
				<div id="addBoxInput">
					<div class="row">
						<label for="newUsername">Email:</label><form:input path="username" /><form:errors path="username" cssClass="formError" />
						<label for="newPassword">Password:</label><form:password path="password" /><form:errors path="password" cssClass="formError" />
					</div>
					<div class="row">
						<label for="newFirstName">First Name:</label><form:input path="firstName" /><form:errors path="firstName" cssClass="formError" />
						<label for="newLastName">Last Name:</label><form:input path="lastName" /><form:errors path="lastName" cssClass="formError" />
					</div>
					<div class="row">
						<label for="newSpeciality">Speciality:</label><form:input path="speciality" /><form:errors path="speciality" cssClass="formError" />
						<label for="newDateOfBirth">Date of Birth:</label><form:input path="dateOfBirth" /><form:errors path="dateOfBirth" cssClass="formError" />
					</div>
					<div class="row">
						<label for="newPhoneNumber">Phone Number:</label><form:input path="phoneNumber" /><form:errors path="phoneNumber" cssClass="formError" />
						<label for="newSSN">SSN:</label><form:input path="ssn" /><form:errors path="ssn" cssClass="formError" />
					</div>
					<div class="row">
						<label for="newStreet">Street:</label><form:input path="street" /><form:errors path="street" cssClass="formError" />
						<label for="newCity">City:</label><form:input path="city" /><form:errors path="city" cssClass="formError" />
					</div>
					<div class="row">
						<label for="newState">State:</label>
						<form:select  class="states" path="state"/>
						<label for="zip">Zip Code:</label><form:input path="zip" /><form:errors path="zip" cssClass="formError" />
					</div>

				</div>
				
				<div class="dialogButtonBox">
					<input type="button" id="newUserSubmit" value="Add" />
					<input type="button" id="newUserCancel" value="Cancel" />
				</div>
			</form:form>
		</div>		
	</body>
</html>