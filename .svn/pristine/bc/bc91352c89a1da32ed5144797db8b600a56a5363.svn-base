package adm.documento.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class PgAdmArchivosDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(PgAdmArchivos pgAdmArchivos) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO ADM_ARCHIVOS"+
				"(FOLIO, DOCUMENTO_ID, ARCHIVO, NOMBRE)"+
				" VALUES(TO_NUMBER(?,'9999999'), TO_NUMBER(?,'99'), ?, ?)";
			
			Object[] parametros = new Object[] {
				pgAdmArchivos.getFolio(),pgAdmArchivos.getDocumentoId(),pgAdmArchivos.getArchivo(),pgAdmArchivos.getNombre()
			};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.apring.PgAdmArchivosDao|insertReg|:"+ex);
		}
		
		return ok;
	}
	
	public boolean updateReg(PgAdmArchivos pgAdmArchivos) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE ADM_ARCHIVOS"+
				" SET ARCHIVO = ?,"+
				" NOMBRE = ?"+
				" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
				" AND DOCUMENTO_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {
				pgAdmArchivos.getArchivo(),pgAdmArchivos.getNombre(),pgAdmArchivos.getFolio(),pgAdmArchivos.getDocumentoId()
			};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	

		}catch(Exception ex){
			System.out.println("Error - adm.documento.apring.PgAdmArchivosDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio, String documentoId) {
		boolean ok = false;
		
		try{
			String comando = "SELECT LO_UNLINK(ARCHIVO) AS RESULTADO FROM ADM_ARCHIVOS"+
			" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
			" AND DOCUMENTO_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[]{folio,documentoId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros) >= 1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.documento.apring.PgAdmArchivosDao|deleteReg|:"+ex);
			ok = false;
		}

		if(ok){
			ok = false;
			try{
				String comando = "DELETE FROM ADM_ARCHIVOS"+
					" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
					" AND DOCUMENTO_ID = TO_NUMBER(?,'99')";
				
				Object[] parametros = new Object[]{folio,documentoId};
				if (salomonJdbc.queryForObject(comando,Integer.class, parametros) >= 1){
					ok = true;
				}
	
			}catch(Exception ex){
				System.out.println("Error - adm.documento.apring.PgAdmArchivosDao|deleteReg|:"+ex);
			}

			}else{
				System.out.println("No se pudo desligar la imagen... - aca.admision..PgAdmArchivos|deleteReg");
			}
		
		return ok;
	}
	
	public PgAdmArchivos mapeaRegId(String folio, String documentoId) {
		PgAdmArchivos objeto = new PgAdmArchivos();
		
		try {
			String comando = "SELECT FOLIO, DOCUMENTO_ID, ARCHIVO, NOMBRE"+
					" FROM ADM_ARCHIVOS" +
					" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
					" AND DOCUMENTO_ID = TO_NUMBER(?, '99')";
			
			Object[] parametros = new Object[] {folio,documentoId};
			objeto = salomonJdbc.queryForObject(comando, new PgAdmArchivosMapper(), parametros);
		} catch (Exception ex) {
			System.out.println("Error - adm.documento.apring.PgAdmArchivosDao|mapeaRegId|:"+ex);
		}

		return objeto;	
	}
	
	public boolean existeReg(String folio, String documentoId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM ADM_ARCHIVOS"+
				" WHERE FOLIO = TO_NUMBER(?,'9999999') " +
				" AND DOCUMENTO_ID = TO_NUMBER(?, '99')";
			
			Object[] parametros = new Object[] {folio,documentoId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}

		}catch(Exception ex){
			System.out.println("Error - adm.documento.apring.PgAdmArchivosDao|existeReg|:"+ex);
		}
		
		return ok;
	}	

}
