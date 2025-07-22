package adm.podium.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ExamenAreaPreguntaDao {

	
	@Autowired
	@Qualifier("jdbcExa")
	private JdbcTemplate jdbcExamen;
	
	public List<Integer> listaExamenAreaPreguntaId(int examenId, int examenAreaId) {
		List<Integer> lista = new ArrayList<Integer>();		
		try {
			String query = "SELECT PREGUNTA_ID FROM EXA.EXAMEN_AREA_PREGUNTA  WHERE EXAMEN_ID = ? AND EXAMEN_AREA_ID = ? ORDER BY ID";
			lista = jdbcExamen.queryForList(query, Integer.class, new Object[]{examenId,examenAreaId});				
		} catch (Exception e) {
			System.out.println("Error -  adm.podium.spring.ExamenAreaPregunta|listaExamenAreaPreguntaId| "+e);
		}
		
		return lista;
	}
	
	public List<ExamenAreaPregunta> listaExamenAreaPregunta(int examenId, int examenAreaId) {
		List<ExamenAreaPregunta> lista = new ArrayList<ExamenAreaPregunta>();		
		try {
			String query = "SELECT * FROM EXA.EXAMEN_AREA_PREGUNTA WHERE EXAMEN_ID = ? AND EXAMEN_AREA_ID = ? ORDER BY ID";
			lista = jdbcExamen.query(query, new ExamenAreaPreguntaMapper(), new Object[]{examenId,examenAreaId});	
		} catch (Exception e) {
			System.out.println("ERROR : ExamenAreaPreguntaDao | listaExamenAreaPregunta | "+e);
		}
		
		return lista;
	}

	public ExamenAreaPregunta buscaExamenAreaPregunta(int examenId, int examenAreaId, int preguntaId) {
		ExamenAreaPregunta objeto = new ExamenAreaPregunta();		
		try {
			String query = "SELECT * FROM EXA.EXAMEN_AREA_PREGUNTA WHERE EXAMEN_ID = ? AND EXAMEN_AREA_ID = ? AND PREGUNTA_ID = ?";
			jdbcExamen.query(query, new ExamenAreaPreguntaMapper(), new Object[]{examenId,examenAreaId,preguntaId});
		} catch (Exception e) {
			System.out.println("ERROR : ExamenAreaPreguntaDao | buscaExamenAreaPregunta | "+e);
		}
		
		return objeto;
	}
	
	public boolean existeTiempoExamenAreaPregunta(int examenId, int examenAreaId, int preguntaId) {
		boolean ok = false;		
		try {
			String query = "SELECT COUNT(*) FROM EXA.EXAMEN_AREA_PREGUNTA WHERE EXAMEN_ID = ? AND EXAMEN_AREA_ID = ? AND PREGUNTA_ID = ? AND TIEMPO != NULL";
			if(jdbcExamen.queryForObject(query, Integer.class, new Object[]{examenId,examenAreaId,preguntaId}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : ExamenAreaPreguntaDao | existeTiempoExamenAreaPregunta | "+e);
		}
		
		return ok;
	}
	
	public boolean iniciarTiempoExamenAreaPregunta(int examenId, int examenAreaId, int preguntaId) {
		boolean ok = false;				
		try {
			String query = "UPDATE EXA.EXAMEN_AREA_PREGUNTA SET INICIO = NOW() WHERE EXAMEN_ID = ? AND EXAMEN_AREA_ID = ? AND PREGUNTA_ID = ?";
			if(jdbcExamen.update(query, new Object[]{examenId,examenAreaId,preguntaId}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : ExamenAreaPreguntaDao | iniciarTiempoExamenAreaPregunta | "+e);
		}
		
		return ok;
	}
	
	public boolean creaExamenAreaPregunta(int examenId, int examenAreaId, int preguntaId) {
		boolean ok = false;
		String query = "INSERT INTO EXA.EXAMEN_AREA_PREGUNTA(EXAMEN_ID,EXAMEN_AREA_ID,PREGUNTA_ID,RESPUESTA_ID) VALUES(?,?,?,-1)";
		
		try {
			if(jdbcExamen.update(query, new Object[]{examenId,examenAreaId,preguntaId}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : ExamenAreaPreguntaDao | creaExamenAreaPregunta | "+e);
		}
		
		return ok;
	}
	
	
	public boolean grabarRespuestaExamenAreaPregunta(int respuestaId, int examenId, int examenAreaId, int preguntaId) {
		boolean ok		= false;
		String tiempo 	= "";		
		try {
			String query 	= "UPDATE EXA.EXAMEN_AREA_PREGUNTA SET RESPUESTA_ID = ?, TERMINO = NOW() WHERE EXAMEN_ID = ? AND EXAMEN_AREA_ID = ? AND PREGUNTA_ID = ?";
			if(jdbcExamen.update(query, new Object[]{respuestaId,examenId,examenAreaId,preguntaId}) >= 1){
				query = "SELECT (TERMINO-INICIO) AS TIEMPO FROM EXA.EXAMEN_AREA_PREGUNTA WHERE EXAMEN_ID = ? AND EXAMEN_AREA_ID = ? AND PREGUNTA_ID = ?";
				tiempo = jdbcExamen.queryForObject(query, String.class, new Object[]{examenId,examenAreaId,preguntaId});
				
				String[]arrTiempo = tiempo.split(":");
				
				double horas 	= Double.parseDouble(arrTiempo[0]);
				double minutos 	= Double.parseDouble(arrTiempo[1]);
				double segundos = Double.parseDouble(arrTiempo[2]);
				
				double tiempoTotal		= 0;
				
				if(horas > 0) {
					tiempoTotal = (horas / 60) / 60;
				}
				if(minutos > 0) {
					tiempoTotal = tiempoTotal + (minutos / 60);
				}
					
				//**** GRABO EL TIEMPO EN SEGUNDOS ****//
				tiempoTotal = tiempoTotal + segundos;

				query = "UPDATE EXA.EXAMEN_AREA_PREGUNTA SET TIEMPO = ? WHERE EXAMEN_ID = ? AND EXAMEN_AREA_ID = ? AND PREGUNTA_ID = ?";
				
				jdbcExamen.update(query, new Object[]{tiempoTotal,examenId,examenAreaId,preguntaId});

				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : ExamenAreaPreguntaDao | grabarRespuestaExamenAreaPregunta | "+e);
		}
		
		return ok;
	}
	
	public boolean existeExamenAreaPregunta(int examenId, int examenAreaId) {
		boolean ok = false;		
		try {
			String query = "SELECT COUNT(*) FROM EXA.EXAMEN_AREA_PREGUNTA WHERE EXAMEN_ID = ? AND EXAMEN_AREA_ID = ?";
			if(jdbcExamen.queryForObject(query, Integer.class, new Object[]{examenId,examenAreaId}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : ExamenAreaPreguntaDao | existeExamenAreaPregunta | "+e);
		}
		
		return ok;
	}
	
	public boolean terminarExamenAreaPregunta(int examenId, int areaId) {
		boolean ok = false;		
		try {
			String query = "UPDATE EXA.EXAMEN_AREA_PREGUNTA SET RESPUESTA_ID = 0, TIEMPO = '0' WHERE EXAMEN_ID = ? AND EXAMEN_AREA_ID = ? AND RESPUESTA_ID = -1";
			if(jdbcExamen.update(query, new Object[]{areaId,examenId}) >= 1){
				ok = true;
			}
		} catch (Exception e) {
			System.out.println("ERROR : ExamenAreaPreguntaDao | terminarExamenAreaPregunta | "+e);
		}		
		return ok;
	}
}
