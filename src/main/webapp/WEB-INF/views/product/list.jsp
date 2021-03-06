<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h2>Product List</h2>
	<c:forEach items="${products}" var="product">
		${product.name} - 
		<a href="${pageContext.request.contextPath}/product/detail/${product.id}.html">Detail</a> - 
		<a href="${pageContext.request.contextPath}/product/edit/${product.id}.html">Edit</a> -
		<a href="${pageContext.request.contextPath}/product/delete/${product.id}.html">Delete</a> <br />
	</c:forEach>
</body>
</html>
