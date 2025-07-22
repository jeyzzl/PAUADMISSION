package adm.podium.spring;

import java.util.Date;

public class Aspirante {
	
	private int id;
	private int folio;
	private Date fecha;
	private String nombre;
	private String email;
	private String password;
	private String genero;
	private String edad;
	private int nacionalidadId;
	private int carreraId;
	private String grado;
	private String modalidad;
	private boolean activo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEdad() {
		return edad;
	}
	public void setEdad(String edad) {
		this.edad = edad;
	}
	public int getNacionalidadId() {
		return nacionalidadId;
	}
	public void setNacionalidadId(int nacionalidadId) {
		this.nacionalidadId = nacionalidadId;
	}
	public int getCarreraId() {
		return carreraId;
	}
	public void setCarreraId(int carreraId) {
		this.carreraId = carreraId;
	}
	public String getGrado() {
		return grado;
	}
	public void setGrado(String grado) {
		this.grado = grado;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
}
