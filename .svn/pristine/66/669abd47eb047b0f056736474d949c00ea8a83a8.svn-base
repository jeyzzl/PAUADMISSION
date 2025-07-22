package adm.alumno.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdmRecuperarMapper implements RowMapper<AdmRecuperar> {

	public AdmRecuperar mapRow(ResultSet rs, int rowNum) throws SQLException {
		AdmRecuperar objeto = new AdmRecuperar();
		
		objeto.setClave(rs.getString("CLAVE"));
		objeto.setCorreo(rs.getString("CORREO"));
		objeto.setFecha(rs.getString("FECHA"));
		
		return objeto;
	}

}
