package adm.alumno.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdmFotoMapper implements RowMapper<AdmFoto> {

	public AdmFoto mapRow(ResultSet rs, int arg1) throws SQLException {
		AdmFoto objeto = new AdmFoto();
		
		objeto.setFolio(rs.getString("FOLIO"));
		objeto.setFoto(rs.getBytes("FOTO"));
		objeto.setFecha(rs.getString("FECHA"));
		
		return objeto;
	}
}
