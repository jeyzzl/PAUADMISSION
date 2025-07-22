<%@ include file= "../head.jsp"%>
<%@ include file= "../menu.jsp"%>

<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="adm.examen.spring.AdmReservada"%>
<%@ page import= "adm.alumno.spring.AdmSolicitud"%>
<%@page import="adm.alumno.spring.AdmCartaSubir"%>

<% 	
	String folio 					= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	boolean eventoRes 				= (boolean)request.getAttribute("eventoRes");
	boolean alumnoRes 				= (boolean)request.getAttribute("alumnoRes");
	boolean existePago 				= (boolean)request.getAttribute("existePago");
	String eventoEstado				= (String)request.getAttribute("eventoEstado");
	String alumnoEstado				= (String)request.getAttribute("alumnoEstado");
	
	AdmSolicitud admSolicitud 		= (AdmSolicitud)request.getAttribute("admSolicitud");
	AdmCartaSubir admCartaSubir 	= (AdmCartaSubir)request.getAttribute("admCartaSubir");
	
	String dias[] = new String[]{"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};

	List<adm.examen.spring.AdmPeriodo> lisPeriodos	= (List<adm.examen.spring.AdmPeriodo>)request.getAttribute("lista");
	HashMap<String, String> mapaOcupados			= (HashMap<String,String>)request.getAttribute("mapaOcupados");	
%>
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
						<span data-bs-toggle="tooltip" data-bs-placement="right" title="Solicitud de admisi�n"><i class="far fa-file-alt fa-2x"></i><br>Solicitud</span>&nbsp;
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
				<div class="page-header">
					<h1><spring:message code='adm.Periodos'/></h1>
				</div>
				<div class="alert alert-info" role="alert">
					<a href="evento" class="btn btn-primary"><i class="fas fa-arrow-left"></i></a>	  
				</div>
<%				if(existePago){ %>
				<span class="fs-4">Para reservar una fecha debes pagar la cuota de admisión</span>
<% 				}%>								
				<table class="table table-sm table-striped table-responsive">
				<thead>
					<tr class="table-dark">
						<th width="10%">#</th>			
						<th width="10%"><spring:message code='cita.periodo.Dia'/></th>
						<th width="20%"><spring:message code='cita.periodo.Inicia'/></th>
						<th width="20%"><spring:message code='cita.periodo.Termina'/></th>
						<th width="10%"><spring:message code='cita.periodo.Cupo'/></th>
						<th width="10%"><spring:message code='cita.periodo.Ocupados'/></th>
						<th width="10%"><spring:message code='cita.periodo.Disponibles'/></th>
						<th width="10%"><spring:message code='cita.periodo.Reservar'/></th>
					</tr>
				</thead>
				<tbody style="background-color:white">	
<%
				int row = 0;
				for (adm.examen.spring.AdmPeriodo periodo : lisPeriodos){
					row++;
					String ocupados = "0";
					if (mapaOcupados.containsKey(periodo.getEventoId()+periodo.getDia()+periodo.getPeriodoId())){
						ocupados = mapaOcupados.get(periodo.getEventoId()+periodo.getDia()+periodo.getPeriodoId());
					}
					int disponibles  = Integer.parseInt(periodo.getCupo())-Integer.parseInt(ocupados);
%>				
					<tr>
						<td><%=row%></td>			
						<td><%=dias[Integer.valueOf(periodo.getDia())-1]%></td>
						<td><%=periodo.getHoraInicio()+":"+periodo.getMinInicio()%></td>
						<td><%=periodo.getHoraFin()+":"+periodo.getMinFin()%></td>
						<td><%=periodo.getCupo()%></td>
						<td><%=ocupados%></td>
						<td><%=disponibles%></td>
						<td>
<% 						if(eventoRes && !alumnoEstado.equals("C")){%>
							<a class="btn btn-warning" href="quitarReserva?EventoId=<%=periodo.getEventoId()%>&PeriodoId=<%=periodo.getPeriodoId()%>&Folio=<%=folio%>"><spring:message code='cita.periodo.QuitarCita'/></a>
<% 						}else if(disponibles > 0 && alumnoRes==false && eventoEstado.equals("A")){%>
<%							if(existePago){%>
							<a class="btn btn-primary" href="reservada?EventoId=<%=periodo.getEventoId()%>&PeriodoId=<%=periodo.getPeriodoId()%>&Dia=<%=periodo.getDia()%>&Folio=<%=folio%>"><spring:message code='cita.periodo.Reservar'/></a>
<% 							}else{%>
							<span style="color:red"><em>pago pendiente</em><span>
<% 							}%>
<% 						}%>
						</td>
					</tr>		
<%			}%>	
				</tbody>	
				</table>
			</div>
		</div>
	</div>
</body >