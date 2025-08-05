<%@ include file= "../head.jsp"%>
<%@ include file= "../menu.jsp"%>

<%@page import="adm.alumno.spring.AdmSolicitud"%>
<%@page import="adm.alumno.spring.AdmCartaSubir"%>

<%@ page import= "java.util.Base64"%>

<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap3/css/bootstrap-fileupload.min.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap3/js/bootstrap-fileupload.min.js"></script>
<link href="<%=request.getContextPath()%>/admision.css" rel="STYLESHEET" type="text/css">
<%	 
	String folio 				= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	String documentoId 			= request.getParameter("documentoId")==null?"0":request.getParameter("documentoId");
	String hoja 				= request.getParameter("hoja")==null?"0":request.getParameter("hoja");
	byte imagenByte[]			= (byte[])request.getAttribute("imagenByte");
	boolean existeImagen		= (boolean)request.getAttribute("existeImagen");
	String estado 				= (String)request.getAttribute("estado");
	String documentoNombre		= (String)request.getAttribute("documentoNombre");
	String esImagen				= (String)request.getAttribute("esImagen");
	
	AdmSolicitud admSolicitud		= (AdmSolicitud)request.getAttribute("admSolicitud");
	AdmCartaSubir admCartaSubir 	= (AdmCartaSubir)request.getAttribute("admCartaSubir");
	
	String imagenStr 			= Base64.getEncoder().encodeToString(imagenByte);
%>
<style>		
		body{
			background-image: url("<%=request.getContextPath()%>/imagenes/Biblioteca.png");
			/* Para dejar la imagen de fondo centrada, vertical y horizontalmente */
			background-position: center center;
			/* La imagen se fija en la ventana de visualización para que la altura de la imagen no supere a la del contenido */
			background-attachment: fixed;
			background-repeat: no-repeat;
			/* La imagen de fondo se reescala automáticamente con el cambio del ancho de ventana del navegador */
			background-size: cover;	
		} 
</style>		
<script type="text/javascript">
	function guardarImagen(){
		if($("imagen").value != ""){	
			document.frmImagen.submit();
		}else
			alert("Selecciona el archivo a subir");
	}
	
	function borrarImagen(documentoId,hoja){
		if(confirm("¿Desea eliminar el documento?")){
			document.location.href = "borrarimagen?documentoId="+documentoId+"&hoja="+hoja;
		}
	}
</script>
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col-lg-1" style="background-color:rgba(255, 255, 255, 0.7);">
			<span class="btn btn-outline-light" style="width: 100px; font-size: 13px;">
				<a href="<%=request.getContextPath()%>/registro" style="color:black">					
					<span data-bs-toggle="tooltip" data-bs-placement="right" title="Datos usuario"><i class="fas fa-user fa-2x"></i><br>Cuenta</span>&nbsp;
				</a>
			</span>
			<span class="btn btn-outline-light" style="width: 100px; font-size: 13px;">
				<a href="<%=request.getContextPath()%>/solicitud/modalidad" style="color:black">					
					<span data-bs-toggle="tooltip" data-bs-placement="right" title="Solicitud de admisi&oacute;n"><i class="far fa-file-alt fa-2x"></i><br>Solicitud</span>&nbsp;
				</a>
			</span>			
			<span class="btn btn-outline-light" style="width: 100px; font-size: 13px;">
				<a href="<%=request.getContextPath()%>/documentos/documentos" style="color:black">					
					<span data-bs-toggle="tooltip" data-bs-placement="right" title="Documentos de ingreso"><i class="fas fa-tasks fa-2x"></i><br>Documentos</span>&nbsp;
				</a>
			</span>					
			<span class="btn btn-outline-light tip" style="width: 100px; font-size: 13px;">
				<a href="<%=request.getContextPath()%>/directo/ingreso" style="color:black">
					<span data-bs-toggle="tooltip" data-bs-placement="right" title="Ingreso directo"><i class="fas fa-door-open fa-2x"></i><br>Ingreso</span>&nbsp;
				</a>
			</span>					
			<span class="btn btn-outline-light tip" style="width: 100px; font-size: 13px;">
				<a href="<%=request.getContextPath()%>/referencias?Folio=<%=folio%>" style="color:black">
					<span data-bs-toggle="tooltip" data-bs-placement="right" title="Pago en linea"><i class="fas fa-credit-card fa-2x"></i><br>Pago</span>&nbsp;
				</a>
			</span>					
			<span class="btn btn-outline-light tip" style="width: 100px; font-size: 13px;">
				<a href="<%=request.getContextPath()%>/cita/evento" style="color:black">
					<span data-bs-toggle="tooltip" data-bs-placement="right" title="Reservar examen"><i class="far fa-calendar-alt fa-2x"></i><br>Examen</span>&nbsp;
				</a>
			</span>					
			<span class="btn btn-outline-light tip" style="width: 100px; font-size: 13px;">
				<a href="<%=request.getContextPath()%>/resultados/resultados" style="color:black">
					<span data-bs-toggle="tooltip" data-bs-placement="right" title="Resultados examen"><i class="fas fa-trophy fa-2x"></i><br>Resultados</span>&nbsp;
				</a>
			</span>					
			<span class="btn btn-outline-light tip" style="width: 100px; font-size: 13px;">
