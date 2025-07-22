<%@ include file= "../con_salomon.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="solicitud" scope="page" class="adm.alumno.Solicitud"/>
<jsp:useBean id="catPais" scope="page" class="adm.catalogo.CatPais"/>
<jsp:useBean id="catPaisU" scope="page" class="adm.catalogo.PaisUtil"/>
<jsp:useBean id="catEstado" scope="page" class="adm.catalogo.CatEstado"/>
<jsp:useBean id="catEstadoU" scope="page" class="adm.catalogo.EstadoUtil"/>
<jsp:useBean id="catCiudad" scope="page" class="adm.catalogo.CatCiudad"/>
<jsp:useBean id="catCiudadU" scope="page" class="adm.catalogo.CiudadUtil"/>
<jsp:useBean id="catReligion" scope="page" class="adm.catalogo.CatReligion"/>
<jsp:useBean id="catReligionU" scope="page" class="adm.catalogo.ReligionUtil"/>
<jsp:useBean id="catCarrera" scope="page" class="adm.catalogo.CatCarrera"/>
<jsp:useBean id="catCarreraU" scope="page" class="adm.catalogo.CatCarreraUtil"/>
<%@page import="adm.catalogo.*"%>
<%@page import="adm.fecha.Fecha"%>
<html>
<%	
	String folio 			= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	
	ArrayList lisPais 		= new ArrayList();
	ArrayList lisEstado 	= new ArrayList(); 
	ArrayList lisCiudad 	= new ArrayList(); 
	ArrayList lisReligion 	= new ArrayList();
	
	String paisId = "", estadoId = "", ciudadId = "", nacionalidadId = "", religionId = "";
	
	solicitud.mapeaRegId(conSalomon, folio);
%>
<head>
<script type="text/javascript" src="../js/prototype-1.6.js"></script>
<script type="text/javascript" src="../js/popcalendar.js"></script>
<script type="text/javascript"></script>
<script type="text/javascript" src="../js/iframeResizer.contentWindow.min.js"></script>
<link href="print.css" rel="stylesheet" type="text/css" media="print">
<style type="text/css">
	table{
		font-family: arial;
		font-size: 8pt;
		border-spacing: 0;
	}
	
	th{
		font-family: Times;
	}
	
	input{
		font-size: 8pt;
	}
	
	select{
		font-size: 8pt;
	}
	
	.etiqueta{
		background-color: black;
		text-align: center;
		color: white;
	}
	
	.dato{
		border-left: solid 1px black;
		border-top: solid 1px black;
	}
	
	.derecha{
		border-right: solid 1px black;
	}
	
	.apartado{
		padding-top: 4px;
		padding-left: 10px;
	}
	
	.seccion{
		font-size: 9pt;
	}
