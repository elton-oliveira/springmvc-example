<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h2>New Product</h2>
	<!-- FIXME Remover o contexto da url -->
	<form action="/springmvc-example/products/save.html" method="POST">
	   <!-- Seta o value para manter o estado em caso de dados inválidos. -->
		<input type="text" name="name" value="${product.name}"/> 
		<input type="text" name="price" value="${product.price}" />
		<input type="submit" value="Save" />
	</form>
</body>
</html>
