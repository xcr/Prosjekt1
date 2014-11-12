package Cabin;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

//stmt.execute("INSERT INTO login (username, passwd) VALUES ('SomeUsername', '123')");
/**
 * Creates an object that can connect to an sql databse. 
 * <p></p>
 * 
 * @param  url  url to the database
 * @param  username the username used to connect to sql database
 * @param  passwd the password assosiated with the username
 * @return      an Sql_data instance
 */

public class Sql_data {

	private String url = null;
	private String username = null;
	private String passwd = null;
	private Connection connection = null;
	private java.sql.Statement statement = null;
	private ResultSet resultSet = null;
	private ObservableList<Cabin> cabinList = null; 

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

	/**
	 * Opens a connection to the database with the information given in the contructor.
	 *<p>Remember to close the connection after use</p>
	 */
	
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

	
	/**
	 * Closes the connection to the database, the resultset and the statement.
	 */
	
	public void closeConnection(){

		if(connection != null){

			try {
				System.out.println("Closing connection...");
				this.connection.close();

				if(resultSet != null){
					resultSet.close();
				}
				if(statement != null){
					statement.close();
				}
			} 

			catch (SQLException e) {
				System.out.println("Failed to close connections" + e);
			}
			System.out.println("Connection closed!");
		}
	}
	
	/**
	 * Retrieves information from the sql database.
	 * Will only get information if the connection is established with connect()
	 * <br>
	 * @param table the sql table you want to retrieve information from
	 * @return Returns a Resultset which is holding all the information
	 */
	public ResultSet getTableInformation(String table){

		if(connection != null){

			try {
				statement = connection.createStatement();
			} catch (SQLException e) {
				System.out.println("Could not create Statement in connection" + e);
				e.printStackTrace();
			}
			try {

				resultSet  = statement.executeQuery("SELECT * FROM " + table);

			} catch (SQLException e) {
				System.out.println("Failed to fetch data from table");
				e.printStackTrace();
			}
		}
		return resultSet;
	}
	
	/**
	 * Updates the specified table in the sql database with a new value.
	 * <p>
	 * @param dbName tablename of the table that is going to be changed
	 * @param columnName name of the column that is going to be changed
	 * @param newValue The new value 
	 * @param columnIDname the unique name of the ID for the column
	 * @param id The id unique id for the row
	 */

	public void updateSqlTable(String dbName, String columnName, String newValue, String columnIDname, String id  ){

		if(connection != null){
			try {
				statement = connection.createStatement();
				statement.execute("UPDATE " + dbName + " SET " + columnName + "=" + "'" + newValue +"'" + " WHERE " + columnIDname + "=" + id);

			} catch (SQLException e) {
				System.out.println("failed to write" + dbName + columnName + newValue);
				e.printStackTrace();
			}
		}
	}

	//Cabin database variables: cnr-name-bednumber-tablenumber-year-terrain-bike-trip-guitar-waffleiron-hunting-fishing-specialities-wood

	/**
	 * Retrieves the tableinformation for the Cabins in the sql database, and makes an array with Cabin objects.
	 * Only works if a connection has been established. 
	 * The observable List is being used by JAVAFX in the GUI.
	 * <p>
	 * @return an ObservableList which contains the cabins
	 */
	
	public ObservableList<Cabin> getCabinData(){
		ResultSet cabin = getTableInformation("Cabin");
		Cabin c;
		cabinList = FXCollections.observableArrayList();

		if(connection != null){
			try {
				while(cabin.next()){

					c = new Cabin(cabin.getInt("cnr"), cabin.getString("name"), cabin.getString("bednumber"), cabin.getString("tablenumber"),
							cabin.getInt("year"), cabin.getString("terrain"), cabin.getString("bike"), cabin.getString("trip"),
							cabin.getString("guitar"), cabin.getString("waffleiron"), cabin.getString("hunting"),cabin.getString("fishing"),
							cabin.getString("specialities"), cabin.getString("wood"));		
					cabinList.add(c);
				}
			} catch (SQLException e) {
				System.out.println("failed to retrieve CabinData from sql server" + e);
			}	
		}
		return cabinList;
	}
	
