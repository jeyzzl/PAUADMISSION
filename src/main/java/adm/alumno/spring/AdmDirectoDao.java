package adm.alumno.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmDirectoDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmDirecto objeto) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_DIRECTO"
					+ " (FOLIO, MATRICULA, PLAN_ID, RECIENTE, TETRA, REC_PREPA, REC_VRE, ENVIO_SOL, PREPA_VALIDO, VRE_VALIDO, NOMBRE_PREPA, NOMBRE_VRE)"
					+ " VALUES(TO_NUMBER(?,'99999999'), ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			Object[] parametros = new Object[] {
				objeto.getFolio(),objeto.getMatricula(),objeto.getPlanId(),objeto.getReciente(),objeto.getTetra(),objeto.getRecPrepa(),objeto.getRecVre(),
				objeto.getEnvioSol(),objeto.getPrepaValido(),objeto.getVreValido(),objeto.getNombrePrepa(),objeto.getNombreVre()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(AdmDirecto objeto) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_DIRECTO"
					+ " SET MATRICULA = ?,"
					+ " PLAN_ID = ?,"
					+ " RECIENTE = ?,"
					+ " TETRA = ?,"
					+ " REC_PREPA = ?,"
					+ " REC_VRE = ?,"
					+ " ENVIO_SOL = ?,"
					+ " PREPA_VALIDO = ?,"
					+ " VRE_VALIDO = ?,"
					+ " NOMBRE_PREPA = ?,"
					+ " NOMBRE_VRE = ?"
					+ " WHERE FOLIO = TO_NUMBER(?,'99999999')";
			
			Object[] parametros = new Object[] {
				objeto.getMatricula(),objeto.getPlanId(),objeto.getReciente(),objeto.getTetra(),objeto.getRecPrepa(),objeto.getRecVre(),
				objeto.getEnvioSol(),objeto.getPrepaValido(),objeto.getVreValido(),objeto.getNombrePrepa(),objeto.getNombreVre(),objeto.getFolio()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean enviarSolicitud(String folio) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_DIRECTO SET ENVIO_SOL = 'S' WHERE FOLIO = TO_NUMBER(?,'99999999')";
			
			if (salomonJdbc.update(comando,new Object[] {folio})==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|enviarSolicitud|:"+ex);
		}
		
		return ok;
	}
	
	public boolean deleteReg(String folio) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_DIRECTO WHERE FOLIO = TO_NUMBER(?,'99999999')";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmDirecto mapeaRegId(String folio) {
		AdmDirecto objeto = new AdmDirecto();
		
		try {
			String comando = "SELECT FOLIO, MATRICULA, PLAN_ID, RECIENTE, TETRA, REC_PREPA, REC_VRE, ENVIO_SOL, PREPA_VALIDO, VRE_VALIDO, NOMBRE_PREPA, NOMBRE_VRE"
					+ " FROM SALOMON.ADM_DIRECTO WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {folio};
			objeto = salomonJdbc.queryForObject(comando,  new AdmDirectoMapper(), parametros);
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String folio) {
		boolean ok = false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_DIRECTO WHERE FOLIO = TO_NUMBER(?,'9999999')";			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|existeReg|:"+ex);
		}
		
		return ok;
	}

	public String promedio(String matricula, String planId) {
		String promedio = "0";		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.ALUM_PLAN WHERE CODIGO_PERSONAL = ? AND PLAN_ID = ?";
			if (salomonJdbc.queryForObject(comando,Integer.class, matricula, planId)>=1){
				comando = "SELECT COALESCE(PROMEDIO,0) FROM ENOC.ALUM_PLAN WHERE CODIGO_PERSONAL = ? AND PLAN_ID = ?";			
				promedio = salomonJdbc.queryForObject(comando,String.class, matricula, planId);
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|promedio|:"+ex);
		}
		
		return promedio;
	}
	
	public String getReprobadas(String matricula, String planId) {
		String total = "0";		
		try{
			String comando = "SELECT COUNT(CURSO_ID) FROM ENOC.ALUMNO_CURSO WHERE CODIGO_PERSONAL = ? AND PLAN_ID = ? AND TIPOCAL_ID IN ('2','4')";			
			Object[] parametros = new Object[] {matricula, planId};
			total 	= salomonJdbc.queryForObject(comando,String.class, parametros);
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|getReprobadas|:"+ex);
		}
		
		return total;
	}

	public String nombreMatricula(String matricula) {
		String nombre = "";		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.ALUM_PERSONAL WHERE CODIGO_PERSONAL = ?";			
			if (salomonJdbc.queryForObject(comando,Integer.class, matricula)>=1){
				comando = "SELECT NOMBRE||' '||APELLIDO_PATERNO||' '||APELLIDO_MATERNO FROM ENOC.ALUM_PERSONAL WHERE CODIGO_PERSONAL = ?";		
				nombre = salomonJdbc.queryForObject(comando,String.class, matricula);
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|nombreMatricula|:"+ex);
		}		
		return nombre;
	}
	
	public boolean subirCartaPrepa(byte[] carta, String folio, String nombre) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_DIRECTO SET REC_PREPA = ?, NOMBRE_PREPA = ? WHERE FOLIO = TO_NUMBER(?,'9999999')";
			Object[] parametros = new Object[] {carta, nombre, folio};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|subirCartaPrepa|:"+ex);		
		}
		
		return ok;
	}

	public boolean subirCartaVre(byte[] carta, String folio, String nombre) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_DIRECTO SET REC_VRE = ?, NOMBRE_VRE = ? WHERE FOLIO = TO_NUMBER(?,'9999999')";
			Object[] parametros = new Object[] {carta, nombre, folio};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|subirCartaVre|:"+ex);		
		}
		
		return ok;
	}
	
	public HashMap<String, String> mapaPlanes(String matricula) {
		List<adm.Mapa> lista			= new ArrayList<adm.Mapa>();
		HashMap<String,String> mapa		= new HashMap<String,String>();
		
		try{
			String comando = "SELECT PLAN_ID AS LLAVE, NOMBRE_PLAN AS VALOR FROM ENOC.MAPA_PLAN"
					+ " WHERE PLAN_ID IN(SELECT DISTINCT(PLAN_ID) FROM ENOC.CARGA_ALUMNO WHERE CODIGO_PERSONAL = ?)";
			
			Object[] parametros = new Object[] {matricula};
			lista = salomonJdbc.query(comando, new adm.MapaMapper(), parametros);
			
			for (adm.Mapa map:lista){		
				mapa.put(map.getLlave(), map.getValor());
			}	
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|mapaPlanes|:"+ex);
		}
		
		return mapa;
	}

	public List<String> listaPlanes(String matricula) {
		List<String> lista = new ArrayList<String>();
		
		try{
			String comando = "SELECT PLAN_ID FROM ENOC.MAPA_PLAN"
					+ " WHERE PLAN_ID IN(SELECT DISTINCT(PLAN_ID) FROM ENOC.CARGA_ALUMNO WHERE CODIGO_PERSONAL = ?)";
			
			Object[] parametros = new Object[] {matricula};
			lista = salomonJdbc.queryForList(comando, String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmDirectoDao|listaPlanes|:"+ex);
		}
		
		return lista;
	}
}
