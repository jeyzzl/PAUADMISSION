package adm.alumno.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class AdmEvaluacionNotaDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg( AdmEvaluacionNota objeto){
		boolean ok = false;		
		try{
			String comando = "INSERT INTO SALOMON.ADM_EVALUACION_NOTA(EVALUACION_ID, FOLIO, NOTA, FECHA, USUARIO)"
					+ "VALUES(TO_NUMBER(?,'99'),?,?,TO_DATE(?,'DD/MM/YYYY'),?)";
			Object[] parametros = new Object[] {objeto.getEvaluacionId(), objeto.getFolio(),objeto.getNota(), objeto.getFecha(), objeto.getUsuario()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionNotaDao|insertReg|:"+ex);			
		}		
		return ok;
	}	
	
	public boolean updateReg(AdmEvaluacionNota objeto) {
		boolean ok = false;		
		try{
			String comando = "UPDATE SALOMON.ADM_EVALUACION_NOTA SET NOTA = ?, FECHA = TO_DATE(?,'DD/MM/YYYY'), USUARIO = ? WHERE EVALUACION_ID = TO_NUMBER(?,'99') AND FOLIO = TO_NUMBER(?,'9999999')";
			Object[] parametros = new Object[] {objeto.getNota(),objeto.getFecha(), objeto.getUsuario(), objeto.getEvaluacionId(), objeto.getFolio()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionNotaDao|updateReg|:"+ex);		
		}		
		return ok;
	}	
	
	public boolean deleteReg(String evaluacionId, String folio) {
		boolean ok = false;		
		try{
			String comando = "DELETE FROM SALOMON.ADM_EVALUACION_NOTA WHERE EVALUACION_ID = TO_NUMBER(?,'99') AND FOLIO = TO_NUMBER(?,'9999999')";
			Object[] parametros = new Object[] {evaluacionId, folio};
			if (salomonJdbc.update(comando,parametros)==1){			
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionNotaDao|deleteReg|:"+ex);			
		}		
		return ok;
	}
	
	public AdmEvaluacionNota mapeaRegId(String evaluacionId, String folio) {
		AdmEvaluacionNota objeto = new AdmEvaluacionNota();		
		try{
			String comando = "SELECT EVALUACION_ID, FOLIO, NOTA, FECHA, USUARIO FROM SALOMON.ADM_EVALUACION_NOTA WHERE EVALUACION_ID = TO_NUMBER(?,'99') AND FOLIO = TO_NUMBER(?,'9999999')";
			Object[] parametros = new Object[] { evaluacionId,folio};
			objeto = salomonJdbc.queryForObject(comando, new AdmEvaluacionNotaMapper(), parametros);			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionNotaDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg( String evaluacionId, String folio) {
		boolean 		ok 	= false;		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_EVALUACION_NOTA WHERE EVALUACION_ID = TO_NUMBER(?,'99') AND FOLIO = TO_NUMBER(?,'9999999')"; 
			Object[] parametros = new Object[] {evaluacionId, folio};	
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){	
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionNotaDao|existeReg|:"+ex);
		}		
		return ok;
	}
	
	public int totalDeEvaluaciones( String folio) {
		int total 	= 0;		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_EVALUACION_NOTA WHERE FOLIO = TO_NUMBER(?,'9999999')";
			Object[] parametros = new Object[] {folio};	
			total = salomonJdbc.queryForObject(comando,Integer.class, parametros);					
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionNotaDao|totalDeEvaluaciones|:"+ex);
		}		
		return total;
	}
	
	public List<AdmEvaluacionNota> getListAll (){
		List<AdmEvaluacionNota> lista = new ArrayList<AdmEvaluacionNota>();		
		try {
			String comando = "SELECT EVALUACION_ID, FOLIO, NOTA, FECHA, USUARIO FROM SALOMON.ADM_EVALUACION_NOTA";			
			lista = salomonJdbc.query(comando, new AdmEvaluacionNotaMapper());
		}catch(Exception ex) {
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionNotaDao|getListAll|:"+ex);
		}
		return lista;
	}

	public HashMap<String,String> mapaResultados(){
		
		HashMap<String, String> mapa = new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();		
		try {
			String comando = "SELECT FOLIO AS LLAVE, COUNT(*) AS VALOR FROM SALOMON.ADM_EVALUACION_NOTA GROUP BY FOLIO";
			lista = salomonJdbc.query(comando, new adm.MapaMapper());
			for (adm.Mapa map : lista ){
				mapa.put(map.getLlave(), map.getValor());
			}
		}catch(Exception ex) {
			System.out.println("Error - adm.alumno.spring.AdmSolicitudDao|mapaResultados|:"+ex);
		}
		return mapa;
	}
	
	public HashMap<String,String> mapaResultadosExamenes(){
		
		HashMap<String, String> mapa = new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();		
		try {
			String comando = "SELECT FOLIO||EVALUACION_ID AS LLAVE, COUNT(*) AS VALOR FROM SALOMON.ADM_EVALUACION_NOTA GROUP BY FOLIO||EVALUACION_ID";
			lista = salomonJdbc.query(comando, new adm.MapaMapper());
			for (adm.Mapa map : lista ){
				mapa.put(map.getLlave(), map.getValor());
			}
		}catch(Exception ex) {
			System.out.println("Error - adm.alumno.spring.AdmSolicitudDao|mapaResultadosExamenes|:"+ex);
		}
		return mapa;
	}

	public HashMap<String,String> mapaNotaResultadosExamenes(){
		
		HashMap<String, String> mapa = new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();		
		try {
			String comando = "SELECT FOLIO||EVALUACION_ID AS LLAVE, NOTA AS VALOR FROM SALOMON.ADM_EVALUACION_NOTA";
			lista = salomonJdbc.query(comando, new adm.MapaMapper());
			for (adm.Mapa map : lista ){
				mapa.put(map.getLlave(), map.getValor());
			}
		}catch(Exception ex) {
			System.out.println("Error - adm.alumno.spring.AdmSolicitudDao|mapaNotaResultadosExamenes|:"+ex);
		}
		return mapa;
	}
	
	public HashMap<String,AdmEvaluacionNota> mapaNotaResultados(){
		
		HashMap<String, AdmEvaluacionNota> mapa = new HashMap<String, AdmEvaluacionNota>();
		List<AdmEvaluacionNota> lista = new ArrayList<AdmEvaluacionNota>();		
		try {
			String comando = "SELECT EVALUACION_ID, FOLIO, NOTA, TO_CHAR(FECHA,'YYYY/MM/DD HH24:MI:SS') AS FECHA, USUARIO FROM SALOMON.ADM_EVALUACION_NOTA";
			lista = salomonJdbc.query(comando, new AdmEvaluacionNotaMapper());
			for (AdmEvaluacionNota nota : lista ){
				mapa.put(nota.getFolio()+nota.getEvaluacionId(), nota);
			}
		}catch(Exception ex) {
			System.out.println("Error - aca.admision.spring.AdmSolicitudDao|mapaNotaResultados|:"+ex);
		}
		return mapa;
	}
	
}
