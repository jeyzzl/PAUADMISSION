package adm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import adm.alumno.spring.AdmCartaSubir;
import adm.alumno.spring.AdmCartaSubirDao;
import adm.alumno.spring.AdmDirecto;
import adm.alumno.spring.AdmDirectoDao;
import adm.alumno.spring.AdmSolicitud;
import adm.alumno.spring.AdmSolicitudDao;

@Controller
public class ControllerDirecto {
	
	@Autowired
	@Qualifier("dataSourceSalomon")
	private DataSource salomon;
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	@Autowired
	private AdmDirectoDao admDirectoDao;
	
	@Autowired
	private AdmSolicitudDao admSolicitudDao;
	
	@Autowired
	private AdmCartaSubirDao admCartaSubirDao;
	
	public void enviarConSalomon(HttpServletRequest request, String url){
		// Enviar de la conexión
		try{ 
			request.setAttribute("conSalomon", salomon.getConnection());
		}catch(Exception ex){ 
			System.out.println(url+" "+ex);
		}
	}
	
	@RequestMapping("/directo/ingreso")
	public String directoIngreso(HttpServletRequest request, Model model){
		String planId 		= request.getParameter("PlanId")==null?"-":request.getParameter("PlanId");
		String matricula	= request.getParameter("Matricula")==null?"0":request.getParameter("Matricula");
		String mensaje		= request.getParameter("Mensaje")==null?"0":request.getParameter("Mensaje");
		String promedio 	= "0";
		String reprobadas	= "0";
		String nombre	 	= "-";			
		
		String folio 		= "";			
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			folio			= (String) sesion.getAttribute("Folio");
		}
		
		AdmSolicitud admSolicitud 	= new AdmSolicitud();
		
		if(admSolicitudDao.existeReg(folio)) {
			admSolicitud = admSolicitudDao.mapeaRegId(folio);	
		}

		AdmDirecto admDirecto 		= new AdmDirecto();
		AdmCartaSubir admCartaSubir = new AdmCartaSubir();

		admDirecto.setMatricula(matricula);
		
		if (admDirectoDao.existeReg(folio)) {
			admDirecto = admDirectoDao.mapeaRegId(folio);
			if(!matricula.equals("0") && !planId.equals("-")){
				admDirecto.setPlanId(planId);			
				admDirectoDao.updateReg(admDirecto);
			}
		}else if(!matricula.equals("0")){
			admDirecto.setFolio(folio);
			if (!planId.equals("-")) {
				admDirecto.setPlanId(planId);			
				admDirectoDao.insertReg(admDirecto);
			}
		}
		
		HashMap<String,String> mapa	= new HashMap<String,String>();
		List<String> lista 			= new ArrayList<String>();
		
		if(!admDirecto.getMatricula().equals("0")) {
			mapa = admDirectoDao.mapaPlanes(admDirecto.getMatricula());
			lista = admDirectoDao.listaPlanes(admDirecto.getMatricula());
		}
		
		if(!admDirecto.getMatricula().equals("0") && !admDirecto.getPlanId().equals("-")) {
			promedio 	= admDirectoDao.promedio(admDirecto.getMatricula(), admDirecto.getPlanId());
			reprobadas 	= admDirectoDao.getReprobadas(matricula, planId);
			nombre 		= admDirectoDao.nombreMatricula(admDirecto.getMatricula());
		}
		if(!admDirecto.getMatricula().equals("0")) {
			nombre = admDirectoDao.nombreMatricula(admDirecto.getMatricula());
		}
		
		if (admCartaSubirDao.existeReg(folio)){
			admCartaSubir = admCartaSubirDao.mapeaRegId(folio);
		}

		model.addAttribute("admDirecto",admDirecto);
		model.addAttribute("admSolicitud",admSolicitud);
		model.addAttribute("admCartaSubir",admCartaSubir);
		model.addAttribute("mapa",mapa);
		model.addAttribute("lista",lista);
		model.addAttribute("promedio",promedio);
		model.addAttribute("reprobadas",reprobadas);
		model.addAttribute("nombre",nombre);
		model.addAttribute("mensaje",mensaje);
		
