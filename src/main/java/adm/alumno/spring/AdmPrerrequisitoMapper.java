package adm.alumno.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdmPrerrequisitoMapper implements RowMapper<AdmPrerrequisito> {

	public AdmPrerrequisito mapRow(ResultSet rs, int arg1) throws SQLException {
		AdmPrerrequisito objeto = new AdmPrerrequisito();
		
		objeto.setCarreraId(rs.getString("CARRERA_ID"));
		objeto.setFolio(rs.getString("FOLIO"));
		objeto.setNombre(rs.getString("NOMBRE"));
		
		return objeto;
	}

}
