package adm.documento.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

@Component
public class AdmFormatoDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmFormato adm){
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_FORMATO "+
				" (FORMATO_ID, FORMATO_NOMBRE, ARCHIVO)"+
				" VALUES(TO_NUMBER(?,'99') ?, ?)";
			Object[] parametros = new Object[] {
					adm.getFormatoId(), adm.getFormatoNombre(), adm.getArchivo()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmFormatoDao|insertReg|:"+ex);

		}
		return ok;
	}
			
	public boolean updateReg(AdmFormato adm){
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_FORMATO" +
				" SET FORMATO_NOMBRE = ?, " +
				" ARCHIVO = ? "+
				" WHERE FORMATO_ID = TO_NUMBER(?,'99')";
			Object[] parametros = new Object[] {
					adm.getFormatoNombre(), adm.getArchivo(), adm.getFormatoId()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}		
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmFormatoDao|updateReg|:"+ex);
		
		}
		return ok;
	}	
	
	public boolean deleteReg(String formatoId){
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_FORMATO WHERE FORMATO_ID = TO_NUMBER(?,'99')";
			Object[] parametros = new Object[]{formatoId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros) >= 1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmFormatoDao|deleteReg|:"+ex);
			ok = false;
		}
				
		return ok;
	}
	
	public AdmFormato mapeaRegId( String formatoId ){
		AdmFormato objeto = new AdmFormato();
		
		try{
			String comando = "SELECT * FROM SALOMON.ADM_FORMATO WHERE FORMATO_ID = TO_NUMBER(?,'99')";
			Object[] parametros = new Object[] {formatoId};
			objeto = salomonJdbc.queryForObject(comando, new AdmFormatoMapper(), parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmFormatoDao|mapeaRegId|:"+ex);
			ex.printStackTrace();

		}
		
		return objeto;
	}
	
	public boolean existeReg(String formatoId){
		boolean 		ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_FORMATO "+
							" WHERE FORMATO_ID = TO_NUMBER(?,'99') ";
			Object[] parametros = new Object[] {formatoId};
				if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
					ok = true;
				}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmFormatoDao|existeReg|:"+ex);
			
		}
		
		return ok;
	}
	
	
	public List<AdmFormato> getAll(String orden){
		List<AdmFormato> lista	= new ArrayList<AdmFormato>();
		
		try{
			String comando = "SELECT FORMATO_ID, FORMATO_NOMBRE, ARCHIVO" +
			" FROM SALOMON.ADM_FORMATO "+ orden; 
			lista = salomonJdbc.query(comando, new AdmFormatoMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmFormatoDao|getAll|:"+ex);
		
		}
		return lista;
	}
	
	public HashMap<String,AdmFormato> mapFormatos() {		
		HashMap<String,AdmFormato> mapa = new HashMap<String,AdmFormato>();
		List<AdmFormato> lista		= new ArrayList<AdmFormato>();
		try{
			String comando = "SELECT FORMATO_ID, FORMATO_NOMBRE, ARCHIVO FROM SALOMON.ADM_FORMATO";								
			lista = salomonJdbc.query(comando, new AdmFormatoMapper());
			for (AdmFormato formato: lista ) {
				mapa.put(formato.getFormatoId(), formato);
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmFormatoDao|mapFormatos|:"+ex);
		}
		
		return mapa;
	}
}