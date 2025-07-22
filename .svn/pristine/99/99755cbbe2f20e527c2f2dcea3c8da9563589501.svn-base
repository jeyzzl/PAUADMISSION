// Bean de documentos de admision del alumno
package  adm.alumno;

import java.sql.*;

public class AdmProceso{
	private String folio;
	private String fechaRegistro;	
	private String fechaSolicitud;
	private String fechaDocumentos;
	private String fechaAdmision;
	private String fechaCarta;
	private String fechaExamen;
	private String fechaDirecto;
	
	
	public AdmProceso(){
		folio 				= "";
		fechaRegistro 		= "";
		fechaSolicitud		= "";
		fechaDocumentos		= "";
		fechaAdmision		= "";
		fechaCarta			= "";
		fechaExamen			= "";
		fechaDirecto		= "";
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public String getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getFechaDocumentos() {
		return fechaDocumentos;
	}

	public void setFechaDocumentos(String fechaDocumentos) {
		this.fechaDocumentos = fechaDocumentos;
	}

	public String getFechaAdmision() {
		return fechaAdmision;
	}

	public void setFechaAdmision(String fechaAdmision) {
		this.fechaAdmision = fechaAdmision;
	}

	public String getFechaCarta() {
		return fechaCarta;
	}

	public void setFechaCarta(String fechaCarta) {
		this.fechaCarta = fechaCarta;
	}

	public String getFechaExamen() {
		return fechaExamen;
	}

	public void setFechaExamen(String fechaExamen) {
		this.fechaExamen = fechaExamen;
	}

	public String getFechaDirecto() {
		return fechaDirecto;
	}

	public void setFechaDirecto(String fechaDirecto) {
		this.fechaDirecto = fechaDirecto;
	}

	public boolean insertReg(Connection Conn) throws Exception{
		boolean ok = false;
		PreparedStatement ps = null;
		try{
			ps = Conn.prepareStatement("INSERT INTO SALOMON.ADM_PROCESO"+ 
				"(FOLIO, F_REGISTRO, F_SOLICITUD, F_DOCUMENTOS, F_ADMISION, F_CARTA, F_EXAMEN, F_DIRECTO) "+
				"VALUES(TO_NUMBER(?,'9999999'), sysdate, NULL, NULL, NULL, NULL, NULL, NULL)");
			ps.setString(1, folio);
			
			if (ps.executeUpdate()== 1)
				ok = true;
			else
				ok = false;
		}catch(Exception ex){
			System.out.println("Error - adm.documento.AdmProceso|insertReg|:"+ex);
		}finally{
			if (ps!=null) ps.close();
		}
		return ok;
	}
	
	public boolean deleteReg(Connection conn ) throws Exception{
		boolean ok = false;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement("DELETE FROM SALOMON.ADM_PROCESO"+ 
				" WHERE FOLIO = TO_NUMBER(?,'9999999')");
			ps.setString(1, folio);
			
			if (ps.executeUpdate()== 1)
				ok = true;
			else
				ok = false;
		}catch(Exception ex){
			System.out.println("Error - adm.documento.AdmProceso|deleteReg|:"+ex);
			ok = false;
		}finally{
			if (ps!=null) ps.close();
		}
				
		return ok;
	}
	
	public void mapeaReg(ResultSet rs ) throws SQLException{
		folio 				= rs.getString("FOLIO");
		fechaRegistro	 	= rs.getString("F_REGISTRO");		
		fechaSolicitud 		= rs.getString("F_SOLICITUD");
		fechaDocumentos		= rs.getString("F_DOCUMENTOS");
		fechaAdmision		= rs.getString("F_ADMISION");
		fechaCarta			= rs.getString("F_CARTA");
		fechaExamen			= rs.getString("F_EXAMEN");
		fechaDirecto		= rs.getString("F_DIRECTO");
	}
	
	public void mapeaRegId( Connection conn, String folio) throws SQLException{
		ResultSet rs = null;
		PreparedStatement ps = conn.prepareStatement("SELECT FOLIO, F_REGISTRO, F_SOLICITUD, F_DOCUMENTOS, F_ADMISION, F_CARTA, F_EXAMEN, F_DIRECTO"+
			" FROM SALOMON.ADM_PROCESO " + 
			" WHERE FOLIO = TO_NUMBER(?, '9999999')");
		ps.setString(1, folio);
		
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
			ps = conn.prepareStatement("SELECT FOLIO FROM SALOMON.ADM_PROCESO "+ 
				"WHERE FOLIO = TO_NUMBER(?,'9999999')");
			ps.setString(1,folio);
			
			rs = ps.executeQuery();
			if (rs.next()){
				ok = true;
			}else{
				ok = false;
			}
		}catch(Exception ex){
			System.out.println("Error - adm.documento.AdmProceso|existeReg|:"+ex);
		}finally{
			if (rs!=null) rs.close();
			if (ps!=null) ps.close();
		}
		return ok;
	}
	
	public boolean updateFecha(Connection conn, int numFecha) throws Exception{
		boolean ok = false;
		PreparedStatement ps = null;
		try{
			if(numFecha==1){
				ps = conn.prepareStatement("UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_REGISTRO = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')");
			}
			else if(numFecha==2){
				ps = conn.prepareStatement("UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_SOLICITUD = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')");
			}
			else if(numFecha==3){
				ps = conn.prepareStatement("UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_DOCUMENTOS = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')");
			}
			else if(numFecha==4){
				ps = conn.prepareStatement("UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_ADMISION = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')");
			}
			else if(numFecha==5){
				ps = conn.prepareStatement("UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_CARTA = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')");
			}
			else if(numFecha==6){
				ps = conn.prepareStatement("UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_EXAMEN = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')");
			}
			else if(numFecha==7){
				ps = conn.prepareStatement("UPDATE SALOMON.ADM_PROCESO"+ 
						" SET F_DIRECTO = sysdate "+
						" WHERE FOLIO = TO_NUMBER(?,'9999999')");
			}
			ps.setString(1, folio);
			
			if (ps.executeUpdate()== 1)
				ok = true;	
			else
				ok = false;
		}catch(Exception ex){
			System.out.println("Error - adm.documento.AdmProceso|updateReg|:"+ex);			
		}finally{
			if (ps!=null) ps.close();
		}
		return ok;
	}
}