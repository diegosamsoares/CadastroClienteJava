package servlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() {//metodo com retorno de um objeto do tipo Connection que é uma conexão
	     try {
	    	
	    	  DriverManager.registerDriver(new com.mysql.jdbc.Driver()); //Essa linha foi a diferença
	         return DriverManager.getConnection( "jdbc:mysql://localhost/fj21", "root", "");
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
	
}
