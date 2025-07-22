package adm.podium.spring;

public class Area {
	
	private int id;
	private String nombre;
	private int tiempo;
	private int minimoBuenas;
	private int puntos;
	private int cantidadPreguntas;
	
	public Area() {
		nombre 				= "";
		tiempo 				= 0;
		minimoBuenas 		= 0;
		puntos 				= 0;
		cantidadPreguntas 	= 0;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public int getMinimoBuenas() {
		return minimoBuenas;
	}

	public void setMinimoBuenas(int minimoBuenas) {
		this.minimoBuenas = minimoBuenas;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public int getCantidadPreguntas() {
		return cantidadPreguntas;
	}

	public void setCantidadPreguntas(int cantidadPreguntas) {
		this.cantidadPreguntas = cantidadPreguntas;
	}
}
