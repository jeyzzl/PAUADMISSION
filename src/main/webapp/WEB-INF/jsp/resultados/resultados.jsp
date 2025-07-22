<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@page import="adm.podium.spring.Examen"%>
<%@page import="adm.podium.spring.ExaArea"%>
<%@page import="adm.alumno.spring.AdmEvaluacionNota"%>
<%@page import="adm.alumno.spring.AdmCartaSubir"%>


<%@ include file= "../head.jsp"%>
<%@ include file= "../menu.jsp"%>

<%
	java.text.DecimalFormat formato = new java.text.DecimalFormat("###,##0.00;-###,##0.00");
	String folio				= (String) session.getAttribute("Folio");
	List<String> opciones		= (List<String>)session.getAttribute("Opciones");
	String examenId				= (String) request.getAttribute("examenId");
	String nombre				= (String) request.getAttribute("nombre");
	String carreraId			= (String) request.getAttribute("carreraId");
	String nombreCarrera 		= (String) request.getAttribute("nombreCarrera");
	boolean mostrar				= false;
	boolean existenResultados	= (boolean) request.getAttribute("existenResultados");
	
	float resLog		= (float)request.getAttribute("resLog");
	float resMat		= (float)request.getAttribute("resMat");
	float resEsp		= (float)request.getAttribute("resEsp");
	float resIng		= (float)request.getAttribute("resIng");
	float resBio		= (float)request.getAttribute("resBio");
	float resFis		= (float)request.getAttribute("resFis");
	float resQui		= (float)request.getAttribute("resQui");
	float resEnsayo		= (float)request.getAttribute("resEnsayo");
	
	float puntosPodium	= resLog+resMat+resEsp+resIng+resBio+resFis+resQui+resEnsayo;
	float puntosOtros	= 0;
	
	boolean paseLog		= (boolean)request.getAttribute("paseLog");
	boolean paseMat		= (boolean)request.getAttribute("paseMat");
	boolean paseEsp		= (boolean)request.getAttribute("paseEsp");
	boolean paseIng		= (boolean)request.getAttribute("paseIng");
	boolean paseBio		= (boolean)request.getAttribute("paseBio");
	boolean paseFis		= (boolean)request.getAttribute("paseFis");
	boolean paseQui		= (boolean)request.getAttribute("paseQui");
	
	boolean tieneLog	= (boolean)request.getAttribute("tieneLog");
	boolean tieneMat	= (boolean)request.getAttribute("tieneMat");
	boolean tieneEsp	= (boolean)request.getAttribute("tieneEsp");
	boolean tieneIng	= (boolean)request.getAttribute("tieneIng");
	boolean tieneBio	= (boolean)request.getAttribute("tieneBio");
	boolean tieneFis	= (boolean)request.getAttribute("tieneFis");
	boolean tieneQui	= (boolean)request.getAttribute("tieneQui");
	
	boolean pregrado	= (boolean)request.getAttribute("pregrado");

	AdmCartaSubir admCartaSubir 	= (AdmCartaSubir)request.getAttribute("admCartaSubir");
	
	List<Examen> lisExamenes						= (List<Examen>) request.getAttribute("lisExamenes");	
	HashMap<String, AdmEvaluacionNota> mapaNotas 	= (HashMap<String,AdmEvaluacionNota>)request.getAttribute("mapaNotas");
	HashMap<Integer,ExaArea> mapaAreas				= (HashMap<Integer,ExaArea>)request.getAttribute("mapaAreas");
	
	String notaTpt 		= "0";
	String fechaTpt		= "";
	if(mapaNotas.containsKey(folio+"7")){
		notaTpt 			= mapaNotas.get(folio+"7").getNota();
		fechaTpt 			= mapaNotas.get(folio+"7").getFecha();
	}
	
	String antecedente 		= "0";
	String fechaAntecedente	= "";
	if(mapaNotas.containsKey(folio+"8")){
		antecedente 		= mapaNotas.get(folio+"8").getNota();
		fechaAntecedente 	= mapaNotas.get(folio+"8").getFecha();
	}
	
	String entrevista 		= "0";
	String fechaEntrevista	= "";
	if(mapaNotas.containsKey(folio+"10")){
		entrevista 			= mapaNotas.get(folio+"10").getNota();
		fechaEntrevista 	= mapaNotas.get(folio+"10").getFecha();
	}
	
	String integral 		= "0";
	String fechaIntegral	= "";
	if(mapaNotas.containsKey(folio+"9")){
		integral 			= mapaNotas.get(folio+"9").getNota();
		fechaIntegral 		= mapaNotas.get(folio+"9").getFecha();
	}
	
	// Validar si se muestran los resultados
	if (carreraId.equals("10301")){
		if( tieneLog && tieneMat && tieneEsp && tieneIng && tieneBio && tieneFis && tieneQui && 
			!notaTpt.equals("0") && !antecedente.equals("0") && !entrevista.equals("0") && !integral.equals("0")){
			mostrar = true;
		}
	}else if (carreraId.equals("10313")||carreraId.equals("10314")){
		if( tieneLog && tieneMat && tieneEsp && tieneIng && tieneBio && tieneFis && tieneQui &&
			!notaTpt.equals("0") && !antecedente.equals("0") && !entrevista.equals("0")){
			mostrar = true;
		}
	}else if (carreraId.equals("10303")){
		if( tieneLog && tieneMat && tieneEsp && tieneIng && tieneBio && tieneFis && tieneQui ){
			mostrar = true;
		}
	}else{
		if( tieneLog && tieneMat && tieneEsp && tieneIng ){
			mostrar = true;
		}
	}
	
	String textoTpt 		= notaTpt;
	if (mostrar==false && !notaTpt.equals("0")){
		textoTpt = "<span class='label label-primary'>"+textoTpt+"</span>";
	}else if (notaTpt.equals("0")){
		textoTpt = "Pendiente"; 
		textoTpt = "<span class='label label-warning'>"+textoTpt+"</span>";
	}else{
		textoTpt = "<span class='label label-default'>"+textoTpt+"</span>";	
	}
	String textoAntecedente = antecedente;
	if (mostrar==false && !antecedente.equals("0")){
		textoAntecedente = "<span class='label label-primary'>"+textoAntecedente+"</span>";
	}else if (antecedente.equals("0")){
		textoAntecedente = "Pendiente";
		textoAntecedente = "<span class='label label-warning'>"+textoAntecedente+"</span>";
	}else{
		textoAntecedente = "<span class='label label-default'>"+textoAntecedente+"</span>";	
	}
	
	String textoEntrevista 	= entrevista;
	if (mostrar==false && !entrevista.equals("0")){
		textoEntrevista = "<span class='label label-primary'>"+textoEntrevista+"</span>";
	}else if (entrevista.equals("0")){
		textoEntrevista = "Pendiente";
		textoEntrevista = "<span class='label label-warning'>"+textoEntrevista+"</span>";
	}else{
		textoEntrevista = "<span class='label label-default'>"+textoEntrevista+"</span>";	
	}
	
	String textoIntegral 	= integral;
	if (mostrar==false && !integral.equals("0")){
		textoIntegral = "<span class='label label-primary'>"+textoIntegral+"</span>";
	}else if (integral.equals("0")){
		textoIntegral = "Pendiente";
		textoIntegral = "<span class='label label-warning'>"+textoIntegral+"</span>";
	}else{
		textoIntegral = "<span class='label label-default'>"+textoIntegral+"</span>";
	}
