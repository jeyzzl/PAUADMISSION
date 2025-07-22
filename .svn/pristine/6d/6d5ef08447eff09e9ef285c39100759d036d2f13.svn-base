package adm.podium.spring;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class RespuestaDao {
	
	@Autowired
	@Qualifier("jdbcExa")
	private JdbcTemplate jdbcExamen;
	
	public ArrayList<Respuesta> listaRespuestasPorPreguntaId(int preguntaId) {
		ArrayList<Respuesta> lista = new ArrayList<Respuesta>();
		
		try {
			String query = "SELECT * FROM EXA.RESPUESTA WHERE PREGUNTA_ID = ? ORDER BY ID";
			jdbcExamen.query(query, new RespuestaMapper(), new Object[]{preguntaId});
		} catch (Exception e) {
			System.out.println("ERROR : RespuestaDao | listaRespuestasPorPreguntaId | "+e);
		}
		
		return lista;
	}
	
	public boolean existeRespuesta(int preguntaId) {
		boolean ok = false;		
		
		try {
			String query = "SELECT COUNT(*) FROM EXA.RESPUESTA WHERE PREGUNTA_ID = ?";
			if(jdbcExamen.queryForObject(query, Integer.class, new Object[]{preguntaId}) >= 1) {
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : RespuestaDao | existeRespuesta | "+e);
		}
		
		return ok;
	}
}
