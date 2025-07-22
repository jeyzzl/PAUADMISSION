package adm.podium.spring;

public class Pregunta {
	
	private int id;
	private String pregunta;
	private String db;
	private String grado;
	private String dificultad;
	private int declaracionId;
	private int subAreaId;
	private int areaId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPregunta() {
		return pregunta;
	}
	public void setPregunta(String pregunta) {
		this.pregunta = pregunta;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	public int getDeclaracionId() {
		return declaracionId;
	}
	public void setDeclaracionId(int declaracionId) {
		this.declaracionId = declaracionId;
	}
	public int getSubAreaId() {
		return subAreaId;
	}
	public void setSubAreaId(int subAreaId) {
		this.subAreaId = subAreaId;
	}
	public int getAreaId() {
		return areaId;
	}
	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}
}
