package adm.catalogo.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatCiudadDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(CatCiudad catCiudad) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO ENOC.CAT_CIUDAD"+
				"(PAIS_ID, ESTADO_ID, CIUDAD_ID, NOMBRE_CIUDAD) "+
				"VALUES( TO_NUMBER (?,'999'), TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), ?)";
			
			Object[] parametros = new Object[] {
				catCiudad.getPaisId(),catCiudad.getEstadoId(),catCiudad.getCiudadId(),catCiudad.getNombreCiudad()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatCiudadDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(CatCiudad catCiudad) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE ENOC.CAT_CIUDAD "+
				"SET NOMBRE_CIUDAD = ? "+
				"WHERE PAIS_ID = TO_NUMBER(?,'999') "+
				"AND ESTADO_ID = TO_NUMBER(?,'999') "+
				"AND CIUDAD_ID = TO_NUMBER(?,'999')";
			
			Object[] parametros = new Object[] {
				catCiudad.getNombreCiudad(),catCiudad.getPaisId(),catCiudad.getEstadoId(),catCiudad.getCiudadId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatCiudadDao|updateReg|:"+ex);			
		}

		return ok;
	}
	
	public boolean deleteReg(String paisId, String estadoId, String ciudadId) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM ENOC.CAT_CIUDAD "+
				"WHERE PAIS_ID = TO_NUMBER(?,'999') "+
				"AND ESTADO_ID = TO_NUMBER(?,'999') "+
				"AND CIUDAD_ID = TO_NUMBER(?,'999')";
				
			Object[] parametros = new Object[] {paisId,estadoId,ciudadId};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatCiudadDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public CatCiudad mapeaRegId(String paisId, String estadoId, String ciudadId ) {
		CatCiudad objeto = new CatCiudad();
		
		try {
			String comando = "SELECT PAIS_ID, ESTADO_ID, CIUDAD_ID, NOMBRE_CIUDAD "+
					"FROM ENOC.CAT_CIUDAD "+
					"WHERE PAIS_ID = TO_NUMBER(?,'999') "+
					"AND ESTADO_ID = TO_NUMBER(?,'999') "+
					"AND CIUDAD_ID = TO_NUMBER(?,'999')";
			
			Object[] parametros = new Object[] {paisId,estadoId,ciudadId};
			objeto = salomonJdbc.queryForObject(comando, new CatCiudadMapper(), parametros);
		} catch (Exception ex) {
			System.out.println("Error - aca.catalogo.spring.CatCiudadDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String paisId, String estadoId, String ciudadId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_CIUDAD "+
				"WHERE PAIS_ID = TO_NUMBER(?,'999') "+
				"AND ESTADO_ID = TO_NUMBER(?,'999') "+
				"AND CIUDAD_ID = TO_NUMBER(?,'999')";
			
			Object[] parametros = new Object[] {paisId,estadoId,ciudadId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}

		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatCiudadDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoReg(String paisId, String estadoId) {
		String maximo	= "001";
		
		try{
			String comando = "SELECT MAX(CIUDAD_ID)+1 AS MAXIMO FROM ENOC.CAT_CIUDAD WHERE PAIS_ID = TO_NUMBER(?,'999') AND ESTADO_ID = TO_NUMBER(?,'999')";			
			Object[] parametros = new Object[] {paisId,estadoId};
			maximo = salomonJdbc.queryForObject(comando,String.class, parametros);			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatCiudadDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}
	
	public String getNombreCiudad(String paisId, String estadoId, String ciudadId) {
		String nombre	= "vacio";		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_CIUDAD WHERE PAIS_ID = TO_NUMBER(?,'999') AND ESTADO_ID = TO_NUMBER(?,'999') AND CIUDAD_ID = TO_NUMBER(?,'999')";			
			Object[] parametros = new Object[] {paisId,estadoId,ciudadId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				comando = "SELECT NOMBRE_CIUDAD FROM ENOC.CAT_CIUDAD WHERE PAIS_ID = TO_NUMBER(?,'999') AND ESTADO_ID = TO_NUMBER(?,'999') AND CIUDAD_ID = TO_NUMBER(?,'999')";
				nombre = salomonJdbc.queryForObject(comando,String.class, parametros);
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatCiudadDao|getNombreCiudad|:"+ex);
		}
		
		return nombre;
	}
	
	public List<CatCiudad> getLista(String paisId, String estadoId, String orden ) {
		List<CatCiudad> lista = new ArrayList<CatCiudad>();		
		try{
			String comando = "SELECT PAIS_ID, ESTADO_ID, CIUDAD_ID, NOMBRE_CIUDAD FROM ENOC.CAT_CIUDAD"
					+ " WHERE PAIS_ID = TO_NUMBER(?,'999')"
					+ " AND ESTADO_ID = TO_NUMBER(?,'999') "+ orden;			
			lista = salomonJdbc.query(comando, new CatCiudadMapper(), paisId, estadoId);			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatCiudadDao|getLista|:"+ex);
		}
		
		return lista;
	}
	
	public List<CatCiudad> getListAll(String orden ) {
		List<CatCiudad> lista = new ArrayList<CatCiudad>();		
		try{
			String comando = "SELECT PAIS_ID, ESTADO_ID, CIUDAD_ID, NOMBRE_CIUDAD FROM ENOC.CAT_CIUDAD "+ orden;			
			lista = salomonJdbc.query(comando, new CatCiudadMapper());			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatCiudadDao|getListAll|:"+ex);
		}
		
		return lista;
	}

	public HashMap<String,CatCiudad> getMapAll( String orden ) {
		
		HashMap<String,CatCiudad> mapa 	= new HashMap<String,CatCiudad>();
		List<CatCiudad> lista 			= new ArrayList<CatCiudad>();
		
		try{
			String comando = "SELECT PAIS_ID, ESTADO_ID, CIUDAD_ID, NOMBRE_CIUDAD FROM ENOC.CAT_CIUDAD "+ orden;		
			lista = salomonJdbc.query(comando, new CatCiudadMapper());
			for(CatCiudad ciudad : lista){
				mapa.put(ciudad.getPaisId()+ciudad.getEstadoId()+ciudad.getCiudadId(), ciudad);
			}
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatCiudadDao|getMapAll|:"+ex);
		}
		
		return mapa;
	}

}
