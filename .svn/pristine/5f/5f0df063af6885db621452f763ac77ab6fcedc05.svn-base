package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmFamiliaDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmFamilia admFamilia) {
		boolean ok = false;		
		try{
			String comando = "INSERT INTO SALOMON.ADM_FAMILIA"
					+ " (CODIGO, HERMANOS, UBICACION, PERSONAS, CAPTURADO) "
					+ " VALUES( ?, TO_NUMBER(?,'99'), TO_NUMBER(?,'99'), TO_NUMBER(?,'99'), ?)";			
			Object[] parametros = new Object[] {admFamilia.getCodigo(), admFamilia.getHermanos(),
					admFamilia.getUbicacion(), admFamilia.getPersonas(), admFamilia.getCapturado()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmFamiliaDao|insertReg|:"+ex);
		}
		return ok;
	}
	
	public boolean updateReg(AdmFamilia admFamilia) {
		boolean ok = false;		
		try{
			String comando = "UPDATE SALOMON.ADM_FAMILIA "
					+ " SET HERMANOS = TO_NUMBER(?,'99'), "
					+ " UBICACION = TO_NUMBER(?,'99'), "
					+ " PERSONAS = TO_NUMBER(?,'99'), "
					+ " CAPTURADO = ? "
					+ " WHERE CODIGO = ?";			
			Object[] parametros = new Object[] {admFamilia.getHermanos(), admFamilia.getUbicacion(),
					admFamilia.getPersonas(), admFamilia.getCapturado(), admFamilia.getCodigo()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmFamiliaDao|updateReg|:"+ex);
		}
		return ok;
	}
	
	public boolean deleteReg(String codigo ) {
		boolean ok = false;		
		try{
			String comando = "DELETE FROM SALOMON.ADM_FAMILIA WHERE CODIGO = ?";			
			Object[] parametros = new Object[] {codigo};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmFamiliaDao|deleteReg|:"+ex);
		}		
		return ok;
	}
	
	public AdmFamilia mapeaRegId(String codigo){
		AdmFamilia objeto = new AdmFamilia();		
		try {
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_FAMILIA WHERE CODIGO = ?";
			Object[] parametros = new Object[] {codigo};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1){
				comando = "SELECT CODIGO, HERMANOS, UBICACION, PERSONAS, CAPTURADO FROM SALOMON.ADM_FAMILIA WHERE CODIGO = ?";
				objeto = salomonJdbc.queryForObject(comando, new AdmFamiliaMapper(), parametros);	
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmFamiliaDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String codigo ) {
		boolean ok = false;		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_FAMILIA WHERE CODIGO = ?";			
			Object[] parametros = new Object[] {codigo};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros)>=1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmFamiliaDao|existeReg|:"+ex);
		}		
		return ok;
	}
	
	public List<AdmFamilia> getAll(String orden) {
		List<AdmFamilia> lista	= new ArrayList<AdmFamilia>();		
		try{
			String comando = "SELECT CODIGO, HERMANOS, UBICACION, PERSONAS, CAPTURADO FROM SALOMON.ADM_FAMILIA "+ orden; 			
			lista = salomonJdbc.query(comando, new AdmFamiliaMapper());			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmFamiliaDao|getAll|:"+ex);
		}		
		return lista;
	}

}
