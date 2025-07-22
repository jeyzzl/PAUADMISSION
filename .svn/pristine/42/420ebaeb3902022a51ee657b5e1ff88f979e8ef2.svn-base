<%
	}catch (SQLException se){                
	    System.out.println("Error: "+se);
	    if (request.getAttribute("conSalomon")!=null){
	    	conSalomon = (java.sql.Connection)request.getAttribute("conSalomon");
	    	request.removeAttribute("conSalomon");	
	    	if (conSalomon!=null) conSalomon.close();
		}
	}finally{
		if (request.getAttribute("conSalomon")!=null){
		    	conSalomon = (java.sql.Connection)request.getAttribute("conSalomon");
		    	request.removeAttribute("conSalomon");	
		    	if (conSalomon!=null) conSalomon.close();
		}		
		conSalomon = null;
	}
	
%>