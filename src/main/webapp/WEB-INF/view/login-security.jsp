<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- The form tag library comes bundled in spring-webmvc.jar. The library descriptor is called spring-form.tld -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Provides core validation features for JSTL tags. -->
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Custom Login Page</title>
</head>
<body>
	<h2>Login Page</h2>
	<!-- Check for login error -->

	<c:if test="${param.error != null}">
		<i>Sorry you have entered an invalid password</i>
	</c:if>

	<!-- Check for logout -->
	<c:if test="${param.logout != null}">
		<i>You have logged out successfully</i>
	</c:if>

	<form:form
		action="${pageContext.request.contextPath}/authenticateTheUser"
		method="POST">
		<p>
			User Name: <input type="text" name="username" />
		</p>
		<p>
			Password: <input type="password" name="password" />
		</p>
		<!-- By default Spring security filter uses user-name and password for authentication in authenticateTheUser  -->
		<input type="submit" value="Login">
	</form:form>
</body>
</html>