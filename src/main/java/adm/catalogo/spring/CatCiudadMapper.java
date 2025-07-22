package adm.catalogo.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CatCiudadMapper implements RowMapper<CatCiudad> {
	
	public CatCiudad mapRow(ResultSet rs, int arg1) throws SQLException {
		CatCiudad objeto =new CatCiudad();
		
		objeto.setPaisId(rs.getString("PAIS_ID"));
		objeto.setEstadoId(rs.getString("ESTADO_ID"));
		objeto.setCiudadId(rs.getString("CIUDAD_ID"));
		objeto.setNombreCiudad(rs.getString("NOMBRE_CIUDAD"));
		
		return objeto;
	}

}
