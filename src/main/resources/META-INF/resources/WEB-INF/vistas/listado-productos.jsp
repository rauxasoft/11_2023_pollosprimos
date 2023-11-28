<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="common-in-head.jsp"/>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="header.jsp"/>
		<h2>Listado de Productos</h2>
		<table class="table">
			<thead>
				<tr>
					<th>Código</th>
					<th>Fecha de Alta</th>
					<th style="text-align: right;">Precio</th>
					<th>Nombre</th>
					<th>Categoría</th>
					<th>Descripción</th>
					<th>Descatalogado</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="producto" items="${productos}">
				<tr>
					<td>${producto.codigo}</td>
					<td><fmt:formatDate value="${producto.fechaAlta}" pattern="dd/MM/yyyy"/></td>
					<td style="text-align: right;">${producto.precio} €</td>
					<td>${producto.nombre}</td>
					<td>${producto.categoria.nombre}</td>
					<td>${producto.descripcion}</td>
					
					<td><c:if test="${producto.descatalogado}"><span style="color:red;">DESCATALOGADO</span></c:if></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>