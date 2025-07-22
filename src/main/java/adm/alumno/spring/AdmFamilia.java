package adm.alumno.spring;

public class AdmFamilia {
	private String codigo;
	private String hermanos;
	private String ubicacion;
	private String personas;
	private String capturado;
		
	public AdmFamilia(){
		codigo			= "0";
		hermanos 		= "0";
		ubicacion 		= "0";
		personas		= "0";
		capturado		= "A";
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getHermanos() {
		return hermanos;
	}

	public void setHermanos(String hermanos) {
		this.hermanos = hermanos;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getPersonas() {
		return personas;
	}

	public void setPersonas(String personas) {
		this.personas = personas;
	}

	public String getCapturado() {
		return capturado;
	}

	public void setCapturado(String capturado) {
		this.capturado = capturado;
	}
	
}