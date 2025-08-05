<%@ include file= "../head2.jsp"%>
<html>
	<head>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap4/css/bootstrap.min.css">		
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/popper.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/bootstrap4/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/iframeResizer.contentWindow.min.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div class="container"> 
			<h2>C�mo usar el calendario</h2>
			<div class="alert alert-info"></div>
			<div class="container-fluid d-flex justify-content-around mt-1">
				<div class="card bg-light mb-3" style="max-width: 27rem;">
					<div class="card-header">
						<img src="<%=request.getContextPath()%>/imagenes/pasouno.png">
					</div>
					<div class="card-body">
						<p style="font-size: 14px">
							<em><u>Instrucciones</u></em>
						</p>
						<p class="card-text">En la parte central superior presiona el
						click dos veces para seleccionar el a�o.</p>
					</div>
					<div class="card-footer bg-transparent border-success">
						<span class="badge bg-success" style="font-size:10pt;">Paso 1</span>
					</div>
				</div>
			
				<div class="card bg-light mb-3" style="max-width: 27rem;">
					<div class="card-header">
						<img src="<%=request.getContextPath()%>/imagenes/pasodos.png">
					</div>
					<div class="card-body">
						<p style="font-size: 14px">
							<em><u>Instrucciones</u></em>
						</p>
						<p class="card-text">Despu�s de elegir el a�o, selecciona el mes correspondiente.</p>
					</div>
					<div class="card-footer bg-transparent border-success">
						<span class="badge bg-success" style="font-size:10pt;">Paso 2</span>
					</div>
				</div>
				
				<div class="card bg-light mb-3" style="max-width: 27rem;">
					<div class="card-header">
						<img src="<%=request.getContextPath()%>/imagenes/pasotres.png">
					</div>
					<div class="card-body">
						<p style="font-size: 14px">
							<em><u>Instrucciones</u></em>
						</p>
						<p class="card-text">Una vez seleccionado el mes, elige el d�a correspondiente.</p>
					</div>
					<div class="card-footer bg-transparent border-success">
						<span class="badge bg-success" style="font-size:10pt;">Paso 3</span>
					</div>
				</div>
			</div>
		</div>	
	</body>
</html>