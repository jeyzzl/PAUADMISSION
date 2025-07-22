package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmEvaluacionDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg( AdmEvaluacion objeto){
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_EVALUACION(EVALUACION_ID, EVALUACION_NOMBRE, ACCESO, ESTADO, ICONO, PUNTOS)"
					+ "VALUES(TO_NUMBER(?,'99'),?,?,?,?,TO_NUMBER(?,'99'))";
			Object[] parametros = new Object[] {objeto.getEvaluacionId(), objeto.getEvaluacionNombre(),objeto.getAcceso(), objeto.getEstado(), objeto.getIcono(), objeto.getPuntos()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionDao|insertReg|:"+ex);			
		}
		
		return ok;
	}	
	
	public boolean updateReg(AdmEvaluacion objeto) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_EVALUACION SET ACCESO = ?, EVALUACION_NOMBRE = ?, ESTADO = ?, PUNTOS = TO_NUMBER(?,'999') WHERE EVALUACION_ID = TO_NUMBER(?,'99') ";
			Object[] parametros = new Object[] {objeto.getAcceso(), objeto.getEvaluacionNombre(),objeto.getEstado(), objeto.getPuntos(), objeto.getEvaluacionId()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionDao|updateReg|:"+ex);		
		}
		
		return ok;
	}	
	
	public boolean deleteReg(String evaluacionId) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_EVALUACION WHERE EVALUACION_ID = TO_NUMBER(?,'99')";
			Object[] parametros = new Object[] {evaluacionId};
			if (salomonJdbc.update(comando,parametros)==1){			
				ok = true;
			}	
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmEvaluacion mapeaRegId(String evaluacionId) {
		AdmEvaluacion objeto = new AdmEvaluacion();
		
		try{
			String comando = "SELECT EVALUACION_ID, EVALUACION_NOMBRE, ACCESO, ESTADO, ICONO, PUNTOS FROM SALOMON.ADM_EVALUACION WHERE EVALUACION_ID = TO_NUMBER(?,'99')";
			Object[] parametros = new Object[] { evaluacionId };
			objeto = salomonJdbc.queryForObject(comando, new AdmEvaluacionMapper(), parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg( String evaluacionId) {
		boolean 		ok 	= false;			
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_EVALUACION WHERE EVALUACION_ID = TO_NUMBER(?,'99')"; 
			Object[] parametros = new Object[] {evaluacionId};	
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){	
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public List<AdmEvaluacion> getListAll (String orden){
		List<AdmEvaluacion> lista = new ArrayList<AdmEvaluacion>();
		
		try {
			String comando = "SELECT EVALUACION_ID, EVALUACION_NOMBRE, ACCESO, ESTADO, ICONO, PUNTOS FROM SALOMON.ADM_EVALUACION "+ orden;	
			lista = salomonJdbc.query(comando, new AdmEvaluacionMapper());
		}catch(Exception ex) {
			System.out.println("Error - adm.alumno.spring.AdmEvaluacionDao|getListAll|:"+ex);
		}
		return lista;
	}

}
