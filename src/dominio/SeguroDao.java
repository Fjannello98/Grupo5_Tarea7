package dominio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.mysql.jdbc.PreparedStatement;

public class SeguroDao implements Dao {
	public int encontrarProximoId() {
		int nro = 0;
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		try{
			conn = DriverManager.getConnection(host + dbName + sslParam, user, pass);
			Statement st = conn.createStatement();
			
			ResultSet rs = st.executeQuery("Select MAX(idSeguro) as maximo FROM seguros");
			if (rs.next()) {	
				nro = rs.getInt("maximo") + 1;
			}
			conn.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return nro;
	}
	
	 public boolean procedimientoInsertarSeguro(Seguro seguro)
	   {
		 try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		   Connection conn = null;
	       try {
	    	    conn = DriverManager.getConnection(host + dbName + sslParam, user, pass);
	            CallableStatement proc = conn.prepareCall(" CALL crearSeguro(?,?,?,?,?) ");
	            proc.setInt("pidSeguro", seguro.getIdSeguro());
	            proc.setString("pdescripcion", seguro.getDescripcion());
	            proc.setInt("pidTipo", seguro.getIdTipo());
	            proc.setFloat("pcostoContratacion", seguro.getCostoContratacion());
	            proc.setFloat("pcostoAsegurado", seguro.getCostoAsegurado());
	            proc.execute();            
	        } 
	       catch (Exception e) {                  
	            System.out.println(e);
	            return false;
	       }
	       return true;
	   }
	 
	 
	 
	 public Seguro obtenerSeguros(int id)
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Seguro seguro = new Seguro();
			Connection con = null;
			try{
				con = DriverManager.getConnection(host + dbName, user, pass);
				PreparedStatement miSentencia = (PreparedStatement) con.prepareStatement("Select * from usuario where Id = ?");
				miSentencia.setInt(1, id); //Cargo el ID recibido
				ResultSet resultado = miSentencia.executeQuery();
				resultado.next();
				
				seguro.setIdSeguro(resultado.getInt(1));
			    seguro.setDescripcion(resultado.getString(2));
			    seguro.setCostoContratacion(resultado.getFloat(3));
			    seguro.setCostoAsegurado(resultado.getFloat(3));
			    
			    con.close();
			}
			catch(Exception e)
			{
				System.out.println("Conexion fallida");
			}
			finally
			{
			}
			return seguro;
		}
	/* Crear proceso almacenado con este script
	 * 
	 DELIMITER $$
	 CREATE PROCEDURE `crearSeguro`(IN pidSeguro int, IN pdescripcion varchar(200), IN pidTipo int, IN pcostoContratacion decimal(10,0), IN pcostoAsegurado decimal(10,0))
	 BEGIN
	 INSERT INTO seguros(idSeguro,descripcion,idTipo,costoContratacion,costoAsegurado) VALUES (pidSeguro,pdescripcion,pidTipo,pcostoContratacion,pcostoAsegurado);
	 END
	$$ DELIMITER ;*/
}
