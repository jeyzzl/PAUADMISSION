package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmIngresoPlanDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	
	public boolean insertReg(AdmIngresoPlan admIngresoPlan) {
		boolean ok = false;
			
		try{
			String comando = "INSERT INTO SALOMON.ADM_INGRESO_PLAN"+ 
				"(PERIODO_ID, MODALIDAD_ID, PLAN_ID) "+
				"VALUES(TO_NUMBER(?,'999'),TO_NUMBER(?,'99'),? )";
				
			Object[] parametros = new Object[] {
					admIngresoPlan.getPeriodoId(),admIngresoPlan.getModalidadId(), admIngresoPlan.getPlanId() };
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmIngresoPlanDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	
	public boolean deleteReg(String periodoId, String modalidadId, String planId) throws Exception{
		boolean ok = false;

		try{
			String comando = "DELETE FROM SALOMON.ADM_INGRESO_PLAN "+ 
					"WHERE PERIODO_ID = TO_NUMBER(?,'999') " +
					"AND MODALIDAD_ID = TO_NUMBER(?,'99') " +
					"AND PLAN_ID = ? ";
			
			Object[] parametros = new Object[] {periodoId,modalidadId,planId};
			
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmIngresoPlanDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	
	
	public AdmIngresoPlan mapeaRegId(String periodoId, String modalidadId, String planId) {
			AdmIngresoPlan objeto = new AdmIngresoPlan();
		
		try {
			String comando = "SELECT PERIODO_ID, MODALIDAD_ID, PLAN_ID  "+
					"FROM SALOMON.ADM_INGRESO_PLAN "+ 
					"WHERE PERIODO_ID  = TO_NUMBER(?,'999') " +
					"AND MODALIDAD_ID = TO_NUMBER(?,'99')" +
					"AND PLAN_ID = ? ";
			
			Object[] parametros = new Object[] {periodoId,modalidadId,planId
					};
			objeto = salomonJdbc.queryForObject(comando, new AdmIngresoPlanMapper(), parametros);
			
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.AdmIngresoPlanDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String periodoId, String modalidadId, String planId) {
		boolean ok 	= false;

		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_INGRESO_PLAN "+ 
					"WHERE PERIODO_ID = TO_NUMBER(?,'999') " +
					"AND MODALIDAD_ID = TO_NUMBER(?,'99') " +
					"AND PLAN_ID = ? ";			
			Object[] parametros = new Object[] { periodoId,modalidadId,planId };
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmIngresoPlanDao|mapeaRegId|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoReg(String periodoId,String modalidadId) {
		String maximo		= "1";
			
		try{
			String comando = "SELECT COALESCE(MAX(PERIODO_ID)+1,1) AS MAXIMO FROM SALOMON.ADM_INGRESO_PLAN "+ 
						     "WHERE PERIODO_ID = TO_NUMBER(?,'999')"
						    +"MODALIDAD_ID = TO_NUMBER(?,'99')"; 
				
			Object[] parametros = new Object[] {periodoId};
			maximo = salomonJdbc.queryForObject(comando,String.class, parametros);
				
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmIngresoPlanDao|maximoReg|:"+ex);
		}
			
		return maximo;
	}
	
	
	public List<AdmIngresoPlan> lisPorPeriodoAndModo(String periodoId,String modo, String orden) {
		List<AdmIngresoPlan> lista	= new ArrayList<AdmIngresoPlan>();		
		try{
			String comando = "SELECT PERIODO_ID, MODALIDAD_ID, PLAN_ID " +
					" FROM SALOMON.ADM_INGRESO_PLAN " + 
					" WHERE PERIODO_ID = ? " +
					" AND MODALIDAD_ID IN (SELECT MODALIDAD_ID FROM ENOC.CAT_MODALIDAD WHERE ONLINE = ?)" +
					orden;			
			lista = salomonJdbc.query(comando, new AdmIngresoPlanMapper(), periodoId, modo);			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmIngresoPlanDao|lisPorPeriodoAndModo|:"+ex);
		}		
		return lista;
	}

}
