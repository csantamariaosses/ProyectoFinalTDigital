<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>UMR</title>
<link href="/css/styles.css" rel="stylesheet" />
<link
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"
	rel="stylesheet" crossorigin="anonymous" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"
	crossorigin="anonymous"></script>
</head>

<body class="sb-nav-fixed">

	<!-- Menú superior -->
	<jsp:include page="../includes/menuSuperior.jsp"></jsp:include>
	<!-- Menú superior -->
	<div id="layoutSidenav">

		<!-- Menú Lateral -->
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
					
					    
						 <jsp:include page="../includes/menuUsuarios.jsp"></jsp:include>
						 
					
						
			</nav>
		</div>
		<!-- Menú superior -->

		<!-- Cuerpo de la aplicación -->
		<div id="layoutSidenav_content">
			<main>

				<div class="modal-body">
				    <h3 class="mt-4"><i class="fa fa-edit"></i> Tratamientos - Modificar</h3>
				    
				    <!--|== Inicio - Mensaje error ======================|-->
						<div class="my-5 mx-5">
							<c:if test="${error}">
								<div class="alert alert-warning alert-dismissible fade show"
									role="alert">
									<strong>¡Error!</strong> ${msg}
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
							</c:if>
						</div>

						<div class="my-5 mx-5">
							<c:if test="${info}">
								<div class="alert alert-success alert-dismissible fade show"
									role="alert">
									<strong>¡Felicidades!</strong> ${msg}
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
							</c:if>
						</div>

					<form action="/tratamientos/tratamientosActualizar" method="post">
						<input type="text" name="idTratamiento" value="${tratamiento.getId() }">
						<input type="text" name="fechaIngreso" value="${tratamiento.getFechaIngreso() }">
						<input type="text" name="codTratamiento" value="${tratamiento.getCodTratamiento() }">
						
						<table>
						   <tr><td>Id</td><td>${tratamiento.getId()}</td></tr>
						   <tr><td>Rut Paciente</td><td>${tratamiento.getPaciente().getRut()}</td></tr>
						   <tr><td>Nombre Paciente</td><td>${tratamiento.getPaciente().getNombre()}</td></tr>
						   <tr><td>Cod Procedimiento</td>
						      <td><select name="codProcedimiento">
										<option value="">Seleccione...</option>
										<c:forEach var="proced" items="${procedimientos}">
											<c:if
												test="${proced.getCodProcedimiento() == tratamiento.getProcedimiento().getCodProcedimiento()}">
												<option value="${proced.getCodProcedimiento()}" selected>${proced.getCodProcedimiento()}
													- ${proced.getNombre()}</option>
											</c:if>
											<c:if
												test="${proced.getCodProcedimiento() != tratamiento.getProcedimiento().getCodProcedimiento()}">
												<option value="${proced.getCodProcedimiento()}">${proced.getCodProcedimiento()}
													- ${proced.getNombre()}</option>
											</c:if>
										</c:forEach>

								</select></td></tr>
                             <tr>
                             <td>Nombre Profesional</td>
                             <td><select name="rutUsuario">
										<option value="">Seleccione...</option>
										<c:forEach var="user" items="${usuarios}">
											<c:if
												test="${user.getRut() == tratamiento.getUsuario().getRut()}">
												<option value="${user.getRut()}" selected>${user.getRut()}
													- ${user.getNombre()}</option>
											</c:if>
											<c:if
												test="${user.getRut() != tratamiento.getUsuario().getRut()}">
												<option value="${user.getRut()}">${user.getRut()}-
													${user.getNombre()}</option>
											</c:if>
										</c:forEach>
								</select></td>
                             </tr>
                             <tr><td>Descripcion</td>
                                 <td><textarea name="descripcion" rows="5" cols="60">${tratamiento.getDescripcion()}</textarea></td>
                             </tr>
						</table>
						<button type = "submit" class = "btn btn-primary">Guardar</button>

					</form>
				</div>
		</main>
			
			<jsp:include page="../includes/footer.jsp"></jsp:include>
		</div>
		<!-- Cuerpo de la aplicación -->
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js"
		crossorigin="anonymous"></script>
	<script src="js/scripts.js"></script>

	<!-- dataTable a espaÃ±ol -->
	<script>
		const cdn = "//cdn.datatables.net/plug-ins/1.10.21/i18n/Spanish.json"
		$(document).ready(function() {
			$('#dataTable').DataTable({
				"language" : {
					"url" : cdn
				}
			});
		});
	</script>
	<!-- dataTable a espaÃ±ol #-->
	<script
		src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js"
		crossorigin="anonymous"></script>
	<script src="assets/demo/datatables-demo.js"></script>
<script>
function traePaciente (id ) {
	window.location.href="/tratamientos/pacienteMuestraDetalle?id=" + id;
}
</script>
</body>

</html>