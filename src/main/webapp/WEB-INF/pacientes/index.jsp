<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cl.csantam.model.entity.Sexo" %>
<%@ page import="cl.csantam.model.entity.Prevision" %>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Home</title>
<link href="/css/styles.css" rel="stylesheet" />
<link
	href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css"
	rel="stylesheet" crossorigin="anonymous" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"
	crossorigin="anonymous"></script>
	<style>
	.dia, .mes, .anio {
	width:80px;
	}
	.verde {
	   background-color:#00ff00;
	}
	</style>,
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
					 				
						
					</div>
				</div>
				<div class="sb-sidenav-footer">
					<div class="small">Reconocido como:</div>
					<c:out value="${username}"></c:out>
				</div>
			</nav>
		</div>
		<!-- Menú superior -->

		<!-- Cuerpo de la aplicación -->
		<div id="layoutSidenav_content">
			<main>
			   
			   <div class="container-fluid">
				<h3 class="mt-4"><i class="fa fa-user"></i>  Pacientes</h3>
				<br>
				 
					 <div class="card mb-4">
						<div class="card-header">
							<i class="far fa-address-card mr-1"></i> Datos Paciente
						</div>
						<div class="card-body datosUsuario">
							<div class="table-responsive">
								<!-- Formulario Paciente -->
								<form action="/pacientes" method="post">
								<div class="form-group">
								    <label for="rut">Rut</label>
								    <input type="text" class="form-control" name="rut" id="rut" aria-describedby="emailHelp" placeholder="Rut" required>
							
								  </div>
								  <div class="form-group">
								    <label for="nombre">Nombre</label>
								    <input type="text" class="form-control"  name="nombre"  id="nombre" aria-describedby="emailHelp" placeholder="Nombre" required>
								  </div>
								  <div class="form-group">
								    <label for="direccion">Direccion</label>
								    <input type="text" class="form-control"  name="direccion"  id="direccion" aria-describedby="emailHelp" placeholder="Direccion" required>
								  </div>
								  <div class="form-group">
								    <label for="direccion">Ciudad</label>
								    <input type="text" class="form-control"  name="ciudad"  id="ciudad" aria-describedby="emailHelp" placeholder="Ciudad" required>
								  </div>
								  <div class="form-group">
								    <label for="direccion">Movil</label>
								    <input type="text" class="form-control"  name="movil"  id="movil" aria-describedby="emailHelp" placeholder="Movil" required>
								  </div>
								  
								  <div class="form-group">
								    <label for="direccion">Correo</label>
								    <input type="email" class="form-control"  name="correo"  id="correo" aria-describedby="emailHelp" placeholder="Correo" required>
								  </div>
								  
								  <div class="form-group">
								    <label for="fechaNacimienyo">Fecha Nacimiento</label>
								    <input type="date" class="form-control"  name="fechaNacimiento"  id="fechaNacimiento" aria-describedby="emailHelp" placeholder="Dia/Mes/Año" required>
								  </div>

									<div class="form-group">
										<label for="sexo">Sexo</label><br> 
										  <select name="sexo" id="sexo">
											<option value="">Seleccione...</option>
											<c:forEach var="sexo" items="${Sexo.values()}">
												<c:if test="${sexo == paciente.getSexo()}">
													<option value="${sexo}" selected>${sexo}</option>
												</c:if>
												<c:if test="${sexo != paciente.getSexo()}">
													<option value="${sexo}">${sexo}</option>
												</c:if>
											</c:forEach>
										</select>

									</div>

									<div class="form-group">
										<label for="prevision">Prevision</label> <br>
										<select name="prevision" id="prevision">
								            <option value="">Seleccione...</option>
								            <c:forEach var="prevision" items="${Prevision.values()}"> 
								            <c:if test = "${prevision == paciente.getPrevision()}">
								            <option value="${prevision}" selected>${prevision}</option>
								            </c:if>
							                <c:if test = "${prevision != paciente.getPrevision()}">
								            <option value="${prevision}">${prevision}</option>
								            </c:if>
								     
								      </c:forEach>
								   
								    </select>
									</div>


									<button type="submit" class="btn btn-primary">Agregar</button>
								</form>
								
							</div>
						</div>
					</div>
					

					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table mr-1"></i> Listado de Pacientes
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<!-- Data Table -->
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Id</th>
											<th>Rut</th>
											<th>Nombre</th>
											<th>Correo</th>
											<th>Accion</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Id</th>
											<th>Rut</th>
											<th>Nombre</th>
											<th>Correo</th>
											<th>Accion</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach var="usuario" items="${pacientes}">
											<tr>
												<td>${usuario.id}</td>
												<td>${usuario.rut}</td>
												<td>${usuario.nombre}</td>
												<td>${usuario.correo}</td>

												<td>
												<a href='<c:out value="/pacientes/actualizar?id=${usuario.id}" />'>Actualizar</a>
												<a href="#">Eliminar</a> 
											</tr>
										</c:forEach>

									</tbody>
								</table>
								<!-- Data Table -->
							</div>
						</div>
					</div>
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
        $(document).ready(function () {
            $('#dataTable').DataTable({
                "language": {
                    "url": cdn
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

</body>

</html>