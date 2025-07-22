package adm.catalogo.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatModalidadDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(CatModalidad catModalidad) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO "+
				"ENOC.CAT_MODALIDAD(MODALIDAD_ID, NOMBRE_MODALIDAD, ENLINEA, ADMISIBLE ) "+
				"VALUES( TO_NUMBER(?,'99'), ?, ?, ? ) ";
			
			Object[] parametros = new Object[] {
				catModalidad.getModalidadId(),catModalidad.getNombreModalidad(),catModalidad.getEnLinea(),catModalidad.getAdmisible()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatModalidadDao|insertReg|:"+ex);			
		}
		
		return ok;
	}	
	
	public boolean updateReg(CatModalidad catModalidad) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE ENOC.CAT_MODALIDAD "+ 
				"SET NOMBRE_MODALIDAD = ?," +
				" ENLINEA = ?," +
				" ADMISIBLE = ? " +
				"WHERE MODALIDAD_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {
				catModalidad.getNombreModalidad(),catModalidad.getEnLinea(),catModalidad.getAdmisible(),catModalidad.getModalidadId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatModalidadDao|updateReg|:"+ex);		
		}
		
		return ok;
	}	
	
	public boolean deleteReg(String modalidadId) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM ENOC.CAT_MODALIDAD "+ 
				"WHERE MODALIDAD_ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {modalidadId};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatModalidadDao|deleteReg|:"+ex);			
		}

		return ok;
	}
	
	public CatModalidad mapeaRegId(String modalidadId) {
		CatModalidad objeto =  new CatModalidad();
		 
		try{
			String comando = "SELECT MODALIDAD_ID, NOMBRE_MODALIDAD, ENLINEA, ADMISIBLE "+
				"FROM ENOC.CAT_MODALIDAD WHERE MODALIDAD_ID = TO_NUMBER(?,'99')"; 
			
			Object[] parametros = new Object[] {modalidadId};
			objeto = salomonJdbc.queryForObject(comando, new CatModalidadMapper(), parametros);
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatModalidadDao|mapeaRegId|:"+ex);
			ex.printStackTrace();
		}

		return objeto;
	}
	
	public boolean existeReg(String modalidadId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_MODALIDAD WHERE MODALIDAD_ID = TO_NUMBER(?,'99') "; 
			
			Object[] parametros = new Object[] {modalidadId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatModalidadDao|existeReg|:"+ex);
		}
		
		return ok;
	}
				
	public String maximoReg() {
		String maximo = "1";
		
		try{
			String comando = "SELECT MAX(MODALIDAD_ID)+1 MAXIMO FROM ENOC.CAT_MODALIDAD"; 
			
			maximo = salomonJdbc.queryForObject(comando,String.class);
		
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatModalidadDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}
	
	public String getNombreModalidad(String modalidadId ) {
		String nombre = "-";
		
		try{
			String comando = "SELECT NOMBRE_MODALIDAD FROM ENOC.CAT_MODALIDAD WHERE MODALIDAD_ID = ?"; 
			
			Object[] parametros = new Object[] {modalidadId};
			nombre = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatModalidadDao|getNombreModalidad|:"+ex);
		}
		
		return nombre;
	}
	
	public boolean esLinea(String modalidadId) {
		boolean ok = true;
		
		try{
			String comando = "SELECT ENLINEA FROM ENOC.CAT_MODALIDAD WHERE MODALIDAD_ID = ?"; 
			
			Object[] parametros = new Object[] {modalidadId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatModalidadDao|esLinea|:"+ex);
		}
		
		return ok;
	}
	
	public String modalidadesEnLinea() {
		String modalidades 	= "";
		
		List<CatModalidad> lista	= new ArrayList<CatModalidad>();
		
		try{
			String comando = "SELECT MODALIDAD_ID FROM ENOC.CAT_MODALIDAD WHERE ENLINEA = 'S' ORDER BY MODALIDAD_ID";	
			
			lista = salomonJdbc.query(comando, new CatModalidadMapper());
			
			if(lista.size() >= 2) {
				for(CatModalidad modalidad : lista) {
					modalidades += ","+modalidad.getModalidadId();
				}
			}else {
				for(CatModalidad modalidad : lista) {
					modalidades += modalidad.getModalidadId();
				}
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatModalidadDao|modalidadesEnLinea|:"+ex);
		}
		
		return modalidades;
	}
	
	public List<CatModalidad> getListAll(String orden ) {
		List<CatModalidad> lista 		= new ArrayList<CatModalidad>();
		
		try{
			String comando = "SELECT MODALIDAD_ID, NOMBRE_MODALIDAD, ENLINEA, ADMISIBLE FROM ENOC.CAT_MODALIDAD "+orden; 
			
			lista = salomonJdbc.query(comando, new CatModalidadMapper());
			
		}catch(Exception ex){
			System.out.println("Error -  aca.catalogo.spring.CatModalidadDao|getListAll|:"+ex);
		}
		
		return lista;
	}
	
	public List<CatModalidad> getListInscritos(String orden ) {
		List<CatModalidad> lista 		= new ArrayList<CatModalidad>();
		
		try{
			String comando = "SELECT A.MODALIDAD_ID AS MODALIDAD_ID," +
					" (SELECT COUNT(DISTINCT(B.CODIGO_PERSONAL)) FROM ENOC.INSCRITOS B WHERE B.MODALIDAD_ID = A.MODALIDAD_ID) AS NOMBRE_MODALIDAD," +
					" ENLINEA, ADMISIBLE" +
					" FROM ENOC.CAT_MODALIDAD A" + 
					" WHERE (SELECT COUNT(DISTINCT(B.CODIGO_PERSONAL)) FROM ENOC.INSCRITOS B WHERE B.MODALIDAD_ID = A.MODALIDAD_ID) > 0 "+ orden;
			
			lista = salomonJdbc.query(comando, new CatModalidadMapper());
			
		}catch(Exception ex){
			System.out.println("Error -  aca.catalogo.spring.CatModalidadDao|getListInscritos|:"+ex);
		}
		
		return lista;
	}
	
	public HashMap<String,CatModalidad> getMapAll(String orden ) {
		HashMap<String,CatModalidad> mapa = new HashMap<String,CatModalidad>();
		List<CatModalidad> lista	= new ArrayList<CatModalidad>();

		try{
			String comando = "SELECT MODALIDAD_ID, NOMBRE_MODALIDAD, ENLINEA, ADMISIBLE FROM ENOC.CAT_MODALIDAD "+ orden;
			
			lista = salomonJdbc.query(comando, new CatModalidadMapper());
			
			for (CatModalidad modalidad : lista){
				mapa.put(modalidad.getModalidadId(), modalidad);
			}
			
		}catch(Exception ex){
			System.out.println("Error -  aca.catalogo.spring.CatModalidadDao|getMapAll|:"+ex);
		}
		
		return mapa;
	}
	
	public List<CatModalidad> getListInscCargas(String cargas, String orden ) {
		List<CatModalidad> lista 		= new ArrayList<CatModalidad>();
		
		try{
			String comando = "SELECT A.MODALIDAD_ID AS MODALIDAD_ID," +
					" (SELECT COALESCE(COUNT(DISTINCT(B.CODIGO_PERSONAL)),0) FROM ENOC.INSCRITOS B WHERE B.CARGA_ID IN ("+cargas+") AND B.MODALIDAD_ID = A.MODALIDAD_ID) AS NOMBRE_MODALIDAD," +
					" ENLINEA, ADMISIBLE" +
					" FROM ENOC.CAT_MODALIDAD A" + 
					" WHERE (SELECT COALESCE(COUNT(DISTINCT(B.CODIGO_PERSONAL)),0) FROM ENOC.INSCRITOS B WHERE B.CARGA_ID IN ("+cargas+") AND B.MODALIDAD_ID = A.MODALIDAD_ID) > 0 "+ orden;

			lista = salomonJdbc.query(comando, new CatModalidadMapper());
			
		}catch(Exception ex){
			System.out.println("Error -  aca.catalogo.spring.CatModalidadDao|getListInscCargas|:"+ex);
		}
		
		return lista;
	}	
	
	public List<CatModalidad> lisPorPeriodoAndPlan(String periodoId, String planId ) {
		List<CatModalidad> lista 		= new ArrayList<CatModalidad>();		
		try{
			String comando = " SELECT MODALIDAD_ID,NOMBRE_MODALIDAD,ENLINEA,ADMISIBLE FROM ENOC.CAT_MODALIDAD WHERE MODALIDAD_ID IN (SELECT MODALIDAD_ID FROM SALOMON.ADM_INGRESO_PLAN WHERE PERIODO_ID = ? AND PLAN_ID = ?)";			
			lista = salomonJdbc.query(comando, new CatModalidadMapper(), periodoId, planId);			
		}catch(Exception ex){
			System.out.println("Error -  aca.catalogo.spring.CatModalidadDao|lisPorPeriodoAndPlan|:"+ex);
		}		
		return lista;
	}
	
	
}
