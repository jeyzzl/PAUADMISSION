// Bean del Catalogo de Facultades
package  adm.catalogo;

import java.sql.*;

public class CatFacultad{
	private String areaId;
	private String facultadId;
	private String titulo;
	private String nombreFacultad;
	private String codigoPersonal;
	
	public CatFacultad(){
		areaId 		= "";
		facultadId		= "";
		titulo			= "";
		nombreFacultad	= "";
		codigoPersonal	= "";
	}
	
	public String getAreaId(){
		return areaId;
	}
	
	public void setAreaId( String areaId){
		this.areaId = areaId;
	}
	
	public String getFacultadId(){
		return facultadId;
	}
	
	public void setFacultadId( String facultadId){
		this.facultadId = facultadId;
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public void setTitulo( String titulo){
		this.titulo = titulo;
	}
	
	public String getNombreFacultad(){
		return nombreFacultad;
	}
	
	public void setNombreFacultad( String nombreFacultad){
		this.nombreFacultad = nombreFacultad;
	}
	
	public String getCodigoPersonal(){
		return codigoPersonal;
	}
	
	public void setCodigoPersonal( String codigoPersonal){
		this.codigoPersonal = codigoPersonal;
	}
	
	public boolean insertReg(Connection conn ) throws Exception{
		boolean ok = false;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement("INSERT INTO "+
				"ENOC.CAT_FACULTAD(AREA_ID, FACULTAD_ID, TITULO, NOMBRE_FACULTAD, CODIGO_PERSONAL) "+
				"VALUES( ?, ?, ? , ? , ?)");			
			ps.setString(1, areaId);
			ps.setString(2, facultadId);
			ps.setString(3, titulo);
			ps.setString(4, nombreFacultad);
			ps.setString(5, codigoPersonal);
			
			if (ps.executeUpdate()== 1)
				ok = true;				
			else
				ok = false;
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.CatFacultad|insertReg|:"+ex);			
		}finally{
			if (ps!=null) ps.close();
		}
		
		return ok;
	}	
	
	public boolean updateReg(Connection conn ) throws Exception{
		boolean ok = false;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement("UPDATE ENOC.CAT_FACULTAD "+
				"SET AREA_ID = ?, "+
				"TITULO = ?, "+
				"NOMBRE_FACULTAD = ?, "+
				"CODIGO_PERSONAL = ? "+
				"WHERE FACULTAD_ID = ?");
			ps.setString(1, areaId);
			ps.setString(2, titulo);
			ps.setString(3, nombreFacultad);
			ps.setString(4, codigoPersonal);			
			ps.setString(5, facultadId);
			
			if (ps.executeUpdate()== 1)
				ok = true;	
			else
				ok = false;
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.CatFacultad|updateReg|:"+ex);		
		}finally{
			if (ps!=null) ps.close();
		}
		
