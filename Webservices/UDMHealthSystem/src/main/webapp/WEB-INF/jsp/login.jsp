<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
	<head>
		<title>UDM Health System Web Admin Login</title>
		<script type="text/javascript">
$(function() {
	$(document).ready(function() {
		$('#j_username').focus();		
	});
});
		</script>
	</head>
	<body>
		<h3>Login</h3>
	
		<c:if test="${not empty error}">
			<div class="error">
				Your login attempt was not successful, try again.<br /> Caused :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>
	
		<form action="<c:url value='/j_spring_security_check' />" method='POST'>
			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' id="j_username" name='j_username' value=''></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='j_password' /></td>
				</tr>
				<tr>
					<td colspan='2'><input name="login" type="submit" value="Login" /></td>
				</tr>
			</table>
		</form>
	</body>
</html>