%>

<head>
<link rel="STYLESHEET" href="/admision.css"  type="text/css">
<style>		
		body{
			background-image: url("<%=request.getContextPath()%>/imagenes/Biblioteca.png");
			</style>
</head>
<body>	
<div class="container-fluid">
		<div class="row">
			<%@ include file= "../opciones.jsp"%>
			
<div class="col-lg-11" style="background-color:white; min-height:calc(100vh - 37px);">
 <div id="content">
	<form name="frmResultados" class="form-inline" action="resultados" method="get">
	<div class="page-header">	  	
		<h2>Evaluaciones<small class="text-muted fs-5">(Folio: <%=folio%> &nbsp; Nombre: <%=nombre%> &nbsp; Carrera: <%=nombreCarrera%> )</small></h2>
		<div class="alert alert-info d-flex align-items-center">
<% 	if(existenResultados){%>			
	  		Elige:&nbsp;
		  	<select name="ExamenId" class="form-select" onchange="javascript:document.frmResultados.submit();" style="width:185px">
<%		for(Examen exa : lisExamenes){ %>		
				<option value="<%=exa.getId()%>" <%=exa.getId()==Integer.parseInt(examenId)?"selected":""%>>Examen <%=exa.getFecha()%></option>
<% 		}%>			
			</select>	
<%		if (lisExamenes.size() >= 1 ){				
			if (pregrado){%>
			<a href="resultadosPDF?ExamenId=<%=examenId%>&Folio=<%=folio%>&CarreraId=<%=carreraId%>" class="btn btn-warning btn-mini"><i class='fas fa-file-pdf' aria-hidden='true'></i> Resultados</a>  	
<%			}else {
				if(resEnsayo > 0){%>
			<a href="resultadosPDF?ExamenId=<%=examenId%>&Folio=<%=folio%>&CarreraId=<%=carreraId%>" class="btn btn-warning btn-mini"><i class='fas fa-file-pdf' aria-hidden='true'></i> Resultados</a>  	
<%				}						
			}						
		}%>	
<% 	}else{%>
		<h2>No tienes resultados aun.</h2>	
<% 	}%>			
		</div>	  	
	</div>
	</form>
	<table class="table table-bordered table-responsive table-sm">
	<thead class="table-dark">
 	<tr>
 		<th width="5%">#1</th>
 		<th width="25%">Examen PODIUM</th>
 		<th class="text-right" width="20%">Preguntas</th> 		
 		<th class="text-right" width="20%">Puntaje mínimo</th>
 		<th class="text-right" width="20%">Puntaje máximo</th>
 		<th class="text-right" width="10%">Puntaje obtenido</th> 			
 	</tr> 
 	</thead>
 	<tbody style="background-color:white">
 	<%
 		float totPreguntas = 0; int totPuntos = 0; int puntosRequeridos = 0; int tiempo = 0;
 		if (mapaAreas.containsKey(1)){
 			if(pregrado) {
	 			totPreguntas 		+= mapaAreas.get(1).getFacilPre()+mapaAreas.get(1).getMedioPre()+mapaAreas.get(1).getDificilPre();
	 			tiempo				+= mapaAreas.get(1).getTiempoPre();
	 			totPuntos 			+= mapaAreas.get(1).getPuntosPre()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(1).getMinimoPre();
			}else {
	 			totPreguntas 		+= mapaAreas.get(1).getFacilPos()+mapaAreas.get(1).getMedioPos()+mapaAreas.get(1).getDificilPos();
	 			tiempo				+= mapaAreas.get(1).getTiempoPos();
	 			totPuntos 			+= mapaAreas.get(1).getPuntosPos()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(1).getMinimoPos();
			}
 		}
 		String color = resLog >= puntosRequeridos?"Aprobado":"Insuficiente";
 	%>
 	<tr>
 		<td>a)</td>
 		<td>Logica</td>
 		<td class="text-right"><%=totPreguntas%></td> 		
 		<td class="text-right"><%=puntosRequeridos%></td>
 		<td class="text-right"><%=totPuntos%></td>
 		<td class="text-right"><%=formato.format(resLog)%></td> 		
 	</tr>
 	<%
 		totPreguntas = 0; totPuntos = 0; puntosRequeridos = 0; tiempo = 0;
 		if (mapaAreas.containsKey(2)){
 			if(pregrado) {
	 			totPreguntas 		+= mapaAreas.get(2).getFacilPre()+mapaAreas.get(2).getMedioPre()+mapaAreas.get(2).getDificilPre();
	 			tiempo				+= mapaAreas.get(2).getTiempoPre();
	 			totPuntos 			+= mapaAreas.get(2).getPuntosPre()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(2).getMinimoPre();
			}else {
	 			totPreguntas 		+= mapaAreas.get(2).getFacilPos()+mapaAreas.get(2).getMedioPos()+mapaAreas.get(2).getDificilPos();
	 			tiempo				+= mapaAreas.get(2).getTiempoPos();
	 			totPuntos 			+= mapaAreas.get(2).getPuntosPos()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(2).getMinimoPos();
			}
 		}
 	%>
 	<tr>
 		<td>b)</td>
 		<td>Matemáticas</td>
 		<td class="text-right"><%=totPreguntas%></td> 		
 		<td class="text-right"><%=puntosRequeridos%></td>
 		<td class="text-right"><%=totPuntos%></td> 		
 		<td class="text-right"><%=formato.format(resMat)%></td>		
 	</tr>
 	
 	<%
 		totPreguntas = 0; totPuntos = 0; puntosRequeridos = 0; tiempo = 0;
 		if (mapaAreas.containsKey(3)){
 			if(pregrado) {
	 			totPreguntas 		= mapaAreas.get(3).getFacilPre()+mapaAreas.get(3).getMedioPre()+mapaAreas.get(3).getDificilPre();
	 			tiempo				+= mapaAreas.get(3).getTiempoPre();
	 			totPuntos 			+= mapaAreas.get(3).getPuntosPre()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(3).getMinimoPre();
			}else {
	 			totPreguntas 		= mapaAreas.get(3).getFacilPos()+mapaAreas.get(3).getMedioPos()+mapaAreas.get(3).getDificilPos();
	 			tiempo				+= mapaAreas.get(3).getTiempoPos();
	 			totPuntos 			+= mapaAreas.get(3).getPuntosPos()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(3).getMinimoPos();
			} 			
 		}
 		
 		if (mapaAreas.containsKey(4)){
 			if(pregrado) {
	 			totPreguntas 		= mapaAreas.get(4).getFacilPre()+mapaAreas.get(4).getMedioPre()+mapaAreas.get(4).getDificilPre();
	 			tiempo				+= mapaAreas.get(4).getTiempoPre();
	 			totPuntos 			+= mapaAreas.get(4).getPuntosPre()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(4).getMinimoPre();
			}else {
	 			totPreguntas 		= mapaAreas.get(4).getFacilPos()+mapaAreas.get(4).getMedioPos()+mapaAreas.get(4).getDificilPos();
	 			tiempo				+= mapaAreas.get(4).getTiempoPos();
	 			totPuntos 			+= mapaAreas.get(4).getPuntosPos()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(4).getMinimoPos();
			}
 		}
 	%>
 	<tr>
 		<td>c)</td>
 		<td>Español</td>
 		<td class="text-right"><%=totPreguntas%></td> 		
 		<td class="text-right"><%=puntosRequeridos%></td>
 		<td class="text-right"><%=totPuntos%></td> 		
 		<td class="text-right"><%=formato.format(resEsp)%></td>
 	</tr>
 	<%
 		totPreguntas = 0; totPuntos = 0; puntosRequeridos = 0; tiempo = 0;
 		if (mapaAreas.containsKey(5)){
 			if(pregrado) {
	 			totPreguntas 		= mapaAreas.get(5).getFacilPre()+mapaAreas.get(5).getMedioPre()+mapaAreas.get(5).getDificilPre();
	 			tiempo				+= mapaAreas.get(5).getTiempoPre();
	 			totPuntos 			+= mapaAreas.get(5).getPuntosPre()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(5).getMinimoPre();
			}else {
	 			totPreguntas 		= mapaAreas.get(5).getFacilPos()+mapaAreas.get(5).getMedioPos()+mapaAreas.get(5).getDificilPos();
	 			tiempo				+= mapaAreas.get(5).getTiempoPos();
	 			totPuntos 			+= mapaAreas.get(5).getPuntosPos()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(5).getMinimoPos();
			}
 		}
 		if (mapaAreas.containsKey(6)){
 			if(pregrado) {
	 			totPreguntas 		= mapaAreas.get(6).getFacilPre()+mapaAreas.get(6).getMedioPre()+mapaAreas.get(6).getDificilPre();
	 			tiempo				+= mapaAreas.get(6).getTiempoPre();
	 			totPuntos 			+= mapaAreas.get(6).getPuntosPre()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(6).getMinimoPre();
			}else {
	 			totPreguntas 		= mapaAreas.get(6).getFacilPos()+mapaAreas.get(6).getMedioPos()+mapaAreas.get(6).getDificilPos();
	 			tiempo				+= mapaAreas.get(6).getTiempoPos();
	 			totPuntos 			+= mapaAreas.get(6).getPuntosPos()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(6).getMinimoPos();
			}
 		}
 	%>
 	<tr>
 		<td>d)</td>
 		<td>Inglés</td>
 		<td class="text-right"><%=totPreguntas%></td>
 		<td class="text-right"><%=puntosRequeridos%></td> 		
 		<td class="text-right"><%=totPuntos%></td> 		 		 		
 		<td class="text-right"><%=formato.format(resIng)%></td> 		
 	</tr>
