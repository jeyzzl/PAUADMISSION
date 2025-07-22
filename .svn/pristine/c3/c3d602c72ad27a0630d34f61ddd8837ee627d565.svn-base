package adm.documento.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PgAdmDocAlumMapper implements RowMapper<PgAdmDocAlum> {

	public PgAdmDocAlum mapRow(ResultSet rs, int arg1) throws SQLException {
		PgAdmDocAlum objeto = new PgAdmDocAlum();
		
		objeto.setFolio(rs.getString("FOLIO"));
		objeto.setDocumentoId(rs.getString("DOCUMENTO_ID"));
		objeto.setHoja(rs.getString("HOJA"));
		objeto.setImagen(rs.getInt("IMAGEN"));
		
		return objeto;
	}

}
