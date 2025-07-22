package adm.catalogo.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CatCarreraMapper implements RowMapper<CatCarrera> {

	public CatCarrera mapRow(ResultSet rs, int arg1) throws SQLException {
		CatCarrera objeto = new CatCarrera();
		
		objeto.setFacultadId(rs.getString("FACULTAD_ID"));
		objeto.setCarreraId(rs.getString("CARRERA_ID"));
		objeto.setNivelId(rs.getString("NIVEL_ID"));
		objeto.setTitulo(rs.getString("TITULO"));
		objeto.setNombreCarrera(rs.getString("NOMBRE_CARRERA"));
		objeto.setNombreCorto(rs.getString("NOMBRE_CORTO"));
		objeto.setCcostoId(rs.getString("CCOSTO_ID"));
		objeto.setCodigoPersonal(rs.getString("CODIGO_PERSONAL"));
		
		return objeto;
	}

}
