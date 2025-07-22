package adm.alumno.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdmAlumDatosMapper implements RowMapper<AdmAlumDatos> {

	public AdmAlumDatos mapRow(ResultSet rs, int arg1) throws SQLException {
		AdmAlumDatos objeto = new AdmAlumDatos();
		
		objeto.setFolio(rs.getString("FOLIO"));
		objeto.setCodigoPersonal(rs.getString("CODIGO_PERSONAL"));
		objeto.setNombre(rs.getString("NOMBRE"));
		objeto.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
		objeto.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
		objeto.setModalidad(rs.getString("MODALIDAD_ID"));
		objeto.setCarreraId(rs.getString("CARRERA_ID"));
		objeto.setFNac(rs.getString("F_NACIMIENTO"));
		
		return objeto;
	}

}
