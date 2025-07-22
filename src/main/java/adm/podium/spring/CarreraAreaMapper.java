package adm.podium.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CarreraAreaMapper implements RowMapper<CarreraArea>{
	
	public CarreraArea mapRow(ResultSet rs, int arg1) throws SQLException {	
		
		CarreraArea objeto = new CarreraArea();
		
		objeto.setId(rs.getInt("ID"));
		objeto.setCarreraId(rs.getInt("CARRERA_ID"));
		objeto.setAreaId(rs.getInt("AREA_ID"));			
		
		return objeto;
	}
}