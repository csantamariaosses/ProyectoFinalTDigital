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
				<h3 class="mt-4"><i class="fa fa-file"></i> Tratamientos - Actualizar</h3>
				<br>
				 
					 <div class="card mb-4">
						<div class="card-header">
							<i class="far fa-address-card mr-1"></i> Informacion del Paciente
						</div>
						
						<div class="card-header">
						<table>
						    <tr><td>Id</td><td>${paciente.id }</td></tr>
						    <tr><td>Rut</td><td>${paciente.rut }</td></tr>
						    <tr><td>Nombre</td><td>${paciente.nombre }</td></tr>
						</table>
					
						</div>
						
                    <br>
					<div class="card mb-4">
						<div class="card-header">
						    <div class="row">
						       <div class="col-6"><i class="fas fa-table mr-1"></i> Historial de Procedimientos</div>
							   <div class="col-6" align="right"><a href="/tratamientos/tratamientosAgregarProcedimento?idPaciente=${paciente.id}"><i class="fa fa-plus" aria-hidden="true"></i>  Agregar Procedimiento</a></div>
							 </div>
							 
							 
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
												<a href="/tratamientos/ver?id=${tratamiento.id}" data-toggle="modal" data-target="#myModal-Datalle-${tratamiento.id}"><i class="fa fa-eye" aria-hidden="true"></i></a>
												<a href='<c:out value="/tratamientos/actualizar?id=${tratamiento.id}&rutPaciente=${tratamiento.getPaciente().getRut() }" />'><i class="fa fa-edit" aria-hidden="true"></i></a>  									
												<a href="/tratamientos/tratamientosEliminar?idTratamiento=${tratamiento.id}&idPaciente=${paciente.id}" data-toggle="modal" data-target="#myModal-${tratamiento.id}"><i class="fa fa-trash" aria-hidden="true"></i></a> 
												
											</tr>
											
											
											<div id="myModal-${tratamiento.id}" class="modal fade"
												role="dialog">
												<div class="modal-dialog">

													<!-- Modal content-->
													<div class="modal-content">
														<div class="modal-header">
														    
															<button type="button" class="close" data-dismiss="modal">&times;</button>
															
														</div>
														<div class="modal-body">
														<h4 class="modal-title">Advertencia de Eliminacion</h4>
															<p>Esta seguro de querer eliminar el
																registro: ${tratamiento.id}?</p>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-danger"
																hint="eliminar"
																onClick="eliminar(${tratamiento.id},${paciente.id });" data-dismiss="modal">Eliminar</button>
															<button type="button" class="btn btn-default"
																data-dismiss="modal">Close</button>
														</div>
													</div>

												</div>
											</div>
											
											
											<div id="myModal-Datalle-${tratamiento.id}" class="modal fade"
												role="dialog">
												<div class="modal-dialog modal-lg">

													<!-- Modal content-->
													<div class="modal-content">
														<div class="modal-header">
														   <h4>Información del Procedimiento</h4>
															<button type="button" class="close" data-dismiss="modal">&times;</button>
															
														</div>
														<div class="modal-body">

														 <b>Rut Paciente:</b>&nbsp;${tratamiento.getPaciente().getRut()}<BR>
														 <b>Nombre Paciente:</b>&nbsp;${tratamiento.getPaciente().getNombre()}<BR>
														 <b>Cod.Procedimiento:</b>&nbsp;${tratamiento.getProcedimiento().getCodProcedimiento()}<BR>
														 <b>Fecha Ingreso:</b>&nbsp;${tratamiento.getFechaIngreso()}<BR>
														 <b>Descripcion:</b>&nbsp;${tratamiento.getDescripcion()}<br>														
														</div>
														<div class="modal-footer">
												
															<button type="button" class="btn btn-default"
																data-dismiss="modal">Close</button>
														</div>
													</div>

												</div>
											</div>
											
											
										
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

function eliminar( idTratamiento, idPaciente ) {
	window.location.href = "/tratamientos/tratamientosEliminar?idTratamiento="+idTratamiento+"&idPaciente="+idPaciente;
}
</script>
</body>

</html>