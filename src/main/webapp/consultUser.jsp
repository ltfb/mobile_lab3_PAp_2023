<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="/head.jsp"%>
<%@ include file="/navBar.jsp"%>


<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="titulo" value="Consultar usuario" />
</jsp:include>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="mobileApp.css">
</head>
<body>
	<div class="container align-items-center justify-content-center mt-5"
		style="margin-bottom: 70px width: 100%; height: 100%;">
		<div class="mb-3">
			<table class="table table-striped">
				<thead>
					<tr>
						<th scope="col">#</th>
						<th scope="col">Nickname</th>
						<th scope="col">Correo Electrónico</th>
						<th scope="col">Nombre</th>
						<th scope="col">Apellido</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody>
					<%int i = 1;%>
					<c:forEach items="${users}" var="user">
						<tr>
							<th scope="row"><%=i++%></th>
							<td>${user.nickname}</td>
							<td>${user.email}</td>
							<td>${user.name}</td>
							<td>${user.lastName}</td>
							<td>
								<form action="UserProfile" method="get">
									<input type="hidden" name="userId" value="${user.nickname}">
									<button type="submit" class="btn btn-primary">
										<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16"
											fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
  										<path
												d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6Zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0Zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4Zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10Z">
											</path>
									</svg>
										Ir al Perfil
									</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>