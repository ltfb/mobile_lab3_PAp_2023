<%@page import="logic.DataTypeOuting"%>
<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="titulo" value="Consultar inscripción" />
</jsp:include>

<body>
	<jsp:include page="navBar.jsp" />
	<%
	String activityName;
	%>
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

	<div class="container align-items-center justify-content-center mt-5"
		style="margin-bottom: 70px">
		<div class="row align-items-start">
			<div class="col-lg-4 col-12 mb-5">
				<h3 class="mb-3">Actividad</h3>
				<form name="formActivities" action="CreateInscription" method="get"
					enctype="multipart/form-data">
					<input type="hidden" name="procesoGet" value="buscarAct"> <input
						type="hidden" name="nameNotEmpty" id="nameNotEmpty" value="">
					<div class="mb-4">
						<select onchange="guardarNombre()" name="combobox-act-ins" id="combobox-act-ins"
							class="form-select mb-3" aria-label=".form-select example"
							required>
							<option value="" selected>Seleccione una actividad</option>
							<c:forEach items="${activities}" var="activity">
								<option value="${activity.name}"
									${actName == activity.name ? 'selected' : ''}>${activity.name}</option>
							</c:forEach>
						</select>
					</div>
					<%
					activityName = request.getParameter("combobox-act-ins");
					%>
					<button type="submit" class="btn btn-primary">Ver salidas</button>
				</form>
			</div>
			<div class="col-lg-2 mb-5 mb-lg-0"></div>
			<%
			DataTypeOuting[] outings = (DataTypeOuting[]) request.getAttribute("outings");
			%>
			<div class="col-lg-6 col-sm-12">
				<h3 class="mb-3">Salidas asociadas</h3>
				<div class="table-responsive">
					<table class="table">
						<thead>
							<tr>
								<th scope="col" class="text-center">Nombre</th>
								<th scope="col" class="text-center">Fecha</th>
								<th scope="col" class="text-center">Hora</th>
								<th scope="col" class="text-center">Lugar</th>
								<th scope="col" class="text-center">Máx. turistas</th>
								<th scope="col" class="text-center">Cupos disponibles</th>
							</tr>
						</thead>
						<tbody>
							<%
							if (!(outings == null) && outings.length > 0) {
								for (DataTypeOuting o : outings) {
							%>
							<tr>
								<th scope="row" class="align-middle text-center"><%=o.getName()%></th>
								<td class="align-middle text-center"><%=o.getDate()%></td>
								<td class="align-middle text-center"><%=o.getHour()%></td>
								<td class="align-middle text-center"><%=o.getPlace()%></td>
								<td class="align-middle text-center"><%=o.getMaxTourist()%></td>
								<td class="align-middle text-center"><%=String.valueOf(o.getFreePlaces())%></td>
							</tr>
							<%
							}
							}
							%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row align-items-start">
			<div class="col-lg-4 col-12 mb-3">
				<h3 class="mb-3">Registrar inscripción</h3>
				<form name="prueba" action="CreateInscription" method="get"
					enctype="multipart/form-data">
					<div class="mb-4">
						<select onchange="guardarNombre()" name="combobox-out-ins" id="combobox-out-ins"
							class="form-select mb-3" aria-label=".form-select example"
							required>
							<option value="" selected>Seleccione una salida</option>
							<c:forEach items="${outings}" var="outing">
								<option value="${outing.name}">${outing.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="mb-3">
						<label for="insNumber" class="form-label">Cantidad de
							inscripciones</label> <input type="number" class="form-control"
							id="insNumber" name="insNumber">
					</div>
					<div class="d-flex align-items-center mt-4">
						<input type="hidden" name="procesoGet" value="registrarIns">
						<input type="hidden" name="activityName" id="actName"
							value="">
						<button type="submit" class="btn btn-primary">Registrar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		function guardarNombre() {
			var selectedOpc = document.getElementById('combobox-act-ins').value;
			var hiddenInput = document.getElementById('actName');
			var hiddenInput2 = document.getElementById('nameNotEmpty');

			hiddenInput.value = selectedOpc;
			hiddenInput2.value = selectedOpc;

			console.log(selectedOpc)
		}
	</script>
	<jsp:include page="footer.jsp" />
</body>

</html>