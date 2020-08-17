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
				<h3 class="mt-4"><i class="fa fa-clipboard"></i>  Procedimientos - Actualizar</h3>
				<br>
				 
					 <div class="card mb-4">
						<div class="card-header">
							<i class="far fa-address-card mr-1"></i> Datos Procedimientos::
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


						<div class="card-body datosUsuario">
							<div class="table-responsive">
								<!-- Formulario Paciente -->
								<form action="/procedimientos/actualizar" method="post">
								
								    <div class="form-group">
										<input type="hidden" id="id" name="id"
											value="<c:out value='${procedimiento.getId() }' />" />

									</div>
									
								<div class="form-group"> 
								    <label for="rut">Codigo</label>
								    <input type="text" class="form-control" name="codProcedimiento" id="codProcedimiento" aria-describedby="emailHelp" placeholder="Codigo Procedimiento" required  value="<c:out value='${procedimiento.getCodProcedimiento() }' />">
							
								  </div>
								  <div class="form-group">
								    <label for="nombre">Nombre</label>
								    <input type="text" class="form-control"  name="nombre"  id="nombre" aria-describedby="emailHelp" placeholder="Nombre" required value="<c:out value='${procedimiento.getNombre() }' />">
								  </div>
								  <div class="form-group">
								    <label for="direccion">Descripcion</label>
								    <textarea class="form-control" name="descripcion"><c:out value='${procedimiento.getDescripcion() }' /></textarea>
								   
								  </div>
								  <div class="form-group">
								    <label for="direccion">Precio</label>
								    $ <input type="text" class="form-control"  name="precio"  id="precio" aria-describedby="emailHelp" placeholder="Precio" required value="<c:out value='${procedimiento.getPrecio() }' />">
								  </div>
								 

								  <button type="submit" class="btn btn-primary">Guardar</button>
								</form>
								
							</div>
						</div>
					</div>
					

					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table mr-1"></i> Listado de Procedimientos
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
											<th>Precio</th>
											<th>Accion</th>
										</tr>
									</thead>
									<tfoot>
										<tr>
											<th>Id</th>
											<th>Codigo</th>
											<th>Nombre</th>
											<th>Precio</th>
											<th>Accion</th>
										</tr>
									</tfoot>
									<tbody>
										<c:forEach var="usuario" items="${procedimientos}">
											<tr>
												<td>${usuario.id}</td>
												<td>${usuario.codProcedimiento}</td>
												<td>${usuario.nombre}</td>
												<td>$ ${usuario.precio}</td>

												<td>
												 <a href='<c:out value="/procedimientos/actualizar?id=${usuario.id}" />'><i class="fa fa-edit" aria-hidden="true"></i></a>
												<a href="/procedimientos/eliminar?id=${usuario.id}" data-toggle="modal" data-target="#myModal-${usuario.id}"><i class="fa fa-trash" aria-hidden="true"></i></a> 
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
																procedimiento: ${usuario.id}?</p>
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
function eliminar( id ) {
	window.location.href = "/procedimientos/eliminar?id="+id;
}
</script>
</body>

</html>