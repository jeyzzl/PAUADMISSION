package adm.alumno.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmAsesorDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public AdmAsesor mapeaRegId(String asesorId ){
		AdmAsesor objeto = new AdmAsesor();
		
		try {
			String comando = "SELECT ASESOR_ID, CORREO,CHAT, TELEFONO, ESTADO" +
					" FROM SALOMON.ADM_ASESOR "+ 
					" WHERE ASESOR_ID = ?";
			
			Object[] parametros = new Object[] {asesorId};
			objeto = salomonJdbc.queryForObject(comando, new AdmAsesorMapper(), parametros);
			
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.AdmAsesorDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String asesorId) {
		boolean ok 		= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_ASESOR "+ 
					" WHERE ASESOR_ID = ?";
			
			Object[] parametros = new Object[] {asesorId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
						
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAsesorDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String getAsesorActivo() {
		String activo = "0";
		
		try {
			String comando = "SELECT ASESOR_ID" +
							" FROM SALOMON.ADM_ASESOR "+
							" WHERE ESTADO = 'A'";
			Object[] parametros = new Object[] {};
			activo = salomonJdbc.queryForObject(comando, String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmAsesorDao|getAsesorActivo|:"+ex);
		}
		return activo;
	}

}
