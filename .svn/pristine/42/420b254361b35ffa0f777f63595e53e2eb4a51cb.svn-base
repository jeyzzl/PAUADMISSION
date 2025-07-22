package adm.podium.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExamenDao {
	
	@Autowired
	@Qualifier("jdbcExa")
	private JdbcTemplate jdbcExamen;	
	
	public Examen buscaExamen(int folio) {
		Examen objeto = new Examen();
		try {
			String query = "SELECT * FROM EXA.EXAMEN WHERE FOLIO = ?";
			jdbcExamen.query(query, new ExamenMapper(), new Object[]{folio});
		} catch (Exception e) {
			System.out.println("ERROR : ExamenDao | buscaExamen | "+e);
		}
		
		return objeto;
	}
	
	public String fechaExamen(String examenId) {
		String fecha = "-";
		
		int exaId = Integer.parseInt(examenId);
		try {
			String query = "SELECT FECHA FROM EXA.EXAMEN WHERE ID = ?";
			fecha = jdbcExamen.queryForObject(query, String.class, new Object[] {exaId});
		} catch (Exception e) {
			System.out.println("ERROR : ExamenDao | fechaExamen | "+e);
		}
		
		return fecha;
	}
	
	public boolean existeActivo(int folio) {
		boolean ok = false;		
		try {
			String query = "SELECT COUNT(*) FROM EXA.EXAMEN WHERE FOLIO = ? AND ACTIVO = TRUE";
			if(jdbcExamen.queryForObject(query, Integer.class, new Object[]{folio}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : ExamenDao | existeActivo | "+e);
		}
		
		return ok;
	}

	public boolean creaExamen(int folio) {
		boolean ok = false;		
		try {
			String query = "INSERT INTO EXA.EXAMEN(FOLIO,FECHA,INICIO,TIEMPO,ACTIVO)"
					+ " VALUES(?,NOW(),NOW(),'0',TRUE)";
			if(jdbcExamen.update(query, new Object[]{folio}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : ExamenDao | creaExamen | "+e);
		}
		
		return ok;
	}
	
	public List<Examen> lisExamenesPorFolio(int folio, String orden) {
		List<Examen> lista = new ArrayList<Examen>();
		try {
			String query = "SELECT * FROM EXA.EXAMEN WHERE FOLIO = ? "+orden;	
			lista = jdbcExamen.query(query, new ExamenMapper(), new Object[]{folio});
		} catch (Exception e) {
			System.out.println("ERROR : ExamenDao | lisExamenesPorFolio | "+e);
		}		
		return lista;
	}
		
}
