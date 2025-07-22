// Clase Util para la tabla de Cat_Division
package adm.catalogo.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatInstitucionDao{
		
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg( CatInstitucion institucion ) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO ENOC.CAT_INSTITUCION(INSTITUCION_ID, NOMBRE_INSTITUCION)"
					+ " VALUES( TO_NUMBER(?,'999'), ?) ";
			Object[] parametros = new Object[] {institucion.getInstitucionId(),institucion.getNombreInstitucion()};		
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatInstitucionDao|insertReg|:"+ex);			
		}
		
		return ok;
	}	
	
	public boolean updateReg( CatInstitucion institucion ) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE ENOC.CAT_INSTITUCION "+ 
				"SET NOMBRE_INSTITUCION = ? "+				
				"WHERE INSTITUCION_ID = TO_NUMBER(?,'999')";
			Object[] parametros = new Object[] {institucion.getNombreInstitucion(), institucion.getInstitucionId()};		
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatInstitucionDao|updateReg|:"+ex);		
		}
		
		return ok;
	}
	
	public boolean deleteReg( String institucionId ) {
		boolean ok = false;		
		try{
			String comando = "DELETE FROM ENOC.CAT_INSTITUCION WHERE INSTITUCION_ID = TO_NUMBER(?,'999')";
			Object[] parametros = new Object[] {institucionId};		
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}		
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatInstitucionDao|deleteReg|:"+ex);			
		}
		
		return ok;
	}
	
	public CatInstitucion mapeaRegId(  String institucionId) {
		
		CatInstitucion institucion 		= new CatInstitucion();
		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_INSTITUCION WHERE INSTITUCION_ID = TO_NUMBER(?,'999')"; 
			Object[] parametros = new Object[] {institucionId};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1) {
				comando = "SELECT INSTITUCION_ID, NOMBRE_INSTITUCION FROM ENOC.CAT_INSTITUCION WHERE INSTITUCION_ID = TO_NUMBER(?,'999')";
				institucion = salomonJdbc.queryForObject(comando, new CatInstitucionMapper(), parametros);
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatInstitucionDao|mapeaRegId|:"+ex);
			ex.printStackTrace();
		}
		
		return institucion;
	}
	
	public boolean existeReg( String institucionId) {
		boolean 		ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_INSTITUCION WHERE INSTITUCION_ID = TO_NUMBER(?,'999') ";
			Object[] parametros = new Object[] {institucionId};			
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatInstitucionDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoReg() {
		String maximo = "1";		
		try{
			String comando = "SELECT COALESCE(MAX(INSTITUCION_ID)+1,1) FROM ENOC.CAT_INSTITUCION";						
			maximo = salomonJdbc.queryForObject(comando, String.class);			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatInstitucionDao|existeReg|:"+ex);
		}		
		return maximo;
	}
	
	public String getNombreInstitucion( String institucionId ) {		
		String nombre	= "-";		
		try{			
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_INSTITUCION WHERE INSTITUCION_ID = TO_NUMBER(?,'999')"; 
			Object[] parametros = new Object[] {institucionId};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1) {
				comando = "SELECT NOMBRE_INSTITUCION FROM ENOC.CAT_INSTITUCION WHERE INSTITUCION_ID = TO_NUMBER(?,'999')";
				nombre = salomonJdbc.queryForObject(comando, String.class, parametros);
			}
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatInstitucionDao|getNombreInstitucion|:"+ex);
		}
		
		return nombre;
	}
	
	public String getNombreInstitucionFinanzas( String institucionId ) {		
		String nombre	= "-";		
		try{			
			String comando = "SELECT COUNT(*) FROM NOE.CAT_INSTITUCION WHERE ID = TO_NUMBER(?,'999')"; 
			Object[] parametros = new Object[] {institucionId};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1) {
				comando = "SELECT NOMBRE FROM NOE.CAT_INSTITUCION WHERE ID = TO_NUMBER(?,'999')";
				nombre = salomonJdbc.queryForObject(comando, String.class, parametros);
			}
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatInstitucionDao|getNombreInstitucionFinanzas|:"+ex);
		}
		
		return nombre;
	}
	
	public String nombreInstitucion( String institucionId) {
		String 	nombreInstitucion 	= "-";
		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_INSTITUCION WHERE INSTITUCION_ID = TO_NUMBER(?,'999') ";
			Object[] parametros = new Object[] {institucionId};			
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1){
				nombreInstitucion = salomonJdbc.queryForObject(comando, String.class, parametros);
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatInstitucionDao|existeReg|:"+ex);
		}
		
		return nombreInstitucion;
	}
	
	public List<CatInstitucion> getListAll( String orden ) {
		
		List<CatInstitucion> lista	= new ArrayList<CatInstitucion>();
		
		try{
			String comando = "SELECT INSTITUCION_ID, NOMBRE_INSTITUCION"
					+ " FROM ENOC.CAT_INSTITUCION "+ orden;
			lista = salomonJdbc.query(comando, new CatInstitucionMapper());
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatInstitucionDao|getListAll|:"+ex);
		}
		
		return lista;
	}
		
	public HashMap<String,CatInstitucion> getMapAll( String orden ) {
		
		HashMap<String,CatInstitucion> mapa = new HashMap<String,CatInstitucion>();
		List<CatInstitucion> lista	= new ArrayList<CatInstitucion>();
		
		try{
			String comando = "SELECT INSTITUCION_ID, NOMBRE_INSTITUCION"
					+ " FROM ENOC.CAT_INSTITUCION "+ orden;	
			lista = salomonJdbc.query(comando, new CatInstitucionMapper());
			for (CatInstitucion institucion : lista){
				mapa.put(institucion.getInstitucionId(), institucion);
			}
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatInstitucionDao|getMapAll|:"+ex);
		}
		
		return mapa;
	}
}