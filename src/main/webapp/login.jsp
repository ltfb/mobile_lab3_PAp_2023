<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="titulo" value="Login" />
</jsp:include>
<script>
	console.log("vengo de js");
	if ($('#confirmLogin')) { // returns true if element is present
		// show or hide another div
		console.log("escondo");
		$('#loginButton').hide();
	}
</script>
<body>
	<jsp:include page="navBar.jsp" />

	<c:if test="${message!= null}">
		<div class="alert alert-success" style="text-align: center;">
			<p>${message}</p>
		</div>
	</c:if>
	<c:if test="${error!= null}">
		<div class="alert alert-danger" style="text-align: center;">
			<p>${error}</p>
		</div>
	</c:if>
	<!--  CONTENIDO INICIO SESION -->
	<div id="formSection" class="container mt-5 pt-5"
		style="margin-bottom: 100px">
		<div class="row">
			<div class="col-12 col-sm-6 col-md-4 m-auto">
				<div class="card" style="background: #e8e8e8;">
					<div class="card-body">
						<form action="Login" method="post" name="loginForm">
							<h4 class="text-center">Iniciar sesión</h4>
							<div class="mt-4 text-center">
								<label for="user" class="form-label text-center">Usuario</label> <input
									type="text" class="form-control text-center" name="user" id="user">
							</div>
							<div class="mt-3 text-center">
								<label for="password" class="form-label text-center">Contraseña</label> <input
									type="password" class="form-control text-center" name="password"
									id="password">
							</div>
							<div class="mt-4 text-center">
								<input type="submit" id="confirmLogin" name="btnEnvio"
									value="Ingresar" class="btn btn-primary">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp">
		<jsp:param name="marginTop" value="100" />
	</jsp:include>
</body>
</html>