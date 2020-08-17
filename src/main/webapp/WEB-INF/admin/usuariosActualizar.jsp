<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cl.csantam.model.entity.Rol" %>
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
						<jsp:include page="../includes/menuAdmin.jsp"></jsp:include>
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
				<h3 class="mt-4">Usuarios</h3>
				<br>
				 
					 <div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table mr-1"></i> Datos Usuario - Actualizar
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<!-- Formulario Usuario -->
								<form action="/admin/actualizar" method="POST">
								
								<div class="form-group">
								    		<input 
											type="hidden" 
											id="id" 
											name="id" 
											value="<c:out value='${usuario.getId() }' />" />
							
								  </div>
								  
								  
								  <!--|== Inicio - Mensaje error ======================|-->
									<c:if test="${ !exito && !empty(msg) }">
										<div class="alert alert-warning alert-dismissible fade show"
											role="alert">
											<strong>¡Error!</strong>${msg}
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
									</c:if>
									
									<!--|== Inicio - Mensaje Info ======================|-->
									<c:if test="${ exito && !empty(msg) }">
										<div class="alert alert-success alert-dismissible fade show"
											role="alert">
											<strong>¡Exito!</strong>${msg}
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
										</div>
									</c:if>
					
								  
								
								<div class="form-group">
								    <label for="rut">Rut</label>
								    <input type="text" class="form-control" name="rut" id="rut" aria-describedby="emailHelp" placeholder="Rut" value="${usuario.getRut()}">
							
								  </div>
								  <div class="form-group">
								    <label for="nombre">Nombre</label>
								    <input type="text" class="form-control"  name="nombre"  id="nombre" aria-describedby="emailHelp" placeholder="Nombre" value="${usuario.getNombre()}">
								  </div>
								  <div class="form-group">
								    <label for="correo">Correo</label>
								    <input type="email" class="form-control" name="correo"  id="correo" placeholder="Correo" value="${usuario.getCorreo()}">
								  </div>
								  <div class="form-group">
								    <label for="password">Password</label> 
								    <input type="password" class="form-control" name="contrasenia" id="contrasenia" placeholder="Password" value="<c:out value='${usuario.getContrasenia() }' />">
								  </div>
								   <div class="form-group">
								    <label for="rol">Rol</label><br>
								    <select name="rol" id="rol">
								    
								      <c:forEach var="rol" items="${Rol.values()}"> 
								            <c:if test = "${rol == usuario.getRol()}">
								            <option value="${rol}" selected>${rol}</option>
								            </c:if>
							                <c:if test = "${rol != usuario.getRol()}">
								            <option value="${rol}">${rol}</option>
								            </c:if>
								     
								      </c:forEach>
								   
								    </select>
								  </div>

								  <button type="submit" class="btn btn-primary">Guardar</button>
								</form>
								
							</div>
						</div>
					</div>
					

					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table mr-1"></i> Ejemplo de tabla
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<!-- Data Table -->
								<table class="table table-bordered" id="dataTable">
									<thead>
										<tr>
											<th>Id</th>
											<th>Nombre</th>
											<th>Correo</th>
											<th>Contrasenia</th>
											<th>Rol</th>
											<th>Accion</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Id</th>
											<th>Nombre</th>
											<th>Correo</th>
											<th>Contrasenia</th>
											<th>Accion</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach var="usuario" items="${usuarios}">
											<tr>
												<td>${usuario.id}</td>
												<td>${usuario.nombre}</td>
												<td>${usuario.correo}</td>
												<td>${usuario.contrasenia.substring(0, 10)}***</td>
												<td>${usuario.rol}</td>
												
												<td>
		                                        <a href='<c:out value="/admin/actualizar?id=${usuario.id}" />'><i class="fa fa-edit" aria-hidden="true"></i></a>
                                                <a href="/admin/eliminar?id=${usuario.id}" data-toggle="modal" data-target="#myModal-${usuario.id}"><i class="fa fa-trash" aria-hidden="true"></i></a>
											</tr>
											
											
											<div id="myModal-${usuario.id}" class="modal fade"
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
																usuario: ${usuario.id}?</p>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-danger"
																hint="eliminar"
																onClick="eliminar(${usuario.id});" data-dismiss="modal">Eliminar</button>
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
							</div>
						</div>
					</div>
				</div>
			</main>
			<!--  <%@ include file="../footer.html" %>  -->
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
		</div>
		<!-- Cuerpo de la aplicación -->
	</div>
	
<%@ include file="../includes/jsFooter.html" %>
<script>
function eliminar( id ) {
	alert(id);
	window.location.href = "/admin//eliminar?id="+id;
}
</script>
	
</body>

</html>