package adm.alumno.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmAlumDatosDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public AdmAlumDatos mapeaRegId(String folio) {
		AdmAlumDatos objeto = new AdmAlumDatos();
		
		try {
			String comando = "SELECT" +
				" ASOL.FOLIO AS FOLIO," +
				" AP.CODIGO_PERSONAL AS CODIGO_PERSONAL," +
				" AP.NOMBRE AS NOMBRE," +
				" AP.APELLIDO_PATERNO AS APELLIDO_PATERNO," +
				" AP.APELLIDO_MATERNO AS APELLIDO_MATERNO," +
				" TO_CHAR(AP.F_NACIMIENTO,'DD/MM/YYYY') AS F_NACIMIENTO," +
				" AA.MODALIDAD_ID AS MODALIDAD_ID," +
				" SALOMON.ADM_CARRERASOL(?) AS CARRERA_ID"+
				" FROM ENOC.ALUM_PERSONAL AP, ENOC.ALUM_ACADEMICO AA, SALOMON.ADM_SOLICITUD ASOL"+ 
				" WHERE ASOL.FOLIO =  TO_NUMBER(?,'999')" +
				" AND AP.CODIGO_PERSONAL = ASOL.MATRICULA" +
				" AND AA.CODIGO_PERSONAL = AP.CODIGO_PERSONAL";
			
			Object[] parametros = new Object[] {folio};
			objeto = salomonJdbc.queryForObject(comando, new AdmAlumDatosMapper(), parametros);
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumDatosDao|mapeaRegId|:"+ex);
		}
			
		return objeto;	
	}
	
	public boolean existeReg(String folio) {
		boolean 		ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_SOLICITUD "+ 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') AND MATRICULA IS NOT NULL";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumDatosDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String getNombreModalidad(String modalidadId ) {
		String nombre	= "null";
		
		try{
			String comando = "SELECT NOMBRE_MODALIDAD FROM ENOC.CAT_MODALIDAD WHERE MODALIDAD_ID = ?";			
			Object[] parametros = new Object[] {modalidadId};
			nombre = salomonJdbc.queryForObject(comando,String.class, parametros);			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAlumDatosDao|getNombreModalidad|:"+ex);
		}
		
		return nombre;
	}

}
