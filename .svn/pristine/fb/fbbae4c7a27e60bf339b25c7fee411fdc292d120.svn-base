package adm.alumno.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdmAlumReqMapper implements RowMapper<AdmAlumReq> {

	public AdmAlumReq mapRow(ResultSet rs, int arg1) throws SQLException {
		AdmAlumReq objeto = new AdmAlumReq();
		
		objeto.setFolio(rs.getString("FOLIO"));
		objeto.setPrerrequisito(rs.getString("PRERREQUISITO"));
		objeto.setPromLic(rs.getString("PROMLIC"));
		objeto.setPromMae(rs.getString("PROMMAE"));
		objeto.setPhca(rs.getString("PHCA"));
		objeto.setPaep(rs.getString("PAEP"));
		objeto.setServicio(rs.getString("SERVICIO"));
		
		return objeto;
	}

}
