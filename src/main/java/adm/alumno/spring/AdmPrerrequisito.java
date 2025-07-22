// Bean folio la tabla folio Adm_Contacto
package  adm.alumno.spring;

public class AdmPrerrequisito{	
	private String carreraId;
	private String folio;
	private String nombre;
		
	public AdmPrerrequisito(){
		carreraId 			= "";
		folio 			= "";
		nombre 			= ""; 
		
	}
	
	/**
	 * @return the carreraId
	 */
	public String getCarreraId() {
		return carreraId;
	}

	/**
	 * @param carreraId the carreraId to set
	 */
	public void setCarreraId(String carreraId) {
		this.carreraId = carreraId;
	}

	/**
	 * @return the folio
	 */
	public String getFolio() {
		return folio;
	}

	/**
	 * @param folio the folio to set
	 */
	public void setFolio(String folio) {
		this.folio = folio;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}		