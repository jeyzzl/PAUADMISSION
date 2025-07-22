package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmAlumReqDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmAlumReq admAlumReq) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_ALUMREQ"+ 
				" (FOLIO, PRERREQUISITO, PROMLIC, PROMMAE, PHCA, PAEP, SERVICIO)"+
				" VALUES( TO_NUMBER(?,'9999999'), ?, TO_NUMBER(?,'999.99'), TO_NUMBER(?,'999.99'), TO_NUMBER(?,'999.99'), TO_NUMBER(?,'999.99'),TO_NUMBER(?,'99'))";
			
			Object[] parametros = new Object[] {
				admAlumReq.getFolio(),admAlumReq.getPrerrequisito(),admAlumReq.getPromLic(),admAlumReq.getPromMae(),admAlumReq.getPhca(),admAlumReq.getPaep(),admAlumReq.getServicio()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumReqDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(AdmAlumReq admAlumReq) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_ALUMREQ " + 
					"SET PRERREQUISITO = ?, " +
					"PROMLIC = TO_NUMBER(?,'999.99'), " +
					"PROMMAE =  TO_NUMBER(?,'999.99'), " +
					"PHCA = TO_NUMBER(?,'999.99'), " +
					"PAEP = TO_NUMBER(?,'999.99'), " +
					"SERVICIO = TO_NUMBER(?,'99')"+
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {
				admAlumReq.getPrerrequisito(),admAlumReq.getPromLic(),admAlumReq.getPromMae(),admAlumReq.getPhca(),admAlumReq.getPaep(),admAlumReq.getServicio(),admAlumReq.getFolio()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumReqDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_ALUMREQ "+ 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') ";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}

		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumReqDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmAlumReq mapeaRegId(String folio )  {
		AdmAlumReq objeto = new AdmAlumReq();
		
		try {
			String comando = "SELECT FOLIO, PRERREQUISITO, PROMLIC, PROMMAE, PHCA, PAEP,SERVICIO "+
				"FROM SALOMON.ADM_ALUMREQ "+ 
				"WHERE FOLIO = TO_NUMBER(?,'9999999') ";
			
			Object[] parametros = new Object[] {folio};
			objeto = salomonJdbc.queryForObject(comando, new AdmAlumReqMapper(), parametros);
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.AdmAlumReqDao|mapeaRegId|:"+ex);
		}
		
		return objeto;	
	}
	
	public boolean existeReg(String folio) {
		boolean 		ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_ALUMREQ "+ 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') ";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumReqDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public List<AdmAlumReq> getLista(String folio, String orden) {
		List<AdmAlumReq> lista	= new ArrayList<AdmAlumReq>();
		
		try{
			String comando = "SELECT FOLIO, PRERREQUISITO, PROMLIC," +
					" PROMMAE, PHCA, PAEP, SERVICIO"+
					" FROM SALOMON.ADM_ALUMREQ" + 
					" WHERE FOLIO = TO_NUMBER('"+folio+"','9999999') "+ orden;
			
			lista = salomonJdbc.query(comando, new AdmAlumReqMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmPrerrequisitoUtil|getLista|:"+ex);
		}
		
		return lista;
	}
	
}
