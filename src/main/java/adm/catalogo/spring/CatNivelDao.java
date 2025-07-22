package adm.catalogo.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatNivelDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(CatNivel catNivel) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO "+
				"ENOC.CAT_NIVEL(NIVEL_ID, NOMBRE_NIVEL) "+
				"VALUES( TO_NUMBER(?,'99'), ? ) ";
			

			Object[] parametros = new Object[] {
				catNivel.getNivelId(),catNivel.getNombreNivel()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatNivelDao|insertReg|:"+ex);			
		}
		
		return ok;
	}	
	
	public boolean updateReg(CatNivel catNivel) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE ENOC.CAT_NIVEL "+
				"SET NOMBRE_NIVEL = ? "+
				"WHERE NIVEL_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {
				catNivel.getNombreNivel(),catNivel.getNivelId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatNivelDao|updateReg|:"+ex);		
		}
		
		return ok;
	}
	
	public boolean deleteReg(String nivelId) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM ENOC.CAT_NIVEL "+
				"WHERE NIVEL_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {nivelId};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatNivelDao|deleteReg|:"+ex);			
		}

		return ok;
	}
	
	public CatNivel mapeaRegId(String nivelId) {
		CatNivel objeto = new CatNivel();
		
		try {
			String comando = "SELECT NIVEL_ID, NOMBRE_NIVEL "+
					"FROM ENOC.CAT_NIVEL WHERE NIVEL_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {nivelId};
			objeto = salomonJdbc.queryForObject(comando, new CatNivelMapper(), parametros);
		} catch (Exception ex) {
			System.out.println("Error - aca.catalogo.spring.CatNivelDao|mapeaRegId|:"+ex);
		}

		return objeto;
	}
	
	public boolean existeReg(String nivelId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_NIVEL WHERE NIVEL_ID = TO_NUMBER(?,'99') ";
			
			Object[] parametros = new Object[] {nivelId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatNivelDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoReg() {
		String maximo = "1";
		
		try{
			String comando = "SELECT MAX(NIVEL_ID)+1 MAXIMO FROM ENOC.CAT_NIVEL";
			
			maximo = salomonJdbc.queryForObject(comando,String.class);
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatNivelDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}
	
	public String getNivelNombre(String nivelId) {
		String nombre = "X";
		
		try{
			String comando = "SELECT NOMBRE_NIVEL FROM ENOC.CAT_NIVEL WHERE NIVEL_ID = ?";
			
			Object[] parametros = new Object[] {nivelId};
			nombre = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatNivelDao|getNivelNombre|:"+ex);
		}
		
		return nombre;
	}

	public HashMap<String,String> mapaNivel () {
		List<CatNivel> lista	= new ArrayList<CatNivel>();
		HashMap<String,String> mapa = new HashMap<String,String>();
		
		try{
			String comando = "SELECT NIVEL_ID,NOMBRE_NIVEL FROM ENOC.CAT_NIVEL";
			
			lista = salomonJdbc.query(comando, new CatNivelMapper());
			
			for(CatNivel nivel : lista) {
				mapa.put(nivel.getNivelId(), nivel.getNombreNivel());
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatNivelDao|mapaNivel|:"+ex);
		}
		
		return mapa;
	}

}
