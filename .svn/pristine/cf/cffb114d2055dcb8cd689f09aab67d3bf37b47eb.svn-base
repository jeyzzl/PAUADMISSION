package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmPrerrequisitoDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmPrerrequisito admPrerrequisito) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_PRERREQUISITO"+ 
				"(CARRERA_ID, FOLIO, NOMBRE,) "+
				"VALUES( ?, TO_NUMBER(?,'9999999'),? )";
			
			Object[] parametros = new Object[] {
				admPrerrequisito.getCarreraId(),admPrerrequisito.getFolio(),admPrerrequisito.getNombre()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}

		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmPrerrequisitoDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(AdmPrerrequisito admPrerrequisito) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_PRERREQUISITO " + 
					"SET NOMBRE = ? " +				
					"WHERE FOLIO = TO_NUMBER(?,'9999999')" +
					"AND CARRERA_ID = ? ";
			
			Object[] parametros = new Object[] {
				admPrerrequisito.getNombre(),admPrerrequisito.getFolio(),admPrerrequisito.getCarreraId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}

		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmPrerrequisitoDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio, String carreraId) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_PRERREQUISITO "+ 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') " +
					"AND CARRERA_ID = ?";
			
			Object[] parametros = new Object[] {folio,carreraId};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmPrerrequisitoDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmPrerrequisito mapeaRegId(String folio, String carreraId) {
		AdmPrerrequisito objeto = new AdmPrerrequisito();
		
		try {
			String comando = "SELECT CARRERA_ID, FOLIO, NOMBRE, "+
					"FROM SALOMON.ADM_PRERREQUISITO "+ 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') " +
					"AND CARRERA_ID = ?";
			
			Object[] parametros = new Object[] {folio,carreraId};
			objeto = salomonJdbc.queryForObject(comando, new AdmPrerrequisitoMapper(), parametros);
			
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.AdmPrerrequisitoDao|mapeaRegId|:"+ex);
		}

		return objeto;
	}
	
	public boolean existeReg(String folio, String carreraId) {
		boolean ok 		= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_PRERREQUISITO "+ 
					"WHERE FOLIO = TO_NUMBER(?,'9999999')" +
					"AND CARRERA_ID = ? ";
			
			Object[] parametros = new Object[] {folio,carreraId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmPrerrequisitoDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoReg(String carreraId) {
		String maximo		= "1";
		
		try{
			String comando = "SELECT COALESCE(MAX(FOLIO)+1,1) AS MAXIMO FROM SALOMON.ADM_PRERREQUISITO "+ 
					"WHERE CARRERA_ID = ?";		
			
			Object[] parametros = new Object[] {carreraId};
			maximo = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmPrerrequisitoDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}
	
	public List<AdmPrerrequisito> getAll(String orden) {
		List<AdmPrerrequisito> lista	= new ArrayList<AdmPrerrequisito>();
		
		try{
			String comando = "SELECT CARRERA_ID, FOLIO, NOMBRE " +
					" FROM SALOMON.ADM_PRERREQUISITO "+ orden; 

			lista = salomonJdbc.query(comando, new AdmPrerrequisitoMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmPrerrequisitoDao|getAll|:"+ex);
		}
		
		return lista;
	}
	
	public List<AdmPrerrequisito> getLista(String carreraId, String orden) {
		List<AdmPrerrequisito> lista	= new ArrayList<AdmPrerrequisito>();
		
		try{
			String comando = "SELECT CARRERA_ID, FOLIO, NOMBRE " +
					" FROM SALOMON.ADM_PRERREQUISITO" + 
					" WHERE CARRERA_ID = '"+carreraId+"' "+ orden;
			
			lista = salomonJdbc.query(comando, new AdmPrerrequisitoMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmPrerrequisitoDao|getLista|:"+ex);
		}
		
		return lista;
	}

}
