<%@ page import="adm.alumno.spring.AdmUsuario"%>
<%@ page import="adm.alumno.spring.AdmRecuperar"%>

<%@ include file= "head.jsp"%>
<%@ include file= "menu.jsp"%>
<%	
	AdmUsuario admUsuario 		= (AdmUsuario)request.getAttribute("admUsuario");
    AdmRecuperar admRecuperar 	= (AdmRecuperar)request.getAttribute("admRecuperar");

    String clave                = (String)request.getAttribute("clave");
	
	String mensaje				= request.getParameter("Mensaje")==null?"-":request.getParameter("Mensaje");
%>
<head>
	<link type="text/css" rel="stylesheet" href="bootstrap3/css/bootstrap.min.css">
	<style>
		body{
			background-image: url("imagenes/Biblioteca.png");
			/* Para dejar la imagen de fondo centrada, vertical y horizontalmente */
			background-position: center center;
			/* La imagen se fija en la ventana de visualizaci�n para que la altura de la imagen no supere a la del contenido */
			background-attachment: fixed;
			background-repeat: no-repeat;
			/* La imagen de fondo se reescala autom�ticamente con el cambio del ancho de ventana del navegador */
			background-size: cover;	
		}
	
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
	<script>	
		function revisa(){
			if(document.getElementById("Nueva").value == document.getElementById("Confirmar").value ){
                return true;
            }
			else{
				alert("Passwords do NOT match");
			}
        }

		function grabar(){
			if(revisa()){
				document.forma.submit();
			}
		}	
    </script>

</head>
<body>
<div class="container">
<% if(mensaje.equals("0")){ %><div class="alert alert-info">Error updating your password. Refresh the page and try again.</div><% }%>
<% if(mensaje.equals("1")){ %><div class="alert alert-info">Redirecting...</div><% }%>
<% if(mensaje.equals("2")){ %><div class="alert alert-info">Error updating your password. The password does not match.</div><% }%>
<% if(mensaje.equals("3")){ %><div class="alert alert-info">Error updating your password. The authorization code is incorrect.</div><% }%>
    <div class="mt-5 p-4 container rounded-3" style="width: 65%; background-color:rgba(255, 255, 255, 0.9);">
	<form id="forma" name="forma" action="guardarNuevaContrasena" method="post">
        <input type="hidden" name="Cuenta" id="Cuenta" value="<%=admUsuario.getCuenta()%>">
        <input type="hidden" name="Clave" id="Clave" value="<%=clave%>">
        <label><spring:message code="restablecerContrasena.ContrasenaNueva"/>*:</label>
        <input class= "form-control" type="password" id="Nueva" name="Nueva" maxlength="30" />
                
        <br><label><spring:message code="restablecerContrasena.ConfirmeClave"/>*:</label>
        <input class= "form-control" type="password" id="Confirmar" name="Confirmar" required="required" maxlength="30"/>	
        <br>
        <div align="left">
            <button type="button" class="btn mt-1 alert-info" onclick="grabar();">
                <spring:message code="adm.Guardar"/> <span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span>
            </button>
        </div>
    </form>
    </div>
</div>
</body>