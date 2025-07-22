<%@page import="java.util.List"%>
<%@page import="adm.alumno.spring.AdmEstudio"%>
<%@page import="adm.alumno.spring.AdmAcademico"%>
<%@page import="adm.alumno.spring.AdmCartaSubir"%>
<%@page import="adm.alumno.spring.AdmSolicitud"%>
<%@page import="adm.alumno.spring.AdmAcomodo"%>
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
			/* La imagen se fija en la ventana de visualizaci�n para que la altura de la imagen no supere a la del contenido */
			background-attachment: fixed;
			background-repeat: no-repeat;
			/* La imagen de fondo se reescala autom�ticamente con el cambio del ancho de ventana del navegador */
			background-size: cover;	
		} 
		a {text-decoration: none;} 
		a:hover {text-decoration: underline;} 
		.tabla td{padding:5px;}
		
		.tinyTip {
			width: 325px;
			padding: 17px 0px 0px 0px;
			display: block;
			background: url(js/Globo/tinyTip-top.png) 0px 0px no-repeat;
		}
		.tinyTip .content {
			padding: 0px 15px 0px 15px;
			font-size: 14px;
			font-family: "Lucida Sans Unicode";
			color: #010101;
			background: url(js/Globo/tinyTip-content.png) 0px 0px repeat-y;
		}
		.tinyTip .bottom {
			height: 47px;
			background: url(js/Globo/tinyTip-bottom.png) 0px 0px no-repeat;
			font: 0px/0px sans-serif;
		}
		.fa{
			color: black;
		}
		a> .fa{
			color: #337ab7;
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
		
		function Borrar(Id){
			if(confirm("Estas seguro de eliminar el registro")==true){
	  			document.location="borrarEstudio?Id="+Id;
			}
		}

		function grabar(){
			if(revisa()){
				document.frmAcomodo.submit();
			}
		}

		function revisa(){
			if(document.getElementById("acomodoId").value != "0"){
				return true;	
			}else{
				alert("Please select your preferred accommodation");
				document.getElementById("acomodoId").focus();	
			}
		}
		
		function Continuar(){
			
		    if (document.frmotro.otro.value == 1){	    		    
	 			document.location="estudios?Otro=S&Convalida=S";
			}else{			
				document.location="salud";			
			} 
		}
		
		function activarTexto(){
			$("#textoConvalida").show();
		}
		
		function quitarTexto(){
			$("#textoConvalida").hide();
		}	
		
		jQuery(document).ready(function quitarTexto() {
	  		$("#textoConvalida").hide();
	  	});
		
	</script>
</head>
<%	
	String folio 			= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	List<String> opciones	= (List<String>)session.getAttribute("Opciones");
	String Id				= request.getParameter("Id")==null?"0":request.getParameter("Id");
	String otro				= request.getParameter("otro")==null ? "2":request.getParameter("otro");
	boolean existe			= (boolean)request.getAttribute("existe");
	String colorGrabar			= existe?"style='color:green'":"style='color:orange'";
	String mensaje 				= request.getParameter("Mensaje")==null?"":request.getParameter("Mensaje");
	
	AdmSolicitud admSolicitud 		= (AdmSolicitud)request.getAttribute("admSolicitud");
	AdmParametros admParametros 	= (AdmParametros)request.getAttribute("admParametros");
	
	List<AdmEstudio> lisEstudio 	= (List<AdmEstudio>) request.getAttribute("lisEstudio");
%>
<body>
	<div class="container-fluid">
		<div class="row">
			<%-- <%@ include file= "../opciones.jsp"%> --%>
			<div class="col-lg-12" style="background-color:white; min-height:calc(100vh - 37px);">
				<div class="mb-5"> 																				<!-- DIV DE ESTUDIOS PREVIOS-->
					<div class="d-flex bd-highlight page-header">
						<div class="p-2 w-100 bd-highlight">
							<h2><i class="fas fa-check-circle" aria-hidden="true" <%=colorGrabar%>></i>&nbsp;<spring:message code="solicitud.otroEstudio.Antecedentes"/></h2>
						</div>
						<div class="p-2 flex-shrink-1 bd-highlight">
							<div class="d-flex align-items-center">
								<a href="<%=request.getContextPath()%>/solicitud/padres"><i class="fas fa-caret-left fa-3x"></i></a>
								&nbsp;<b class="fs-5">4/12</b>&nbsp;
	<%					if(lisEstudio.size() >= 1){ %>
								<a href="<%=request.getContextPath()%>/solicitud/acomodo"><i class="fas fa-caret-right fa-3x"></i></a>
	<%					}else{
							out.print("&nbsp;");
						}%>	      
							</div>
						</div>
					</div>
	<%		if(admSolicitud.getEstado().equals("1")){ %>
					<a class="btn btn-success text-decoration-none <%=lisEstudio.size()>=1?"disabled":""%>" href="<%=request.getContextPath()%>/solicitud/estudios">Add</a><br><br>
	<% 		}%>
	<%			if(admParametros.getInstitucion().equals("Sonoma") || admParametros.getInstitucion().equals("Pacific Adventist University")){
	%>
					<div class="alert alert-info">
						<b><spring:message code="solicitud.requerimientos.Sonoma"/></b>
					</div>
	<%
				}
	%>
					<form id="frmotro" name="frmotro" action="grabarOtro" method="post">
						<input name="Accion" type="hidden">
						<table class="table table-bordered table-condensed">
							<tr>
								<th width="2%"><spring:message code="solicitud.otroEstudio.Edicion"/></th>
								<th width="3%">No.</th>
								<th width="8%"><spring:message code="solicitud.estudios.UltimoNivel"/></th>
								<th width="8%"><spring:message code="solicitud.otroEstudio.Titulo"/></th>
								<th width="7%"><spring:message code="solicitud.otroEstudio.Institucion"/></th>
							</tr>
	<% 						for(int i=0; i<lisEstudio.size(); i++){
								AdmEstudio tmp = (AdmEstudio)lisEstudio.get(i);%>
							<tr align="center">
								<td>
<%
								if(admSolicitud.getEstado().equals("1")){
%>
									<a href="estudios?Id=<%=tmp.getId()%>&Otro=O&Convalida=<%=tmp.getConvalida()%>"><i class="fas fa-edit"></i> </a> 
										&nbsp;&nbsp;&nbsp;
									<a href="javascript:Borrar('<%=tmp.getId()%>')"><i class="fas fa-times"></i></a> 
<%								}%>
								</td>
								<td width="3%"><%=tmp.getId()%></td>
								<td width="8%">
	<%							if(tmp.getEstudios().equals("1")){%>
									<spring:message code="solicitud.estudios.Primaria"/>
	<%							}else if(tmp.getEstudios().equals("2")){%>
									<spring:message code="solicitud.estudios.Secundaria"/>
	<%							}else if(tmp.getEstudios().equals("3")){%>
									<spring:message code="solicitud.estudios.Bachillerato"/>
	<%							}else if(tmp.getEstudios().equals("4")){%>
									<spring:message code="solicitud.estudios.Certificado"/>
	<%							}else if(tmp.getEstudios().equals("5")){%>
									<spring:message code="solicitud.estudios.Tecnica"/>
	<%							}else if(tmp.getEstudios().equals("6")){%>
									<spring:message code="solicitud.estudios.Licenciatura"/>
	<%							}else if(tmp.getEstudios().equals("7")){%>
									<spring:message code="solicitud.estudios.Especializacion"/>
	<%							}else if(tmp.getEstudios().equals("8")){%>
									<spring:message code="solicitud.estudios.Maestria"/>
	<%							}else if(tmp.getEstudios().equals("9")){%>
									<spring:message code="solicitud.estudios.Doctorado"/>
	<%							}else if(tmp.getEstudios().equals("10")){%>
									<spring:message code="solicitud.estudios.Otro"/>
	<%							}%>
								</td>
								<td width="8%"><%=tmp.getTitulo()%></td>
								<td width="7%"><%=tmp.getInstitucion()%></td>
							</tr>
	<%						}
							
							if(lisEstudio.size()<1){%>
							<tr>
								<td colspan="5" class="text-primary"><b>Click on 'Add' to record your background studies</b></td>
							</tr>
	<%						}%>
						</table>
						<br>
							<input type="hidden" name="otro" value="2"/>
						<div class="row" id="textoConvalida">
							<div class="col-md-12">
								<br>
								<b><spring:message code="solicitud.otroEstudio.ParaConvalidar"/>:</b><spring:message code="solicitud.otroEstudio.MensajeConvalidacion"/><br><br>
								<b><spring:message code="solicitud.otroEstudio.Convalidacion"/>:</b> <spring:message code="solicitud.otroEstudio.MensajeConvalidar"/><br><br>
							</div>
						</div>
					</form>
				</div>
				<div class="d-grid gap-3">
					<div class="p-2"></div>
  					<div class="p-2"></div>
					<div class="p-2"></div>
				</div>
			</div>
		</div>
	</div>
</body>
<% if (mensaje.equals("1")){ %>
	<meta http-equiv="refresh" content="1;url=acomodo" />
<% } %>