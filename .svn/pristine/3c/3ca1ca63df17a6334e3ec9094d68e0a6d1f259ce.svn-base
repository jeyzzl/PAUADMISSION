package adm.podium.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ExamenAreaPreguntaMapper implements RowMapper<ExamenAreaPregunta>{
	
	public ExamenAreaPregunta mapRow(ResultSet rs, int arg1) throws SQLException {	
		
		ExamenAreaPregunta objeto = new ExamenAreaPregunta();
		
		objeto.setId(rs.getInt("ID"));
		objeto.setInicio(rs.getString("INICIO"));
		objeto.setTermino(rs.getString("TERMINO"));
		objeto.setTiempo(rs.getString("TIEMPO"));
		objeto.setPreguntaId(rs.getInt("PREGUNTA_ID"));
		objeto.setRespuestaId(rs.getInt("RESPUESTA_ID"));
		objeto.setAreaId(rs.getInt("EXAMEN_AREA_ID"));
		objeto.setExamenId(rs.getInt("EXAMEN_ID"));
		
		return objeto;
	}
}