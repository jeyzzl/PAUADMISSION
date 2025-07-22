package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmSaludDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmSalud admSalud) {
		boolean ok = false;
		
		try{
			if (admSalud.getEnfermedad().equals("N")) admSalud.setEnfermedadDato("-");
			if (admSalud.getImpedimento().equals("N")) admSalud.setImpedimentoDato("-");
			
			String comando = "INSERT INTO SALOMON.ADM_SALUD"+ 
				"(FOLIO, ENFERMEDAD, ENFERMEDAD_DATO, IMPEDIMENTO, IMPEDIMENTO_DATO) "+
				"VALUES( TO_NUMBER(?,'9999999'), ?, ?, ?, ?)";
			
			Object[] parametros = new Object[] {
				admSalud.getFolio(),admSalud.getEnfermedad(),admSalud.getEnfermedadDato(),admSalud.getImpedimento(),admSalud.getImpedimentoDato()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmSaludDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(AdmSalud admSalud) {
		boolean ok = false;
		
		try{
			if (admSalud.getEnfermedad().equals("N")) admSalud.setEnfermedadDato("-");
			if (admSalud.getImpedimento().equals("N")) admSalud.setImpedimentoDato("-");
			
			String comando = "UPDATE SALOMON.ADM_SALUD " + 
					" SET ENFERMEDAD = ?, " +
					" ENFERMEDAD_DATO = ?, " +
					" IMPEDIMENTO = ?, " +
					" IMPEDIMENTO_DATO = ? " +		
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {
				admSalud.getEnfermedad(),admSalud.getEnfermedadDato(),admSalud.getImpedimento(),admSalud.getImpedimentoDato(),admSalud.getFolio()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmSaludDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_SALUD "+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmSaludDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmSalud mapeaRegId(String folio ) {
		AdmSalud objeto = new AdmSalud();
		
		try {
			String comando = "SELECT FOLIO, ENFERMEDAD, ENFERMEDAD_DATO, IMPEDIMENTO, IMPEDIMENTO_DATO" +
					" FROM SALOMON.ADM_SALUD "+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {folio};
			objeto = salomonJdbc.queryForObject(comando, new AdmSaludMapper(), parametros);
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.AdmSaludDao|mapeaRegId|:"+ex);
		}
		
		return objeto;	
	}
	
	public boolean existeReg(String folio) {
		boolean ok 		= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_SALUD "+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmSaludDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public List<AdmSalud> getAll(String orden) {
		List<AdmSalud> lista		= new ArrayList<AdmSalud>();
		
		try{
			String comando = "SELECT FOLIO, ENFERMEDAD, ENFERMEDAD_DATO, IMPEDIMENTO, IMPEDIMENTO_DATO" +
			" FROM SALOMON.ADM_SALUD "+ orden; 
			
			lista = salomonJdbc.query(comando, new AdmSaludMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmSaludUtil|getAll|:"+ex);
		}
		
		return lista;
	}

}
