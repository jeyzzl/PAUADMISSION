package adm.catalogo.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatEstadoDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(CatEstado catEstado) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO ENOC.CAT_ESTADO"+
				"(PAIS_ID, ESTADO_ID, NOMBRE_ESTADO) "+
				"VALUES( TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), ?)";
			
			Object[] parametros = new Object[] {
				catEstado.getPaisId(),catEstado.getEstadoId(),catEstado.getNombreEstado()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatEstadoDao|insertReg|:"+ex);
		}

		return ok;
	}
			
	public boolean updateReg(CatEstado catEstado) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE ENOC.CAT_ESTADO "+
				"SET NOMBRE_ESTADO = ? "+
				"WHERE PAIS_ID = TO_NUMBER(?,'999') AND ESTADO_ID = TO_NUMBER(?,'999')";
			
			Object[] parametros = new Object[] {
					catEstado.getNombreEstado(),catEstado.getPaisId(),catEstado.getEstadoId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatEstadoDao|updateReg|:"+ex);			
		}
		
		return ok;
	}
	
	
	public boolean deleteReg(String paisId, String estadoId) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM ENOC.CAT_ESTADO "+
				"WHERE PAIS_ID = TO_NUMBER(?,'999') AND ESTADO_ID = TO_NUMBER(?,'999')";
			
			Object[] parametros = new Object[] {paisId,estadoId};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatEstadoDao|deleteReg|:"+ex);
		}
				
		return ok;
	}
	
	public CatEstado mapeaRegId(String paisId, String estadoId) {
		CatEstado objeto = new CatEstado();
		
		try {
			String comando = "SELECT PAIS_ID, ESTADO_ID, NOMBRE_ESTADO "+
					"FROM ENOC.CAT_ESTADO WHERE PAIS_ID = TO_NUMBER(?,'999') AND ESTADO_ID = TO_NUMBER(?, '999')";
			
			Object[] parametros = new Object[] {paisId,estadoId};
			objeto = salomonJdbc.queryForObject(comando, new CatEstadoMapper(), parametros);
		} catch (Exception ex) {
			System.out.println("Error - aca.catalogo.spring.CatEstadoDao|mapeaRegId|:"+ex);
		}
		
		return objeto;		
	}
	
	public boolean existeReg(String paisId, String estadoId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_ESTADO "+
				"WHERE PAIS_ID = TO_NUMBER(?,'999') AND ESTADO_ID = TO_NUMBER(?,'999')";
			
			Object[] parametros = new Object[] {paisId,estadoId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}

		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatEstadoDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoReg(String paisId) {
		String maximo = "1";
		
		try{
			String comando = "SELECT MAX(ESTADO_ID)+1 AS MAXIMO FROM ENOC.CAT_ESTADO WHERE PAIS_ID = TO_NUMBER(?,'999')";
			
			Object[] parametros = new Object[] {paisId};
			maximo = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatEstadoDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}
	
	public String getNombreEstado(String paisId, String estadoId){
		String nombre = "vacio";		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_ESTADO WHERE PAIS_ID = TO_NUMBER(?,'999') AND ESTADO_ID = TO_NUMBER(?,'999')";			
			Object[] parametros = new Object[] {paisId,estadoId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				comando = "SELECT NOMBRE_ESTADO FROM ENOC.CAT_ESTADO WHERE PAIS_ID = TO_NUMBER(?,'999') AND ESTADO_ID = TO_NUMBER(?,'999')";
				nombre = salomonJdbc.queryForObject(comando,String.class, parametros);
			}		
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatEstadoDao|getNombreEstado|:"+ex+"::"+paisId+"::"+estadoId);
		}		
		return nombre;
	}
	
	public List<CatEstado> getLista(String paisId, String orden ) {
		List<CatEstado> lista	= new ArrayList<CatEstado>();		
		try{
			String comando = "SELECT PAIS_ID, ESTADO_ID, NOMBRE_ESTADO FROM ENOC.CAT_ESTADO WHERE PAIS_ID = TO_NUMBER(?,'999') "+ orden;
			Object[] parametros = new Object[] {paisId};
			lista = salomonJdbc.query(comando, new CatEstadoMapper(), parametros);			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatEstadoDao|getLista|:"+ex);
		}
		
		return lista;
	}
	
	public List<CatEstado> getListAll(String orden) {
		List<CatEstado> lista	= new ArrayList<CatEstado>();
		
		try{
			String comando = "SELECT PAIS_ID, ESTADO_ID, NOMBRE_ESTADO "+
				"FROM ENOC.CAT_ESTADO "+ orden;
			
			lista = salomonJdbc.query(comando, new CatEstadoMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatEstadoDao|getListAll|:"+ex);
		}
		
		return lista;
	}

	public HashMap<String,CatEstado> getMapAll() {
		
		HashMap<String,CatEstado> mapa	= new HashMap<String,CatEstado>();
		List<CatEstado> lista			= new ArrayList<CatEstado>();		
		try{
			String comando = " SELECT PAIS_ID, ESTADO_ID, NOMBRE_ESTADO, CORTO, SEP_ID, SEP_CORTO, SEMAFORO FROM ENOC.CAT_ESTADO ";
			lista = salomonJdbc.query(comando, new CatEstadoMapper());	
			for ( CatEstado estado : lista){
				 mapa.put(estado.getPaisId()+estado.getEstadoId(), estado);
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatEstadoDao|getMapAll|:"+ex);
		}
		
		return mapa;
	}
}
