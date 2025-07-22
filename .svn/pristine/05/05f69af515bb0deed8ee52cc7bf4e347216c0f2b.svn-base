package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmPadresDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmPadres admPadres) {
		boolean ok = false;

		try{
			String comando = "INSERT INTO SALOMON.ADM_PADRES"+ 
				"(FOLIO, PADRE_NOMBRE, PADRE_APELLIDO, PADRE_RELIGION, PADRE_NACIONALIDAD, PADRE_PAIS_ID, PADRE_ESTADO_ID, PADRE_CIUDAD_ID, PADRE_OCUPACION, MADRE_NOMBRE, MADRE_APELLIDO, MADRE_RELIGION," +
				" MADRE_NACIONALIDAD, MADRE_PAIS_ID, MADRE_ESTADO_ID, MADRE_CIUDAD_ID, MADRE_OCUPACION, VIVE_PADRE, VIVE_MADRE, PADRE_TIPO, MADRE_TIPO, PADRE_RESPONSABLE, MADRE_RESPONSABLE, PADRE_TELEFONO, MADRE_TELEFONO )"+
				" VALUES( TO_NUMBER(?,'9999999'), ?, ?, TO_NUMBER(?,'99'), TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), ?, ?, ?, TO_NUMBER(?,'99'), TO_NUMBER(?,'999'),"+
				" TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			Object[] parametros = new Object[] {
				admPadres.getFolio(),admPadres.getPadreNombre(),admPadres.getPadreApellido(),admPadres.getPadreReligion(),admPadres.getPadreNacionalidad(), admPadres.getPadrePaisId(), admPadres.getPadreEstadoId(),
				admPadres.getPadreCiudadId(),admPadres.getPadreOcupacion(), admPadres.getMadreNombre(),admPadres.getMadreApellido(),admPadres.getMadreReligion(),admPadres.getMadreNacionalidad(),
				admPadres.getMadrePaisId(), admPadres.getMadreEstadoId(), admPadres.getMadreCiudadId(),admPadres.getMadreOcupacion(),admPadres.getVivePadre(), admPadres.getViveMadre(), admPadres.getPadreTipo(), 
				admPadres.getMadreTipo(), admPadres.getPadreResponsable(), admPadres.getMadreResponsable(), admPadres.getPadreTelefono(), admPadres.getMadreTelefono()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmPadresDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(AdmPadres admPadres) {
		boolean ok = false;

		try{
			String comando = "UPDATE SALOMON.ADM_PADRES"
					+ " SET PADRE_NOMBRE = ?,"
					+ " PADRE_APELLIDO = ?,"
					+ " PADRE_RELIGION = TO_NUMBER(?,'99'),"
					+ " PADRE_NACIONALIDAD = TO_NUMBER(?,'999'),"
					+ " PADRE_PAIS_ID = TO_NUMBER(?,'999'),"
					+ " PADRE_ESTADO_ID = TO_NUMBER(?,'999'),"
					+ " PADRE_CIUDAD_ID = TO_NUMBER(?,'999'),"
					+ " PADRE_OCUPACION = ?,"
					+ " MADRE_NOMBRE = ?,"
					+ " MADRE_APELLIDO = ?,"
					+ " MADRE_RELIGION = TO_NUMBER(?,'99'),"
					+ " MADRE_NACIONALIDAD = TO_NUMBER(?,'999'),"
					+ " MADRE_PAIS_ID = TO_NUMBER(?,'999'),"
					+ " MADRE_ESTADO_ID = TO_NUMBER(?,'999'),"
					+ " MADRE_CIUDAD_ID = TO_NUMBER(?,'999'),"
					+ " MADRE_OCUPACION = ?,"
					+ " VIVE_PADRE = ?,"
					+ " VIVE_MADRE = ?,"
					+ " PADRE_TIPO = ?,"
					+ " MADRE_TIPO = ?,"
					+ " PADRE_RESPONSABLE = ?,"
					+ " MADRE_RESPONSABLE = ?,"
					+ " PADRE_TELEFONO = ?,"
					+ " MADRE_TELEFONO = ?"			
					+ " WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {
				admPadres.getPadreNombre(),admPadres.getPadreApellido(),admPadres.getPadreReligion(),admPadres.getPadreNacionalidad(),admPadres.getPadrePaisId(),admPadres.getPadreEstadoId(),admPadres.getPadreCiudadId(),admPadres.getPadreOcupacion(),
				admPadres.getMadreNombre(),admPadres.getMadreApellido(),admPadres.getMadreReligion(),admPadres.getMadreNacionalidad(),admPadres.getMadrePaisId(),admPadres.getMadreEstadoId(),admPadres.getMadreCiudadId(),admPadres.getMadreOcupacion(),
				admPadres.getVivePadre(), admPadres.getViveMadre(), admPadres.getPadreTipo(), admPadres.getMadreTipo(), admPadres.getPadreResponsable(), admPadres.getMadreResponsable(), admPadres.getPadreTelefono(), admPadres.getMadreTelefono(), admPadres.getFolio()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmPadresDao|updateReg|:"+ex);
		}
		
		return ok;
	}
	
	
	public boolean deleteReg(String folio) {
		boolean ok = false;

		try{
			String comando = "DELETE FROM SALOMON.ADM_PADRES"+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmPadresDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmPadres mapeaRegId(String folio ) {
		AdmPadres objeto = new AdmPadres();
		
		try {
			String comando = "SELECT FOLIO, PADRE_NOMBRE, PADRE_APELLIDO, PADRE_RELIGION, PADRE_NACIONALIDAD, PADRE_PAIS_ID, PADRE_ESTADO_ID, PADRE_CIUDAD_ID," +
					" PADRE_OCUPACION, MADRE_NOMBRE, MADRE_APELLIDO, MADRE_RELIGION, MADRE_NACIONALIDAD, MADRE_PAIS_ID, MADRE_ESTADO_ID, MADRE_CIUDAD_ID, MADRE_OCUPACION, VIVE_PADRE, VIVE_MADRE," +
					" PADRE_TIPO, MADRE_TIPO, PADRE_RESPONSABLE, MADRE_RESPONSABLE, PADRE_TELEFONO, MADRE_TELEFONO" +
					" FROM SALOMON.ADM_PADRES"+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {folio};
			objeto = salomonJdbc.queryForObject(comando, new AdmPadresMapper(), parametros);
			
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.spring.AdmPadresDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String folio) {
		boolean 		ok 		= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_PADRES"+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmPadresDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public List<AdmPadres> getAll(String orden) {
		List<AdmPadres> lista		= new ArrayList<AdmPadres>();
		
		try{
			String comando = "SELECT FOLIO, PADRE_NOMBRE, PADRE_APELLIDO, PADRE_RELIGION, PADRE_NACIONALIDAD, PADRE_PAIS_ID, PADRE_ESTADO_ID, PADRE_CIUDAD_ID, PADRE_OCUPACION," +
					" MADRE_NOMBRE, MADRE_APELLIDO, MADRE_RELIGION, MADRE_NACIONALIDAD, MADRE_PAIS_ID, MADRE_ESTADO_ID, MADRE_CIUDAD_ID, MADRE_OCUPACION, VIVE_PADRE, VIVE_MADRE" +
					" PADRE_TIPO, MADRE_TIPO, PADRE_RESPONSABLE, MADRE_RESPONSABLE, PADRE_TELEFONO, MADRE_TELEFONO" +
					" FROM SALOMON.ADM_PADRES "+ orden; 
			
			lista = salomonJdbc.query(comando, new AdmPadresMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmPadresUtil|getAll|:"+ex);
		}
		
		return lista;
	}

}
