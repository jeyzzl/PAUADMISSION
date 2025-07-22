<%@ page import= "java.util.List"%>
<%@ page import= "java.util.HashMap"%>
<%@ page import= "adm.alumno.spring.AdmDirecto"%>
<%@ page import= "adm.alumno.spring.AdmSolicitud"%>
<%@page import="adm.alumno.spring.AdmCartaSubir"%>

<%@ include file= "../head.jsp"%>
<%@ include file= "../menu.jsp"%>
<head>	
	<link rel="STYLESHEET" href="/admision.css"  type="text/css">
	<script src="js/jquery-1.5.1.min.js" type="text/javascript"></script>
  	<script src='js/Globo/jquery.tinyTips2.js' type='text/javascript'></script>		
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
		function buscaMatricula(){
			var matricula = document.getElementById('Matricula').value; 
			if(matricula.length === 7){
				window.location.replace("ingreso?Matricula="+matricula);
			} 
		}

		function confirmar(){
			var opcion = confirm("Estás seguro de enviar la solicitud");
		    if (opcion == true) {
				document.frmEnviar.submit();
			}
		}
	</script>
</head>
<body>
<%	 
	String folio 			= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	String promedio			= (String)request.getAttribute("promedio");
	String reprobadas		= (String)request.getAttribute("reprobadas");
	String nombre			= (String)request.getAttribute("nombre");
	String mensaje			= (String)request.getAttribute("mensaje");
	
	AdmDirecto admDirecto 			= (AdmDirecto)request.getAttribute("admDirecto");
	AdmSolicitud admSolicitud 		= (AdmSolicitud)request.getAttribute("admSolicitud");
	AdmCartaSubir admCartaSubir 	= (AdmCartaSubir)request.getAttribute("admCartaSubir");
	HashMap<String,String> mapa	 	= (HashMap<String,String>)request.getAttribute("mapa");
	List<String> lista			 	= (List<String>)request.getAttribute("lista");
%>
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
						<span data-bs-toggle="tooltip" data-bs-placement="right" title="Solicitud de admisión"><i class="far fa-file-alt fa-2x"></i><br>Solicitud</span>&nbsp;
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
				<div class="row">
					<div class="col-sm-12">
						<div class="alert alert-info">Estudios de preparatoria <small>( <%=nombre%> )</small></div>
					</div>
					<div class="col-sm-12">
						Para conocer tu promedio registra la matrícula y el programa académico que estudiaste en la escuela preparatoria ICF.
					</div>
<% 					if(mensaje.equals("1")){%>
					<div class="col-sm-12">
						<div class="alert alert-success" role="alert"><strong>Tu solicitud se envio exitosamente. Un Administrador validará si cumples con los primeros 2 requisitos.</strong></div>
					</div>
<% 					}%>
					<form action="ingreso" name="form" method="get">
						<div class="col-sm-2">
							<strong>Matricula:</strong>
<% 						if(admDirecto.getMatricula().equals("0") && admDirecto.getPlanId().equals("-")){%>
							<input onkeyup="buscaMatricula()" id="Matricula" name="Matricula" type="text" class="form-control" placeholder="Matricula" autofocus="autofocus" maxlength="7">
<% 						}else{%>
							<input onkeyup="buscaMatricula()" id="Matricula" name="Matricula" type="text" class="form-control" value="<%=admDirecto.getMatricula()%>" maxlength="7">
<% 						}%>
						</div>
						<div class="col-sm-10">
