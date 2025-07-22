package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmSocioecoDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmSocioeco admSocioeco) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO SALOMON.ADM_SOCIOECO (CODIGO, BANOS, AUTOS, INTERNET, PERSONAS, CUARTOS, INGRESO, CAPTURADO) "+
				"VALUES(?, TO_NUMBER(?,'99'), TO_NUMBER(?,'99'), ?, TO_NUMBER(?,'99'), TO_NUMBER(?,'99'), TO_NUMBER(?,'99'), ?)";
			
			Object[] parametros = new Object[] {admSocioeco.getCodigo(), admSocioeco.getBanos(), admSocioeco.getAutos(),
					admSocioeco.getInternet(), admSocioeco.getPersonas(), admSocioeco.getCuartos(), admSocioeco.getIngreso(), admSocioeco.getCapturado()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmSocioecoDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(AdmSocioeco admSocioeco ) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE SALOMON.ADM_SOCIOECO "
					+ " SET BANOS = TO_NUMBER(?,'99'), "
					+ " AUTOS = TO_NUMBER(?,'99'), "
					+ " INTERNET = ?, "
					+ " PERSONAS = TO_NUMBER(?,'99'), "
					+ " CUARTOS = TO_NUMBER(?,'99'), "
					+ " INGRESO = TO_NUMBER(?,'99'), "
					+ " CAPTURADO = ? "
					+ " WHERE CODIGO = ?";
			
			Object[] parametros = new Object[] {admSocioeco.getBanos(), admSocioeco.getAutos(),
					admSocioeco.getInternet(), admSocioeco.getPersonas(), admSocioeco.getCuartos(), 
					admSocioeco.getIngreso(), admSocioeco.getCapturado(), admSocioeco.getCodigo()};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmSocioecoDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String codigo ) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM SALOMON.ADM_SOCIOECO WHERE CODIGO = ?";
			
			Object[] parametros = new Object[] {codigo};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmSocioecoDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmSocioeco mapeaRegId(String codigo ) {
		AdmSocioeco objeto = new AdmSocioeco();
		
		try {
			String comando = "SELECT CODIGO, BANOS, AUTOS, INTERNET, PERSONAS, CUARTOS, INGRESO, CAPTURADO"
					+ " FROM SALOMON.ADM_SOCIOECO WHERE CODIGO = ?";
			
			Object[] parametros = new Object[] {codigo};
			objeto = salomonJdbc.queryForObject(comando, new AdmSocioecoMapper(), parametros);
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmSocioecoDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String codigo ) {
		boolean ok = false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_SOCIOECO WHERE CODIGO = ?";
			
			Object[] parametros = new Object[] {codigo};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmSocioecoDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public List<AdmSocioeco> getAll(String orden) {
		List<AdmSocioeco> lista	= new ArrayList<AdmSocioeco>();
		
		try{
			String comando = "SELECT CODIGO, BANOS, AUTOS, INTERNET, PERSONAS, "
					+ " CUARTOS, INGRESO, CAPTURADO FROM SALOMON.ADM_SOCIOECO "+ orden;
			
			lista = salomonJdbc.query(comando, new AdmSocioecoMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.spring.AdmSocioecoDao|getAll|:"+ex);
		}
		
		return lista;
	}

}
