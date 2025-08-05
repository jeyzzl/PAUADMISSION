<%@page import="adm.alumno.spring.AdmUsuario"%>

<%@ include file= "head.jsp"%>
<%
	boolean existeSolicitud   	= (boolean)	request.getAttribute("existeSolicitud");
	AdmUsuario admUsuario 		= (AdmUsuario) request.getAttribute("admUsuario");
	boolean existeUsuario 		= (boolean) request.getAttribute("existeUsuario");
	boolean sustituir 			= (boolean) request.getAttribute("sustituir");
	int redireccion 			= (int) request.getAttribute("redireccion");
	
	if(sustituir && existeUsuario){ %>
		<meta http-equiv="refresh" content="0;url=inicio" />
<%	}else{ %>
<head>
	<script src="js/jquery-1.5.1.min.js" type="text/javascript"></script>
  	<script src='js/Globo/jquery.tinyTips2.js' type='text/javascript'></script>		
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
		/* Color Daisy Bush #5b3e90; rgb(83,38,163) */
	</style>
	<script>
	    function recargar(){
	     var doc =  window.parent.frames['frameMenu'].document.location; 
	      doc.href=doc.href;
	    }
	 </script>
</head>
<body style="height: 97%;background:url(imagenes/um3.png) no-repeat bottom right; background-color:#F7EBEB;" onLoad=" recargar();"> 
<br>
<div class="container">
<%		if (!existeUsuario){ %>
	<div class="alert alert-danger" role="alert"><spring:message code="valida.AlertaContrasena"/></div>
	<meta http-equiv="refresh" content="1.5;url=login?error=1" /> 
<% 		}else {%>
	<div class="alert alert-light text-center" role="alert" style="opacity:0.7">
		<spring:message code="valida.Bienvenido"/>&nbsp;<%=admUsuario.getNombre()%>
	</div>
	<script type="text/javascript">		    	
			var theHeight;	
			if (document.compatMode && document.compatMode != "BackCompat")
				theHeight = document.documentElement.clientHeight;
			else
				theHeight = document.body.clientHeight;
			var mensaje = document.getElementById("mensaje");
			mensaje.style.position = "relative";
			mensaje.style.top = theHeight/3-30+"px";
	</script>
<%			if(redireccion == 0){%>
		<meta http-equiv="refresh" content="2;url=registro" />
<%			}else{%>
		<meta http-equiv="refresh" content="2;url=inicio" />

<%		
			}
		}
%>
	</div>
</body>
<%	}  %>