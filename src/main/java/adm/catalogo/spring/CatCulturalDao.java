package adm.catalogo.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatCulturalDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg( CatCultural cultural ) {
		
		boolean ok = false;
		try{ 			
			String comando = "INSERT INTO ENOC.CAT_CULTURAL"+ 
				" (CULTURAL_ID, NOMBRE_CULTURAL, PRINCIPAL)"+
				" VALUES(?, ?, ?)";
			Object[] parametros = new Object[] {
					cultural.getCulturalId(), cultural.getNombreCultural(), cultural.getPrincipal()
			 };	
		   if (salomonJdbc.update(comando,parametros)==1){
			   ok = true;
		   }			
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCulturalDao|insertReg|:"+ex);	
		}
		return ok;
	}
   
	public boolean updateReg( CatCultural cultural ) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE ENOC.CAT_CULTURAL "+ 
				" SET "+				
				" NOMBRE_CULTURAL = ?,"+
				" PRINCIPAL = ?"+
				" WHERE CULTURAL_ID = ? ";
			Object[] parametros = new Object[] {
					cultural.getNombreCultural(), cultural.getPrincipal(), cultural.getCulturalId()
			 };		
		   if (salomonJdbc.update(comando,parametros)==1){
			   ok = true;
		   }			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCulturalDao|updateReg|:"+ex);		
		} 		
		return ok;
	}
	
	public boolean deleteReg( String culturalId ) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM ENOC.CAT_CULTURAL WHERE CULTURAL_ID = ?";
			Object[] parametros = new Object[] {culturalId};
			if (salomonJdbc.update(comando, parametros)==1){
				ok = true;
			} 			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCatalogoDao|deleteReg|:"+ex);			
		}
		return ok;
	}
	
	public CatCultural mapeaRegId(  String culturalId ){
		
		CatCultural cultural 	= new CatCultural(); 
		
		try{
			String comando = "SELECT COUNT(CULTURAL_ID) FROM ENOC.CAT_CULTURAL WHERE CULTURAL_ID = ?";
			if (salomonJdbc.queryForObject(comando, Integer.class, culturalId) >= 1) {
				comando = "SELECT CULTURAL_ID, NOMBRE_CULTURAL, PRINCIPAL FROM ENOC.CAT_CULTURAL WHERE CULTURAL_ID = ?";
				cultural = salomonJdbc.queryForObject(comando, new CatCulturalMapper(), culturalId);
			}	 		
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCulturalDao|mapeaRegId|:"+ex);
		} 	
		
		return cultural;
	}
	
	public boolean existeReg( String culturalId) {
		boolean 		ok 	= false;
		try{
			String comando = "SELECT COUNT(CULTURAL_ID) FROM ENOC.CAT_CULTURAL WHERE CULTURAL_ID = ?";
			Object[] parametros = new Object[] {culturalId};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1){
			   ok = true;
		   } 			
		}catch(Exception ex){
			System.out.println("Error - adm.cultural.spring.CatCulturalDao|existeReg|:"+ex);
		}
		return ok;
	}
	
   public String maximoReg() {
	   String maximo = "1";		
	   try{
		   String comando = "SELECT COALESCE(MAX(CULTURAL_ID)+1,1) FROM ENOC.CAT_CULTURAL";
		   maximo = salomonJdbc.queryForObject(comando, String.class);			
	   }catch(Exception ex){
		   System.out.println("Error - adm.catalogo.spring.CatCulturalDao|maximoReg|:"+ex);
	   }
	   
	   return maximo;
   }
	
   public List<CatCultural> getListAll( String orden ) {
	   
	   List<CatCultural> lista = new ArrayList<CatCultural>();
	   
	   try{
		   String comando = "SELECT CULTURAL_ID, NOMBRE_CULTURAL, PRINCIPAL FROM ENOC.CAT_CULTURAL "+ orden;
		   lista = salomonJdbc.query(comando, new CatCulturalMapper());
	   }catch(Exception ex){
		   System.out.println("Error - adm.catalogo.spring.CatCulturalDao|getListAll|:"+ex);
	   }
	   
	   return lista;
   }
   
   public String getNombreCultural( String culturalId) {
	   
	   String nombre			= "EMPTY";
	   
	   try{
		   String comando = "SELECT COUNT(*) FROM ENOC.CAT_CULTURAL WHERE CULTURAL_ID = TO_NUMBER(?,'999')";		
		   Object[] parametros = new Object[] {culturalId};
		   if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1) {
			   comando = "SELECT NOMBRE_CULTURAL FROM ENOC.CAT_CULTURAL WHERE CULTURAL_ID = TO_NUMBER(?,'999')";	
			   nombre = salomonJdbc.queryForObject(comando, String.class, parametros);
		   }	
	   }catch(Exception ex){		
		   System.out.println("Error - adm.catalogo.spring.CatCulturalDao|getNombreCultural|:"+ex);
	   }
	   
	   return nombre;
   }
}