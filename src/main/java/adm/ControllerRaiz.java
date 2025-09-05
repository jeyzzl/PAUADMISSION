package adm;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import javax.sql.DataSource;

import adm.alumno.spring.*;
import adm.catalogo.spring.CatCarrera;
import adm.catalogo.spring.CatCarreraDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import adm.pago.spring.AdmPago;
import adm.pago.spring.AdmPagoDao;
import adm.parametros.spring.AdmParametros;
import adm.banco.spring.AdmBanco;
import adm.banco.spring.AdmBancoDao;
import adm.documento.spring.AdmArchivo;
import adm.documento.spring.AdmDocAlumDao;
import adm.examen.spring.AdmPeriodo;
import adm.examen.spring.AdmPeriodoDao;
import adm.examen.spring.AdmReservadaDao;
import adm.podium.spring.Examen;
import adm.podium.spring.ExamenAreaDao;
import adm.podium.spring.ExamenDao;
import adm.parametros.spring.AdmParametrosDao;
import adm.services.MailService;
import adm.util.Encriptar;
import adm.vista.spring.Maestros;
import adm.vista.spring.MaestrosDao;
import net.glxn.qrgen.javase.QRCode;

@Controller
public class ControllerRaiz{

    private final AdmSocioecoDao admSocioecoDao;
	
	@Autowired
	@Qualifier("dataSourceSalomon")
	private DataSource salomon;
	
	@Autowired
	private AdmSolicitudDao admSolicitudDao;
	
	@Autowired
	private AdmUsuarioDao admUsuarioDao;
	
	@Autowired
	private AdmAsesorDao admAsesorDao;
	
	@Autowired
	private MaestrosDao maestrosDao; 
	
	@Autowired
	private AdmProcesoDao admProcesoDao;
	
	@Autowired
	private AdmRecuperarDao admRecuperarDao;
	
	@Autowired
	private AdmEvaluacionNotaDao admEvaluacionNotaDao;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	AdmReservadaDao admReservadaDao;
	
	@Autowired
	AdmEncuestaDao admEncuestaDao;
	
	@Autowired
	AdmOpcionPlanDao admOpcionPlanDao;
	
	@Autowired
	AdmPasosDao admPasosDao;

	@Autowired
	AdmPasoDao admPasoDao;

	@Autowired
	CatCarreraDao catCarreraDao;

	@Autowired
	ExamenDao examenDao;
	
	@Autowired
	ExamenAreaDao examenAreaDao;
	
	@Autowired
	private AlumReferenciaDao alumReferenciaDao;
	
	@Autowired
	private ParametrosDao parametrosDao;
	
	@Autowired
	private AdmAcademicoDao admAcademicoDao;

	@Autowired
	private AdmDirectoDao admDirectoDao;
	
	@Autowired
	AdmDocAlumDao admDocAlumDao;

	@Autowired
	AdmCartaSubirDao admCartaSubirDao;

	@Autowired
	private AdmFotoDao admFotoDao;
	
	@Autowired
	private AdmPagoDao admPagoDao;

	@Autowired
	private AdmBancoDao admBancoDao;

	@Autowired
	private AdmParametrosDao admParametrosDao;

	@Autowired
	private AdmIngresoDao admIngresoDao;


    ControllerRaiz(AdmSocioecoDao admSocioecoDao) {
        this.admSocioecoDao = admSocioecoDao;
    }
	
	
	@RequestMapping(value={"/inicio"})
	public String inicio(HttpServletRequest request, Model modelo){
		
		String usuarioId 		= "0";				
		String idiomaUsuario	= LocaleContextHolder.getLocale().toString().split("_")[0];
		String usuarioNombre 	= "-";
		String carrera			= "";
		boolean existeUsuario 	= false; 
		AdmUsuario admUsuario 	= new AdmUsuario();
		HttpSession sesion		= ((HttpServletRequest)request).getSession();	
		
		if (sesion!=null){						
			usuarioId 			= (String)sesion.getAttribute("UsuarioId")==null?"0":(String)sesion.getAttribute("UsuarioId");
			sesion.setAttribute("IdiomaUsuario", idiomaUsuario);
			if (admUsuarioDao.existeReg(usuarioId)) {
				existeUsuario   = true;
				admUsuario 		= admUsuarioDao.mapeaRegId(usuarioId);
				usuarioNombre 	= admUsuarioDao.getNombreCorto(usuarioId);
				sesion.setAttribute("UsuarioNombre", usuarioNombre);
				sesion.setAttribute("Carrera", carrera);
			}
		}

		List<AdmIngreso> lisIngresos = admIngresoDao.lisActivos("");

		String fecha = adm.util.Fecha.getHoy();
		boolean tieneSolicitudActiva = false;

		AdmIngreso admIngreso = admIngresoDao.periodoActivoFecha(fecha);
		if(admIngreso.getPeriodoId().equals("0")) admIngreso = admIngresoDao.periodoActivo();
		if(admIngreso.getPeriodoId().equals("0")) admIngreso = lisIngresos.get(0);

		if(admUsuario.getEstado().equals("0")) {
			return "redirect:/registro";
		}else{
			boolean existeMatricula 	= admUsuarioDao.existeAlumno(admUsuario.getMatricula());
			
			List<AdmSolicitud> lisSolicitudes	= admSolicitudDao.listPorUsuario(usuarioId);

			for(AdmSolicitud admSolicitud : lisSolicitudes){
				if(admSolicitud.getPeriodoId().equals("0")) {
					tieneSolicitudActiva = true;
				}
				if(admSolicitud.getPeriodoId().equals(admIngreso.getPeriodoId())) {
					tieneSolicitudActiva = true;
				}
			}

			HashMap<String, AdmPaso> mapPaso = admPasoDao.mapaPaso();
			HashMap<String, AdmAcademico> mapAcademico = admAcademicoDao.mapaPorUsuario(usuarioId);
			HashMap<String, CatCarrera> mapCarrera = catCarreraDao.mapaCarreras();	

			modelo.addAttribute("admUsuario", admUsuario);
			modelo.addAttribute("existeUsuario", existeUsuario);
			modelo.addAttribute("existeMatricula", existeMatricula);
			modelo.addAttribute("lisSolicitudes", lisSolicitudes);
			modelo.addAttribute("mapPaso", mapPaso);
			modelo.addAttribute("mapAcademico", mapAcademico);
			modelo.addAttribute("mapCarrera", mapCarrera);
			modelo.addAttribute("tieneSolicitudActiva", tieneSolicitudActiva);
			
			if (existeUsuario) {
				return "inicio";
			}else {
				return "login";
			}
		} 
	}
	
