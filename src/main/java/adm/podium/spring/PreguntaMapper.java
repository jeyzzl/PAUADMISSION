package adm.podium.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PreguntaMapper implements RowMapper<Pregunta>{
	
	public Pregunta mapRow(ResultSet rs, int arg1) throws SQLException {	
		
		Pregunta objeto = new Pregunta();
		
		objeto.setId(rs.getInt("ID"));
		objeto.setPregunta(rs.getString("PREGUNTA"));
		objeto.setDb(rs.getString("DB"));
		objeto.setGrado(rs.getString("GRADO"));
		objeto.setDificultad(rs.getString("DIFICULTAD"));
		objeto.setDeclaracionId(rs.getInt("DECLARACION_ID"));
		objeto.setSubAreaId(rs.getInt("SUB_AREA_ID"));
		objeto.setAreaId(rs.getInt("AREA_ID"));
		
		return objeto;
	}
}