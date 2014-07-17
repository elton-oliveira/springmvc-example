<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h2>Editing Product</h2>
	<!-- FIXME Remover o contexto da url -->
	<!-- O Elemento form do spring é utilizado para poder exibir as mensagens do bean validator em tela -->
	<form:form action="/springmvc-example/products/update.html" method="POST" commandName="product">
	    <form:hidden path="id"/>
		<form:input path="name"/><form:errors path="name" /><br />
		<form:input path="price" /><form:errors path="price" /><br />
		<input type="submit" value="Save" />
	</form:form>
</body>
</html>
