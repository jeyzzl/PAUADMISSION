package adm.alumno.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdmContactoMapper implements RowMapper<AdmContacto> {
		
	public AdmContacto mapRow(ResultSet rs, int arg1) throws SQLException {
		AdmContacto objeto = new AdmContacto();
		
		objeto.setFolio(rs.getString("FOLIO"));
		objeto.setOrden(rs.getString("ORDEN"));
		objeto.setEnvio(rs.getString("ENVIO"));
		objeto.setFecha(rs.getString("FECHA"));
		objeto.setMensaje(rs.getString("MENSAJE"));
		objeto.setEstado(rs.getString("ESTADO"));

		return null;
	}
}