<%
	if(carreraId.equals("10301")||carreraId.equals("10314")||carreraId.equals("10313")||carreraId.equals("10303")){
 		totPreguntas = 0; totPuntos = 0; puntosRequeridos = 0; tiempo = 0;
 		if (mapaAreas.containsKey(7)){
 			if(pregrado) {
	 			totPreguntas 		+= mapaAreas.get(7).getFacilPre()+mapaAreas.get(7).getMedioPre()+mapaAreas.get(7).getDificilPre();
	 			tiempo				+= mapaAreas.get(7).getTiempoPre();
	 			totPuntos 			+= mapaAreas.get(7).getPuntosPre()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(7).getMinimoPre();
			}else {
	 			totPreguntas 		+= mapaAreas.get(7).getFacilPos()+mapaAreas.get(7).getMedioPos()+mapaAreas.get(7).getDificilPos();
	 			tiempo				+= mapaAreas.get(7).getTiempoPos();
	 			totPuntos 			+= mapaAreas.get(7).getPuntosPos()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(7).getMinimoPos();
			}
 		}
 	%>
 	<tr>
 		<td>e)</td>
 		<td>Biología</td>
<%--  		<td><span class="label <%=paseBio?"label-success":"label-warning"%>"><%=tieneBio?"SI":"NO"%></span></td> --%>
<%--  		<td class="right"><%=tiempo%></td> --%>
 		<td class="text-right"><%=totPreguntas%></td> 		
 		<td class="text-right"><%=puntosRequeridos%></td>
 		<td class="text-right"><%=totPuntos%></td>
 		<td class="text-right"><%=formato.format(resBio)%></td> 		
 	</tr>
 	<%
 		totPreguntas = 0; totPuntos = 0; puntosRequeridos = 0; tiempo = 0;
 		if (mapaAreas.containsKey(8)){
 			if(pregrado) {
	 			totPreguntas 		+= mapaAreas.get(8).getFacilPre()+mapaAreas.get(8).getMedioPre()+mapaAreas.get(8).getDificilPre();
	 			tiempo				+= mapaAreas.get(8).getTiempoPre();
	 			totPuntos 			+= mapaAreas.get(8).getPuntosPre()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(8).getMinimoPre();
			}else {
	 			totPreguntas 		+= mapaAreas.get(8).getFacilPos()+mapaAreas.get(8).getMedioPos()+mapaAreas.get(8).getDificilPos();
	 			tiempo				+= mapaAreas.get(8).getTiempoPos();
	 			totPuntos 			+= mapaAreas.get(8).getPuntosPos()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(8).getMinimoPos();
			}
 		}
 	%>
 	<tr>
 		<td>f)</td>
 		<td>Física</td>
