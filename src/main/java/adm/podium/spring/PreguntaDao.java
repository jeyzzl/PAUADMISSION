package adm.podium.spring;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PreguntaDao {
	
	@Autowired
	@Qualifier("jdbcExa")
	private JdbcTemplate jdbcExamen;
	
	public ArrayList<Integer> listaPreguntaId(int areaId) {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		try {
			String query = "SELECT ID FROM EXA.PREGUNTA  WHERE AREA_ID = ? ORDER BY ID";
			jdbcExamen.query(query, new PreguntaMapper(), new Object[]{areaId});
		} catch (Exception e) {
			System.out.println("ERROR : PreguntaDao | listaPreguntaId | "+e);
		}
		
		return lista;
	}
	
	public Pregunta buscaPreguntaPorId(int id) {
		Pregunta objeto = new Pregunta();
		
		try {
			String query = "SELECT * FROM EXA.PREGUNTA WHERE ID = ?";
			jdbcExamen.query(query, new PreguntaMapper(), new Object[]{id});
		} catch (Exception e) {
			System.out.println("ERROR : PreguntaDao | buscaPreguntaPorId | "+e);
		}
	
		return objeto;
	}
	
	public boolean existePregunta(int preguntaId) {
		boolean ok = false;
		
		try {
			String query = "SELECT COUNT(*) FROM EXA.PREGUNTA WHERE ID = ?";
			if(jdbcExamen.queryForObject(query, Integer.class, new Object[]{preguntaId}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : buscaPreguntaPorId | existePregunta | "+e);
		}
		
		return ok;
	}
}
