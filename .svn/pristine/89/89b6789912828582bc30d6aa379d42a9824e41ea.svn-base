package adm;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import adm.alumno.spring.AdmCartaSubir;
import adm.alumno.spring.AdmCartaSubirDao;
import adm.alumno.spring.AdmPasos;
import adm.alumno.spring.AdmPasosDao;
import adm.alumno.spring.AdmSolicitud;
import adm.alumno.spring.AdmSolicitudDao;
import adm.examen.spring.AdmReservada;

@Controller
public class ControllerCita {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	@Autowired
	private adm.examen.spring.AdmEventoDao admEventoDao;		
	
	@Autowired
	private adm.examen.spring.AdmPeriodoDao admPeriodoDao;	
	
	@Autowired
	private adm.examen.spring.AdmReservadaDao admReservadaDao;	
	
	@Autowired
	private AdmSolicitudDao admSolicitudDao;	
	
	@Autowired
	private AdmCartaSubirDao admCartaSubirDao;	
	
	@Autowired
	AdmPasosDao admPasosDao;
	
	@RequestMapping("/cita/evento")
	public String citaEvento(HttpServletRequest request, Model modelo) throws SQLException{
		
		String folio 		= "0";		
		String estado 		= "0"; 
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			folio			= (String) sesion.getAttribute("Folio");
			if (admSolicitudDao.existeReg(folio)){
				estado = admSolicitudDao.mapeaRegId(folio).getEstado();
			}
		}		
		
		String eventoRes	= admReservadaDao.getEventoReservado(folio); 
		List<adm.examen.spring.AdmEvento> lista	= admEventoDao.lisActivos("'A','C'", " ORDER BY FECHA");
		
		AdmCartaSubir admCartaSubir = new AdmCartaSubir();
		
		if (admCartaSubirDao.existeReg(folio)){
			admCartaSubir = admCartaSubirDao.mapeaRegId(folio);
		}
		
		modelo.addAttribute("admCartaSubir",admCartaSubir);
		modelo.addAttribute("estado",estado);
		modelo.addAttribute("lista", lista);
		modelo.addAttribute("eventoRes", eventoRes);	
		
		return "cita/evento";
	}
	
	@RequestMapping("/cita/periodo")
	public String citaPeriodo(HttpServletRequest request, Model modelo) throws SQLException{
		
		String eventoId 		= request.getParameter("EventoId")==null?"0":request.getParameter("EventoId");
		boolean eventoRes 		= false;
		boolean alumnoRes		= false;
		String folio 			= "0";
		String eventoEstado		= "A";
		String alumnoEstado		= "-";
		
		HttpSession sesion		= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			folio				= (String) sesion.getAttribute("Folio");
		}		
		AdmSolicitud admSolicitud 	= new AdmSolicitud();		
		if(admSolicitudDao.existeReg(folio)) {
			admSolicitud = admSolicitudDao.mapeaRegId(folio);	
		}		
		boolean existePago = false;
		if (admPasosDao.existeReg(folio, "1")){
			existePago = true;
		}
		
		alumnoEstado 	= admReservadaDao.getEstado(folio);		
		eventoRes 		= admReservadaDao.existeReservacion(folio, eventoId, "'A','C'");
		alumnoRes 		= admReservadaDao.existeReservacion(folio, "'A','C'");
		eventoEstado	= admEventoDao.getEstado(eventoId);
		
		List<adm.examen.spring.AdmPeriodo> lista	= admPeriodoDao.getListEvento(eventoId, " ORDER BY PERIODO_ID");		
		HashMap<String, String> mapaOcupados 		= admReservadaDao.mapaOcupados("A");
		
		AdmCartaSubir admCartaSubir = new AdmCartaSubir();
		
		if (admCartaSubirDao.existeReg(folio)){
			admCartaSubir = admCartaSubirDao.mapeaRegId(folio);
		}
		
		modelo.addAttribute("admCartaSubir", admCartaSubir);
		modelo.addAttribute("existePago", existePago);
		modelo.addAttribute("lista", lista);
		modelo.addAttribute("admSolicitud", admSolicitud);
		modelo.addAttribute("mapaOcupados", mapaOcupados);
		modelo.addAttribute("eventoRes", eventoRes);		
		modelo.addAttribute("alumnoRes", alumnoRes);
		modelo.addAttribute("eventoEstado", eventoEstado);
		modelo.addAttribute("alumnoEstado", alumnoEstado);
		
		return "cita/periodo";
	}
	
	@RequestMapping("/cita/reservada")
	public String citaReservar(HttpServletRequest request, Model modelo) throws SQLException{
		AdmReservada citaReservada = new AdmReservada();
		
		String eventoId 	= request.getParameter("EventoId");
		String periodoId 	= request.getParameter("PeriodoId");
		String dia 			= request.getParameter("Dia");
		String folio		= request.getParameter("Folio");
		
		citaReservada.setFolio(folio);
		citaReservada.setDia(dia);
		citaReservada.setPeriodoId(periodoId);
		citaReservada.setFecha(adm.util.Fecha.getHoy());
		citaReservada.setEstado("A");
		citaReservada.setEventoId(eventoId);
		if (admReservadaDao.existeReg(folio, eventoId, periodoId)){
			admReservadaDao.updateReg(citaReservada);
		}else{
			admReservadaDao.insertReg(citaReservada);
		}	
		
		return "redirect:/cita/periodo?EventoId="+eventoId;
	}
	
	@RequestMapping("/cita/quitarReserva")
	public String citaQuitarReserva(HttpServletRequest request, Model modelo) throws SQLException{
		
		String eventoId 	= request.getParameter("EventoId");
		String periodoId 	= request.getParameter("PeriodoId");	
		String folio		= request.getParameter("Folio");
		
		admReservadaDao.deleteReg(folio, eventoId, periodoId );		
		
		return "redirect:/cita/periodo?EventoId="+eventoId;
	}

}
