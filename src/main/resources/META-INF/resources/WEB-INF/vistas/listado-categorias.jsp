<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Pollos Primos</title>
	<link rel="stylesheet" href="css/estilos.css">
</head>
<body>
	<div>
		<jsp:include page="header.jsp"/>
		<h2>Listado de Categorias</h2>
		<table id="table">
			<thead>
				<tr>
					<th>Id</th>
					<th>nombre</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="categoria" items="${categorias}">
				<tr>
					<td>${categoria.id}</td>
					<td>${categoria.nombre}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>