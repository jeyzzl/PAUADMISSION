package adm.alumno.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmOpcionPlanDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmOpcionPlan objeto) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_OPCION_PLAN (OPCION_ID, PLAN_ID) VALUES( TO_NUMBER(?,'99'), ?)";
			
			Object[] parametros = new Object[] {
				objeto.getOpcionId(),objeto.getPlanId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.admision.spring.AdmOpcionPlanDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String opcionId, String planId ) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_OPCION_PLAN WHERE OPCION_ID = TO_NUMBER(?,'99') AND PLAN_ID = ?";
			Object[] parametros = new Object[] {opcionId,planId};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - aca.admision.spring.AdmOpcionPlanDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public boolean existeReg( String opcionId, String planId) {		
		boolean 		ok 	= false;	
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_OPCION_PLAN WHERE OPCION_ID = TO_NUMBER(?,'99') AND PLAN_ID = ?";
			Object[] parametros = new Object[] {opcionId, planId};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1 ){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - aca.admision.spring.AdmOpcionPlanDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public List<AdmOpcionPlan> listaPorOpcionId(String opcionId, String orden) {
		List<AdmOpcionPlan> lista	= new ArrayList<AdmOpcionPlan>();		
		try{
			String comando = "SELECT OPCION_ID, PLAN_ID FROM SALOMON.ADM_OPCION_PLAN WHERE OPCION_ID = ?"+ orden;			
			lista = salomonJdbc.query(comando, new AdmOpcionPlanMapper(), opcionId);			
		}catch(Exception ex){
			System.out.println("Error - aca.admision.spring.AdmOpcionPlanDao|listaPorOpcionId|:"+ex);
		}		
		return lista;
	}
	
	public HashMap<String, String> mapaTotalPlanes(){		
		HashMap<String, String> mapa = new HashMap<String, String>();
		List<adm.Mapa> lista	= new ArrayList<adm.Mapa>();
		try{
			String comando = "SELECT OPCION_ID AS LLAVE, COUNT(PLAN_ID) AS VALOR FROM SALOMON.ADM_OPCION_PLAN GROUP BY OPCION_ID";
			lista = salomonJdbc.query(comando, new adm.MapaMapper());	
			for (adm.Mapa map : lista) {
				mapa.put(map.getLlave() , map.getValor());
			}
		}catch(Exception ex){
			System.out.println("Error - aca.admision.spring.AdmOpcionPlanDao|mapaTotalPlanes:"+ex);
		}
		
		return mapa;
	}
	
	public HashMap<String, String> mapaPorPlan(){		
		HashMap<String, String> mapa = new HashMap<String, String>();
		List<adm.Mapa> lista	= new ArrayList<adm.Mapa>();
		try{
			String comando = "SELECT PLAN_ID||OPCION_ID AS LLAVE, OPCION_ID AS VALOR FROM SALOMON.ADM_OPCION_PLAN";
			lista = salomonJdbc.query(comando, new adm.MapaMapper());	
			for (adm.Mapa map : lista) {
				mapa.put(map.getLlave() , map.getValor());
			}
		}catch(Exception ex){
			System.out.println("Error - aca.admision.spring.AdmOpcionPlanDao|mapaPorPlan:"+ex);
		}
		
		return mapa;
	}
	
}
