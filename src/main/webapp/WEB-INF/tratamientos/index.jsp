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
<title>Home</title>
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
	<nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
		<a class="navbar-brand" href="index.html">Start Bootstrap</a>
		<button class="btn btn-link btn-sm order-1 order-lg-0"
			id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>


		<ul class="navbar-nav ml-auto mr-0 mr-md-2">
			<!-- Menú usuario -->
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" id="userDropdown" href="#"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="userDropdown">
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="/logout">Logout</a>
				</div></li>
			<!-- Menú usuario -->
		</ul>
	</nav>
	<!-- Menú superior -->
	<div id="layoutSidenav">

		<!-- Menú Lateral -->
		<div id="layoutSidenav_nav">
			<nav class="sb-sidenav accordion sb-sidenav-dark"
				id="sidenavAccordion">
				<div class="sb-sidenav-menu">
					<div class="nav">
					
					    
						 <jsp:include page="../includes/menuUsuarios.jsp"></jsp:include>
						 
						 <!-- - 
						 <div class="sb-sidenav-menu-heading">Menu</div>
						
						<a class="nav-link" href="<c:out value='/usuario' />">
							<div class="sb-nav-link-icon">
								<i class="fa fa-child"></i>
							</div> Home
						</a> 
						
						<a class="nav-link" href="<c:out value='/pacientes' />">
							<div class="sb-nav-link-icon">
								<i class="fa fa-child"></i>
							</div> Pacientes
						</a> 
						
						<a class="nav-link" href="<c:out value='/procedimientos' />">
							<div class="sb-nav-link-icon">
								<i class="fa fa-child"></i>
							</div> Procedimientos
						</a> 
						
						<a class="nav-link" href="<c:out value='/tratamientos' />">
							<div class="sb-nav-link-icon">
								<i class="fa fa-child"></i>
							</div> Tratamientos
						</a>
						  -->
						
			</nav>
		</div>
		<!-- Menú superior -->

		<!-- Cuerpo de la aplicación -->
		<div id="layoutSidenav_content">
			<main>
			   
			   <div class="container-fluid">
				<h3 class="mt-4">Tratamientos</h3>
				<br>
				 
					 <div class="card mb-4">
						<div class="card-header">
							<i class="far fa-address-card mr-1"></i> Informacion del Tratamiento
						</div>
						<div class="card-body datosUsuario">
							<div class="table-responsive">
								<!-- Formulario Paciente -->
								<form action="/admin" method="post">
								<div class="form-group">
								    <input type="text" class="form-control" name="rut" id="rut" aria-describedby="emailHelp" placeholder="Rut paciente" required>						
								  </div>
								  <div class="form-group">
								    <input type="text" class="form-control"  name="nombre"  id="nombre" aria-describedby="emailHelp" placeholder="Nombre paciente" required>
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
										<c:forEach var="usuario" items="${usuarios}">
											<tr>
												<td>${usuario.id}</td>
												<td>${usuario.rut}</td>
												<td>${usuario.conombrerreo}</td>
												<td>${usuario.correo}</td>

												<td>
												<a href='<c:out value="/admin/actualizar?id=${usuario.id}" />'>Actualizar</a>
												<a href="#">Eliminar</a> 
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
							<i class="fas fa-table mr-1"></i> Historial Tratamientos
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<!-- Data Table -->
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
									<thead>
										<tr>
											<th>Id</th>
											<th>Codigo</th>
											<th>Nombre</th>
											<th>Fecha</th>
											<th>Ver Detalle</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Id</th>
											<th>Codigo</th>
											<th>Nombre</th>
											<th>Fecha</th>
											<th>Ver Detalle</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach var="usuario" items="${usuarios}">
											<tr>
												<td>${usuario.id}</td>
												<td>${usuario.codigo}</td>
												<td>${usuario.nombre}</td>
												<td>${usuario.fecha}</td>

												<td>
												<a href='<c:out value="/admin/actualizar?id=${usuario.id}" />'>Actualizar</a>
												<a href="#">Eliminar</a> 
											</tr>
										</c:forEach>

									</tbody>
								</table>
								<!-- Data Table -->
							</div> <!--  div table  -->
						</div>     <!--  div card body   -->
					</div>         <!--  div card  -->
					
					
					
					<div class="card mb-5">
						<div class="card-header">
							<i class="far fa-address-card mr-1"></i> Datos Procedimiento
						</div>
						<div class="card-body datosUsuario">
							<div class="table-responsive">
								<!-- Formulario Paciente -->
								<form action="/admin" method="post">

									<div class="form-group">
										<label for="procedimiento" class="col-sm-3 col-form-label">Procedimiento</label>
										<div class="col-sm-9">
											<select name="procedimiento">
								            <option value="">Seleccione...</option>
								            </select>
										</div>
									</div>
									<div class="form-group">
										<label for="profesional" class="col-sm-3 col-form-label">Profesional</label>
										<div class="col-sm-9">
											<select name="profesional">
								            <option value="">Seleccione...</option>
								            </select>
										</div>
									</div>

								 <div class="form-group">
  									<label for="comment" class="col-sm-3 col-form-label">Descripcion:</label>
  									<div class="col-sm-9">
  									<textarea class="form-control" rows="5" id="comment"></textarea>
  									</div>
								 </div>
								 
								 <div class="form-group">
										
										<div class="col-sm-12">
											<button type="submit" class="btn btn-primary">Agregar</button>
										</div>
									</div>
								  
								  
								</form>
							</div>
						</div>
					</div>
					
					
				</div>
				
			</main>
			<footer class="py-4 bg-light mt-auto">
				<div class="container-fluid">
					<div
						class="d-flex align-items-center justify-content-between small">
						<div class="text-muted">Copyright &copy; Your Website 2020</div>
						<div>
							<a href="#">Privacy Policy</a> &middot; <a href="#">Terms
								&amp; Conditions</a>
						</div>
					</div>
				</div>
			</footer>
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