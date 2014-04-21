<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="display" uri="http://displaytag.sf.net" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
	<head>
		<title>Web Users</title>
		
		<script type="text/javascript" src="<c:url value="/resources/js/utils.js" />"></script>
		
		<script type="text/javascript">
var contextPath = "${pageContext.servletContext.contextPath}";
		
$(function() {
	$(document).ready(function() {
		// setup dialog boxes open and close
		$('#passwordBox').dialog({ autoOpen: false, width: 300, modal: true });
		$('#privilegesBox').dialog({ autoOpen: false, width: 600, modal: true });
		$('#messageBox').dialog({ autoOpen: false, width: 300, modal: true });
		$('#addBox').dialog({ autoOpen: false, width: 600, modal: true });
		
		$('#passwordCancel').click(function() {
			$('#passwordBox').dialog('close');
		});
		$('#messageOk').click(function() {
			$('#messageBox').dialog('close');
		});
		$('#privilegesCancel').click(function() {
			$('#privilegesBox').dialog('close');
		});
		$('#privilegesCancel').click(function() {
			$('#privilegesBox').dialog('close');
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
			
			$.get(contextPath + "/admin/privileges/all",
				function(privileges) {
					$.each(privileges, function(index, value) {
						$('#addBox .availablePrivileges').append('<option>' + value + '</option>');
					});						

					$('#addBox').dialog('open');
			});
			
		});
		
		// update user password
		$('#passwordSave').click(function() {
			var newPassword = $('#passwordField').val();
			var userId = $('#passwordId').val();
			if (newPassword && userId) {
				$.get(contextPath + "/admin/users/" + userId + "/changePassword/" + newPassword,
					function(response) {
						$('#passwordBox').dialog('close');
						$('#messageBox p').text(response);
						$('#messageBox').dialog('open');
				});
			}
		});
		
		// toggle user enabled
		$('input[name="enabled"]').click(function(event) {
			var enabled = event.srcElement.checked;
			var userId = event.srcElement.value;
			$.get(contextPath + "/admin/users/" + userId + "/enable/" + enabled);			
		});
		
		// move privileges in edit box
		$('#privilegesBox .moveRight').click(function(event) {
			movePrivileges($('#privilegesBox .availablePrivileges option:selected'), $('#privilegesBox .assignedPrivileges'));
		});
		$('#privilegesBox .moveLeft').click(function(event) {
			movePrivileges($('#privilegesBox .assignedPrivileges option:selected'), $('#privilegesBox .availablePrivileges'));
		});
		
		// move privileges in add box
		$('#addBox .moveRight').click(function(event) {
			movePrivileges($('#addBox .availablePrivileges option:selected'), $('#addBox .assignedPrivileges'));
		});
		$('#addBox .moveLeft').click(function(event) {
			movePrivileges($('#addBox .assignedPrivileges option:selected'), $('#addBox .availablePrivileges'));
		});
		
		function movePrivileges(src, dst) {
			$.each(src, function(index, option) {
				dst.append(option);
			});			
		}
		
		// save new user
		$('#newUserSubmit').click(function(event) {
			$('#addBox .assignedPrivileges option').attr("selected", "selected");
			$('#newUser').submit();
		});
		
		// save privilege changes
		$('#privilegesSave').click(function(event) {
			var username = $('#email').val();
			var data = [];
			data.push(username);
			$('#privilegesBox .assignedPrivileges option').each(function(index, option) {
				data.push(option.value);
			});
			
			$.ajax({
				url:contextPath + "/admin/privileges/savePrivileges/" +username,
				type:"POST",
				data:JSON.stringify(data),
				contentType:"application/json; charset=utf-8",
				success: function(response) {
					$('#privilegesBox').dialog('close');
					$('#messageBox p').text(response);
					$('#messageBox').dialog('open');
				}
			});
		});
	});
});

function resetPassword(id) {
	$('#passwordField').val('');
	$('#passwordId').val(id);
	$('#passwordBox').dialog('open');
	$('#passwordField').focus();
}