	@GetMapping("/inicial")
	public String raiz(HttpServletRequest request, Model modelo){
		
		String folio			= request.getParameter("Folio")==null?"0":request.getParameter("Folio");
		String planId			= "0";
		String usuarioId		= "";
		String usu 				= "0";		
		String examenId			= "0";
		int estado 				= -1;
		boolean activo 			= false;
		boolean existeSol		= false;
		boolean existeEval		= false;
		boolean existeCita		= false;
		boolean tieneBanco		= false;		
		String idiomaUsuario	= LocaleContextHolder.getLocale().toString().split("_")[0];
		String promedio 		= "0";
		String reprobadas		= "0";
		String usuarioNombre 	= "-";
		String carrera			= "";
		List<String> opciones			= new ArrayList<String>();
		HashMap<String, String> mapOpcionPlan = admOpcionPlanDao.mapaPorPlan();

		AdmParametros parametros = admParametrosDao.getAll(" ORDER BY ID").get(0);
	
		HttpSession sesion		= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			if (folio.equals("0")) {
				folio 				= (String)sesion.getAttribute("Folio")==null?"0":(String)sesion.getAttribute("Folio");
			}else{
				sesion.setAttribute("Folio", folio);
			}
			
			usuarioId 			= (String)sesion.getAttribute("UsuarioId")==null?"0":(String)sesion.getAttribute("UsuarioId");
			usu 				= (String)sesion.getAttribute("Usuario")==null?"0":(String)sesion.getAttribute("Usuario");		
			
			//Hashmaps para atraer el nombre de la carrera
			HashMap<String, AdmAcademico> mapAcademico = admAcademicoDao.mapaPorUsuario(usuarioId);
			HashMap<String, CatCarrera> mapCarrera = catCarreraDao.mapaCarreras();
			
			
			//Comprobando si existe en las mapas
			if(mapAcademico.containsKey(folio)){
				if(mapCarrera.containsKey(mapAcademico.get(folio).getCarreraId())){
					carrera = mapCarrera.get(mapAcademico.get(folio).getCarreraId()).getNombreCarrera();
				}
			}else{
				carrera = "Indefinido";
			}
			
			sesion.setAttribute("Carrera", carrera);
			
			sesion.setAttribute("IdiomaUsuario", idiomaUsuario);
			if (admEvaluacionNotaDao.totalDeEvaluaciones(folio) > 0) existeEval = true;	
			if (admUsuarioDao.existeReg(usuarioId)) {
				usuarioNombre = admUsuarioDao.getNombreCorto(usuarioId);
				sesion.setAttribute("UsuarioNombre", usuarioNombre);
			}
			
			planId = admAcademicoDao.getPlanId(folio);
			
			sesion.setAttribute("PlanId","-"+planId+"-");
			for(int i = 1; i <= 8; i++) {
				String n = Integer.toString(i);					
				if(mapOpcionPlan.containsKey(planId+n) || planId.equals("0") || planId.equals("00000000") ){					
					opciones.add(n);
				}						
			}			
			sesion.setAttribute("Opciones", opciones);			
		}
		
		float resLog 	= 0;		
		float resMat 	= 0;		
		float resEsp 	= 0;		
		float resIng 	= 0;
		float resBio 	= 0;
		float resFis 	= 0;
		float resQui 	= 0;
		
		List<Examen> lisExamenes	= examenDao.lisExamenesPorFolio(Integer.parseInt(folio), "ORDER BY FECHA DESC");
		
