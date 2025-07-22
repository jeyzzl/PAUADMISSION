package adm.documento.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PgAdmArchivosMapper implements RowMapper<PgAdmArchivos> {

	public PgAdmArchivos mapRow(ResultSet rs, int arg1) throws SQLException {
		PgAdmArchivos objeto = new PgAdmArchivos();
		
		objeto.setFolio(rs.getString("FOLIO"));
		objeto.setDocumentoId(rs.getString("DOCUMENTO_ID"));
		objeto.setArchivo(rs.getInt("ARCHIVO"));
		objeto.setNombre(rs.getString("NOMBRE"));
		
		return objeto;
	}
}
