<%@ include file= "../con_salomon.jsp"%>
<jsp:useBean id="Ubicacion" scope="page" class="adm.alumno.AdmUbicacion" />
<jsp:useBean id="Solicitud" scope="page" class="adm.alumno.AdmSolicitud" />
<jsp:useBean id="catPais" scope="page" class="adm.catalogo.CatPais"/>
<jsp:useBean id="catPaisU" scope="page" class="adm.catalogo.PaisUtil"/>
<jsp:useBean id="catEstado" scope="page" class="adm.catalogo.CatEstado"/>
<jsp:useBean id="catEstadoU" scope="page" class="adm.catalogo.EstadoUtil"/>
<jsp:useBean id="catCiudad" scope="page" class="adm.catalogo.CatCiudad"/>
<jsp:useBean id="catCiudadU" scope="page" class="adm.catalogo.CiudadUtil"/>


<head>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/iframeResizer.contentWindow.min.js"></script>
<style>
	.next{
		cursor: hand;cursor: pointer;
		background:#3C277D;
		height:32px;
		width: 165px;
		
		border: 4px solid #2C176D;
		border-radius:1.4em;
		
		background: -webkit-gradient(linear, left top, left bottom, from(#644FA5), to(#3C277D));
		background: -webkit-linear-gradient(#644FA5, #3C277D 60%);
		background: -moz-linear-gradient(#644FA5, #3C277D 60%);
		background: -o-linear-gradient(#644FA5, #3C277D 60%);
	}
	.next:hover{
		background:#644FA5;
		background: -webkit-gradient(linear, left top, left bottom, from(#8C77CD), to(#644FA5));
		background: -webkit-linear-gradient(#8C77CD, #644FA5 60%);
		background: -moz-linear-gradient(#8C77CD, #644FA5 60%);
		background: -o-linear-gradient(#8C77CD, #644FA5 60%);
	}
	.image{
		background: white;
		width: 27px;
		height: 26px;
		border-radius:20em;
	}
	.flecha{
		position:relative;
		left: 7px;
		top: 4px;
		border-left: 16px solid #2C176D; 
		border-right: 9px solid transparent; 
		border-bottom: 9px solid transparent; 
		border-top: 9px solid transparent; 
	}
</style>
<link href="<%=request.getContextPath()%>/admision.css" rel="STYLESHEET" type="text/css">
<script type="text/javascript" src="<%=request.getContextPath()%>/js/prototype-1.6.js"></script>
<script type="text/javascript">

	function muestraEstados(objEstado, objCiudad, paisId){
		objEstado = $(objEstado);
		objCiudad = $(objCiudad);
		
		objEstado.innerHTML = '<option>Cargando Estados...</option>';
		objCiudad.innerHTML = '<option>Cargando Ciudades...</option>';
		
		muestraCiudades(objCiudad, paisId, 1);
		
		//URL para estados
		var url = 'solicitudAccion?Accion=5'+
				  '&paisId='+paisId;
		
		new Ajax.Request(url, {
			onSuccess: function(req){
				eval(req.responseText);
			},
			onFailure: function(req){
				objEstado.innerHTML = '<option value="1">Error...</option>';
				objCiudad.innerHTML = '<option value="1">Error...</option>';
				alert("Intentelo de nuevo seleccionando primero cualquier pais y despues seleccionando el pais deseado");
			}
		});
	}
	
	function muestraCiudades(objCiudad, paisId, estadoId){
		objCiudad = $(objCiudad);
		
		objCiudad.innerHTML = '<option>Cargando Ciudades...</option>';
		
		//URL para ciudades
		var url = 'solicitudAccion?Accion=6'+
				  '&paisId='+paisId+
				  '&estadoId='+estadoId;
		
		new Ajax.Request(url, {
			onSuccess: function(req){
				eval(req.responseText);
			},
			onFailure: function(req){
				objCiudad.innerHTML = '<option value="1">Error...</option>';
				alert("Intentelo de nuevo seleccionando primero cualquier estado y despues seleccionando el estado deseado");
			}
		});
	}
	
	function revisa(){
		if(document.getElementById("nacPais").value != "0"){
			if(document.getElementById("nacEdo").value != "0"){
				if(document.getElementById("nacCiudad").value != "0"){
					if(document.getElementById("Postal").value != ""){
						if(document.getElementById("Colonia").value != ""){
							if(document.getElementById("Calle").value != ""){
								if(document.getElementById("Telefono").value != "" && document.getElementById("Telefono").value.length >= 10){									
									return true;
								}
								else{
									alert("El tel�fono es incorrecto, escriba por lo menos 10 d�gitos");
									document.getElementById("Telefono").focus();
								}
							}
							else{
								alert("La calle es requerida");
								document.getElementById("Calle").focus();
							}
						}
						else{
							alert("La colonia es requerida");
							document.getElementById("Colonia").focus();	
						}
					}
					else{
						alert("El c�digo postal es requerido");
						document.getElementById("Postal").focus();
					}
				}
				else{
					alert("La ciudad es requerida");
					document.getElementById("nacCiudad").focus();
				}
			}
			else{
				alert("El estado es requerido");
				document.getElementById("nacEdo").focus();
			}
		}
		else{
			alert("El pa�s es requerido");
			document.getElementById("nacPais").focus();
		}
		
		return false;
	}
	
	function grabar(){
		if(revisa()){
			document.frmDatos.submit();
		}
	}

	
</script>
</head>
<% 
	
	String folio 		= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	String accion		= request.getParameter("Accion")==null?"0":request.getParameter("Accion");
	boolean grabo 		= false; 
	
	if (accion.equals("1")){
		Ubicacion.setFolio(folio);
		Ubicacion.setPaisId(request.getParameter("nacPais"));
		Ubicacion.setEstadoId(request.getParameter("nacEdo"));
		Ubicacion.setCiudadId(request.getParameter("nacCiudad"));
		Ubicacion.setCodigoPostal(request.getParameter("Postal").equals("")?"null":request.getParameter("Postal"));
		Ubicacion.setCalle(request.getParameter("Calle"));
		Ubicacion.setNumero(request.getParameter("Numero"));
		Ubicacion.setTelefono(request.getParameter("Telefono"));
		Ubicacion.setColonia(request.getParameter("Colonia").equals("")?"null":request.getParameter("Colonia"));
		conSalomon.setAutoCommit(false);
		if (Ubicacion.existeReg(conSalomon)){
			if (Ubicacion.updateReg(conSalomon)){
				grabo	= true;
				conSalomon.commit();
			}
		}else{
			if (Ubicacion.insertReg(conSalomon)){
				grabo	= true;
				conSalomon.commit();
			}
		}
		conSalomon.setAutoCommit(true);
	}
	Ubicacion.setFolio(folio);
	if(Ubicacion.existeReg(conSalomon)){
		Ubicacion.mapeaRegId(conSalomon,folio);
	}
	

%>
<body style="height: 97%;background:url(../imagenes/um3.png) no-repeat bottom right fixed;background-color:#F7EBEB;">
<table width="80%" align="center">
  <tr>
    <td align="center" width="15%"><a href="datos"><img src="../imagenes/back.png" border="0" width="40" height="40"></a></td>	    
	<td align="center" width="70%">&nbsp;</td>
	<td align="center" width="15%">
<%	// Verifica si ya grabo sus datos 
	if(accion.equals("1") || Ubicacion.existeReg(conSalomon)){ 
%>
	      <a href="estudios"><img src="../imagenes/next.png" border="0" width="40" height="40"></a>
<%	}else{out.print("&nbsp;");} %>	      
	</td>
  </tr>
</table>
<table width="40%" align="center">
  <tr>
		<td class="title" colspan="3"><spring:message code='solicitud.ubicacion.Paso2'/></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td class="title3" align="justify" colspan="3">
			<spring:message code='solicitud.ubicacion.Domicilio'/>:<br><br><br>
    </td>
  </tr>
</table>
<form id="frmDatos" name="frmDatos" action="ubicacion?folio=<%=folio%>&Accion=1" method="post"> 
<table align="center" width="40%" class="tabla"> 
  <tr>
    <td width="1%">&nbsp;</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td><b><spring:message code='solicitud.ubicacion.Pais'/>:</b></td> 
    <td> 
      <select id="nacPais" name="nacPais" class="form-select" onchange="muestraEstados('nacEdo', 'nacCiudad', this.value);">
<%	String paisId = "91";
	if(Ubicacion.getPaisId()!=null && !Ubicacion.getPaisId().equals(""))
		paisId = Ubicacion.getPaisId();
	ArrayList lisPais	= catPaisU.getListAll(conSalomon, "ORDER BY NOMBRE_PAIS");
	for(int i = 0; i < lisPais.size(); i++){
		adm.catalogo.CatPais pais = (adm.catalogo.CatPais) lisPais.get(i);
%>
               
         <option value="<%=pais.getPaisId() %>" <%=pais.getPaisId().equals(paisId)?"Selected":"" %>><%=pais.getNombrePais() %></option>
                <%
	}
%>            
      </select> 
    </td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td><spring:message code='solicitud.ubicacion.Estado'/></td>
    <td> 
      <select id="nacEdo" name="nacEdo" class="form-select" onchange="muestraCiudades('nacCiudad', $('nacPais').value, $(nacEdo).value);">
        <option value="0"  SELECTED  ><spring:message code='solicitud.ubicacion.SeleccionaEstado'/></option>
<%
    String estadoId = "1";
	if(Ubicacion.getEstadoId()!=null)
		estadoId = Ubicacion.getEstadoId();
	ArrayList lisEstado	= catEstadoU.getLista(conSalomon, paisId, "ORDER BY NOMBRE_ESTADO");
	for(int i = 0; i < lisEstado.size(); i++){
		adm.catalogo.CatEstado estado = (adm.catalogo.CatEstado) lisEstado.get(i);
%>
        <option value="<%=estado.getEstadoId() %>" <%=estado.getEstadoId().equals(estadoId)?"Selected":"" %>><%=estado.getNombreEstado() %></option>
                <%
	}
%>
      </select> 
     </td>
   </tr>
   <tr> 
     <td width="10%">&nbsp;</td>
     <td><spring:message code='solicitud.ubicacion.Ciudad'/>:</td> 
     <td> 
       <select id="nacCiudad" name="nacCiudad" class="form-select">
         <option value="0"  SELECTED  ><spring:message code='solicitud.ubicacion.Ninguna'/></option>
<%
	String ciudadId = "1";
	if(Ubicacion.getCiudadId()!=null)
		ciudadId = Ubicacion.getCiudadId();
	ArrayList lisCiudad	= catCiudadU.getLista(conSalomon, paisId, estadoId, "ORDER BY NOMBRE_CIUDAD");	
	for(int i = 0; i < lisCiudad.size(); i++){
		adm.catalogo.CatCiudad ciudad = (adm.catalogo.CatCiudad) lisCiudad.get(i);
%>
         <option value="<%=ciudad.getCiudadId() %>" <%=ciudad.getCiudadId().equals(ciudadId)?"Selected":"" %>><%=ciudad.getNombreCiudad() %></option>
<%
	}
%>
       </select> 
     </td>
   </tr>
   <tr>
    <td width="1%">&nbsp;</td>
    <td><b><spring:message code='solicitud.ubicacion.Postal'/>:</b></td>
    <td><input type="text" id="Postal" name="Postal" value="<%= (Ubicacion.getCodigoPostal()==null || Ubicacion.getCodigoPostal().equals("null"))?"-":Ubicacion.getCodigoPostal() %>" size="10" maxlength="30" /></td>
   </tr>
   <tr>
    <td width="1%">&nbsp;</td>
    <td><b><spring:message code='solicitud.ubicacion.Colonia'/>:</b></td>
    <td><input type="text" id="Colonia" name="Colonia" value="<%= (Ubicacion.getColonia()==null || Ubicacion.getColonia().equals("null"))?"-":Ubicacion.getColonia() %>" size="20" maxlength="50" /></td>
   </tr>
   <tr>
     <td width="1%">&nbsp;</td>
     <td><b><spring:message code='solicitud.ubicacion.Calle'/>:</b></td>
     <td><input type="text" id="Calle" name="Calle" value="<%=(Ubicacion.getCalle()==null || Ubicacion.getCalle().equals("null"))?"-" : Ubicacion.getCalle() %>" size="20" maxlength="70" />
         <b><spring:message code='solicitud.ubicacion.Numero'/>:</b><input type="text" id="Numero" name="Numero" value="<%=(Ubicacion.getNumero()==null || Ubicacion.getNumero().equals("null"))?"-" : Ubicacion.getNumero() %>" size="3" maxlength="70" />
     </td>
   </tr>
   <tr>
     <td width="1%">&nbsp;</td>
     <td><b><spring:message code='solicitud.ubicacion.Telefono'/>:</b></td>
     <td><input type="text" id="Telefono" name="Telefono" value="<%= (Ubicacion.getTelefono()==null || Ubicacion.getTelefono().equals("null"))?"-" : Ubicacion.getTelefono() %>" size="20" maxlength="70" onKeypress="if (event.keyCode<48||event.keyCode>57)event.returnValue=false;" /></td>
   </tr>   
   <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td width="1%">&nbsp;</td>
    <td colspan="2" align="center">
		<div class="next" onclick="grabar();" >
			<table border=0 align="center">
				<tr>
					<td width="116px"><font size="5" color="white" face="Helvetica"><b><spring:message code='adn.Continuar'/></b></font></td>
					<td>
						<div class="image">
							<div class="flecha"></div>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</td>
  </tr>
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
</form>
</body >

<% if (accion.equals("1") && grabo){%>
	<table align="center"><tr><td><spring:message code='solicitud.ubicacion.Grabo'/></td></tr></table>
	<meta http-equiv="refresh" content="1;url=estudios" />
<%}else if(accion.equals("1") && !grabo){%>
	<table align="center"><tr><td><spring:message code='solicitud.ubicacion.NoGrabo'/></td></tr></table>
<%}%>

<%@ include file= "../cierra_salomon.jsp" %>