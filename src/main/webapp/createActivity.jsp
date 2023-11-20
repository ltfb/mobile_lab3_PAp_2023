<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="titulo" value="Registrar actividad" />
</jsp:include>
<body>

	<jsp:include page="navBar.jsp" />

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
	<div class="container mt-4" style="margin-bottom: 70px">
		<div class="row">
			<div class="col-10 col-sm-7 col-md-5 m-auto">
				<h3 class="mb-5 text-center">Registrar actividad</h3>
				<form action="CreateActivity" method="post"
					enctype="multipart/form-data">
					<div class="mb-3">
						<label for="actName" class="form-label">Nombre</label> <input
							type="text" class="form-control" id="actName" name="actName">
					</div>
					<div class="mb-3">
						<label for="actDesc" class="form-label">Descripción</label> <input
							type="text" class="form-control" id="actDesc" name="actDesc">
					</div>
					<div class="mb-3">
						<label for="actDur" class="form-label">Duración (hs)</label> <input
							type="number" class="form-control" id="actDur" name="actDur">
					</div>
					<div class="mb-3">
						<label for="actCost" class="form-label">Costo ($U)</label> <input
							type="number" class="form-control" id="actCost" name="actCost">
					</div>
					<div class="mb-3">
						<label for="actCity" class="form-label">Ciudad</label> <input
							type="text" class="form-control" id="actCity" name="actCity">
					</div>
					<div class="mb-3">
						<label for="actImg" class="form-label">Imagen</label> <input
							type="file" class="form-control" id="actImg" name="actImg">
					</div>

					<div class="d-flex align-items-center justify-content-center mt-4">
						<button type="submit" class="btn btn-primary">Registrar</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>

</html>