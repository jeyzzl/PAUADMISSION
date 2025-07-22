package adm.alumno.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class SolicitudMapper implements RowMapper<Solicitud> {
	
	public Solicitud mapRow(ResultSet rs, int arg1) throws SQLException {
		Solicitud objeto = new Solicitud();
		
		objeto.setFolio(rs.getString("FOLIO"));
		objeto.setPerNombre(rs.getString("PER_NOMBRE"));
		objeto.setPerPaterno(rs.getString("PER_PATERNO"));
		objeto.setPerMaterno(rs.getString("PER_MATERNO"));
		objeto.setNacCiudad(rs.getString("NAC_CIUDAD"));
		objeto.setNacEdo(rs.getString("NAC_EDO"));
		objeto.setNacPais(rs.getString("NAC_PAIS"));
		objeto.setNacNacionalidad(rs.getString("NAC_NACIONALIDAD"));
		objeto.setPerNacimiento(rs.getString("PER_NACIMIENTO"));
		objeto.setPerEdocivil(rs.getString("PER_EDOCIVIL"));
		objeto.setPerGenero(rs.getString("PER_GENERO"));
		objeto.setPerReligion(rs.getString("PER_RELIGION"));
		objeto.setPerBautizado(rs.getString("PER_BAUTIZADO"));
		objeto.setPerCalle(rs.getString("PER_CALLE"));
		objeto.setPerNumero(rs.getString("PER_NUMERO"));
		objeto.setPerColonia(rs.getString("PER_COLONIA"));
		objeto.setPerCiudad(rs.getString("PER_CIUDAD"));
		objeto.setPerEdo(rs.getString("PER_EDO"));
		objeto.setPerPais(rs.getString("PER_PAIS"));
		objeto.setPerCp(rs.getString("PER_CP"));
		objeto.setPerFax(rs.getString("PER_FAX"));
		objeto.setPerTel(rs.getString("PER_TEL"));
		objeto.setPerEmail(rs.getString("PER_EMAIL"));
		objeto.setAcaCarrera(rs.getString("ACA_CARRERA"));
		objeto.setAcaFecha(rs.getString("ACA_FECHA"));
		objeto.setBacInstitucion(rs.getString("BAC_INSTITUCION"));
		objeto.setBacDireccion(rs.getString("BAC_DIRECCION"));
		objeto.setBacCiudad(rs.getString("BAC_CIUDAD"));
		objeto.setBacEdo(rs.getString("BAC_EDO"));
		objeto.setBacPais(rs.getString("BAC_PAIS"));
		objeto.setBacFinicio(rs.getString("BAC_FINICIO"));
		objeto.setBacFfinal(rs.getString("BAC_FFINAL"));
		objeto.setAntInstitucion(rs.getString("ANT_INSTITUCION"));
		objeto.setAntDireccion(rs.getString("ANT_DIRECCION"));
		objeto.setAntCiudad(rs.getString("ANT_CIUDAD"));
		objeto.setAntEdo(rs.getString("ANT_EDO"));
		objeto.setAntPais(rs.getString("ANT_PAIS"));
		objeto.setAntGrado(rs.getString("ANT_GRADO"));
		objeto.setAntFinicio(rs.getString("ANT_FINICIO"));
		objeto.setAntFfinal(rs.getString("ANT_FFINAL"));
		objeto.setUsuario(rs.getString("USUARIO"));
		objeto.setClave(rs.getString("CLAVE"));
		objeto.setFecha(rs.getString("FECHA"));
		objeto.setMatricula(rs.getString("MATRICULA"));
		objeto.setEstado(rs.getString("ESTADO"));
		
		return objeto;
	}

}
