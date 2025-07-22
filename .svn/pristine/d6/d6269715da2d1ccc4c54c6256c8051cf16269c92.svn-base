package adm.podium.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class NacionalidadMapper implements RowMapper<Nacionalidad>{
	
	public Nacionalidad mapRow(ResultSet rs, int arg1) throws SQLException {	
		
		Nacionalidad objeto = new Nacionalidad();
		
		objeto.setId(rs.getInt("ID"));
		objeto.setPais(rs.getString("PAIS"));
		objeto.setNacionalidad(rs.getString("NACIONALIDAD"));
		
		return objeto;
	}
}