<%--  		<td><span class="label <%=paseFis?"label-success":"label-warning"%>"><%=tieneFis?"SI":"NO"%></span></td> --%>
<%--  		<td class="right"><%=tiempo%></td> --%>
 		<td class="text-right"><%=totPreguntas%></td> 		
 		<td class="text-right"><%=puntosRequeridos%></td>
 		<td class="text-right"><%=totPuntos%></td>		
 		<td class="text-right"><%=formato.format(resFis)%></td>  		
 	</tr>
 	<%
 		totPreguntas = 0; totPuntos = 0; puntosRequeridos = 0; tiempo = 0;
 		if (mapaAreas.containsKey(9)){
 			if(pregrado) {
	 			totPreguntas 		+= mapaAreas.get(9).getFacilPre()+mapaAreas.get(9).getMedioPre()+mapaAreas.get(9).getDificilPre();
	 			tiempo				+= mapaAreas.get(9).getTiempoPre();
	 			totPuntos 			+= mapaAreas.get(9).getPuntosPre()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(9).getMinimoPre();
			}else {
	 			totPreguntas 		+= mapaAreas.get(9).getFacilPos()+mapaAreas.get(9).getMedioPos()+mapaAreas.get(9).getDificilPos();
	 			tiempo				+= mapaAreas.get(9).getTiempoPos();
	 			totPuntos 			+= mapaAreas.get(9).getPuntosPos()*totPreguntas;
	 			puntosRequeridos	+= mapaAreas.get(9).getMinimoPos();
			}
 		}
 	%>
 	<tr>
 		<td>g)</td>
 		<td>Química</td>
