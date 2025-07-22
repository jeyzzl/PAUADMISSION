package adm;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerError implements ErrorController{
	
	@Autowired
	ServletContext context;
	
	private static final String path 	= "/error";
	
	public String getErrorPath() {		
		return path;
	}
	
	@RequestMapping(value={path},method=RequestMethod.GET)
	public String errorMensaje(HttpServletRequest request, Model modelo){
		
		String paginaError 	= "errores"; 
        String mensaje 		= "";
        
        int httpErrorCode 	= (Integer)request.getAttribute("javax.servlet.error.status_code");
        String comentario	= (String)request.getAttribute("javax.servlet.error.message");
        String uri			= (String)request.getAttribute("javax.servlet.error.request_uri");
        //Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
 
        switch (httpErrorCode) {
            case 400: {
                mensaje = "Error: 400, ¡ Error in application form !";
                break;
            }
            case 401: {
                mensaje = "Error: 401. ¡ Not authorized !";
                break;
            }
            case 402: {
                mensaje = "Error: 402. ¡ Fee Required !";
                break;
            }
            case 403: {
                mensaje = "Error: 403. ¡ Forbiden !";
                break;
            }
            case 404: {
                mensaje = "Error: 404. ¡ Not Found !";
                break;
            }
            case 405: {
                mensaje = "Error: 405. ¡ Method not allowed !";
                break;
            }
            case 406: {
                mensaje = "Error: 406. ¡ Invalid response !";
                break;
            }
            case 407: {
                mensaje = "Error: 407. ¡ Proxy authentication required !";
                break;
            }
            case 408: {
                mensaje = "Error: 408. ¡ Request time out !";
                break;
            }
            case 500: {
                mensaje = "Error: 500. ! Internal Server Error !";
                break;
            }
            case 501: {
                mensaje = "Error: 501. ! Not implemented !";
                break;
            }
            case 502: {
                mensaje = "Error: 502. ! Bad gateway error!";
                break;
            }
            case 503: {
                mensaje = "Error: 503. ! Service Unavailable !";
                break;
            }
            case 504: {
                mensaje = "Error: 504. ! Puerta de tiempo de espera Awating Repsonse  !";
                break;
            }
            case 505: {
                mensaje = "Error: 505. ! HTTP version not comaptible !";
                break;
            }
            default:{
            	mensaje = "¡ Error "+String.valueOf(httpErrorCode)+" undefined error !";
            	break;
            }
        }
        
        modelo.addAttribute("error",httpErrorCode);
        modelo.addAttribute("mensaje",mensaje);
        modelo.addAttribute("comentario",comentario);
        modelo.addAttribute("uri",uri);      
        
		return paginaError;
	}
	
}