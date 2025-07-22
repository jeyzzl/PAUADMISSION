package adm;

import jakarta.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ControllerAdmitido {
	
	@Autowired
	@Qualifier("dataSourceSalomon")
	private DataSource salomon;
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public void enviarConSalomon(HttpServletRequest request, String url){
		// Enviar de la conexión
		try{ 
			request.setAttribute("conSalomon", salomon.getConnection());			
		}catch(Exception ex){ 
			System.out.println(url+" "+ex);
		}
	}
	
	@RequestMapping("/admitido/admitido")
	public String admitidoAdmitido(HttpServletRequest request){
		enviarConSalomon(request,"Error-adm.ControllerAdmitido|admitidoAdmitido:");
		return "admitido/admitido";
	}	
	
}