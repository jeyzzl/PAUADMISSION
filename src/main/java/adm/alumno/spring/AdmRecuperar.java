package adm.alumno.spring;

public class AdmRecuperar {
	private String clave;
	private String correo;	
	private String fecha;

	public AdmRecuperar() {
		clave 	= "";
		correo 	= "";
		fecha	= "";
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
