<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<h2>Product Details</h2>
	Id: ${product.id} <br /> 
	Name: ${product.name} <br />
	Price: ${product.price} <br />
	Last Modified: <fmt:formatDate value="${product.lastModified}" pattern="dd/MM/yyyy HH:mm:ss"/>
</body>
</html>
