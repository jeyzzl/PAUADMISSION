package adm.podium.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CarreraAreaDao {
	
	@Autowired
	@Qualifier("jdbcExa")
	private JdbcTemplate jdbcExamen;
	
	public HashMap<Integer,Integer> mapaCarreraArea(int carreraId) {		
		HashMap<Integer,Integer> mapa = new HashMap<Integer,Integer>();
		List<CarreraArea> lista		= new ArrayList<CarreraArea>();
		try{
			String query = "SELECT * FROM EXA.CARRERA_AREA WHERE CARRERA_ID = ?";						
			lista = jdbcExamen.query(query, new CarreraAreaMapper(), new Object[]{carreraId});
			for (CarreraArea carrera: lista ) {
				mapa.put(carrera.getId(), carrera.getAreaId());
			}			
		}catch(Exception e){
			System.out.println("Error - adm.podium.spring.CarreraAreaDao|mapaCarreraArea|:"+e);
		}
		
		return mapa;
	}

}
