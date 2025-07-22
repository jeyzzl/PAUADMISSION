package adm.catalogo.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CatEstadoMapper implements RowMapper<CatEstado> {
	
	public CatEstado mapRow(ResultSet rs, int arg1) throws SQLException {
		CatEstado objeto = new CatEstado();
		
		objeto.setPaisId(rs.getString("PAIS_ID"));
		objeto.setEstadoId(rs.getString("ESTADO_ID"));
		objeto.setNombreEstado(rs.getString("NOMBRE_ESTADO"));
		
		return objeto;
	}

}
