<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
	<jsp:param name="titulo" value="Registrar salida" />
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
				<h3 class="mb-5 text-center">Registrar salida</h3>
				<form action="CreateOuting" method="post"
					enctype="multipart/form-data">
					<div class="mb-4">
						<label for="combobox-act-type" class="form-label">Actividad
							*</label> <select name="combobox-act-type" id="combobox-act-type"
							class="form-select mb-3" aria-label=".form-select example"
							required>
							<option value="" selected>Seleccione una actividad</option>
							<c:forEach items="${activities}" var="activity">
								<option value="${activity.name}">${activity.name}</option>
							</c:forEach>
						</select>
					</div>

					<div class="mb-3">
						<label for="inputOutName" class="form-label">Nombre *</label> <input
							type="text" class="form-control" id="inputOutName"
							name="inputOutName" required>
					</div>
					<div class="mb-3">
						<label for="inputOutDate" class="form-label">Fecha y hora
							*</label> <input type="datetime-local" class="form-control"
							id="inputOutDate" name="inputOutDate" required>
					</div>
					<div class="mb-3">
						<label for="inputOutPlace" class="form-label">Lugar de
							salida *</label> <input type="text" class="form-control"
							id="inputOutPlace" name="inputOutPlace" required>
					</div>
					<div class="mb-3">
						<label for="inputOutMaxTourist" class="form-label">Cantidad
							máxima de turistas *</label> <input type="number" class="form-control"
							id="inputOutMaxTourist" name="inputOutMaxTourist" required>
					</div>
					<div class="mb-4">
						<label for="fileOutImg" class="form-label">Imagen</label> <input
							type="file" class="form-control" id="fileOutImg"
							name="fileOutImg" accept="image/*">
					</div>

					<c:if test="${message!= null}">
						<br>
						<div class="alert alert-danger" style="text-align: center;">
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