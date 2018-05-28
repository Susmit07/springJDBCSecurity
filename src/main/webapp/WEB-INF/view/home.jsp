<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Provides core validation features for JSTL tags. -->
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!-- To provide spring security -->

<html>
<head>
</head>
<body>
	<p>
	<h2>Welcome Susmit!</h2>
	<!-- Display user name -->
	<hr>
	<p>
		User ID:
		<security:authentication property="principal.username" />
		<br> <br> Roles:
		<security:authentication property="principal.authorities" />
	</p>

	<hr>
	<!-- Add a link to point to /leaders as shown in the diagram requirements. This is for managers.-->
	<!--  Showing content based on roles-->
	<security:authorize access="hasRole('MANAGER')">
		<p>
			<a href="${pageContext.request.contextPath}/leaders">Leadership
				Teams</a> (Only Manager and Leaders are allowed)
		</p>
	</security:authorize>
	<!-- Add a link to point to /systems as shown in the diagram requirements. This is for Admins.-->
	<security:authorize access="hasRole('ADMIN')">
	<p>
		<a href="${pageContext.request.contextPath}/systems">Admin and
			Systems Teams</a> (Only Admins are allowed)
	</p>
	</security:authorize>

	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout">
	</form:form>
</body>
</html>