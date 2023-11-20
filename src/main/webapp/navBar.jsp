<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
HttpSession sesion = request.getSession();
%>
<jsp:include page="bootstrapScripts.jsp" />
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
<nav class="navbar navbar-expand-lg bg-body-tertiary sticky-top">
	<div class="container-fluid">
		<a class="navbar-brand ms-4 me-5" href="index.jsp">Agencia</a>
		<button class="navbar-toggler mb-3 mb-md-0 me-3 mt-2" type="button"
			data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
			aria-controls="navbarNavDropdown" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="fas fa-bars"></span>
		</button>
		<div class="collapse navbar-collapse text-center"
			id="navbarNavDropdown">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Inicio
						<span class="sr-only"></span>
				</a></li>
				<%
				if (sesion.getAttribute("connected") != null) {
				%>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" role="button"
					data-bs-toggle="dropdown" href="#" aria-expanded="false">Registrar</a>
					<ul class="dropdown-menu text-center">
						<%
						if (sesion.getAttribute("uUserType") != null
								&& sesion.getAttribute("uUserType").toString().toLowerCase().equals("provider")) {
						%>
						<li><a class="dropdown-item" href="createActivity.jsp">Registrar
								actividad</a></li>
						<li>
							<form id="loadActivitiesForm" action=CreateOuting method="get">
								<a id="createOutingLink" class="dropdown-item"
									onclick="document.getElementById('loadActivitiesForm').submit()"
									href="#">Registrar salida</a>
							</form>
						</li>

						<%
						}
						if (sesion.getAttribute("uUserType") != null
								&& sesion.getAttribute("uUserType").toString().toLowerCase().equals("tourist")) {
						%>
						<li>
							<form id="loadActivitiesIns" action=CreateInscription method="get">
								<a id="createOutingLink" class="dropdown-item"
									onclick="document.getElementById('loadActivitiesIns').submit()"
									href="#">Registrar inscripción</a>
							</form>
						</li>
						<%
						}
						%>
					</ul></li>
				<%
				}
				%>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" role="button"
					data-bs-toggle="dropdown" href="#" aria-expanded="false">Consultar</a>
					<ul class="dropdown-menu text-center">
						<li>
							<form id="consultUserForm" action=ConsultUser method="get">
								<a id="consultUserLink" class="dropdown-item"
									onclick="document.getElementById('consultUserForm').submit()"
									href="#">Consultar usuario</a>
							</form>
						</li>
						<li>
							<form id="loadConActivitiesForm" action=ConsultActivity
								method="get">
								<a id="consultActivityLink" class="dropdown-item"
									onclick="document.getElementById('loadConActivitiesForm').submit()"
									href="#">Consultar actividad</a>
							</form>
						</li>
						<li>
							<form id="loadConOutingsForm" action=ConsultOuting method="get">
								<a id="consultOutingLink" class="dropdown-item"
									onclick="document.getElementById('loadConOutingsForm').submit()"
									href="#">Consultar salida</a>
							</form>
						</li>
					</ul></li>
			</ul>
			<%
			if (sesion.getAttribute("connected") != null) {
			%>
			<ul class="nav navbar-nav ms-auto">
				<li class="nav-item dropdown">
					<%
					String src = (sesion.getAttribute("uImage") != null && !sesion.getAttribute("uImage").equals(""))
							? "data:image/jpeg;base64," + sesion.getAttribute("uImage")
							: "./img/Anonimo.jpeg";
					%> <a class="nav-link dropdown-toggle" role="button"
					data-bs-toggle="dropdown" href="#" aria-expanded="false"> <img
						src="<%=src%>"
						alt="Avatar"
						style="height: 40px; width: 40px; border-radius: 50%">

				</a>
					<div class="dropstart">
						<ul class="dropdown-menu text-center">
							<li>
								<form id="editUserForm" action=EditUser method="get">
									<%
									request.setAttribute("nickname", (String) sesion.getAttribute("uNickname"));
									%>
									<a id="editUserLink" class="dropdown-item"
										onclick="document.getElementById('editUserForm').submit()"
										href="#">Mis datos</a>
								</form>
							</li>
							<li>
								<hr>
							</li>
							<li>
								<form action=Logout method="GET">
									<input type="hidden" name="close" value="close"
										class="btn btn-primary"> <input type="submit"
										value="Cerrar sesión" class="btn btn-secondary">
								</form>
							</li>
						</ul>
					</div>
				</li>
			</ul>
			<%
			} else {
			%>
			<ul class="nav navbar-nav ms-auto text-center">
				<li class="nav-item"><a class="text-decoration-none text-body-secondary"
					href="./login.jsp">Iniciar sesión</a></li>
				<li class="ms-lg-3 me-lg-2 mt-sm-1 mt-lg-0 nav-item"><a
					class="text-decoration-none text-body-s
			econdary"
					href="./createUser.jsp">Registrarse</a></li>
			</ul>
			<%
			}
			%>
		</div>
	</div>
</nav>
<script>
	function logout() {
		var formParametr = new FormData();
		formParametr.append("logout", "close");
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				// Typical action to be performed when the document is ready:
				console.log(xhttp.responseText);

			}
		};
		xhttp.open("POST", "Usuario", true);
		xhttp.send(formParametr);
		return false;
	}
</script>
<script>
	function getEditUser() {
		document.getElementById("editUserLink").onclick = function() {
			document.getElementById("editUserForm").submit();
		}
	}
</script>