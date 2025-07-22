package adm.documento.spring;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmDocAlumDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmDocAlum admDocAlum) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_DOCALUM"+ 
				"(FOLIO, DOCUMENTO_ID, ESTADO, USUARIO, COMENTARIO, CARTA) "+
				"VALUES( TO_NUMBER(?,'9999999'), TO_NUMBER(?,'99'), ?, ?, ?, ?)";
			
			Object[] parametros = new Object[] {
				admDocAlum.getFolio(),admDocAlum.getDocumentoId(),admDocAlum.getEstado(),admDocAlum.getUsuario(),
				admDocAlum.getComentario(),admDocAlum.getCarta()
			};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|insertReg|:"+ex);
		}

		return ok;
	}
			
	public boolean updateReg(AdmDocAlum admDocAlum) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_DOCALUM"+ 
				" SET ESTADO = ?, USUARIO = ?, COMENTARIO = ?, CARTA = ?"+
				" WHERE FOLIO = TO_NUMBER(?,'9999999') " +
				" AND DOCUMENTO_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {
				admDocAlum.getEstado(),admDocAlum.getUsuario(),admDocAlum.getComentario(),admDocAlum.getCarta(),
				admDocAlum.getFolio(),admDocAlum.getDocumentoId()
			};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|updateReg|:"+ex);			
		}

		return ok;
	}
	
	public boolean updateEstado(String folio, String documentoId, String estado) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_DOCALUM"+ 
				" SET ESTADO = ?"+
				" WHERE FOLIO = TO_NUMBER(?,'9999999') " +
				" AND DOCUMENTO_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {estado, folio, documentoId};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|updateReg|:"+ex);			
		}

		return ok;
	}
	
	public boolean deleteReg(String folio, String documentoId) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_DOCALUM"+ 
				" WHERE FOLIO = TO_NUMBER(?,'9999999') " +
				" AND DOCUMENTO_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[]{folio,documentoId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros) >= 1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|deleteReg|:"+ex);
		}
				
		return ok;
	}
	
	public AdmDocAlum mapeaRegId(String folio, String documentoId) {
		AdmDocAlum objeto = new AdmDocAlum();
		
		try {
			String comando = "SELECT FOLIO, DOCUMENTO_ID, ESTADO, USUARIO, COMENTARIO, CARTA"+
					" FROM SALOMON.ADM_DOCALUM " + 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
					" AND DOCUMENTO_ID = TO_NUMBER(?, '99')";
			
			Object[] parametros = new Object[] {folio,documentoId};
			objeto = salomonJdbc.queryForObject(comando, new AdmDocAlumMapper(), parametros);
			
		} catch (Exception ex) {
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|deleteReg|:"+ex);
		}
		
		return objeto;	
	}
	
	public boolean existeReg(String folio, String documentoId){
		boolean ok 	= false;	
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_DOCALUM "+ 
				"WHERE FOLIO = TO_NUMBER(?,'9999999') " +
				"AND DOCUMENTO_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {folio,documentoId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|existeReg|:"+ex);
		}
		
		return ok;
	}	
	
	public int docRegistrados(String folio){
		int total = 0;	
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_DOCALUM WHERE FOLIO = TO_NUMBER(?,'9999999')";
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				total = salomonJdbc.queryForObject(comando,Integer.class, parametros);
			}
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|docRegistrados|:"+ex);
		}
		
		return total;
	}
	
	public int docAutorizados(String folio){
		int total = 0;	
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_DOCALUM WHERE FOLIO = TO_NUMBER(?,'9999999') AND ESTADO = 'A'";			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				total = salomonJdbc.queryForObject(comando,Integer.class, parametros);
			}
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|docAutorizados|:"+ex);
		}
		
		return total;
	}
	
	public List<AdmDocAlum> getLista(Connection conn, String folio, String orden ){
		List<AdmDocAlum> lista	= new ArrayList<AdmDocAlum>();
		
		try{
			String comando = "SELECT FOLIO, DOCUMENTO_ID, ESTADO, USUARIO, COMENTARIO, CARTA"+
				" FROM SALOMON.ADM_DOCALUM" + 
				" WHERE FOLIO = TO_NUMBER('"+folio+"','99999999') "+ orden;
			
			lista = salomonJdbc.query(comando, new AdmDocAlumMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|getLista|:"+ex);
		}
		
		return lista;
	}
	
	public List<AdmDocAlum> getListAll(String orden ) {
		List<AdmDocAlum> lista	= new ArrayList<AdmDocAlum>();
		try{
			String comando = "SELECT FOLIO, DOCUMENTO_ID, ESTADO, USUARIO, COMENTARIO, CARTA FROM SALOMON.ADM_DOCALUM "+ orden; 
			
			lista = salomonJdbc.query(comando, new AdmDocAlumMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|getListAll|:"+ex);
		}
		
		return lista;
	}
	
	public List<AdmDocAlum> lisPorFolio( String folio, String orden ){
		
		List<AdmDocAlum> lista	= new ArrayList<AdmDocAlum>();	
		try{
			String comando = "SELECT FOLIO, DOCUMENTO_ID, ESTADO, USUARIO, COMENTARIO, CARTA FROM SALOMON.ADM_DOCALUM WHERE FOLIO = TO_NUMBER(?,'99999999') "+ orden;
			Object[] parametros = new Object[] {folio};
			lista = salomonJdbc.query(comando, new AdmDocAlumMapper(), parametros);
		}catch(Exception ex){
			System.out.println("Error - aca.admision.spring.AdmDocAlumDao|lisPorFolio|:"+ex);
		}		
		return lista;
	}
	
	public HashMap<String,AdmDocAlum> mapDocumentosAlumno(String folio) {
		HashMap<String,AdmDocAlum> mapa = new HashMap<String,AdmDocAlum>();
		List<AdmDocAlum> lista		= new ArrayList<AdmDocAlum>();		
		try{
			String comando = "SELECT FOLIO, DOCUMENTO_ID, ESTADO, USUARIO, COMENTARIO, CARTA FROM SALOMON.ADM_DOCALUM"
					+ " WHERE FOLIO = TO_NUMBER(?,'99999999')";
			Object[] parametros = new Object[] {folio};
			lista = salomonJdbc.query(comando, new AdmDocAlumMapper(), parametros);
			for (AdmDocAlum doc: lista ) {
				mapa.put(doc.getFolio()+doc.getDocumentoId(), doc);
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|mapDocumentosAlumno|:"+ex);
		}
		
		return mapa;
	}

	public HashMap<String,AdmDocAlum> mapDocumentosRequeridosAlumno(String folio, String carreraId ,String modalidad, String nivel, String tipo, String tipoAplicante) {
		HashMap<String,AdmDocAlum> mapa = new HashMap<String,AdmDocAlum>();
		List<AdmDocAlum> lista		= new ArrayList<AdmDocAlum>();		
		try{
			String comando = "SELECT FOLIO, DOCUMENTO_ID, ESTADO, USUARIO, COMENTARIO, CARTA FROM SALOMON.ADM_DOCALUM"
					+ " WHERE DOCUMENTO_ID IN (" 
					+ " SELECT DOCUMENTO_ID FROM SALOMON.ADM_REQUISITO WHERE MODALIDADES LIKE '%?%'"
					+ " AND NIVELES LIKE '%?%' AND TIPOS LIKE '%?%' AND NACIONALIDADES LIKE '%?%' AND CARRERA_ID = ?) AND FOLIO = ?";
			Object[] parametros = new Object[] {modalidad, nivel, tipo, tipoAplicante, carreraId, folio};
			lista = salomonJdbc.query(comando, new AdmDocAlumMapper(), parametros);
			for (AdmDocAlum doc: lista ) {
				mapa.put(doc.getFolio()+doc.getDocumentoId(), doc);
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.AdmDocAlumDao|mapDocumentosRequeridosAlumno|:"+ex);
		}
		
		return mapa;
	}

}
