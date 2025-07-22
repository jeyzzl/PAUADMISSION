package adm.podium.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class RespuestaMapper implements RowMapper<Respuesta>{
	
	public Respuesta mapRow(ResultSet rs, int arg1) throws SQLException {	
		
		Respuesta objeto = new Respuesta();
		
		objeto.setId(rs.getInt("ID"));
		objeto.setRespuesta(rs.getString("RESPUESTA"));
		objeto.setCorrecta(rs.getBoolean("CORRECTA"));
		objeto.setPreguntaId(rs.getInt("PREGUNTA_ID"));
		
		return objeto;
	}
}