<%@page import="logic.DataTypeOuting"%>
<%@page import="java.util.Base64"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="titulo" value="Consultar salida" />
</jsp:include>

<body>
	<jsp:include page="navBar.jsp" />

	<%String activityName;%>
	<div class="container align-items-center justify-content-center mt-5"
		style="margin-bottom: 70px">
		<div class="row align-items-start">
			<div class="col-lg-4 col-12 mb-5">
				<h3 class="mb-3">Actividad</h3>
				<form action="ConsultOuting" method="get" name=formConsultOuting>
					<div class="mb-4">
						<label for="activity" class="form-label"></label> <select
							name="activity" id="combobox-act-conOut" class="form-select mb-3"
							aria-label=".form-select example" required>
							<option value="" selected>Seleccione una actividad</option>
							<c:forEach items="${activities}" var="activity">
								<option value="${activity.name}"
									${actName == activity.name ? 'selected' : ''}>${activity.name}</option>
							</c:forEach>
						</select>
						<%activityName = request.getParameter("activity");%>
					</div>
					<button type="submit" class="btn btn-primary">Ver salidas</button>
				</form>
			</div>
		</div>
		<%
		DataTypeOuting[] outings = (DataTypeOuting[]) request.getAttribute("outings");
		%>
		<div class="row align-items-start">
			<div class="col-lg-8 col-sm-12">
				<h3 class="mb-3">Salidas asociadas</h3>
				<div class="table-responsive">
					<table class="table table-sm table-striped">
						<thead>
							<tr>
								<th scope="col" class="text-center">Nombre</th>
								<th scope="col" class="text-center"></th>
							</tr>
						</thead>
						<tbody>
							<%
							if (!(outings == null) && outings.length > 0) {
								for (DataTypeOuting o : outings) {
							%>
							<tr>
								<th scope="row" class="align-middle text-center"><%=o.getName()%></th>
								<td>
									<form action="ConsultOuting" method="post">
										<input type="hidden" name="actId" value="<%=activityName%>">
										<input type="hidden" name="outId" value="<%=o.getName()%>">
										<button type="submit" class="btn btn-primary">Ver mas
											info</button>
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