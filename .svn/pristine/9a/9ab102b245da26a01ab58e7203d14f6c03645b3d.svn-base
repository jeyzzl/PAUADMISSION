<%@page import="adm.alumno.spring.Solicitud"%>
<%	
	String folio 			= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	Solicitud solicitud		= (Solicitud) request.getAttribute("solicitud");
	int estado 				= Integer.parseInt(solicitud.getEstado());		
%>
<head>
<link href="../admision.css" rel="STYLESHEET" type="text/css">
<script type="text/javascript" src="../js/iframeResizer.contentWindow.min.js"></script>
</head>
<body>
<table style="margin: 0 auto;">
  <tr><td class="title">Avance del proceso de admisión: </td></tr>
<% 	if (estado == 0){ %>
  <tr><td style="color:black;"><spring:message code='seguimiento.AntesIniciar'/></td></tr>
<% 	}%>
<% 	if (estado >= 1){ %>
  <tr><td style="color:black;"><spring:message code='seguimiento.PasoUno'/></td></tr>
<% 	}%>  
<% 	if (estado >= 2){ %>
  <tr><td style="color:black;"><spring:message code='seguimiento.PasoDosInformacion'/></td></tr>
<% 	}%>
<% 	if (estado >= 4){ %>
  <tr><td style="color:black;"><spring:message code='seguimiento.PasoDosSolicitud'/></td></tr>
<% 	}%>
<% 	if (estado >= 6){ %>
  <tr><td style="color:black;"><spring:message code='seguimiento.PasoTres'/></td></tr>
<% 	}%>
<% 	if (estado >= 7){ %>
  <tr><td align="left" style="color:black;"><spring:message code='seguimiento.PasoTres'/></td></tr>
<% 	}%>
  <tr><td>&nbsp;</td></tr>
  <tr><td class="title"><spring:message code='seguimiento.QueHacer'/></td></tr>
  <tr><td>&nbsp;</td></tr>	
<% 	if (folio==null){%>
  <tr><td style="color:black;"><spring:message code='seguimiento.CrearCuentaAcceso'/></td></tr>
<% 	}%> 
<% 	if(solicitud.getEstado().equals("1")){%>
  <tr><td style="color:black;"><spring:message code='seguimiento.LlenarSolicitud'/></td></tr>
<% 	}%>
<% 	if(solicitud.getEstado().equals("2")){%>
  <tr><td style="color:black;"><spring:message code='seguimiento.EsperaConfirmacion'/></td></tr>
<% 	}%>
<% 	if(solicitud.getEstado().equals("3")){%>
  <tr><td style="color:black;"><spring:message code='SolicitudRechazada'/></td></tr>
<% 	}%>
<% 	if(solicitud.getEstado().equals("4")){%>
  <tr><td style="color:black;"><spring:message code='seguimiento.SolicitudAceptada'/></td></tr>
<% 	}%>
<% 	if(solicitud.getEstado().equals("5")){%>
  <tr><td style="color:black;"><spring:message code='seguimiento.RequisitosGenerales'/></td></tr>
<% 	}%>
<% 	if(solicitud.getEstado().equals("6")){%>
  <tr><td style="color:black;"><spring:message code='seguimiento.EsperaAsignacion'/></td></tr>
<% 	}%>
<% 	if(solicitud.getEstado().equals("7")){%>
  <tr><td style="color:black;"><spring:message code='seguimiento.AceptacionVerificacion'/></td></tr>
<%	}%>
</table> 
<table id="mensaje" style="margin: 0 auto;">
  <tr>
    <td align="left" >
      <font size="2" color="#5A4545"><spring:message code='seguimiento.FraseDuda'/></font>
    </td>
  </tr>
</table>
<script type="text/javascript">		    	
		var theHeight;	
		if (document.compatMode && document.compatMode != "BackCompat")
			theHeight = document.documentElement.clientHeight;
		else
			theHeight = document.body.clientHeight;
		var mensaje = document.getElementById("mensaje");
		mensaje.style.position = "relative";
		mensaje.style.top = theHeight/2-30+"px";
</script>
</body>