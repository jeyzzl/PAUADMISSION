package adm.podium.spring;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class NacionalidadDao {
	
	@Autowired
	@Qualifier("jdbcExa")
	private JdbcTemplate jdbcExamen;
	
	public HashMap<Integer,Nacionalidad> mapaNacionalidad(){
		HashMap<Integer,Nacionalidad> mapa 	= new HashMap<Integer,Nacionalidad>();
		
		try {
			String query = "SELECT * FROM EXA.NACIONALIDAD ORDER BY NACIONALIDAD";
			jdbcExamen.query(query, new NacionalidadMapper());
		} catch (Exception e) {
			System.out.println("ERROR : NacionalidadDao | mapaNacionalidad | "+e);
		}
		
		return mapa;
	}
	
	public ArrayList<Nacionalidad> listNacionalidades() {
		ArrayList<Nacionalidad> lista = new ArrayList<Nacionalidad>();
		
		try {
			String query = "SELECT * FROM EXA.NACIONALIDAD ORDER BY NACIONALIDAD";
			jdbcExamen.query(query, new NacionalidadMapper());
		} catch (Exception e) {
			System.out.println("ERROR : NacionalidadDao | listNacionalidades | "+e);
		}
		
		return lista;
	}

}
