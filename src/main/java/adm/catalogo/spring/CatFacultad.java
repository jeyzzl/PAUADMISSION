// Bean del Cat�logo de Facultades
package  adm.catalogo.spring;

public class CatFacultad{
	private String areaId;
	private String facultadId;
	private String titulo;
	private String nombreFacultad;
	private String codigoPersonal;
	
	public CatFacultad(){
		areaId 		= "";
		facultadId		= "";
		titulo			= "";
		nombreFacultad	= "";
		codigoPersonal	= "";
	}
	
	public String getAreaId(){
		return areaId;
	}
	
	public void setAreaId( String areaId){
		this.areaId = areaId;
	}
	
	public String getFacultadId(){
		return facultadId;
	}
	
	public void setFacultadId( String facultadId){
		this.facultadId = facultadId;
	}
	
	public String getTitulo(){
		return titulo;
	}
	
	public void setTitulo( String titulo){
		this.titulo = titulo;
	}
	
	public String getNombreFacultad(){
		return nombreFacultad;
	}
	
	public void setNombreFacultad( String nombreFacultad){
		this.nombreFacultad = nombreFacultad;
	}
	
	public String getCodigoPersonal(){
		return codigoPersonal;
	}
	
	public void setCodigoPersonal( String codigoPersonal){
		this.codigoPersonal = codigoPersonal;
	}
	
}