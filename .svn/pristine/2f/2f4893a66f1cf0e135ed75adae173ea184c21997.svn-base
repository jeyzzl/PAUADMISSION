package adm.alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdmEstudio {
	private String folio;
	private String Id;
	private String titulo;
	private String institucion;
	private String paisId;
	private String estadoId;
	private String ciudadId;
	private String completo;
	private String inicio;
	private String fin;
	private String dependencia;
	private String convalida;
	private String estudios;
	private String otraMateria;
		
	public AdmEstudio(){
		folio 			= "";
		Id		 		= "";
		titulo	 		= "";
		institucion		= "";
		paisId 			= "";
		estadoId 		= "";
		ciudadId 		= "";
		completo		= "";
		inicio			= "-";
		fin				= "-";
		dependencia		= "-";
		convalida		= "N";
		estudios		= "0";
		otraMateria		= "0";
	}
	
	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getPaisId() {
		return paisId;
	}

	public void setPaisId(String paisId) {
		this.paisId = paisId;
	}

	public String getEstadoId() {
		return estadoId;
	}

	public void setEstadoId(String estadoId) {
		this.estadoId = estadoId;
	}

	public String getCiudadId() {
		return ciudadId;
	}

	public void setCiudadId(String ciudadId) {
		this.ciudadId = ciudadId;
	}

	public String getCompleto() {
		return completo;
	}

	public void setCompleto(String completo) {
		this.completo = completo;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getFin() {
		return fin;
	}

	public void setFin(String fin) {
		this.fin = fin;
	}

	public String getDependencia() {
		return dependencia;
	}

	public void setDependencia(String dependencia) {
		this.dependencia = dependencia;
	}
	
	public String getConvalida() {
		return convalida;
	}

	public void setConvalida(String convalida) {
		this.convalida = convalida;
	}	

	public String getEstudios() {
		return estudios;
	}

	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}
	
	public String getOtraMateria() {
		return otraMateria;
	}

	public void setOtraMateria(String otraMateria) {
		this.otraMateria = otraMateria;
	}

	// Insertar
	public boolean insertReg(Connection conn) throws Exception{
		boolean ok = false;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement("INSERT INTO SALOMON.ADM_ESTUDIO"
					+ " (FOLIO, ID, TITULO, INSTITUCION, PAIS_ID, ESTADO_ID, CIUDAD_ID, COMPLETO, INICIO, FIN, DEPENDENCIA, CONVALIDA, ESTUDIOS, OTRA_MATERIA)"
					+ " VALUES( TO_NUMBER(?,'99999999'), TO_NUMBER(?,'99'), ?,?, TO_NUMBER(?,'999'), TO_NUMBER(?,'999'), TO_NUMBER(?,'999'),"
					+ " ?, ?, ?, ?, ?, TO_NUMBER(?,'9'), ? )");
			ps.setString(1, folio);
			ps.setString(2, Id);
			ps.setString(3, titulo);
			ps.setString(4, institucion);
			ps.setString(5, paisId);
			ps.setString(6, estadoId);
			ps.setString(7, ciudadId);
			ps.setString(8, completo);
			ps.setString(9, inicio);
			ps.setString(10, fin);
			ps.setString(11, dependencia);
			ps.setString(12, convalida);
			ps.setString(13, estudios);
			ps.setString(14, otraMateria);
	
			if (ps.executeUpdate()== 1)
				ok = true;
			else
				ok = false;
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudio|insertReg|:"+ex);
		}finally{
			if (ps!=null) ps.close();
		}
		return ok;
	}
	
	// Modificar
	public boolean updateReg(Connection conn ) throws SQLException{
		boolean ok = false;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement("UPDATE SALOMON.ADM_ESTUDIO"
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
					+ " ESTUDIOS = TO_NUMBER(?,'9'), "
					+ " OTRA_MATERIA = TO_NUMBER(?,'9') "
					+ " WHERE FOLIO = TO_NUMBER(?,'99999999')"
					+ " AND ID = TO_NUMBER(?,'99')");			
			ps.setString(1,  titulo);
			ps.setString(2,  institucion);
			ps.setString(3,  paisId);
			ps.setString(4,  estadoId);
			ps.setString(5,  ciudadId);
			ps.setString(6,  completo);
			ps.setString(7,  inicio);
			ps.setString(8,  fin);
			ps.setString(9,  dependencia);
			ps.setString(10, convalida);
			ps.setString(11, estudios);
			ps.setString(12, otraMateria);
			ps.setString(13, folio);
			ps.setString(14, Id);
			
			if ( ps.executeUpdate()== 1){
				ok = true;				
			}else{
				ok = false;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudio|updateReg|:"+ex);
		}finally{
			if (ps!=null) ps.close();
		}
		return ok;
	}
	
	// Borrar
	public boolean deleteReg(Connection conn ) throws Exception{
		boolean ok = false;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement("DELETE FROM SALOMON.ADM_ESTUDIO "+ 
					" WHERE FOLIO = TO_NUMBER(?,'99999999') " +
					" AND ID = TO_NUMBER(?,'99') ");
			ps.setString(1, folio);
			ps.setString(2, Id);
			
			if (ps.executeUpdate()== 1)
				ok = true;
			else
				ok = false;
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudio|deleteReg|:"+ex);
			ok = false;
		}finally{
			if (ps!=null) ps.close();
		}
		
		return ok;
	}
	
	// MapeaReg
	public void mapeaReg(ResultSet rs ) throws SQLException{
		folio			= rs.getString("FOLIO");
		Id				= rs.getString("ID");
		titulo 			= rs.getString("TITULO");
		institucion		= rs.getString("INSTITUCION");
		paisId			= rs.getString("PAIS_ID");
		estadoId 		= rs.getString("ESTADO_ID");
		ciudadId		= rs.getString("CIUDAD_ID");
		completo		= rs.getString("COMPLETO");
		inicio			= rs.getString("INICIO");
		fin				= rs.getString("FIN");
		dependencia		= rs.getString("DEPENDENCIA");
		convalida		= rs.getString("CONVALIDA");
		estudios		= rs.getString("ESTUDIOS");
		otraMateria		= rs.getString("OTRA_MATERIA");
	}
	
	public void mapeaRegId( Connection conn, String folio, String Id ) throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = conn.prepareStatement("SELECT FOLIO, ID, TITULO, INSTITUCION, PAIS_ID, " +
				" ESTADO_ID, CIUDAD_ID, COMPLETO, INICIO, FIN, DEPENDENCIA, CONVALIDA, ESTUDIOS, OTRA_MATERIA " +
				" FROM SALOMON.ADM_ESTUDIO "+ 
				" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
				" AND ID = TO_NUMBER(?,'99')");
		ps.setString(1, folio);
		ps.setString(2, Id);
		
		rs = ps.executeQuery();
		if (rs.next()){
			mapeaReg(rs);
		}
		if (rs!=null) rs.close();
		if (ps!=null) ps.close();
	}
	
	public boolean existeReg(Connection conn ) throws SQLException{
		boolean 		ok 		= false;
		ResultSet 		rs		= null;
		PreparedStatement ps	= null;
		
		try{
			ps = conn.prepareStatement("SELECT * FROM SALOMON.ADM_ESTUDIO "+ 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')" +
					" AND ID = TO_NUMBER(?,'99')");
			ps.setString(1, folio);
			ps.setString(2, Id);
						
			rs = ps.executeQuery();
			if (rs.next()){
				ok = true;
			}else{			
				ok = false;
			}
		}catch(Exception ex){
		
		}finally{
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
		}
		
		return ok;
	}
	
	public String maximoReg(Connection conn, String folio) throws SQLException{
		
		ResultSet 		rs		= null;
		PreparedStatement ps	= null;
		String maximo			= "1";
		
		try{
			ps = conn.prepareStatement("SELECT MAX(ID)+1 MAXIMO FROM SALOMON.ADM_ESTUDIO WHERE FOLIO = ?"); 
			ps.setString(1, folio);
			rs = ps.executeQuery();
			if (rs.next())
				maximo = rs.getString("MAXIMO");
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudio|maximoReg|:"+ex);
		}finally{
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
		}
		
		return maximo;
	}
	
	public boolean tieneConvalida(Connection conn, String folio) throws SQLException{
		
		ResultSet 		rs		= null;
		PreparedStatement ps	= null;
		boolean convalida		= false;
		
		try{
			ps = conn.prepareStatement("SELECT * FROM SALOMON.ADM_ESTUDIO WHERE FOLIO = TO_NUMBER(?) AND CONVALIDA = 'S'");
			ps.setString(1, folio);
			rs = ps.executeQuery();
			if (rs.next())
				convalida = true;
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmEstudio|tieneConvalida|:"+ex);
		}finally{
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
		}
		
		return convalida;
	}	

}