		if (examenId.equals("0") && lisExamenes.size() >= 1) {
			examenId = String.valueOf(lisExamenes.get(0).getId());
			
			resLog 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "1");		
			resMat 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "2");		
			resEsp 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "3,4");		
			resIng 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "5,6");
			resBio 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "7");
			resFis 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "8");
			resQui 	= examenAreaDao.getPuntosPorArea(Integer.parseInt(examenId), "9");
		}
		
		if (resLog > 0 || resMat > 0 || resEsp > 0 || resIng > 0 || resBio > 0 || resFis > 0 || resQui > 0) existeEval = true;
		
		AdmProceso admProceso 		= new AdmProceso();
		AdmPasos admPasos			= new AdmPasos(); 
		AdmSolicitud admSolicitud 	= new AdmSolicitud();
		AdmCartaSubir admCartaSubir	= new AdmCartaSubir(); 
		AdmAsesor admAsesor 		= new AdmAsesor();
		AdmBanco admBanco			= new AdmBanco();
		Maestros maestro 			= new Maestros();
		
		if(!usu.equals("0")){
			if (admSolicitudDao.existeReg(folio)) {
				admSolicitud 	= admSolicitudDao.mapeaRegId(folio);
				if (admAsesorDao.existeReg(admSolicitud.getAsesorId()))admAsesor = admAsesorDao.mapeaRegId(admSolicitud.getAsesorId());
				maestro 		= maestrosDao.mapeaRegId(admSolicitud.getAsesorId());
				existeSol		= true;
				estado 			= Integer.parseInt(admSolicitud.getEstado());
				existeCita		= admReservadaDao.existeReservacion(folio, "'A','C'");				 
			}			
			activo		 	= true;
		}
		if(admProcesoDao.existeReg(folio)) {
			admProceso = admProcesoDao.mapeaRegId(folio);
		}

		boolean tienePago = false;
		if (admPasosDao.existeReg(folio,"1")){
			tienePago 		= true;
		}

		if (admPasosDao.existeReg(folio,"6")){
			admPasos = admPasosDao.mapeaRegId(folio, "6");
		}
		
		if (admCartaSubirDao.existeReg(folio)){
			admCartaSubir = admCartaSubirDao.mapeaRegId(folio);
		}

		if (admBancoDao.existeReg(folio)){
			admBanco = admBancoDao.mapeaRegId(folio);
			tieneBanco = true;
		}
		
		AdmDirecto admDirecto = new AdmDirecto();		
		if (admDirectoDao.existeReg(folio)){
			admDirecto = admDirectoDao.mapeaRegId(folio);
		}
		
		if(!admDirecto.getMatricula().equals("0") && !admDirecto.getPlanId().equals("-")){
			promedio = admDirectoDao.promedio(admDirecto.getMatricula(), admDirecto.getPlanId());
			reprobadas 	= admDirectoDao.getReprobadas(admDirecto.getMatricula(), admDirecto.getPlanId());
		}
		int dirAvance = 0;
		if (admDirecto.getTetra().equals("S") && admDirecto.getReciente().equals("S") && admDirecto.getPrepaValido().equals("S") && reprobadas.equals("0") && (Float.parseFloat(promedio) >= 90)) {
			dirAvance = 2;
		}else if (admDirecto.getTetra().equals("S") || admDirecto.getReciente().equals("S") || admDirecto.getPrepaValido().equals("S") || reprobadas.equals("0") || (Float.parseFloat(promedio) >= 90)){
			dirAvance = 1;
		}
		
		int solAvance = 0;
		if (admSolicitudDao.existeReg(folio) && estado > 1){
			solAvance = 2;				
		}else if (admAcademicoDao.existeReg(folio)){
			solAvance = 1;			
		}
		
		int docAvance = 0;
		if (estado > 2){
			docAvance = 2;
		}else if (admDocAlumDao.docRegistrados(folio) >= 1) {
			docAvance = 1;
		}
		
		modelo.addAttribute("admPasos", admPasos);
		modelo.addAttribute("admSolicitud", admSolicitud);
		modelo.addAttribute("admCartaSubir", admCartaSubir);
		modelo.addAttribute("admAsesor", admAsesor);
		modelo.addAttribute("admBanco", admBanco);
		modelo.addAttribute("maestro", maestro);
		modelo.addAttribute("estado", estado);
		modelo.addAttribute("activo", activo);
		modelo.addAttribute("existeSol", existeSol);
		modelo.addAttribute("existeEval", existeEval);		
		modelo.addAttribute("admProceso", admProceso);
		modelo.addAttribute("existeCita", existeCita);
		modelo.addAttribute("tienePago", tienePago);
		modelo.addAttribute("tieneBanco", tieneBanco);
		modelo.addAttribute("solAvance", solAvance);
		modelo.addAttribute("docAvance", docAvance);
		modelo.addAttribute("dirAvance", dirAvance);
		modelo.addAttribute("planId", planId);
		modelo.addAttribute("mapOpcionPlan", mapOpcionPlan);
		modelo.addAttribute("parametros", parametros);
		if (existeSol) {
			return "inicial";
		}else{
			return "redirect:/login";
		}	
	}

	@RequestMapping("/crearNuevaSolicitud")
	public String crearNuevaSolicitud(HttpServletRequest request, Model modelo){
		String folio = "";
		String usuarioId = request.getParameter("UsuarioId")==null?"0":request.getParameter("UsuarioId");
		
		AdmSolicitud admSolicitud = new AdmSolicitud();
		try{
			folio = admSolicitudDao.maximoReg();
			System.out.println("Nuevo folio: "+folio);
			if(!admSolicitudDao.existeReg(folio)){
				admSolicitud.setFecha(adm.util.Fecha.getHoy());
				admSolicitud.setFechaIngreso(adm.util.Fecha.getHoy());
				admSolicitud.setAgente("0");
				admSolicitud.setClave("-");
				admSolicitud.setFolio(folio);
				admSolicitud.setUsuarioId(usuarioId);
				admSolicitud.setEstado("1");
				if (admSolicitudDao.insertReg(admSolicitud)) {
					System.out.println("SolicitudCreada: "+usuarioId+"-"+folio);
					try {
						AdmProceso admProceso = new AdmProceso();
						admProceso.setFolio(folio);
						admProceso.setFechaRegistro(adm.util.Fecha.getHoy());
						if(!admProcesoDao.existeReg(folio)) {
							if (admProcesoDao.insertReg(admProceso)) {
								System.out.println("Proceso creado: "+folio);						
							}
						}
					}catch(Exception ex){
						System.out.println("Error"+ex);
					}
				}
			}
		}catch (Exception ex){
			System.out.println("Error: Error al crear solicitud : "+ex);
		}

		return "redirect:/inicio";
	}

	@RequestMapping("/borrarSolicitud")
	public String borrarSolicitud(HttpServletRequest request, Model modelo){
		String folio = request.getParameter("Folio")==null?"0":request.getParameter("Folio");
		try{
			if(admSolicitudDao.existeReg(folio)){
				admSolicitudDao.deleteReg(folio);
			}
		}catch (Exception ex){
			System.out.println("Error: raizBorrarSolicitud:"+ex);
		}

		return "redirect:/inicio";
	}
	
	@RequestMapping("/descargarCartaAdmision")
	public void descargarCartaAdmision(HttpServletRequest request, HttpServletResponse response ) {
		
		AdmCartaSubir carta = new AdmCartaSubir();
		String folio 			= ""; 
		HttpSession  sesion		= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			folio 			= (String)sesion.getAttribute("Folio")==null?"0":(String)sesion.getAttribute("Folio");
		}
		
		try {						
			if(admCartaSubirDao.existeReg(folio)){
				carta = admCartaSubirDao.mapeaRegId(folio);	
				
				OutputStream out = response.getOutputStream();
				response.setHeader("Content-Disposition","attachment; filename=\""+ carta.getNombre()+"\"");
				out.write(carta.getCarta());
				
			}
		}catch(Exception ex){
			System.out.println("Error : Descargar carta Admision : "+ex);
		}			
	}
	
	@RequestMapping("/referencias")
	public String referencias(HttpServletRequest request, Model modelo) {
		String folio = "";

		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){		
			folio 		= (String)sesion.getAttribute("Folio")==null?"0":(String)sesion.getAttribute("Folio");
		}

		String nombre			= "";
		
		boolean tieneRecibo		= false;
		boolean tienePago 		= false;
		
		AdmSolicitud admSolicitud	= new AdmSolicitud();
		AdmParametros admParametros = admParametrosDao.mapeaRegId("1");
		
		if (admSolicitudDao.existeReg(folio)) {
			admSolicitud 		= admSolicitudDao.mapeaRegId(folio);
			nombre = admSolicitud.getNombre()+" "+admSolicitud.getApellidoPaterno();
		}
		
		String institucion 		= parametrosDao.mapeaRegId("1").getInstitucion();
		
		AdmCartaSubir admCartaSubir = new AdmCartaSubir();
		
		if (admCartaSubirDao.existeReg(folio)){
			admCartaSubir = admCartaSubirDao.mapeaRegId(folio);
		}
		
		List<AdmPago> lisPagos = admPagoDao.listPagos(folio);
		
		if (admPagoDao.existeReg(folio)){
			tieneRecibo 		= true;
		}  

		if(admPasosDao.existeReg(folio, "1")){
			tienePago = true;
		}
		
		modelo.addAttribute("SubMenu", "5");
		modelo.addAttribute("admCartaSubir", admCartaSubir);
		modelo.addAttribute("admParametros", admParametros);
		modelo.addAttribute("nombre", nombre);
		modelo.addAttribute("tieneRecibo", tieneRecibo);
		modelo.addAttribute("tienePago", tienePago);
		modelo.addAttribute("institucion", institucion);
		modelo.addAttribute("lisPagos", lisPagos);

		modelo.addAttribute("admSolicitud", admSolicitud);
		
		return "referencias";
	}
	
	@RequestMapping("/grabarPago")
	public String solicitudGrabarPago(@RequestParam("imagen") MultipartFile file, HttpServletRequest request){
		String folio 		= "0";
		String usuario 		= "";
		String usuarioId	= "0";
		
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){		
			folio 		= (String)sesion.getAttribute("Folio")==null?"0":(String)sesion.getAttribute("Folio");
			usuarioId 	= (String)sesion.getAttribute("UsuarioId")==null?"0":(String)sesion.getAttribute("UsuarioId");
			usuario		= (String)sesion.getAttribute("Usuario");
		}
		adm.documento.spring.AdmImagen imagen = new adm.documento.spring.AdmImagen();
		String cantidad 	= request.getParameter("Cantidad") == null ? "0" : request.getParameter("Cantidad");
		String metodo 		= request.getParameter("Metodo") == null ? "-" : request.getParameter("Metodo");
		String recibo 		= request.getParameter("Recibo") == null ? "0" : request.getParameter("Recibo").trim();
		String comentario	= request.getParameter("Comentario") == null ? "-" : request.getParameter("Comentario");
 		String fecha 			= adm.util.Fecha.getHoy();
				
		boolean esImagen 	= false;
		AdmPago admPago = new AdmPago();
		String nombreArchivo = file.getOriginalFilename();
		
		admPago.setFolio(folio);
		admPago.setCantidad(cantidad);
		admPago.setRecibo(recibo);
		admPago.setComentario(comentario);
		admPago.setFecha(fecha);
		try {
			admPago.setArchivo(file.getBytes());
		} catch (IOException e) {
			System.out.println("Image error");
			e.printStackTrace();		
		}
		admPago.setNombre(nombreArchivo);
		admPago.setMetodo(metodo);
		
		
		// Verifica que el archivo sea una imagen con extension jgp o png
		if (file.getOriginalFilename().toLowerCase().contains(".jpg")||file.getOriginalFilename().toLowerCase().contains(".png")||file.getOriginalFilename().toLowerCase().contains(".jpeg")) esImagen = true;

		if (!esImagen) {
			System.out.println("Not an image");
		}
		String grabo = "";
		if (!admPagoDao.existeReg(folio)){			
			if(admPagoDao.insertReg(admPago)) {
				grabo = "SI";
			}
			else {
				grabo = "NO";
			}
		}else {
			if(admPagoDao.updateReg(admPago)) {
				grabo = "SI";
			}
			else {
				grabo = "NO";
			}
		}
		
		
		return "redirect:/referencias?Folio="+folio+"&Grabo="+grabo;
	}
	
	@RequestMapping("/borrarPaso")
	public String borrarPaso(HttpServletRequest request){
		String folio 	= request.getParameter("Folio")==null?"0":request.getParameter("Folio");		
		String folioS 	= "";
		String paso 	= request.getParameter("Paso")==null?"0":request.getParameter("Paso");
		
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){		
			folioS		= (String)sesion.getAttribute("Folio")==null?"0":(String)sesion.getAttribute("Folio");
		}

		if(folioS.equals(folio)){

			if (admPasosDao.existeReg(folio, paso)){
				admPasosDao.deleteReg(folio, paso);
			}

			if(admPagoDao.existeReg(folio)) {
				admPagoDao.deleteReg(folio);
			}
		}else{
			return "redirect:inicio";
		}

		return "redirect:referencias?Folio="+folio;
	}
	
	@RequestMapping("/descargarPago")
	public void descargarPago(HttpServletResponse response, HttpServletRequest request){
		String folio 			= request.getParameter("Folio")==null?"0":request.getParameter("Folio");  
		String folioS 	= "";

		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){		
			folioS		= (String)sesion.getAttribute("Folio")==null?"0":(String)sesion.getAttribute("Folio");
		}

		AdmPago admPago		= new AdmPago();
		
		try {	
			if(folioS.equals(folio)){		
				if(admPagoDao.existeReg(folio)){
					admPago = admPagoDao.mapeaRegId(folio);
					
					OutputStream out = response.getOutputStream();
					response.setHeader("Content-Disposition","attachment; filename=\""+ admPago.getNombre()+"\"");
					out.write(admPago.getArchivo());
					
				}
			}
		}catch(Exception ex){
			System.out.println("Error:grabarPago/descargarPago:"+ex);
		}
	}
	
	@RequestMapping("/qr")
	public ResponseEntity<byte[]> qr(HttpServletRequest request){
		
		String textoQR = "https://admision.um.edu.mx/admision/verificaQr?Codigo=123";
//		String textoQR = "localhost:8085/admision/verificaQr?Codigo=123";
		
		byte[] bytes = QRCode.from(textoQR).withSize(120, 120).stream().toByteArray();
		
		 final HttpHeaders headers = new HttpHeaders();
		  headers.setContentType(MediaType.IMAGE_PNG);
		  headers.setContentLength(bytes.length);
		  return new ResponseEntity<byte[]> (bytes, headers, HttpStatus.CREATED); 
	}
	
	@RequestMapping(value={"/verificaQr"})
	public String verificaQr(HttpServletRequest request, Model modelo){
		
		String pagina = "codigoError";
		
		String codigo 	= request.getParameter("Codigo")==null?"0":request.getParameter("Codigo");
		
		if(codigo.equals("123")) {
			pagina = "codigoExitoso";
		}
		
		return pagina;
	}

	@RequestMapping(value={"/menu"})
	public String menu(HttpServletRequest request, Model modelo){
		
		//System.out.println(mensajes.getMessage("adm.titulo", null, LocaleContextHolder.getLocale()));
		//System.out.println(LocaleContextHolder.getLocale().toString());
		
		return "menu";
	}
	
	@RequestMapping(value={"/fotoMenu"})
	public void fotoMenu(HttpServletRequest request, HttpServletResponse response){
		
		String usuarioId 			= "0";
		AdmFoto admFoto			 = new AdmFoto();
		
		HttpSession sesion		= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			usuarioId 			= (String)sesion.getAttribute("UsuarioId")==null?"0":(String)sesion.getAttribute("UsuarioId");
			try{
        		if (admFotoDao.existeReg(usuarioId)){
        			admFoto = admFotoDao.mapeaRegId(usuarioId);
        		}			
        		OutputStream out = response.getOutputStream();    		
        		out.write(admFoto.getFoto());
        		out.close();
        	}catch(Exception ex){
        		System.out.println("Error /foto:"+ex);
        	}
		}
	}	
	
	@RequestMapping("/registro")
	public String registro(HttpServletRequest request, Model modelo){
		
		String error 				= request.getParameter("Error")==null?"0":request.getParameter("Error");
		String usuarioId 			= "0";
		boolean existe 				= false;

		AdmUsuario admUsuario		= new AdmUsuario();
		AdmFoto admFoto				= new AdmFoto();
		AdmParametros admParametros = admParametrosDao.getAll(" ORDER BY ID").get(0);

		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			usuarioId			= (String)sesion.getAttribute("UsuarioId")==null?"0":(String)sesion.getAttribute("UsuarioId");
			if (admUsuarioDao.existeReg(usuarioId)) {
				admUsuario = admUsuarioDao.mapeaRegId(usuarioId);
				existe = true;
			}
			if (admFotoDao.existeReg(usuarioId)) {
				admFoto = admFotoDao.mapeaRegId(usuarioId);
			}
		}	
		
		modelo.addAttribute("admUsuario", admUsuario);	
		modelo.addAttribute("admParametros", admParametros);	
		modelo.addAttribute("existe", existe);
		modelo.addAttribute("admFoto", admFoto);
		modelo.addAttribute("error", error);
		
		return "registro";
	}
	
	@RequestMapping("/subirFoto")
	public String subirFoto(HttpServletRequest request, Model modelo){
		String mensaje 	= "0";

		modelo.addAttribute("mensaje", mensaje);
		
		return "subirFoto";
	}
	
	@RequestMapping("/guardarFoto")
	public String documentosGuardarArchivo(@RequestParam("archivo") MultipartFile file, HttpServletRequest request, Model modelo){
		
		AdmFoto admFoto			 	= new AdmFoto();
		String folio 				= "0";
		String mensaje 				= "0";
		boolean esImagen			= false;
		
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			folio 			= (String)sesion.getAttribute("Folio")==null?"0":(String)sesion.getAttribute("Folio");
			if (admFotoDao.existeReg(folio)) {
				admFoto = admFotoDao.mapeaRegId(folio);
			}else {
				admFoto.setFolio(folio);
			}
		}	
		try {
			if(!folio.equals("0")) {
				admFoto.setFoto(file.getBytes());
				admFoto.setFecha(adm.util.Fecha.getHoy());
				
				if (file.getOriginalFilename().toLowerCase().contains(".png") || file.getOriginalFilename().toLowerCase().contains(".jpg")) esImagen = true;
				
				if(esImagen) {
					if(admFotoDao.existeReg(folio)){
						if(admFotoDao.updateReg(admFoto)) {
							mensaje = "1";
						}
					}else{
						if(admFotoDao.insertReg(admFoto)) {
							mensaje = "1";
						}
					}
				}
			}
		}catch(Exception ex){
			
		}
		
		modelo.addAttribute("mensaje", mensaje);
		
		return "subirFoto";
	}
	
	@ResponseBody
	@RequestMapping(value="/verificaUsuario")
	public String verificaUsuario(HttpServletRequest request){
		String usuario 				= request.getParameter("Usuario")==null?"usuario0":request.getParameter("Usuario");
		String mensaje				= "Disponible";
		if (admSolicitudDao.existeUsuario(usuario)) {
			mensaje = "Usuario no disponible";
		}
		
		return mensaje;
	}	
	
	@RequestMapping("/grabarRegistro")
	public String grabarRegistro(HttpServletRequest request, Model modelo){
		
		String usuario 				= request.getParameter("Email")==null?"0":request.getParameter("Email").toLowerCase();
		String clave				= request.getParameter("clave")==null?"0":request.getParameter("clave");	
		String confClave			= request.getParameter("confClave")==null?"0":request.getParameter("confClave");		
		String claveDigest			= adm.util.Encriptar.md5ConBase64(clave);
		String usuarioId 			= "0";
		String mensaje 				= "-";
		AdmUsuario admUsuario		= new AdmUsuario();
		AdmProceso admProceso		= new AdmProceso();
		boolean existe 				= false;

		HttpSession sesion			= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			usuarioId 					= (String)sesion.getAttribute("UsuarioId")==null?"0":(String)sesion.getAttribute("UsuarioId");
			if (admUsuarioDao.existeReg(usuarioId)) {
				admUsuario 			= admUsuarioDao.mapeaRegId(usuarioId);
				existe 				= true;
			}
		}
		
		// Quitar acentos
		usuario = usuario.replaceAll("á","a").replaceAll("é","e").replaceAll("í","i").replaceAll("ó","o").replaceAll("ú","u");
		usuario = usuario.replaceAll("Á","A").replaceAll("É","E").replaceAll("Í","I").replaceAll("Ó","O").replaceAll("Ú","U");
		
		String nombre = adm.util.Utilerias.quitarLetrasEspeciales(request.getParameter("Nombre").trim().toUpperCase());
		admUsuario.setNombre(nombre);

		String paterno = adm.util.Utilerias.quitarLetrasEspeciales(request.getParameter("Paterno").trim().toUpperCase());
		admUsuario.setApellidoPaterno(paterno);

		String materno = adm.util.Utilerias.quitarLetrasEspeciales(request.getParameter("Materno").trim().toUpperCase());
		admUsuario.setApellidoMaterno(materno);

		admUsuario.setGenero(request.getParameter("Genero"));
		admUsuario.setEmail(usuario);
		admUsuario.setCuenta(usuario);
		admUsuario.setTelefono(request.getParameter("Telefono"));
		admUsuario.setFechaNac(request.getParameter("FechaNac"));
		
		String codigo = UUID.randomUUID().toString().substring(0, 6).toUpperCase();
		String texto 	= "Code: "+codigo;
		
		// Si no existe
		if (!existe){
			if(clave.equals(confClave)){
				// Verifica nuevo usuario no se encuentra en 
				if(!admUsuarioDao.existeCuenta(usuario) && !admUsuarioDao.existeEmail(usuario) && !usuario.equals("")){
					System.out.println("INSERT REGISTRATION: "+usuario);	
					admUsuario.setUsuarioId(admUsuarioDao.maximoReg());
					admUsuario.setClave(claveDigest);
					admUsuario.setGenero("M");
					admUsuario.setEstado("0");
					admUsuario.setCodigo(codigo);
					if(admUsuarioDao.insertReg(admUsuario)){
						// Send Code Email
						try{
							mailService.sendVerificationCode(usuario, codigo);
						}catch(Exception ex){			
							System.out.println("Error: enviarCodigo| "+ex);
						}

						admProceso.setFolio(admUsuario.getUsuarioId());

						admProcesoDao.insertReg(admProceso);

						return "redirect:/valida?usuario="+admUsuario.getCuenta()+"&clave="+clave+"&redir=0";
					}else{
						mensaje = "enter_all_the_mandatory_fields";
					}
				}else{
					mensaje = "user_already_exists";		
				}	
			}else{
				mensaje = "passwords_do_not_match";	
			}	
		}else{
			System.out.println("UPDATE REGISTRATION: "+usuario);
			admUsuario.setUsuarioId(usuarioId);			
			admUsuario.setEstado(request.getParameter("estado")==null?"0":request.getParameter("estado"));
			admUsuario.setGenero("M");
			// admUsuario.setCodigo(codigo);
			if(admUsuarioDao.updateReg(admUsuario)){
				mensaje = "Updated...";
			}else{
				mensaje = "There was an error while updating. Try again";
			}
		}
		
		return "redirect:/registro?Mensaje="+mensaje;
	}
	
	@RequestMapping("/terminarRegistro")
	public String terminarRegistro(HttpServletRequest request, Model modelo){
		
		String codigo 		= request.getParameter("Codigo")==null?"0":request.getParameter("Codigo");
		String usuarioId 	= "0";
		String error 		= "2";
		AdmSolicitud admSolicitud 	= new AdmSolicitud();
		AdmUsuario 	 admUsuario		= new AdmUsuario();
		
		HttpSession sesion		= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			usuarioId 				= (String)sesion.getAttribute("UsuarioId")==null?"0":(String)sesion.getAttribute("UsuarioId");			
		}
		if (admUsuarioDao.existeReg(usuarioId)) {
			admUsuario 	= admUsuarioDao.mapeaRegId(usuarioId);	
			if(codigo.equals(admUsuario.getCodigo())) {
				admUsuario.setEstado("1");
				if(admUsuarioDao.updateReg(admUsuario)) {
					System.out.println("User: "+usuarioId+" registered. "+adm.util.Fecha.getFechayHora());
				}
			}else {
				error = "1";
			}
		}else {
			error = "1";
		}
		
		return "redirect:/registro?Error="+error;
	}
	
	@RequestMapping("/restablecerContrasena")
	public String restablecerContrasena(HttpServletRequest request, Model modelo){
		
		String usuarioId 				= "0";
		AdmUsuario admUsuario 			= new AdmUsuario();
		boolean existe 					= false;
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			usuarioId = (String)sesion.getAttribute("UsuarioId")==null?"0":(String)sesion.getAttribute("UsuarioId");
			if (admUsuarioDao.existeReg(usuarioId)) {
				admUsuario = admUsuarioDao.mapeaRegId(usuarioId);
				existe = true;
			}
		}		
		modelo.addAttribute("admUsuario", admUsuario);
		modelo.addAttribute("existe", existe);
			
		return "restablecerContrasena";
	}
	
	@RequestMapping("/guardarContrasena")
	public String guardarContrasena(HttpServletRequest request, Model modelo){
		
		String claveActual		= request.getParameter("Actual")==null?"":request.getParameter("Actual");
		String claveNueva		= request.getParameter("Clave")==null?"":request.getParameter("Clave");
		String claveConfirmar	= request.getParameter("Confirmar")==null?"":request.getParameter("Confirmar");
		String mensaje 			= "-";
		
		String usuarioId 				= "0";
		AdmUsuario admUsuario 	= new AdmUsuario();
		HttpSession sesion		= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			usuarioId 			= (String)sesion.getAttribute("UsuarioId")==null?"0":(String)sesion.getAttribute("UsuarioId");
			if (admUsuarioDao.existeReg(usuarioId)) {
				admUsuario = admUsuarioDao.mapeaRegId(usuarioId);		
			}
			claveActual = adm.util.Encriptar.md5ConBase64(claveActual);
			claveNueva = adm.util.Encriptar.md5ConBase64(claveNueva);
			claveConfirmar = adm.util.Encriptar.md5ConBase64(claveConfirmar);			
			if(claveActual.equals(admUsuario.getClave())){
				if(claveNueva.equals(claveConfirmar)){
					// Modificar el registro del usuario
					admUsuario.setUsuarioId(usuarioId);
					admUsuario.setClave(claveNueva);						
					if(admUsuarioDao.updateClaveUsuario(usuarioId, claveNueva)){
						mensaje = "Password Updated...";
					}else{
						mensaje = "<font size=3 color=gray><b>Error while updating. Try again</b></font>";
					}
				}else{
					mensaje = "<font size=3 color=gray><b>The new password does not match</b></font>";
				}
			}else{
				mensaje = "<font size=3 color=gray><b>Incorrect password</b></font>";				
			}			
		}
	
		return "redirect:/restablecerContrasena?Mensaje="+mensaje;
	}
	
	@RequestMapping(value={"/","/login"})
	public String login(HttpServletRequest request, Model modelo){
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			sesion.setAttribute("IdiomaUsuario", "en");
		}	
		
		AdmParametros admParametros = admParametrosDao.getAll(" ORDER BY ID").get(0);

		modelo.addAttribute("admParametros", admParametros);
		
		return "login";
	}

	@RequestMapping("/intentaRecuperar")
	public String intentaRecuperar(HttpServletRequest request, Model modelo){	
		AdmParametros parametros = admParametrosDao.mapeaRegId("1");
		
		modelo.addAttribute("parametros", parametros);
		
		return "intentaRecuperar";
	}

	@RequestMapping("/enviaRecuperar")
	public String enviaRecuperar(HttpServletRequest request){
		
		String correo 		= request.getParameter("Email")==null?"0":request.getParameter("Email");
		String clave		= "-";
		String claveSecret 	= "-";
		boolean ok 			= false;
		String mensaje 		= "-";
		String url 			= "-";
		
		AdmSolicitud admSolicitud = new AdmSolicitud();
		AdmUsuario admUsuario = new AdmUsuario(); 
		AdmParametros admParametros	= admParametrosDao.mapeaRegId("1");
		if(admParametros.getInstitucion().equals("Pacific Adventist University")){ url = "https://academic.pau.ac.pg/admission"; }
		if(admParametros.getInstitucion().equals("Sonoma")){ url = "https://admissions.sonoma.ac.pg/admission"; }
		if(admParametros.getInstitucion().equals("Fulton")){ url = "https://fulton.um.edu.mx/admission"; }

		if (admUsuarioDao.existeCuenta(correo)){
			admUsuario 		= admUsuarioDao.mapeaRegPorUsuario(correo);
			admSolicitud 	= admSolicitudDao.mapeaRegPorUsuario(admUsuario.getUsuarioId());

			String[] nombre = admUsuario.getNombre().trim().split(" ");

			clave 			= nombre[0]+admSolicitud.getFolio();
			claveSecret		= Encriptar.md5ConBase64(clave);

			AdmRecuperar admRecuperar = new AdmRecuperar();
			admRecuperar.setCorreo(correo);
			admRecuperar.setFecha(adm.util.Fecha.getHoy());
			admRecuperar.setClave(claveSecret);

			if (admRecuperarDao.existeReg(correo)) {
				if (admRecuperarDao.updateReg(admRecuperar)) ok = true;
			}else {
				if (admRecuperarDao.insertReg(admRecuperar)) ok = true;
			}
			
		}
		if (ok) {
			String texto = "We have received your request to reset your password, please click on the following link to continue the process.\n"
				+ "Link: "+url+"/recuperarContrasena?correo="+correo+"&clave="+claveSecret.replace("=", "").replace("+", "%2B")+" \n\n"
				+ "If you do not recognize this action, ignore this email and notify the System's Department.\n";	
			try {
				mensaje = "0";
				mailService.sendPasswordRecovery(correo, texto);
			} catch (Exception ex) {
				System.out.println("Error:"+ex);
			}
		}else {
			mensaje = "1";
		}	
		
		return "redirect:/intentaRecuperar?Mensaje="+mensaje+"&Email="+correo;
	}

	@RequestMapping("/recuperarContrasena")
	public String recuperarContrasena(HttpServletRequest request, Model modelo){
		String correo 	= request.getParameter("correo")==null?"-":request.getParameter("correo");	
		String clave	= request.getParameter("clave")==null?"-":request.getParameter("clave");
 
		AdmUsuario admUsuario = new AdmUsuario();
		if(admUsuarioDao.existeCuenta(correo)){
			admUsuario = admUsuarioDao.mapeaRegPorUsuario(correo);
		}
		
		AdmRecuperar admRecuperar = new AdmRecuperar();
		if(admRecuperarDao.existeClave(correo, clave+"==")){
			admRecuperarDao.mapeaRegId(correo);
		}
		
		modelo.addAttribute("admUsuario", admUsuario);
		modelo.addAttribute("admRecuperar", admRecuperar);
		modelo.addAttribute("clave", clave);

		return "recuperarContrasena";
	}

	@RequestMapping("/guardarNuevaContrasena")
	public String guardarNuevaContrasena(HttpServletRequest request, Model modelo){
		String cuenta = request.getParameter("Cuenta")==null?"":request.getParameter("Cuenta");
		String clave = request.getParameter("Clave")==null?"":request.getParameter("Clave");
		String nueva = request.getParameter("Nueva")==null?"":request.getParameter("Nueva");
		String confirmar = request.getParameter("Confirmar")==null?"":request.getParameter("Confirmar");
		String claveBase = "";
		String mensaje = "";
		String url = "redirect:/recuperarContrasena";

		AdmUsuario admUsuario = new AdmUsuario();
		if(admUsuarioDao.existeCuenta(cuenta)){
			admUsuario = admUsuarioDao.mapeaRegPorUsuario(cuenta);
		}

		AdmRecuperar admRecuperar = new AdmRecuperar();

		if(admRecuperarDao.existeClave(cuenta, clave+"==")){
			admRecuperar = admRecuperarDao.mapeaRegId(cuenta);
			if(nueva.equals(confirmar)){
				claveBase = adm.util.Encriptar.md5ConBase64(nueva);
				if(admUsuarioDao.updateClaveUsuario(admUsuario.getUsuarioId(), claveBase)){
					if(admRecuperarDao.deleteReg(cuenta)){
						url = "redirect:/login";
						mensaje = "1";
					}
				}else{
					mensaje = "0";		
				}
			}else{
				mensaje = "2";
			}
		}else{
			mensaje = "3";
		}

		return url+"?Mensaje="+mensaje;
	}

	@RequestMapping("/valida")
	public String valida(HttpServletRequest request, Model modelo){
		
		String folio 				= "0";		
		AdmUsuario admUsuario 		= new AdmUsuario();
		AdmSolicitud admSolicitud	= new AdmSolicitud();
		boolean existeSolicitud		= false;
		boolean mal 				= false;
		boolean sustituir 			= false;
		boolean existeUsuario 		= false;
		boolean existeFoto			= false;
		int redireccion 			= 0;
		String usuarioId 			= "";
		String carrera				= "";
		
		AdmFoto admFoto			= new AdmFoto();
		
		HashMap<String, AdmAcademico> mapAcademico = admAcademicoDao.mapaPorUsuario(usuarioId);
		HashMap<String, CatCarrera> mapCarrera = catCarreraDao.mapaCarreras();
		
		try{
			folio 		= (Integer.parseInt(request.getParameter("52we1f56HGkljuymCVFfhjbg"))/Integer.parseInt(request.getParameter("tgr09thujtgrjiu")))+"";
			usuarioId 	= admSolicitudDao.mapeaRegId(folio).getUsuarioId();			
			sustituir 	= true;
		}catch(Exception e){
			mal = true;
		}
		
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){	
			if(request.getParameter("52we1f56HGkljuymCVFfhjbg")==null & request.getParameter("tgr09thujtgrjiu")==null) mal = false;
			if(!mal){
				
				String usuario			= request.getParameter("usuario")==null?"0":request.getParameter("usuario").toLowerCase();
				String clave			= request.getParameter("clave")==null?"0":request.getParameter("clave");
				String claveDigest		= "";
				redireccion 			= Integer.parseInt(request.getParameter("redir"));		
				
				if(!sustituir){	
					try {
						// Encriptacion de la clave						
						java.security.MessageDigest md5	= java.security.MessageDigest.getInstance("MD5");				
						md5.update(clave.getBytes("UTF-8"));
						byte raw[] 		= md5.digest();
						claveDigest		=  java.util.Base64.getEncoder().encodeToString(raw);
						if(admUsuarioDao.existeUsuario(usuario, claveDigest) || admRecuperarDao.existeClave(usuario, claveDigest) ){
							if (admRecuperarDao.existeClave(usuario, claveDigest)) {
								if (admUsuarioDao.updateClave(usuario, claveDigest)) {
									admRecuperarDao.deleteReg(usuario);
								}
							}
							
							usuarioId 	= admUsuarioDao.getUsuarioId(usuario, claveDigest);
							admUsuario 	= admUsuarioDao.mapeaRegId(usuarioId);
							folio 		= admSolicitudDao.getFolioReciente(usuarioId);
							sesion.setAttribute("Usuario", admUsuario.getCuenta());
							sesion.setAttribute("UsuarioId", admUsuario.getUsuarioId());
							sesion.setAttribute("Folio", folio);							
							sesion.setAttribute("Genero", admUsuario.getGenero());
							sesion.setAttribute("UsuarioNombre", admUsuario.getNombre()+" "+admUsuario.getApellidoPaterno() );
							sesion.setAttribute("Carrera", carrera);
							sesion.setAttribute("PlanId", "00000000");
							
							existeUsuario = true;							
						}else{
							existeUsuario = false;
						}
					}catch(Exception ex) {
						System.out.println("Error:"+ex);
					}	
				}else{
					
					admUsuario 	= admUsuarioDao.mapeaRegId(usuarioId);
					folio 		= admSolicitudDao.getFolioReciente(usuarioId);
					sesion.setAttribute("Usuario", admUsuario.getCuenta());
					sesion.setAttribute("UsuarioId", admUsuario.getUsuarioId());
					sesion.setAttribute("Folio", folio);					
					sesion.setAttribute("Genero", admUsuario.getGenero());
					sesion.setAttribute("UsuarioNombre", admUsuario.getNombre()+" "+admUsuario.getApellidoPaterno() );
					sesion.setAttribute("Carrera", carrera);
					existeUsuario = true;
				}			
			}	
			//System.out.println(usuarioId);
			if (admFotoDao.tieneFoto(usuarioId)) {
				existeFoto = true;
			}			
			sesion.setAttribute("existeUsuario", existeUsuario);
			sesion.setAttribute("existeFoto", existeFoto);		
		}		
		
		folio 		= admSolicitudDao.getFolioReciente(usuarioId);
		existeSolicitud = folio.equals("0") ? false : true;
		
		modelo.addAttribute("existeSolicitud", existeSolicitud);
		modelo.addAttribute("sustituir", sustituir);
		modelo.addAttribute("existeUsuario", existeUsuario);
		modelo.addAttribute("admUsuario", admUsuario);
		modelo.addAttribute("redireccion", redireccion);		
		
		return "valida";
	}
	
	@RequestMapping("/salir")
	public String salir(HttpServletRequest request){				
		HttpSession sesion	= ((HttpServletRequest)request).getSession();
		if (sesion!=null){
			sesion.invalidate();
		}		
		return "salir";
	}
	
	@RequestMapping("/encuesta")
	public String encuesta(HttpServletRequest request, Model modelo){	
		
		AdmParametros parametros = admParametrosDao.mapeaRegId("1");
		String folio			= request.getParameter("Folio") == null ? "0" : request.getParameter("Folio");
		String clave			= request.getParameter("Clave") == null ? "0" : request.getParameter("Clave");
		String recomendacionId	= request.getParameter("Id") == null ? "0" : request.getParameter("Id");
		String solicitante 		= folio+recomendacionId;
		String comparaClave 	= Encriptar.sha256ConBase64(solicitante);		
		String accion			= request.getParameter("Accion")==null?"0":request.getParameter("Accion");			
		String dias				= request.getParameter("Dias")==null?"0":request.getParameter("Dias");
		String meses			= request.getParameter("Meses")==null?"0":request.getParameter("Meses");
		String anos				= request.getParameter("Anos")==null?"0":request.getParameter("Anos");
		dias 	= dias.trim();
		meses 	= meses.trim();
		anos 	= anos.trim();
		if (adm.util.Fecha.isNumeric(dias)==false) dias = "0"; 
		if (adm.util.Fecha.isNumeric(meses)==false) meses = "0";
		if (adm.util.Fecha.isNumeric(anos)==false) anos = "0";
		
		String nombreCandidato 	= admSolicitudDao.getNombre(folio);
		boolean grabo 			= false;
		String relacion 	  	= "";
		String censura			= "";
		AdmEncuesta encuesta 	= new AdmEncuesta();
		boolean existe 			= false;
		if (admEncuestaDao.existeReg(folio, recomendacionId)){
			encuesta 	= admEncuestaDao.mapeaRegId(folio, recomendacionId);
			existe 		= true;
		}
		
		clave = clave.replace(" ", "+");

		comparaClave = comparaClave.replace("=", "");
		
		boolean autorizado 		= false;
		if(comparaClave.equals(clave)) {
			autorizado = true;
		}else if (Integer.parseInt(folio) <= 19821) {
			autorizado = true;
		}		
		
		if (accion.equals("1")){
			encuesta.setFolio(folio);
			encuesta.setRecomendacionId(recomendacionId);
			encuesta.setR1(request.getParameter("R1")==null?"0":request.getParameter("R1"));
			encuesta.setR2(request.getParameter("R2")==null?"0":request.getParameter("R2"));
			encuesta.setR3(request.getParameter("R3")==null?"0":request.getParameter("R3"));
			encuesta.setR4(request.getParameter("R4")==null?"0":request.getParameter("R4"));
			encuesta.setR5(request.getParameter("R5")==null?"0":request.getParameter("R5"));
			encuesta.setR6(request.getParameter("R6")==null?"0":request.getParameter("R6"));
			encuesta.setR7(request.getParameter("R7")==null?"0":request.getParameter("R7"));
			encuesta.setR8(request.getParameter("R8")==null?"0":request.getParameter("R8"));
			encuesta.setR9(request.getParameter("R9")==null?"0":request.getParameter("R9"));
			encuesta.setR10(request.getParameter("R10")==null?"0":request.getParameter("R10"));
			encuesta.setR11(request.getParameter("R11")==null?"0":request.getParameter("R11"));
			encuesta.setR12(request.getParameter("R12")==null?"0":request.getParameter("R12"));
			encuesta.setR13(request.getParameter("R13")==null?"0":request.getParameter("R13"));
			encuesta.setR14(request.getParameter("R14")==null?"0":request.getParameter("R14"));
			
			encuesta.setTiempo(dias+","+meses+","+anos);
			encuesta.setConocer(request.getParameter("Conocer"));
			encuesta.setRelacion(request.getParameter("Relacion"));
			if(request.getParameter("Otra")!=null){
				encuesta.setOtra(request.getParameter("Otra"));
			}else{
				encuesta.setOtra("-");
			}
			encuesta.setCensura(request.getParameter("Censura"));
			if(request.getParameter("Conducta")!=null){
				encuesta.setConducta(request.getParameter("Conducta"));
			}else{
				encuesta.setConducta("-");
			}
			encuesta.setOpinion(request.getParameter("Opinion"));
			encuesta.setAdicional(request.getParameter("Adicional"));
					
			if (admEncuestaDao.existeReg(folio, recomendacionId)){
				// Modifica la informacion de la tabla AdmAcademico
				if (admEncuestaDao.updateReg(encuesta)){
					grabo	= true;				
				}
			}else{
				// Insertar un nuevo registro en AdmAcademico
				if (admEncuestaDao.insertReg(encuesta)){
					grabo	= true;				
				}
			}
		}
		
		if(accion.equals("2")){
			
			admEncuestaDao.mapeaRegId(folio,recomendacionId);		
			encuesta.setR1(request.getParameter("R1")==null?"0":request.getParameter("R1"));
			encuesta.setR2(request.getParameter("R2")==null?"0":request.getParameter("R2"));
			encuesta.setR3(request.getParameter("R3")==null?"0":request.getParameter("R3"));
			encuesta.setR4(request.getParameter("R4")==null?"0":request.getParameter("R4"));
			encuesta.setR5(request.getParameter("R5")==null?"0":request.getParameter("R5"));
			encuesta.setR6(request.getParameter("R6")==null?"0":request.getParameter("R6"));
			encuesta.setR7(request.getParameter("R7")==null?"0":request.getParameter("R7"));
			encuesta.setR8(request.getParameter("R8")==null?"0":request.getParameter("R8"));
			encuesta.setR9(request.getParameter("R9")==null?"0":request.getParameter("R9"));
			encuesta.setR10(request.getParameter("R10")==null?"0":request.getParameter("R10"));
			encuesta.setR11(request.getParameter("R11")==null?"0":request.getParameter("R11"));
			encuesta.setR12(request.getParameter("R12")==null?"0":request.getParameter("R12"));
			encuesta.setR13(request.getParameter("R13")==null?"0":request.getParameter("R13"));
			encuesta.setR14(request.getParameter("R14")==null?"0":request.getParameter("R14"));
			
			encuesta.setTiempo(dias+","+meses+","+anos);		
			encuesta.setConocer(request.getParameter("Conocer")==null?"0":request.getParameter("Conocer"));
			
			relacion = request.getParameter("Relacion");
			encuesta.setRelacion(relacion);	
					
		}
		
		if(accion.equals("3")){
			
			admEncuestaDao.mapeaRegId(folio,recomendacionId);
			
			encuesta.setR1(request.getParameter("R1")==null?"0":request.getParameter("R1"));
			encuesta.setR2(request.getParameter("R2")==null?"0":request.getParameter("R2"));
			encuesta.setR3(request.getParameter("R3")==null?"0":request.getParameter("R3"));
			encuesta.setR4(request.getParameter("R4")==null?"0":request.getParameter("R4"));
			encuesta.setR5(request.getParameter("R5")==null?"0":request.getParameter("R5"));
			encuesta.setR6(request.getParameter("R6")==null?"0":request.getParameter("R6"));
			encuesta.setR7(request.getParameter("R7")==null?"0":request.getParameter("R7"));
			encuesta.setR8(request.getParameter("R8")==null?"0":request.getParameter("R8"));
			encuesta.setR9(request.getParameter("R9")==null?"0":request.getParameter("R9"));
			encuesta.setR10(request.getParameter("R10")==null?"0":request.getParameter("R10"));
			encuesta.setR11(request.getParameter("R11")==null?"0":request.getParameter("R11"));
			encuesta.setR12(request.getParameter("R12")==null?"0":request.getParameter("R12"));
			encuesta.setR13(request.getParameter("R13")==null?"0":request.getParameter("R13"));
			encuesta.setR14(request.getParameter("R14")==null?"0":request.getParameter("R14"));
			
			encuesta.setTiempo(dias+","+meses+","+anos);
			encuesta.setConocer(request.getParameter("Conocer"));
			
			relacion = request.getParameter("Relacion");
			encuesta.setRelacion(relacion);
			
			if(request.getParameter("Otra")!=null){
				encuesta.setOpinion(request.getParameter("Otra"));
			}
			
			censura = request.getParameter("Censura");
			encuesta.setCensura(censura);
					
		}	
		
		if(admEncuestaDao.existeReg(folio, recomendacionId)){
			admEncuestaDao.mapeaRegId(folio, recomendacionId);
			String tiempo = encuesta.getTiempo();
			String[] arrTiempo = tiempo.split(",");
			 dias = arrTiempo[0];
			 meses = arrTiempo[1]; 
			 anos = arrTiempo[2];
		}
		
		modelo.addAttribute("existe", existe);
		modelo.addAttribute("autorizado", autorizado);
		modelo.addAttribute("encuesta", encuesta);
		modelo.addAttribute("grabo", grabo);
		modelo.addAttribute("nombreCandidato", nombreCandidato);	
		modelo.addAttribute("parametros", parametros);	
		
		return "encuesta";
	}
	
	@RequestMapping("/archivo")
	public void archivo(HttpServletRequest request, HttpServletResponse response){		
		String ruta		= request.getParameter("ruta")==null?"":request.getParameter("ruta");
		String nombre	= request.getParameter("nombre")==null?"vacio.txt":request.getParameter("nombre");
		try{
			if(request.getParameter("ruta") != null){		
				java.io.File f = new java.io.File(ruta);		
				byte[] archivo = null;
				java.io.FileInputStream instream = null;		
				if(f.exists()){
					archivo = new byte[(int)f.length()];
					instream = new java.io.FileInputStream(ruta);
				}
				instream.read(archivo,0,(int)f.length());
				
				response.setContentType("application/octet-stream");
				response.setHeader("Content-Disposition","attachment; filename=\""+nombre+ "\"");
				response.getOutputStream().write(archivo);
				response.flushBuffer();
				instream.close();
			}
		}catch(Exception ex){
			System.out.println("Error:"+ex);
		};		
	}
}