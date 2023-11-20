<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="titulo" value="Registrar usuario" />
</jsp:include>

<script>
	function validarContraseña() {
		var password = document.getElementById("inputPassword").value;
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
<script>
	function showElements() {
		// Obtén el valor seleccionado del combo box
		var selected = document.getElementById("combobox-user-type").value;
		// Obtén el contenedor donde se mostrarán los elementos dinámicos
		var container = document.getElementById("elementsContainer");
		// Limpia el contenido anterior del contenedor
		container.innerHTML = "";
		if (selected === "turista") {
			// Crea una etiqueta y una caja de texto
			var tag = document.createElement("label");
			tag.innerHTML = "Nacionalidad *";
			tag.classList.add("form-label");
			container.appendChild(tag);

			var nationalityBox = document.createElement("input");
			nationalityBox.type = "text";
			nationalityBox.name = "nationality"; // Nombre del campo de entrada, esto mando a servlet
			nationalityBox.classList.add("form-control");
			nationalityBox.setAttribute("required", "required");
			container.appendChild(nationalityBox);

		} else if (selected === "proveedor") {
			var descriptionTag = document.createElement("label");
			descriptionTag.innerHTML = "Descripcion *";
			descriptionTag.classList.add("form-label");
			container.appendChild(descriptionTag);

			var descriptionBox = document.createElement("input");
			descriptionBox.type = "text";
			descriptionBox.name = "description"; // Nombre del campo de entrada, esto mando a servlet
			descriptionBox.classList.add("form-control");
			descriptionBox.setAttribute("required", "required");
			container.appendChild(descriptionBox);

			var websiteTag = document.createElement("label");
			websiteTag.innerHTML = "Website";
			websiteTag.classList.add("form-label");
			container.appendChild(websiteTag);

			var websiteBox = document.createElement("input");
			websiteBox.type = "url";
			websiteBox.name = "website"; // Nombre del campo de entrada, esto mando a servlet
			websiteBox.classList.add("form-control");
			container.appendChild(websiteBox);

		}

	}
</script>

<body>
	<jsp:include page="navBar.jsp" />
	<div class="container mt-4" style="margin-bottom: 70px">
		<div class="row">
			<div class="col-10 col-sm-7 col-md-5 m-auto">
				<h3 class="mb-5 text-center">Registrar usuario</h3>
				<form action="CreateUser" method="post"
					enctype="multipart/form-data"
					onsubmit="return validarContraseña();">
					<div class="mb-3">
						<label for="inputUserName" class="form-label">Nombre *</label> <input
					type="text" class="form-control" id="inputUserName"
					name="inputUserName" required>
					</div>
					<div class="mb-3">
						<label for="inputUserLastName" class="form-label">Apellido *</label>
				<input type="text" class="form-control" id="inputUserLastName"
					name="inputUserLastName" required>
					</div>
					<div class="mb-3">
						<label for="inputUserNickname" class="form-label">Nickname *</label>
				<input type="text" class="form-control" id="inputUserNickname"
					name="inputUserNickname" required>
					</div>
					<div class="mb-3">
						<label for="inputUserEmail" class="form-label">Email *</label> <input
					type="email" class="form-control" id="inputUserEmail"
					name="inputUserEmail" required>
					</div>
					<div class="mb-3">
						<label for="inputUserBirthday" class="form-label">Fecha de
							nacimiento *</label> <input type="date" class="form-control"
							id="inputUserBirthday" name="inputUserBirthday" required>
					</div>
					<div class="mb-3">
						<label for="inputUserPassword" class="form-label">Contraseña
							*</label> <input type="password" class="form-control"
							id="inputUserPassword" name="inputUserPassword" required>
					</div>
					<div class="mb-3">
						<label for="inputUserConfirmPassword" class="form-label">Confirmar
							contraseña *</label> <input type="password" class="form-control"
							id="inputUserConfirmPassword" name="inputUserConfirmPassword"
							required> <span id="errorMessage" style="color: red;"></span>
					</div>
					<div class="mb-4">
						<label for="fileUserImg" class="form-label">Imagen</label> <input
					type="file" class="form-control" id="fileUserImg"
					name="fileUserImg" accept="image/*">
					</div>
					<div class="mb-4">
						<label for="combobox-user-type" class="form-label">Seleccione
							tipo de usuario *</label> <select name="combobox-user-type"
							id="combobox-user-type" class="form-select mb-3"
							aria-label=".form-select example" onchange="showElements()"
							required>
							<option value="" selected>&lt;Tipo de usuario&gt;</option>
							<option value="turista">Turista</option>
							<option value="proveedor">Proveedor</option>
						</select>
					</div>
					<!-- Contenedor para los elementos dinámicos -->
					<div class="mb-3" id="elementsContainer">
						<!-- Aquí se mostrarán los elementos dinámicos -->
					</div>

					<c:if test="${error!= null}">
						<br>
						<div class="alert alert-danger" style="text-align: center;">
							<p>${error}</p>
						</div>
						<br>
					</c:if>
					<c:if test="${message!= null}">
						<br>
						<div class="alert alert-success" style="text-align: center;">
							<p>${message}</p>
						</div>
						<br>
					</c:if>
					<div class="d-flex align-items-center justify-content-center mt-2">
						<button type="submit" class="btn btn-primary">Registrar</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>