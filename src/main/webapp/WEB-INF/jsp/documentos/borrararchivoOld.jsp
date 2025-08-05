<%@ include file= "../con_salomon.jsp" %>
<%@ include file= "../conectadbp.jsp" %>

<jsp:useBean id="pgAdmArchivos" scope="page" class="adm.documento.PgAdmArchivos" />
<jsp:useBean id="admDocAlum" scope="page" class="adm.documento.AdmDocAlum" />
<jsp:useBean id="pgAdmDocAlum" scope="page" class="adm.documento.PgAdmDocAlum"/>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/iframeResizer.contentWindow.min.js"></script>
<%	
	
	String folio 		= (String)session.getAttribute("Folio")==null?"0":(String)session.getAttribute("Folio");
	String documentoId	= request.getParameter("documentoId");
	
	boolean borroPg = false;
	try{
		conn2.setAutoCommit(false);
		
		pgAdmArchivos.setFolio(folio);
		pgAdmArchivos.setDocumentoId(documentoId);
		if(pgAdmArchivos.existeReg(conn2)){
			if(pgAdmArchivos.deleteReg(conn2))
				borroPg = true;
				admDocAlum.setFolio(folio);
				admDocAlum.setDocumentoId(documentoId);	
				
				pgAdmDocAlum.setDocumentoId(documentoId);
				pgAdmDocAlum.setFolio(folio);
				if(!pgAdmDocAlum.existeDocumentos(conn2) && !pgAdmArchivos.existeReg(conn2) ){
					if (admDocAlum.existeReg(conSalomon)){
						if (admDocAlum.deleteReg(conSalomon)){
							conSalomon.commit();
						}
					}
				}
		}else{
			borroPg = false;
	   	}		
	    conn2.setAutoCommit(true);
	    
	    if(borroPg){
	    	response.sendRedirect("enviar?documentoId="+documentoId);
	    }else{
%>
		<font size="4" color="red"><b><spring:message code='documentos.borrararchivo.ErrorAlBorrar'/></b> <a href="enviar?documentoId=<%=documentoId%>"><spring:message code='documentos.borrararchivo.Regresar'/></a></font><br>
<%
	    }
	}catch(Exception e){
		System.out.println("Error al subir el archivo: "+e);
%>
		<font size="4"><b><spring:message code='documentos.borrararchivo.ArchivoMuyGrande'/></b> <a href="subirarchivo?documentoId=<%=documentoId%>"><spring:message code='documentos.borrararchivo.Regresar'/></a></font>
<%
	}
%>
<%@ include file= "../cierradbp.jsp" %>
<%@ include file= "../cierra_salomon.jsp" %>