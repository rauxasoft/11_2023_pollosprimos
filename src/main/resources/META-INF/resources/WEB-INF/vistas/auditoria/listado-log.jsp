<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="../common-in-head.jsp"/>
</head>
<body>
	<div class="container-fluid">
		<jsp:include page="../header.jsp"/>
		<h2>Listado de Logs</h2>
		<table class="table">	
			<thead>
				<tr>
					<th>Id</th>
					<th>Method</th>
					<th>Resource</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="log" items="${logs}">
				<tr>
					<td>${log.id}</td>
					<td>${log.verbo}</td>
					<td>${log.recurso}</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>