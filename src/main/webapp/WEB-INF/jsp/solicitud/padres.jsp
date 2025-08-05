<%@ page import="java.util.List"%>
<%@ page import="adm.alumno.spring.AdmPadres"%>
<%@ page import="adm.alumno.spring.AdmSolicitud"%>
<%@ page import="adm.alumno.spring.AdmCartaSubir"%>
<%@ page import="adm.parametros.spring.AdmParametros"%>
<%@ page import="adm.catalogo.spring.CatPais"%>
<%@ page import="adm.catalogo.spring.CatEstado"%>
<%@ page import="adm.catalogo.spring.CatCiudad"%>
<%@ page import="adm.catalogo.spring.CatReligion"%>

<%@ include file= "../head.jsp"%>
<%@ include file= "../menu.jsp"%>
<html>
<head> 
	<script src="<%=request.getContextPath()%>/js/jquery-1.5.1.min.js" type="text/javascript"></script>
  	<script src='<%=request.getContextPath()%>/js/Globo/jquery.tinyTips2.js' type='text/javascript'></script>		
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
		function revisaPadre(){
			if(document.getElementById("nombrePadre").value != ""){
				if(document.getElementById("apellidoPadre").value != ""){
					if(document.getElementById("ocupacionPadre").value != ""){
						if(document.getElementById("nacionalidadPadre").value != "0"){
							if(document.getElementById("religionPadre").value != "0"){
								if(document.getElementById("paisHogarPadre").value != "0"){
									if(document.getElementById("estadoHogarPadre").value != "0"){
										if(document.getElementById("ciudadHogarPadre").value != "0"){
											if(document.getElementById("telefonoPadre").value != "" && document.getElementById("telefonoPadre").value.length >= 8 ){
												return true;
											}else{
												alert("Enter a valid Father's Phone Number (8 digits)");
												document.getElementById("telefonoPadre").focus();												
											}
										}else{
											alert("Father's Home District is required");
											document.getElementById("ciudadHogarPadre").focus();				
										}
									}else{
										alert("Father's Home Province is required");
										document.getElementById("estadoHogarPadre").focus();						
									}
								}else{
									alert("Father's Home Country is required");
									document.getElementById("paisHogarPadre").focus();
								}
							}
							else{
								alert("Father's religion is required");
								document.getElementById("religionPadre").focus();
							}
						}
						else{
							alert("Fathers nationality is required");
							document.getElementById("nacionalidadPadre").focus();
						}
					}
					else{
						alert("Father's occupation is required");
						document.getElementById("ocupacionPadre").focus();
					}
				}
				else{
					alert("Father's surname required");
					document.getElementById("apellidoPadre").focus();	
				}
			}
			else{
				alert("Father's name is required");
				document.getElementById("nombrePadre").focus();
			}
			return false;
		}

		function revisaMadre(){
			if(document.getElementById("nombreMadre").value != ""){
				if(document.getElementById("apellidoMadre").value != ""){
					if(document.getElementById("ocupacionMadre").value != ""){
						if(document.getElementById("nacionalidadMadre").value != "0"){
							if(document.getElementById("religionMadre").value != "0"){
								if(document.getElementById("paisHogarMadre").value != "0"){
									if(document.getElementById("estadoHogarMadre").value != "0"){
										if(document.getElementById("ciudadHogarMadre").value != "0"){
											if(document.getElementById("telefonoMadre").value != "" && document.getElementById("telefonoMadre").value.length >= 8 ){
												return true;
											}else{
												alert("Enter a valid Mother's Phone Number (8 digits)");
												document.getElementById("telefonoMadre").focus();												
											}
										}else{
											alert("Mother's Home District is required");
											document.getElementById("ciudadHogarMadre").focus();				
										}
									}else{
										alert("Mother's Home Province is required");
										document.getElementById("estadoHogarMadre").focus();						
									}
								}else{
									alert("Mother's Home Country is required");
									document.getElementById("paisHogarMadre").focus();
								}
							}
							else{
								alert("Mother's religion is required");
								document.getElementById("religionMadre").focus();
							}
						}
						else{
							alert("Mother nationality is required");
							document.getElementById("nacionalidadMadre").focus();
						}
					}
					else{
						alert("Mother's occupation is required");
						document.getElementById("ocupacionMadre").focus();
					}
				}
				else{
					alert("Mother's surname required");
					document.getElementById("apellidoMadre").focus();	
				}
			}
			else{
				alert("Mother's name is required");
				document.getElementById("nombreMadre").focus();
			}
			return false;
		}
		
		function grabar(){

			var checkboxPadre 	= $("#VivePadre");
			var checkboxMadre 	= $("#ViveMadre");
			var save = 0;

			if (checkboxPadre.is(':checked')) {
				if(revisaPadre()){
					save++;
				}
			}else{
				save++;
			}
			if (checkboxMadre.is(':checked')) {
				if(revisaMadre()){
					save++;
				}
			}else{
				save++;
			}
			
			if(save === 2){
				document.formPadres.submit();
			}
		}

		function refreshEstadosMadre(){				
			jQuery('#estadoHogarMadre').html('<option>Updating</option>');
			var paisId = $('#paisHogarMadre').val();		
			
			$("#estadoHogarMadre").empty();
			jQuery.get('getEstados?paisId='+paisId, function(data){
				jQuery("#estadoHogarMadre").html(data);			
				refreshCuidadesMadre();
			});	
		}

		function refreshCuidadesMadre(){
			jQuery('#ciudadHogarMadre').html('<option>Updating</option>');				
			var paisId 		= $('#paisHogarMadre').val();
			var estadoId 	= $('#estadoHogarMadre').val();				
			jQuery.get('getCiudades?paisId='+paisId+'&estadoId='+estadoId, function(data){
				jQuery("#ciudadHogarMadre").html(data);
			});
		}

		function refreshEstadosPadre(){				
			jQuery('#estadoHogarPadre').html('<option>Updating</option>');
			var paisId = $('#paisHogarPadre').val();		
			
			$("#estadoHogarPadre").empty();
			jQuery.get('getEstados?paisId='+paisId, function(data){
				jQuery("#estadoHogarPadre").html(data);			
				refreshCuidadesPadre();
			});	
		}

		function refreshCuidadesPadre(){
			jQuery('#ciudadHogarPadre').html('<option>Updating</option>');				
			var paisId 		= $('#paisHogarPadre').val();
			var estadoId 	= $('#estadoHogarPadre').val();				
			jQuery.get('getCiudades?paisId='+paisId+'&estadoId='+estadoId, function(data){
				jQuery("#ciudadHogarPadre").html(data);
			});
		}

		$(function() {

			var checkboxPadre 	= $("#VivePadre");
			var hiddenPadre 	= $("#hidden_padre");

			if (checkboxPadre.is(':checked')) {
				hiddenPadre.show();
			} else {
		    	hiddenPadre.hide();
		    	document.getElementById("nombrePadre").value="-";
		    	document.getElementById("apellidoPadre").value="-";
		    	document.getElementById("ocupacionPadre").value="-";
		    	document.getElementById("nacionalidadPadre").value="0";
		    	document.getElementById("religionPadre").value="0";
			}
			  
			checkboxPadre.change(function() {
			    if (checkboxPadre.is(':checked')) {
			    	hiddenPadre.show();
			    } else {
			    	hiddenPadre.hide();
			    	document.getElementById("nombrePadre").value="-";
			    	document.getElementById("apellidoPadre").value="-";
			    	document.getElementById("ocupacionPadre").value="-";
			    	document.getElementById("nacionalidadPadre").value="0";
			    	document.getElementById("religionPadre").value="0";
			    }
			});

			var checkboxMadre = $("#ViveMadre");
			var hiddenMadre = $("#hidden_madre");

			if (checkboxMadre.is(':checked')) {
				hiddenMadre.show();
			} else {
			    hiddenMadre.hide();
			    document.getElementById("nombreMadre").value="-";
			    document.getElementById("apellidoMadre").value="-";
			    document.getElementById("ocupacionMadre").value="-";
			    document.getElementById("nacionalidadMadre").value="0";
			    document.getElementById("religionMadre").value="0";
			}
			  
			checkboxMadre.change(function() {
			    if (checkboxMadre.is(':checked')) {
			    	hiddenMadre.show();
			    } else {
			    	hiddenMadre.hide();
			    	document.getElementById("nombreMadre").value="-";
			    	document.getElementById("apellidoMadre").value="-";
			    	document.getElementById("ocupacionMadre").value="-";
			    	document.getElementById("nacionalidadMadre").value="0";
			    	document.getElementById("religionMadre").value="0";
			    }
			});
		});
	</script>
