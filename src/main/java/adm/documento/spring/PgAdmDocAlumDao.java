package adm.documento.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PgAdmDocAlumDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(PgAdmDocAlum pgAdmDocAlum) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO ADM_DOCALUM"+ 
				"(FOLIO, DOCUMENTO_ID, HOJA, IMAGEN)"+
				" VALUES(TO_NUMBER(?,'9999999'), TO_NUMBER(?,'99'), TO_NUMBER(?,'99'), ?)";
			
			Object[] parametros = new Object[] {
				pgAdmDocAlum.getFolio(),pgAdmDocAlum.getDocumentoId(),pgAdmDocAlum.getHoja(),pgAdmDocAlum.getImagen()
			};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.PgAdmDocAlumDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(PgAdmDocAlum pgAdmDocAlum) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE ADM_DOCALUM"+ 
				" SET IMAGEN = ?"+
				" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
				" AND DOCUMENTO_ID = TO_NUMBER(?,'99')" +
				" AND HOJA = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {
				pgAdmDocAlum.getImagen(),pgAdmDocAlum.getFolio(),pgAdmDocAlum.getDocumentoId(),pgAdmDocAlum.getHoja()
			};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	

		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.PgAdmDocAlumDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio, String documentoId, String hoja) {
		boolean ok = false;
		
		try{
			String comando = "SELECT LO_UNLINK(IMAGEN) AS RESULTADO FROM ADM_DOCALUM"+ 
			" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
			" AND DOCUMENTO_ID = TO_NUMBER(?,'99')" +
			" AND HOJA = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[]{folio,documentoId,hoja};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros) >= 1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.PgAdmDocAlumDao|deleteReg|:"+ex);
		}

		if(ok){
			ok = false;
			try{
				String comando = "DELETE FROM ADM_DOCALUM"+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
					" AND DOCUMENTO_ID = TO_NUMBER(?,'99')" +
					" AND HOJA = TO_NUMBER(?,'99')";
				
				Object[] parametros = new Object[]{folio,documentoId,hoja};
				if (salomonJdbc.queryForObject(comando,Integer.class, parametros) >= 1){
					ok = true;
				}
				
			}catch(Exception ex){
				System.out.println("Error - adm.documento.spring.PgAdmDocAlumDao|borrar - deleteReg|:"+ex);
			}

		}else{
			System.out.println("No se pudo desligar la imagen... - adm.documento.spring.PgAdmDocAlumDao|deleteReg");
		}
		
		return ok;
	}
	
	public PgAdmDocAlum mapeaRegId(String folio, String documentoId, String hoja) {
		PgAdmDocAlum objeto = new PgAdmDocAlum();
		
		try {
			String comando = "SELECT FOLIO, DOCUMENTO_ID, HOJA, IMAGEN"+
					" FROM ADM_DOCALUM" + 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
					" AND DOCUMENTO_ID = TO_NUMBER(?, '99')" +
					" AND HOJA = TO_NUMBER(?, '99')";
		
			Object[] parametros = new Object[] {folio,documentoId,hoja};
			objeto = salomonJdbc.queryForObject(comando, new PgAdmDocAlumMapper(), parametros);
			
		} catch (Exception ex) {
			System.out.println("Error - adm.documento.spring.PgAdmDocAlumDao|mapeaRegId|:"+ex);
		}
		
		return objeto;	
	}
	
	public boolean existeReg(String folio, String documentoId, String hoja) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM ADM_DOCALUM"+ 
				" WHERE FOLIO = TO_NUMBER(?,'9999999') " +
				" AND DOCUMENTO_ID = TO_NUMBER(?, '99')" +
				" AND HOJA = TO_NUMBER(?, '99')";
			
			Object[] parametros = new Object[] {folio,documentoId,hoja};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.PgAdmDocAlumDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public boolean existeDocumentos(String folio, String documentoId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT * FROM ADM_DOCALUM"+ 
				" WHERE FOLIO = TO_NUMBER(?,'9999999') " +
				" AND DOCUMENTO_ID = TO_NUMBER(?, '99')";
			
			Object[] parametros = new Object[] {folio,documentoId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.PgAdmDocAlumDao|existeDocumentos|:"+ex);
		}
		
		return ok;
	}
	
	public boolean existeReg(String folio, String documentoId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM ADM_DOCALUM"+ 
				" WHERE FOLIO = TO_NUMBER(?,'9999999') " +
				" AND DOCUMENTO_ID = TO_NUMBER(?, '99')";
			
			Object[] parametros = new Object[] {folio,documentoId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.spring.PgAdmDocAlumDao|existeReg|:"+ex);
		}

		return ok;
	}

}
