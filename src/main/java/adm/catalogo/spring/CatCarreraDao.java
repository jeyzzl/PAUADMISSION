//Clase  para la tabla Materias_Insc
package adm.catalogo.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import adm.alumno.spring.AdmAcademico;
import adm.alumno.spring.AdmAcademicoMapper;

@Component
public class CatCarreraDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(CatCarrera catCarrera) {
		boolean ok = false;
		
		try{			 
			String comando = "INSERT INTO ENOC.CAT_CARRERA"+
				"(FACULTAD_ID, CARRERA_ID, NIVEL_ID, TITULO, NOMBRE_CARRERA, NOMBRE_CORTO, CCOSTO_ID, CODIGO_PERSONAL ) "+
				"VALUES( ?, ?, TO_NUMBER(?,'99') , ? , ?, ?, ?, ?)";	
			
			Object[] parametros = new Object[] {
				catCarrera.getFacultadId(),catCarrera.getCarreraId(),catCarrera.getNivelId(),catCarrera.getTitulo(),catCarrera.getNombreCarrera(),
				catCarrera.getNombreCorto(),catCarrera.getCcostoId(),catCarrera.getCodigoPersonal()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|insertReg|:"+ex);			
		}
		
		return ok;
	}	
	
	public boolean updateReg(CatCarrera catCarrera) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE ENOC.CAT_CARRERA "+
				"SET FACULTAD_ID = ?, NIVEL_ID = TO_NUMBER(?,'99'), TITULO = ?, "+
				"NOMBRE_CARRERA = ?, NOMBRE_CORTO = ?, CCOSTO_ID = ?, "+
				"CODIGO_PERSONAL = ? "+
				"WHERE CARRERA_ID = ?";
				
