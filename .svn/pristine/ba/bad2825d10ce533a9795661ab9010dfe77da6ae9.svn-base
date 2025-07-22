package adm.alumno.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AdmSocioecoMapper implements RowMapper<AdmSocioeco> {

	public AdmSocioeco mapRow(ResultSet rs, int arg1) throws SQLException {
		AdmSocioeco objeto = new AdmSocioeco();
		
		objeto.setCodigo(rs.getString("CODIGO"));
		objeto.setBanos(rs.getString("BANOS"));
		objeto.setAutos(rs.getString("AUTOS"));
		objeto.setInternet(rs.getString("INTERNET"));
		objeto.setPersonas(rs.getString("PERSONAS"));
		objeto.setCuartos(rs.getString("CUARTOS"));
		objeto.setIngreso(rs.getString("INGRESO"));
		objeto.setCapturado(rs.getString("CAPTURADO"));

		return objeto;
	}

}
