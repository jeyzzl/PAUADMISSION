<%@page import="adm.alumno.spring.AdmFamilia"%>

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
	AdmFamilia admFamilia 	= (AdmFamilia) request.getAttribute("admFamilia");
	String mensaje			=  request.getParameter("Mensaje")==null?"0":request.getParameter("Mensaje");
	boolean existeFamilia 	= (boolean) request.getAttribute("existeFamilia");
%>
	<body>
		<div class="container">
<%if(existeFamilia){ %>

<nav class="navbar-right">
  <ul class="pager">
    <li><a href="padres"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span><spring:message code='solicitud.familia.Anterior'/></a></li>
    <li><a href="socioeconomico"><spring:message code='solicitud.familia.Siguiente'/> <span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span></a></li>
  </ul>
</nav>
<% }%>	
			<div class="page-header">
				<h1><spring:message code='solicitud.familia.Titulo'/></h1>
			</div>
			<div class="well">
				<form id="formFamilia" name="formFamilia" method="post" action="grabarFamilia">
				<div class="row">
					<div class="col-md-6">
						<label><spring:message code='solicitud.familia.NumeroHermanos'/></label>
						<input type="text" class="form-control" id="Hermanos" name="Hermanos" value="<%=admFamilia.getHermanos()==null ? "" : admFamilia.getHermanos() %>" size="30" maxlength="50" />
						<br><br>
						<label><spring:message code='solicitud.familia.UbicacionHermanos'/></label>
						<input type="text" class="form-control" id="UbiHermanos" name="UbiHermanos" value="<%=admFamilia.getUbicacion()==null ? "" : admFamilia.getUbicacion() %>" size="30" maxlength="50" />
						<br><br>
						<label><spring:message code='solicitud.familia.PersonasEnCasa'/></label>
						<input type="text" class="form-control" id="NumFamilia" name="NumFamilia" value="<%=admFamilia.getPersonas()==null ? "" : admFamilia.getPersonas() %>" size="30" maxlength="40" />
					</div>					
				</div>
				<br>
				<button type="submit" class="btn alert-info"><spring:message code='adm.Continuar'/><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span>				
				</form>
			</div>
		</div>
	</body>
</html>
<%	if (mensaje.equals("1")){%>
	<table align="center"><tr><td><spring:message code='solicitud.familia.GrabarDatos'/></td></tr></table>
	<meta http-equiv="refresh" content="1;url=socioeconomico" />
<%	}else if(mensaje.equals("2")||mensaje.equals("3")){%>
	<table align="center"><tr><td><spring:message code='solicitud.familia.NoGrabo'/></td></tr></table>
<%	}%>