			Object[] parametros = new Object[] {
				catCarrera.getFacultadId(),catCarrera.getNivelId(),catCarrera.getTitulo(),catCarrera.getNombreCarrera(),
				catCarrera.getNombreCorto(),catCarrera.getCcostoId(),catCarrera.getCodigoPersonal(),catCarrera.getCarreraId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}	
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|updateReg|:"+ex);		
		}
		
		return ok;
	}
	
	public boolean deleteReg(String carreraId) {
		boolean ok = false;		
		try{
			String comando = "DELETE FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";			
			Object[] parametros = new Object[] {carreraId};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|deleteReg|:"+ex);			
		}

		return ok;
	}
	
	public CatCarrera mapeaRegId(String facultadId, String carreraId) {
		CatCarrera objeto = new CatCarrera();		
		try {
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_CARRERA WHERE FACULTAD_ID = ? AND CARRERA_ID = ?";			
			if (salomonJdbc.queryForObject(comando,Integer.class, facultadId, carreraId)>=1){
				comando = "SELECT FACULTAD_ID, "+
						"CARRERA_ID, NIVEL_ID, TITULO, NOMBRE_CARRERA, NOMBRE_CORTO, CCOSTO_ID, CODIGO_PERSONAL "+
						"FROM ENOC.CAT_CARRERA WHERE FACULTAD_ID = ? AND CARRERA_ID = ?";				
				objeto = salomonJdbc.queryForObject(comando, new CatCarreraMapper(), facultadId,carreraId);
			}			
		} catch (Exception ex) {
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|mapeaRegId|:"+ex);	
		}

		return objeto;
	}
	
	public CatCarrera mapeaRegIdsinFac(String carreraId) {
		CatCarrera objeto = new CatCarrera();
		try {
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";	
			if (salomonJdbc.queryForObject(comando,Integer.class, carreraId)>=1){
				comando = "SELECT FACULTAD_ID, "+
						"CARRERA_ID, NIVEL_ID, TITULO, NOMBRE_CARRERA, NOMBRE_CORTO, CCOSTO_ID, CODIGO_PERSONAL "+
						"FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";
				objeto = salomonJdbc.queryForObject(comando, new CatCarreraMapper(), carreraId);
			}			
		} catch (Exception ex) {
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|mapeaRegIdsinFac|:"+ex);	
		}
		
		return objeto;	
	}
	
	public boolean existeReg(String carreraId) {
		boolean ok 	= false;
		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";			
			Object[] parametros = new Object[] {carreraId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoReg(String facultadId ) {
		String maximo	= "1";
		
		try{
			String comando = "SELECT MAX(CARRERA_ID)+1 MAXIMO FROM ENOC.CAT_CARRERA WHERE FACULTAD_ID = ?";
			
			Object[] parametros = new Object[] {facultadId};
			maximo = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}	
	
	public String getNombreCarrera(String carreraId) {
		String nombre = "";
		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";	
			if (salomonJdbc.queryForObject(comando,Integer.class, carreraId)>=1){
				comando = "SELECT NOMBRE_CARRERA FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";
				nombre = salomonJdbc.queryForObject(comando,String.class, carreraId);
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getNombreCarrera|:"+ex);
		}
		
		return nombre;
	}
	
		
	public String getNombreCorto(String carreraId ) {
		String nombre = "";		
		try{			
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";	
			if (salomonJdbc.queryForObject(comando,Integer.class, carreraId) >= 1){
				comando = "SELECT NOMBRE_CORTO FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";
				nombre = salomonJdbc.queryForObject(comando,String.class, carreraId);
			}		
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getNombreCorto|:"+ex);
		}
		
		return nombre;
	}
	
	public String getFacultadId(String carreraId ) {
		String facultadId = "";		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";
			if (salomonJdbc.queryForObject(comando,Integer.class, carreraId) >= 1){
				comando = "SELECT FACULTAD_ID FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";
				facultadId = salomonJdbc.queryForObject(comando,String.class, carreraId);
			}		
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getFacultadId|:"+ex);
		}
		
		return facultadId;
	}
	
	public String getCoordinador(String carreraId) {
		String coordinadorId = "";		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";
			if (salomonJdbc.queryForObject(comando,Integer.class, carreraId) >= 1 ){
				comando = "SELECT CODIGO_PERSONAL FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";			
				coordinadorId = salomonJdbc.queryForObject(comando,String.class, carreraId);
			}						
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getCoordinador|:"+ex);
		}
		
		return coordinadorId;
	}
	
	public String getNivel(String carreraId) {
		String nivelId	= "0";		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";			
			if (salomonJdbc.queryForObject(comando,Integer.class, carreraId) >= 1 ){
				comando = "SELECT NIVEL_ID FROM ENOC.CAT_CARRERA WHERE CARRERA_ID = ?";
				nivelId = salomonJdbc.queryForObject(comando,String.class, carreraId);
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getNivel|:"+ex);
		}		
		return nivelId;
	}
	
	public String getNombreFacultad(String facultadId ) {
		String nombre	= "1";		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?";			
			if (salomonJdbc.queryForObject(comando,Integer.class, facultadId) >= 1 ){
				comando = "SELECT TITULO||DECODE(FACULTAD_ID,'107',' ',' de ')||NOMBRE_FACULTAD AS NOMBRE FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?";				
				nombre = salomonJdbc.queryForObject(comando,String.class, facultadId);
			}		
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getNombreFacultad|:"+ex);
		}
		
		return nombre;
	}
	
	public List<CatCarrera> getListAll(String orden) {
		List<CatCarrera> lista		= new ArrayList<CatCarrera>();
		
		try{
			String comando = "SELECT FACULTAD_ID, CARRERA_ID, NIVEL_ID, TITULO, NOMBRE_CARRERA, " +
					"NOMBRE_CORTO, CCOSTO_ID, CODIGO_PERSONAL " +
					"FROM ENOC.CAT_CARRERA "+orden;	
			
			lista = salomonJdbc.query(comando, new CatCarreraMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getListAll|:"+ex);
		}
		
		return lista;
	}
	
	public List<CatCarrera> getListCarga(String cargaId, String orden) {
		List<CatCarrera> lista		= new ArrayList<CatCarrera>();
		
		try{
			String comando = "SELECT FACULTAD_ID, CARRERA_ID, NIVEL_ID, TITULO, NOMBRE_CARRERA, " +
					"NOMBRE_CORTO, CCOSTO_ID, CODIGO_PERSONAL " +
					"FROM ENOC.CAT_CARRERA" +
					" WHERE CARRERA_ID IN (SELECT CARRERA_ID FROM CARGA_PERMISO" +
										 " WHERE CARGA_ID = '"+cargaId+"') "+orden;	
			
			lista = salomonJdbc.query(comando, new CatCarreraMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getListCarga|:"+ex);
		}
		
		return lista;
	}
	
	public List<CatCarrera> getListCarrera(String facultad_Id, String orden) {
		List<CatCarrera> lista		= new ArrayList<CatCarrera>();
		
		try{
			String comando = "SELECT FACULTAD_ID, CARRERA_ID, NIVEL_ID, TITULO, NOMBRE_CARRERA, " +
					"NOMBRE_CORTO, CCOSTO_ID, CODIGO_PERSONAL " +
					"FROM ENOC.CAT_CARRERA WHERE FACULTAD_ID = '"+facultad_Id+"'"+orden;	
			
			lista = salomonJdbc.query(comando, new CatCarreraMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getListCarrera|:"+ex);
		}
		
		return lista;
	}
	
	public List<CatCarrera> getListAutorizadas(String codigoPersonal, String orden) {
		List<CatCarrera> lista		= new ArrayList<CatCarrera>();
		
		try{
			String comando = "SELECT FACULTAD_ID, CARRERA_ID, NIVEL_ID, TITULO, NOMBRE_CARRERA, " +
					"NOMBRE_CORTO, CCOSTO_ID, CODIGO_PERSONAL " +
					"FROM ENOC.CAT_CARRERA" +
					" WHERE (SELECT ACCESOS FROM ACCESO" +
							" WHERE CODIGO_PERSONAL = '"+codigoPersonal+"') LIKE '%'||CARRERA_ID||'%' "+orden;	
			
			lista = salomonJdbc.query(comando, new CatCarreraMapper());

		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getListAutorizadas|:"+ex);
		}
		
		return lista;
	}
	
	public List<CatCarrera> getListConReprobados(String cargaId, String orden) {
		List<CatCarrera> lista		= new ArrayList<CatCarrera>();
		
		try{
			String comando = "SELECT FACULTAD_ID, CARRERA_ID, NIVEL_ID, TITULO, NOMBRE_CARRERA, " +
					"NOMBRE_CORTO, CCOSTO_ID, CODIGO_PERSONAL " +
					"FROM ENOC.CAT_CARRERA" +
					" WHERE CARRERA_ID IN (SELECT CARRERA(SUBSTR(CURSO_ID,1,8))" +
										" FROM ENOC.KRDX_CURSO_ACT" +
										" WHERE SUBSTR(CURSO_CARGA_ID,1,6) = '"+cargaId+"'" +
										" AND TIPOCURSO_ID(CURSO_ID) NOT IN ('4','6')) "+orden;	

			lista = salomonJdbc.query(comando, new CatCarreraMapper());
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getListConReprobados|:"+ex);
		}
		
		return lista;
	}
	
	public List<CatCarrera> getListEnLinea(String modalidadId, String orden) {
		List<CatCarrera> lista		= new ArrayList<CatCarrera>();		
		try{
			String comando = "SELECT FACULTAD_ID, CARRERA_ID, NIVEL_ID, TITULO, NOMBRE_CARRERA," +
					" NOMBRE_CORTO, CCOSTO_ID, CODIGO_PERSONAL" +
					" FROM ENOC.CAT_CARRERA" +
					" WHERE CARRERA_ID IN (SELECT CARRERA_ID FROM ENOC.MAPA_PLAN WHERE ENLINEA IN('S','M') AND ESTADO = 'A')" +
					" AND CARRERA_ID IN (SELECT CARRERA_ID FROM SALOMON.ADM_MODCARRERA WHERE MODALIDAD_ID ='"+modalidadId+"') "+orden;
			lista = salomonJdbc.query(comando, new CatCarreraMapper());			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getListEnLinea|:"+ex);
		}
		
		return lista;
	}
	
	public List<CatCarrera> getListPlanes(String orden) {
		List<CatCarrera> lista		= new ArrayList<CatCarrera>();		
		try{
			String comando = "SELECT FACULTAD_ID, CARRERA_ID, NIVEL_ID, TITULO, NOMBRE_CARRERA, NOMBRE_CORTO, CCOSTO_ID, CODIGO_PERSONAL" +
					" FROM ENOC.CAT_CARRERA" +
					" WHERE CARRERA_ID IN (SELECT CARRERA_ID FROM ENOC.MAPA_PLAN WHERE ESTADO = 'A') "+orden;
			lista = salomonJdbc.query(comando, new CatCarreraMapper());			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|getListPlanes|:"+ex);
		}		
		return lista;
	}
	
	public HashMap<String,CatCarrera> mapaCarreras(){
		HashMap<String, CatCarrera> mapa = new HashMap<String, CatCarrera>();
		List<CatCarrera> lista = new ArrayList<CatCarrera>();
		try {
			String comando = "SELECT FACULTAD_ID, CARRERA_ID, NIVEL_ID, TITULO, NOMBRE_CARRERA, NOMBRE_CORTO, CCOSTO_ID, CODIGO_PERSONAL"
					+ " FROM ENOC.CAT_CARRERA";
			lista = salomonJdbc.query(comando, new CatCarreraMapper());
			for (CatCarrera objeto: lista ){
				mapa.put(objeto.getCarreraId(), objeto);
			}
		}catch(Exception ex) {
			System.out.println("Error - adm.catalogo.spring.CatCarreraDao|mapaCarreras|:"+ex);
		}
		return mapa;
	}
	
}