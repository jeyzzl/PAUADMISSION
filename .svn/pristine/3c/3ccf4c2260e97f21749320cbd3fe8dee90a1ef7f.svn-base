<%@ include file= "head.jsp"%>
<%@ include file= "menu.jsp"%>
<%
	String sesionId			= session.getId();	
	session.invalidate();
%>
<head>
  	<link rel="STYLESHEET" href="/admision.css"  type="text/css">	  			
	<style>		
		body{
			background-image: url("imagenes/Biblioteca.png");
			/* Para dejar la imagen de fondo centrada, vertical y horizontalmente */
			background-position: center center;
			/* La imagen se fija en la ventana de visualización para que la altura de la imagen no supere a la del contenido */
			background-attachment: fixed;
			background-repeat: no-repeat;
			/* La imagen de fondo se reescala automáticamente con el cambio del ancho de ventana del navegador */
			background-size: cover;	
		}	
		/* Color Daisy Bush #5b3e90; rgb(83,38,163) */
	</style>		
</head>
<body>
<div class="container">
	<br>
	<div class="alert alert-light text-center" style="opacity:0.7">
	<span class="fs-5"><spring:message code="salir.CerrarSesion"/></span>
	</div>
</div>		
</body>
<meta http-equiv="refresh" content='1; URL=login'>