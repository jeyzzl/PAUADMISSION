package adm.podium.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DeclaracionDao {
	
	@Autowired
	@Qualifier("jdbcExa")
	private JdbcTemplate jdbcExamen;
	
	public Declaracion buscaDeclaracionPorId(int id) {
		Declaracion objeto = new Declaracion();
	
		try {
			String query = "SELECT * FROM EXA.DECLARACION WHERE ID = ?";
			
			objeto = jdbcExamen.queryForObject(query, new DeclaracionMapper());
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.DeclaracionDao|buscaDeclaracionPorId| "+e);
		}
	
		return objeto;
	}
	
	public boolean existeDeclaracion(int id) {
		boolean ok = false;
	
		try {
			String query = "SELECT COUNT(*) FROM EXA.DECLARACION WHERE ID = ?";
			if(jdbcExamen.queryForObject(query, Integer.class, new Object[]{id}) >= 1) {
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.DeclaracionDao|existeDeclaracion| "+e);
		}
		
		return ok;
	}

}
