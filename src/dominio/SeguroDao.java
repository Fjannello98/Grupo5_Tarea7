package dominio;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
	 
	 
	 
	 public ArrayList<Seguro> obtenerSeguros(){
		 
		 
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<Seguro> lista = new ArrayList<Seguro>();
			Connection con = null;
			try{
				con = DriverManager.getConnection(host + dbName, user, pass);
				Statement st = con.createStatement();
				
				ResultSet rs = st.executeQuery("SELECT idSeguro, descripcion, costoContratacion, costoAsegurado FROM seguros");
				
				while(rs.next()) {
					
					Seguro seguro = new Seguro();
					seguro.setIdSeguro(rs.getInt("idSeguro"));
					seguro.setDescripcion(rs.getString("descripcion"));
					seguro.setCostoContratacion(rs.getFloat("costoContratacion"));
					seguro.setCostoAsegurado(rs.getFloat("costoAsegurado"));
						
				
				lista.add(seguro);
					
					
				}
				
			}
			catch(Exception e)
			{
				System.out.println("Conexion fallida");
			}
			finally
			{
			}
			return lista;
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
