package adm.catalogo.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CatPaisMapper implements RowMapper<CatPais> {

	public CatPais mapRow(ResultSet rs, int arg1) throws SQLException {
		CatPais objeto = new CatPais();
		
		objeto.setPaisId(rs.getString("PAIS_ID"));
		objeto.setNombrePais(rs.getString("NOMBRE_PAIS"));
		objeto.setNacionalidad(rs.getString("NACIONALIDAD"));
		
		return objeto;
	}

}
