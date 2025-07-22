package adm.alumno.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdmIngresoModMapper implements RowMapper<AdmIngresoMod> {
	
	public AdmIngresoMod mapRow(ResultSet rs, int arg1) throws SQLException {
		AdmIngresoMod objeto = new AdmIngresoMod();
		
		objeto.setPeriodoId(rs.getString("PERIODO_ID"));
		objeto.setModalidadId(rs.getString("MODALIDAD_ID"));
		
		
		return objeto;
	}

}
