package adm.podium.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AreaDao {
	
	@Autowired
	@Qualifier("jdbcExa")
	private JdbcTemplate jdbcExamen;

	public List<Area> lisAreasPorCarreraId(int carreraId) {
		List<Area> lista = new ArrayList<Area>();				
		try {
			String query = "SELECT * FROM EXA.AREA WHERE ID IN(SELECT AREA_ID FROM EXA.CARRERA_AREA WHERE CARRERA_ID = ? ORDER BY AREA_ID) ORDER BY ID";	
			lista = jdbcExamen.query(query, new AreaMapper(),new Object[]{carreraId});
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.AreaDao|lisAreasPorCarreraId| "+e);
		}		
		return lista;
	}

	public Area buscaAreaPorId(int id) {
		Area objeto = new Area();		
		try {
			String query = "SELECT * FROM EXA.AREA WHERE ID = ?";
			objeto = jdbcExamen.queryForObject(query, new AreaMapper(), new Object[]{id});
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.AreaDao|buscarAreaPorId| "+e);
		}
	
		return objeto;
	}
	
	public boolean existeArea(int areaId) {
		boolean ok = false;
		
		try {
			String query = "SELECT COUNT(*) FROM EXA.AREA WHERE ID = ?";
			
			if(jdbcExamen.queryForObject(query, Integer.class, new Object[]{areaId}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.AreaDao|existeArea| "+e);
		}
		
		return ok;
	}

	public HashMap<Integer,Area> mapaArea(){
		HashMap<Integer,Area> mapa = new HashMap<Integer,Area>();
		List<Area> lista	= new ArrayList<Area>();
		
		try {
			String query = "SELECT * FROM EXA.AREA";
			
			lista = jdbcExamen.query(query, new AreaMapper());	

					for(Area area : lista) {
						mapa.put(area.getId(), area);
						
					}
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.AreaDao|mapaArea| "+e);
		}
		
		return mapa;
	}

}