</style>
</head>
<body>
<form id="forma" name="forma" action="solicitud?Accion=2" method="post">
	<table align="center" width="95%" style="border-spacing: 0px;">
		<tr>
			<td rowspan="8" align="center" >
				<img src="../imagenes/logoUM.jpg" width="100px">
			</td>
			<th align="center">
				<font size="5">Universidad de Montemorelos</font><br>
				<font size="2">Solicitud de Admisi&oacute;n / Admission Form</font>
			</th>
			<td rowspan="8" align="center">
				<p style="width: 100px; height: 133px; border: solid 1px black; vertical-align: middle; font-size: 7pt;">
					<br><br>Pegue una fotografia reciente en este espacio.<br><br>
					Paste a recent photograph in this space.
				</p>
			</td>
		</tr>
		<tr>
			<td align="center">
				<font face="Arial">Si eres o fuiste alumno de la UM, escribe en este espacio tu numero de matr&iacute;cula</font><br>
				<font face="Arial">If you are or where a MU student, write your student ID number in this space.</font>
			</td>
		</tr>
		<tr>
			<td align="center"><p style="border: solid 1px black; width: 100px;"><%=solicitud.getMatricula() %>&nbsp;</p></td>
		</tr>
		<tr>
			<td align="center" class="seccion">
				<b><i>Informaci&oacute;n Personal / Personal Information</i></b>
			</td>
		</tr>
	</table>
	<table align="center" width="95%">
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td colspan="3" class="apartado">1. Nombre / Name</td>
					</tr>
					<tr>
						<td width="33%" class="dato"><%=solicitud.getPerPaterno() %>&nbsp;</td>
						<td width="34%" class="dato"><%=solicitud.getPerMaterno() %>&nbsp;</td>
						<td width="33%" class="dato derecha"><%=solicitud.getPerNombre() %>&nbsp;</td>
					</tr>
					<tr>
						<td class="etiqueta">Apellido Paterno/Last Name</td>
						<td class="etiqueta">Apellido Materno/Mother's Maiden Name</td>
						<td class="etiqueta">Nombre(s)/Name(s)</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td colspan="4" class="apartado">2. Lugar de nacimiento/Place of Birth</td>
					</tr>
					<tr>
						<td width="25%" class="dato">
							<%=CatPais.getNombrePais(conSalomon, solicitud.getNacPais()) %>&nbsp;
						</td>
						<td width="25%" class="dato">
							<%=CatEstado.getNombreEstado(conSalomon, solicitud.getNacPais(), solicitud.getNacEdo()) %>&nbsp;
						</td>
						<td width="25" class="dato">
							<%=CatCiudad.getNombreCiudad(conSalomon, solicitud.getNacPais(), solicitud.getNacEdo(), solicitud.getNacCiudad()) %>&nbsp;
						</td>
						<td width="25%" class="dato derecha">
							<%=CatPais.getNacionalidad(conSalomon, solicitud.getNacNacionalidad()) %>&nbsp;
						</td>
					</tr>
					<tr>
						<td class="etiqueta">Pais/Country</td>
						<td class="etiqueta">Estado/State</td>
						<td class="etiqueta">Ciudad/City</td>
						<td class="etiqueta">Nacionalidad/Nationality</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td colspan="6" class="apartado">3. Fecha de nacimiento / Date of Birth</td>
					</tr>
					<tr>
						<td width="20%" class="dato">
							<%=solicitud.getPerNacimiento() %>&nbsp;
						</td>
						<td width="10%" class="dato">
							<%=solicitud.getEdad(conSalomon) %>&nbsp;
						</td>
						<td width="15%" class="dato">
<%
	switch(solicitud.getPerEdocivil().charAt(0)){
		case 'C':
%>
							Casado&nbsp;
<%
			break;
		case 'S':
%>
							Soltero&nbsp;
<%
			break;
		case 'V':
%>
							Viudo&nbsp;
<%
			break;
		case 'D':
%>
							Divorciado&nbsp;
<%
			break;
	}