</head>
<%	
	String folio			= (String) session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	List<String> opciones	= (List<String>)session.getAttribute("Opciones");
	String mensaje			= request.getParameter("Mensaje")==null?"0":request.getParameter("Mensaje");
	boolean existe 			= (boolean) request.getAttribute("existe");	
	String colorGrabar		= existe?"style='color:green'":"style='color:orange'";
	
	AdmPadres admPadres 			= (AdmPadres) request.getAttribute("admPadres");
	AdmSolicitud admSolicitud 		= (AdmSolicitud) request.getAttribute("admSolicitud");	
	AdmCartaSubir admCartaSubir 	= (AdmCartaSubir)request.getAttribute("admCartaSubir");
	AdmParametros admParametros 	= (AdmParametros)request.getAttribute("admParametros");
	List<CatPais> lisPais			= (List<CatPais>) request.getAttribute("lisPais");
	List<CatEstado> lisEstadoPadre		= (List<CatEstado>) request.getAttribute("lisEstadoPadre");
	List<CatCiudad> lisCiudadPadre		= (List<CatCiudad>) request.getAttribute("lisCiudadPadre");
	List<CatEstado> lisEstadoMadre		= (List<CatEstado>) request.getAttribute("lisEstadoMadre");
	List<CatCiudad> lisCiudadMadre		= (List<CatCiudad>) request.getAttribute("lisCiudadMadre");
	List<CatReligion> lisReligion		= (List<CatReligion>) request.getAttribute("lisReligion");	

