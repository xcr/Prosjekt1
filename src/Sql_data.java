import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Statement;

//tid brukt: ca. 4 timer

//stmt.execute("INSERT INTO login (username, passwd) VALUES ('SomeUsername', '123')");
public class Sql_data {

	private String url = null;
	private String username = null;
	private String passwd = null;
	private Connection connection = null;
	private java.sql.Statement statement = null;
	private ResultSet resultSet = null;

	public Sql_data(String sqlURL, String username,String passwd){

		this.url = sqlURL;
		this.username = username;
		this.passwd = passwd;

		try {
			System.out.println("Loading driver...");
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded!");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}
	}

	//Connecting to the SQL database with information given in the constructor. 
	//You have to use connect before any other functions! And remember to close the connection
	
	
	public void connect(){

		try {
			System.out.println("Connecting to SQLdatabase...");  
			connection = DriverManager.getConnection(url, username, passwd);
			System.out.println("connection established");	
		}

		catch (SQLException e) {
			throw new RuntimeException("Cannot connect to the database!", e);
		}
	}

	public void closeConnection(){

		try {
			System.out.println("Closing connection...");
			this.connection.close();
			resultSet.close();
			statement.close();
			} 
		
		catch (SQLException e) {
			System.out.println("connection failed" + e);
		}
		System.out.println("Connection closed!");
	}

	public ResultSet getTableInformation(String table, String column){

		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Could not create Statement in connection" + e);
			e.printStackTrace();
		}

		try {
			if(column != null){
				resultSet  = statement.executeQuery("SELECT * FROM " + table);
			}

			else{
				resultSet = statement.executeQuery("SELECT "+ column +" FROM " + table + ":");
			}

		} catch (SQLException e) {
			System.out.println("Failed to fetch data from table");
			e.printStackTrace();
		}
		
		return resultSet;
	}

	public static void main(String Args[]){

		String url = "jdbc:mysql://mysql.stud.ntnu.no/gabrielb_gruppe2";
		String username = "gabrielb_guest";
		String password = "guest";
		
		Sql_data sql = new Sql_data(url, username, password);
		sql.connect();
		sql.getTableInformation("Destroyed", "l");

		sql.closeConnection();
	}
}