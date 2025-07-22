// Bean folio la tabla folio Adm_Contacto
package  adm.alumno;

import java.sql.*;

public class AdmContacto{	
	private String folio;
	private String orden;
	private String envio;
	private String fecha;
	private String mensaje;
	private String estado;
	
	public AdmContacto(){
		folio 		= "";
		orden 		= "";
		envio 		= ""; 
		fecha		= "";
		mensaje		= "";
		estado 		= "";
	}
	

	/**
	 * @return the folio
	 */
	public String getFolio() {
		return folio;
	}


	/**
	 * @param folio the folio to set
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}


	/**
	 * @return the orden
	 */
	public String getOrden() {
		return orden;
	}


	/**
	 * @param orden the orden to set
	 */
	public void setOrden(String orden) {
		this.orden = orden;
	}



	/**
	 * @return the envio
	 */
	public String getEnvio() {
		return envio;
	}



	/**
	 * @param envio the envio to set
	 */
	public void setEnvio(String envio) {
		this.envio = envio;
	}



	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}



	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}



	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}



	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}



	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}



	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}



	public boolean insertReg(Connection conn) throws Exception{
		boolean ok = false;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement("INSERT INTO SALOMON.ADM_CONTACTO"+ 
				"(FOLIO, ORDEN, FECHA, ENVIO, MENSAJE, ESTADO) "+
				"VALUES( TO_NUMBER(?,'9999999'), ?, sysdate, ?, ?, ?)");
			ps.setString(1, folio);
			ps.setString(2, orden);
			ps.setString(3, envio);
			ps.setString(4, mensaje);
			ps.setString(5, estado);
			
			if (ps.executeUpdate()== 1)
				ok = true;
			else
				ok = false;
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmContacto|insertReg|:"+ex);
		}finally{
			if (ps!=null) ps.close();
		}
		return ok;
	}
	
	public boolean deleteReg(Connection conn ) throws Exception{
		boolean ok = false;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement("DELETE FROM SALOMON.ADM_CONTACTO "+ 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') "+
					"AND ORDEN = ? ");
			ps.setString(1, folio);
			ps.setString(2, orden);			
			
			if (ps.executeUpdate()== 1)
				ok = true;
			else
				ok = false;
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmContacto|folioleteReg|:"+ex);
			ok = false;
		}finally{
			if (ps!=null) ps.close();
		}
		
		return ok;
	}
	
	public void mapeaReg(ResultSet rs ) throws SQLException{
		folio 		= rs.getString("FOLIO");
		orden	 	= rs.getString("ORDEN");
		envio		= rs.getString("ENVIO");
		fecha	 	= rs.getString("FECHA");
		mensaje 	= rs.getString("MENSAJE");
		estado 		= rs.getString("ESTADO");
	}
	
	public void mapeaRegId( Connection conn, String folio, String orden, String fecha ) throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = conn.prepareStatement("SELECT FOLIO, ORDEN, FECHA, MENSAJE, ESTADO "+
			"FROM SALOMON.ADM_CONTACTO "+ 
			"WHERE FOLIO = TO_NUMBER(?,'9999999') "+
			"AND ORDEN = ? ");
		ps.setString(1, folio);
		ps.setString(2, orden);		
		
		rs = ps.executeQuery();
		if (rs.next()){
			mapeaReg(rs);
		}
		if (rs!=null) rs.close();
		if (ps!=null) ps.close();		
	}
	
	public boolean existeReg(Connection conn ) throws SQLException{
		boolean 		ok 	= false;
		ResultSet 		rs		= null;
		PreparedStatement ps	= null;
		
		try{
			ps = conn.prepareStatement("SELECT * FROM SALOMON.ADM_CONTACTO "+ 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') "+
					"AND ORDEN = ? ");
			ps.setString(1,folio);
			ps.setString(2,orden);			
			
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
	
	public String maximoOrden(Connection conn, String folio) throws SQLException{
		
		ResultSet 		rs		= null;
		PreparedStatement ps	= null;
		String maximo			= "1";
		
		try{
			ps = conn.prepareStatement("SELECT MAX(ORDEN)+1 AS MAXIMO FROM SALOMON.ADM_CONTACTO" + 
					" WHERE FOLIO = TO_NUMBER(?,'9999999')");
			ps.setString(1, folio);	
			rs = ps.executeQuery();
			if (rs.next())
				maximo = rs.getString("MAXIMO")==null?"1":rs.getString("MAXIMO");
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmContacto|getNombreCiudad|:"+ex);
		}finally{
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
		}
		
		return maximo;
	}
	
	public static String getMensaje(Connection conn, String folio, String orden, String fecha) throws SQLException{
		
		ResultSet 		rs		= null;
		PreparedStatement ps	= null;
		String nombre			= "vacio";
		
		try{
			ps = conn.prepareStatement("SELECT MENSAJE FROM SALOMON.ADM_CONTACTO " + 
					"WHERE FOLIO = TO_NUMBER(?,'9999999') " +
					"AND ORDEN = ?");
			ps.setString(1, folio);
			ps.setString(2, orden);			
			rs = ps.executeQuery();
			if (rs.next())
				nombre = rs.getString("MENSAJE");
			
		}catch(Exception ex){
			System.out.println("Error - adm.alumno.AdmContacto|getNombreCiudad|:"+ex);
		}finally{
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
		}
		
		return nombre;
	}
}