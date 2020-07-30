<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cl.csantam.model.entity.Rol" %>
<%@ include file="../header.html" %>


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
						<div class="sb-sidenav-menu-heading">Tí­tulo prueba</div>
						<a class="nav-link" href="<c:out value='/admin' />">
							<div class="sb-nav-link-icon">
								<i class="far fa-thumbs-up"></i>
							</div> Home
						</a>
						<a class="nav-link" href="<c:out value='/admin/usuarios' />">
							<div class="sb-nav-link-icon">
								<i class="far fa-thumbs-up"></i>
							</div> Usuarios
						</a>
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
							<i class="far fa-address-card mr-1"></i> Datos Usuario
						</div>
						<div class="card-body datosUsuario">
							<div class="table-responsive">
								<!-- Formulario Usuario -->
								<form action="/admin" method="post">
								<div class="form-group">
								    <label for="rut">Rut</label>
								    <input type="text" class="form-control" name="rut" id="rut" aria-describedby="emailHelp" placeholder="Rut" required>
							
								  </div>
								  <div class="form-group">
								    <label for="nombre">Nombre</label>
								    <input type="text" class="form-control"  name="nombre"  id="nombre" aria-describedby="emailHelp" placeholder="Nombre" required>
								  </div>
								  <div class="form-group">
								    <label for="correo">Correo</label>
								    <input type="email" class="form-control" name="correo" id="correo" placeholder="Correo" required>
								  </div>
								  <div class="form-group">
								    <label for="password">Password</label>
								    <input type="password" class="form-control" name="contrasenia" id="contrasenia" placeholder="Password" required>
								  </div>
								   <div class="form-group">
								    <label for="rol">Rol</label><br>
								    <select name="rol" id="rol">
								      <option value="">Seleccione</option>
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

								  <button type="submit" class="btn btn-primary">Agregar</button>
								</form>
								
							</div>
						</div>
					</div>
					

					<div class="card mb-4">
						<div class="card-header">
							<i class="fas fa-table mr-1"></i> Listado Usuarios
						</div>
						<div class="card-body">
							<div class="table-responsive">
								<!-- Data Table -->
								<table class="table table-bordered" id="dataTable" width="100%"
									cellspacing="0">
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
												<a href='<c:out value="/admin/actualizar?id=${usuario.id}" />'>Actualizar</a>
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
			<%@ include file="../footer.html" %>
		</div>
		<!-- Cuerpo de la aplicación -->
	</div>
	<%@ include file="../jsFooter.html" %>
</body>

</html>