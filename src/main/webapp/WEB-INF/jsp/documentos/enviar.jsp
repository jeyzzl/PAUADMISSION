<%@ include file="../con_salomon.jsp"%>
<%@ include file="../conectadbp.jsp"%>
<%@ include file= "../head2.jsp"%>
<%@ include file= "../menu2.jsp"%>

<jsp:useBean id="RequisitoU" scope="page" class="adm.documento.RequisitoUtil"/>
<jsp:useBean id="AdmDocAlum" scope="page" class="adm.documento.AdmDocAlum"/>
<jsp:useBean id="pgAdmDocAlum" scope="page" class="adm.documento.PgAdmDocAlum"/>
<jsp:useBean id="pgAdmArchivos" scope="page" class="adm.documento.PgAdmArchivos"/>

<%@page import="adm.documento.AdmDocumento"%>
<head>
<link rel="stylesheet" href="../bootstrap3/css/bootstrap-fileupload.min.css">
<script type="text/javascript" src="../bootstrap3/js/bootstrap-fileupload.min.js"></script>
<style>
	.enviar{
		 width:156px;
         height:30px;
         background-color:#E6E6E6;
         border: 2px solid black;
         border-radius: 7px;
         cursor:hand;
         cursor:pointer;
         color:black;
         font-size:14px;
	}
</style>
<link href="jquery.si.css" rel="stylesheet" type="text/css" />
<script src="http://jqueryjs.googlecode.com/files/jquery-1.3.1.min.js" type="text/javascript"></script>
<script src="jquery.si.js" type="text/javascript"></script>
<script type="text/javascript">
	jQuery.noConflict();
	jQuery(document).ready(function() {
		jQuery("input.file").si();
	});
</script>

<link href="../admision.css" rel="STYLESHEET" type="text/css">
<%	 
	String folio 			= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	String documentoId 		= request.getParameter("documentoId");	
%>
<script type="text/javascript" src="../js/prototype-1.6.js"></script>
<script type="text/javascript">
	function guardar(){
		if($("archivo").value != ""){
			$("btnGuardar").disabled = true;
			$("btnGuardar").value = "<spring:message code='documentos.enviar.Guardando'/>";
			document.formaEnviar.submit();
		}else
			alert("<spring:message code='documentos.enviar.SeleccionaArchivo'/>");
	}
	
	function guardarImagen(Hoja){
		if($("img"+Hoja).value != ""){
			$("btnGuardar"+Hoja).disabled = true;
			$("btnGuardar"+Hoja).value = "<spring:message code='documentos.enviar.Guardando'/>";
			if(Hoja==1){
				document.forma1.submit();
			}
			if(Hoja==2){
				document.forma2.submit();
			}
			if(Hoja==3){
				document.forma3.submit();
			}
			if(Hoja==4){
				document.forma4.submit();
			}
			if(Hoja==5){
				document.forma5.submit();
			}
			if(Hoja==6){
				document.forma6.submit();
			}
			if(Hoja==7){
				document.forma7.submit();
			}
		}else
			alert("<spring:message code='documentos.enviar.SeleccionaImagen'/>");
	}
	
	function eliminarImg(Hoja){
		if(confirm("<spring:message code='documentos.enviar.DeseaEliminar'/>")){
			document.location = "visualizar?Accion=2&documentoId=<%=documentoId%>&hoja="+Hoja;
		}
	}
</script>


</head>
<body style="height: 97%;background:url(../imagenes/um3.png) no-repeat bottom right fixed;background-color:#F7EBEB;">

<div class="container">
		
	<nav class="navbar-right">
	  <ul class="pager">
			<li><a href="documentos"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span><spring:message code='adm.Regresar'/></a></li>
	  </ul>
	</nav> 
	
	<div class="page-header">
	  <h1><spring:message code='documentos.enviar.PEnviar'/><%=adm.documento.AdmDocumento.getNombreDocumento(conSalomon,documentoId) %></small></h1>
	</div>
	
	<div class="well">
	
	
  <div class="row">
    <div class="col-md-4 col-md-offset-4">  

<form name="formaEnviar" id="formaEnviar" enctype="multipart/form-data" action="guardararchivo?documentoId=<%=documentoId%>" method="post">

		<h2><b><font size="5"><spring:message code='documentos.enviar.Documento'/></font></b></h2>
	<!-- ----------------------DOCUMENTO -------------------- -->
	<%
	
		pgAdmArchivos.setFolio(folio);
		pgAdmArchivos.setDocumentoId(documentoId);
		if (pgAdmArchivos.existeReg(conn2)) {
			pgAdmArchivos.mapeaRegId(conn2, folio, documentoId);
		%>
		
	<div class="fileupload-preview fileupload-exists thumbnail" style="width: 100px; height: 100px;"> <i class="fas fa-file-alt" style="position:absolute"></i><img src="bajar?documentoId=<%= documentoId%>" width="700px" onerror="this.parentNode.removeChild(this)" /> </div>
		
		<a style="text-decoration:none;" href="bajar?documentoId=<%= documentoId%>"title="Descargar archivo">
			<button type="button" class="btn btn-primary btn-large" ><spring:message code='documentos.enviar.Descargar'/></button>
		</a> 
		
		
		<a href="borrararchivo?documentoId=<%= documentoId%>" title="Borrar el archivo"> 
		<button type="button" class="btn btn-primary btn-large" ><spring:message code='adm.Eliminar'/></button>
		</a>
	<%}else{ %>
	
		<div class="fileupload fileupload-new" data-provides="fileupload">
		  <div class="fileupload-new thumbnail" style="width: 100px; height: 100px;"><img src="http://www.placehold.it/100x100/EFEFEF/AAAAAA" /></div>
		  <div class="fileupload-preview fileupload-exists thumbnail" style="width: 100px; height: 100px;"></div>
		  
			<span class="btn  btn-default btn-file">
		    	<span class="fileupload-new"><spring:message code='documentos.enviar.Seleccionar'/></span>
		    	<span class="fileupload-exists"><spring:message code='documentos.enviar.PalabraCambiar'/> </span>
		    	<input type="file" id="archivo" name="archivo"/>
			</span>
			
			<span class="fileupload-exists pul-right">
				<a href="#" class="btn fileupload-exists" data-dismiss="fileupload"> <button class="btn btn-primary btn-large" ><spring:message code='documentos.enviar.PalabraQuitar'/></button></a>
			</span>
			
			<span class="fileupload-exists" >
				<button class="btn btn-primary btn-large" id="btnGuardar" value="Enviar" onclick="guardar();" class="enviar" ><i class="icon-arrow-up icon-white"></i> <spring:message code='documentos.enviar.PEnviar'/></button>
			</span>
		</div>
	
	<%} %>
