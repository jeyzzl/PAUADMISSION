package adm.catalogo.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatFacultadDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean insertReg(CatFacultad catFacultad) {
		boolean ok = false;
		
		try{
			String comando = "INSERT INTO "+
				"ENOC.CAT_FACULTAD(AREA_ID, FACULTAD_ID, TITULO, NOMBRE_FACULTAD, CODIGO_PERSONAL) "+
				"VALUES( ?, ?, ? , ? , ?)";	
			
			Object[] parametros = new Object[] {
				catFacultad.getAreaId(),catFacultad.getFacultadId(),catFacultad.getTitulo(),catFacultad.getNombreFacultad(),catFacultad.getCodigoPersonal()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatFacultadDao|insertReg|:"+ex);			
		}
		
		return ok;
	}	
	
	public boolean updateReg(CatFacultad catFacultad) {
		boolean ok = false;
		
		try{
			String comando = "UPDATE ENOC.CAT_FACULTAD "+
				"SET AREA_ID = ?, "+
				"TITULO = ?, "+
				"NOMBRE_FACULTAD = ?, "+
				"CODIGO_PERSONAL = ? "+
				"WHERE FACULTAD_ID = ?";
			
			Object[] parametros = new Object[] {
				catFacultad.getAreaId(),catFacultad.getTitulo(),catFacultad.getNombreFacultad(),catFacultad.getCodigoPersonal(),catFacultad.getFacultadId()
 		 	};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatFacultadDao|updateReg|:"+ex);		
		}
		
		return ok;
	}
	
	public boolean deleteReg(String facultadId) {
		boolean ok = false;
		
		try{
			String comando = "DELETE FROM ENOC.CAT_FACULTAD "+
				"WHERE FACULTAD_ID = ?";

			Object[] parametros = new Object[] {facultadId};
			if (salomonJdbc.update(comando,parametros)==1){
				ok = true;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatFacultadDao|deleteReg|:"+ex);			
		}

		return ok;
	}
	
	public CatFacultad mapeaRegId(String facultadId, String areaId) {
		CatFacultad objeto = new CatFacultad();
		
		try {
			String comando = "SELECT AREA_ID, FACULTAD_ID, TITULO, NOMBRE_FACULTAD, CODIGO_PERSONAL "+
					"FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ? AND AREA_ID = ?";		

			Object[] parametros = new Object[] {facultadId,areaId};
			objeto = salomonJdbc.queryForObject(comando, new CatFacultadMapper(), parametros);
		} catch (Exception ex) {
			System.out.println("Error - adm.catalogo.spring.CatFacultadDao|mapeaRegId|:"+ex);			
		}
		
		return objeto;
	}
	
	public CatFacultad mapeaRegId(String facultadId) {
		CatFacultad objeto = new CatFacultad();		
		try {
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?";
			Object[] parametros = new Object[] {facultadId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				comando = "SELECT AREA_ID, FACULTAD_ID, TITULO, NOMBRE_FACULTAD, CODIGO_PERSONAL FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?";				
				objeto = salomonJdbc.queryForObject(comando, new CatFacultadMapper(), parametros);				
			}				
		} catch (Exception ex) {
			System.out.println("Error - adm.catalogo.spring.CatFacultadDao|mapeaRegId|:"+ex);
		}
		
		return objeto;
	}
	
	public boolean existeReg(String facultadId) {
		boolean ok 	= false;		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?";
			Object[] parametros = new Object[] {facultadId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatFacultadDao|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public String maximoReg(String areaId) {
		String maximo = "1";
		
		try{
			String comando = "SELECT MAX(FACULTAD_ID)+1 MAXIMO FROM ENOC.CAT_FACULTAD WHERE AREA_ID = ?";
			
			Object[] parametros = new Object[] {areaId};
			maximo = salomonJdbc.queryForObject(comando,String.class, parametros);
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatFacultadDao|maximoReg|:"+ex);
		}
		
		return maximo;
	}
	
	public String getNombreFacultad(String facultadId) {
		String nombre = "-";		
		try{
			String comando = "SELECT COUNT(*) FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?";
			Object[] parametros = new Object[] {facultadId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				comando = "SELECT TITULO||DECODE(FACULTAD_ID,'107',' ',' De ')||NOMBRE_FACULTAD AS NOMBRE "+
						"FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?";		
				nombre = salomonJdbc.queryForObject(comando,String.class, parametros);				
			}			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatFacultadDao|getNombreFacultad|:"+ex);
		}
		
		return nombre;
	}
	
	public String getNombreCorto(String facultadId ) {
		String nombre = "1";		
		try{			
			String comando = "SELECT COUNT(NOMBRE_CORTO) FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?";
			Object[] parametros = new Object[] {facultadId};
			if (salomonJdbc.queryForObject(comando,Integer.class, parametros)>=1){
				comando = "SELECT NOMBRE_CORTO FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?";
				nombre = salomonJdbc.queryForObject(comando,String.class, parametros);
			}		
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.spring.CatFacultadDao|getNombreCorto|:"+ex);
		}
		
		return nombre;
	}
	
	public HashMap<String,String> mapaFacultad () {
		List<CatFacultad> lista	= new ArrayList<CatFacultad>();
		HashMap<String,String> mapa = new HashMap<String,String>();
		
		try{
			String comando = "SELECT AREA_ID,FACULTAD_ID,TITULO,NOMBRE_FACULTAD,CODIGO_PERSONAL,NOMBRE_CORTO, INV_REFERENTE FROM ENOC.CAT_FACULTAD";
			
			lista = salomonJdbc.query(comando, new CatFacultadMapper());
			
			for(CatFacultad facultad : lista) {
				mapa.put(facultad.getFacultadId(), facultad.getNombreFacultad());
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.catalogo.spring.CatFacultadDao|mapaFacultad|:"+ex);
		}
		
		return mapa;
	}

}
