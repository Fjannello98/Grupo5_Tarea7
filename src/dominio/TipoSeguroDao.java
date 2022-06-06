package dominio;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class TipoSeguroDao implements Dao {

		
		public ArrayList<TipoSeguro> obtenerTiposSeguro() {

			try {
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ArrayList<TipoSeguro> lista = new ArrayList<TipoSeguro>();
			Connection conn = null;
			try{
				conn = DriverManager.getConnection(host + dbName + sslParam, user, pass);
				Statement st = conn.createStatement();
				
				ResultSet rs = st.executeQuery("Select idTipo,descripcion FROM tiposeguros");
				
				while(rs.next()){
					
					TipoSeguro ts = new TipoSeguro();
					ts.setId(rs.getInt("idTipo"));
					ts.setDescripcion(rs.getString("descripcion"));
					lista.add(ts);
				}
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
			
			}
			
			return lista;
		}

}
