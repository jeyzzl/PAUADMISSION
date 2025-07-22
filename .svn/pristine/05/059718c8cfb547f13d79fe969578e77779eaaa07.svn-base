<%@ include file= "../con_salomon.jsp"%>
<%@page import="adm.catalogo.CatEstado"%>
<%@page import="adm.catalogo.CatCiudad"%>
<jsp:useBean id="catEstado" scope="page" class="adm.catalogo.CatEstado"/>
<jsp:useBean id="catEstadoU" scope="page" class="adm.catalogo.EstadoUtil"/>
<jsp:useBean id="catCiudad" scope="page" class="adm.catalogo.CatCiudad"/>
<jsp:useBean id="catCiudadU" scope="page" class="adm.catalogo.CiudadUtil"/>
<%
	int accion = Integer.parseInt(request.getParameter("Accion"));

	switch(accion){
		case 5:{// Muestra los estados
			String paisId = request.getParameter("paisId");			
			ArrayList<CatEstado> lisEstados	= catEstadoU.getLista(conSalomon, paisId, " ORDER BY NOMBRE_ESTADO");
			System.out.print(lisEstados.size());
			if (lisEstados.size()>0){
				// Coloca la opci�n cero
				out.print("<option value='0' selected>Elige...</option>");
				for(int i=0; i<lisEstados.size(); i++){
					catEstado = (CatEstado) lisEstados.get(i);
					out.print("<option value='"+catEstado.getEstadoId()+"'>"+catEstado.getNombreEstado()+"</option>");
				}	
			}else{
				out.print("<option value='0'>Sin registrar...</option>");
			}			
		}break;
		case 6:{//Muestra las ciudades
			String paisId	= request.getParameter("paisId");
			String estadoId	= request.getParameter("estadoId");
			ArrayList<CatCiudad> lisCiudades	= catCiudadU.getLista(conSalomon, paisId, estadoId, " ORDER BY NOMBRE_CIUDAD");
			if (lisCiudades.size()>0){
				//Coloca la opcion cero
				out.print("<option value='0' selected>Elige...</option>");
				for(int i=0; i<lisCiudades.size(); i++){
					catCiudad = (CatCiudad) lisCiudades.get(i);
					out.print("<option value='"+catCiudad.getCiudadId()+"'>"+catCiudad.getNombreCiudad()+"</option>");
				}				
			}else{
				out.print("<option value='0'>Sin registrar...</option>");
			}
		}break;
	}	
%>
<%@ include file= "../cierra_salomon.jsp" %>