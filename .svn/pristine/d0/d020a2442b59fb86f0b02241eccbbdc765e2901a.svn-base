<%@page import="java.util.List"%>
<%@page import="adm.alumno.spring.AdmSolicitud"%>
<%@page import="adm.alumno.spring.AdmCartaSubir"%>
<%@page import="adm.parametros.spring.AdmParametros"%>

<%@ include file= "../head.jsp"%>
<%@ include file= "../menu.jsp"%>
<head>
<link rel="STYLESHEET" href="/admision.css"  type="text/css">	  			
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
		@media (min-width: 587px) {
			.col-md-3{
				float: left;
				margin-left: 85px;
			}
		}
		@media (min-width: 764px) {
			.col-md-3{
				float: left;
				margin-left: 0;
			}		
		}
		@media (min-width: 480px) {
			.col-md-5{
				float: left;
			}
		}	
	</style>	
		
	<script type="text/javascript">
		function guardarArchivo(){
			if($("archivo").value != ""){	
				document.frmArchivo.submit();
			}else
				alert("Select a file");
				
		}
		
		function borrarArchivo(documentoId){
			if(confirm("Are you sure you want to delete this document?")){
				document.location.href = "borrararchivo?documentoId="+documentoId;
			}
		}
	</script>
</head>
<%	 
	String folio 			= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	List<String> opciones	= (List<String>)session.getAttribute("Opciones");
	String documentoId 		= request.getParameter("documentoId")==null?"0":request.getParameter("documentoId");	
	boolean existeArchivo	= (boolean)request.getAttribute("existeArchivo");
	String estado 			= (String)request.getAttribute("estado");
	String nombreArchivo	= (String)request.getAttribute("nombreArchivo");
	String nombreDocumento	= (String)request.getAttribute("nombreDocumento");
	String iconoArchivo		= "X";
	String esImagen			= (String)request.getAttribute("esImagen");
	String vacio			= (String)request.getAttribute("vacio");
	
	AdmSolicitud admSolicitud		= (AdmSolicitud)request.getAttribute("admSolicitud");
	AdmCartaSubir admCartaSubir 	= (AdmCartaSubir)request.getAttribute("admCartaSubir");
	AdmParametros admParametros 	= (AdmParametros)request.getAttribute("admParametros");
	
	if (nombreArchivo.contains(".doc")||nombreArchivo.contains(".docx")) 
		iconoArchivo = "<img src='../imagenes/word.png' style='height:30px'> "+nombreArchivo;
	else if (nombreArchivo.contains(".xls")||nombreArchivo.contains(".xlsx")) 
		iconoArchivo = "<img src='../imagenes/word.png' style='height:30px'> "+nombreArchivo;
	else if (nombreArchivo.contains(".ppt")||nombreArchivo.contains(".pptx")) 
		iconoArchivo = "<img src='../imagenes/word.png' style='height:30px'> "+nombreArchivo;		
	else if (nombreArchivo.contains(".pdf")) 
		iconoArchivo = "<img src='../imagenes/word.png' style='height:30px'> "+nombreArchivo;			
	else
		iconoArchivo = "<span class='glyphicon glyphicon-folder-open' aria-hidden='true'> "+nombreArchivo+"</span>";
		
%>
<body>
<div class="container-fluid">
	<div class="row">
		<%-- <%@ include file= "../opciones.jsp"%> --%>
		<div class="col-lg-12" style="background-color:white; min-height:calc(100vh - 37px);">
			<div class="page-header">
		  		<h2><%=nombreDocumento%></h2>
			</div>
			<div class="alert alert-info" role="alert">
				<a href="documentos" class="btn btn-primary"><i class="fas fa-arrow-left"></i></a>	  
			</div>
			<% if (existeArchivo && !estado.equals("A")){ %>
					<h4>
					<%=iconoArchivo%>	
					<a href="javascript:borrarArchivo('<%=documentoId%>')" class="btn btn-danger btn-sm"><i class="fas fa-times"></i></a>
					</h4>				
			<% 	} %>	
<%	if(vacio.equals("1")){%>	
		<h2 style="color: red;">Select a file</h2>
<%  }%>	
<% 		if(!existeArchivo){%>
				<h2><b><font size="5"><spring:message code='documentos.archivo.SubirArchivo'/></font></b></h2>
				<!-- ----------------------IMAGENES-------------------- -->
<%			if (!estado.equals("A")){ %>
				<form name="frmArchivo" id="frmArchivo" enctype="multipart/form-data" action="guardararchivo?documentoId=<%=documentoId%>" method="post">
					<div class="fileupload fileupload-new" data-provides="fileupload">
<%					if(admSolicitud.getEstado().equals("2")){ %>	
						<span class="btn  btn-default btn-file">
					    	<input type="file" id="archivo" name="archivo"/>
						</span><br><br>			
						<button class="btn btn-primary btn-large" id="btnGuardar" value="Enviar" onclick="guardarArchivo();" class="enviar" > <spring:message code='documentos.archivo.Enviar'/></button>
<% 					} %>	
					</div>
<% 					if(esImagen.equals("1")){%>
				    	<h2 style="color: red;"><spring:message code='documentos.archivo.TipoArchivo'/></h2>
<% 					}%>	
				</form>
<% 			}%>	
<% 		}	
 		if(existeArchivo){%>
			<br>
 			<a href="descargarPDF?documentoId=<%=documentoId%>" class="btn btn-warning"> <i class="fas fa-download fa-2x"></i> Download</a>
<% 		} %>	
		</div>
	</div>
</div>	
</body>