<%--  		<td><span class="label <%=paseQui?"label-success":"label-warning"%>"><%=tieneQui?"SI":"NO"%></span></td> --%>
<%--  		<td class="right"><%=tiempo%></td> --%>
 		<td class="text-right"><%=totPreguntas%></td> 		
 		<td class="text-right"><%=puntosRequeridos%></td>
 		<td class="text-right"><%=totPuntos%></td> 		
 		<td class="text-right"><%=formato.format(resQui)%></td>
 	</tr>
 	<tr>
 		<th class="text-right" width="90%" colspan="5" class="right">TOTAL</th>
 		<th class="text-right" width="10%"><%=formato.format(puntosPodium)%></th>
 	</tr> 
<% } %>
	</tbody>
	</table>	
<%	if(carreraId.equals("10301")||carreraId.equals("10314")||carreraId.equals("10313")){ %>
	<table class="table table-bordered table-responsive table-sm">
	<thead class="table-dark">	
		<tr>
	 		<th width="5%">#</th>
	 		<th width="65%">Otras evaluaciones</th>
	 		<th width="20%">Fecha de registro</th>
	 		<th class="text-right" width="10%">Nota</th>
	 	</tr>
	 </thead>	 
	 <tbody style="background-color:white">	
		<tr>
	 		<td>2</td>
	 		<td>Test de personalidad TPT</td>
	 		<td><%=fechaTpt%></td>
	 		<td class="text-right"><%=textoTpt%></td>
	 	</tr>
	 	<tr>
	 		<td>3</td>
	 		<td>Antecedentes académicos</td>
	 		<td><%=fechaAntecedente%></td>
	 		<td class="text-right"><%=textoAntecedente%></td>
	 	</tr>
	 	<tr>
	 		<td>4</td>
	 		<td>Entrevista</td>
	 		<td><%=fechaEntrevista%></td>
	 		<td class="text-right"><%=textoEntrevista%></td>
	 	</tr>	
<%		if(carreraId.equals("10301")){%>
		<tr>
			<td>5</td>
		 	<td>Evaluación Integral(Física,cultural,social y espiritual)</td>
		 	<td><%=fechaIntegral%></td>
		 	<td class="text-right"><%=textoIntegral%></td>
		</tr>
	</tbody>
<%		}%>
<%
		boolean mostrarTotales = false;
		if ( carreraId.equals("10301") && !entrevista.equals("0") && !antecedente.equals("0") && !notaTpt.equals("0") && !integral.equals("0") ){
			mostrarTotales = true;
		}else if ( ( carreraId.equals("10313") || carreraId.equals("10314") ) && !entrevista.equals("0") && !antecedente.equals("0") && !notaTpt.equals("0") ){
			mostrarTotales = true;
		}	
		if(mostrarTotales){
			puntosOtros = Float.parseFloat(entrevista)+Float.parseFloat(antecedente)+Float.parseFloat(notaTpt)+Float.parseFloat(integral);
%>
	<tfoot>
		<tr>
			<th colspan="3" class="text-right">TOTAL</th>
			<th class="text-right"><%=puntosOtros%></th>
		</tr>
	</tfoot>
<%		}%>
	</table>
<%		if(!entrevista.equals("0") && !antecedente.equals("0") && !notaTpt.equals("0")){ %>	
	<div class="alert alert-success text-right">Total de Puntos: <%=puntosPodium+puntosOtros%></div>
<%		} %>		
<% } %>
	<div class="alert alert-success">
		Si tienes alguna duda o pregunta sobre tus resultados, comunicate a la Dirección de Admisiones, al teléfono (826) 2630900 Ext. 1503 o al correo electrónico: ruben_oro@um.edu.mx
	</div>
	</div>
</div>	
</body>