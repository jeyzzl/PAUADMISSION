package adm.examen.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmReservadaDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg( AdmReservada objeto){
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_RESERVADA(FOLIO, EVENTO_ID, DIA, PERIODO_ID, FECHA, ESTADO)"
					+ " VALUES(TO_NUMBER(?,'9999999'), TO_NUMBER(?,'99999'),TO_NUMBER(?,'9'),TO_NUMBER(?,'99'),SYSDATE,?)";
			Object[] parametros = new Object[] {objeto.getFolio(), objeto.getEventoId(), objeto.getDia(),objeto.getPeriodoId(), objeto.getEstado()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|insertReg|:"+ex);			
		}		
		return ok;
	}	
	
	public boolean updateReg( AdmReservada objeto ) {
		boolean ok = false;		
		try{
			String comando = "UPDATE SALOMON.ADM_RESERVADA SET FECHA = SYSDATE, ESTADO = ?"
				+ " WHERE FOLIO = TO_NUMBER(?,'9999999') AND EVENTO_ID = TO_NUMBER(?,'99999') AND PERIODO_ID = TO_NUMBER(?,'99')";
			Object[] parametros = new Object[] { objeto.getEstado(), objeto.getFolio(), objeto.getEventoId(), objeto.getPeriodoId() };			
			if (salomonJdbc.update(comando,parametros)==1){			
				ok = true;
			}	
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|updateReg|:"+ex);		
		}
		
		return ok;
	}	
	
	public boolean deleteReg( String folio, String eventoId, String periodoId ) {
		boolean ok = false;		
		try{
			String comando = "DELETE FROM SALOMON.ADM_RESERVADA WHERE FOLIO = TO_NUMBER(?,'9999999') AND EVENTO_ID = TO_NUMBER(?,'99999') AND PERIODO_ID = TO_NUMBER(?,'99')";
			Object[] parametros = new Object[] {folio, eventoId, periodoId};
			if (salomonJdbc.update(comando,parametros)==1){			
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.carga.dao.CatActividadDao|deleteReg|:"+ex);			
		}
		
		return ok;
	}
	
	public AdmReservada mapeaRegId( String folio, String eventoId, String periodoId ) {
		AdmReservada objeto = new AdmReservada();		
		try{
			String comando = "SELECT FOLIO, DIA, PERIODO_ID, FECHA, ESTADO FROM SALOMON.ADM_RESERVADA"
					+ " WHERE FOLIO = TO_NUMBER(?,'9999999') AND EVENTO_ID = TO_NUMBER(?,'99999') AND PERIODO_ID = TO_NUMBER(?,'99')";
			Object[] parametros = new Object[] {folio, eventoId, periodoId };
			objeto = salomonJdbc.queryForObject(comando, new AdmReservadaMapper(), parametros);			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg( String folio, String eventoId, String periodoId ) {
		boolean 		ok 	= false;		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_RESERVADA WHERE FOLIO = TO_NUMBER(?,'9999999') AND EVENTO_ID = TO_NUMBER(?,'99999') AND PERIODO_ID = TO_NUMBER(?,'99')";
			Object[] parametros = new Object[] {folio, eventoId, periodoId};	
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){	
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|existeReg|:"+ex);
		}		
		return ok;
	}
	
	public String getEventoReservado( String folio ) {		
		int eventoId		= 0;	
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_RESERVADA WHERE FOLIO = TO_NUMBER(?,'9999999')"; 
			Object[] parametros = new Object[] {folio};	
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				comando = "SELECT EVENTO_ID FROM SALOMON.ADM_RESERVADA WHERE FOLIO = TO_NUMBER(?,'9999999')";
				eventoId = salomonJdbc.queryForObject(comando,Integer.class, parametros);				
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|getEventoReservado|:"+ex);
		}
		
		return String.valueOf(eventoId);
	}
	
	public boolean existeReservacion( String folio, String estados ) {
		boolean 		ok 	= false;			
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_RESERVADA WHERE FOLIO = TO_NUMBER(?,'9999999') AND ESTADO IN ("+estados+")";			
			Object[] parametros = new Object[] {folio};	
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){	
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|existeReservacion|:"+ex);
		}
		
		return ok;
	}
	
	public boolean existeReservacion( String folio, String eventoId,  String estados ) {
		boolean 		ok 	= false;		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_RESERVADA WHERE FOLIO = TO_NUMBER(?,'9999999') AND EVENTO_ID = TO_NUMBER(?,'99999') AND ESTADO IN ("+estados+")";		
			Object[] parametros = new Object[] {folio, eventoId};	
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){	
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|existeReservacion|:"+ex);
		}
		
		return ok;
	}
	
	public boolean existeReservacion( String folio, String eventoId, String periodo,  String estado ) {
		boolean 		ok 	= false;		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_RESERVADA WHERE FOLIO = TO_NUMBER(?,'9999999') AND EVENTO_ID = TO_NUMBER(?,'99999') AND ESTADO IN (?)";
			Object[] parametros = new Object[] {folio, eventoId, periodo, estado};	
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){	
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|existeReservacion|:"+ex);
		}
		
		return ok;
	}
	
	public String getEstado( String folio ) {
		String estado = "-";		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_RESERVADA WHERE FOLIO = TO_NUMBER(?,'9999999')";			
			Object[] parametros = new Object[] {folio};	
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				comando = "SELECT ESTADO FROM SALOMON.ADM_RESERVADA WHERE FOLIO = TO_NUMBER(?,'9999999')";
				estado = salomonJdbc.queryForObject(comando,String.class, parametros);
			}
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|existeReservacion|:"+ex);
		}
		
		return estado;
	}
	
	public int getOcupados( String eventoId, String dia, String periodoId, String estado ) {
		int ocupados = 0;			
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_RESERVADA"
					+ " WHERE EVENTO_ID = TO_NUMBER(?,'99999')"
					+ " AND DIA = TO_NUMBER(?,'9')"
					+ " AND PERIODO_ID = TO_NUMBER(?,'99')"
					+ " AND ESTADO = ?"; 
			Object[] parametros = new Object[] {eventoId, dia, periodoId, estado};	
			ocupados = salomonJdbc.queryForObject(comando,Integer.class, parametros);
		}catch(Exception ex){
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|ocupados|:"+ex);
		}
		
		return ocupados;
	}
	
	public List<AdmReservada> getListAll (){
		List<AdmReservada> lista = new ArrayList<AdmReservada>();
		
		try {
			String comando = "SELECT FOLIO, EVENTO_ID, DIA, PERIODO_ID, FECHA, ESTADO FROM SALOMON.ADM_RESERVADA";
			
			lista = salomonJdbc.query(comando, new AdmReservadaMapper());
		}catch(Exception ex) {
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|getListAll|:"+ex);
		}
		return lista;
	}
	
	public HashMap<String, String> mapaOcupados (String estado){
		HashMap<String, String> mapa = new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();
		
		try {
			String comando = "SELECT EVENTO_ID||DIA||PERIODO_ID AS LLAVE, COUNT(FOLIO) AS VALOR"
					+ " FROM SALOMON.ADM_RESERVADA"
					+ " WHERE ESTADO = ?"
					+ " GROUP BY EVENTO_ID||DIA||PERIODO_ID";	
			Object[] parametros = new Object[] {estado};
			lista = salomonJdbc.query(comando, new adm.MapaMapper(), parametros);
			for (adm.Mapa map : lista ){
				mapa.put(map.getLlave(), map.getValor());			
			}
		}catch(Exception ex) {
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|mapaOcupados|:"+ex);
		}
		return mapa;
	}
	
	public HashMap<String, String> mapaAlumnoReservado (){
		HashMap<String, String> mapa = new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();
		
		try {
			String comando = "SELECT EVENTO_ID||DIA||PERIODO_ID||FOLIO AS LLAVE, ESTADO AS VALOR"
					+ " FROM SALOMON.ADM_RESERVADA";			
			lista = salomonJdbc.query(comando, new adm.MapaMapper());
			for (adm.Mapa map : lista ){
				mapa.put(map.getLlave(), map.getValor());	
			}
		}catch(Exception ex) {
			System.out.println("Error - adm.examen.spring.AdmReservadaDao|mapaAlumnoReservado|:"+ex);
		}
		return mapa;
	}
}
