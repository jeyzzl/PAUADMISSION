// Clase para conectarse a la BD
package adm.conecta;
import java.sql.*;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import javax.naming.Context;

public class Conectar{
	
	// Coneccion para Oracle con el usuario Enoc
	public Connection conEnoc() throws SQLException{
		Connection conn = null;
		try{			
			//conn = enoc.getConnection();			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.209.247:1521:XE", "enoc", "caminacondios");					
		}catch(Exception e) {
			System.out.println("De plano no me pude conectar jajaja...");
			e.printStackTrace();
		}	
		return conn;
	}
		
	// Coneccion para Oracle con el usuario Enoc ( Incluye Rigel y Oreb )
	public Connection conSalomon( String servidor){
		Connection conn = null;
		try{
			
			// EL SERVIDOR RIGEL ES EL DE PRODUCCION Y EL OREB ES EL DE PRUEBAS			
		 	if (servidor.equals("OREB")){
		 		Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.254.14:1521:ora1", "salomon", "elsabio");
			}else if (servidor.equals("RIGEL")){				
				Context initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				DataSource ds = (DataSource)envContext.lookup("jdbc/admision");
				conn = ds.getConnection();
				//System.out.println("Coneccion pool..");
			}			
		}catch(Exception ex){
			System.out.println("Error - aca.conectar.Conectar|conection conSalomon|Pool:"+ex);
			try {
				//System.out.println("Coneccion directa...");
		 		Class.forName("oracle.jdbc.driver.OracleDriver");
		 		if (servidor.equals("RIGEL")) {
		 			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.254.25:1521:ora1", "salomon", "elsabio");
		 		} else {
		 			conn = DriverManager.getConnection("jdbc:oracle:thin:@172.16.254.14:1521:ora1", "salomon", "elsabio");
		 		}

			} catch(Exception e) {
				System.out.println("De plano no me pude conectar jajaja...");
				e.printStackTrace();
			}
		}
		return conn;
	}	
	
	// Desconectar Oracle usuario Enoc
	public void desSalomon(Connection Conn){
		try{			
			Conn.close();
		}catch(Exception ex){
			System.out.println("Error - aca.conectar.Conectar|desSalomon|:"+ex);
		}
	}
	
	
	// Coneccion para Postgresql
	public Connection conPostgres(){
		Connection Conn = null;
		try{
			//System.out.println("Coneccion directa postgres...");
			Class.forName("org.postgresql.Driver");
			Conn = DriverManager.getConnection("jdbc:postgresql://172.16.209.247:5432/academico","postgres","ptstann69");
		}catch(Exception e){
			System.out.println("No es posible conectarse... Error - aca.conectar.Conectar|conPostgres|:");
			e.printStackTrace();
		}
		return Conn;
	}	
	
	// Desconectar Oracle
	public void desPostgres(Connection Conn){
		try{			
			Conn.close();
		}catch(Exception ex){
			System.out.println("Error - aca.conectar.Conectar|desPostgres|:"+ex);
		}
	}
}