<%@ page import="publish.DataTypeOuting"%>
<%@ page import="publish.DataTypeOutingArray"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="titulo" value="Consultar actividad" />
</jsp:include>

<body>
	<jsp:include page="navBar.jsp" />

	<%
	String activityName;
	%>
	<div class="container align-items-center justify-content-center mt-5"
		style="margin-bottom: 70px">
		<div class="row align-items-start">
			<div class="col-lg-4 col-12">
				<h3 class="mb-3">Actividad</h3>
				<form action="ConsultActivity" method="get"
					enctype="multipart/form-data" name="conActGet">


					<div class="mb-4">
						<label for="act-combobox" class="form-label">Actividad</label> <select
							name="act-combobox" id="act-combobox-consultAct"
							class="form-select mb-3" aria-label="form-select example"
							required>
							<option value="">Seleccione una actividad</option>
							<c:forEach items="${activities}" var="activity">
								<option value="${activity.name}"
									${consultActName == activity.name ? 'selected' : ''}>${activity.name}</option>
							</c:forEach>
						</select>
						<%
						activityName = request.getParameter("act-combobox");
						%>
					</div>

					<div class="mb-3">
						<label for="inputDescAct" class="form-label">Descripción</label> <input
							type="text" class="form-control" value="${consultActDesc}"
							id="inputDescAct" disabled>
					</div>
					<div class="mb-3">
						<label for="inputDurAct" class="form-label">Duración (hs)</label>
						<input type="number" class="form-control"
							value="${consultActDuration}" id="inputDurAct" disabled>
					</div>
					<div class="mb-3">
						<label for="inputCostAct" class="form-label">Costo ($U)</label> <input
							type="number" class="form-control" value="${consultActCost}"
							id="inputCostAct" disabled>
					</div>
					<div class="mb-3">
						<label for="inputDateAct" class="form-label">Fecha de
							registro</label> <input type="text" class="form-control"
							value="${consultActDate}" id="inputDateAct" disabled>
					</div>
					<div class="mb-3">
						<label for="inputStateAct" class="form-label">Estado</label> <input
							type="text" class="form-control" value="${consultActState}"
							id="inputStateAct" disabled>
					</div>
					<div class="mb-3">
						<%
						String src = (request.getAttribute("consultActImage") != null && !request.getAttribute("consultActImage").equals(""))
								? "data:image/jpeg;base64," + request.getAttribute("consultActImage")
								: "./img/noImageActivity.png";
						%>
						<img src="<%=src%>"
							style="height: 120px; width: auto; border-radius: 5px">
					</div>
					<button type="submit" class="btn btn-primary">Consultar</button>
				</form>
			</div>
			<div class="col-lg-2 mb-5 mb-lg-0"></div>
			<div class="col-lg-6 col-sm-12">
				<%
				List<DataTypeOuting> outings = (List<DataTypeOuting>) request.getAttribute("outings");
				%>
				<h3 class="mb-3">Salidas asociadas</h3>
				<div class="table-responsive">
					<table class="table table-sm table-striped">
						<thead>
							<tr>
								<th scope="col" class="text-center">Nombre</th>
								<th scope="col"></th>
							</tr>
						</thead>
						<tbody>
							<%
							if (!(outings == null) && outings.size() > 0) {
								for (DataTypeOuting o : outings) {
							%>
							<tr>
								<th scope="row" class="align-middle text-center"><%=o.getName()%></th>
								<td>
									<form action="ConsultOuting" method="post"
										name="formToConOutFromConAct">
										<input type="hidden" name="actId" value="<%=activityName%>">
										<input type="hidden" name="outId" value="<%=o.getName()%>">
										<button type="submit" class="btn btn-secondary">Ver
											más info</button>
									</form>
								</td>
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
	</div>

	<jsp:include page="footer.jsp" />
</body>

</html>