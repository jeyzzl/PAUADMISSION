
<head>
<script type="text/javascript">
	function muestraSiguiente(){
		$("boton").innerHTML = '<input type="button" value="Seguir con Documentos >>" onclick="siguiente();" />';
	}
	
	function muestraBotonImprimir(){
		$("boton").innerHTML = '<input type="button" value="Seguir con Documentos >>" onclick="siguiente();" /><br><br>'+
								'<input type="button" value="Imprimir Solicitud" onclick="imprimirSolicitud();" />';
	}
	
	function imprimirSolicitud(){
		if(document.all){
			window.frames[0].document.execCommand('print', false, null);
		}else{
			window.frames[0].print();
		}
	}
	
	function siguiente(){
		location.href = '../documentos/documentos';
	}
</script>
<script type="text/javascript" src="../js/iframeResizer.contentWindow.min.js"></script>
</head>
<body>
	<table width="100%">
		<tr>
			<td width="33%" valign="top">
				<iframe id="solicitud" name="solicitud" src="solicitud" height="1700" width="900" style="background-color: white;"></iframe>
			</td>
			<td width="46%" valign="top" align="right">
				<div id="boton" style="position: fixed;"></div>
				&nbsp;
			</td>
		</tr>
	</table>
</body>
</html>