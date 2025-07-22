package adm.podium.spring;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.springframework.jdbc.core.RowMapper;

public class CarreraMapper implements RowMapper<Carrera>{
	
	public Carrera mapRow(ResultSet rs, int arg1) throws SQLException {	
		
		
		Carrera objeto = new Carrera();
		
		objeto.setId(rs.getInt("ID"));
		objeto.setNombre(rs.getString("NOMBRE"));
		
		return objeto;
	}
}