package adm.catalogo.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatPaisDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(CatPais catPais){
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO ENOC.CAT_PAIS(PAIS_ID, NOMBRE_PAIS, NACIONALIDAD)"
					+ " VALUES( TO_NUMBER (?,'999'), ?, ? )";			
			Object[] parametros = new Object[] {
				catPais.getPaisId(),catPais.getNombrePais(),catPais.getNacionalidad()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatPaisDao|insertReg|:"+ex);			
		}
		
		return ok;
	}	

	public boolean updateReg(CatPais catPais) {
		boolean ok = false;		
		try{
			String comando = "UPDATE ENOC.CAT_PAIS SET NOMBRE_PAIS = ?, NACIONALIDAD = ? WHERE PAIS_ID = TO_NUMBER(?,'999')";			
			Object[] parametros = new Object[] {
				catPais.getNombrePais(),catPais.getNacionalidad(),catPais.getPaisId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatPaisDao|updateReg|:"+ex);		
		}				
		return ok;
	}

	
	public boolean deleteReg(String paisId) {
		boolean ok = false;		
		try{
			String comando = "DELETE FROM ENOC.CAT_PAIS WHERE PAIS_ID = TO_NUMBER(?,'999')";
			Object[] parametros = new Object[] {paisId};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatPaisDao|deleteReg|:"+ex);			
		}
		return ok;
	}

	public CatPais mapeaRegId(String paisId) {
		CatPais objeto = new CatPais();		
		try {
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_PAIS WHERE PAIS_ID = TO_NUMBER(?,'999')";			
			Object[] parametros = new Object[] {paisId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros) >= 1){
				comando = "SELECT PAIS_ID, NOMBRE_PAIS, NACIONALIDAD FROM ENOC.CAT_PAIS WHERE PAIS_ID = TO_NUMBER(?,'999')";			
				objeto = salomonJdbc.queryForObject(comando, new CatPaisMapper(), parametros);
			}			
		} catch (Exception ex) {
			System.out.println("Error - aca.catalogo.spring.CatPaisDao|mapeaRegId|:"+ex);	
		}		
		return objeto;	
	}
	
	public boolean existeReg(String paisId) {
		boolean ok 	= false;		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_PAIS WHERE PAIS_ID = TO_NUMBER(?,'999')";			
			Object[] parametros = new Object[] {paisId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatPaisDao|existeReg|:"+ex);
		}		
		return ok;
	}
	
	public String maximoReg() {
		String maximo = "1";		
		try{
			String comando = "SELECT MAX(PAIS_ID)+1 MAXIMO FROM ENOC.CAT_PAIS";			
			maximo = salomonJdbc.queryForObject(comando,String.class);			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatPaisDao|maximoReg|:"+ex);
		}		
		return maximo;
	}
	
	public String getNombrePais(String paisId ) {
		String nombre = "vacio";
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_PAIS WHERE PAIS_ID = TO_NUMBER(?,'999')";	
			Object[] parametros = new Object[] { paisId };
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros) >= 1){
				comando = "SELECT NOMBRE_PAIS FROM ENOC.CAT_PAIS WHERE PAIS_ID = ?";			
				nombre = salomonJdbc.queryForObject(comando,String.class, parametros);
			}						
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatPaisDao|getNombrePais|:"+ex);
		}
		
		return nombre;
	}
	
	public String getNacionalidad(String paisId) {
		String nombre	= "No encontro";		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_PAIS WHERE PAIS_ID = TO_NUMBER(?,'999')";			
			if (salomonJdbc.queryForObject(comando,Integer.class, paisId) >= 1){
				comando = "SELECT NACIONALIDAD FROM ENOC.CAT_PAIS WHERE PAIS_ID = TO_NUMBER(?,'999')";
				nombre = salomonJdbc.queryForObject(comando,String.class, paisId);
			}						
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatPaisDao|getNacionalidad|:"+ex);
		}		
		return nombre;
	}
	
	public List<CatPais> getListAll(String orden ) {
		List<CatPais> lista		= new ArrayList<CatPais>();		
		try{
			String comando = "SELECT PAIS_ID, NOMBRE_PAIS, NACIONALIDAD FROM ENOC.CAT_PAIS "+ orden;			
			lista = salomonJdbc.query(comando, new CatPaisMapper());			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatPaisDao|getListAll|:"+ex);
		}		
		return lista;
	}

	public HashMap<String,CatPais> mapaPaises() {
		HashMap<String,CatPais> mapa	= new HashMap<String,CatPais>();
		List<CatPais> lista				= new ArrayList<CatPais>();		
		try{
			String comando = "SELECT PAIS_ID, NOMBRE_PAIS, NACIONALIDAD FROM ENOC.CAT_PAIS";
			lista = salomonJdbc.query(comando, new CatPaisMapper());			
			for(CatPais pais : lista) {
				mapa.put(pais.getPaisId(), pais);
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatPaisDao|mapaPaises|:"+ex);
		}		
		return mapa;
	}
}
