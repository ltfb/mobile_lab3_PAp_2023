<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%HttpSession sesion = request.getSession();%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="titulo" value="Inicio" />
</jsp:include>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" type="text/css" href="mobileApp.css">
</head>
<body>

	<jsp:include page="navBar.jsp" />

	<div id="carousel" class="carousel carousel-dark slide"
		data-bs-ride="carousel">

		<%-- <img src="data:image/jpeg;base64, <%= sesion.getAttribute("uImage") %>" alt="Imagen desde la Base de Datos"> --%>

		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="./img/torreEiffel.jpeg" class="d-block w-95 h-auto"
					alt="Torre Eiffel">
			</div>
			<div class="carousel-item">
				<img src="./img/coliseo.jpeg" class="d-block w-95 h-auto"
					alt="Coliseo">
			</div>
			<div class="carousel-item">
				<img src="./img/piramides.jpeg" class="d-block w-95"
					alt="Piramides">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carousel" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carousel" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>

	<jsp:include page="footer.jsp"/>
</body>

</html>