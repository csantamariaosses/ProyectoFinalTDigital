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
				 
					
					<div class="card mb-5">
						<div class="card-header">
							<i class="far fa-address-card mr-1"></i> Datos Procedimiento
						</div>
						<div class="card-body datosUsuario">
							<div class="table-responsive">
								<!-- Formulario Paciente -->
								<form action="/tratamientos/tratamientosAgregarProcedimiento" method="post">

									<div class="form-group">
										<input type="text" class="form-control" name="idPaciente"
											id="idPaciente" aria-describedby="emailHelp"
											value="${idPaciente }">
									</div>
									
									<div class="form-group">
										<label for="procedimiento" class="col-sm-3 col-form-label">Procedimiento</label>
										<div class="col-sm-9">
											<select name="codProcedimiento"
											id="codProcedimiento">
											<option value="">Seleccioe...</option>
											<c:forEach var="proced" items="${procedimientos}">	
													<option value="${proced.getCodProcedimiento()}">${proced.getNombre()}</option>												
											</c:forEach>
										    </select>
										</div>
									</div>
									<div class="form-group">
										<label for="profesional" class="col-sm-3 col-form-label">Profesional</label>
										<div class="col-sm-9">
											<select name="rutUsuario"
											id="rutUsuario">
											<option value="">Seleccioe...</option>
											<c:forEach var="usuario" items="${usuarios}">	
													<option value="${usuario.getRut()}">${usuario.getNombre()}</option>												
											</c:forEach>
										    </select>
										</div>
									</div>

								 <div class="form-group">
  									<label for="comment" class="col-sm-3 col-form-label">Descripcion:</label>
  									<div class="col-sm-9">
  									<textarea class="form-control" rows="5" name="descripcion" id="descripcion"></textarea>
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

</body>

</html>