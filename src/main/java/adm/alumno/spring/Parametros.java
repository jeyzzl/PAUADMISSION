package adm.alumno.spring;

public class Parametros {
	private String id;
	private String institucion;
	private String certificados;
	private String constancias;
	private String cardex;	

	public Parametros(){
		id				= "1";
		institucion		= "-";
		certificados	= "-";
		constancias		= "-";
		cardex			= "-";		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getCertificados() {
		return certificados;
	}

	public void setCertificados(String certificados) {
		this.certificados = certificados;
	}

	public String getConstancias() {
		return constancias;
	}

	public void setConstancias(String constancias) {
		this.constancias = constancias;
	}

	public String getCardex() {
		return cardex;
	}

	public void setCardex(String cardex) {
		this.cardex = cardex;
	}
	
}