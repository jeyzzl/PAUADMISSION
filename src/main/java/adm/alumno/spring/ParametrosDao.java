package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ParametrosDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(Parametros parametros) {
		boolean ok 		= false;
		try{
			String comando = "INSERT INTO ENOC.PARAMETROS" 
						+ "(INSTITUCION,CERTIFICADOS,CONSTANCIAS,CARDEX)" 
						+ " VALUES(?,?,?,?)";
			Object[] parametros2 = new Object[] {parametros.getInstitucion(), parametros.getCertificados(), parametros.getConstancias(), parametros.getCardex()};
			if (salomonJdbc.update(comando,parametros2)==1){
				ok = true;
			}							
		}catch(Exception ex){
			System.out.println("Error - adm.parametros.spring.ParametrosDao|insertReg|:"+ex);	
		}
		return ok;
	}
	
	public boolean updateReg(Parametros parametros){
		boolean ok			= false;
		try{
			String comando = "UPDATE ENOC.PARAMETROS" 
							+" SET INSTITUCION = ?," 
							+" CERTIFICADOS = ?," 
							+" CONSTANCIAS = ?," 
							+" CARDEX = ?" 
							+" WHERE ID = TO_NUMBER(?,'99')";
			Object[] parametros2 = new Object[] {parametros.getInstitucion(), parametros.getCertificados(), parametros.getConstancias(), parametros.getCardex(), parametros.getId()};
			if (salomonJdbc.update(comando,parametros2)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.parametros.spring.ParametrosDao|updateReg|:"+ex); 
			}	
		return ok;
	}
	
	public boolean deleteReg(String id){
		boolean ok	 = false;
		try{
			String comando = "DELETE FROM ENOC.PARAMETROS WHERE ID = TO_NUMBER(?,'99')";
			if (salomonJdbc.update(comando,id)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.parametros.spring.ParametrosDao|deleteReg|:"+ex);
		}
		return ok;
	}
		
	public Parametros mapeaRegId(String id) {
		Parametros parametros = new Parametros();
		try{
			String comando 	= " SELECT ID, INSTITUCION, CERTIFICADOS, CONSTANCIAS, CARDEX"
							+ " FROM ENOC.PARAMETROS WHERE ID = TO_NUMBER(?,'99')";
			Object[] parametros2 = new Object[] {id};
			parametros = salomonJdbc.queryForObject(comando, new ParametrosMapper(), parametros2);	
	}catch(Exception ex){
		System.out.println("Error - adm.plan.spring.MapaAvanceDao|mapeaRegId|:"+ex);
	}
		return parametros;
	}
	
	public boolean existeReg(String id) {
		boolean	ok 		= false;
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.PARAMETROS WHERE ID = TO_NUMBER(?,'99')";
			Object[] parametros2 = new Object[] {id};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros2) >=1 ){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.parametros.spring.ParametrosDao|existeReg|:"+ex);
		}
			return ok;
		}
		
	public String getInstitucion(String id) {
		String institucion  		= ""; 
		
		try{
			String comando	= " SELECT COALESCE(INSTITUCION, 'UNIVERSIDAD') AS INST FROM ENOC.PARAMETROS"
							+ " WHERE ID = TO_NUMBER(?,'99')";			
			Object[] parametros2 = new Object[] {id};
			institucion = salomonJdbc.queryForObject(comando,String.class, parametros2);			
		}catch(Exception ex){
			System.out.println("Error - adm.parametros.spring.ParametrosDao|getInstitucion|:"+ex);
		}
		return institucion;
	}
	
	public List<Parametros> getLista() {
		List<Parametros> lista	= new ArrayList<Parametros>();
		try{
			String comando = " SELECT ID, INSTITUCION, CERTIFICADOS, CONSTANCIAS, CARDEX"
					+ " FROM ENOC.PARAMETROS";			
			lista = salomonJdbc.query(comando, new ParametrosMapper());
		}catch(Exception ex){
			System.out.println("Error - adm.plan.spring.ParametrosDao|getLista|:"+ex);
		}		
		return lista;
	}
	
}