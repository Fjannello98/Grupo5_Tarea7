package dominio;

public interface Dao {
    String host = "jdbc:mysql://localhost:3306/";
	String user = "root";
	String pass = "";
	String dbName = "segurosgroup";
	String sslParam = "?useSSL=false";
	String driver = "com.mysql.jdbc.Driver";
}
