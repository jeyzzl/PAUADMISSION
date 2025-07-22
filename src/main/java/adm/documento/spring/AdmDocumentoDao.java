package adm.documento.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmDocumentoDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmDocumento admDocumento) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO "+
				"SALOMON.ADM_DOCUMENTO(DOCUMENTO_ID, DOCUMENTO_NOMBRE, TIPO, COMENTARIO, ORIGINAL, ORDEN, FORMATO_ID) "+
				"VALUES( TO_NUMBER(?,'999'), ?, ?, ?, ?, TO_NUMBER(?,'99'), TO_NUMBER(?, '99'), ?)";
			
			Object[] parametros = new Object[] {
				admDocumento.getDocumentoId(),admDocumento.getDocumentoNombre(),admDocumento.getTipo(),admDocumento.getComentario(),admDocumento.getOriginal(),admDocumento.getOrden(),
				admDocumento.getFormatoId(),admDocumento.getCorto()
			};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
			
		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|insertReg|:"+ex);			
		}
		
		return ok;
	}
	
	public boolean updateReg(AdmDocumento admDocumento) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_DOCUMENTO"+
				" SET DOCUMENTO_NOMBRE = ?," +
				" TIPO = ?," +
				" COMENTARIO = ?," +
				" ORIGINAL = ?, " +
				" ORDEN = TO_NUMBER(?,'99')," +
				" FORMATO_ID = TO_NUMBER(?, '99')," +
				" CORTO = ?" +
				" WHERE DOCUMENTO_ID = TO_NUMBER(?,'999')";
			
			Object[] parametros = new Object[] {
				admDocumento.getDocumentoNombre(),admDocumento.getTipo(),admDocumento.getComentario(),admDocumento.getOriginal(),admDocumento.getOrden(),
				admDocumento.getFormatoId(),admDocumento.getCorto(),admDocumento.getDocumentoId()
			};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
			
		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|updateReg|:"+ex);		
		}
		
		return ok;
	}
	
	public boolean deleteReg(String documentoId) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_DOCUMENTO "+
				"WHERE DOCUMENTO_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[]{documentoId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros) >= 1){
				ok = true;
			}

		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|deleteReg|:"+ex);			
		}

		return ok;
	}

	public AdmDocumento mapeaRegId(String documentoId) {
		AdmDocumento objeto = new AdmDocumento();
		 
		try{
			String comando = "SELECT DOCUMENTO_ID, DOCUMENTO_NOMBRE, TIPO, COMENTARIO, ORIGINAL, ORDEN, FORMATO_ID, CORTO "+
				"FROM SALOMON.ADM_DOCUMENTO WHERE DOCUMENTO_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {documentoId};
			objeto = salomonJdbc.queryForObject(comando, new AdmDocumentoMapper(), parametros);
			
		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String documentoId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_DOCUMENTO WHERE DOCUMENTO_ID = TO_NUMBER(?,'99') ";
			
			Object[] parametros = new Object[] {documentoId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoReg() {
		String maximo = "1";
		
		try{
			String comando = "SELECT MAX(DOCUMENTO_ID)+1 MAXIMO FROM SALOMON.ADM_DOCUMENTO";
			
			maximo = salomonJdbc.queryForObject(comando,String.class);
			
		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}
	
	public String getNombreDocumento(String documentoId ) {
		String nombre = "Empty";
		
		try{
			String comando = "SELECT DOCUMENTO_NOMBRE FROM SALOMON.ADM_DOCUMENTO WHERE DOCUMENTO_ID = ?";
			
			Object[] parametros = new Object[] {documentoId};
			nombre = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|getNombreDocumento|:"+ex);
		}
		
		return nombre;
	}
	
	public String getTipo(String documentoId ) {
		String tipo	= "9";
		
		try{
			String comando = "SELECT TIPO FROM SALOMON.ADM_DOCUMENTO WHERE DOCUMENTO_ID = ?";

			Object[] parametros = new Object[] {documentoId};
			tipo = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|getTipo|:"+ex);
		}
		
		return tipo;
	}
	
	public List<AdmDocumento> getListAll(String orden ) {
		List<AdmDocumento> lista = new ArrayList<AdmDocumento>();
		
		try{
			String comando = "SELECT DOCUMENTO_ID, DOCUMENTO_NOMBRE, TIPO, COMENTARIO, ORIGINAL, ORDEN, FORMATO_ID, CORTO" +
					" FROM SALOMON.ADM_DOCUMENTO "+ orden;
			
			lista = salomonJdbc.query(comando, new AdmDocumentoMapper());
			
			
		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|getListAll|:"+ex);
		}
		
		return lista;
	}	
	
	public List<AdmDocumento> getListNewDoc(String nivelId, String orden ) {
		List<AdmDocumento> lista = new ArrayList<AdmDocumento>();
		
		try{
			String comando = "SELECT DOCUMENTO_ID, DOCUMENTO_NOMBRE, TIPO, COMENTARIO, ORIGINAL, ORDEN, FORMATO_ID, CORTO " +
					" FROM SALOMON.ADM_DOCUMENTO " +
					" WHERE DOCUMENTO_ID NOT IN " +
							" (SELECT DOCUMENTO_ID FROM SALOMON.ADM_REQUISITO" +
							" WHERE NIVEL_ID = "+nivelId+")" + orden;
			
			lista = salomonJdbc.query(comando,new AdmDocumentoMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|getListNewDoc|:"+ex);
		}
		
		return lista;
	}
	
	public List<AdmDocumento> getLista(String documentos, String orden ) {
		List<AdmDocumento> lista = new ArrayList<AdmDocumento>();
		
		try{
			String comando = " SELECT DOCUMENTO_ID, DOCUMENTO_NOMBRE, TIPO, COMENTARIO, ORIGINAL, ORDEN, FORMATO_ID, CORTO"
					+ " FROM SALOMON.ADM_DOCUMENTO"
					+ " WHERE DOCUMENTO_ID IN ("+documentos+")" + orden;
			
			lista = salomonJdbc.query(comando,new AdmDocumentoMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|getListNewDoc|:"+ex);
		}
		
		return lista;
	}
	
	public HashMap<String,AdmDocumento> mapDocumentos() {		
		HashMap<String,AdmDocumento> mapa = new HashMap<String,AdmDocumento>();
		List<AdmDocumento> lista		= new ArrayList<AdmDocumento>();		
		try{
			String comando = "SELECT DOCUMENTO_ID, DOCUMENTO_NOMBRE, TIPO, COMENTARIO, ORIGINAL, ORDEN, FORMATO_ID, CORTO"
					+ " FROM SALOMON.ADM_DOCUMENTO";			
			lista = salomonJdbc.query(comando, new AdmDocumentoMapper());
			for (AdmDocumento doc: lista ) {
				mapa.put(doc.getDocumentoId(), doc);
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.documento.spring.AdmDocumentoDao|mapDocumentos|:"+ex);
		}
		
		return mapa;
	}
}