<% 			if(!admDirecto.getMatricula().equals("0")){%>
							<strong>Plan:</strong>
							<select class="form-control form-select" name="PlanId">
<% 							for(String plan : lista){
								String nombrePlan = "";
								if(mapa.containsKey(plan)){
									nombrePlan = mapa.get(plan);
								}%>
 						 		<option value="<%=plan%>" <%=admDirecto.getPlanId().equals(plan) ? "selected" : ""%>><%=nombrePlan%></option>
<% 							}%>
							</select>
							<br>
						</div>
<% 				if(!admDirecto.getReciente().equals("S") && !admDirecto.getTetra().equals("S") && !admDirecto.getPrepaValido().equals("S") && !admDirecto.getVreValido().equals("S")){%>
<% 					if(!admDirecto.getMatricula().equals("0")){%>
						<div class="col-sm-12">
							<small>*Elige el plan y da clic al botón consultar para continuar</small>
						</div>
<% 					}%>
						<div class="col-sm-12">
							<button type="submit" class="btn btn-primary mb-2">Consultar</button>
						</div>
<% 				}%>
<% 			}%>
					</form>
				</div>
				<div class="row">
<% 			if(!admDirecto.getMatricula().equals("0") && !admDirecto.getPlanId().equals("-")){
		 		if( !reprobadas.equals("0") || Float.parseFloat(promedio) < 90){%>
					<br>
					<div class="col-sm-12">
						<strong><% if (Float.parseFloat(promedio) < 90) out.print("¡El promedio no es igual o superior a 9!");%>&nbsp; <% if (!reprobadas.equals("0")) out.print("Materias no acreditadas:"+reprobadas);%></strong></div>
				</div>
<% 				}%>
	<!-- 			<div class="col-sm-12"> -->
	<!-- 				<div class="alert alert-success" role="alert"><strong>Puedes enviar la solicitud aunque no dispongas de las cartas de recomendaciÃ³n, pero para que sea autorizada debes incluirlas en la siguiente secciÃ³n.</strong></div> -->
	<!-- 			</div> -->
<% 			}%>
				<div class="col-sm-12">				
					<div class="alert alert-info">Requisitos para aplicar al ingreso directo</small></div>
					<table class="table table-sm table-bordered table-responsive">
					<thead class="table-dark">
						<tr>
							<th>Requisito</th>
							<th>Estado</th>
						</tr>
					</thead>
					<tbody style="background-color:white">
						<tr>
							<td><span class="badge bg-dark">1</span> Terminado en los últimos 5 años</td>
							<td>
<% 						if(!admDirecto.getMatricula().equals("0") && !admDirecto.getPlanId().equals("-")){	 						
		 					if(admDirecto.getReciente().equals("N")){%>
								<span class="badge bg-danger">No aprobado</span>
<% 							}else if(admDirecto.getReciente().equals("S")){%>
								<span class="badge bg-success">Aprobado</span>
<%							} 							
	 					}%>
							</td>
						</tr>
						<tr>
							<td><span class="badge bg-dark">2</span> Haber cursado tres tetramestres</td>
							<td>
<% 							if(!admDirecto.getMatricula().equals("0") && !admDirecto.getPlanId().equals("-")){	 						
		 						if(admDirecto.getTetra().equals("N")){%>
								<span class="badge bg-danger">No aprobado</span>
<% 								}else if(admDirecto.getTetra().equals("S")){%>
								<span class="badge bg-success">Aprobado</span>
<%								} 							
	 						}%>
							</td>
						</tr>
						<tr>
							<td><span class="badge bg-dark">3</span> Haber mantenido promedio de 9 sin materias reprobadas</td>
							<td>
<% 							if(!admDirecto.getMatricula().equals("0") && !admDirecto.getPlanId().equals("-")){
		 						if(promedio.equals("") || Float.parseFloat(promedio) < 90){%>
								<span class="badge bg-danger">No aprobado</span>
<% 								}else{%>
								<span class="badge bg-success">Aprobado</span>
<% 								}
	 						}%>
							</td>
						</tr>
						<tr>
							<td><span class="badge bg-dark">4</span> Recomendación de escuela preparatoria</td>
							<td>
								<div class="row">
									<div class="col-sm-5">
