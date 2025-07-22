<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="adm.alumno.spring.AdmSolicitud"%>
<%@page import="adm.alumno.spring.AdmAcademico"%>
<%@page import="adm.alumno.spring.AdmCartaSubir"%>
<%@page import="adm.banco.spring.AdmBanco"%>
<%@page import="adm.parametros.spring.AdmParametros"%>
<%@page import="adm.catalogo.spring.CatBanco"%>

<%@ include file= "../head.jsp"%>
<%@ include file= "../menu.jsp"%>

<%
	String folio 			= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	List<String> opciones	= (List<String>)session.getAttribute("Opciones");
	String colorImagen 		= "bg-warning";
	String colorArchivo		= "style='color:#ff9633'";	
  	String mensaje        = (String)request.getAttribute("mensaje");
	
	AdmSolicitud admSolicitud		= (AdmSolicitud)request.getAttribute("admSolicitud");
	AdmAcademico admAcademico		= (AdmAcademico)request.getAttribute("admAcademico");	
  	AdmCartaSubir admCartaSubir = (AdmCartaSubir)request.getAttribute("admCartaSubir");
  	AdmBanco admBanco           = (AdmBanco)request.getAttribute("admBanco");
	AdmParametros admParametros 	= (AdmParametros)request.getAttribute("admParametros");
	
  	boolean tieneBanco    = (boolean)request.getAttribute("tieneBanco");
	boolean tienePago 	  = (boolean)request.getAttribute("tienePago");
	String nivelNombre		= (String)request.getAttribute("nivelNombre");
	String carreraNombre	= (String)request.getAttribute("carreraNombre");	 
	String modalidad 		= admAcademico.getModalidad();

	List<CatBanco> lisBancos = (List<CatBanco>)request.getAttribute("lisBancos");

	String grabo 			= request.getParameter("Grabo")==null?"-":request.getParameter("Grabo");
	String colorGrabar			= tieneBanco?"style='color:green'":"style='color:orange'";
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
		function revisaBanco(){
			if(document.getElementById("Banco").value != ""){
				if(document.getElementById("NombreCuenta").value != ""){
					if(document.getElementById("TipoCuenta").value != ""){
						if(document.getElementById("Rama").value != "0"){
							if(document.getElementById("NumeroCuenta").value != "0"){
								return true;
							}
							else{
								alert("Account number is required");
								document.getElementById("NumeroCuenta").focus();
							}
						}
						else{
							alert("Account branch is required");
							document.getElementById("Rama").focus();
						}
					}
					else{
						alert("Account type is required");
						document.getElementById("TipoCuenta").focus();
					}
				}
				else{
					alert("Account Name is required");
					document.getElementById("NombreCuenta").focus();	
				}
			}
			else{
				alert("Bank name is required");
				document.getElementById("Banco").focus();
			}
			return false;
		}

		function grabar(){
			if(revisaBanco()){
				document.frmBanco.submit();
			}
		}
	</script>	
</head>
<body>
  <div class="container-fluid">
    <div class="row">
      <%-- <%@ include file= "../opciones.jsp"%> --%>
      <div class="col-lg-12" style="background-color:white; min-height:calc(100vh - 37px);">
		<div class="d-flex bd-highlight page-header">
			<div class="p-2 w-100 bd-highlight">
				<h2><i class="fas fa-check-circle" aria-hidden="true" <%=colorGrabar%>></i>&nbsp;<spring:message code='banco.titulo'/></h2>
			</div>
			<div class="p-2 flex-shrink-1 bd-highlight">
				<div class="d-flex align-items-center">
					<a href="<%=request.getContextPath()%>/solicitud/recomendaciones"><i class="fas fa-caret-left fa-3x"></i></a>
					&nbsp;<b class="fs-5">9/12</b>&nbsp;
