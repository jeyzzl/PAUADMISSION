package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmCartaDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg( AdmCarta objeto){
		boolean ok = false;
		try{
			String comando = "INSERT INTO SALOMON.ADM_CARTA(FOLIO, CONDICION_ID, CONDICION_NOMBRE)"
					+ " VALUES(TO_NUMBER(?,'99999999'),TO_NUMBER(?,'9'),?)";
			Object[] parametros = new Object[] {objeto.getFolio(), objeto.getCondicionId(), objeto.getCondicionNombre()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmCartaDao|insertReg|:"+ex);
		}		
		return ok;
	}	
	
	public boolean updateReg(AdmCarta objeto){
		boolean ok = false;		
		try{
			String comando = "UPDATE SALOMON.ADM_CARTA SET CONDICION_NOMBRE = ? WHERE FOLIO = TO_NUMBER(?,'99999999') AND CONDICION_ID = TO_NUMBER(?,'9')";
			Object[] parametros = new Object[] {objeto.getCondicionNombre(), objeto.getFolio(), objeto.getCondicionId()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmCartaDao|updateReg|:"+ex);		
		}		
		return ok;
	}	
	
	public boolean deleteReg(String folio, String condicionId){
		boolean ok = false;		
		try{
			String comando = "DELETE FROM SALOMON.ADM_CARTA WHERE FOLIO = TO_NUMBER(?,'99999999') AND CONDICION_ID = TO_NUMBER(?,'9')";
			Object[] parametros = new Object[] {folio, condicionId};
			if (salomonJdbc.update(comando,parametros)==1){			
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmCartaDao|deleteReg|:"+ex);			
		}		
		return ok;
	}
	
	public String maximoReg(String folio){
		String maximo = "1";		
		try{
			String comando = "SELECT COALESCE(MAX(CONDICION_ID)+1,1) FROM SALOMON.ADM_CARTA WHERE FOLIO = TO_NUMBER(?,'9999999')";
			Object[] parametros = new Object[] {folio};
			maximo = salomonJdbc.queryForObject(comando, String.class, parametros);			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmCartaDao|maximoReg|:"+ex);
		}		
		return maximo;
	}
	
	public AdmCarta mapeaRegId(String folio, String condicionId) {
		AdmCarta objeto = new AdmCarta();		
		try{
			String comando = "SELECT FOLIO, CONDICION_ID, CONDICION_NOMBRE FROM SALOMON.ADM_CARTA WHERE FOLIO = TO_NUMBER(?,'99999999') AND CONDICION_ID = TO_NUMBER(?,'9')";
			Object[] parametros = new Object[] {folio, condicionId};
			objeto = salomonJdbc.queryForObject(comando, new AdmCartaMapper(), parametros);			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmCartaDao|mapeaRegId|:"+ex);
		}		
		return objeto;
	}
	
	public String getNumCondiciones(String folio){
		String total = "0";		
		try{
			String comando = "SELECT COUNT(CONDICION_ID) FROM SALOMON.ADM_CARTA WHERE FOLIO = TO_NUMBER(?,'9999999')";
			Object[] parametros = new Object[] {folio};
			total = salomonJdbc.queryForObject(comando, String.class, parametros);			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmCartaDao|maximoReg|:"+ex);
		}		
		return total;
	}
	
	public boolean existeReg( String folio, String condicionId){
		boolean ok 	= false;		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_CARTA WHERE FOLIO = TO_NUMBER(?,'99999999') AND CONDICION_ID = TO_NUMBER(?,'99')"; 
			Object[] parametros = new Object[] {folio, condicionId};	
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros)>=1){	
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmCartaDao|existeReg|:"+ex);
		}	
		return ok;
	}
	
	public List<AdmCarta> lisTodos (String orden){
		List<AdmCarta> lista = new ArrayList<AdmCarta>();
		
		try {
			String comando = "SELECT FOLIO, CONDICION_ID, CONDICION_NOMBRE FROM SALOMON.ADM_CARTA "+ orden;			
			lista = salomonJdbc.query(comando, new AdmCartaMapper());
		}catch(Exception ex) {
			System.out.println("Error - adm.alumno.spring.AdmCartaDao|lisTodos|:"+ex);
		}
		return lista;
	}
	
	public List<AdmCarta> lisPorFolio (String folio, String orden){
		List<AdmCarta> lista = new ArrayList<AdmCarta>();
		
		try {
			String comando = "SELECT FOLIO, CONDICION_ID, CONDICION_NOMBRE FROM SALOMON.ADM_CARTA WHERE FOLIO = TO_NUMBER(?,'99999999') "+ orden;
			Object[] parametros = new Object[] {folio};
			lista = salomonJdbc.query(comando, new AdmCartaMapper(), parametros);
		}catch(Exception ex) {
			System.out.println("Error - adm.alumno.spring.AdmCartaDao|lisPorFolio|:"+ex);
		}
		return lista;
	}

}
