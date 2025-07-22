package adm.examen.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmEventoDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg( AdmEvento objeto){
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_EVENTO(EVENTO_ID, ESTADO, EVENTO_NOMBRE, LUGAR, ORDEN)"
					+ " VALUES(TO_NUMBER(?,'99999'),?,?,?,TO_NUMBER(?,'999.99'))";
			Object[] parametros = new Object[] {objeto.getEventoId(), objeto.getEstado(),objeto.getEventoNombre(), objeto.getLugar(), objeto.getOrden()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmEventoDao|insertReg|:"+ex);			
		}
		
		return ok;
	}	
	
	public boolean updateReg( AdmEvento objeto ) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_EVENTO SET EVENTO_NOMBRE = TO_NUMBER(?,'99999'), ESTADO = ?, LUGAR = ? WHERE EVENTO_ID = TO_NUMBER(?,'99999') ";
			Object[] parametros = new Object[] {objeto.getEventoNombre(), objeto.getEstado(),objeto.getLugar(),objeto.getOrden(),objeto.getEventoId()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmEventoDao|updateReg|:"+ex);		
		}
		
		return ok;
	}	
	
	public boolean deleteReg( String eventoId) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_EVENTO WHERE EVENTO_ID = ?";
			Object[] parametros = new Object[] {eventoId};
			if (salomonJdbc.update(comando,parametros)==1){			
				ok = true;
			}	
			
		}catch(Exception ex){
			System.out.println("Error - aca.carga.dao.CatActividadDao|deleteReg|:"+ex);			
		}
		
		return ok;
	}
	
	public AdmEvento mapeaRegId( String actividadId) {
		AdmEvento objeto = new AdmEvento();
		
		try{
			String comando = "SELECT EVENTO_ID, ESTADO, EVENTO_NOMBRE, LUGAR, ORDEN, FECHA FROM SALOMON.CAT_ACTIVIDAD WHERE EVENTO_ID = TO_NUMBER(?,'99999')";		 
			Object[] parametros = new Object[] { actividadId };
			objeto = salomonJdbc.queryForObject(comando, new AdmEventoMapper(), parametros);		
			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmEventoDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg( String eventoId ) {
		boolean 		ok 	= false;			
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_EVENTO WHERE EVENTO_ID = TO_NUMBER(?,'99999')"; 
			Object[] parametros = new Object[] {eventoId};	
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){	
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmEventoDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String getEventoNombre( String eventoId ) {
		String nombre = "-";			
		
		try{
			String comando = "SELECT COALESCE(EVENTO_NOMBRE,'-') FROM SALOMON.ADM_EVENTO WHERE EVENTO_ID = TO_NUMBER(?,'99999')"; 
			Object[] parametros = new Object[] {eventoId};	
			nombre = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmEventoDao|getEventoNombre|:"+ex);
		}
		
		return nombre;
	}
	
	public String getEstado( String eventoId ) {
		String estado = "A";			
		
		try{
			String comando = "SELECT COALESCE(ESTADO,'-') FROM SALOMON.ADM_EVENTO WHERE EVENTO_ID = TO_NUMBER(?,'99999')"; 
			Object[] parametros = new Object[] {eventoId};	
			estado = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmEventoDao|getEstado|:"+ex);
		}
		
		return estado;
	}
	
	public List<AdmEvento> getListAll (){
		List<AdmEvento> lista = new ArrayList<AdmEvento>();
		
		try {
			String comando = "SELECT EVENTO_ID, ESTADO, EVENTO_NOMBRE, LUGAR, ORDEN, FECHA FROM SALOMON.ADM_EVENTO";
			
			lista = salomonJdbc.query(comando, new AdmEventoMapper());
		}catch(Exception ex) {
			System.out.println("Error - adm.examen.spring.AdmEventoDao|getListAll|:"+ex);
		}
		return lista;
	}	
	
	public List<AdmEvento> lisActivos(String estados, String orden){
		List<AdmEvento> lista = new ArrayList<AdmEvento>();
		
		try {
			String comando = "SELECT EVENTO_ID, ESTADO, EVENTO_NOMBRE, LUGAR, ORDEN, FECHA FROM SALOMON.ADM_EVENTO WHERE ESTADO IN ("+estados+") "+orden;
			
			lista = salomonJdbc.query(comando, new AdmEventoMapper());
		}catch(Exception ex) {
			System.out.println("Error - adm.examen.spring.AdmEventoDao|lisActivos|:"+ex);
		}
		return lista;
	}
}
