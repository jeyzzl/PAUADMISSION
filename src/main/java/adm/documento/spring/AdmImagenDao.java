package adm.documento.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmImagenDao {
	
	@Autowired
	@Qualifier("jdbcAdm")
	private JdbcTemplate admJdbc;
	
	public boolean insertReg(AdmImagen admImagen) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO ADM_IMAGEN(FOLIO, DOCUMENTO_ID, HOJA, IMAGEN, FECHA)"
					+ " VALUES(TO_NUMBER(?,'9999999'), TO_NUMBER(?,'99'), TO_NUMBER(?,'99'), ?, TO_DATE(?,'DD/MM/YYYY'))";
			
			Object[] parametros = new Object[] {
				admImagen.getFolio(),admImagen.getDocumentoId(),admImagen.getHoja(),admImagen.getImagen(), admImagen.getFecha()
			};
			if (admJdbc.update(comando,parametros)==1){
				ok = true;
			}	
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmImagenDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(AdmImagen admImagen) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE ADM_IMAGEN"
					+ " SET IMAGEN = ?"
					+ " WHERE FOLIO = TO_NUMBER(?,'9999999')"
					+ " AND DOCUMENTO_ID = TO_NUMBER(?,'99')"
					+ " AND HOJA = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {
				admImagen.getImagen(),admImagen.getFolio(),admImagen.getDocumentoId(),admImagen.getHoja()
			};
			if (admJdbc.update(comando,parametros)==1){
				ok = true;
			}	

		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmImagenDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio, String documentoId, String hoja) {
		boolean ok = false;	
		try{
			String comando = "DELETE FROM ADM_IMAGEN"
					+ " WHERE FOLIO = TO_NUMBER(?,'9999999')"
					+ " AND DOCUMENTO_ID = TO_NUMBER(?,'99')"
					+ " AND HOJA = TO_NUMBER(?,'99')";			
			Object[] parametros = new Object[]{folio,documentoId,hoja};
			if (admJdbc.update(comando,parametros) == 1){
				ok = true;
			}				
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmImagenDao|borrar - deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmImagen mapeaRegId(String folio, String documentoId, String hoja) {
		AdmImagen objeto = new AdmImagen();
		
		try {
			String comando = "SELECT FOLIO, DOCUMENTO_ID, HOJA, IMAGEN, FECHA"
					+ " FROM ADM_IMAGEN"
					+ " WHERE FOLIO = TO_NUMBER(?,'9999999')"
					+ " AND DOCUMENTO_ID = TO_NUMBER(?, '99')"
					+ " AND HOJA = TO_NUMBER(?, '99')";
		
			Object[] parametros = new Object[] {folio,documentoId,hoja};
			objeto = admJdbc.queryForObject(comando, new AdmImagenMapper(), parametros);
			
		} catch (Exception ex) {
			System.out.println("Error - adm.documento.spring.AdmImagenDao|mapeaRegId|:"+ex);
		}
		
		return objeto;	
	}
	
	public boolean existeReg(String folio, String documentoId, String hoja) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(FOLIO) FROM ADM_IMAGEN"
					+ " WHERE FOLIO = TO_NUMBER(?,'9999999')"
					+ " AND DOCUMENTO_ID = TO_NUMBER(?, '99')"
					+ " AND HOJA = TO_NUMBER(?, '99')";
			
			Object[] parametros = new Object[] {folio,documentoId,hoja};
			if (admJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmImagenDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public boolean existeDocumentos(String folio, String documentoId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(FOLIO) FROM ADM_IMAGEN"
					+ " WHERE FOLIO = TO_NUMBER(?,'9999999')"
					+ " AND DOCUMENTO_ID = TO_NUMBER(?, '99')";
			
			Object[] parametros = new Object[] {folio,documentoId};
			if (admJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmImagenDao|existeDocumentos|:"+ex);
		}
		
		return ok;
	}
	
	public boolean existeReg(String folio, String documentoId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(FOLIO) FROM ADM_IMAGEN"
					+ " WHERE FOLIO = TO_NUMBER(?,'9999999')"
					+ " AND DOCUMENTO_ID = TO_NUMBER(?, '99')";
			
			Object[] parametros = new Object[] {folio,documentoId};
			if (admJdbc.queryForObject(comando,Integer.class, parametros) >= 1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmImagenDao|existeReg|:"+ex);
		}

		return ok;
	}
	
	public boolean tieneImagenes(String folio, String documentoId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(FOLIO) FROM ADM_IMAGEN"
					+ " WHERE FOLIO = TO_NUMBER(?,'9999999')"
					+ " AND DOCUMENTO_ID = TO_NUMBER(?, '99')"
					+ " AND IMAGEN IS NOT NULL";
			
			Object[] parametros = new Object[] {folio,documentoId};
			if (admJdbc.queryForObject(comando,Integer.class, parametros) >= 1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmImagenDao|existeReg|:"+ex);
		}

		return ok;
	}
	
	public HashMap<String,String> mapImagen(String folio) {
		
		HashMap<String,String> mapa = new HashMap<String,String>();
		List<adm.Mapa> lista		= new ArrayList<adm.Mapa>();
		
		try{
			String comando = "SELECT TRIM(TO_CHAR(FOLIO,'9999999'))||TRIM(TO_CHAR(DOCUMENTO_ID,'99'))||TRIM(TO_CHAR(HOJA,'99')) AS LLAVE, HOJA AS VALOR"
					+ " FROM ADM_IMAGEN "
					+ " WHERE FOLIO = TO_NUMBER(?,'9999999')"
					+ " GROUP BY FOLIO,DOCUMENTO_ID,HOJA";
			Object[] parametros = new Object[] {folio};
			lista = admJdbc.query(comando, new adm.MapaMapper(), parametros);
			for (adm.Mapa map : lista ) {
				mapa.put(map.getLlave(), map.getValor());
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmImagenDao|mapImagen|:"+ex);
		}
		
		return mapa;
	}

}