		return "directo/ingreso";
	}

	@RequestMapping("/directo/subirPrepa")
	public String directoSubirPrepa(@RequestParam("archivo") MultipartFile archivo,HttpServletRequest request){
		String planId 			= request.getParameter("PlanId")==null?"-":request.getParameter("PlanId");
		String matricula		= request.getParameter("Matricula")==null?"0":request.getParameter("Matricula");
		String nombreArchivo 	= "";
		
		String folio = "";			
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			folio = (String) sesion.getAttribute("Folio");
		}
		
		if (admDirectoDao.existeReg(folio)) {
			try {
				nombreArchivo = archivo.getOriginalFilename();
				admDirectoDao.subirCartaPrepa(archivo.getBytes(), folio, nombreArchivo);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			nombreArchivo = archivo.getOriginalFilename();
			
			AdmDirecto admDirecto = new AdmDirecto();
			
			admDirecto.setFolio(folio);
			admDirecto.setMatricula(matricula);
			admDirecto.setPlanId(planId);	
			admDirecto.setNombrePrepa(nombreArchivo);
			
			try {
				admDirecto.setRecPrepa(archivo.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			admDirectoDao.insertReg(admDirecto);
		}
		
		return "redirect:/directo/ingreso?Matricula="+matricula+"&PlanId="+planId;
	}
	
	@RequestMapping("/directo/subirVre")
	public String directoSubirVre(@RequestParam("archivo") MultipartFile archivo,HttpServletRequest request){
		String planId 			= request.getParameter("PlanId")==null?"-":request.getParameter("PlanId");
		String matricula		= request.getParameter("Matricula")==null?"0":request.getParameter("Matricula");
		String nombreArchivo 	= "";
		
		String folio = "";			
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			folio = (String) sesion.getAttribute("Folio");
		}
		
		if (admDirectoDao.existeReg(folio)) {
			try {
				nombreArchivo = archivo.getOriginalFilename();
				admDirectoDao.subirCartaVre(archivo.getBytes(), folio, nombreArchivo);
			} catch (Exception e) {
				System.out.println("Error : directoSubirVre-"+e);
			}
		}else {
			nombreArchivo = archivo.getOriginalFilename();
			
			AdmDirecto admDirecto = new AdmDirecto();
			
			admDirecto.setFolio(folio);
			admDirecto.setMatricula(matricula);
			admDirecto.setPlanId(planId);	
			admDirecto.setNombreVre(nombreArchivo);
			
			try {
				admDirecto.setRecPrepa(archivo.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			admDirectoDao.insertReg(admDirecto);
		}
		
		return "redirect:/directo/ingreso?Matricula="+matricula+"&PlanId="+planId;
	}

	@RequestMapping("/directo/enviar")
	public String directoEnviar(HttpServletRequest request){
		String planId 		= request.getParameter("PlanId")==null?"-":request.getParameter("PlanId");
		String matricula	= request.getParameter("Matricula")==null?"0":request.getParameter("Matricula");
		String mensaje		= "0";			
		
		String folio = "";			
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			folio = (String) sesion.getAttribute("Folio");
		}
		
		if (admDirectoDao.existeReg(folio)) {
			if (admDirectoDao.enviarSolicitud(folio)) {
				mensaje = "1";
			}
		}else {
			AdmDirecto admDirecto = new AdmDirecto();
			
			admDirecto.setFolio(folio);
			admDirecto.setMatricula(matricula);
			admDirecto.setPlanId(planId);			
			admDirecto.setEnvioSol("S");
			
			admDirectoDao.insertReg(admDirecto);
			mensaje = "1";
		}
		
		return "redirect:/directo/ingreso?Matricula="+matricula+"&PlanId="+planId+"&Mensaje="+mensaje;
	}

}