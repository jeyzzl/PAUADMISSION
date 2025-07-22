// Bean del Cat�logo de Estados
package  adm.catalogo.spring;

public class CatEstado{
	private String paisId;
	private String estadoId;
	private String nombreEstado;
	
	public CatEstado(){
		paisId 		= "";
		estadoId 		= "";
		nombreEstado	= "";
	}
	
	public String getPaisId(){
		return paisId;
	}
	
	public void setPaisId( String paisId){
		this.paisId = paisId;
	}
	
	public String getEstadoId(){
		return estadoId;
	}
	
	public void setEstadoId( String estadoId){
		this.estadoId = estadoId;
	}
	
	public String getNombreEstado(){
		return nombreEstado;
	}
	
	public void setNombreEstado( String nombreEstado){
		this.nombreEstado = nombreEstado;
	}
	
}