	/**
	 * Retrieves the tableinformation for the Destroyed items in the sql database, and makes an array with Destroyed objects.
	 * Only works if a connection has been established. 
	 * The observable List is being used by JAVAFX in the GUI.
	 * <p>
	 * @return an ObservableList which contains the Destroyed objects.
	 */
	
	public ObservableList<Destroyed> getDestroyedData(){

		ObservableList<Destroyed> destroyed =  FXCollections.observableArrayList();
		Destroyed d;

		if(connection != null){

			ResultSet destroyedItems = getTableInformation("Destroyed");

			try {
				while(destroyedItems.next()){
					d = new Destroyed(destroyedItems.getInt("dnr"), destroyedItems.getString("cabinname"), destroyedItems.getString("description")
							, destroyedItems.getString("email"));
					destroyed.add(d);
				}
			} catch (SQLException e) {
				System.out.println("Failed to read from ResultSet");
				e.printStackTrace();
			}
		}
		return destroyed;
	}
	
	/**
	 * Retrieves the tableinformation for the Forgotten items in the sql database, and makes an array with Forgotten objects.
	 * Only works if a connection has been established. 
	 * The observable List is being used by JAVAFX in the GUI.
	 * <p>
	 * @return an ObservableList which contains the Forgotten objects.
	 */

	public ObservableList<Forgotten> getForgottenData(){
		ObservableList<Forgotten> forgotten = FXCollections.observableArrayList();
		Forgotten f;
		ResultSet fi = getTableInformation("Forgotten");

		try {
			while(fi.next()){
				f = new Forgotten(fi.getInt("fnr"), fi.getString("cabinname"), fi.getString("description"), fi.getString("email"));
				forgotten.add(f);
			}
		} catch (SQLException e) {
			System.out.println("Failed to retrieve forgotten items from ResultSet");
			e.printStackTrace();
		}
		return forgotten;
	}

	/**
	 * Retrieves the tableinformation for the Reservations in the sql database, and makes an array with Reservation objects.
	 * Only works if a connection has been established. 
	 * The observable List is being used by JAVAFX in the GUI.
	 * <p>
	 * @return an ObservableList which contains the Reservation objects.
	 */
	public ObservableList<Reservation> getReservationData(){
		ObservableList<Reservation> reservations = FXCollections.observableArrayList();
		Reservation r;
		ResultSet res = null;

		try {
			statement = connection.createStatement();
			res  = statement.executeQuery("SELECT * FROM Reservation, User WHERE Reservation.email = User.email");
			
			while(res.next()){
				r = new Reservation(res.getInt("rnr"), res.getString("cabinname"), res.getString("email"), res.getString("startdate"),
						res.getString("enddate"), res.getString("firstname"));
				reservations.add(r);
			}
		} catch (SQLException e) {
			System.out.println("failed to retrieve reservationdata from resultSet in getReservationdata()");
			e.printStackTrace();
		}
		return reservations;
	}

	/**
	 * Checks if the cabin has had any changes, and updates all the changes in the sql database.
	 */
	public void updateCabindata(){
		//checks if the cabinlist is not null to ensure that getCabindata() has been used first 
		if(cabinList != null){
			HashMap<String, String> temp = new HashMap<String, String>();
			for (Cabin cabin : cabinList) {
				temp = cabin.getChangedItems();
				for(Map.Entry<String, String> e : temp.entrySet()){
					updateSqlTable("Cabin", e.getKey(), e.getValue(), "cnr", cabin.getId());
				}
			}
		}
		else{
			System.out.println("Can not updateCabinList when cabinList is empty. Use getCabinData() first.");
		}
	}

	public void updateReservationData(){
		
	}
	
}