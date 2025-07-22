package adm.alumno.spring;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdmPasoMapper implements RowMapper<AdmPaso> {
    @Override
    public AdmPaso mapRow(ResultSet rs, int rowNum) throws SQLException{
        AdmPaso objeto = new AdmPaso();

        objeto.setNombre(rs.getString("NOMBRE"));
        objeto.setOrden(rs.getString("ORDEN"));
        objeto.setCorto(rs.getString("CORTO"));
        objeto.setPasoId(rs.getString("PASO_ID"));

        return objeto;
    }

}
