package adm.podium.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class AspiranteMapper implements RowMapper<Aspirante>{
	
	public Aspirante mapRow(ResultSet rs, int arg1) throws SQLException {	
		
		Aspirante objeto = new Aspirante();
		
		objeto.setId(rs.getInt("ID"));
		objeto.setFolio(rs.getInt("FOLIO"));
		objeto.setFecha(rs.getDate("FECHA"));			
		objeto.setNombre(rs.getString("NOMBRE"));
		objeto.setEmail(rs.getString("EMAIL"));
		objeto.setPassword(rs.getString("PASSWORD"));
		objeto.setGenero(rs.getString("GENERO"));
		objeto.setEdad(rs.getString("EDAD"));
		objeto.setNacionalidadId(rs.getInt("NACIONALIDAD_ID"));
		objeto.setCarreraId(rs.getInt("CARRERA_ID"));	
		objeto.setGrado(rs.getString("GRADO"));
		objeto.setModalidad(rs.getString("MODALIDAD"));
		objeto.setActivo(rs.getBoolean("ACTIVO"));	
		
		return objeto;
	}
}