<%@ include file= "../head.jsp"%>
<%@ include file= "../menu.jsp"%>

<%@page import="java.util.List"%>
<%@page import="adm.examen.spring.AdmEvento"%>
<%@page import="adm.alumno.spring.AdmCartaSubir"%>
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
<%	
	String folio 					= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	String estado					= (String)request.getAttribute("estado");
	String eventoRes				= (String)request.getAttribute("eventoRes");
	List<AdmEvento> lisEventos		= (List<AdmEvento>)request.getAttribute("lista");
	AdmCartaSubir admCartaSubir 	= (AdmCartaSubir)request.getAttribute("admCartaSubir");
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
				<span data-bs-toggle="tooltip" data-bs-placement="right" title="Solicitud de admisiï¿½n"><i class="far fa-file-alt fa-2x"></i><br>Solicitud</span>&nbsp;
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
			<h2><spring:message code='cita.evento.Eventos'/>
<%				if(estado.equals("0") || estado.equals("1")){ %>
				<small class="text-muted fs-5">( Para reservar una fecha primero debes completar tu solicitud )</small>
<% 				}%>			
			</h2>
		</div>
		<table class="table table-sm table-responsive table-striped">
			<thead>
				<tr>
					<th width="5%">#</th>			
					<th width="5%">Fecha</th>			
					<th width="30%"><spring:message code='cita.evento.Evento'/></th>
					<th width="55%"><spring:message code='cita.evento.Lugar'/></th>
					<th width="10%"><spring:message code='cita.evento.Estado'/></th>
				</tr>
			</thead>
			<tbody style="background-color:white">
<%
	int row = 0;
	for (adm.examen.spring.AdmEvento evento : lisEventos){
		row++;
		String colorReservado = "";
		if (eventoRes.equals(evento.getEventoId())){
			colorReservado = "style='background-color:#33D7FF;'";
		}
		String fecha = evento.getFecha().substring(0,10);
		fecha = fecha.replace("-", "/");
%>
				<tr <%=colorReservado%>>
					<td><%=row%></td>			
					<td><%=fecha%></td>			
					<td>
						<a href="periodo?EventoId=<%=evento.getEventoId()%>"><%=evento.getEventoNombre()%></a>
					</td>
					<td><%=evento.getLugar() %></td>
					<td><%=evento.getEstado().equals("A")?"Activo":"Inactivo"%></td>
				</tr>
			</tbody>		
<%		
	}
%>		
		</table>
	</div>
	</div>
</div>
</body>