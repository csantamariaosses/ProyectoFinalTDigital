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
			   
			   <div class="container-fluid">
				<h3 class="mt-4"><i class="fa fa-file"></i> Tratamientos</h3>
				<br>
				 
					 <div class="card mb-4">
						<div class="card-header">
							<i class="far fa-address-card mr-1"></i> Informacion del Tratamiento
						</div>
						<div class="card-body datosUsuario">
							<div class="table-responsive">
								<!-- Formulario Paciente -->
								<form action="/tratamientos/buscar" method="post">
									<div class="radio">
										<label><input type="radio" name="optradio" value="rut"> Rut</label>
									</div>
									<div class="radio">
										<label><input type="radio" name="optradio" value="nombre"> Nombre</label>
									</div>
									<div class="form-group">
								    <input type="text" class="form-control" name="txtBuscar" id="txtBuscar" aria-describedby="emailHelp" required>						
								  </div>
								  
								  
								  <button type="submit" class="btn btn-primary">Buscar</button>
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
											<th>Seleccion</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Id</th>
											<th>Rut</th>
											<th>Nombre</th>
											<th>Correo</th>
											<th>Seleccion</th>
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
												<input type="radio" name="idPaciente" onClick="pacienteSelecciona(${usuario.id})"> 
											   </td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
								<!-- Data Table -->
							</div> <!--  div table  -->
						</div>     <!--  div card body   -->
					</div>         <!--  div card  -->
					
					
					<div class="card mb-4">
						<div class="card-header">
							<i class="far fa-address-card mr-1"></i> Datos Paciente
						</div>
						<div class="card-body datosUsuario">
							<div class="table-responsive">
								<!-- Formulario Paciente -->
								<form action="/admin" method="post">
								<div class="form-group">
								    <input type="text" class="form-control" name="rut" id="rut" aria-describedby="emailHelp" placeholder="Rut paciente" required>						
								  </div>
								  <div class="form-group">
								    <input type="text" class="form-control"  name="nombre"  id="nombre" aria-describedby="emailHelp" placeholder="Nombre paaciente" required>
								  </div>
								  <div class="form-group">
								    <input type="text" class="form-control"  name="direccion"  id="direccion" aria-describedby="emailHelp" placeholder="Direccion paaciente" required>
								  </div>
								  
								</form>
							</div>
						</div>
					</div>
					
					
					
					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table mr-1"></i> Historial de Procedimientos
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<!-- Data Table -->
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Id</th>
											<th>Rut Paciente</th>
											<th>Codigo</th>
											<th>Nombre</th>
											<th>Fecha</th>
											<th>Ver Detalle</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Id</th>
											<th>Rut Paciente</th>
											<th>Codigo</th>
											<th>Nombre</th>
											<th>Fecha</th>
											<th>Ver Detalle</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach var="tratamiento" items="${tratamientos}">
											<tr>
												<td>${tratamiento.id}</td>
												<td>${tratamiento.getPaciente().getRut()}</td>
												<td>${tratamiento.getProcedimiento().getCodProcedimiento()}</td>
												<td>${tratamiento.getProcedimiento().getNombre()}</td>
												<td>${tratamiento.getFechaIngreso()}</td>

												<td>
                                                    <a href="/tratamientos/ver?id=${tratamiento.id}" data-toggle="modal"
													data-target="#myModal-Datalle-${tratamiento.id}"><i
														class="fa fa-eye" aria-hidden="true"></i></a> <a
													href='<c:out value="/tratamientos/actualizar?id=${tratamiento.id}&rutPaciente=${tratamiento.getPaciente() }" />'><i
														class="fa fa-edit" aria-hidden="true"></i></a> <a
													href="/tratamientos/eliminar?id=${tratamiento.id}"
													data-toggle="modal"
													data-target="#myModal-${tratamiento.id}"><i
														class="fa fa-trash" aria-hidden="true"></i></a></tr>
										</c:forEach>

									</tbody>
								</table>
								<!-- Data Table -->
							</div> <!--  div table  -->
						</div>     <!--  div card body   -->
					</div>         <!--  div card  -->
						
					
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
	<script src="/js/scripts.js"></script>

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
	<script src="/assets/demo/datatables-demo.js"></script>
<script>
function pacienteSelecciona (id ) {
	window.location.href="/tratamientos/pacienteSelecciona?id=" + id;
}
</script>
</body>

</html>