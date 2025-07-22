package adm.catalogo.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatReligionDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(CatReligion catReligion){
		boolean ok = false;		
		try{
			String comando = "INSERT INTO ENOC.CAT_RELIGION(RELIGION_ID, NOMBRE_RELIGION ) "+
				"VALUES( TO_NUMBER(?,'999'), ? ) ";			
			Object[] parametros = new Object[] {
				catReligion.getReligionId(),catReligion.getNombreReligion()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatReligionDao|insertReg|:"+ex);			
		}
		
		return ok;
	}	
	
	public boolean updateReg(CatReligion catReligion) {
		boolean ok = false;		
		try{
			String comando = "UPDATE ENOC.CAT_RELIGION SET NOMBRE_RELIGION = ? WHERE RELIGION_ID = TO_NUMBER(?,'999')";
			Object[] parametros = new Object[] {
				catReligion.getNombreReligion(),catReligion.getReligionId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatReligionDao|updateReg|:"+ex);		
		}
		
		return ok;
	}	
	
	public boolean deleteReg(String religionId) {
		boolean ok = false;		
		try{
			String comando = "DELETE FROM ENOC.CAT_RELIGION WHERE RELIGION_ID = TO_NUMBER(?,'999')";			
			Object[] parametros = new Object[] { religionId };
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatReligionDao|deleteReg|:"+ex);			
		}

		return ok;
	}
	
	public CatReligion mapeaRegId(String religionId) {
		CatReligion objeto = new CatReligion();		
		try {
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_RELIGION WHERE RELIGION_ID = TO_NUMBER(?,'999') ";
			Object[] parametros = new Object[] {religionId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				comando = "SELECT RELIGION_ID, NOMBRE_RELIGION FROM ENOC.CAT_RELIGION WHERE RELIGION_ID = TO_NUMBER(?,'999')";			
				objeto = salomonJdbc.queryForObject(comando, new CatReligionMapper(), parametros);
			}			
		} catch (Exception ex) {
			System.out.println("Error - adm.catalogo.spring.CatReligionDao|mapeaRegId|:"+ex);	
		}
		
		return objeto;
	}
	
	public boolean existeReg(String religionId) {
		boolean ok 	= false;		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_RELIGION WHERE RELIGION_ID = TO_NUMBER(?,'999')";			
			Object[] parametros = new Object[] {religionId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatReligionDao|existeReg|:"+ex);
		}		
		return ok;
	}
	
	public String maximoReg() {
		String maximo = "1";		
		try{
			String comando = "SELECT MAX(RELIGION_ID)+1 MAXIMO FROM ENOC.CAT_RELIGION";
			maximo = salomonJdbc.queryForObject(comando,String.class);			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatReligionDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}
	
	public String getNombreReligion(String religionId ) {
		String nombre = "vacio";		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_RELIGION WHERE RELIGION_ID = TO_NUMBER(?,'999') ";
			Object[] parametros = new Object[] {religionId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				comando = "SELECT NOMBRE_RELIGION FROM ENOC.CAT_RELIGION WHERE RELIGION_ID = ?";
				nombre = salomonJdbc.queryForObject(comando,String.class, parametros);
			}		
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatReligionDao|getNombreReligion|:"+ex);
		}
		
		return nombre;
	}
	
	public List<CatReligion> getListAll(String orden ) {
		List<CatReligion> lista = new ArrayList<CatReligion>();		
		try{
			String comando = "SELECT RELIGION_ID, NOMBRE_RELIGION FROM ENOC.CAT_RELIGION "+ orden;			
			lista = salomonJdbc.query(comando, new CatReligionMapper());			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatReligionDao|getListAll|:"+ex);
		}		
		return lista;
	}

}
