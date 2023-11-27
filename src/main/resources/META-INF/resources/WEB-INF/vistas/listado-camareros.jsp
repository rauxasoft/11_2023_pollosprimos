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
		<h2>Listado de Camareros</h2>
		<table id="table">
			<thead>
				<tr>
					<th>id</th>
					<th>DNI</th>
					<th>Nombre Completo</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="camarero" items="${camareros}">
				<tr>
					<td><a href="camareros?id=${camarero.id}">${camarero.id}</a></td>
					<td>${camarero.dni}</td>
					<td>${camarero.apellido1} ${camarero.apellido2}, ${camarero.nombre}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>