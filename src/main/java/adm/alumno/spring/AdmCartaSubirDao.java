package adm.alumno.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmCartaSubirDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public AdmCartaSubir mapeaRegId(String folio) {
		AdmCartaSubir objeto = new AdmCartaSubir();		
		try{
			String comando = "SELECT FOLIO, NOMBRE, CARTA, FECHA FROM SALOMON.ADM_CARTA_SUBIR WHERE FOLIO = TO_NUMBER(?,'99999999')";
			Object[] parametros = new Object[] {folio};
			objeto = salomonJdbc.queryForObject(comando, new AdmCartaSubirMapper(),parametros);			
		}catch(Exception ex){
			System.out.println("Error - aca.admision.spring.AdmCartaSubirDao|mapeaRegId|:"+ex);
		}		
		return objeto;
	}
	
	public boolean existeReg(String folio){
		boolean ok 	= false;		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_CARTA_SUBIR WHERE FOLIO = TO_NUMBER(?,'99999999')"; 
			Object[] parametros = new Object[] {folio};	
			if (salomonJdbc.queryForObject(comando,Integer.class,parametros)>=1){	
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.admision.spring.AdmCartaSubirDao|existeReg|:"+ex);
		}	
		return ok;
	}
}
