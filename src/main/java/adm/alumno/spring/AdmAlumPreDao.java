package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmAlumPreDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean updateReg(AdmAlumPre admAlumPre ) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_ALUMPRE " + 
					"SET ESTADO = ? " +				
					"WHERE ALUM_FOLIO = TO_NUMBER(?,'99999999')" +
					"AND CARRERA_ID = ? " +
					"AND FOLIO = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {
				admAlumPre.getEstado(),admAlumPre.getAlumFolio(),admAlumPre.getCarreraId(),admAlumPre.getFolio()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumPreDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	
	public boolean deleteReg(String alumFolio, String carreraId, String folio) throws Exception{
		boolean ok = false;

		try{
			String comando = "DELETE FROM SALOMON.ADM_ALUMPRE "+ 
					"WHERE ALUM_FOLIO = TO_NUMBER(?,'99999999') " +
					"AND CARRERA_ID = ?" +
					"AND FOLIO = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {
				alumFolio,carreraId,folio
			};
			
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumPreDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	
	
	public AdmAlumPre mapeaRegId(String alumFolio, String carreraId, String folio) {
		AdmAlumPre objeto = new AdmAlumPre();
		
		try {
			String comando = "SELECT CODIGO_PERSONAL, CARRERA_ID, FOLIO,ESTADO  "+
					"FROM SALOMON.ADM_ALUMPRE "+ 
					"WHERE ALUM_FOLIO  = TO_NUMBER(?,'99999999') " +
					"AND CARRERA_ID = ?" +
					"AND FOLIO = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {
				alumFolio,carreraId,folio
			};
			objeto = salomonJdbc.queryForObject(comando, new AdmAlumPreMapper(), parametros);
			
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.AdmAlumPreDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String alumFolio, String carreraId, String folio) {
		boolean ok 	= false;

		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_ALUMPRE "+ 
					"WHERE ALUM_FOLIO = TO_NUMBER(?,'99999999')" +
					"AND CARRERA_ID = ? " +
					"AND FOLIO = TO_NUMBER(?,'99')";			
			Object[] parametros = new Object[] { alumFolio,carreraId,folio };
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumPreDao|mapeaRegId|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoReg(String alumFolio, String carreraId ) {
		String	maximo		= "1";
		
		try{
			String comando = "SELECT COALESCE(MAX(FOLIO)+1,1) AS MAXIMO FROM SALOMON.ADM_ALUMPRE "+ 
					"WHERE ALUM_FOLIO = TO_NUMBER(?,'99999999')" +
					"AND CARRERA_ID = ?";		
			Object[] parametros = new Object[] {
				alumFolio,carreraId
			};
			maximo = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumPreDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}
	
	public List<AdmAlumPre> getAll(String orden) {
		List<AdmAlumPre> lista	= new ArrayList<AdmAlumPre>();
		
		try{
			String comando = "SELECT ALUM_FOLIO,CARRERA_ID, FOLIO, ESTADO " +
					" FROM SALOMON.ADM_ALUMPRE "+ orden; 
			
			lista = salomonJdbc.query(comando, new AdmAlumPreMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumPreDao|getAll|:"+ex);
		}
		
		return lista;
	}
	
	public List<AdmAlumPre> getLista(String carreraId, String orden) {
		List<AdmAlumPre> lista	= new ArrayList<AdmAlumPre>();
		
		try{
			String comando = "SELECT ALUM_FOLIO, CARRERA_ID, FOLIO, ESTADO " +
					" FROM SALOMON.ADM_ALUMPRE" + 
					" WHERE CARRERA_ID = '"+carreraId+"' "+ orden;
			
			lista = salomonJdbc.query(comando, new AdmAlumPreMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumPreDao|getLista|:"+ex);
		}
		
		return lista;
	}

}
