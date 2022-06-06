package dominio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

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
				System.out.println("Entro");
				nro = rs.getInt("maximo") + 1;
			}
			conn.close();
		} catch(Exception e){
			e.printStackTrace();
		}
		return nro;
	}
}
