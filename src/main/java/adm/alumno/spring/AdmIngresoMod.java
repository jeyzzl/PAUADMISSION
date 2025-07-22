package adm.alumno.spring;

public class AdmIngresoMod {
	private String periodoId;
	private String modalidadId;

	
	public AdmIngresoMod(){
		periodoId 		= "0";
		modalidadId	 	= "0";
	}
	
	public String getPeriodoId() {
		return periodoId;
	}

	public void setPeriodoId(String periodoId) {
		this.periodoId = periodoId;
	}
	
	public String getModalidadId() {
		return modalidadId;
	}
	
	public void setModalidadId(String modalidadId) {
		this.modalidadId = modalidadId;
	}

}