%>
<body>
	<div class="container-fluid">
		<div class="row">
			<%-- <%@ include file= "../opciones.jsp"%> --%>
			<div class="col-lg-12" style="background-color:white; min-height:calc(100vh - 37px);">
				<div class="d-flex bd-highlight page-header">
					<div class="p-2 w-100 bd-highlight">			
						<h2><i class="fas fa-check-circle" aria-hidden="true" <%=colorGrabar%>></i>&nbsp;<spring:message code="solicitud.padres.InformacionFamiliar"/></h2>
					</div>
					<div class="p-2 flex-shrink-1 bd-highlight">
						<div class="d-flex align-items-center">
				    		<a href="<%=request.getContextPath()%>/solicitud/datos"><i class="fas fa-caret-left fa-3x"></i></a>
				    		&nbsp;<b class="fs-5">3/12</b>&nbsp;
<% 				if(existe){%>
				    		<a href="<%=request.getContextPath()%>/solicitud/otroEstudio"><i class="fas fa-caret-right fa-3x"></i></a>
<%				}else{
					out.print("&nbsp;");
				}
%>	      
						</div>
					</div>				
				</div>
				<div class="alert alert-info" role="alert">
					<spring:message code="solicitud.padres.Mensaje1"/>
				</div>
				<form id="formPadres" name="formPadres" method="post" action="grabarPadres">
					<div class="row">
						<div class="col-md-6">
							<h3><spring:message code="solicitud.padres.InfoPadre"/></h3>
							<input type="checkbox" name="VivePadre" id="VivePadre" value="S" checked style="display:none"><br>
							<div>
								<input type="checkbox" name="padreResponsable" id="padreResponsable" value="S" <%=admPadres.getPadreResponsable().equals("S")?"checked":""%> class="form-check-input">
								<label><spring:message code="solicitud.padres.Responsable"/></label>
								<br><br>
								<label><spring:message code="solicitud.padres.Tipo"/></label><br>
								<select class="form-control" name="padreTipo" id="padreTipo">
									<option value="B" <%=admPadres.getPadreTipo().equals("B")?"selected":""%>>Biological</option>
									<option value="A" <%=admPadres.getPadreTipo().equals("A")?"selected":""%>>Adoptive</option>
									<option value="S" <%=admPadres.getPadreTipo().equals("S")?"selected":""%>>Step</option>
									<option value="G" <%=admPadres.getPadreTipo().equals("G")?"selected":""%>>Guardian</option>
								</select><br>
								<label><spring:message code="solicitud.padres.Nombre"/>:</label>
								<input type="text" class="form-control" id="nombrePadre" name="nombrePadre" value="<%=admPadres.getPadreNombre() %>" size="30" maxlength="50" >
								<br>
								<label><spring:message code="solicitud.padres.Apellido"/>:</label>
								<input type="text" class="form-control" id="apellidoPadre" name="apellidoPadre" value="<%=admPadres.getPadreApellido() %>" size="30" maxlength="50" />
								<br>
								<label><spring:message code="solicitud.padres.Ocupacion"/>:</label>
								<input type="text" class="form-control" id="ocupacionPadre" name="ocupacionPadre" value="<%=admPadres.getPadreOcupacion() %>" size="30" maxlength="40" />
								<br>
								<label><spring:message code="solicitud.padres.Nacionalidad"/>:</label>
								<select class="form-select" id="nacionalidadPadre" name="nacionalidadPadre" onChange="" data-live-search="true">
									<option value="0" selected>SELECT</option>
