package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmContactoDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmContacto admContacto) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_CONTACTO"+ 
				"(FOLIO, ORDEN, FECHA, ENVIO, MENSAJE, ESTADO) "+
				"VALUES( TO_NUMBER(?,'9999999'), ?, sysdate, ?, ?, ?)";
			
			Object[] parametros = new Object[] {
				admContacto.getFolio(),admContacto.getOrden(),admContacto.getEnvio(),admContacto.getMensaje(),admContacto.getEstado()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmContactoDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio, String orden) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_CONTACTO "+ 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') "+
					"AND ORDEN = ? ";
			
			Object[] parametros = new Object[] {folio,orden};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmContactoDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmContacto mapeaRegId(String folio, String orden) {
		AdmContacto objeto = new AdmContacto();
		
		try {
			String comando = "SELECT FOLIO, ORDEN, FECHA, MENSAJE, ESTADO "+
					"FROM SALOMON.ADM_CONTACTO "+ 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') "+
					"AND ORDEN = ? ";
			
			Object[] parametros = new Object[] {folio,orden};
			objeto = salomonJdbc.queryForObject(comando, new AdmContactoMapper(), parametros);
			
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.AdmContactoDao|mapeaRegId|:"+ex);
		}

		return objeto;
	}
	
	public boolean existeReg(String folio, String orden ) {
		boolean 		ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_CONTACTO "+ 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') "+
					"AND ORDEN = ? ";
			
			Object[] parametros = new Object[] {folio, orden};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmContactoDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoOrden(String folio) {
		String maximo	= "1";
		
		try{
			String comando = "SELECT MAX(ORDEN)+1 AS MAXIMO FROM SALOMON.ADM_CONTACTO" + 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')";
			
			Object[] parametros = new Object[] {folio};
			maximo = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmContactoDao|maximoOrden|:"+ex);
		}
		
		return maximo;
	}
	
	public String getMensaje(String folio, String orden) {
		String mensaje	= "vacio";
		
		try{
			String comando = "SELECT MENSAJE FROM SALOMON.ADM_CONTACTO " + 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') " +
					"AND ORDEN = ?";
			
			Object[] parametros = new Object[] {folio,orden};
			mensaje = salomonJdbc.queryForObject(comando,String.class, parametros);
		
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmContactoDao|getMensaje|:"+ex);
		}
		
		return mensaje;
	}

	public List<AdmContacto> getLista(String folio, String orden) {
		List<AdmContacto> lista	= new ArrayList<AdmContacto>();
		
		try{
			String comando = "SELECT FOLIO, ORDEN, ENVIO," +
					" TO_CHAR(FECHA, 'DD/MM/YYYY HH24:MI') AS FECHA, MENSAJE, ESTADO"+
					" FROM SALOMON.ADM_CONTACTO" + 
					" WHERE FOLIO = TO_NUMBER('"+folio+"','99999999') "+ orden;
			
			lista = salomonJdbc.query(comando, new AdmContactoMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmContactoDao|getLista|:"+ex);
		}
		
		return lista;
	}

}
