package adm.podium.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DeclaracionMapper implements RowMapper<Declaracion>{
	
	public Declaracion mapRow(ResultSet rs, int arg1) throws SQLException {	
		
		Declaracion objeto = new Declaracion();
		
		objeto.setId(rs.getInt("ID"));
		objeto.setDeclaracion(rs.getString("DECLARACION"));
		objeto.setImagen(rs.getBytes("IMAGEN"));
		objeto.setSubAreaId(rs.getInt("SUB_AREA_ID"));
		objeto.setAreaId(rs.getInt("AREA_ID"));
		
		return objeto;
	}
}