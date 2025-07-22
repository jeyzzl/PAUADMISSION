package adm.podium.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CarreraDao {
	
	@Autowired
	@Qualifier("jdbcExa")
	private JdbcTemplate jdbcExamen;
	
	public HashMap<Integer,Carrera> mapCarrera() {		
		HashMap<Integer,Carrera> mapa = new HashMap<Integer,Carrera>();
		List<Carrera> lista		= new ArrayList<Carrera>();
		try{
			String query = "SELECT * FROM EXA.CARRERA";							
			lista = jdbcExamen.query(query, new CarreraMapper());
			for (Carrera carrera: lista ) {
				mapa.put(carrera.getId(), carrera);
			}			
		}catch(Exception e){
			System.out.println("Error - adm.podium.spring.CarreraDao|mapaCarrera|:"+e);
		}
		
		return mapa;
	}

}
