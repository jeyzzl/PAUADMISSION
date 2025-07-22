<jsp:useBean id="sol" scope="page" class="adm.alumno.AdmSolicitud"/>
<%
	String usSesion 	= session.getAttribute("Usuario")==null?"0":session.getAttribute("Usuario").toString();
	String folSesion	= session.getAttribute("Folio")==null?"0":session.getAttribute("Folio").toString();
	String idioma		= session.getAttribute("Idioma")==null?"es":session.getAttribute("Idioma").toString();	
	boolean existeFolio	= false;	
	if (sol.existeReg(folSesion) && !usSesion.equals("null") && !usSesion.equals("")){
		sol.mapeaRegId(folSesion);
		existeFolio = true;
	}	
%>
<style>
.imagen {
  margin-top: -28px;
  margin-left: 40px;
}
</style>
<nav class="navbar navbar-default" style="background:#3C277D;">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
   	  <a class="navbar-brand" href="../inicial" title="Home Page">
        <img border="0" style="position:relative;top:-7px;" src="<%=request.getContextPath()%>/imagenes/logoUm.png" width="35px"/> <img border="0" src="<%=request.getContextPath()%>/imagenes/um.png" height="26px"/> 
      </a>     
    </div>
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    
      <ul class="nav navbar-nav navbar-right">
        <li>
        	<a href="../inicial" title="Home" style="color:white;"><i class="fa fa-home" aria-hidden="true"></i> <spring:message code='adm.Inicio'/></a>
        </li>
      	<li>
<% 	if (idioma.equals("es")){ %>        
 	    <img src="<%=request.getContextPath()%>/imagenes/esp.png" height="15px" style="position:relative;top:14px;">
<% 	}else if (idioma.equals("en")){ %>
	    <img src="<%=request.getContextPath()%>/imagenes/eng.png" height="15px" style="position:relative;top:14px;">
<% 	}else if (idioma.equals("fr")){ %>
		<img src="<%=request.getContextPath()%>/imagenes/fra.png" height="17px" style="position:relative;top:14px;">
<%	} %>		
		</li>
<%	if(existeFolio){ %>		
        <li>
        	<a href="../registro?usuario=<%=usSesion%>" title="Datos Personales" style="color:white;">
			<%=sol.getNombre()+" "+sol.getApellidoPaterno() %>
			</a>
		</li>        
        <li>
        	<a href="../salir" title="Cerrar Sesi&oacute;n" style="color:white;"><i class="fa fa-power-off" style="color:white;"></i> <spring:message code='adm.Salir'/></a>
    	</li>
<% 	} %>    	
      </ul>
    </div>
  </div>
</nav>