<%						if(tieneBanco){%>
					<a href="<%=request.getContextPath()%>/documentos/documentos"><i class="fas fa-caret-right fa-3x"></i></a>
<% 						}else{
					out.print("&nbsp;");
				}%>
				</div>
			</div>
		</div>
        <div class="alert alert-info" role="alert">
          <spring:message code='banco.instrucciones'/>&nbsp;&nbsp;
        </div>
        <form id="frmBanco" name="frmBanco" action="grabarBanco" method="post">
        <div class="row">
			<div class="col-md-6">
				<label><spring:message code="banco.nombre"/>*</label>	<!-- BANK -->
				<select class="form-select" id="Banco" name="Banco">
					<option>SELECT</option>
<%				for(CatBanco banco : lisBancos){%>
					<option value="<%=banco.getNombre()%>"><%=banco.getNombre()%> - <%=banco.getNombreCorto()%></option>
<%				}%>
				</select>
				<br>
				<label><spring:message code="banco.cuenta"/>*</label>	<!-- ACCOUNT NAME -->
				<input  class= "form-control" type="text" id="NombreCuenta" name="NombreCuenta" value="<%=admBanco.getBanco()%>" size="40" maxlength="70" />
				<br>
				<label><spring:message code="banco.tipo"/>*</label>	<!-- ACCOUNT TYPE -->
				<select class="form-select" id="TipoCuenta" name="TipoCuenta">
					<option>SELECT</option>
					<option value="SAVINGS" <%=admBanco.getCuentaTipo().equals("SAVINGS")?"selected":""%>>SAVINGS</option>
					<option value="CHEQUE" <%=admBanco.getCuentaTipo().equals("CHEQUE")?"selected":""%>>CHEQUE</option>
					<option value="TRANSACTION" <%=admBanco.getCuentaTipo().equals("TRANSACTION")?"selected":""%>>TRANSACTION</option>
					<option value="TERM DEPOSIT" <%=admBanco.getCuentaTipo().equals("TERM DEPOSIT")?"selected":""%>>TERM DEPOSIT</option>
				</select>
				<br>
				<%-- <label><spring:message code="banco.codigoSwift"/></label>	<!-- SWIFT CODE -->
				<input  class= "form-control" type="text" id="CodigoSwift" name="CodigoSwift" value="<%=admBanco.getCodigoSwift()==null?"":admBanco.getCodigoSwift()%>" size="40" maxlength="70" />
				<br> --%>
			</div>
			<div class="col-md-6">
        		<label><spring:message code="banco.branch"/>*</label>	<!-- BRANCH -->
				<input  class= "form-control" type="text" id="Rama" name="Rama" value="<%=admBanco.getBancoRama()%>" size="40" maxlength="70" /><br>
				<label><spring:message code="banco.numeroCuenta"/>*</label>	<!-- ACCOUNT NUMBER -->
				<input  class= "form-control" type="text" id="NumeroCuenta" name="NumeroCuenta" value="<%=admBanco.getCuentaNumero()%>" size="40" maxlength="70" /><br>
				<label><spring:message code="banco.numeroBBS"/></label>	<!-- BBS -->
				<input  class= "form-control" type="text" id="NumeroBBS" name="NumeroBBS" value="<%=admBanco.getNumeroBbs()==null?"":admBanco.getNumeroBbs()%>" size="40" maxlength="70" /><br>
			</div>
        </div>
        <br>
<%	if(admSolicitud.getEstado().equals("1")){%>
<%		if(tieneBanco){%>
        <a href="borrarBanco" class="btn alert-info text-decoration-none"><spring:message code="adm.Borrar"/></a>
<%		}%>	
		<button type="button" class="btn alert-info" onclick="grabar();"><spring:message code="adm.Guardar"/></button>&nbsp;
<%	}%>   
      </div>
      </form>
    </div>
  </div>
</body>
<% if (grabo.equals("S")){%>
	<table align="center"><tr><td><spring:message code="solicitud.salud.Grabo"/></td></tr></table>
	<meta http-equiv="refresh" content="1;url=<%=request.getContextPath()%>/documentos/documentos" />
<% }else if(grabo.equals("N")){%>
	<table align="center"><tr><td><spring:message code="solicitud.salud.NoGrabo"/></td></tr></table>
<% }%>