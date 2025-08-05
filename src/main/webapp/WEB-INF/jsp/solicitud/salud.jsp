<%@page import="java.util.List"%>
<%@page import="adm.alumno.spring.AdmSalud"%>
<%@page import="adm.alumno.spring.AdmSolicitud"%>
<%@page import="adm.alumno.spring.AdmCartaSubir"%>
<%@page import="adm.parametros.spring.AdmParametros"%>

<%@ include file= "../head.jsp"%>
<%@ include file= "../menu.jsp"%>

<head> 
	<link rel="STYLESHEET" href="<%=request.getContextPath()%>/admision.css"  type="text/css">
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
	<script>
		function cambiarEnfermedad(){
		
			if(document.frmSalud.Enfermedad.value == 'S'){
				document.getElementById('Enfermedad2').style.display = 'block';
				document.getElementById('EnfermedadDato').style.display = 'block';
			}else{
				document.getElementById('Enfermedad2').style.display = 'none';
				document.getElementById('EnfermedadDato').style.display = 'none';
			}
		}
		function cambiarImpedimento(){
		
			if(document.frmSalud.Impedimento.value == 'S'){
				document.getElementById('Impedimiento2').style.display = 'block';
				document.getElementById('ImpedimientoDato').style.display = 'block';
			}else{
				document.getElementById('Impedimiento2').style.display = 'none';
				document.getElementById('ImpedimientoDato').style.display = 'none';
			}
		}
		
		function grabar(){
			var grabar = "S"; 	
		
			if($("Enfermedad").value == "S" && $("EnfermedadDato").value == "" ){
				alert("Please specify the chronic disease");
				$("EnfermedadDato").focus();
				grabar = "N";	
			}
			
			if($("Impedimento").value == "S" && $("ImpedimentoDato").value == "" ){
				alert("Please specify the physical impairment");
				$("ImpedimentoDato").focus();	
				grabar = "N";
			}
			
			if(grabar=="S"){
				document.frmSalud.submit();
			}
		}
	</script>
</head>
<% 	
	String folio 			= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	List<String> opciones	= (List<String>)session.getAttribute("Opciones");
	String grabo 			= request.getParameter("Grabo")==null?"-":request.getParameter("Grabo");
	AdmSalud salud 			= (AdmSalud)request.getAttribute("admSalud");
	boolean existe			= (boolean)request.getAttribute("existe");	
	String colorGrabar		= existe?"style='color:green'":"style='color:orange'";
	
	AdmSolicitud admSolicitud 	= (AdmSolicitud) request.getAttribute("admSolicitud");	
	AdmCartaSubir admCartaSubir = (AdmCartaSubir)request.getAttribute("admCartaSubir");
	AdmParametros admParametros = (AdmParametros)request.getAttribute("admParametros");
	
%>
<body>
	<div class="container-fluid">
		<div class="row">
			<%-- <%@ include file= "../opciones.jsp"%> --%>
			<div class="col-lg-12" style="background-color:white; min-height:calc(100vh - 37px);">   
				<div class="d-flex bd-highlight page-header">
					<div class="p-2 w-100 bd-highlight">
						<h2><i class="fas fa-check-circle" aria-hidden="true" <%=colorGrabar%>></i>&nbsp;<spring:message code="solicitud.salud.Salud"/></h2>
					</div>
					<div class="p-2 flex-shrink-1 bd-highlight">
						<div class="d-flex align-items-center">
							<a href="<%=request.getContextPath()%>/solicitud/acomodo"><i class="fas fa-caret-left fa-3x"></i></a>
							&nbsp;<b class="fs-5">6/12</b>&nbsp;
<%						if(existe){ %>
		   					<a href="<%=request.getContextPath()%>/solicitud/tutor"><i class="fas fa-caret-right fa-3x"></i></a>
<%						}else{
							out.print("&nbsp;");
						}%>	  
						</div> 
					</div>
				</div>
				<div class="alert alert-info" role="alert">
					<spring:message code="solicitud.salud.InfoSalud"/>
				</div>
				<form id="frmSalud" name="frmSalud" action="grabarSalud" method="post">					
					<div class="col-md-6">
						<label><spring:message code="solicitud.salud.EnfermedadCronica"/></label>
						<select class="form-select" name="Enfermedad" id="Enfermedad" onchange="javascript:cambiarEnfermedad();">
							<option value="N" <% if(salud.getEnfermedad().equals("N")){out.print("selected");} %>>No</option>
							<option value="S" <% if(salud.getEnfermedad().equals("S")){out.print("selected");} %>>Yes</option>
						</select>
						<br>					
						<label id="Enfermedad2" ><spring:message code="solicitud.salud.Especifique"/>:</label>
						<input class="form-control" name="EnfermedadDato" id="EnfermedadDato" type="text" value="<%=salud.getEnfermedadDato()%>" maxlength="100" size="30">
						<br>
						<label><spring:message code="solicitud.salud.ImpedimentoFisico"/></label>
						<select class="form-select" name="Impedimento" id="Impedimento" onchange="javascript:cambiarImpedimento();">
							<option value="N" <% if(salud.getImpedimento().equals("N")){out.print("selected");} %>>No</option>
							<option value="S" <% if(salud.getImpedimento().equals("S")){out.print("selected");} %>>Yes</option>
						</select>
						<br>						
						<label id="Impedimiento2"><spring:message code="solicitud.salud.Especifique"/>:</label>
						<input class="form-control" id="ImpedimientoDato" name="ImpedimentoDato" type="text" value="<%=salud.getImpedimentoDato()%>" maxlength="100" size="30">
						<br>						
					</div>
<%				if(admSolicitud.getEstado().equals("1")){ %>
					<div>
						<button type="button" class="btn alert-info" onclick="grabar();"><spring:message code="adm.Guardar"/></button>
					</div>
<%				}%>
				</form>
			</div>
		</div>
	</div>
	<script>
		cambiarEnfermedad();
		cambiarImpedimento();
	</script>
</body >
<% if (grabo.equals("S")){%>
	<div class="alert alert-success"><spring:message code="solicitud.salud.Grabo"/></div>
	<meta http-equiv="refresh" content="1;url=tutor" />
<% }else if(grabo.equals("N")){%>
	<div class="alert alert-warning"><spring:message code="solicitud.salud.NoGrabo"/></div>
<% }%>