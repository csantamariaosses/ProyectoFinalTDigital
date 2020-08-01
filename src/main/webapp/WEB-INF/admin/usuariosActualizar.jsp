<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="cl.csantam.model.entity.Rol" %>
<%@ include file="../header.html" %>


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
												<a href="/admin/actualizar?id=${usuario.id}">Actualizar</a> | 
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
<script>
function habilitaPwd(){
	alert("habiltar pwd");
}
</script>
	
</body>

</html>