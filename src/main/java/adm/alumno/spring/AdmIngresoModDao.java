package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmIngresoModDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
		
		
	public boolean insertReg(AdmIngresoMod admIngresoMod) {
		boolean ok = false;
			
		try{
			String comando = "INSERT INTO SALOMON.ADM_INGRESO_MOD"+ 
				"(PERIODO_ID, MODALIDAD_ID) "+
				"VALUES(TO_NUMBER(?,'999'),TO_NUMBER(?,'99'))";
				
			Object[] parametros = new Object[] {
				admIngresoMod.getPeriodoId(),admIngresoMod.getModalidadId() };
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmIngresoModDao|insertReg|:"+ex);
		}

		return ok;
	}
		
		
	public boolean deleteReg(String periodoId, String modalidadId) {
		boolean ok = false;
			
		try{
			String comando = "DELETE FROM SALOMON.ADM_INGRESO_MOD "+ 
					"WHERE PERIODO_ID = TO_NUMBER(?,'999') " +
					"AND MODALIDAD_ID = TO_NUMBER(?,'99')";
				
			Object[] parametros = new Object[] {periodoId,modalidadId};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmIngresoModDao|deleteReg|:"+ex);
		}
			
		return ok;
	}
		
	public AdmIngresoMod mapeaRegId(String periodoId, String modalidadId) {
		AdmIngresoMod objeto = new AdmIngresoMod();
		
		try {
			String comando = "SELECT PERIODO_ID, MODALIDAD_ID "+
					"FROM SALOMON.ADM_INGRESO_MOD "+ 
					"WHERE PERIODO_ID = TO_NUMBER(?,'999') " +
					"AND MODALIDAD_ID = TO_NUMBER(?,'99')";
				
			Object[] parametros = new Object[] {periodoId,modalidadId};
			objeto = salomonJdbc.queryForObject(comando, new AdmIngresoModMapper(), parametros);
				
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.AdmIngresoModDao|mapeaRegId|:"+ex);
		}

		return objeto;
	}
		
	public boolean existeReg(String periodoId, String modalidadId) {
		boolean ok 		= false;
			
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_INGRESO_MOD "+ 
					"WHERE PERIODO_ID = TO_NUMBER(?,'999')" +
					"AND MODALIDAD_ID = TO_NUMBER(?,'99') ";
				
			Object[] parametros = new Object[] {periodoId,modalidadId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
				
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmIngresoModDao|existeReg|:"+ex);
		}
			
		return ok;
	}
		
	public String maximoReg(String periodoId) {
		String maximo		= "1";
			
		try{
			String comando = "SELECT COALESCE(MAX(PERIODO_ID)+1,1) AS MAXIMO FROM SALOMON.ADM_INGRESO_MOD "+ 
						     "WHERE PERIODO_ID = TO_NUMBER(?,'999') ";		
				
			Object[] parametros = new Object[] {periodoId};
			maximo = salomonJdbc.queryForObject(comando,String.class, parametros);
				
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmIngresoModDao|maximoReg|:"+ex);
		}
			
		return maximo;
	}
		
	public List<AdmIngresoMod> lisTodos(String orden) {
		List<AdmIngresoMod> lista	= new ArrayList<AdmIngresoMod>();
			
		try{
			String comando = "SELECT PERIODO_ID, MODALIDAD_ID " +
							 " FROM SALOMON.ADM_INGRESO_MOD "+ orden; 

			lista = salomonJdbc.query(comando, new AdmIngresoModMapper());
				
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmIngresoModDao|lisTodos|:"+ex);
		}
			
		return lista;
	}
		
	
}
