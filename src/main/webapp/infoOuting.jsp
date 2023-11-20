<%@page import="logic.DataTypeOuting"%>
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
	DataTypeOuting dto = (DataTypeOuting) request.getAttribute("outing");
	%>
	<div class="container align-items-center justify-content-center mt-5"
		style="margin-bottom: 70px">
		<div class="row">
			<div class="col-md-4">
				<%
				String src = (request.getAttribute("outInfoImage") != null && !request.getAttribute("outInfoImage").equals(""))
						? "data:image/jpeg;base64," + request.getAttribute("outInfoImage")
						: "./img/noImageActivity.png";
				%>
				<img src="<%=src%>" class="img-fluid" alt="Imagen Salida" />
				<!-- style="height: 240px; width: 200px; border-radius: 50%" -->
			</div>
			<div class="col-md-8">
				<h1>${outing.name}</h1>
				<p>Fecha: ${outing.date}</p>
				<p>Hora: ${outing.hour}</p>
				<p>Lugar: ${outing.place}</p>
				<p>Cantidad Máxima de turistas: ${outing.maxTourist}</p>
				<p>
					Fecha de alta:
					<%=dto.getEntryDate().toString().split("T")[0]%></p>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>

</html>