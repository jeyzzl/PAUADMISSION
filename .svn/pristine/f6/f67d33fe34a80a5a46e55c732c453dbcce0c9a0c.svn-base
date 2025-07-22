package adm.alumno.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdmEstudioDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(AdmEstudio admEstudio) {
		boolean ok = false;

		try{
			String comando = "INSERT INTO SALOMON.ADM_ESTUDIO"+ 
				"(FOLIO, ID, TITULO, INSTITUCION, PAIS_ID, ESTADO_ID, CIUDAD_ID, COMPLETO, INICIO, FIN, DEPENDENCIA, CONVALIDA, ESTUDIOS, OTRA_MATERIA) "+
				"VALUES(TO_NUMBER(?,'99999999'), TO_NUMBER(?,'99'), ?,?, TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), ?, ?, ?, ?, ?, TO_NUMBER(?,'99'), ?)";
			
			Object[] parametros = new Object[] {
				admEstudio.getFolio(),admEstudio.getId(),admEstudio.getTitulo(),admEstudio.getInstitucion(),admEstudio.getPaisId(),admEstudio.getEstadoId(),admEstudio.getCiudadId(),
				admEstudio.getCompleto(),admEstudio.getInicio(),admEstudio.getFin(),admEstudio.getDependencia(),admEstudio.getConvalida(), admEstudio.getEstudios(), admEstudio.getOtraMateria()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}

		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudioDao|insertReg|:"+ex);
		}

		return ok;
	}
	
	public boolean updateReg(AdmEstudio admEstudio) {
		boolean ok = false;

		try{
			String comando = "UPDATE SALOMON.ADM_ESTUDIO"
					+ " SET TITULO = ?,"
					+ " INSTITUCION = ?,"
					+ " PAIS_ID = TO_NUMBER(?,'999'),"
					+ " ESTADO_ID = TO_NUMBER(?,'999'),"
					+ " CIUDAD_ID = TO_NUMBER(?,'999'),"
					+ " COMPLETO = ?,"
					+ " INICIO = ?,"
					+ " FIN = ?,"
					+ " DEPENDENCIA = ?, "
					+ " CONVALIDA = ?,"
					+ " ESTUDIOS = TO_NUMBER(?,'99'), "
					+ " OTRA_MATERIA = ? "
					+ " WHERE FOLIO = TO_NUMBER(?,'99999999')"
					+ " AND ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {
				admEstudio.getTitulo(),admEstudio.getInstitucion(),admEstudio.getPaisId(),admEstudio.getEstadoId(),admEstudio.getCiudadId(),admEstudio.getCompleto(),
				admEstudio.getInicio(),admEstudio.getFin(),admEstudio.getDependencia(),admEstudio.getConvalida(), admEstudio.getEstudios(), admEstudio.getOtraMateria(), admEstudio.getFolio(),admEstudio.getId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudioDao|updateReg|:"+ex);
		}

		return ok;
	}
	
	public boolean deleteReg(String folio, String id) {
		boolean ok = false;
		try{
			String comando = "DELETE FROM SALOMON.ADM_ESTUDIO "+ 
					" WHERE FOLIO = TO_NUMBER(?,'99999999') " +
					" AND ID = TO_NUMBER(?,'99') ";			
			Object[] parametros = new Object[] {folio,id};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudioDao|deleteReg|:"+ex);
		}
		
		return ok;
	}
	
	public AdmEstudio mapeaRegId(String folio, String id ) {
		AdmEstudio objeto = new AdmEstudio();
		
		try {
			String comando = "SELECT FOLIO, ID, TITULO, INSTITUCION, PAIS_ID, " +
					" ESTADO_ID, CIUDAD_ID, COMPLETO, INICIO, FIN, DEPENDENCIA, CONVALIDA, ESTUDIOS " +
					" FROM SALOMON.ADM_ESTUDIO "+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
					" AND ID = TO_NUMBER(?,'99')";
			
			Object[] parametros = new Object[] {folio,id};
			objeto = salomonJdbc.queryForObject(comando, new AdmEstudioMapper(), parametros);
		} catch (Exception ex) {
			System.out.println("Error - adm.alumno.AdmEstudioDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String folio, String id) {
		boolean ok 		= false;		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_ESTUDIO WHERE FOLIO = TO_NUMBER(?,'9999999') AND ID = TO_NUMBER(?,'99')";			
			Object[] parametros = new Object[] {folio,id};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudioDao|existeReg|:"+ex);
		}		
		return ok;
	}
	
	public boolean existeReg(String folio) {
		boolean ok 		= false;		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_ESTUDIO WHERE FOLIO = TO_NUMBER(?,'9999999')";			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudioDao|existeReg|:"+ex);
		}		
		return ok;
	}
	
	public int totReg(String folio) {
		int rows = 0;		
		try{
			String comando = "SELECT COUNT(*) FROM SALOMON.ADM_ESTUDIO WHERE FOLIO = TO_NUMBER(?,'9999999')";			
			Object[] parametros = new Object[] {folio};
			rows = salomonJdbc.queryForObject(comando,Integer.class, parametros);		
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudioDao|existeReg|:"+ex);
		}		
		return rows;
	}
	
	public String maximoReg(String folio) {
		String maximo	= "1";
		
		try{
			String comando = "SELECT MAX(ID)+1 MAXIMO FROM SALOMON.ADM_ESTUDIO WHERE FOLIO = ?"; 

			Object[] parametros = new Object[] {folio};
			maximo = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudioDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}
	
	public boolean tieneConvalida(String folio) {
		boolean convalida		= false;
		
		try{
			String comando = "SELECT * FROM SALOMON.ADM_ESTUDIO WHERE FOLIO = TO_NUMBER(?) AND CONVALIDA = 'S'";
			
			Object[] parametros = new Object[] {folio};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				convalida = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudioDao|tieneConvalida|:"+ex);
		}
		
		return convalida;
	}	
	
	public List<AdmEstudio> getAll(String orden) {
		List<AdmEstudio> lista	= new ArrayList<AdmEstudio>();
		
		try{
			String comando = "SELECT FOLIO, ID, TITULO, INSTITUCION, PAIS_ID, ESTADO_ID, CIUDAD_ID, COMPLETO, INICIO, FIN, DEPENDENCIA, CONVALIDA, ESTUDIOS " +
			" FROM SALOMON.ADM_ESTUDIO "+ orden; 
			
			lista = salomonJdbc.query(comando, new AdmEstudioMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudioDao|getAll|:"+ex);
		}
		
		return lista;
	}
	
	public List<AdmEstudio> getListAFolio(String folio, String orden) {
		List<AdmEstudio> lista	= new ArrayList<AdmEstudio>();
		
		try{
			String comando = "SELECT FOLIO, ID, TITULO, INSTITUCION, PAIS_ID, ESTADO_ID, CIUDAD_ID, COMPLETO, INICIO, FIN, DEPENDENCIA, CONVALIDA, ESTUDIOS " +
			" FROM SALOMON.ADM_ESTUDIO WHERE FOLIO = '"+folio+"'"+ orden; 
			
			lista = salomonJdbc.query(comando, new AdmEstudioMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudioDao|getListAFolio|:"+ex);
		}
		
		return lista;
	}

}
