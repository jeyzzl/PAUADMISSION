package adm.podium.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AspiranteDao {
	
	@Autowired
	@Qualifier("jdbcExa")
	private JdbcTemplate jdbcExamen;
	
	public Aspirante buscaAspirantePorEmail(String email) {
		Aspirante objeto = new Aspirante();
		try {
			String query = "SELECT COUNT(*) FROM EXA.ASPIRANTE WHERE EMAIL = ?";
			if (jdbcExamen.queryForObject(query, Integer.class, email) >= 1){
				query = "SELECT * FROM EXA.ASPIRANTE WHERE EMAIL = ?";
				objeto = jdbcExamen.queryForObject(query, new AspiranteMapper(), email);
			}							
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.AspiranteDao|buscaAspirantePorEmail| " +e);
		}
		
		return objeto;
	}

	public Aspirante buscaAspirantePorFolio(int folio) {
		Aspirante objeto = new Aspirante();
		try {
			String query = "SELECT COUNT(FOLIO) FROM EXA.ASPIRANTE WHERE FOLIO = ? GROUP BY ID ORDER BY ID DESC LIMIT 1";
			if (jdbcExamen.queryForObject(query, Integer.class, folio) >= 1){
				query = "SELECT * FROM EXA.ASPIRANTE WHERE FOLIO = ? ORDER BY ID DESC LIMIT 1";
				objeto = jdbcExamen.queryForObject(query, new AspiranteMapper(), folio);
			}							
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.AspiranteDao|buscaAspirantePorFolio| " +e);
		}
		
		return objeto;
	}
	
	public boolean existeAspirante(int folio) {
		boolean ok = false;
		
		try {
			String query = "SELECT COUNT(*) FROM EXA.ASPIRANTE WHERE FOLIO = ?";
			
			if(jdbcExamen.queryForObject(query, Integer.class, folio) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.AspiranteDao|existeAspirante| "+e);
		}
		
		return ok;
	}
	
	public boolean grabarDatos(Aspirante objeto) {
		boolean ok = false;
		
		try {
			String query = "UPDATE EXA.ASPIRANTE SET GENERO = ?, NACIONALIDAD_ID = ?, EDAD = ?, GRADO = ?, MODALIDAD = ? WHERE FOLIO = ?";
			
			Object[] parametros = new Object[]{
					objeto.getGenero(),objeto.getNacionalidadId(),objeto.getEdad(),objeto.getGrado(),objeto.getModalidad(),objeto.getFolio()
			};
			
			if(jdbcExamen.update(query, parametros) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.AspiranteDao|grabarDatos| "+e);
		}
		
		return ok;
	}
		
}