<%										String nacionalidadId = admParametros.getPaisId();
										if(admPadres.getPadreNacionalidad()!=null && !admPadres.getPadreNacionalidad().equals("")){
											nacionalidadId = admPadres.getPadreNacionalidad();
										}
										for (CatPais pais : lisPais){ %>
											<option value="<%=pais.getPaisId() %>" <%= pais.getPaisId().equals(nacionalidadId) ? "Selected" : "" %>><%=pais.getNacionalidad() %></option>
<%										}%>
								</select>
								<br>
								<label><spring:message code="solicitud.padres.PaisHogar"/>:</label>
								<select class="form-select" id="paisHogarPadre" name="paisHogarPadre" onChange="javascript:refreshEstadosPadre();" data-live-search="true">
									<option value="0" selected>SELECT</option>
<%									for (CatPais pais : lisPais){ %>
								       	<option value="<%=pais.getPaisId() %>" <%=pais.getPaisId().equals(admPadres.getPadrePaisId()) ? "Selected" : "" %>><%=pais.getNombrePais() %></option>
								    <%	}%>
								</select>
								<br>
								<label><spring:message code="solicitud.padres.EstadoHogar"/>:</label>
								<select class="form-select" id="estadoHogarPadre" name="estadoHogarPadre" onChange="javascript:refreshCuidadesPadre();" data-live-search="true">
<% 									for(CatEstado estado : lisEstadoPadre){%>								
									<option value="<%=estado.getEstadoId() %>" <%=estado.getEstadoId().equals(admPadres.getPadreEstadoId()) ? "Selected" : "" %>><%=estado.getNombreEstado() %></option>
									<%  }%>
								</select>
								<br>
								<label><spring:message code="solicitud.padres.CiudadHogar"/>:</label>
								<select class="form-select" id="ciudadHogarPadre" name="ciudadHogarPadre" data-live-search="true">
<% 									for(CatCiudad ciudad : lisCiudadPadre){%>	
									<option value="<%=ciudad.getCiudadId() %>" <%=ciudad.getCiudadId().equals(admPadres.getPadreCiudadId()) ? "Selected" : "" %>><%=ciudad.getNombreCiudad() %></option>
									<%  }%>
								</select>
								<br>
								<label><spring:message code="solicitud.padres.Telefono"/>:</label>
								<input type="text" class="form-control" id="telefonoPadre" name="telefonoPadre" value="<%=admPadres.getPadreTelefono()==null ? "" : admPadres.getPadreTelefono() %>" size="30" maxlength="10" />
								<br>								
								<label><spring:message code="solicitud.padres.Religion"/></label>
								<select class="form-select" id="religionPadre" name="religionPadre" >
									<option value="0" selected>SELECT</option>
<%								String religionId = "0";
								if(admPadres.getPadreReligion()!=null){
									religionId = admPadres.getPadreReligion();
								}
								for (CatReligion religion : lisReligion){ %>
									<option value="<%=religion.getReligionId() %>" <%=religion.getReligionId().equals(religionId) ? "Selected" : "" %>><%=religion.getNombreReligion() %></option>
<%								}%>
								</select>
								<br>
							</div>
						</div>
						<div class="col-md-6">
							<h3><spring:message code="solicitud.padres.InfoMadre"/></h3>
							<input type="checkbox" name="ViveMadre" id="ViveMadre" value="S" checked style="display:none"><br>
							<div>
								<input type="checkbox" name="madreResponsable" id="madreResponsable" value="S" <%=admPadres.getMadreResponsable().equals("S")?"checked":""%> class="form-check-input">
								<label><spring:message code="solicitud.padres.Responsable"/></label>
								<br><br>
								<label><spring:message code="solicitud.padres.Tipo"/></label><br>
								<select class="form-control" name="madreTipo" id="madreTipo">
									<option value="B" <%=admPadres.getMadreTipo().equals("B")?"selected":""%>>Biological</option>
									<option value="A" <%=admPadres.getMadreTipo().equals("A")?"selected":""%>>Adoptive</option>
									<option value="S" <%=admPadres.getMadreTipo().equals("S")?"selected":""%>>Step</option>
									<option value="G" <%=admPadres.getMadreTipo().equals("G")?"selected":""%>>Guardian</option>
								</select><br>
								<label><spring:message code="solicitud.padres.Nombre"/>:</label>
								<input type="text" class="form-control" id="nombreMadre" name="nombreMadre" value="<%=admPadres.getMadreNombre()==null ? "" : admPadres.getMadreNombre() %>" size="30" maxlength="50" />
								<br>
								<label><spring:message code="solicitud.padres.Apellido"/>:</label>
								<input type="text" class="form-control" id="apellidoMadre" name="apellidoMadre" value="<%=admPadres.getMadreApellido()==null ? "" : admPadres.getMadreApellido() %>" size="30" maxlength="50" />
								<br>
								<label><spring:message code="solicitud.padres.Ocupacion"/>:</label>
								<input type="text" class="form-control" id="ocupacionMadre" name="ocupacionMadre" value="<%=admPadres.getMadreOcupacion()==null ? "" : admPadres.getMadreOcupacion() %>" size="30" maxlength="40" />
								<br>
								<label><spring:message code="solicitud.padres.Nacionalidad"/>:</label>
								<select class="form-select" id="nacionalidadMadre" name="nacionalidadMadre" data-live-search="true">
									<option value="0" selected>SELECT</option>
