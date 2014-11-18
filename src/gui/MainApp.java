package gui;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sqldata.Cabin;
import sqldata.Item;
import sqldata.MailInterface;
import sqldata.Received;
import sqldata.Reservation;
import sqldata.Sql_data;
import sqldata.ItemType;
import sqldata.Forgotten;
import sqldata.Sent;

/**
 * This is the main class. This is where the windows are started, the main data lists are stored and also talks with all the controllers.
 */
public class MainApp extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;


    
    //the lists that comes from the mysql and contains the main data for the program
    private ObservableList<Cabin> cabinData = FXCollections.observableArrayList();
    private ObservableList<MailInterface> forgottenData = FXCollections.observableArrayList();
    private ObservableList<Reservation> reservationData = FXCollections.observableArrayList();
    private ObservableList<Item> itemData = FXCollections.observableArrayList();
    private ObservableList<ItemType> itemTypeData = FXCollections.observableArrayList();
    private ObservableList<Sent> outBox = FXCollections.observableArrayList();
    private static ArrayList<String> cabinNames = new ArrayList<String>();
    private Sql_data sql = new Sql_data();
    

    


    /**
     * The main constructor retrives all the data from the mysql and put them in observablelists
     */
    public MainApp() {

		try {
			cabinData = sql.getCabinData();
			forgottenData = sql.getForgottenData();
			forgottenData.addAll(sql.getDestroyedData());
			reservationData = sql.getReservationData();
			itemData = sql.getItemData();
			outBox = sql.getSentMessages();
			
            forgottenData.addAll(sql.getReceivedMessages());

		} catch (SQLException e) {
			//Extracts data from local backup if sql load fails
			System.out.println("Kunne ikke hente all data fra database. Har laster inn backup" + e);
			outBox = BackupHandler.readOutboxBackup();
	        itemData = BackupHandler.readItemBackup();
	        reservationData = BackupHandler.readReservationBackup();
	        cabinData = BackupHandler.readCabinBackup();
	        forgottenData = BackupHandler.readMailInterfaceBackup();
			e.printStackTrace();
		}

        reservationSorting();
		itemHandling();

        for(Cabin c : cabinData){
            cabinNames.add(c.getName());
        }


    }


    /**
     * Sorts the items into the correct cabins and itemtype.
     */
    public void itemHandling(){

        itemTypeData.clear();
        for(Cabin c : cabinData){
            c.getItemList().clear();
            for(Item i : itemData){
                if(c.getName().equals(i.getCabinName())){
                    c.addItem(i);
                }
            }
        }

        ArrayList<String> names = new ArrayList<String>();
        for(Item i : itemData){
            if(!names.contains(i.getItemName())){
                names.add(i.getItemName());
            }
        }

        for(String s : names){
            itemTypeData.add(new ItemType(s));
        }

        for(ItemType i : itemTypeData){
            for(Item j : itemData){
                if(i.getItemName().equals(j.getItemName())){
                    i.addItem(j);
                }
            }
        }
    }



    /**
     * Sorts the reservations to the correct cabins
     */

    public void reservationSorting(){
    	for(Cabin c : cabinData){
            c.getReservationList().clear();
			for(Reservation r : reservationData){

				if(c.getName().toLowerCase().equals(r.getName().toLowerCase())){
					c.addReservation(r);

				}
			}
		}
    }



    public ObservableList<Cabin> getCabinData() {
        return cabinData;
    }

    public ObservableList<MailInterface> getForgottenData(){
    	return forgottenData;
    }

    public ObservableList<Reservation> getReservationData(){
    	return reservationData;
    }
    public ObservableList<Item> getItemData(){
    	return itemData;
    }

    public ObservableList<ItemType> getItemTypeData(){
        return itemTypeData;
    }
    public ObservableList<Sent> getOutBox(){return outBox;}

    public static ArrayList<String> getCabinNames(){
        return cabinNames;
    }


    /**
     * Initializes the window of the program.
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
    	
    	
        this.primaryStage = primaryStage;

        this.primaryStage.setTitle("Koie Admin Client");
        this.primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/koiene_logo.gif")));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent event) {
		        saveAllDataToDatabase();
		    }
		});

        initRootLayout();
        showCabinOverview();
    }

   //initialiserer root layouten

    /**
     * initializes the root layout
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/Root.fxml"));
            rootLayout = (BorderPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //scene.getStylesheets().add(getClass().getResource("/css/gui.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * loads all the tabs into the program.
     */
    public void showCabinOverview() {
        try {
            // Load sqldata overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/Tabs.fxml"));
            AnchorPane cabinOverview = (AnchorPane) loader.load();

            // Setter cabinOverview i midtten av boarderpaneet
            rootLayout.setCenter(cabinOverview);
            
            //gir MainControllern til gang til mainApp
            MainController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * returns the primary stage
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Shows the item edit window
     * @param selected
     * @return
     */
    public boolean showItemEditDialog(Item selected) {
   	 try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/ItemEditor.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Item");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            ItemEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setChanges(selected);
   
            dialogStage.showAndWait();
            
            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
   }

    /**
     * shows the reservation edit window
     * @param res
     * @return
     */
    public boolean showReservationEditDialog(Reservation res) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/ReservationEditor.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Reservation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the sqldata into the controller.
            ReservationEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setChanges(res);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Saves the data to the database
     */
    public void saveAllDataToDatabase(){
    	BackupHandler.writeCabinBackup(this.cabinData);
    	BackupHandler.writeMailInterfaceBackup(this.forgottenData);
    	BackupHandler.writeReservationBackup(this.reservationData);
    	BackupHandler.writeItemBackup(this.itemData);
    	BackupHandler.writeOutboxBackup(this.outBox);
    	
    	sql.saveItemsToDatabase(this.itemData);
    	sql.saveReservationsAndUsers(this.reservationData);
    	sql.saveWoodToDatabase(this.cabinData);
    	sql.removeDestroyedAndForgottenFromDatabase(this.forgottenData);
    	sql.removeAndAddSentToDatabase(outBox);
    }
    
    @FXML
    private void saveChanges(){
        saveAllDataToDatabase();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}    

