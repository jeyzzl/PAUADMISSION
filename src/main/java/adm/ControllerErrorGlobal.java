package adm;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerErrorGlobal{
	
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(HttpServletRequest request){
		String ruta = ((jakarta.servlet.http.HttpServletRequest)request).getRequestURI();
		System.out.println("Entre a error global"+ruta);
		// Cerrar la conexion de enoc
        if (request.getAttribute("conSalomon")!=null){
        	try{
        		java.sql.Connection conSalomon = (java.sql.Connection)request.getAttribute("conSalomon");
        		request.removeAttribute("conSalomon");
        		if (conSalomon != null){
        			conSalomon.close();
        		}        		
        	}catch(Exception ex){}        	
        }
        
		return "error";
	}
	
}