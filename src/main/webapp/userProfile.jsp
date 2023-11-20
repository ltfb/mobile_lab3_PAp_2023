<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<%
HttpSession sesion = request.getSession();
%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="titulo" value="Perfil de usuario" />
</jsp:include>

<body>
	<jsp:include page="navBar.jsp" />
	<%String activityName;%>
	<div class="container align-items-center justify-content-center mt-5"
		style="margin-bottom: 70px">
		<div class="row">
			<div class="col-md-4">
				<div class="text-center">
					<%
					String src = (request.getAttribute("userImage") != null && !request.getAttribute("userImage").equals(""))
							? "data:image/jpeg;base64," + request.getAttribute("userImage")
							: "./img/Anonimo.jpeg";
					%>
					<img src="<%=src%>" class="img-circle" alt="Imagen de Perfil"
						style="height: auto; width: 300px; border-radius: 20px" />
				</div>
			</div>
			<div class="col-md-6">
				<h1><%=request.getAttribute("userName") + " " + request.getAttribute("userLastName")%></h1>
				<p>
					Nickname:
					<%=request.getAttribute("userNickname")%></p>
				<p>
					Correo electrónico:
					<%=request.getAttribute("userEmail")%></p>
				<p>
					Fecha de nacimiento:
					<%=request.getAttribute("userBirthday")%></p>
				<p>
					Tipo de usuario:
					<%=request.getAttribute("userType")%></p>
				<%
				if (request.getAttribute("userType").equals("Proveedor")) {
				%>
				<p>
					Descripción:
					<%=request.getAttribute("userDescription")%></p>
				<p>
					Sitio web:
					<%=request.getAttribute("userWebsite")%></p>
				<div class="mb-4">
					<form action="ConsultActivity" method="get"
						name="formToConActFromUsrProf">
						<label for="act-combobox" class="form-label">Actividades:
						</label> <select name="act-combobox" id="act-combobox-usrProf"
							class="form-select mb-3" aria-label=".form-select example"
							required onchange="loadOutings()">
							<option value="" selected>Seleccione una actividad</option>
							<c:forEach items="${activities}" var="activity">
								<option value="${activity.name}">${activity.name}</option>
							</c:forEach>
						</select>
						<%activityName = request.getParameter("act-combobox");%>
						<button type="submit" class="btn btn-primary">Ver mas
							info</button>
					</form>
				</div>
				<div class="mb-4">
					<form action="ConsultOuting" method="post"
						name="formToConOutFromProvProf">
						<label for="outId" class="form-label">Salidas:</label> <select
							name="outId" id="provProfOutCombo" class="form-select mb-3"
							aria-label=".form-select example" required>
							<option value="" selected>Seleccione una salida</option>
						</select> <input type="hidden" name="actId" id="actId" value="">
						<button type="submit" class="btn btn-primary">Ver mas
							info</button>
					</form>
				</div>
				<%
				} else if (request.getAttribute("userType").equals("Turista")) {
				%>
				<div class="mb-4">
					<form action="ConsultOuting" method="post"
						name="formToConOutFromUsrProf">
						<label for="outId" class="form-label">Salidas:</label> <select
							name="outId" id="tourProfOutCombo" class="form-select mb-3"
							aria-label=".form-select example" required>
							<option value="" selected>Seleccione una salida</option>
							<c:forEach items="${touristOutings}" var="outing">
								<option value="${outing.name}">${outing.name}</option>
							</c:forEach>
						</select>
						<button type="submit" class="btn btn-primary">Ver más
							info</button>
					</form>
				</div>
				<%
				if (sesion.getAttribute("uNickname").equals(request.getAttribute("userNickname"))) {
				%>
				<br>
				<h3 class="mt-4">Inscripciones</h3>
				<table class="table table-sm table-striped">
					<thead>
						<tr>
							<th scope="col" class="align-middle text-center">#</th>
							<th scope="col" class="align-middle text-center">Salida</th>
							<th scope="col" class="align-middle text-center">Fecha de
								inscripción</th>
							<th scope="col" class="align-middle text-center">Costo de
								inscripción</th>
							<th scope="col" class="align-middle text-center">Cantidad de
								turistas</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${inscriptions}" var="entry" varStatus="loop">
							<c:set var="inscription" value="${entry.value}" />
							<tr>
								<th scope="row" class="align-middle text-center">${loop.index+1}</th>
								<td class="align-middle text-center">${entry.key}</td>
								<c:set var="dateStr" value="${inscription.inscriptionDate}" />
								<c:set var="formattedDate" value="${fn:substringBefore(dateStr, 'T')}"/>
								<td class="align-middle text-center">${formattedDate}</td>
								<td class="align-middle text-center">${inscription.costo}</td>
								<td class="align-middle text-center">${inscription.touristNumber}</td>
							<tr>
						</c:forEach>
					</tbody>
				</table>
				<%
				}
				}
				%>
			</div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
	<script>
		function loadOutings() {
			var selectedOption = document
					.getElementById("act-combobox-usrProf").value;
			var segundoComboBox = document.getElementById("provProfOutCombo");

			var hiddenInput = document.getElementById("actId");
			// Actualiza el valor del input hidden con el valor seleccionado
			hiddenInput.value = selectedOption;

			segundoComboBox.innerHTML = "<option value='' selected>Seleccione una salida</option>";

			if (selectedOption !== "") {

				// Realiza una petición AJAX al servidor para obtener las opciones del Outings combobox.
				// Asegúrate de ajustar la URL del servlet y los parámetros según tu aplicación.
				var xhr = new XMLHttpRequest();
				xhr.open("GET", "UserProfile?activityId=" + selectedOption,
						true);
				xhr.onreadystatechange = function() {
					if (xhr.readyState == 4 && xhr.status == 200) {
						// Limpia el segundo ComboBox.
						segundoComboBox.innerHTML = "<option value='' selected>Seleccione una salida</option>";

						// Agrega las opciones del segundo ComboBox basadas en la respuesta AJAX.
						var response = JSON.parse(xhr.responseText);
						console.log(response);
						for (var i = 0; i < response.length; i++) {
							var option = document.createElement("option");
							option.textContent = response[i].name;
							segundoComboBox.appendChild(option);
						}
					}
				};
				xhr.send();
			}
		}
	</script>
</body>