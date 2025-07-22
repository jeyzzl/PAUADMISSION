<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>

<%@page import="adm.alumno.spring.AdmTutor"%>
<%@page import="adm.alumno.spring.AdmAcademico"%>
<%@page import="adm.alumno.spring.AdmPadres"%>
<%@page import="adm.alumno.spring.AdmSolicitud"%>
<%@page import="adm.alumno.spring.AdmUsuario"%>
<%@page import="adm.parametros.spring.AdmParametros"%>
<%@page import="adm.catalogo.spring.CatPais"%>
<%@page import="adm.catalogo.spring.CatEstado"%>
<%@page import="adm.catalogo.spring.CatCiudad"%>
<%@page import="adm.alumno.spring.AdmCartaSubir"%>

<%@ include file= "../head.jsp"%>
<%@ include file= "../menu.jsp"%>

<html>
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
		a .fa{
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
		function revisa(){
			if(document.getElementById("nombreTutor").value != "" && document.getElementById("nombreTutor").value != "-"){
				if(document.getElementById("calleTutor").value != ""){
					if(document.getElementById("coloniaTutor").value != ""){
						if(document.getElementById("codigoPostalTutor").value != ""){
							if(document.getElementById("nacPais").value != "0"){
								if((document.getElementById("nacPais").value != "0" && document.getElementById("nacEdo").value!="0")){
									if((document.getElementById("nacPais").value != "0" && document.getElementById("nacCiudad").value!="0")){
										if(document.getElementById("telefonoTutor").value!="0" && document.getElementById("telefonoTutor").value.length>=8){
											return true;
										}
										else{
											alert("The phone number should be at least 8 digits");
											document.getElementById("telefonoTutor").focus();
										}
									}
									else{
										alert("District required");
										document.getElementById("nacCiudad").focus();
									}
								}
								else{
									alert("Province required");
									document.getElementById("nacEdo").focus();	
								}
							}
							else{
								alert("Country required");
								document.getElementById("nacPais").focus();
							}
						}
						else{
							alert("Postal Code required");
							document.getElementById("codigoPostalTutor").focus();
						}
					}
					else{
						alert("Neighborhood is required");
						document.getElementById("coloniaTutor").focus();
					}
				}
				else{
					alert("Street required");
					document.getElementById("calleTutor").focus();	
				}
			}
			else{
				alert("Name required");
				document.getElementById("nombreTutor").focus();
			}
			return false;
		}
		
		function grabar(){
			if(revisa()){
				document.formTutor.submit();
			}
		}		
	</script>
</head>
<%	
	String folio 			= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	List<String> opciones	= (List<String>)session.getAttribute("Opciones");	
	boolean disabled 		= false;
	String grabo 			= request.getParameter("Grabo")==null?"-":request.getParameter("Grabo");
	boolean existeTutor 	= (boolean)request.getAttribute("existeTutor");	
	String colorGrabar		= existeTutor?"style='color:green'":"style='color:orange'";

	AdmTutor tutor 				= (AdmTutor)request.getAttribute("tutor");
	AdmAcademico academico 		= (AdmAcademico)request.getAttribute("academico");
	AdmPadres padres 			= (AdmPadres)request.getAttribute("padres");
	AdmUsuario admUsuario 		= (AdmUsuario)request.getAttribute("admUsuario");
	AdmSolicitud admSolicitud 	= (AdmSolicitud)request.getAttribute("admSolicitud");
	AdmCartaSubir admCartaSubir = (AdmCartaSubir)request.getAttribute("admCartaSubir");
	AdmParametros admParametros = (AdmParametros)request.getAttribute("admParametros");

	List<CatPais> lisPais		= (List<CatPais>)request.getAttribute("lisPais");
	List<CatEstado> lisEstados	= (List<CatEstado>)request.getAttribute("lisEstados");
	List<CatCiudad> lisCiudades	= (List<CatCiudad>)request.getAttribute("lisCiudades");

	HashMap<String,CatPais> mapPais 	= (HashMap<String,CatPais>)request.getAttribute("mapPais");
	HashMap<String,CatEstado> mapEstado = (HashMap<String,CatEstado>)request.getAttribute("mapEstado");
	HashMap<String,CatCiudad> mapCiudad = (HashMap<String,CatCiudad>)request.getAttribute("mapCiudad");

	String paisPadre = "";
	String estadoPadre = "";
	String ciudadPadre = "";
%>
<body>
<div class="container-fluid">
	<div class="row">
		<%-- <%@ include file= "../opciones.jsp"%> --%>
		<div class="col-lg-12" style="background-color:white; min-height:calc(100vh - 37px);">
			<div class="d-flex bd-highlight page-header">
				<div class="p-2 w-100 bd-highlight">
					<h2><i class="fas fa-check-circle" aria-hidden="true" <%=colorGrabar%>></i>&nbsp;<spring:message code="solicitud.tutor.InfoTutor"/></h2> <!-- CHECKMARK -->
				</div>
				<div class="p-2 flex-shrink-1 bd-highlight">
					<div class="d-flex align-items-center">
			    		<a href="<%=request.getContextPath()%>/solicitud/salud"><i class="fas fa-caret-left fa-3x"></i></a>
			    		&nbsp;<b class="fs-5">7/12</b>&nbsp;
<%				if(existeTutor){%>
						<a href="<%=request.getContextPath()%>/solicitud/recomendaciones"><i class="fas fa-caret-right fa-3x"></i></a>
<%				} %>					
					</div>
				</div>
			</div>
			<div class="alert alert-info" role="alert">
				<spring:message code="solicitud.tutor.IntroDatos"/>
			</div>		
			<form id="formTutor" name="formTutor" method="post" action="grabaTutor">
				<input type="hidden" name="codigoPostalTutor" id="codigoPostalTutor" value="00000">
				<input type="hidden" name="paisInstitucion" id="paisInstitucion" value="<%=admParametros.getPaisId()%>"> <!-- INPUT PARA TENER EL PAIS DE LA INSTITUCION -->
				<div class="row">		
					<div class="col">		    	
						<label>Guardian Type:</label> <!-- ADVISOR TYPE -->
		       			<select class="form-select" id="tutor" name="tutor" onChange="window.location.href='tutor?tut='+document.formTutor.tutor.value">
<%							String tutorId = request.getParameter("tut")==null ? "0" : request.getParameter("tut");
							if(request.getParameter("tut")==null && (tutor.getTutor()!=null && !tutor.getTutor().equals(""))){
								tutorId = tutor.getTutor();
							}
%>
							<option value="0" <%="0".equals(tutorId) ? "Selected" : "" %>>Father</option>
							<option value="1" <%="1".equals(tutorId) ? "Selected" : "" %>>Mother</option>
							<option value="2" <%="2".equals(tutorId) ? "Selected" : "" %>>Other</option>
			        	</select>
						<br>
			        	<label><spring:message code="solicitud.tutor.ApellidoNombre"/>*:</label>
<%							String nombreTutor = "";
		    				if(tutorId.equals("0") || tutorId.equals("1")){
		    					disabled = true;
			    				if(tutorId.equals("0")){
			    					nombreTutor = padres.getPadreNombre()==null ? "" : padres.getPadreNombre()+" "+padres.getPadreApellido();
			    				}else{
			    					nombreTutor = padres.getMadreNombre()==null ? "" : padres.getMadreNombre()+" "+padres.getMadreApellido();
			    				}
		    				}else{
			    				nombreTutor = tutor.getNombre()==null ? "" : tutor.getNombre();
	    					}
%> 
			    		<input class="form-control" type="text" id="nombreTutor" name="nombreTutor" value="<%=nombreTutor%>" <%=disabled?"readonly":""%> size="30" maxlength="100" /><!-- ADVISOR NAME -->
						<br>
			    		<label><spring:message code="solicitud.tutor.Calle"/>*:</label>
			    		<input class="form-control" type="text" id="calleTutor" name="calleTutor" value="<%=tutor.getCalle() %>" size="30" maxlength="50" /><br> <!-- STREET -->
			    		<label><spring:message code="solicitud.tutor.Numero"/>:</label>
			    		<b>#</b><input class="form-control" type="text" id="numeroTutor" name="numeroTutor" value="<%=tutor.getNumero()==null?"":tutor.getNumero() %>" size="30" maxlength="20" /><br> <!-- STREET # -->
			    		<label><spring:message code="solicitud.tutor.Colonia"/>*:</label>
			    		<input class="form-control" type="text" id="coloniaTutor" name="coloniaTutor" value="<%=tutor.getColonia() %>" size="30" maxlength="40" /><br> <!-- NEIGHBORHOOD -->
					</div>
					<div class="col">
			    		<label><spring:message code="adm.Pais"/>*:</label>
<%					if(tutorId.equals("2")){%>
			      		<select class="form-select " id="nacPais" name="nacPais" data-live-search="true" onchange="javascript:cambioPais();"> <!-- COUNTRY -->
							<option>SELECT</option>
<%						
						for(CatPais pais : lisPais){ %>
	         				<option value="<%=pais.getPaisId() %>" <%=pais.getPaisId().equals(tutor.getPaisId()) ? "Selected" : ""%>><%=pais.getNombrePais() %></option>
<%							} %>
	      				</select>
<% 					}else {
						paisPadre = tutorId.equals("1")?padres.getPadrePaisId():padres.getMadrePaisId();
						String nombrePais = mapPais.get(paisPadre).getNombrePais();
%>
						<input id="nacPais" name="nacPais" value="<%=paisPadre%>" type="hidden"/>
						<input value="<%=nombrePais%>" type="text" class="form-control" readonly/>
<%					}%>
						<br>
						<label><spring:message code="solicitud.tutor.Estado"/>*</label>
<%					if(tutorId.equals("2")){%>
						<select class="form-select" id="nacEdo" name="nacEdo"> <!-- STATE -->
<%					
						for(CatEstado estado : lisEstados){ 
%>
							<option value="<%=estado.getEstadoId() %>" <%=estado.getEstadoId().equals(tutor.getEstadoId()) ? "Selected" : ""%>><%=estado.getNombreEstado()%></option>
<%						}		%>
						</select>
<%					}else{
						estadoPadre = tutorId.equals("1")?padres.getPadreEstadoId():padres.getMadreEstadoId();
						String nombreEstado = mapEstado.get(paisPadre+estadoPadre).getNombreEstado();
%>
						<input id="nacEdo" name="nacEdo" value="<%=estadoPadre%>" type="hidden"/>
						<input value="<%=nombreEstado%>" type="text" class="form-control" readonly/>
<%					}%>
						<br>
						<label><spring:message code="solicitud.tutor.Ciudad"/>*:</label>
<%					if(tutorId.equals("2")){%>
						<select class="form-select" id="nacCiudad" name="nacCiudad" > <!-- CITIY -->
<%						String ciudadId = tutor.getCiudadId().equals("0") ? "1" : tutor.getCiudadId();
						for(CatCiudad ciudad : lisCiudades){
							String elegir = ciudad.getCiudadId().equals(ciudadId)?" selected":"";
%>
							<option value="<%=ciudad.getCiudadId()%>" <%=ciudad.getCiudadId().equals(tutor.getCiudadId())?"Selected":"" %>><%=ciudad.getNombreCiudad()%></option>
<%						}%>					
						</select>
<%					}else{
						ciudadPadre = tutorId.equals("1")?padres.getPadreCiudadId():padres.getMadreCiudadId();
						String nombreCiudad = mapCiudad.get(paisPadre+estadoPadre+ciudadPadre).getNombreCiudad();
%>						
						<input id="nacCiudad" name="nacCiudad" value="<%=ciudadPadre%>" type="hidden"/>
						<input value="<%=nombreCiudad%>" type="text" class="form-control" readonly/>
<%					}%>
						<br>      				
		   	 			<label><spring:message code="adm.Telefono"/>*:</label>
<%					String telefonoTutor = ""; 
					if(tutorId.equals("2")) telefonoTutor = tutor.getTelefono();
					if(tutorId.equals("0")) telefonoTutor = padres.getPadreTelefono();
					if(tutorId.equals("1")) telefonoTutor = padres.getMadreTelefono();
%>
			    		<input class="form-control" type="text" id="telefonoTutor" name="telefonoTutor" value="<%=telefonoTutor%>" size="20" maxlength="70"  onKeypress="if (event.keyCode<48||event.keyCode>57)event.returnValue=false;" <%=!tutorId.equals("2")?"readonly":""%>/> <!-- PHONE NUMBER -->
						<br>
					</div>
				</div>
<%				if(admSolicitud.getEstado().equals("1")){ %>	
		    		<button type="button" class="btn alert-info" onClick="grabar();">
 						<spring:message code="adm.Guardar"/> <span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span>
					</button>
<%				} %>
			</form>
		</div>
	</div>
</div>
</body>
<script>
	jQuery(document).ready(function(){
		$("#nacPais").change(function(){
	    	var pais = $('#nacPais').val();
	    	var parametros = {Accion:'5',paisId:pais};
			$("#nacEdo").load("solicitudAccion",parametros);
			$("#nacCiudad").empty();
			$("#nacCiudad").append("<option value='0'>SELECT</option>");
		});
		
		$("#nacEdo").change(function(){
			var pais 		= $('#nacPais').val();
			var estado 		= $('#nacEdo').val();
	    	var parametros 	= {Accion:'6',paisId:pais,estadoId:estado};
			$("#nacCiudad").load("solicitudAccion",parametros);
		});
	});
</script>	
</html>
<% if (grabo.equals("S")){ %>
	<table align="center"><tr><td><spring:message code="solicitud.tutor.Grabo"/></td></tr></table>
	<meta http-equiv="refresh" content="1;url=recomendaciones" />
<% }else if(grabo.equals("N")){ %>
	<table align="center"><tr><td><spring:message code="solicitud.tutor.NoGrabo"/></td></tr></table>
<% } %>