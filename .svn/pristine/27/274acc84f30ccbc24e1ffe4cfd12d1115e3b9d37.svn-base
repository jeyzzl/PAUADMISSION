package adm.podium.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AreaMapper implements RowMapper<Area>{
	
	public Area mapRow(ResultSet rs, int arg1) throws SQLException {	
		
		Area objeto = new Area();
		
		objeto.setId(rs.getInt("ID"));
		objeto.setNombre(rs.getString("NOMBRE"));
		objeto.setTiempo(rs.getInt("TIEMPO"));		
		objeto.setMinimoBuenas(rs.getInt("MINIMO_BUENAS"));
		objeto.setPuntos(rs.getInt("PUNTOS"));
		objeto.setCantidadPreguntas(rs.getInt("CANTIDAD_PREGUNTAS"));
		
		return objeto;
	}
}