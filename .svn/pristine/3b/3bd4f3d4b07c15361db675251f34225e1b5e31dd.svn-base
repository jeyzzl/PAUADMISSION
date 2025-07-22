package adm.alumno.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class AdmPasoDao {

    @Autowired
    @Qualifier("jdbcSalomon")
    private JdbcTemplate salomonJdbc;

    public boolean insertReg(AdmPaso paso){
        boolean ok = false;
        try{
            String comando ="INSERT INTO SALOMON.ADM_PASO(NOMBRE, ORDEN, CORTO, PASO_ID)"
                    + " VALUES( ?, TO_NUMBER(?,'99'), ?, TO_NUMBER(?,'99'))";
            Object[] parametros = new Object[] { paso.getNombre(), paso.getOrden(), paso.getCorto(), paso.getPasoId() };
            if (salomonJdbc.update(comando,parametros) >= 1){
                ok = true;
            }
        }catch(Exception ex){
            System.out.println("Error - aca.admision.spring.AdmPasoDao|insertReg|:"+ex);
        }

        return ok;
    }

    public boolean deleteReg(String pasoId, String nombre){
        boolean ok = false;
        try{
            String comando = "DELETE FROM SALOMON.ADM_PASO WHERE PASO_ID = TO_NUMBER(?,'99') AND NOMBRE = ?";
            Object[] parametros = new Object[] { pasoId, nombre };
            if (salomonJdbc.update(comando,parametros)==1){
                ok = true;
            }
        }catch(Exception ex){
            System.out.println("Error - aca.admision.spring.AdmPasoDao|deleteReg|:"+ex);
//            ok = false;
        }

        return ok;
    }

    public AdmPaso mapeaRegId(String pasoId) {
        AdmPaso admPaso = new AdmPaso();

        try {
            String comando = "SELECT NOMBRE, ORDEN, CORTO, PASO_ID"
                    + " FROM SALOMON.ADM_PASO"
                    + " WHERE PASO_ID = TO_NUMBER(?, '99')"
                    + " AND NOMBRE = ?";
            Object[] parametros = new Object[] { pasoId };
            admPaso = salomonJdbc.queryForObject(comando, new AdmPasoMapper(), parametros);
        } catch (Exception ex) {
            System.out.println("Error - aca.admision.spring.AdmPasosDao|mapeaRegId|:"+ex);
        }

        return admPaso;
    }

    public boolean existeReg(String pasoId, String nombre) {
        boolean ok 	= false;
        try{
            String comando = "SELECT COUNT(PASO_ID) FROM SALOMON.ADM_PASO WHERE PASO_ID = TO_NUMBER(?,'99') AND NOMBRE = ?";
            Object[] parametros = new Object[] {pasoId, nombre};
            if (salomonJdbc.queryForObject(comando, Integer.class, parametros) >= 1){
                ok = true;
            }
        }catch(Exception ex){
            System.out.println("Error - aca.admision.spring.AdmPasoDao|existeReg|:"+ex);
        }
        return ok;
    }

    public List<AdmPaso> getAll(String orden) {
        List<AdmPaso> lista = new ArrayList<AdmPaso>();

        try{
            String comando = "SELECT NOMBRE, ORDEN, CORTO, PASO_ID"
                    + " FROM SALOMON.ADM_PASO " + orden;
            lista = salomonJdbc.query(comando, new AdmPasoMapper());
        }catch(Exception ex){
            System.out.println("Error - aca.admision.spring.AdmPasoDao|getAll|:"+ex);
        }

        return lista;
    }

    public HashMap<String, AdmPaso> mapaPaso(){
        HashMap<String, AdmPaso> mapa = new HashMap<String, AdmPaso>();
        List<AdmPaso> lista = new ArrayList<AdmPaso>();

        try {
            String comando = "SELECT NOMBRE, ORDEN, CORTO, PASO_ID"
                    + " FROM SALOMON.ADM_PASO";
            lista = salomonJdbc.query(comando, new AdmPasoMapper());
            for (AdmPaso aca : lista ){
                mapa.put(aca.getPasoId(), aca);
            }
        }catch(Exception ex) {
            System.out.println("Error - aca.admision.spring.AdmPasoDao|mapaPasos|:"+ex);
        }
        return mapa;
    }

}
