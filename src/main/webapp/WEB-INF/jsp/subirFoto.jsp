<%@page import="adm.util.Utilerias"%>

<%@ include file= "head.jsp"%>
<%@ include file= "menu.jsp"%>

<%
	
	String mensaje = (String)request.getAttribute("mensaje");
%>

<head>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<style>			
	@media (min-width: 320px){
		.alert-success {
			width: 550px;
			margin:0 auto;
			margin-bottom: 50px;
			padding-left: 5px;
			padding-right: 5px;
		}
	}
		
	body{
		background-image: url("imagenes/Biblioteca.png");
		/* Para dejar la imagen de fondo centrada, vertical y horizontalmente */
		background-position: center center;
		/* La imagen se fija en la ventana de visualizaci�n para que la altura de la imagen no supere a la del contenido */
		background-attachment: fixed;
		background-repeat: no-repeat;
		/* La imagen de fondo se reescala autom�ticamente con el cambio del ancho de ventana del navegador */
		background-size: cover;	
	}
	
	.well-white{
		width: 80%;
		height:530px;
		margin: auto;
		border:3px solid white;
		border-radius: 60px;
		background:white;
		background-color:rgba(255, 255, 255, 0.7);	
		background-attachment: fixed;
		padding-left: 20px;
		padding-right: 20px;
		
		-webkit-box-sizing:border-box;
		   -moz-box-sizing:border-box;
		    -ms-box-sizing:border-box;
		        box-sizing:border-box;
	
		-webkit-box-shadow: 1px 2px 12px -2px rgba(0,0,0,.5);
		   -moz-box-shadow: 1px 2px 12px -2px rgba(0,0,0,.5);
		     -o-box-shadow: 1px 2px 12px -2px rgba(0,0,0,.5);
			-ms-box-shadow: 1px 2px 12px -2px rgba(0,0,0,.5);
			    box-shadow: 1px 2px 12px -2px rgba(0,0,0,.5);		   
	}
</style>
<script type="text/javascript">

</script>
</head>
<body>
<div class="container-fluid">
	<form name="frmArchivo" id="frmArchivo" enctype="multipart/form-data" action="guardarFoto" method="post">
		<div class="well-white">	
<% 	if(mensaje.equals("1")){%>
	<div class="alert alert-success" role="alert">Grabado</div>
<% 	}%>
			<h3 style="color:blue" align="center">Subir foto (.jpg o .png)</h3>
			<div align="center" class="fileupload fileupload-new" data-provides="fileupload">
				<span class="btn  btn-default btn-file">
			    	<span class="fileupload-new"><spring:message code='documentos.archivo.SeleccionArchivo'/></span>
			    	<input type="file" id="archivo" name="archivo"/>
				</span><br>			
				<button type="submit" class="btn btn-primary btn-large" value="Enviar"><spring:message code='documentos.archivo.Enviar'/></button>
			</div>
		</div>
	</form>
</div>
</body >
