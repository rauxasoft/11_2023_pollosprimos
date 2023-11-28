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
		<h2>Pedido</h2>
		<p style="color:red;">${mensajeError}</p>
		<c:if test="${mensajeError == null}">
			<table class="table">
				<tr>
					<th>Número</th>
					<td>${pedido.numero}</td>
				</tr>
				<tr>
					<th>Fecha</th>
					<td><fmt:formatDate value="${pedido.fecha}" pattern="dd/MM/yyyy"/></td>
				</tr>
				<tr>
					<th>Hora</th>
					<td><fmt:formatDate value="${pedido.fecha}" pattern="HH:mm"/></td>
				</tr>
				<tr>
					<th>Estado</th>
					<td>${pedido.estado}</td>
				</tr>
			</table>
		</c:if>
	</div>
</body>
</html>