function displayPrivileges(username) {
	$('#privilegesBox .availablePrivileges').empty();
	$('#privilegesBox .assignedPrivileges').empty();
	$('#email').val(username);
	
	$.get(contextPath + "/admin/privileges/all",
			function(privileges) {
				var allPrivileges = privileges;
				$.get(contextPath + "/admin/privileges/assigned/" + username,
						function(privileges) {
							var assignedPrivileges = privileges;
							$.each(allPrivileges, function(index, value) {
								if ($.inArray(value, assignedPrivileges) == -1) {
									$('#privilegesBox .availablePrivileges').append('<option>' + value + '</option>');
								} else {
									$('#privilegesBox .assignedPrivileges').append('<option>' + value + '</option>');
								}
							});
							
							$('#privilegesBox').dialog('open');
				});
	});
}

function deleteUser(id) {
	if (confirm('Are you sure you want to delete user?')) {
		window.location.href = contextPath + "/admin/users/" + id +"/delete"; 
	}
}

// re-display add user form if there are validation errors
<spring:hasBindErrors name="newUser">
	$.get(contextPath + "/admin/privileges/all",
		function(privileges) {
			var assigned = []
			<c:forEach var="privilege" items="${newUser.privileges}">
				assigned.push("${privilege}")
			</c:forEach>
		
			$.each(privileges, function(index, value) {
				if ($.inArray(value, assigned) == -1) {
					$('#addBox .availablePrivileges').append('<option>' + value + '</option>');
				} else {
					$('#addBox .assignedPrivileges').append('<option>' + value + '</option>');
				}
			});						
			
			$('#addBox').dialog('open');
	});
</spring:hasBindErrors>

		</script>
	</head>
	<body>
		<h1>Web Users</h1>
		<display:table class="listTable" name="${users}" defaultsort="3" pagesize="50" requestURI="${pageContext.servletContext.contextPath}/admin/users" decorator="com.udm.health.displaytag.WebUserDecorator">
			<display:column property="delete" title="" />
			<display:column property="username" title="Username" sortable="true" />
			<display:column property="password" title="Password" />
			<display:column property="enabled" title="Enabled" sortable="true" />
			<display:column property="privileges" title="Privileges" />
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
		<div id="privilegesBox" title="Privileges">
			<input type="hidden" id="email" value="" />
			<div style="display: table-row; width= 100%;">
				<div class="available">
					<h4>Available</h4>
					<select class="availablePrivileges" multiple="true" size="15"></select>
				</div>
				<div class="moveButtons">
					<input type="button" class="moveRight" value="&gt;&gt;" />
					<br />
					<input type="button" class="moveLeft" value="&lt;&lt;" />
				</div>
				<div class="assigned">
					<h4>Assigned</h4>
					<select class="assignedPrivileges" multiple="true" size="15"></select>
				</div>
			</div>
			<div class="dialogButtonBox">
				<input type="button" id="privilegesSave" value="Save" />
				<input type="button" id="privilegesCancel" value="Cancel" />
			</div>
		</div>
		<div id="addBox" title="New User">
			<form:form commandName="newUser" action="${pageContext.servletContext.contextPath}/admin/users/new" method="POST">
				<div id="addBoxInput">
					<div class="row">
						<label for="newUsername">Username:</label><form:input path="username" /><form:errors path="username" cssClass="formError" />
					</div>
					<div class="row">
						<label for="newPassword">Password:</label><form:password path="password" /><form:errors path="password" cssClass="formError" />
					</div>
					<div class="row">
						<label for="newEnabled">Enabled:</label><form:checkbox path="enabled"/><form:errors path="enabled" cssClass="formError" />
					</div>
					<div class="row">
						<label for="newPrivileges">Privileges:</label>
						<form:errors path="privileges" cssClass="formError" />
						<div style="display: table-cell">
							<div class="row">
								<div class="available">
									<h4>Available</h4>
									<select class="availablePrivileges" multiple="true" size="15"></select>
								</div>
								<div class="moveButtons">
									<input type="button" class="moveRight" value="&gt;&gt;" />
									<br />
									<input type="button" class="moveLeft" value="&lt;&lt;" />
								</div>
								<div class="assigned">
									<h4>Assigned</h4>
									<form:select path="privileges" class="assignedPrivileges" multiple="true" size="15" />
								</div>
							</div>
						</div>
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