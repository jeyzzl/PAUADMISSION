package adm.alumno.spring;

public class AdmFoto {
	
	private String folio;
	private byte[] foto;
	private String fecha;
	
	public AdmFoto(){
		folio		= "0";
		foto		= null;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