%>
						</td>
						<td width="10%" class="dato">
							<%=solicitud.getPerGenero().equals("F")?"Femenino":"Masculino" %>&nbsp;
						</td>
						<td width="30%" class="dato">
							<%=CatReligion.getNombreReligion(conSalomon, solicitud.getPerReligion()) %>&nbsp;
						</td>
						<td width="15%" class="dato derecha">
							<%=solicitud.getPerBautizado().equals("S")?"Si":"No" %>&nbsp;
						</td>
					</tr>
					<tr>
						<td class="etiqueta">Dia/Day&nbsp;Mes/Month&nbsp;A�o/Year</td>
						<td class="etiqueta">Edad/Age</td>
						<td class="etiqueta">Estado Civil/Marital Status</td>
						<td class="etiqueta">Sexo/Sex</td>
						<td class="etiqueta">Religion</td>
						<td class="etiqueta">Bautizado/Baptized</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td colspan="6" class="apartado">4. Domicilio legal permanente para recibir correspondencia / Permanent Mailing Address</td>
					</tr>
					<tr>
						<td>
							<table width="100%">
								<tr>
									<td width="40%" class="dato"><%=solicitud.getPerCalle() %>&nbsp;</td>
									<td width="20%" class="dato"><%=solicitud.getPerNumero() %>&nbsp;</td>
									<td width="40%" class="dato derecha"><%=solicitud.getPerColonia() %>&nbsp;</td>
								</tr>
							
								<tr>
									<td class="etiqueta">Calle/Street</td>
									<td class="etiqueta">Numero/Number</td>
									<td class="etiqueta">Colonia/Zone</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table width="100%" align="center">
								<tr>
									<td width="33%" class="dato">
										<%=CatPais.getNombrePais(conSalomon, solicitud.getPerPais()) %>&nbsp;
									</td>
									<td width="33%" class="dato">
										<%=CatEstado.getNombreEstado(conSalomon, solicitud.getPerPais(), solicitud.getPerEdo()) %>&nbsp;
									</td>
									<td width="34%" class="dato derecha">
										<%=CatCiudad.getNombreCiudad(conSalomon, solicitud.getPerPais(), solicitud.getPerEdo(), solicitud.getPerCiudad()) %>&nbsp;
									</td>
								</tr>
						
								<tr>
									<td class="etiqueta">Pais/Country</td>
									<td class="etiqueta">Estado/State</td>
									<td class="etiqueta">Municipio/City</td>
								</tr>
							</table>
						</td>					
					</tr>
					<tr>
						<td>
							<table width="100%" align="center">
								<tr>
									<td width="20%" class="dato"><%=solicitud.getPerCp() %>&nbsp;</td>
									<td width="20%" class="dato"><%=solicitud.getPerFax() %>&nbsp;</td>
									<td width="20%" class="dato"><%=solicitud.getPerTel() %>&nbsp;</td>
									<td width="40%" class="dato derecha"><%=solicitud.getPerEmail() %>&nbsp;</td>
								</tr>
						
								<tr>
									<td class="etiqueta">Codigo Postal/Zip Code</td>
									<td class="etiqueta">Fax</td>
									<td class="etiqueta">Telefono/Telephone</td>
									<td class="etiqueta">Correo Electronico/E-Mail</td>
								</tr>
							</table>
						</td>	
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>&nbsp;
				
			</td>
		</tr>
		<tr>
			<td align="center" class="seccion">
				<b><i>Informaci&oacute;n acad&eacute;mica / Academia Information</i></b>
			</td>
		</tr>
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td colspan="2" class="apartado">5. Carrera / Programa que desea cursar y fecha de inicio / program you want to study and beginning date</td>
					</tr>
					<tr>
						<td width="60%" class="dato">
							<%=CatCarrera.getNombreCarrera(conSalomon, solicitud.getAcaCarrera()) %>&nbsp;
						</td>
						<td width="40%" class="dato derecha"><%=solicitud.getAcaFecha() %>&nbsp;</td>
					</tr>
					<tr>
						<td class="etiqueta">Nombre de la carrera que deseas estudiar/Name of program you want to study</td>
						<td class="etiqueta">A�o y periodo/Year and Term</td>
					</tr>
				</table>
			</td>
		</tr>		
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td colspan="2" class="apartado">6. Bachillerato al que asisti&oacute; (para solicitantes a carreras universitarias) / High School attended (college applicants)</td>
					</tr>
					<tr>
						<td width="50%" class="dato"><%=solicitud.getBacInstitucion() %>&nbsp;</td>
						<td width="50%" class="dato derecha"><%=solicitud.getBacDireccion() %>&nbsp;</td>
					</tr>
					<tr>
						<td class="etiqueta">Nombre de la instituci&oacute;n/Institution's name</td>
						<td class="etiqueta">Direcci&oacute;n/Address</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td width="34%" class="dato">
							<%=CatPais.getNombrePais(conSalomon, solicitud.getBacPais()) %>&nbsp;
						</td>
						<td width="33%" class="dato">
							<%=CatEstado.getNombreEstado(conSalomon, solicitud.getBacPais(), solicitud.getBacEdo()) %>&nbsp;
						</td>
						<td width="33%" class="dato derecha">
							<%=CatCiudad.getNombreCiudad(conSalomon, solicitud.getBacPais(), solicitud.getBacEdo(), solicitud.getBacCiudad()) %>&nbsp;
						</td>
					</tr>
					<tr>
						<td class="etiqueta">Pais/Country</td>
						<td class="etiqueta">Estado/State</td>
						<td class="etiqueta">Ciudad/City</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td width="50%" class="dato"><%=solicitud.getBacFinicio() %>&nbsp;</td>
						<td width="50%" class="dato derecha"><%=solicitud.getBacFfinal() %>&nbsp;</td>
					</tr>
					<tr>
						<td class="etiqueta">Fecha de Inicio/Beginning Date</td>
						<td class="etiqueta">Fecha de Terminaci&oacute;n/Ending Date</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td colspan="2" class="apartado">7. Carrera Profesional que ha cursado / Bachelor's Degree Earned</td>
					</tr>
					<tr>
						<td width="50%" class="dato"><%=solicitud.getAntInstitucion() %>&nbsp;</td>
						<td width="50%" class="dato derecha"><%=solicitud.getAntDireccion() %>&nbsp;</td>
					</tr>
					<tr>
						<td class="etiqueta">Nombre de la instituci&oacute;n/Institution's name</td>
						<td class="etiqueta">Direcci&oacute;n/Address</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td width="34%" class="dato">
							<%=CatPais.getNombrePais(conSalomon, solicitud.getAntPais()) %>&nbsp;
						</td>
						<td width="33%" class="dato">
							<%=CatEstado.getNombreEstado(conSalomon, solicitud.getAntPais(), solicitud.getAntEdo()) %>&nbsp;
						</td>
						<td width="33%" class="dato derecha">
							<%=CatCiudad.getNombreCiudad(conSalomon, solicitud.getAntPais(), solicitud.getAntEdo(), solicitud.getAntCiudad()) %>&nbsp;
						</td>
					</tr>
					<tr>
						<td class="etiqueta">Pais/Country</td>
						<td class="etiqueta">Estado/State</td>
						<td class="etiqueta">Ciudad/City</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td width="48%" class="dato"><%=solicitud.getAntGrado() %>&nbsp;</td>
						<td width="25%" class="dato"><%=solicitud.getAntFinicio() %>&nbsp;</td>
						<td width="27%" class="dato derecha"><%=solicitud.getAntFfinal() %>&nbsp;</td>
					</tr>
					<tr>
						<td class="etiqueta">Grado Obtenido (o en tramite)/Degree Earned (or in Progress)</td>
						<td class="etiqueta">Fecha de Inicio/Beginning Date</td>
						<td class="etiqueta">Fecha de Terminaci&oacute;n/Ending Date</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr>
						<td class="etiqueta">Obrero/Worker</td>
						<td class="etiqueta">Hijo(a) de Obrero/Son or Daughter of worker</td>
						<td class="etiqueta">C&oacute;nyuge del Obrero/Spuse of worker</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td class="dato">
						</td>
						<td class="dato derecha">
						</td>
					</tr>
					<tr>
						<td class="etiqueta">El Obrero denominacional es mi/The Denominational worker is my</td>
						<td class="etiqueta">El Obrero denominacional es/The Denominational worker is</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table align="center" width="100%">
					<tr>
						<td class="etiqueta">Uni&oacute;n donde est&aacute; el empleado/Union Conference of Employment</td>
						<td class="etiqueta">Asociaci&oacute;n o Instituci&oacute;n donde est&aacute; el empleado/Conference or Institution of employment</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<table align="center" width="95%" cellpadding="8px">
		<tr>
			<td style="font-size: 6pt;" width="50%" valign="top">
			<i>Documentaci&oacute;n de apoyo (se aceptan copias fotost&aacute;ticas para el proceso de admisi&oacute;n) 
			Para poderle dar tr&aacute;mite a esta solicitud es indispensable que envie los siguientes 
			documentos</i> junto con la cuota no reembolsable de admisi&oacute;n $100.00 MN. Para aquellas personas 
			que han realizado estudios fuera de M&eacute;xico y desean iniciar una carrera universitaria, deber&aacute;n 
			presentar un certificado o transcipci&oacute;n de estudios completos. EL GED NO es aceptado para este fin.<br>
			1. Acta de nacimiento<br>
			2. Certificado de secundaria<br>
			3. Certificado de preparatoria o constancia de estudios (para solcitantes a carrera universitaria)<br>
			4. Constancia de buena conducta de la instituci&oacute;n donde curs&oacute; su ultimo a&ntilde;o de estudio.<br>
			5. Tres (3) cartas de recomendaci&oacute;n<br>
			6. Seis fotografias en blanco y negro de tama&ntilde;o credencial con su nombre en la parte de atras<br>
			7. Examen m&eacute;dico actualizado<br>
			<strong>Nota:</strong> Al momento de inscripci&oacute;n deber&aacute; entregar los documentos originales mencionados en los incisos uno al tres.
			</td>
			<td style="font-size: 6pt;" width="50%" valign="top">
			<i>Supporting Documentation (photostatic copies are sufficient for processing your admission)
			In order to process this application you will need to send the following documents</i> along 
			with a non refundable admissions fee of the equivalent to $100.00 (Mexican Pesos). Applicants who
			have studied in foreign countries and wish to obtain a Bachelors degree, must present a completed
			high school transcript (or equivalent of 1 2 grades). In Mexico, the GED is not accepted as a high school equivalent.<br>
			1. Birth certificate.<br>
			2. Transcript for grades 7,8,9.<br>
			3. Transcript for grades 10,11,12 (applicants to college degree).<br>
			4. Letter of good conduct from previous institution. <br>
			5. Three (3) letters of recommendation.<br>
			6. Six black and white photographs (passport size) with your name written on the back.<br>
			7. Physical examination.<br>
			<strong>Note:</strong> At the time of registration you will have to submit the original documents mentioned in sentences one, two and three.
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<b><i>COMPROMISO DEL ALUMNO Y DEL PADRE/TUTOR / STUDENT AND PARENT/GUARD COMMITMENT</i></b>
			</td>
		</tr>
		<tr>
			<td align="justify" colspan="2" style="font-size: 7pt;">
				Hago constar que los datos sometidos en esta solicitud son verdaderos. Entiendo que es mi responsabilidad entregar toda la documentaci&oacute;n oficial original,
				debidamente legalizada/autenticada al momento de inscribirme. Estoy de acuerdo en que la Universidad de Montemorelos no me expida documento oficial alguno en
				tanto que no haya hecho entrega de la documentaci&oacute;n estipulada y haber cumplido con todos mis compromisos financieros. Me comprometo a cumplir con cada uno de
				los reglamentos establecidos en cuanto a disciplina y estudios. Adem&aacute;s, por este medio autorizo a la Universidad de Montemorelos a informar a mis padres/tutor oficial
				sobre mi avance acad&eacute;mico, sitaci&oacute;n financiera y de conducta, seg&uacute;n lo amerite el caso.
			</td>
		</tr>
		<tr>
			<td align="justify" colspan="2" style="font-size: 7pt;">
				I hereby state that the information submitted in this application is true. I understand that it is my sole responsibility to submit the stipuled original official
				documentation duly legalized/authenticated at the time of registration. I agree to the fact that Montemorelos Unversity will not issue any official academic document to my
				name unless the aforementioned documentation has been turned in and I have comp lied with all of my financial responsibilities. I agree to comply with the Institution's
				rules and code of conduct. Furthermore, I hereby authorize Montemorelos University to release to my parents/legal guardian information regarding my academic 
				achievements, financial obligations and behavior.
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
	<table align="center" width="95%">
		<tr>
			<td align="center">&nbsp;</td>
			<td align="center">_____________________________________</td>
			<td align="center">_____________________________________</td>
		</tr>
		<tr>
			<td align="center" style="font-size: 7pt;">&nbsp;</td>
			<td align="center" style="font-size: 7pt;">Firma del estudiante / Student's Signature</td>
			<td align="center" style="font-size: 7pt;">Fecha / Date</td>
		</tr>
	</table>
	<br>
	<br>
	<table width="95%" align="center" border="1" cellspacing="0" cellpadding="0">
		<tr>
			<td colspan="8" class="etiqueta">
				<b>PARA USO EXCLUSIVO DE LA DIRECCI&Oacute;N DE ADMISIONES / FOR OFFICIAL USE ONLY</b>
			</td>
		</tr>
		<tr>
			<td align="center" style="font-size: 7pt;"><b>Documento</b></td>
			<td align="center" style="font-size: 7pt;"><b>Copia</b></td>
			<td align="center" style="font-size: 7pt;"><b>Original</b></td>
			<td align="center" style="font-size: 7pt;"><b>Documento</b></td>
			<td align="center" style="font-size: 7pt;"><b>Copia</b></td>
			<td align="center" style="font-size: 7pt;"><b>Original</b></td>
			<td align="center" style="font-size: 7pt;"><b>Aceptaci&oacute;n</b></td>
			<td align="center" style="font-size: 7pt;"><b>Fecha</b></td>
		</tr>
		<tr>
			<td style="font-size: 7pt;">Acta de nacimiento</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="font-size: 7pt;">Recomendaci&oacute;n 2</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="font-size: 7pt;">Carta notificaci&oacute;n pendientes</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td style="font-size: 7pt;">Certificado de secundaria;</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="font-size: 7pt;">Recomendaci&oacute;n 3</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="font-size: 7pt;">Permiso para tomar clases</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td style="font-size: 7pt;">Certificado de bachillerato</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="font-size: 7pt;">Fotografias</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="font-size: 7pt;">Aceptaci&oacute;n condicionada</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td style="font-size: 7pt;">Buena conducta</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="font-size: 7pt;">Examen m&eacute;dico actual</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="font-size: 7pt;">Carta de aceptaci&oacute;n</td>
			<td>&nbsp;</td>
		</tr>
		<tr>
			<td style="font-size: 7pt;">Recomendaci&oacute;n 1</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="font-size: 7pt;">Cuota de admisi&oacute;n</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
			<td style="font-size: 7pt;">Carta de no aceptaci&oacute;n</td>
			<td>&nbsp;</td>
		</tr>
	</table>
</form>
<script type="text/javascript">
	parent.muestraSiguiente();
<%
	if(Integer.parseInt(solicitud.getEstado()) >= 4){
%>
	parent.muestraBotonImprimir();
<%
	}
%>
</script>
</body>
</html>
<%@ include file= "../cierra_salomon.jsp"%>