package adm.vista.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class MaestrosDao {
	
	@Autowired
	@Qualifier("jdbcSalomon")
	private JdbcTemplate salomonJdbc;
	
	public boolean existeReg( String codigoPersonal){
		boolean ok =	false;
		
		try{
			String comando = "SELECT COUNT(CODIGO_PERSONAL) FROM ENOC.MAESTROS WHERE CODIGO_PERSONAL = ?";
			Object[] parametros = new Object[] {codigoPersonal};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1){
				ok = true;
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.Maestros|existeReg|:"+ex);
		}
		
		return ok;
	}
	
	public Maestros mapeaRegId( String codigoPersonal){
		String comando = "";
		Maestros maestro = new Maestros();
		try{
			comando = "SELECT COUNT(CODIGO_PERSONAL) FROM ENOC.MAESTROS WHERE CODIGO_PERSONAL = ?";
			Object[] parametros = new Object[] {codigoPersonal};
			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1){
				comando = "SELECT CODIGO_PERSONAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, " +
					"TO_CHAR(F_NACIMIENTO, 'DD/MM/YYYY') AS F_NACIMIENTO, GENERO, ESTADOCIVIL, TELEFONO, EMAIL, ESTADO " +
					"FROM ENOC.MAESTROS "+
					"WHERE CODIGO_PERSONAL = ?";
				maestro = salomonJdbc.queryForObject(comando, new MaestrosMapper(), parametros);
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.Maestros|mapeaRegId|:"+ex);			
		}
		
		return maestro;
	}

	public List<Maestros> getListAll( String orden ){
		
		List<Maestros> lista	    	= new ArrayList<Maestros>();
		
		try{
			String comando = "SELECT CODIGO_PERSONAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, " +
					"TO_CHAR(F_NACIMIENTO, 'DD/MM/YYYY') AS F_NACIMIENTO, GENERO, ESTADOCIVIL, " +
					"TELEFONO, EMAIL, ESTADO FROM ENOC.MAESTROS "+ orden;
			lista = salomonJdbc.query(comando, new MaestrosMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestroDao|getListAll|:"+ex);
		}
		
		return lista;
	}
	
	public List<Maestros> getListMaestros( String orden ){
		
		List<Maestros> lista	    	= new ArrayList<Maestros>();
		
		try{
			String comando = "SELECT CODIGO_PERSONAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO," +
					" TO_CHAR(F_NACIMIENTO, 'DD/MM/YYYY') AS F_NACIMIENTO, GENERO, ESTADOCIVIL," +
					" TELEFONO, EMAIL, ESTADO FROM ENOC.MAESTROS" +
					" WHERE CODIGO_PERSONAL IN " +
					"	(SELECT DISTINCT(CODIGO_PERSONAL) FROM ENOC.CARGA_GRUPO) "+ orden; 
			lista = salomonJdbc.query(comando, new MaestrosMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestroDao|getListMaestros|:"+ex);
		}
		
		return lista;
	}
	
	public List<Maestros> listMaestrosHoras( String orden ){
		
		List<Maestros> lista	    	= new ArrayList<Maestros>();
		
		try{
			String comando = "SELECT CODIGO_PERSONAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO," +
					" TO_CHAR(F_NACIMIENTO, 'DD/MM/YYYY') AS F_NACIMIENTO, GENERO, ESTADOCIVIL," +
					" TELEFONO, EMAIL, ESTADO FROM ENOC.MAESTROS" +
					" WHERE CODIGO_PERSONAL IN " +
					"	(SELECT DISTINCT(CODIGO_PERSONAL) FROM ENOC.EMP_HORAS) "+ orden; 
			lista = salomonJdbc.query(comando, new MaestrosMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestroDao|listMaestrosHoras|:"+ex);
		}
		
		return lista;
	}
	
	public List<Maestros> ListaEnCargasyModalidades( String cargas, String modalidades, String orden ){
		
		List<Maestros> lista	    	= new ArrayList<Maestros>();
		
		try{
			String comando = " SELECT CODIGO_PERSONAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, TO_CHAR(F_NACIMIENTO, 'DD/MM/YYYY') AS F_NACIMIENTO,"
					+ " GENERO, ESTADOCIVIL, TELEFONO, EMAIL, ESTADO"
					+ " FROM ENOC.MAESTROS"
					+ " WHERE CODIGO_PERSONAL IN"
					+ "		(SELECT CODIGO_PERSONAL FROM ENOC.CARGA_ACADEMICA WHERE CARGA_ID IN ("+cargas+") AND MODALIDAD_ID IN ("+modalidades+")) "+ orden; 
			lista = salomonJdbc.query(comando, new MaestrosMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestroDao|ListaEnCargasyModalidades|:"+ex);
		}
		
		return lista;
	}
	
	public List<Maestros> getListMaestros( String nombre, String paterno, String materno, String orden ){
		
		List<Maestros> lista	    		= new ArrayList<Maestros>();

		try{
			String comando = "SELECT CODIGO_PERSONAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, " +
					"TO_CHAR(F_NACIMIENTO, 'DD/MM/YYYY') AS F_NACIMIENTO, GENERO, ESTADOCIVIL, " +
					"TELEFONO, EMAIL, ESTADO FROM ENOC.MAESTROS " +
					"WHERE UPPER(NOMBRE) LIKE UPPER('"+nombre+"%') "+
					"AND UPPER(APELLIDO_PATERNO) LIKE UPPER('%"+paterno+"%') "+
					"AND UPPER(APELLIDO_MATERNO) LIKE UPPER('%"+materno+"%')" + orden;
			lista = salomonJdbc.query(comando, new MaestrosMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestroDao|getListMaestros|:"+ex);
		}
		
		return lista;
	}
	
	public TreeMap<String, Maestros> getTreeAll( String orden){
		
		TreeMap<String,Maestros> treeEmp	= new TreeMap<String, Maestros>();
		List<Maestros> lista	    		= new ArrayList<Maestros>();
		String llave						= "";
		
		try{
			String comando = "SELECT CODIGO_PERSONAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, " +
				" TO_CHAR(F_NACIMIENTO, 'DD/MM/YYYY') AS F_NACIMIENTO, GENERO, ESTADOCIVIL, " +
				" TELEFONO, EMAIL, ESTADO FROM ENOC.MAESTROS "+ orden;
			lista = salomonJdbc.query(comando, new MaestrosMapper());
			
			for (Maestros maestro: lista){
		
				llave = maestro.getCodigoPersonal();
				treeEmp.put(llave, maestro);
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|getTreeAll|:"+ex);
		}
		
		return treeEmp;
	}
	
	public String getNombreMaestro( String codigoPersonal, String opcion){
 		
 		String nombre			= "";
 		String comando          = "";
 		
 		try{
 			comando = "SELECT COUNT(*) FROM ENOC.MAESTROS WHERE CODIGO_PERSONAL = ? ";
 			Object[] parametros = new Object[] {codigoPersonal};
 			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1) {
	 			if (opcion.equals("NOMBRE")){
	 				comando = "SELECT NOMBRE||' '||APELLIDO_PATERNO||' '||APELLIDO_MATERNO AS NOMBRE FROM ENOC.MAESTROS WHERE CODIGO_PERSONAL = ? ";
	 			}else if (opcion.equals("APELLIDO")){
	 				comando = "SELECT APELLIDO_PATERNO||' '||APELLIDO_MATERNO||' '||NOMBRE AS NOMBRE FROM ENOC.MAESTROS WHERE CODIGO_PERSONAL = ? ";
	 			}else{
	 				comando = "SELECT NOMBRE||' '||APELLIDO_PATERNO||' '||APELLIDO_MATERNO AS NOMBRE FROM ENOC.MAESTROS WHERE CODIGO_PERSONAL = ? ";
	 			}
	 			
	 			nombre = salomonJdbc.queryForObject(comando, String.class, parametros);
 			} 	
 		}catch(Exception ex){
 			System.out.println("Error - aca.vista.spring.MaestrosDao|getNombreMaestro|:"+ex);
 		}
 		
 		return nombre;
 	}	
	
	public String getNombreCorto( String codigoPersonal, String opcion){
 		
 		String nombre			= "";
 		String comando			= "";
 		
 		try{
 			comando = "SELECT COUNT(*) FROM ENOC.MAESTROS WHERE CODIGO_PERSONAL = ? ";
 			Object[] parametros = new Object[] {codigoPersonal};
 			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1) {
 				if (opcion.equals("NOMBRE")){
 	 				comando = "SELECT SUBSTR(NOMBRE,1, CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ')-1 END)||' '||APELLIDO_PATERNO AS NOMBRE FROM ENOC.MAESTROS "+
 	 					"WHERE CODIGO_PERSONAL = ? ";
 	 			}else if (opcion.equals("APELLIDO")){
 	 				comando = "SELECT APELLIDO_PATERNO||' '||SUBSTR(NOMBRE,1, CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ')-1 END) AS NOMBRE FROM ENOC.MAESTROS "+
 	 					"WHERE CODIGO_PERSONAL = ? ";
 	 			}else{
 	 				comando = "SELECT SUBSTR(NOMBRE,1, CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ')-1 END)||' '||APELLIDO_PATERNO AS NOMBRE FROM ENOC.MAESTROS "+
 	 					"WHERE CODIGO_PERSONAL = ? ";
 	 			}	 			
 	 			nombre = salomonJdbc.queryForObject(comando, String.class, parametros);
 			} 			
 		}catch(Exception ex){
 			System.out.println("Error - aca.vista.spring.MaestrosDao|getNombreMaestro|:"+ex);
 		}
 		
 		return nombre;
 		
 	}
	
	public String getEquipo(String codigoPersonal){
		
 		String equipo			= "0"; 		
 		try{ 			
 			String comando = "SELECT COUNT(EQUIPO_ID) FROM POR_REGISTRO WHERE CODIGO_PERSONAL = ?";
 			Object[] parametros = new Object[] {codigoPersonal};
 			if ( salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1) {
 				comando = "SELECT COALESCE(EQUIPO_ID, 0) AS EQUIPO FROM POR_REGISTRO WHERE CODIGO_PERSONAL = ?";
 				equipo = salomonJdbc.queryForObject(comando, String.class, parametros);
 			} 			
 		}catch(Exception ex){
 			System.out.println("Error - aca.vista.spring.MaestrosDao|getEquipo|:"+ex);
 		}
 		
 		return equipo;
 	}
	
	public HashMap<String, String> mapaMaestrosAlumno( String codigoAlumno ) {
		
		List<adm.Mapa> lista 				= new ArrayList<adm.Mapa>();		
		HashMap<String, String> mapa		= new HashMap<String,String>();
		
		try{
			String comando = "SELECT CODIGO_PERSONAL AS LLAVE, NOMBRE||' '||APELLIDO_PATERNO AS VALOR"
					+ " FROM ENOC.MAESTROS WHERE CODIGO_PERSONAL IN (SELECT DISTINCT(MAESTRO) FROM ENOC.ALUMNO_CURSO WHERE CODIGO_PERSONAL = ?)";
			Object[] parametros = new Object[] {codigoAlumno};
			lista = salomonJdbc.query(comando, new adm.MapaMapper(), parametros);
			for ( adm.Mapa map : lista) {
				mapa.put(map.getLlave(), map.getValor() );
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|mapaMaestrosAlumno|:"+ex);
		}
		
		return mapa;
	}
	
	public String getGenero( String codigoPersonal){
		 		
 		String genero			= "-";
 		
 		try{
 			String comando = "SELECT COUNT(*) FROM ENOC.MAESTROS WHERE CODIGO_PERSONAL = ? ";
 			Object[] parametros = new Object[] {codigoPersonal};
 			if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1) {
 				comando = "SELECT GENERO FROM ENOC.MAESTROS WHERE CODIGO_PERSONAL = ?"; 			
 	 			genero = salomonJdbc.queryForObject(comando,String.class, parametros);
 			}
 			
 		}catch(Exception ex){
 			System.out.println("Error - aca.vista.spring.MaestrosDao|getGenero|:"+ex);
 		}
 		
 		return genero;
	
	}
	
	public List<Maestros> getListEmp( String nombre, String paterno, String materno, String orden ){
		
		List<Maestros> lista	= new ArrayList<Maestros>();
		
		try{
			String comando = "SELECT CODIGO_PERSONAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, "+
				"F_NACIMIENTO, GENERO, ESTADOCIVIL, TELEFONO, EMAIL, ESTADO "+
				"FROM ENOC.MAESTROS "+
				"WHERE CODIGO_PERSONAL LIKE '9%' "+
				"AND NOMBRE LIKE UPPER('%"+nombre+"%') "+
				"AND APELLIDO_PATERNO LIKE UPPER('"+paterno+"%') "+
				"AND (APELLIDO_MATERNO LIKE UPPER('"+materno+"%') OR APELLIDO_MATERNO IS NULL) "+orden;
			lista = salomonJdbc.query(comando, new MaestrosMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|getListEmp|:"+ex);
		}
		
		return lista;
	}
	
	public List<Maestros> getListMaestroEval( String cargas, String orden){
		
		List<Maestros> lista = new ArrayList<Maestros>();
		
		try{
			String comando = "SELECT CODIGO_PERSONAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO, "+
				"F_NACIMIENTO, GENERO, ESTADOCIVIL, TELEFONO, EMAIL, ESTADO "+
				"FROM ENOC.MAESTROS "+				
				"WHERE CODIGO_PERSONAL IN " +
				"	(SELECT DISTINCT(CODIGO_PERSONAL) FROM ENOC.CARGA_GRUPO WHERE CARGA_ID IN ("+cargas+") ) "+orden; 
			lista = salomonJdbc.query(comando, new MaestrosMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|getListMaestroEval|:"+ex);
		}			
		
		return lista;
	}
	
	/* Lista de Maestros evaluados en una determinada evaluacixn docente*/
	public List<Maestros> getListMaestroEvalDocente( String edoId, String orden){
		
		List<Maestros> lista = new ArrayList<Maestros>();
		
		try{
			String comando = "SELECT CODIGO_PERSONAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO,"+
				" F_NACIMIENTO, GENERO, ESTADOCIVIL, TELEFONO, EMAIL, ESTADO"+
				" FROM ENOC.MAESTROS"+				
				" WHERE CODIGO_PERSONAL IN" +
				" (SELECT DISTINCT(CODIGO_PERSONAL) FROM ENOC.CARGA_GRUPO WHERE CURSO_CARGA_ID IN (SELECT DISTINCT(CURSO_CARGA_ID) FROM ENOC.EDO_ALUMNORESP WHERE EDO_ID = "+edoId+")) "+orden;
			lista = salomonJdbc.query(comando, new MaestrosMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|getListMaestroEval|:"+ex);
		}			
		
		return lista;
	}
	
	/* Lista de Maestros con documentos digitalizados*/
	public List<Maestros> listMaestroConDocumentos( String opcion, String orden){
		
		List<Maestros> lista = new ArrayList<Maestros>();
		
		try{
			String comando = "SELECT CODIGO_PERSONAL, NOMBRE, APELLIDO_PATERNO, APELLIDO_MATERNO,"
					+ " TO_CHAR(F_NACIMIENTO,'DD/MM/YYYY') AS F_NACIMIENTO, GENERO, ESTADOCIVIL,"
					+ " TELEFONO, EMAIL, ESTADO"
					+ " FROM ENOC.MAESTROS ";
			
					if (!opcion.equals("T")) 
						comando += " WHERE CODIGO_PERSONAL IN (SELECT DISTINCT(CODIGO_PERSONAL) FROM ENOC.EMP_DOCEMP WHERE IMAGEN IS NOT NULL) ";
					
					comando += orden;
			lista = salomonJdbc.query(comando, new MaestrosMapper());
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|listMaestroConDocumentos|:"+ex);
		}			
		
		return lista;
	}
	
	public HashMap<String,String> getMaestroEdad(){
		
		HashMap<String,String> mapa	= new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();
		
		try{
			String comando = "SELECT CODIGO_PERSONAL AS LLAVE, ENOC.EMP_EDAD(CODIGO_PERSONAL) AS EDAD AS VALOR FROM ENOC.MAESTROS"; 
			lista = salomonJdbc.query(comando, new adm.MapaMapper());
			for(adm.Mapa map : lista){				
				mapa.put(map.getLlave(), (String)map.getValor());					
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|getMaestroEdad|:"+ex);
		}
		
		return mapa;
	}
	
	public HashMap<String,String> mapMaestroNombre( String opcion ){
		
		HashMap<String,String> mapa	= new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();
		String comando	             		= "";	
		
		try{
			if (opcion.equals("NOMBRE")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, NOMBRE||' '||APELLIDO_PATERNO||' '||APELLIDO_MATERNO AS VALOR FROM ENOC.MAESTROS";
			}else if (opcion.equals("APELLIDOS")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||' '||APELLIDO_MATERNO||' '||NOMBRE AS VALOR FROM ENOC.MAESTROS";
			}else {
				comando = " SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||','||SUBSTR(NOMBRE,1,CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ') END) AS VALOR"
						+ " FROM ENOC.MAESTROS";
			}
			lista = salomonJdbc.query(comando, new adm.MapaMapper());
			
			for (adm.Mapa map: lista){				
				mapa.put(map.getLlave(), (String)map.getValor());
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|mapMaestroNombre|:"+ex);
		}
		
		return mapa;
	}
	
	public HashMap<String,String> mapMaestrosDeCodigo( String maestros, String opcion ){
		
		HashMap<String,String> mapa	= new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();
		String comando	             		= "";	
		
		try{
			if (opcion.equals("NOMBRE")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, NOMBRE||' '||APELLIDO_PATERNO||' '||APELLIDO_MATERNO AS VALOR FROM ENOC.MAESTROS";
			}else if (opcion.equals("APELLIDOS")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||' '||APELLIDO_MATERNO||' '||NOMBRE AS VALOR FROM ENOC.MAESTROS";
			}else {
				comando = " SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||','||SUBSTR(NOMBRE,1,CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ') END) AS VALOR"
						+ " FROM ENOC.MAESTROS";
			}
			comando += " WHERE CODIGO_PERSONAL IN ("+maestros+")";			
			lista = salomonJdbc.query(comando, new adm.MapaMapper());			
			for (adm.Mapa map: lista){				
				mapa.put(map.getLlave(), (String)map.getValor());
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|mapMaestroNombre|:"+ex);
		}
		
		return mapa;
	}
	
	public HashMap<String,String> mapaMaestroCorto( String opcion ){
		
		HashMap<String,String> mapa	= new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();
		String comando	             		= "";	
		
		try{
			if (opcion.equals("NOMBRE")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, SUBSTR(NOMBRE,1,CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ') END)||' '||APELLIDO_PATERNO AS VALOR FROM ENOC.MAESTROS";
			}else if (opcion.equals("APELLIDOS")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||' '||SUBSTR(NOMBRE,1,CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ') END) AS VALOR FROM ENOC.MAESTROS";
			}else {
				comando = " SELECT CODIGO_PERSONAL AS LLAVE, SUBSTR(NOMBRE,1,CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ') END)||' '||APELLIDO_PATERNO AS VALOR FROM ENOC.MAESTROS";
			}
			lista = salomonJdbc.query(comando, new adm.MapaMapper());
			
			for (adm.Mapa map: lista){				
				mapa.put(map.getLlave(), (String)map.getValor());
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|mapaMaestroCorto|:"+ex);
		}
		
		return mapa;
	}
	
	public HashMap<String,String> mapMaestroNombre( String cargaId, String opcion ){
		
		HashMap<String,String> mapa	= new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();
		String comando	             		= "";	
		
		try{
			if (opcion.equals("NOMBRE")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, NOMBRE||' '||APELLIDO_PATERNO||' '||APELLIDO_MATERNO AS VALOR FROM ENOC.MAESTROS";
			}else if (opcion.equals("APELLIDOS")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||' '||APELLIDO_MATERNO||' '||NOMBRE AS VALOR FROM ENOC.MAESTROS";
			}else {
				comando = " SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||','||SUBSTR(NOMBRE,1,CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ') END) AS VALOR"
						+ " FROM ENOC.MAESTROS";
			}
			comando += " WHERE CODIGO_PERSONAL IN (SELECT CODIGO_PERSONAL FROM CARGA_GRUPO WHERE SUBSTR(CURSO_CARGA_ID,1,6) = '"+cargaId+"')";
			lista = salomonJdbc.query(comando, new adm.MapaMapper());
			
			for (adm.Mapa map: lista){				
				mapa.put(map.getLlave(), (String)map.getValor());
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|mapMaestroNombre|:"+ex);
		}
		
		return mapa;
	}
	
	public HashMap<String,String> mapMaestroAnalisis( String cargas, String opcion){
		
		HashMap<String,String> mapa	= new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();
		String comando	             		= "";	
		
		try{
			if (opcion.equals("NOMBRE")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, NOMBRE||' '||APELLIDO_PATERNO||' '||APELLIDO_MATERNO AS VALOR FROM ENOC.MAESTROS";
			}else if (opcion.equals("APELLIDOS")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||' '||APELLIDO_MATERNO||' '||NOMBRE AS VALOR FROM ENOC.MAESTROS";
			}else {
				comando = " SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||','||SUBSTR(NOMBRE,1,CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ') END) AS VALOR"
						+ " FROM ENOC.MAESTROS";
			}
			comando += " WHERE CODIGO_PERSONAL IN (SELECT DISTINCT(CODIGO_PERSONAL) FROM EST_CCOSTO WHERE SUBSTR(CURSO_CARGA_ID,1,6) IN ("+cargas+") )";
			lista = salomonJdbc.query(comando, new adm.MapaMapper());
			
			for (adm.Mapa map: lista){				
				mapa.put(map.getLlave(), (String)map.getValor());
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|mapMaestroNombre|:"+ex);
		}
		
		return mapa;
	}
	
	public HashMap<String,String> mapaMaestroComentaorio( String opcion){
		
		HashMap<String,String> mapa	= new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();
		String comando	             		= "";	
		
		try{
			if (opcion.equals("NOMBRE")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, NOMBRE||' '||APELLIDO_PATERNO||' '||APELLIDO_MATERNO AS VALOR FROM ENOC.MAESTROS";
			}else if (opcion.equals("APELLIDOS")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||' '||APELLIDO_MATERNO||' '||NOMBRE AS VALOR FROM ENOC.MAESTROS";
			}else {
				comando = " SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||','||SUBSTR(NOMBRE,1,CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ') END) AS VALOR"
						+ " FROM ENOC.MAESTROS";
			}
			comando += " WHERE CODIGO_PERSONAL IN (SELECT DISTINCT(USUARIO) FROM FIN_COMENTARIO)";
			lista = salomonJdbc.query(comando, new adm.MapaMapper());
			
			for (adm.Mapa map: lista){				
				mapa.put(map.getLlave(), (String)map.getValor());
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|mapMaestroNombre|:"+ex);
		}
		
		return mapa;
	}
	
	public HashMap<String,String> mapaMaestroContrato( String opcion){
		
		HashMap<String,String> mapa	= new HashMap<String, String>();
		List<adm.Mapa> lista = new ArrayList<adm.Mapa>();
		String comando	             		= "";	
		
		try{
			if (opcion.equals("NOMBRE")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, NOMBRE||' '||APELLIDO_PATERNO||' '||APELLIDO_MATERNO AS VALOR FROM ENOC.MAESTROS";
			}else if (opcion.equals("APELLIDOS")){
				comando = "SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||' '||APELLIDO_MATERNO||' '||NOMBRE AS VALOR FROM ENOC.MAESTROS";
			}else {
				comando = " SELECT CODIGO_PERSONAL AS LLAVE, APELLIDO_PATERNO||','||SUBSTR(NOMBRE,1,CASE INSTR(NOMBRE,' ') WHEN 0 THEN LENGTH(NOMBRE) ELSE INSTR(NOMBRE,' ') END) AS VALOR"
						+ " FROM ENOC.MAESTROS";
			}
			comando += " WHERE CODIGO_PERSONAL IN (SELECT DISTINCT(CODIGO_PERSONAL) FROM ENOC.EMP_CONTRATO)";
			lista = salomonJdbc.query(comando, new adm.MapaMapper());
			
			for (adm.Mapa map: lista){				
				mapa.put(map.getLlave(), (String)map.getValor());
			}
			
		}catch(Exception ex){
			System.out.println("Error - aca.vista.spring.MaestrosDao|mapaMaestroContrato|:"+ex);
		}
		
		return mapa;
	}	
}