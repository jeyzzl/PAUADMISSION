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
public class CatRegionDao{
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg( CatRegion region ) {
		
 		boolean ok = false;
 		try{ 			
 			String comando = "INSERT INTO ENOC.CAT_REGION"+ 
 				" (REGION_ID, CULTURAL_ID, NOMBRE_REGION)"+
 				" VALUES(?, ?, ?)";
 			Object[] parametros = new Object[] {
 					region.getRegionId(), region.getCulturalId(), region.getNombreRegion()
 		 	};	
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
 			
 		}catch(Exception ex){
 			System.out.println("Error - adm.catalogo.spring.CatRegionDao|insertReg|:"+ex);	
 		}
 		return ok;
 	}
	
 	public boolean updateReg( CatRegion region ) {
 		boolean ok = false;
 		
 		try{
 			String comando = "UPDATE ENOC.CAT_REGION "+ 
 				" SET "+
 				" NOMBRE_REGION = ? "+
 				" WHERE REGION_ID = ? ";
 			Object[] parametros = new Object[] {
 					region.getNombreRegion(), region.getRegionId()
 		 	};		
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
 		}catch(Exception ex){
 			System.out.println("Error - adm.catalogo.spring.CatRegionDao|updateReg|:"+ex);		
 		} 		
 		return ok;
 	}
 	
 	public boolean deleteReg( String regionId, String culturalId ) {
 		boolean ok = false;
 		
 		try{
 			String comando = "DELETE FROM ENOC.CAT_REGION WHERE REGION_ID = TO_NUMBER(?,'999') AND CULTURAL_ID = TO_NUMBER(?,'999')";
 			Object[] parametros = new Object[] {regionId, culturalId};
 			if (salomonJdbc.update(comando, parametros)==1){
 				ok = true;
 			} 			
 		}catch(Exception ex){
 			System.out.println("Error - adm.catalogo.spring.CatRegionDao|deleteReg|:"+ex);			
 		}
 		return ok;
 	}
 	
 	public CatRegion mapeaRegId(  String regionId, String culturalId ){
 		
 		CatRegion region 	= new CatRegion(); 		
 		try{
	 		String comando = "SELECT COUNT(*) FROM ENOC.CAT_REGION WHERE REGION_ID = TO_NUMBER(?,'999') AND CULTURAL_ID = TO_NUMBER(?,'999')";
	 		Object[] parametros = new Object[] {regionId, culturalId };
	 		if(salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1) {
	 			comando = "SELECT REGION_ID, CULTURAL_ID, NOMBRE_REGION FROM ENOC.CAT_REGION WHERE REGION_ID = TO_NUMBER(?, '999') AND CULTURAL_ID = TO_NUMBER(?, '999')";
	 			region = salomonJdbc.queryForObject(comando, new CatRegionMapper(), parametros);
	 		}	 		
 		}catch(Exception ex){
 			System.out.println("Error - adm.catalogo.spring.CatRegionDao|mapeaRegId|:"+ex);
 		} 		
 		return region;
 	}
 	
 	public boolean existeReg( String regionId, String culturalId ) {
 		boolean 		ok 	= false;
 		try{
 			String comando = "SELECT COUNT(*) FROM ENOC.CAT_REGION WHERE REGION_ID = TO_NUMBER(?,'999') AND CULTURAL_ID = TO_NUMBER(?,'999')";
 			Object[] parametros = new Object[] {regionId, culturalId};
 			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1){
				ok = true;
			} 			
 		}catch(Exception ex){
 			System.out.println("Error - adm.cultural.spring.CatRegionDao|existeReg|:"+ex);
 		}
 		return ok;
 	}
 	
	public String maximoReg( String culturalId) {
		
		String maximo			= "1";
		
		try{
			String comando = "SELECT MAX(REGION_ID)+1 AS MAXIMO FROM ENOC.CAT_REGION WHERE CULTURAL_ID = TO_NUMBER(?,'999')";
			Object[] parametros = new Object[] {culturalId};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1){
				maximo = salomonJdbc.queryForObject(comando, String.class, parametros);
			}		
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatRegionDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}
 	
	public HashMap<String,String> mapaTotalRegiones(){		
		HashMap<String,String> mapa 	= new HashMap<String,String>();
		List<adm.Mapa> lista			= new ArrayList<adm.Mapa>();		
		try{
			String comando = "SELECT CULTURAL_ID AS LLAVE, COUNT(REGION_ID) AS VALOR FROM ENOC.CAT_REGION GROUP BY CULTURAL_ID";
			lista = salomonJdbc.query(comando, new adm.MapaMapper());	
			for ( adm.Mapa objeto : lista){
				 mapa.put(objeto.getLlave(), objeto.getValor());
			}				
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatRegionDao|mapaTotalEstados|:"+ex);
		}		
		return mapa;
	}
	
	public List<CatRegion> getLista( String culturalId, String orden ) {
		
		List<CatRegion> lista	= new ArrayList<CatRegion>();	
		
		try{
			String comando = "SELECT REGION_ID, CULTURAL_ID, NOMBRE_REGION FROM ENOC.CAT_REGION WHERE CULTURAL_ID = TO_NUMBER(?,'999') "+ orden;
			Object[] parametros = new Object[] {culturalId};
			lista = salomonJdbc.query(comando, new CatRegionMapper(), parametros);			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatRegionDao|getLista|:"+ex);
		}		
		
		return lista;
	}
	
	public String getNombreRegion( String culturalId, String regionId) {
		
		String nombre			= "EMPTY";
		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_REGION WHERE CULTURAL_ID = TO_NUMBER(?,'999') AND REGION_ID = TO_NUMBER(?,'999')";		
			Object[] parametros = new Object[] {culturalId, regionId};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1) {
				comando = "SELECT NOMBRE_REGION FROM ENOC.CAT_REGION WHERE CULTURAL_ID = TO_NUMBER(?,'999') AND REGION_ID = TO_NUMBER(?,'999')";	
				nombre = salomonJdbc.queryForObject(comando, String.class, parametros);
			}	
		}catch(Exception ex){		
			System.out.println("Error - adm.catalogo.spring.CatRegionDao|getNombreRegion|:"+ex);
		}
		
		return nombre;
	}
	
}