		return ok;
	}
	
	
	public boolean deleteReg(Connection conn ) throws Exception{
		boolean ok = false;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement("DELETE FROM ENOC.CAT_FACULTAD "+
				"WHERE FACULTAD_ID = ?");
			ps.setString(1, facultadId);
			
			if (ps.executeUpdate()== 1)
				ok = true;
			else
				ok = false;
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.CatFacultad|deleteReg|:"+ex);			
		}finally{
			if (ps!=null) ps.close();
		}
		return ok;
	}
	
	public void mapeaReg(ResultSet rs ) throws SQLException{
		areaId 			= rs.getString("AREA_ID");
		facultadId 		= rs.getString("FACULTAD_ID");
		titulo	 			= rs.getString("TITULO");
		nombreFacultad 	= rs.getString("NOMBRE_FACULTAD");
		codigoPersonal 	= rs.getString("CODIGO_PERSONAL");
	}
	
	public void mapeaRegId( Connection conn, String facultadId, String areaId) throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = conn.prepareStatement("SELECT AREA_ID, FACULTAD_ID, TITULO, NOMBRE_FACULTAD, CODIGO_PERSONAL "+
			"FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ? AND AREA_ID = ?");		
		ps.setString(1, facultadId);
		ps.setString(2, areaId);
		rs = ps.executeQuery();
		if (rs.next()){
			mapeaReg(rs);
		}
		if (rs!=null) rs.close();
		if (ps!=null) ps.close();		
	}
	
	public void mapeaRegId( Connection conn, String facultadId) throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = conn.prepareStatement("SELECT AREA_ID, FACULTAD_ID, TITULO, NOMBRE_FACULTAD, CODIGO_PERSONAL "+
			"FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?");		
		ps.setString(1, facultadId);
		
		rs = ps.executeQuery();
		if (rs.next()){
			mapeaReg(rs);
		}
		if (rs!=null) rs.close();
		if (ps!=null) ps.close();		
	}
	
	public boolean existeReg(Connection conn) throws SQLException{
		boolean 		ok 	= false;
		ResultSet 		rs		= null;
		PreparedStatement ps	= null;
		
		try{
			ps = conn.prepareStatement("SELECT * FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?");	
			ps.setString(1, facultadId);
			
			rs = ps.executeQuery();
			if (rs.next())
				ok = true;
			else
				ok = false;
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.CatFacultad|existeReg|:"+ex);
		}finally{
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
		}
		
		return ok;
	}
	
	public String maximoReg(Connection conn, String areaId ) throws SQLException{
		
		ResultSet 		rs		= null;
		PreparedStatement ps	= null;
		String maximo			= "1";
		
		try{
			ps = conn.prepareStatement("SELECT MAX(FACULTAD_ID)+1 MAXIMO FROM ENOC.CAT_FACULTAD WHERE AREA_ID = ?");
			ps.setString(1, areaId);
			rs = ps.executeQuery();
			if (rs.next())
				maximo = rs.getString("MAXIMO");	
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.CatFacultad|maximoReg|:"+ex);
		}finally{
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
		}
		
		return maximo;
	}
	
	public String getNombreFacultad(Connection conn ) throws SQLException{
		
		ResultSet 		rs		= null;
		PreparedStatement ps	= null;
		String nombre			= "1";
		
		try{
			ps = conn.prepareStatement("SELECT TITULO||DECODE(FACULTAD_ID,'107',' ',' De ')||NOMBRE_FACULTAD AS NOMBRE "+
					"FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?");
			ps.setString(1, facultadId);
			rs = ps.executeQuery();
			if (rs.next())
				nombre = rs.getString("NOMBRE");	
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.CatFacultad|getNombreFacultad|:"+ex);
		}finally{
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
		}
		
		return nombre;
	}
	
	public static String getNombreFacultad(Connection conn, String facultadId ) throws SQLException{
		
		ResultSet 		rs		= null;
		PreparedStatement ps	= null;
		String nombre			= "1";
		
		try{
			ps = conn.prepareStatement("SELECT TITULO||DECODE(FACULTAD_ID,'107',' ',' de ')||NOMBRE_FACULTAD AS NOMBRE "+
					"FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?");
			ps.setString(1, facultadId);
			rs = ps.executeQuery();
			if (rs.next())
				nombre = rs.getString("NOMBRE");	
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.CatFacultad|getNombreFacultad|:"+ex);
		}finally{
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
		}
		
		return nombre;
	}
	
	public static String getNombreCorto(Connection conn, String facultadId ) throws SQLException{
		
		ResultSet 		rs		= null;
		PreparedStatement ps	= null;
		String nombre			= "1";
		
		try{
			ps = conn.prepareStatement("SELECT NOMBRE_CORTO "+
					"FROM ENOC.CAT_FACULTAD WHERE FACULTAD_ID = ?");
			ps.setString(1, facultadId);
			rs = ps.executeQuery();
			if (rs.next())
				nombre = rs.getString("NOMBRE_CORTO");	
			
		}catch(Exception ex){
			System.out.println("Error - adm.catalogo.CatFacultad|getNombreCorto|:"+ex);
		}finally{
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
		}
		
		return nombre;
	}
}