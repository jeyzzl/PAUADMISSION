package adm.parametros.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmParametrosDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmParametros admParametros) {
		boolean ok = false;		
		try{
			String comando = "INSERT INTO ENOC.PARAMETROS(ID, INSTITUCION, CERTIFICADOS, CONSTANCIAS, CARDEX, MONITOR, MONITOR_RUTA, PAIS_ID)"
					+ " VALUES(TO_NUMBER(?,'99'), ?, ?, ?, ?, ?, ?, TO_NUMBER(?, '999'))";
			Object[] parametros = new Object[] {
				admParametros.getId(), admParametros.getInstitucion(), admParametros.getCertificados(), admParametros.getConstancias(), admParametros.getCardex(),
				admParametros.getMonitor(), admParametros.getMonitorRuta(), admParametros.getPaisId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.parametros.spring.AdmParametrosDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(AdmParametros admParametros) {
		boolean ok = false;		
		try{
			String comando = "UPDATE ENOC.PARAMETROS " 
					+ " SET INSTITUCION = ?, "
					+ " CERTIFICADOS = ?, "
					+ " CONSTANCIAS = ?, "
					+ " CARDEX = ?,"
					+ " MONITOR = ?,"
					+ " MONITOR_RUTA = ?,"
					+ " PAIS_ID = TO_NUMBER(?, '999') "
					+ " WHERE ID = TO_NUMBER(?,'99')";			
			Object[] parametros = new Object[] {
				admParametros.getInstitucion(), admParametros.getCertificados(), admParametros.getConstancias(), admParametros.getCardex(), admParametros.getMonitor(), admParametros.getMonitorRuta(), admParametros.getPaisId(), admParametros.getId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.parametros.spring.AdmParametrosDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String id) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM ENOC.PARAMETROS WHERE ID = TO_NUMBER(?,'99')";			
			if (salomonJdbc.update(comando,id)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.parametros.spring.AdmParametrosDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmParametros mapeaRegId(String id) {
		AdmParametros objeto = new AdmParametros();		
		try {
			String comando = "SELECT COUNT(*) FROM ENOC.PARAMETROS WHERE ID = TO_NUMBER(?,'99')";			
			if (salomonJdbc.queryForObject(comando,Integer.class, id) >= 1){
				comando = "SELECT ID, INSTITUCION, CERTIFICADOS, CONSTANCIAS, CARDEX, MONITOR, MONITOR_RUTA, PAIS_ID " +
						" FROM ENOC.PARAMETROS "+ 
						" WHERE ID = TO_NUMBER(?,'99')";			
				objeto = salomonJdbc.queryForObject(comando, new AdmParametrosMapper(), id);				
			}		
		}catch(Exception ex){
			System.out.println("Error - adm.parametros.spring.AdmParametrosDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String id ) {
		boolean ok = false;		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.PARAMETROS WHERE ID = TO_NUMBER(?,'99')";			
			Object[] parametros = new Object[] {id};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.parametros.spring.AdmParametrosDao|existeReg|:"+ex);
		}		
		return ok;
	}
	
	public List<AdmParametros> getAll(String orden) {
		List<AdmParametros> lista	= new ArrayList<AdmParametros>();		
		try{
			String comando = "SELECT ID, INSTITUCION, CERTIFICADOS, CONSTANCIAS, CARDEX, MONITOR, MONITOR_RUTA, PAIS_ID FROM ENOC.PARAMETROS "+ orden;			
			lista = salomonJdbc.query(comando, new AdmParametrosMapper());			
		}catch(Exception ex){
			System.out.println("Error - adm.parametros.spring.AdmParametrosDao|getAll|:"+ex);
		}		
		return lista;
	}
}