<%	 									if(!admDirecto.getMatricula().equals("0") && !admDirecto.getPlanId().equals("-")){
			 								if(reprobadas.equals("0") && Float.parseFloat(promedio) >= 90){%>
										<form name="frmPrepa" enctype="multipart/form-data" action="subirPrepa" method="post">
											<input type="hidden" name="Matricula" value="<%=admDirecto.getMatricula()%>">
											<input type="hidden" name="PlanId" value="<%=admDirecto.getPlanId()%>">
											<input type="file" id="archivo" name="archivo" /><br>
											<button type="submit" id="btnGuardar" class="btn btn-warning"><i class="glyphicon glyphicon-open"></i> Subir</button>
										</form>
<% 											}
		 								}%>
 									</div>
									<div class="col-sm-3">
<% 										if(admDirecto.getRecPrepa() != null){%>
											Enviado <i class="fa fa-check fa-2x" style="color: green;" aria-hidden="true"></i> 
<% 										}%>
 									</div>
									<div class="col-sm-4">
<% 										if(admDirecto.getRecPrepa() != null && admDirecto.getPrepaValido().equals("-")){%>
										<small>Un administrador revizara que este correcto el archivo enviado</small> 
<% 										}else if(admDirecto.getPrepaValido().equals("N")){%>
										<span class="badge bg-danger">No aprobado </span>
<% 										}else if(admDirecto.getPrepaValido().equals("S")){%>
										<span class="badge bg-success">Aprobado</span> <small>Sube la carta de recomendación de la preparatoria</small>
<% 										}%>
 									</div>
 								</div>
							</td>
						</tr>
						<tr>
							<td><span class="badge bg-dark">5</span> Recomendación de VRE</td>
							<td>
								<div class="row">
									<div class="col-sm-5">
<% 									if(!admDirecto.getMatricula().equals("0")){
	 									if(reprobadas.equals("0") && Float.parseFloat(promedio) >= 90){%>
											<form name="frmVRE" enctype="multipart/form-data" action="subirVre" method="post">
												<input type="hidden" name="Matricula" value="<%=admDirecto.getMatricula()%>">
												<input type="hidden" name="PlanId" value="<%=admDirecto.getPlanId()%>">
												<input type="file" id="archivo" name="archivo" /><br>
												<button type="submit" id="btnGuardar" class="btn btn-warning"><i class="glyphicon glyphicon-open"></i> Subir</button>
											</form>
<% 										}
 									}%>
									</div>
									<div class="col-sm-3">
<% 									if(admDirecto.getRecVre() != null){%>
											Enviado <i class="fa fa-check fa-2x" style="color: green;" aria-hidden="true"></i>
<% 									}%>
 									</div>
									<div class="col-sm-4">
<% 										if(admDirecto.getRecVre() != null && admDirecto.getVreValido().equals("-")){%>
										<small>Un administrador revizará que está correcto el archivo enviado</small> 
<% 										}else if(admDirecto.getVreValido().equals("N")){%>
										<span class="badge bg-danger">No aprobado</span> <small>Sube la carta de recomendación de VRE</small>
<% 										}else if(admDirecto.getVreValido().equals("S")){%>
										<span class="badge bg-success">Aprobado</span>
<% 										}%>
 									</div>
 								</div>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
<% 				if(!admDirecto.getMatricula().equals("0") && !admDirecto.getPlanId().equals("-")){
	 				if(promedio.equals("") || Float.parseFloat(promedio) >= 90 && admDirecto.getEnvioSol().equals("N")){%>
				<div class="col-sm-12">
					<div class="alert alert-info">
						<form name="frmEnviar" action="enviar" method="post">
							<input type="hidden" name="Matricula" value="<%=admDirecto.getMatricula()%>">
							<input type="hidden" name="PlanId" value="<%=admDirecto.getPlanId()%>">
<%						if(admSolicitud.getEstado().equals("2")){ %>
							<a href="javascript:confirmar();" class="btn btn-primary">Enviar solicitud</a>
<% 						}%> 
						</form>
					</div>
				</div>
<% 					}
 				}%> 
			</div>
		</div>	
	</div>	
</body>