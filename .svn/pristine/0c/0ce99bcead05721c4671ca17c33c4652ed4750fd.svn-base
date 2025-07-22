package adm.alumno.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdmFamiliaMapper implements RowMapper<AdmFamilia> {

	public AdmFamilia mapRow(ResultSet rs, int arg1) throws SQLException {
		AdmFamilia objeto = new AdmFamilia();
		
		objeto.setCodigo(rs.getString("CODIGO"));
		objeto.setHermanos(rs.getString("HERMANOS"));
		objeto.setUbicacion(rs.getString("UBICACION"));
		objeto.setPersonas(rs.getString("PERSONAS"));
		objeto.setCapturado(rs.getString("CAPTURADO"));

		return objeto;
	}

}
