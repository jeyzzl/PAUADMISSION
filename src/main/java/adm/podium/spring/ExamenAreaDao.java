package adm.podium.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExamenAreaDao {

	@Autowired
	@Qualifier("jdbcExa")
	private JdbcTemplate jdbcExamen;
	
	public List<ExamenArea> listaExamenArea(int examenId) {
		List<ExamenArea> lista = new ArrayList<ExamenArea>();

		try {
			String query = "SELECT * FROM EXA.EXAMEN_AREA WHERE EXAMEN_ID = ? ORDER BY AREA_ID";
			lista = jdbcExamen.query(query, new ExamenAreaMapper(), new Object[]{examenId});
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.ExamenAreaDao|listaExamenArea| "+e);
		}
		
		return lista;
	}
	
	public boolean existeExamenArea(int examenId) {
		boolean ok = false;
		
		try {
			String query = "SELECT COUNT(*) FROM EXA.EXAMEN_AREA WHERE EXAMEN_ID = ?";
			if(jdbcExamen.queryForObject(query, Integer.class, new Object[]{examenId}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.ExamenAreaDao|existeExamenArea| "+e);
		}
		
		return ok;
	}

	public boolean creaExamenArea(int areaId, int examenId) {
		boolean ok = false;
		
		try {
			String query = "INSERT INTO EXA.EXAMEN_AREA(TERMINO,ACTIVO,AREA_ID,EXAMEN_ID)"
					+ " VALUES(FALSE,FALSE,?,?)";
			if(jdbcExamen.update(query, new Object[]{areaId,examenId}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.ExamenAreaDao|creaExamenArea| "+e);
		}
		
		return ok;
	}

	public boolean activarExamenArea(int examenId, int areaId) {
		boolean ok = false;
		
		try {
			String query = "UPDATE EXA.EXAMEN_AREA SET ACTIVO = TRUE WHERE EXAMEN_ID = ? AND AREA_ID = ?";
			if(jdbcExamen.update(query, new Object[]{examenId,areaId}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.ExamenAreaDao|activarExamenArea| "+e);
		}
		
		return ok;
	}

	public boolean terminarExamenArea(int examenId, int areaId) {
		boolean ok = false;
		
		try {
			String query = "UPDATE EXA.EXAMEN_AREA SET ACTIVO = TRUE WHERE EXAMEN_ID = ? AND AREA_ID = ?";
			if(jdbcExamen.update(query, new Object[]{examenId,areaId}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.ExamenAreaDao|terminarExamenArea| "+e);
		}
		
		return ok;
	}
	
	public boolean examenAreaTerminadoCompletamente(int examenId) {
		boolean ok = false;
		
		try {
			String query = "SELECT COUNT(*) FROM EXA.EXAMEN_AREA WHERE EXAMEN_ID = ? AND TERMINO = FALSE";
			if(jdbcExamen.queryForObject(query, Integer.class, new Object[]{examenId}) == 0){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : ExamenAreaDao | ExamenAreaTerminadoCompletamente | "+e);
		}
		
		return ok;
	}

	public int examenAreaActiva(int examenId) {
		int dato = 0;
		String query = "SELECT COUNT(*) FROM EXA.EXAMEN_AREA WHERE EXAMEN_ID = ? AND ACTIVO = TRUE AND TERMINO = FALSE";
		
		try {
			if(jdbcExamen.queryForObject(query, Integer.class, new Object[]{examenId}) >= 1){
				query = "SELECT AREA_ID FROM EXA.EXAMEN_AREA WHERE EXAMEN_ID = ? AND ACTIVO = TRUE AND TERMINO = FALSE";
				
				dato = jdbcExamen.queryForObject(query, Integer.class, new Object[]{examenId});
			}
		} catch (Exception e) {
			System.out.println("ERROR : ExamenAreaDao | examenAreaActiva | "+e);
		}
		
		return dato;
	}
	
	public float getPuntosPorArea(int examenId, String areas){
		float puntos 	= 0;				
		try {
			String query = "SELECT COUNT(*) FROM EXA.EXAMEN_AREA WHERE EXAMEN_ID = ? AND AREA_ID IN ("+areas+")";
			if(jdbcExamen.queryForObject(query, Integer.class, new Object[]{examenId}) >= 1){
				query = "SELECT COALESCE(SUM(PUNTOS),0) FROM EXA.EXAMEN_AREA WHERE EXAMEN_ID = ? AND AREA_ID IN ("+areas+")";				
				puntos = jdbcExamen.queryForObject(query, Float.class, new Object[]{examenId});
			}
		} catch (Exception e) {
			System.out.println("ERROR : ExamenAreaDao | getPuntosPorArea | "+e);
		}		
		return puntos;
	}	
		
}
