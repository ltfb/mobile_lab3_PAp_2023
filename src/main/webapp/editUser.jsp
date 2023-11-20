<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
HttpSession sesion = request.getSession();
%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="titulo" value="Mis datos" />
</jsp:include>

<script>
	function validarContraseña() {
		var password = document.getElementById("inputEditPassword").value;
		var confirm_password = document.getElementById("inputConfirmPassword").value;
		var error_message = document.getElementById("errorMessage");

		if (password !== confirm_password) {
			error_message.innerHTML = "Las contraseñas no coinciden";
			return false;
		} else {
			error_message.innerHTML = "";
			return true;
		}
	}
</script>
<body>
	<jsp:include page="navBar.jsp" />
	<div class="container mt-4" style="margin-bottom: 70px">
		<div class="row">
			<div class="col-10 col-sm-7 col-md-5 m-auto">
				<h3 class="mb-5 text-center">Mis datos</h3>
				<form action="EditUser" method="post" enctype="multipart/form-data"
					onsubmit="return validarContraseña();">
					<div class="mb-3">
						<label for="inputEditName" class="form-label">Nombre *</label> <input
							type="text" class="form-control" id="inputEditName"
							name="inputEditName"
							value="<%=request.getAttribute("userName")%>" required>
					</div>
					<div class="mb-3">
						<label for="inputEditLastName" class="form-label">Apellido
							*</label> <input type="text" class="form-control" id="inputEditLastName"
							value="<%=request.getAttribute("userLastName")%>"
							name="inputEditLastName" required>
					</div>
					<div class="mb-3">
						<label for="inputEditNickname" class="form-label">Nickname
							*</label> <input type="text" class="form-control" id="inputEditNickname"
							value="<%=request.getAttribute("userNickname")%>"
							name="inputEditNickname" disabled>
					</div>
					<div class="mb-3">
						<label for="inputEditEmail" class="form-label">Email *</label> <input
							type="email" class="form-control" id="inputEditEmail"
							name="inputEditEmail"
							value="<%=request.getAttribute("userEmail")%>" disabled>
					</div>
					<div class="mb-3">
						<label for="inputEditBirthday" class="form-label">Fecha de
							nacimiento *</label> <input type="date" class="form-control"
							id="inputEditBirthday" name="inputEditBirthday"
							value="<%=request.getAttribute("userBirthday")%>" required>
					</div>
					<div class="mb-3">
						<label for="inputEditPassword" class="form-label">Contraseña
							*</label> <input type="password" class="form-control"
							id="inputEditPassword" name="inputEditPassword"
							value="<%=request.getAttribute("userPassword")%>" required>
					</div>
					<div class="mb-3">
						<label for="inputEditConfirmPassword" class="form-label">Confirmar
							contraseña *</label> <input type="password" class="form-control"
							id="inputEditConfirmPassword" name="inputEditConfirmPassword"
							value="<%=request.getAttribute("userPassword")%>" required>
						<span id="errorMessage" style="color: red;"></span>
					</div>
					<div class="mb-4">
						<label for="fileEditImg" class="form-label">Imagen</label> <input
							type="file" class="form-control" id="fileEditImg"
							name="fileEditImg"<%-- src="<%=sesion.getAttribute("uImage")%>" --%>>
						<!-- TODO ver cómo meter una imagen -->
					</div>
					<%
					if (sesion.getAttribute("uUserType").equals("provider")) {
					%>
					<div class="mb-3">
						<label for="inputEditDescription" class="form-label">
							Descripción </label> <input type="text" class="form-control"
							id="inputEditDescription" name="inputEditDescription"
							value="<%=request.getAttribute("userDescription")%>" required>
					</div>
					<div class="mb-3">
						<label for="inputEditWebsite" class="form-label"> Website
						</label> <input type="url" class="form-control" id="inputEditWebsite"
							name="inputEditWebsite"
							value="<%=request.getAttribute("userWebsite")%>">
					</div>
					<%
					} else if (sesion.getAttribute("uUserType").equals("tourist")) {
					%>
					<div class="mb-3">
						<label for="inputEditNationality" class="form-label">
							Nacionalidad </label> <input type="text" class="form-control"
							id="inputEditNationality" name="inputEditNationality"
							value="<%=request.getAttribute("userNationality")%>" required>
					</div>
					<%
					}
					%>

					<div class="d-flex align-items-center justify-content-center mt-2">
						<button type="submit" class="btn btn-primary">Guardar
							datos</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>