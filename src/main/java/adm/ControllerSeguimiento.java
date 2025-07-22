package adm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import adm.alumno.spring.Solicitud;
import adm.alumno.spring.SolicitudDao;


@Controller
public class ControllerSeguimiento {	
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	@Autowired
	SolicitudDao solicitudDao;
	
	
	@RequestMapping("/seguimiento/seguimiento")
	public String seguimientoSeguimiento(HttpServletRequest request, Model modelo){
		
		String folio = "0";
		HttpSession sesion		= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			folio 	= (String)sesion.getAttribute("Folio")==null?"0":(String)sesion.getAttribute("Folio");
		}
		
		Solicitud solicitud  = new Solicitud();
		if (!folio.equals("0")){
			solicitud = solicitudDao.mapeaRegId( folio);			
		}
		
		modelo.addAttribute("solicitud", solicitud);
		
		return "seguimiento/seguimiento";
	}	
	
}