package adm.alumno.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmProcesoDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmProceso admProceso) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_PROCESO"+ 
				"(FOLIO, F_REGISTRO, F_SOLICITUD, F_DOCUMENTOS, F_ADMISION, F_CARTA, F_EXAMEN, F_DIRECTO) "+
				"VALUES(TO_NUMBER(?,'9999999'), sysdate, NULL, NULL, NULL, NULL, NULL, NULL)";
			
			Object[] parametros = new Object[] {
				admProceso.getFolio()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}

		}catch(Exception ex){
			System.out.println("Error - adm.documento.AdmProcesoDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_PROCESO"+ 
				" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.AdmProcesoDao|deleteReg|:"+ex);
		}
				
		return ok;
	}
	
	public AdmProceso mapeaRegId(String folio) {
		AdmProceso objeto = new AdmProceso();
		
		try {
			String comando = "SELECT FOLIO, F_REGISTRO, F_SOLICITUD, F_DOCUMENTOS, F_ADMISION, F_CARTA, F_EXAMEN, F_DIRECTO"+
					" FROM SALOMON.ADM_PROCESO " + 
					" WHERE FOLIO = TO_NUMBER(?, '9999999')";
			
			Object[] parametros = new Object[] {folio};
			objeto = salomonJdbc.queryForObject(comando, new AdmProcesoMapper(), parametros);
			
		} catch (Exception ex) {
			System.out.println("Error - adm.documento.AdmProcesoDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String folio) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_PROCESO "+ 
				"WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando,Integer.class,parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.AdmProcesoDao|existeReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateFecha(int numFecha, String folio) {
		boolean ok = false;
		
		String comando = "";
		
		try{
			if(numFecha==1){
				comando = "UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_REGISTRO = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			}
			else if(numFecha==2){
				comando = "UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_SOLICITUD = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			}
			else if(numFecha==3){
				comando = "UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_DOCUMENTOS = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			}
			else if(numFecha==4){
				comando = "UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_ADMISION = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			}
			else if(numFecha==5){
				comando = "UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_CARTA = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			}
			else if(numFecha==6){
				comando = "UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_EXAMEN = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			}
			else if(numFecha==7){
				comando = "UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_DIRECTO = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			}
			
			if (salomonJdbc.update(comando,new Object[] {folio})==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.AdmProcesoDao|updateFecha|:"+ex);			
		}

		return ok;
	}

}
