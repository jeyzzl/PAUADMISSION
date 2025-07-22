package adm.alumno.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdmAlumPreMapper implements RowMapper<AdmAlumPre> {

	public AdmAlumPre mapRow(ResultSet rs, int arg1) throws SQLException {
		AdmAlumPre objeto = new AdmAlumPre();
		
		objeto.setAlumFolio(rs.getString("ALUM_FOLIO"));
		objeto.setCarreraId(rs.getString("CARRERA_ID"));
		objeto.setFolio(rs.getString("FOLIO"));
		objeto.setEstado(rs.getString("ESTADO"));
		
		return objeto;
	}

}
