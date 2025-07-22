<%@page import="adm.alumno.spring.AdmSocioeco"%>

<%@ include file= "../head2.jsp"%>
<%@ include file= "../menu2.jsp"%>

<html>
<head>
	<script type="text/javascript" src="../js/iframeResizer.contentWindow.min.js"></script>
	<script type="text/javascript">
	</script>
</head>
<%	
	String folio 			= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	AdmSocioeco admSocio 	= (AdmSocioeco) request.getAttribute("admSocio");
	String mensaje			=  request.getParameter("Mensaje")==null?"0":request.getParameter("Mensaje");
	boolean existeSocioeco 	= (boolean) request.getAttribute("existeSocioeco");
%>
<body>
	<div class="container">
<%if(existeSocioeco){ %>

	<nav class="navbar-right">
	  <ul class="pager">
	    <li><a href="familia"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span><spring:message code='solicitud.socioeconomico.Anterior'/></a></li>
	    <li><a href="tutor"><spring:message code='solicitud.socioeconomico.Siguiente'/> <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></li>
	  </ul>
	</nav>
<% }%>	
	<div class="page-header">
		<h1><spring:message code='solicitud.socioeconomico.Titulo'/></h1>
	</div>
	<div class="well">
		<form id="formSocio" name="formSocio" method="post" action="grabarSocio">
		<div class="row">
			<div class="col-md-6">
				<label><spring:message code='solicitud.socioeconomico.Ba�osCompletos'/></label>
				<input type="text" class="form-control" id="Banos" name="Banos" value="<%=admSocio.getBanos()==null?"0":admSocio.getBanos()%>" size="30" maxlength="2"/>
				<br><br>
				<label><spring:message code='solicitud.socioeconomico.Automoviles'/></label>
				<input type="text" class="form-control" id="Autos" name="Autos" value="<%=admSocio.getAutos()==null?"0":admSocio.getAutos()%>" size="30" maxlength="2"/>
				<br><br>
				<label><spring:message code='solicitud.socioeconomico.Internet'/></label>
				<select name="Internet" in="Internet" class="form-select">
					<option value="S" <%=admSocio.getIngreso().equals("S")?"selected":""%>><spring:message code='solicitud.socioeconomico.SI'/></option>
					<option value="N" <%=admSocio.getIngreso().equals("N")?"selected":""%>><spring:message code='solicitud.socioeconomico.NO'/></option>														
				</select>
				<br><br>
				<label><spring:message code='solicitud.socioeconomico.Trabajan'/></label>
				<input type="text" class="form-control" id="Personas" name="Personas" value="<%=admSocio.getPersonas()==null?"0":admSocio.getPersonas()%>" size="30" maxlength="2"/>
				<br><br>
				<label><spring:message code='solicitud.socioeconomico.Dormir'/></label>
				<input type="text" class="form-control" id="Cuartos" name="Cuartos" value="<%=admSocio.getCuartos()==null?"0":admSocio.getCuartos()%>" size="30" maxlength="2"/>
				<br><br>
				<label><spring:message code='solicitud.socioeconomico.Ingreso'/></label>
				<select name="Ingreso" in="Ingreso" class="form-select">
					<option value="1" <%=admSocio.getIngreso().equals("1")?"selected":""%>>6,820 - 12349</option>
					<option value="2" <%=admSocio.getIngreso().equals("2")?"selected":""%>>12,350 - 16,676</option>
					<option value="3" <%=admSocio.getIngreso().equals("3")?"selected":""%>>16,677 - 21,028</option>
					<option value="4" <%=admSocio.getIngreso().equals("4")?"selected":""%>>21,029 - 25,919</option>
					<option value="5" <%=admSocio.getIngreso().equals("5")?"selected":""%>>25,920 - 31,649</option>
					<option value="6" <%=admSocio.getIngreso().equals("6")?"selected":""%>>31,650 - 38,840</option>
					<option value="7" <%=admSocio.getIngreso().equals("7")?"selected":""%>>38,841 - 49,217</option>
					<option value="8" <%=admSocio.getIngreso().equals("8")?"selected":""%>>49,218 - 67,033</option>
					<option value="9" <%=admSocio.getIngreso().equals("9")?"selected":""%>>67,034 - 160,819</option>
					<option value="10" <%=admSocio.getIngreso().equals("10")?"selected":""%>>160,820 <spring:message code='solicitud.socioeconomico.OSuperior'/></option>							
				</select>						
			</div>					
		</div>
		<br>
			<button type="submit" class="btn alert-info"><spring:message code='adm.Continuar'/> <span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span>				
		</form>
	</div>
	</div>
</body>
</html>
<%	if (mensaje.equals("1")){%>
	<table align="center"><tr><td><spring:message code='solicitud.socioeconomico.Grabo'/></td></tr></table>
	<meta http-equiv="refresh" content="1;url=tutor" />
<%	}else if(mensaje.equals("2")||mensaje.equals("3")){%>
	<table align="center"><tr><td><spring:message code='solicitud.socioeconomico.NoGrabo'/></td></tr></table>
<%	}%>