<% 				String colorCarta = "black";
				if(admCartaSubir.getCarta() != null){	
					colorCarta = "green";										
 				}%>												
				<a href="<%=request.getContextPath()%>/descargarCartaAdmision" style="color:<%=colorCarta%>">
					<span data-bs-toggle="tooltip" data-bs-placement="right" title="Carta de admisi&oacute;n"><i class="fas fa-envelope fa-2x"></i><br>Carta</span>&nbsp;
				</a>
			</span>					
		</div>
		<div class="col-lg-11" style="background-color:white; min-height:calc(100vh - 37px);">
			<h2><%=documentoNombre%><small class="text-muted fs-5">( <spring:message code='documentos.imagen.Hoja'/>: <%=hoja%> )</small></h2>		
			<div class="alert alert-info">
				<a href="documentos" class="btn btn-primary"><i class="fas fa-arrow-left"></i></a>	  			
			</div>
			<div class="alert alert-secondary">
				<div class="row">
					<div class="col-md-3">
						<img id="img" width="100%" src="data:image/jpg;base64,<%=imagenStr%>" />
<%				if (existeImagen && !estado.equals("A")){%>
						<br><br>
						<a href="javascript:borrarImagen('<%=documentoId%>','<%=hoja%>')" class="btn btn-danger btn-sm"><i class="fas fa-times"></i></a>				
<%				}%>	
					</div>
					<div class="col-md-4">
<%				if (existeImagen==false){%> 
						<h2 align="center" colspan="4"><font size="3"><spring:message code='documentos.imagen.SubirImagen'/></font></h2>
						<br>								
				<!-- ----------------------IMAGENES-------------------- -->
						<form name="frmImagen" id="frmImagen" enctype="multipart/form-data" action="guardarimagen?documentoId=<%=documentoId%>&hoja=<%=hoja%>" method="post">				
							<div class="fileupload fileupload-new" data-provides="fileupload">
					  			<div class="fileupload-new thumbnail" style="width: 100px; height: 100px;"><img src="http://www.placehold.it/100x100/EFEFEF/AAAAAA" /></div>
					  			<div class="fileupload-preview fileupload-exists thumbnail" style="width: 100px; height: 100px;"></div>
								<span class="btn btn-default btn-file">
<%							if(admSolicitud.getEstado().equals("2")){ %>	
				    				<span class="btn btn-info fileupload-new"><spring:message code='documentos.imagen.SeleccionaImagen'/></span>
<% 							} %>	
					    		<span class="btn btn-info fileupload-exists"><spring:message code='documentos.imagen.Cambiar'/></span>
					    			<input type="file" id="imagen" name="imagen" />
								</span>					
								<span class="fileupload-exists pul-right">
									<a href="#" class="btn btn-default fileupload-exists" data-dismiss="fileupload"> <button class="btn btn-danger btn-large" ><spring:message code='documentos.imagen.Quitar'/></button></a>
								</span>
								<span class="fileupload-exists">
									<button class="btn btn-primary btn-large" id="btnGuardar" value="Enviar" onclick="javascript:guardarImagen();" class="enviar" ><i class="icon-arrow-up icon-white"></i><spring:message code='documentos.imagen.Enviar'/></button>
								</span>
							</div>	
<% 						if(esImagen.equals("1")){%>
			    			<h2 style="color: red;"><spring:message code='documentos.imagen.TipoArchivos'/></h2>
<% 						}%>
						</form>
<% 				} %>	
					</div>			
				</div>
			</div>
		</div>	
	</div>
</div>	
</body>