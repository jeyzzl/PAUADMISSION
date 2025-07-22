package adm;

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

import adm.podium.spring.Aspirante;
import adm.podium.spring.AspiranteDao;
import adm.podium.spring.ExaArea;
import adm.podium.spring.ExaAreaDao;
import adm.alumno.spring.AdmSolicitudDao;
import adm.catalogo.spring.CatCarreraDao;
import adm.podium.spring.Examen;
import adm.podium.spring.ExamenArea;
import adm.podium.spring.ExamenAreaDao;
import adm.podium.spring.ExamenDao;
import adm.alumno.spring.AdmAcademico;
import adm.alumno.spring.AdmAcademicoDao;
import adm.alumno.spring.AdmCartaSubir;
import adm.alumno.spring.AdmCartaSubirDao;
import adm.alumno.spring.AdmEvaluacionNota;
import adm.alumno.spring.AdmEvaluacionNotaDao;


@Controller
public class ControllerResultados {
	
	@Autowired
	AdmSolicitudDao admSolicitudDao;
	
	@Autowired
	CatCarreraDao catCarreraDao;
	
	@Autowired
	AdmAcademicoDao admAcademicoDao;
	
	@Autowired
	AdmEvaluacionNotaDao admEvaluacionNotaDao;
	
	@Autowired
	ExamenDao examenDao;
	
	@Autowired
	ExamenAreaDao examenAreaDao;
	
	@Autowired
	ExaAreaDao exaAreaDao;

	@Autowired
	private AspiranteDao aspiranteDao;
	
	@Autowired
	private AdmCartaSubirDao admCartaSubirDao;
	
	@RequestMapping("/resultados/resultados")
	public String seguimientoSeguimiento(HttpServletRequest request, Model modelo){
		String examenId			= request.getParameter("ExamenId")==null?"0":request.getParameter("ExamenId");
		String folio 			= "0";
		String nombre 			= "-";
		String carreraId		= "-";
		String nombreCarrera 	= "-";
		
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			folio 	   	  = (String)sesion.getAttribute("Folio");			
			nombre		  = admSolicitudDao.getNombre(folio);
			carreraId	  = admAcademicoDao.getCarreraId(folio);
			nombreCarrera = catCarreraDao.getNombreCarrera(carreraId);
		}
		
		boolean pregrado = true;		
		boolean existenResultados = false;		
		
		Aspirante aspirante 		= new Aspirante();
		AdmCartaSubir admCartaSubir = new AdmCartaSubir();
		if(aspiranteDao.existeAspirante(Integer.parseInt(folio))) {
			existenResultados = true;
			aspirante = aspiranteDao.buscaAspirantePorFolio(Integer.parseInt(folio));
			if(aspirante.getGrado().equals("P")) {
				pregrado = false;
			}
		}
		
		if (admCartaSubirDao.existeReg(folio)){
			admCartaSubir = admCartaSubirDao.mapeaRegId(folio);
		}

		HashMap<String,AdmEvaluacionNota> mapaNotas 		= admEvaluacionNotaDao.mapaNotaResultados();
		HashMap<Integer,ExaArea> mapaAreas					= exaAreaDao.mapaArea();
		
		float resLog 	= 0;		
		float resMat 	= 0;		
		float resEsp 	= 0;		
		float resIng 	= 0;
		float resBio 	= 0;
		float resFis 	= 0;
		float resQui 	= 0;
		float resEnsayo = 0;

		List<Examen> lisExamenes	= examenDao.lisExamenesPorFolio(Integer.parseInt(folio), "ORDER BY FECHA DESC");
		
		if (examenId.equals("0") && lisExamenes.size() >= 1) {
			examenId = String.valueOf(lisExamenes.get(0).getId());
		}
			
