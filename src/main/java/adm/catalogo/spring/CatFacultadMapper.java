package adm.catalogo.spring;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CatFacultadMapper implements RowMapper<CatFacultad> {

	public CatFacultad mapRow(ResultSet rs, int arg1) throws SQLException {
		CatFacultad objeto = new CatFacultad();
		
		objeto.setAreaId(rs.getString("AREA_ID"));
		objeto.setFacultadId(rs.getString("FACULTAD_ID"));
		objeto.setTitulo(rs.getString("TITULO"));
		objeto.setNombreFacultad(rs.getString("NOMBRE_FACULTAD"));
		objeto.setCodigoPersonal(rs.getString("CODIGO_PERSONAL"));
		
		return objeto;
	}

}