</form>
</div>
  </div>  
   </div>
<br>

<div class="well">

<div class="row">
<div class="col-md-12 ">  
		<h2 align="center" colspan="2"><b><font size="5"><spring:message code='documentos.enviar.Imagenes'/></font></b></h2>
<br>
<%		
	int contador = 0;
	for(int i=1; i<=7; i++){
		pgAdmDocAlum.setFolio(folio);
		pgAdmDocAlum.setDocumentoId( documentoId);
		pgAdmDocAlum.setHoja(i+"");
		
		if (pgAdmDocAlum.existeReg(conn2)) {
			contador = Integer.parseInt(pgAdmDocAlum.getHoja());
		}
	}
	boolean activar = false;
	
	String SnumImg = request.getParameter("num")==null?"2":request.getParameter("num");
	int numImg = Integer.parseInt(SnumImg);
	if(contador>numImg){
		numImg=contador;
		activar=true;
	}
	if(request.getParameter("num")==null){
		activar=true;
	}
	if(contador==7)activar=false;

	for(int i=1; i<=numImg; i++){ %>
	
	<div class="col-xs-5">
	<!-- ----------------------IMAGENES-------------------- -->
		<form name="forma<%=i%>" id="forma<%=i%>" enctype="multipart/form-data" action="guardar?documentoId=<%=documentoId%>&hoja=<%=i%>" method="post">
		    
		    

			<%
					pgAdmDocAlum.setFolio(folio);
					pgAdmDocAlum.setDocumentoId( documentoId);
					pgAdmDocAlum.setHoja(i+"");
					
					if (pgAdmDocAlum.existeReg(conn2)) {
			%>
					 <br>
					 <div class="fileupload-preview fileupload-exists thumbnail" style="width: 100px; height: 100px;"><img src="imagenDoc?Folio=<%=folio%>&documentoId=<%=documentoId %>&hoja=<%=i%>" width="700px" /> </div>
						<a href="visualizar?documentoId=<%=documentoId%>&hoja=<%=i%>" title="Mostrar">
							<button type="button" class="btn btn-primary btn-large" >Ver</button>
						</a>
						<a onclick="eliminarImg('<%=i%>');" title="Mostrar" href="#">
						<button type="button" class="btn btn-primary btn-large" ><spring:message code='adm.Eliminar'/></button>
						</a>
						
				<%  }else{ %>
				
				<br>
				<div class="fileupload fileupload-new" data-provides="fileupload">
				  <div class="fileupload-new thumbnail" style="width: 100px; height: 100px;"><img src="http://www.placehold.it/100x100/EFEFEF/AAAAAA" /></div>
				  <div class="fileupload-preview fileupload-exists thumbnail" style="width: 100px; height: 100px;"></div>
				  
					<span class="btn  btn-default btn-file">
				    	<span class="fileupload-new"><spring:message code='documentos.enviar.SeleccionaImagenes'/></span>
				    	<span class="fileupload-exists"><spring:message code='documentos.enviar.PalabraCambiar'/> </span>
				    	<input type="file" id="img<%=i%>" name="archivo" />
					</span>
					
					<span class="fileupload-exists pul-right">
						<a href="#" class="btn fileupload-exists" data-dismiss="fileupload"> <button class="btn btn-primary btn-large" ><spring:message code='documentos.enviar.PalabraQuitar'/></button></a>
					</span>
					
					<span class="fileupload-exists" >
						<button class="btn btn-primary btn-large" id="btnGuardar<%=i%>" value="Enviar" onclick="guardarImagen('<%=i%>');" class="enviar" ><i class="icon-arrow-up icon-white"></i> <spring:message code='documentos.enviar.PEnviar'/></button>
					</span>
				</div>
				
				<%  } %>
			
			</form>
</div>
	<%if(i==numImg && activar){ %>
	<a href="enviar?documentoId=<%=documentoId%>&num=7" style="text-decoration:none; "><button type="button" class="btn btn-primary btn-large float-right" style="margin-top:125px;" ><spring:message code='documentos.enviar.SubirImagenes'/></button></a>
	<%} %>
<%}//System.out.println("Paso 4"); %>
</div>

</div></div>
</div>

</body>


<%@ include file="../cierradbp.jsp"%>
<%@ include file="../cierra_salomon.jsp"%>