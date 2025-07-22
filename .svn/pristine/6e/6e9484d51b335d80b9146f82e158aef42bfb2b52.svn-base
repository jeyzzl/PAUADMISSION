package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmTutorDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmTutor admTutor) {
		boolean ok = false;
		
		try{
			if (admTutor.getEstado().equals("")||admTutor.getEstado()==null) admTutor.setEstado(" ");
			if (admTutor.getCiudad().equals("")||admTutor.getCiudad()==null) admTutor.setCiudad(" ");
			if (admTutor.getNumero().equals("")||admTutor.getNumero()==null) admTutor.setNumero(" ");
			
			String comando = "INSERT INTO SALOMON.ADM_TUTOR"+ 
				"(FOLIO, TUTOR, NOMBRE, CALLE, NUMERO, COLONIA, CODIGOPOSTAL, PAIS_ID, ESTADO_ID, CIUDAD_ID, TELEFONO, ESTADO, CIUDAD) "+
				" VALUES( TO_NUMBER(?,'9999999'), TO_NUMBER(?,'9'), ?, ?, ?, ?, ?, " +
				" TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), ?,?, ? )";
			
			Object[] parametros = new Object[] {
				admTutor.getFolio(),admTutor.getTutor(),admTutor.getNombre(),admTutor.getCalle(),admTutor.getNumero(),admTutor.getColonia(),admTutor.getCodigoPostal(),
				admTutor.getPaisId(),admTutor.getEstadoId(),admTutor.getCiudadId(),admTutor.getTelefono(),admTutor.getEstado(),admTutor.getCiudad()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmTutorDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(AdmTutor admTutor) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_TUTOR SET " +
					" TUTOR = TO_NUMBER(?,'9')," +		
					" NOMBRE = ?, " +
					" CALLE = ?, " +
					" NUMERO = ?, " +		
					" COLONIA = ?, " +		
					" CODIGOPOSTAL = ?, " +		
					" PAIS_ID = TO_NUMBER(?,'999'), " +		
					" ESTADO_ID = TO_NUMBER(?,'999'), " +	
					" CIUDAD_ID = TO_NUMBER(?,'999'), " +
					" TELEFONO = ?, " +
					" ESTADO = ?, " +
					" CIUDAD = ? " +			
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {
				admTutor.getTutor(),admTutor.getNombre(),admTutor.getCalle(),admTutor.getNumero(),admTutor.getColonia(),admTutor.getCodigoPostal(),
				admTutor.getPaisId(),admTutor.getEstadoId(),admTutor.getCiudadId(),admTutor.getTelefono(),admTutor.getEstado(),admTutor.getCiudad(),admTutor.getFolio()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmTutorDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_TUTOR "+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
					
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}

		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmTutorDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmTutor mapeaRegId(String folio ) {
		AdmTutor objeto = new AdmTutor();
		
		try {
			String comando = "SELECT FOLIO, TUTOR, NOMBRE, CALLE, NUMERO, COLONIA, CODIGOPOSTAL, " +
					" PAIS_ID, ESTADO_ID, CIUDAD_ID, TELEFONO, ESTADO, CIUDAD " +
					" FROM SALOMON.ADM_TUTOR "+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {folio};
			objeto = salomonJdbc.queryForObject(comando, new AdmTutorMapper(), parametros) ;
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.AdmTutorDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String folio) {
		boolean ok 		= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_TUTOR "+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";

			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmTutorDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public List<AdmTutor> getAll(String orden) {
		List<AdmTutor> lista		= new ArrayList<AdmTutor>();
		
		try{
			String comando = "SELECT FOLIO, TUTOR, NOMBRE, CALLE, NUMERO, COLONIA, CODIGOPOSTAL, PAIS_ID, ESTADO_ID, CIUDAD_ID, TELEFONO, ESTADO, CIUDAD " +
			" FROM SALOMON.ADM_TUTOR "+ orden; 
			
			lista = salomonJdbc.query(comando, new AdmTutorMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmTutorDao|getAll|:"+ex);
		}
		
		return lista;
	}

}
