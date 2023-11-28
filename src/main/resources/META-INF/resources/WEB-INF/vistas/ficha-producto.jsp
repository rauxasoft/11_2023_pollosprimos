<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Pollos Primos</title>
	<jsp:include page="common-in-head.jsp"/>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="header.jsp"/>
		<c:if test="${modo eq 'alta'}"><h2>Alta de Producto</h2></c:if>
		<c:if test="${modo eq 'editar'}"><h2>Edición de Producto</h2></c:if>
		<form:form action="procesar-formulario-producto" method="POST" modelAttribute="producto">
			<form:hidden path="codigo"/>
			<table>
				<tr>
					<th>Nombre</th>
					<td><form:input path="nombre"/></td>
				</tr>
				<tr>
					<th>Precio</th>
					<td><form:input type="number" path="precio"/></td>
				</tr>
				<tr>
					<th>Fecha de Alta</th>
					<td><form:input type="date" path="fechaAlta"/></td>
				</tr>
				<tr>
					<th>Descripción</th>
					<td><form:textarea rows="4" cols="100" path="descripcion"/></td>
				</tr>
				<tr>
					<th>Categoría</th>
					<td><form:select path="categoria.id">
							<form:options items="${categorias}" itemValue="id" itemLabel="nombre"></form:options> 
						</form:select>
					</td>
				</tr>
				<tr>
					<th>Descatalogado</th>
					<td><form:checkbox path="descatalogado"/></td>
				</tr>
				<tr>
					<th></th>
					<td style="text-align:right;">
						<form:button>
							<c:if test="${modo eq 'alta'}">CREAR</c:if>
							<c:if test="${modo eq 'editar'}">EDITAR</c:if>
						</form:button>
					</td>
				</tr>
			</table>
		</form:form>
	</div>
</body>
</html>