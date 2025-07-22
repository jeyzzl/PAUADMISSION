package adm.alumno.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmFotoDao {
	
	@Autowired
	@Qualifier("jdbcAdm")
	private JdbcTemplate admJdbc;
	
	public boolean insertReg(AdmFoto admFoto) {
		boolean ok = false;		
		try{
			String comando = "INSERT INTO ADM_FOTO (FOLIO, FOTO, FECHA) VALUES(TO_NUMBER(?,'9999999'), ?, TO_DATE(?,'DD/MM/YYYY'))";			
			Object[] parametros = new Object[] {
				admFoto.getFolio(),admFoto.getFoto(),admFoto.getFecha()
			};
			if (admJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmFotoDao|insertReg|:"+ex);
		}		
		return ok;
	}
	
	public boolean updateReg(AdmFoto admFoto) {
		boolean ok = false;		
		try{
			String comando = "UPDATE ADM_FOTO SET FOTO = ? WHERE FOLIO = TO_NUMBER(?,'9999999')";
			Object[] parametros = new Object[] {
				admFoto.getFoto(),admFoto.getFolio()
			};
			if (admJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmFotoDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio) {
		boolean ok = false;		
		try{
			String comando = "DELETE FROM ADM_FOTO FOLIO = TO_NUMBER(?,'9999999')";			
			Object[] parametros = new Object[]{folio};
			if (admJdbc.update(comando,parametros) >= 1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmFotoDao|deleteReg|:"+ex);
		}		
		return ok;
	}
	
	public AdmFoto mapeaRegId(String folio) {
		AdmFoto objeto = new AdmFoto();		
		try {
			String comando = "SELECT FOLIO, FOTO, FECHA FROM ADM_FOTO WHERE FOLIO = TO_NUMBER(?,'9999999')";			
			Object[] parametros = new Object[] {folio};
			objeto = admJdbc.queryForObject(comando, new AdmFotoMapper(), parametros);
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.spring.AdmFotoDao|mapeaRegId|:"+ex);
		}
		return objeto;	
	}
	
	public boolean existeReg(String folio) {
		boolean ok 	= false;		
		try{
			String comando = "SELECT COUNT(FOLIO) FROM ADM_FOTO WHERE FOLIO = TO_NUMBER(?,'9999999')";			
			Object[] parametros = new Object[] {folio};
			if (admJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmFotoDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public boolean tieneFoto(String folio) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(FOLIO) FROM ADM_FOTO WHERE FOLIO = TO_NUMBER(?,'9999999') AND FOTO IS NOT NULL";			
			Object[] parametros = new Object[] {folio};
			if (admJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmFotoDao|existeReg|:"+ex);
		}
		
		return ok;
	}
}
