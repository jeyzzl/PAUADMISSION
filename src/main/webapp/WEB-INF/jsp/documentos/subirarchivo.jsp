<%		
	String documentoId 			= request.getParameter("documentoId")==null?"0":request.getParameter("documentoId");	
	String documentoNombre 		= (String) request.getAttribute("documentoNombre");
%>
<head>
<link href="<%=request.getContextPath()%>/admision.css" rel="STYLESHEET" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/prototype-1.6.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/iframeResizer.contentWindow.min.js"></script>
<script type="text/javascript">
	function guardar(){
		if($("archivo").value != ""){
			$("btnGuardar").disabled = true;
			$("btnGuardar").value = "Guardando...";
			document.formaEnviar.submit();
		}else
			alert("Selecciona el archivo a subir");
	}
</script>
</head>
<body>
<table class="goback">
	<tr>
		<td><a href="documentos">&lsaquo;&lsaquo; <spring:message code='adm.Regresar'/></a></td>
	</tr>
</table>
<table width="80%" align="center">
	<tr>
		<td class="title"><spring:message code='documentos.subirArchivo.EnviaDocumento'/></td>
	</tr>
	<tr>
		<td class="title2"><%=documentoNombre%></td>
	</tr>
</table>
<br>
<form name="formaEnviar" id="formaEnviar" enctype="multipart/form-data" action="guardararchivo?documentoId=<%=documentoId%>" method="post">
<table width="80%" align="center">
	<tr>
		<td><spring:message code='documentos.subirArchivo.ElijeArchivo'/></td>
	</tr>
	<tr>
		<td><input type="file" id="archivo" name="archivo" /></td>
	</tr>
	<tr>
		<td><input type="button" id="btnGuardar" value="Guardar" onclick="guardar();" /></td>
	</tr>
</table>
</form>
</body>
<%@ include file= "../cierra_salomon.jsp" %>