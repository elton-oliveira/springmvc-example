<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h2>Editing Product</h2>
	<!-- The spring form is used to display messages validator bean -->
	<form:form action="${pageContext.request.contextPath}/product/update.html" method="POST" commandName="product">
	    <form:hidden path="id"/>
		Name: <form:input path="name"/><form:errors path="name" /><br />
		Price: <form:input path="price" /><form:errors path="price" /><br />
		<input type="submit" value="Save" />
	</form:form>
</body>
</html>