<%									nacionalidadId = admParametros.getPaisId();
									if(admPadres.getMadreNacionalidad()!=null && !admPadres.getMadreNacionalidad().equals("")){
										nacionalidadId = admPadres.getMadreNacionalidad();
									}
									for (CatPais pais : lisPais){ %>
								       	<option value="<%=pais.getPaisId() %>" <%= pais.getPaisId().equals(nacionalidadId) ? "Selected" : "" %>><%=pais.getNacionalidad() %></option>
								    <%	}%>
								</select>
								<br>
								<label><spring:message code="solicitud.padres.PaisHogar"/>:</label>
								<select class="form-select" id="paisHogarMadre" name="paisHogarMadre" onChange="javascript:refreshEstadosMadre();" data-live-search="true">
									<option value="0" selected>SELECT</option>
<%									for (CatPais pais : lisPais){ %>
								       	<option value="<%=pais.getPaisId() %>" <%=pais.getPaisId().equals(admPadres.getMadrePaisId()) ? "Selected" : "" %>><%=pais.getNombrePais() %></option>
								    <%	}%>
								</select>
								<br>
								<label><spring:message code="solicitud.padres.EstadoHogar"/>:</label>
								<select class="form-select" id="estadoHogarMadre" name="estadoHogarMadre" onChange="javascript:refreshCuidadesMadre();" data-live-search="true">
<% 									for(CatEstado estado : lisEstadoMadre){%>
 									<option value="<%=estado.getEstadoId()%>" <%=estado.getEstadoId().equals(admPadres.getMadreEstadoId()) ? "selected" : ""%>><%=estado.getNombreEstado()%></option>
								<% 	}%>
								</select>
								<br>
								<label><spring:message code="solicitud.padres.CiudadHogar"/>:</label>
								<select class="form-select" id="ciudadHogarMadre" name="ciudadHogarMadre" data-live-search="true">
<% 									for(CatCiudad ciudad : lisCiudadMadre){%>	
									<option value="<%=ciudad.getCiudadId() %>" <%=ciudad.getCiudadId().equals(admPadres.getMadreCiudadId()) ? "Selected" : "" %>><%=ciudad.getNombreCiudad() %></option>
									<%  }%>								
								</select>
								<br>
								<label><spring:message code="solicitud.padres.Telefono"/>:</label>
								<input type="text" class="form-control" id="telefonoMadre" name="telefonoMadre" value="<%=admPadres.getMadreTelefono()==null ? "" : admPadres.getMadreTelefono() %>" size="30" maxlength="10" />
								<br>
								<label><spring:message code="solicitud.padres.Religion"/></label>
								<select class="form-select" id="religionMadre" name="religionMadre" >
								<option value="0" selected>SELECT</option>
									<%	religionId = "0";
									if(admPadres.getMadreReligion()!=null){
										religionId = admPadres.getMadreReligion();
									}
									for (CatReligion religion : lisReligion){ %>
										<option value="<%=religion.getReligionId() %>" <%=religion.getReligionId().equals(religionId)?"Selected":"" %>><%=religion.getNombreReligion() %></option>
								     <%	}%>
								</select>
								<br>
							</div>
						</div>
					</div>
<%		if(admSolicitud.getEstado().equals("1")){ %>	
					<button type="button" class="btn alert-info mb-4" onclick="grabar();">
						 <spring:message code="adm.Guardar"/> <span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span>
					</button>
<%		} %>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
<% if (mensaje.equals("1")){ %>
	<div class="alert alert-success"><spring:message code="solicitud.padres.Grabo"/></div>
	<meta http-equiv="refresh" content="1;url=otroEstudio" />
<% }else if(mensaje.equals("2")){ %>
	<div class="alert alert-warning"><spring:message code="solicitud.padres.NoGrabo"/></div>
<% } %>