		if (!pregrado){
			resLog 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "1,15,16");		
			resMat 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "2,17,18");		
			resEsp 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "3,4,14,19");
			resIng 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "5,6");
			resBio 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "7");
			resFis 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "8");
			resQui 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "9");
			resEnsayo = examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "12,20");
		}
		
		if (pregrado) {
			if (lisExamenes.size() >= 1) {
				for(Examen examen : lisExamenes) {
					if(resLog <= examenAreaDao.getPuntosPorArea(examen.getId(), "1,15,16")) {
						resLog 	= examenAreaDao.getPuntosPorArea(examen.getId(), "1,15,16");		
					}
					if(resMat <= examenAreaDao.getPuntosPorArea(examen.getId(), "2,17,18")) {
						resMat 	= examenAreaDao.getPuntosPorArea(examen.getId(), "2,17,18");		
					}
					if(resEsp <= examenAreaDao.getPuntosPorArea(examen.getId(), "3,4,14,19")) {
						resEsp 	= examenAreaDao.getPuntosPorArea(examen.getId(), "3,4,14,19");
					}
					if(resIng <= examenAreaDao.getPuntosPorArea(examen.getId(), "5,6")) {
						resIng 	= examenAreaDao.getPuntosPorArea(examen.getId(), "5,6");
					}
					if(resBio <= examenAreaDao.getPuntosPorArea(examen.getId(), "7")) {
						resBio 	= examenAreaDao.getPuntosPorArea(examen.getId(), "7");
					}
					if(resFis <= examenAreaDao.getPuntosPorArea(examen.getId(), "8")) {
						resFis 	= examenAreaDao.getPuntosPorArea(examen.getId(), "8");
					}
					if(resQui <= examenAreaDao.getPuntosPorArea(examen.getId(), "9")) {
						resQui 	= examenAreaDao.getPuntosPorArea(examen.getId(), "9");
					}
					if(resEnsayo <= examenAreaDao.getPuntosPorArea(examen.getId(), "12,20")) {
						resEnsayo = examenAreaDao.getPuntosPorArea(examen.getId(), "12,20");
					}
				}
			}else if (lisExamenes.size() == 1) {
				examenId = String.valueOf(lisExamenes.get(0).getId());
				
				resLog 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "1,15,16");		
				resMat 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "2,17,18");		
				resEsp 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "3,4,14,19");
				resIng 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "5,6");
				resBio 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "7");
				resFis 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "8");
				resQui 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "9");
				resEnsayo = examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "12,20");
			}
		}
	
		float minLogPre	= 0;
		float minLogPos	= 0;
		if (mapaAreas.containsKey(15)){	
			minLogPre  = mapaAreas.get(15).getMinimoPre() * mapaAreas.get(15).getPuntosPre();	
			minLogPos  = mapaAreas.get(15).getMinimoPos() * mapaAreas.get(15).getPuntosPos();	
		}else if (mapaAreas.containsKey(16)){	
			minLogPre  = mapaAreas.get(16).getMinimoPre() * mapaAreas.get(16).getPuntosPre();	
			minLogPos  = mapaAreas.get(16).getMinimoPos() * mapaAreas.get(16).getPuntosPos();	
		}else if (mapaAreas.containsKey(1)){	
			minLogPre  = mapaAreas.get(1).getMinimoPre() * mapaAreas.get(1).getPuntosPre();	
			minLogPos  = mapaAreas.get(1).getMinimoPos() * mapaAreas.get(1).getPuntosPos();	
		}
		float minMatPre	= 0;
		float minMatPos	= 0;
		if (mapaAreas.containsKey(2)){	
			minMatPre  = mapaAreas.get(2).getMinimoPre() * mapaAreas.get(2).getPuntosPre();
			minMatPos  = mapaAreas.get(2).getMinimoPos() * mapaAreas.get(2).getPuntosPos();
		} else if (mapaAreas.containsKey(17)){	
			minMatPre  = mapaAreas.get(17).getMinimoPre() * mapaAreas.get(17).getPuntosPre();
			minMatPos  = mapaAreas.get(17).getMinimoPos() * mapaAreas.get(17).getPuntosPos();
		} else if (mapaAreas.containsKey(18)){	
			minMatPre  = mapaAreas.get(18).getMinimoPre() * mapaAreas.get(18).getPuntosPre();
			minMatPos  = mapaAreas.get(18).getMinimoPos() * mapaAreas.get(18).getPuntosPos();
		}					
		float minEspPre	= 0;
		float minEspPos	= 0;
		if (mapaAreas.containsKey(3)){	
			minEspPre  = mapaAreas.get(3).getMinimoPre() * mapaAreas.get(3).getPuntosPre();	
			minEspPos  = mapaAreas.get(3).getMinimoPos() * mapaAreas.get(3).getPuntosPos();	
		}else if (mapaAreas.containsKey(14)){	
			minEspPre  = mapaAreas.get(14).getMinimoPre() * mapaAreas.get(14).getPuntosPre();	
			minEspPos  = mapaAreas.get(14).getMinimoPos() * mapaAreas.get(14).getPuntosPos();	
		}
		if (mapaAreas.containsKey(4)){	
			minEspPre += mapaAreas.get(4).getMinimoPre() * mapaAreas.get(4).getPuntosPre();	
			minEspPos += mapaAreas.get(4).getMinimoPos() * mapaAreas.get(4).getPuntosPos();	
		} else if (mapaAreas.containsKey(19)){	
			minEspPre += mapaAreas.get(19).getMinimoPre() * mapaAreas.get(19).getPuntosPre();	
			minEspPos += mapaAreas.get(19).getMinimoPos() * mapaAreas.get(19).getPuntosPos();	
		}		
		float minIngPre	= 0;
		float minIngPos	= 0;
		if (mapaAreas.containsKey(3)){
			minIngPre  = mapaAreas.get(5).getMinimoPre() * mapaAreas.get(5).getPuntosPre();
			minIngPos  = mapaAreas.get(5).getMinimoPos() * mapaAreas.get(5).getPuntosPos();
		}
		if (mapaAreas.containsKey(4)){	
			minIngPre += mapaAreas.get(6).getMinimoPre() * mapaAreas.get(6).getPuntosPre();
			minIngPos += mapaAreas.get(6).getMinimoPos() * mapaAreas.get(6).getPuntosPos();
		}		
		float minBioPre	= 0;
		float minBioPos	= 0;
		if (mapaAreas.containsKey(7)){
			minBioPre  = mapaAreas.get(7).getMinimoPre() * mapaAreas.get(7).getPuntosPre();
			minBioPos  = mapaAreas.get(7).getMinimoPos() * mapaAreas.get(7).getPuntosPos();
		}		
		float minFisPre	= 0;
		float minFisPos	= 0;
		if (mapaAreas.containsKey(8)){
			minFisPre  = mapaAreas.get(8).getMinimoPre() * mapaAreas.get(8).getPuntosPre();
			minFisPos  = mapaAreas.get(8).getMinimoPos() * mapaAreas.get(8).getPuntosPos();
		}		
		float minQuiPre	= 0;
		float minQuiPos	= 0;
		if (mapaAreas.containsKey(8)){
			minQuiPre  = mapaAreas.get(9).getMinimoPre() * mapaAreas.get(9).getPuntosPre();
			minQuiPos  = mapaAreas.get(9).getMinimoPos() * mapaAreas.get(9).getPuntosPos();
		}
		
		boolean paseLog	= false;		boolean paseMat	= false;		boolean paseEsp	= false;
		boolean paseIng	= false;		boolean paseBio	= false;		boolean paseFis	= false;		
		boolean paseQui	= false;
		
		boolean tieneLog= false;		boolean tieneMat= false;		boolean tieneEsp= false;
		boolean tieneIng= false;		boolean tieneBio= false;		boolean tieneFis= false;
		boolean tieneQui= false;
		
		float notaLog = 0;
		float notaMat = 0;
		float notaEsp = 0;
		float notaIng = 0;
		float notaBio = 0;
		float notaFis = 0;
		float notaQui = 0;
		for (Examen exa : lisExamenes) {
			notaLog = examenAreaDao.getPuntosPorArea(exa.getId(), "1,15,16"); 
			notaMat = examenAreaDao.getPuntosPorArea(exa.getId(), "2,17,18");
			notaEsp = examenAreaDao.getPuntosPorArea(exa.getId(), "3,4,14,19");
			notaIng = examenAreaDao.getPuntosPorArea(exa.getId(), "5,6");
			notaBio = examenAreaDao.getPuntosPorArea(exa.getId(), "7");
			notaFis = examenAreaDao.getPuntosPorArea(exa.getId(), "8");
			notaQui = examenAreaDao.getPuntosPorArea(exa.getId(), "9");
			
			if (notaLog >= 1) tieneLog 	= true;
			if (notaMat >= 1) tieneMat 	= true;
			if (notaEsp >= 1) tieneEsp 	= true;
			if (notaIng >= 1) tieneIng 	= true;
			if (notaBio >= 1) tieneBio 	= true;
			if (notaFis >= 1) tieneFis 	= true;		
			if (notaQui >= 1) tieneQui 	= true;

			if(pregrado) {
				if (notaLog >= minLogPre) paseLog = true;
				if (notaMat >= minMatPre) paseMat = true;
				if (notaEsp >= minEspPre) paseEsp = true;
				if (notaIng >= minIngPre) paseIng = true;
				if (notaBio >= minBioPre) paseBio = true;
				if (notaFis >= minFisPre) paseFis = true;
				if (notaQui >= minQuiPre) paseQui = true;
			}else {
				if (notaLog >= minLogPos) paseLog = true;
				if (notaMat >= minMatPos) paseMat = true;
				if (notaEsp >= minEspPos) paseEsp = true;
				if (notaIng >= minIngPos) paseIng = true;
				if (notaBio >= minBioPos) paseBio = true;
				if (notaFis >= minFisPos) paseFis = true;
				if (notaQui >= minQuiPos) paseQui = true;
			}
		}
		
		if(resEnsayo > 0) {
			resEsp = resEsp + resEnsayo;
		}
		
		modelo.addAttribute("admCartaSubir", admCartaSubir);
		modelo.addAttribute("examenId", examenId);
		modelo.addAttribute("nombre", nombre);
		modelo.addAttribute("carreraId", carreraId);
		modelo.addAttribute("nombreCarrera", nombreCarrera);
		modelo.addAttribute("resLog", resLog);		
		modelo.addAttribute("resMat", resMat);
		modelo.addAttribute("resEsp", resEsp);
		modelo.addAttribute("resIng", resIng);
		modelo.addAttribute("resBio", resBio);
		modelo.addAttribute("resFis", resFis);
		modelo.addAttribute("resQui", resQui);
		modelo.addAttribute("resEnsayo", resEnsayo);
		
		modelo.addAttribute("paseLog", paseLog);		
		modelo.addAttribute("paseMat", paseMat);
		modelo.addAttribute("paseEsp", paseEsp);
		modelo.addAttribute("paseIng", paseIng);
		modelo.addAttribute("paseBio", paseBio);
		modelo.addAttribute("paseFis", paseFis);
		modelo.addAttribute("paseQui", paseQui);
		
		modelo.addAttribute("tieneLog", tieneLog);		
		modelo.addAttribute("tieneMat", tieneMat);
		modelo.addAttribute("tieneEsp", tieneEsp);
		modelo.addAttribute("tieneIng", tieneIng);
		modelo.addAttribute("tieneBio", tieneBio);
		modelo.addAttribute("tieneFis", tieneFis);
		modelo.addAttribute("tieneQui", tieneQui);
		
		modelo.addAttribute("pregrado", pregrado);
		modelo.addAttribute("existenResultados", existenResultados);
		
		modelo.addAttribute("lisExamenes", lisExamenes);
		modelo.addAttribute("mapaNotas", mapaNotas);
		modelo.addAttribute("mapaAreas", mapaAreas);
		
		return "resultados/resultados";		
	}	
	
	@RequestMapping("/resultados/resultadosPDF")
	public String resultadoPDF(HttpServletRequest request, Model modelo){
		
		String examenId			= request.getParameter("ExamenId")==null?"0":request.getParameter("ExamenId");
		String folio 			= request.getParameter("Folio")==null?"0":request.getParameter("Folio");
		String carreraId		= request.getParameter("CarreraId")==null?"0":request.getParameter("CarreraId");
		Aspirante aspirante 	= aspiranteDao.buscaAspirantePorFolio(Integer.parseInt(folio));
		
		boolean pregrado = true;
		
		if(aspirante.getGrado().equals("P")) {
			pregrado = false;
		}
		
		AdmAcademico nomCarrera = admAcademicoDao.mapeaRegId(folio);
		String nombreCarrera 	= catCarreraDao.getNombreCarrera(nomCarrera.getCarreraId());
		
		String fecha = examenDao.fechaExamen(examenId);
		
		String nombre	= admSolicitudDao.getNombre(folio);
		
		HashMap<Integer,ExaArea> mapaAreas					= exaAreaDao.mapaArea();
		
		float resLog 	= 0;		
		float resMat 	= 0;		
		float resEsp 	= 0;		
		float resIng 	= 0;
		float resBio 	= 0;
		float resFis 	= 0;
		float resQui 	= 0;
		float resEnsayo = 0;
		
		List<Examen> lisExamenes	= examenDao.lisExamenesPorFolio(Integer.parseInt(folio), "ORDER BY FECHA DESC");
		
		if (examenId.equals("0") && lisExamenes.size() >= 1) {
			examenId = String.valueOf(lisExamenes.get(0).getId());
		}
			
		if (!pregrado){
			resLog 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "1,15,16");		
			resMat 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "2,17,18");		
			resEsp 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "3,4,14,19");
			resIng 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "5,6");
			resBio 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "7");
			resFis 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "8");
			resQui 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "9");
			resEnsayo = examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "12,20");
		}
		
		if (pregrado) {
			if (lisExamenes.size() >= 1) {
				for(Examen examen : lisExamenes) {
					if(resLog <= examenAreaDao.getPuntosPorArea(examen.getId(), "1,15,16")) {
						resLog = examenAreaDao.getPuntosPorArea(examen.getId(), "1,15,16");		
					}
					if(resMat <= examenAreaDao.getPuntosPorArea(examen.getId(), "2,17,18")) {
						resMat 	= examenAreaDao.getPuntosPorArea(examen.getId(), "2,17,18");		
					}
					if(resEsp <= examenAreaDao.getPuntosPorArea(examen.getId(), "3,4,14,19")) {
						resEsp 	= examenAreaDao.getPuntosPorArea(examen.getId(), "3,4,14,19");		
					}
					if(resIng <= examenAreaDao.getPuntosPorArea(examen.getId(), "5,6")) {
						resIng 	= examenAreaDao.getPuntosPorArea(examen.getId(), "5,6");
					}
					if(resBio <= examenAreaDao.getPuntosPorArea(examen.getId(), "7")) {
						resBio 	= examenAreaDao.getPuntosPorArea(examen.getId(), "7");
					}
					if(resFis <= examenAreaDao.getPuntosPorArea(examen.getId(), "8")) {
						resFis 	= examenAreaDao.getPuntosPorArea(examen.getId(), "8");
					}
					if(resQui <= examenAreaDao.getPuntosPorArea(examen.getId(), "9")) {
						resQui 	= examenAreaDao.getPuntosPorArea(examen.getId(), "9");
					}
					if(resEnsayo <= examenAreaDao.getPuntosPorArea(examen.getId(), "12,20")) {
						resEnsayo = examenAreaDao.getPuntosPorArea(examen.getId(), "12,20");
					}
				}
			}else if (lisExamenes.size() == 1) {
				examenId = String.valueOf(lisExamenes.get(0).getId());
				
				resLog 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "1,15,16");		
				resMat 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "2,17,18");		
				resEsp 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "3,4,14,19");		
				resIng 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "5,6");
				resBio 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "7");
				resFis 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "8");
				resQui 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "9");
				resEnsayo = examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "12,20");
			}
		}
		
		if(resEnsayo > 0) {
			resEsp = resEsp + resEnsayo;
		}
		
		modelo.addAttribute("examenId", examenId);
		modelo.addAttribute("folio", folio);
		modelo.addAttribute("carreraId", carreraId);
		modelo.addAttribute("nombre", nombre);
		modelo.addAttribute("resLog", resLog);		
		modelo.addAttribute("resMat", resMat);
		modelo.addAttribute("resEsp", resEsp);
		modelo.addAttribute("resIng", resIng);
		modelo.addAttribute("resBio", resBio);
		modelo.addAttribute("resFis", resFis);
		modelo.addAttribute("resQui", resQui);		
		modelo.addAttribute("mapaAreas", mapaAreas);
		modelo.addAttribute("fecha", fecha);
		modelo.addAttribute("nombreCarrera", nombreCarrera);
		modelo.addAttribute("grado", aspirante.getGrado());
		
		return "resultados/resultadosPDF";		
	}	
	
}