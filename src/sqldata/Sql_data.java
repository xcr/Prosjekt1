package sqldata;
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


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

    private static String url = "jdbc:mysql://mysql.stud.ntnu.no/gabrielb_gruppe2";
    private static String username = "gabrielb_guest";
    private static String passwd = "guest";
    private static Connection connection = null;
    private static java.sql.Statement statement = null;
    private static ResultSet resultSet = null;
    private static ObservableList<Cabin> cabinList = null;
    private static ObservableList<Item> itemList = null;
    private ObservableList<Item> itemsOld;
    private ObservableList<Cabin> cabinsOld;
    private ObservableList<Destroyed> destroyedOld;
    private ObservableList<Forgotten> forgottenOld;


    public Sql_data(){

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

    public static void connect() throws SQLException {
        System.out.println("Connecting to SQLdatabase...");
        connection = DriverManager.getConnection(url, username, passwd);
        System.out.println("connection established");
    }

    /**
     * Closes the connection to the database, the resultset and the statement.
     */

    public static void closeConnection(){

        if(connection != null){

            try {
                System.out.println("Closing connection...");
                connection.close();

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
    private static ResultSet getTableInformation(String table){
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

    public static void updateSqlTable(String dbName, String columnName, String newValue, String columnIDname, String id  ){

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
     *  .
     * The observable List is being used by JAVAFX in the GUI.
     * <p>
     * @return an ObservableList which contains the cabins
     */

    public static  ObservableList<Cabin> getCabinData() throws SQLException{
        connect();
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
            finally{
                closeConnection();
            }
        }
        return cabinList;
    }
    /**
     * Retrieves the tableinformation for Item table in the sql database, and makes an array with Item objects.
     *  .
     * The observable List is being used by JAVAFX in the GUI.
     * <p>
     * @return an ObservableList which contains the Item objects.
     */

    public static ObservableList<Item> getItemData() throws SQLException{
        connect();
        ResultSet item = getTableInformation("Item");
        Item i;
        itemList = FXCollections.observableArrayList();
        if(connection != null){
            try {
                while(item.next()){
                    i = new Item(item.getString("cabinname"), item.getString("itemname"), item.getString("amount"), item.getString("inr"));
                    itemList.add(i);
                }
            } catch (SQLException e) {
                System.out.println("failed to retrieve data from table: Item in database");
                e.printStackTrace();
            }

            finally{
                closeConnection();
            }
        }
        return itemList;
    }

    /**
     * Retrieves the tableinformation for the Destroyed items in the sql database, and makes an array with Destroyed objects.
     *  .
     * The observable List is being used by JAVAFX in the GUI.
     * <p>
     * @return an ObservableList which contains the Destroyed objects.
     */

    public static ObservableList<MailInterface> getDestroyedData() throws SQLException{

        connect();

        ObservableList<MailInterface> destroyed =  FXCollections.observableArrayList();
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
            finally{
                closeConnection();
            }
        }
        return destroyed;
    }

    /**
     * Retrieves the tableinformation for the Forgotten items in the sql database, and makes an O with Forgotten objects.
     *  .
     * The observable List is being used by JAVAFX in the GUI.
     * <p>
     * @return an ObservableList which contains the Forgotten objects.
     */

    public static ObservableList<MailInterface> getForgottenData() throws SQLException{

        connect();
        ObservableList<MailInterface> forgotten = FXCollections.observableArrayList();
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
        finally{
            closeConnection();
        }
        return forgotten;
    }

    /**
     * Retrieves the tableinformation for the Reservations in the sql database, and makes an array with Reservation objects.
     *
     * The observable List is being used by JAVAFX in the GUI.
     * <p>
     * @return an ObservableList which contains the Reservation objects.
     */
    public static ObservableList<Reservation> getReservationData() throws SQLException{
        connect();
        ObservableList<Reservation> reservations = FXCollections.observableArrayList();
        Reservation r;
        ResultSet res = null;

        try {
            statement = connection.createStatement();
            res  = statement.executeQuery("SELECT * FROM Reservation, User WHERE Reservation.email = User.email");

            while(res.next()){
                r = new Reservation(res.getInt("rnr"), res.getString("cabinname"), res.getString("email"), res.getString("startdate"),
                        res.getString("enddate"), res.getString("firstname"), res.getString("lastname"));
                reservations.add(r);
            }
        } catch (SQLException e) {
            System.out.println("failed to retrieve reservationdata from resultSet in getReservationdata()");
            e.printStackTrace();
        }
        finally{
            closeConnection();
        }
        return reservations;
    }

    /**
     * Checks if the cabin has had any changes, and updates all the changes in the sql database.
     */
    public static void updateCabindata(String itemName, String value, String id) throws SQLException{

        updateSqlTable("Cabin", itemName, value, "cnr", id);

    }
    /**
     * Adds a new item to the database
     * @param cabinName name of the cabin
     * @param itemName name of the new item
     * @param amount amount of the new item
     * @throws SQLException
     */

    public static void addItemToDatabase(String cabinName, String itemName, String amount) throws SQLException{

        statement = connection.createStatement();
        statement.execute("INSERT INTO Item (cabinname, itemname, amount) VALUES "
                + "('" +cabinName+ "','" + itemName+ "','" + amount + "')" );

        System.out.println("Item Successfully added to database");
    }

    /**
     * Removes the item with specified ID from the sql database
     * @param id the id of the item that is going to be removed
     * @throws SQLException
     */

    public static void removeItemsFromDatabase(String id) throws SQLException{
        statement = connection.createStatement();
        statement.execute("DELETE FROM Item WHERE inr =" + id);
        closeConnection();
    }

    /**
     * Updates the item table in the sql database
     * <br>
     * @param cabinName new name of the cabin
     * @param itemName new itemname
     * @param amount new amount
     * @param id id of the table to be changed
     * @throws SQLException
     */

    public static void updateItemInDatabase(String cabinName, String itemName, String amount, String id) throws SQLException{

        java.sql.PreparedStatement update = connection.prepareStatement("UPDATE Item SET cabinname = ?, itemname = ?, amount = ? WHERE inr = ?");
        update.setString(1, cabinName);
        update.setString(2, itemName);
        update.setString(3, amount);
        update.setString(4, id);
        update.executeUpdate();

    }
    public static void addReservationToDatabase(String cabinName, String email, String startDate, String endDate) throws SQLException{

        java.sql.PreparedStatement add = connection.prepareStatement("INSERT INTO Reservation (cabinname, email, startdate, enddate) VALUES(?, ?, ?, ?)");
        add.setString(1, cabinName);
        add.setString(2, email);
        add.setString(3, startDate);
        add.setString(4, endDate);
        add.executeUpdate();
    }

    /**
     * Updates both the Reservation table, and the Person table in the sql database.
     * </p>
     * @param cabinName name of the cabin of the reservation
     * @param email the email of the user
     * @param startDate stardate for the reservation
     * @param endDate       enddate for the reservation
     * @param reservationId the INR id for the column to be updated
     * @param firstName     first name of the person
     * @param lastName lastname of the person
     * @param PersonId the PNR id for the column to be updated
     * @throws SQLException
     */

    public static void updateReservationInDatabase(String cabinName, String email, String startDate, String endDate, String reservationId, String firstName, String lastName, String PersonId) throws SQLException{

        java.sql.PreparedStatement updateRes = connection.prepareStatement("UPDATE Item SET cabinname = ?, email = ?, startdate = ?, enddate = ? WHERE inr = ?");
        updateRes.setString(1, cabinName);
        updateRes.setString(2, email);
        updateRes.setString(3, startDate);
        updateRes.setString(4, endDate);
        updateRes.setString(5, reservationId);
        updateRes.executeUpdate();
        System.out.println("Reservation Updated");
        java.sql.PreparedStatement updateUser = connection.prepareStatement("UPDATE User SET firstname = ?, lastname = ?, email = ? WHERE pnr = ?");
        updateUser.setString(1, firstName);
        updateUser.setString(2, lastName);
        updateUser.setString(3, PersonId);
        updateUser.executeUpdate();
        System.out.println("User Updated");

    }

    /**
     * Adds all the items to the database
     * <p>
     * @param items
     * @throws SQLException
     */
    public static void saveItemsToDatabase(ObservableList<Item> items) throws SQLException{

        statement = connection.createStatement();
        statement.execute("TRUNCATE TABLE Item");
        for(Item i : items){
            addItemToDatabase(i.getCabinName(), i.getItemName(), i.getAmount());
        }

        System.out.println("Successfully added all items to database");
    }

    public static void saveReservationsToDatabase(ObservableList<Reservation> reservations) throws SQLException{

        statement = connection.createStatement();
        statement.execute("TRUNCATE TABLE Reservation");
        for(Reservation i : reservations){
            addReservationToDatabase(i.getName(), i.getEmail(), i.getStartDate(), i.getEndDate());
        }
        System.out.println("Successfully added all reservationstodatabase");
    }

    public static void saveUsersToDatabase(ObservableList<Reservation> reservations) throws SQLException{

        statement = connection.createStatement();
        statement.execute("TRUNCATE TABLE User");
        for(Reservation r : reservations){
            java.sql.PreparedStatement add = connection.prepareStatement("INSERT INTO User (firstname, lastname, email) VALUES(?, ?, ?)");
            add.setString(1, r.getfirstname());
            add.setString(2, r.getlastname());
            add.setString(3, r.getEmail());
            add.executeUpdate();
        }
        System.out.println("Successfully added all users to database");

    }

    public static void saveWoodToDatabase(ObservableList<Cabin> cabins) throws SQLException{

        for(Cabin c : cabins){
            java.sql.PreparedStatement updateWood = connection.prepareStatement("UPDATE Cabin SET wood = ? WHERE cnr = ?");
            updateWood.setString(1, c.getWood());
            updateWood.setString(2, c.getId());
            updateWood.executeUpdate();
        }
        System.out.println("successfully updated woodstatus for all cabins");

    }
    public static void saveMessagesToDatabase(ObservableList<MailInterface> forgotten) throws SQLException{

        statement = connection.createStatement();
        statement.execute("TRUNCATE TABLE Forgotten");
        statement.execute("TRUNCATE TABLE Destroyed");
        java.sql.PreparedStatement add;
        for(MailInterface f : forgotten){
            if(f.getSubject().equals("Ødelagt")){
                add = connection.prepareStatement("INSERT INTO Destroyed (cabinname, description, email) VALUES(?, ?, ?)");
                add.setString(1, f.getName());
                add.setString(2, f.getDescription());
                add.setString(3, f.getEmail());
                add.executeUpdate();
                add.clearBatch();
            }
            else if(f.getSubject().equals("Glemt")){
                add = connection.prepareStatement("INSERT INTO Forgotten (cabinname, description, email) VALUES(?, ?, ?)");
                add.setString(1, f.getName());
                add.setString(2, f.getDescription());
                add.setString(3, f.getEmail());
                add.executeUpdate();
                add.clearBatch();
            }
        }
        System.out.println("successfully added Forgotten items to database");
        System.out.println("successfully added Destroyed items to database");
    }

}