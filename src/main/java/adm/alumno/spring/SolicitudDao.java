package adm.alumno.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class SolicitudDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(Solicitud solicitud) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_SOLICITUD(" + 
					" FOLIO, PER_NOMBRE, PER_PATERNO," +
					" PER_MATERNO, NAC_CIUDAD, NAC_EDO," +
					" NAC_PAIS, NAC_NACIONALIDAD, PER_NACIMIENTO," +
					" PER_EDOCIVIL, PER_GENERO, PER_RELIGION," +
					" PER_BAUTIZADO, PER_CALLE, PER_NUMERO," +
					" PER_COLONIA, PER_CIUDAD, PER_EDO, " +
					" PER_PAIS, PER_CP, PER_FAX," +
					" PER_TEL, PER_EMAIL, ACA_CARRERA," +
					" ACA_FECHA, BAC_INSTITUCION, BAC_DIRECCION," +
					" BAC_CIUDAD, BAC_EDO, BAC_PAIS," +
					" BAC_FINICIO, BAC_FFINAL, ANT_INSTITUCION," +
					" ANT_DIRECCION, ANT_CIUDAD, ANT_EDO," +
					" ANT_PAIS, ANT_GRADO, ANT_FINICIO," +
					" ANT_FFINAL, USUARIO, CLAVE," +
					" FECHA, MATRICULA, ESTADO," +
					" MODALIDAD_ID)" +
					" VALUES (" +
					" TO_NUMBER(?,'99999999'), ?, ?," +
					" ?,TO_NUMBER(?,'999'), TO_NUMBER(?,'999')," +
					" TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), TO_DATE(?,'DD/MM/YYYY')," +
					" ?, ?, TO_NUMBER(?,'99')," +
					" ?, ?, ?," +
					" ?, TO_NUMBER(?,'999'), TO_NUMBER(?,'999')," +
					" TO_NUMBER(?,'999'), ?, ?," +
					" ?, ?, ?," +
					" ?, ?, ?," +
					" TO_NUMBER(?,'999'), TO_NUMBER(?,'999'),TO_NUMBER(?,'999')," +
					" TO_DATE(?,'DD/MM/YYYY'), TO_DATE(?,'DD/MM/YYYY'), ?," +
					" ?, TO_NUMBER(?,'999'), TO_NUMBER(?,'999')," +
					" TO_NUMBER(?,'999'), ?, TO_DATE(?,'DD/MM/YYYY')," +
					" TO_DATE(?,'DD/MM/YYYY'), ?, ?," +
					" TO_DATE(?, 'DD/MM/YYYY'), ?, ?," +
					" TO_NUMBER(?,'99'))"; 
			
			
			Object[] parametros = new Object[] {
				solicitud.getFolio(),solicitud.getPerNombre(),solicitud.getPerPaterno(),solicitud.getPerMaterno(),solicitud.getNacCiudad(),solicitud.getNacEdo(),
				solicitud.getNacPais(),solicitud.getNacNacionalidad(),solicitud.getPerNacimiento(),solicitud.getPerEdocivil(),solicitud.getPerGenero(),solicitud.getPerReligion(),
				solicitud.getPerBautizado(),solicitud.getPerCalle(),solicitud.getPerNumero(),solicitud.getPerColonia(),solicitud.getPerCiudad(),solicitud.getPerEdo(),
				solicitud.getPerPais(),solicitud.getPerCp(),solicitud.getPerFax(),solicitud.getPerTel(),solicitud.getPerEmail(),solicitud.getAcaCarrera(),solicitud.getAcaFecha(),
				solicitud.getBacInstitucion(),solicitud.getBacDireccion(),solicitud.getBacCiudad(),solicitud.getBacEdo(),solicitud.getBacPais(),solicitud.getBacFinicio(),
				solicitud.getBacFfinal(),solicitud.getAntInstitucion(),solicitud.getAntDireccion(),solicitud.getAntCiudad(),solicitud.getAntEdo(),solicitud.getAntPais(),
				solicitud.getAntGrado(),solicitud.getAntFinicio(),solicitud.getAntFfinal(),solicitud.getUsuario(),solicitud.getClave(),solicitud.getFecha(),solicitud.getMatricula(),
				solicitud.getEstado(),solicitud.getModalidadId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
					
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.SolicitudDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean insertRegistro(Solicitud solicitud) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_SOLICITUD" + 
					"(FOLIO, PER_NOMBRE, PER_PATERNO, PER_MATERNO, PER_GENERO, PER_EMAIL, USUARIO, CLAVE, ESTADO)" +
					" VALUES (TO_NUMBER(?,'99999999'), ?, ?, ?, ?, ?, ?, ?,1)"; 
			
			
			Object[] parametros = new Object[] {
					solicitud.getFolio(),solicitud.getPerNombre(),solicitud.getPerPaterno(),solicitud.getPerMaterno(),solicitud.getPerGenero(),
					solicitud.getPerEmail(),solicitud.getUsuario(),solicitud.getClave()
	 		 	};
				if (salomonJdbc.update(comando,parametros)==1){
					ok = true;
				}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.SolicitudDao|insertRegistro|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(Solicitud solicitud) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_SOLICITUD " + 
					" SET" +
					" PER_NOMBRE = ?," +
					" PER_PATERNO = ?," +
					" PER_MATERNO = ?," +
					" NAC_CIUDAD = TO_NUMBER(?,'999')," +
					" NAC_EDO = TO_NUMBER(?,'999')," +
					" NAC_PAIS = TO_NUMBER(?,'999')," +
					" NAC_NACIONALIDAD = TO_NUMBER(?,'999')," +
					" PER_NACIMIENTO = TO_DATE(?,'DD/MM/YYYY')," +
					" PER_EDOCIVIL = ?," +
					" PER_GENERO = ?," +
					" PER_RELIGION = TO_NUMBER(?,'99')," +
					" PER_BAUTIZADO = ?," +
					" PER_CALLE = ?," +
					" PER_NUMERO = ?," +
					" PER_COLONIA = ?," +
					" PER_CIUDAD = TO_NUMBER(?,'999')," +
					" PER_EDO = TO_NUMBER(?,'999')," +
					" PER_PAIS = TO_NUMBER(?,'999')," +
					" PER_CP = ?," +
					" PER_FAX = ?," +
					" PER_TEL = ?," +
					" PER_EMAIL = ?," +
					" ACA_CARRERA = ?," +
					" ACA_FECHA = ?," +
					" BAC_INSTITUCION = ?," +
					" BAC_DIRECCION = ?," +
					" BAC_CIUDAD = TO_NUMBER(?,'999')," +
					" BAC_EDO = TO_NUMBER(?,'999')," +
					" BAC_PAIS = TO_NUMBER(?,'999')," +
					" BAC_FINICIO = TO_DATE(?,'DD/MM/YYYY')," +
					" BAC_FFINAL = TO_DATE(?,'DD/MM/YYYY')," +
					" ANT_INSTITUCION = ?," +
					" ANT_DIRECCION = ?," +
					" ANT_CIUDAD = TO_NUMBER(?,'999')," +
					" ANT_EDO = TO_NUMBER(?,'999')," +
					" ANT_PAIS = TO_NUMBER(?,'999')," +
					" ANT_GRADO = ?," +
					" ANT_FINICIO = TO_DATE(?,'DD/MM/YYYY')," +
					" ANT_FFINAL = TO_DATE(?,'DD/MM/YYYY')," +
					" USUARIO = ?," +
					" CLAVE = ?," +
					" FECHA = TO_DATE(?, 'DD/MM/YYYY')," +
					" MATRICULA = ?," +
					" ESTADO = ?" +
					" WHERE FOLIO = TO_NUMBER(?,'99999999')";
			
			Object[] parametros = new Object[] {
					solicitud.getPerNombre(),solicitud.getPerPaterno(),solicitud.getPerMaterno(),solicitud.getNacCiudad(),solicitud.getNacEdo(),
					solicitud.getNacPais(),solicitud.getNacNacionalidad(),solicitud.getPerNacimiento(),solicitud.getPerEdocivil(),solicitud.getPerGenero(),solicitud.getPerReligion(),
					solicitud.getPerBautizado(),solicitud.getPerCalle(),solicitud.getPerNumero(),solicitud.getPerColonia(),solicitud.getPerCiudad(),solicitud.getPerEdo(),
					solicitud.getPerPais(),solicitud.getPerCp(),solicitud.getPerFax(),solicitud.getPerTel(),solicitud.getPerEmail(),solicitud.getAcaCarrera(),solicitud.getAcaFecha(),
					solicitud.getBacInstitucion(),solicitud.getBacDireccion(),solicitud.getBacCiudad(),solicitud.getBacEdo(),solicitud.getBacPais(),solicitud.getBacFinicio(),
					solicitud.getBacFfinal(),solicitud.getAntInstitucion(),solicitud.getAntDireccion(),solicitud.getAntCiudad(),solicitud.getAntEdo(),solicitud.getAntPais(),
					solicitud.getAntGrado(),solicitud.getAntFinicio(),solicitud.getAntFfinal(),solicitud.getUsuario(),solicitud.getClave(),solicitud.getFecha(),solicitud.getMatricula(),
					solicitud.getEstado(),solicitud.getFolio(),
	 		 	};
				if (salomonJdbc.update(comando,parametros)==1){
					ok = true;
				}
				
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.SolicitudDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_SOLICITUD WHERE FOLIO = ?";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.SolicitudDao|deleteReg|:"+ex);
		}

		return ok;
	}
	
	public Solicitud mapeaRegId(String folio) {
		Solicitud objeto = new Solicitud();
		
		try {
			String comando = "SELECT FOLIO, PER_NOMBRE, PER_PATERNO, PER_MATERNO," +
					" NAC_CIUDAD, NAC_EDO, NAC_PAIS, NAC_NACIONALIDAD," +
					" TO_CHAR(PER_NACIMIENTO, 'DD/MM/YYYY') AS PER_NACIMIENTO, PER_EDOCIVIL, PER_GENERO, PER_RELIGION," +
					" PER_BAUTIZADO, PER_CALLE, PER_NUMERO, PER_COLONIA," +
					" PER_CIUDAD, PER_EDO, PER_PAIS, PER_CP," +
					" PER_FAX, PER_TEL, PER_EMAIL, ACA_CARRERA," +
					" ACA_FECHA, BAC_INSTITUCION, BAC_DIRECCION, BAC_CIUDAD," +
					" BAC_EDO, BAC_PAIS, TO_CHAR(BAC_FINICIO, 'DD/MM/YYYY') AS BAC_FINICIO, TO_CHAR(BAC_FFINAL, 'DD/MM/YYYY') AS BAC_FFINAL," +
					" ANT_INSTITUCION, ANT_DIRECCION, ANT_CIUDAD, ANT_EDO," +
					" ANT_PAIS, ANT_GRADO, TO_CHAR(ANT_FINICIO, 'DD/MM/YYYY') AS ANT_FINICIO, TO_CHAR(ANT_FFINAL, 'DD/MM/YYYY') AS ANT_FFINAL," +
					" USUARIO, CLAVE, TO_CHAR(FECHA, 'DD/MM/YYYY') AS FECHA, MATRICULA, ESTADO" +
					" FROM SALOMON.ADM_SOLICITUD WHERE FOLIO = TO_NUMBER(?,'99999999')"; 
			
			Object[] parametros = new Object[] {folio};
			objeto = salomonJdbc.queryForObject(comando, new SolicitudMapper(), parametros);
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.SolicitudDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String folio) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_SOLICITUD WHERE FOLIO = TO_NUMBER(?,'99999999') "; 
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.SolicitudDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public boolean existeUsuario(String usuario, String clave) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT FOLIO, PER_NOMBRE, PER_PATERNO, PER_MATERNO," +
					" NAC_CIUDAD, NAC_EDO, NAC_PAIS, NAC_NACIONALIDAD," +
					" PER_NACIMIENTO, PER_EDOCIVIL, PER_GENERO, PER_RELIGION," +
					" PER_BAUTIZADO, PER_CALLE, PER_NUMERO, PER_COLONIA," +
					" PER_CIUDAD, PER_EDO, PER_PAIS, PER_CP," +
					" PER_FAX, PER_TEL, PER_EMAIL, ACA_CARRERA," +
					" ACA_FECHA, BAC_INSTITUCION, BAC_DIRECCION, BAC_CIUDAD," +
					" BAC_EDO, BAC_PAIS, BAC_FINICIO, BAC_FFINAL," +
					" ANT_INSTITUCION, ANT_DIRECCION, ANT_CIUDAD, ANT_EDO," +
					" ANT_PAIS, ANT_GRADO, ANT_FINICIO, ANT_FFINAL, USUARIO," +
					" CLAVE, TO_CHAR(FECHA, 'DD/MM/YYYY') AS FECHA, MATRICULA, ESTADO" +
					" FROM SALOMON.ADM_SOLICITUD" + 
					" WHERE USUARIO = ?" +
					" AND CLAVE = ?";
			
			Object[] parametros = new Object[] {usuario,clave};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.SolicitudDao|existeUsuario|:"+ex);
		}
		
		return ok;
	}
	
	public boolean existeUsuario(String usuario ) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT FOLIO, PER_NOMBRE, PER_PATERNO, PER_MATERNO," +
					" NAC_CIUDAD, NAC_EDO, NAC_PAIS, NAC_NACIONALIDAD," +
					" PER_NACIMIENTO, PER_EDOCIVIL, PER_GENERO, PER_RELIGION," +
					" PER_BAUTIZADO, PER_CALLE, PER_NUMERO, PER_COLONIA," +
					" PER_CIUDAD, PER_EDO, PER_PAIS, PER_CP," +
					" PER_FAX, PER_TEL, PER_EMAIL, ACA_CARRERA," +
					" ACA_FECHA, BAC_INSTITUCION, BAC_DIRECCION, BAC_CIUDAD," +
					" BAC_EDO, BAC_PAIS, BAC_FINICIO, BAC_FFINAL," +
					" ANT_INSTITUCION, ANT_DIRECCION, ANT_CIUDAD, ANT_EDO," +
					" ANT_PAIS, ANT_GRADO, ANT_FINICIO, ANT_FFINAL," +
					" USUARIO, CLAVE, TO_CHAR(FECHA, 'DD/MM/YYYY') AS FECHA, MATRICULA, ESTADO" +
					" FROM SALOMON.ADM_SOLICITUD" + 
					" WHERE USUARIO = ?";
			
			Object[] parametros = new Object[] {usuario};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.SolicitudDao|existeUsuario|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoReg() {
		String folio = "1";
		
		try{
			String comando = "SELECT NVL(MAX(FOLIO), 0)+1 AS MAXIMO FROM SALOMON.ADM_SOLICITUD"; 

			folio = salomonJdbc.queryForObject(comando, String.class);
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.SolicitudDao|maximoReg|:"+ex);
		}
		
		return folio;
	}
	
	public String getEdad(String folio) {
		String edad = "1";
		
		try{
			String comando = "SELECT FLOOR(MONTHS_BETWEEN(SYSDATE, PER_NACIMIENTO)/12) AS EDAD" +
					" FROM SALOMON.ADM_SOLICITUD" + 
					" WHERE FOLIO = TO_NUMBER(?, '99999999')";
			
			Object[] parametros = new Object[] {folio};
			edad = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.SolicitudDao|getEdad|:"+ex);
		}
		
		return edad;
	}
	
	public String getCarrera(String folio) {
		String carrera	= "";
		
		try{
			String comando = "ACA_CARRERA AS CARRERA" +
					" FROM SALOMON.ADM_SOLICITUD" + 
					" WHERE FOLIO = TO_NUMBER(?, '99999999')";
			
			Object[] parametros = new Object[] {folio};
			carrera = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.SolicitudDao|getCarrera|:"+ex);
		}
		
		return carrera;
	}

}
