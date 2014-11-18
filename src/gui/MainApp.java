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

public class MainApp extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;

/*
 * 2. Administratorer i koiestyret skal kunne ta ut status for utstyr p� en eller alle koiene, samt legge inn n�r nytt utstyr er innkj�pt.
3. Administrator i koiestyret skal kunne ta ut status for ved p� en eller alle koiene, og f� anslag p� hvor lenge det vil vare f�r det er n�dvendig med veddugnad.
4. Administrator i koiestyret skal kunne melde til brukere som har reservert koie at det er utstyr som m� fraktes til koia.
5. Administrator i koiestyret skal kunne se et kart hvor koiene er plottet inn, og hvor klikk p� en koie gir administrativ informasjon.
 
 toDo:
 *lage items klasse
 *integrer item i gui
 *lag kode som h�ndterer logikken med item amount osv
 *add editorer for de gjennv�rende feltene.
 *fix kart markers
 *lag wood algorithme
 *add threading til email 
 *flytt ting fra about cabin til items(i mysql)
 *add destroyed
 *fix wood css
 *add map button til coie status
 *fix wood per koie
 *fix reservasjonsbuttons
 *fix dato felt greia for reservasjoner
 *fix set conditions for datoer
 *fix s� datoan st�r riktig vei
 *fix p� alle tabellene s� de ikke har overfl�dige felt osv
 *legg til subject på mottatte meldinger
 *embed kart(lav prio)
 * fix item knapper
 * fix reservasjonsknapper
 center tables
 *sort greia til wood
 ordentlig ved algorithme
 fix date picker med add remove og edit
 lagre meldinger
 hente emails fra gmail(denne kan noen andre gj�re)
 lag undo(hotkey?)
 save og load funksjoner(david)
 sette cabin reservation og cabin info til samme colonne listener(lav prio)
 add about page(lav prio)
 adde masse throws osv(noe av det siste som kan gj�res)
 comment all koden
 *fix email så email settes i til feltet
 fix så man ikke kan adde two instanser av samme item på en koie(lavprio)
 add så man kan slette destoryed/forgotten
 fix wood average level labeln

 */
    
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
	/*
		//test data
	reservationData.add(new Reservation(null, null,"Heinfjordstua","bmail@rofl.copter","2014-11-02","2014-11-10", "Magnus","Blomlie"));
	reservationData.add(new Reservation(null, null,"Fosenkoia","amail@rofl.copter","2014-11-01","2014-11-10", "David","Bakke"));
	reservationData.add(new Reservation(null, null, "Heinfjordstua","cmail@rofl.copter","2014-11-03","2014-11-10", "Eirik","Bertelsen"));
	reservationData.add(new Reservation(null, null, "Fosenkoia","dmail@rofl.copter","2014-11-04","2014-10-07", "Gabriel","Et eller annet"));
		reservationData.add(new Reservation(null, null, "Fosenkoia","email@rofl.copter","2014-11-05","2014-11-28", "Ola","Nordmann"));
		reservationData.add(new Reservation(null, null, "Fosenkoia","fmail@rofl.copter","2014-11-06","2014-11-08", "Ola","Nordmann"));
	reservationData.add(new Reservation(null, null, "Fosenkoia","gmail@rofl.copter","2014-11-07","2014-11-09", "Ola","Nordmann"));
		reservationData.add(new Reservation(null, null, "Fosenkoia","hmail@rofl.copter","2014-11-08","2014-11-10", "Ola","Nordmann"));
        outBox.add(new Sent("espen.d.hansen@gmail.com", "Frakting av utstyr til koia","Hei, \n\n vi har noe utstyr som skulle vært fraktet til koia som vi hopet at du kunne ta med deg \n\n Hilsen Ntnu koie systemet"));
        outBox.add(new Sent("morten.hansen@gmail.com", "Hei Morten! vi lurte på om","HEllo!, we bought you " +
                "a new rotor and we are sending it in the mail \n you're welcome! \n\n from ntnu koie"));
        outBox.add(new Sent("Magnus Blomlie", "Hey Magnus! We were wondering if you the way!!!!","HEllo!, we bought you " +
                "a new rotor and we are sending it in the mail \n you're welcome! \n\n from ntnu koie"));
*/


//	itemData.add(new Item("Heinfjordstua","Guitar", "4","1"));
//	itemData.add(new Item("Fosenkoia","Guitar","5","2"));
//	itemData.add(new Item("Heinfjordstua","Grill", "1","3"));
//	itemData.add(new Item("Heinfjordstua","Sykkel", "1","4"));
//	itemData.add(new Item("Fosenkoia","Sykkel", "1","5"));

        reservationSorting();
		itemHandling();
        //lager liste av alle koie navnene som skal brukes til dropdown menyene
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
        for( ItemType i : itemTypeData){
     //       System.out.println("item typer: " + i.getItemName());
            for(Item j : i.getItemList()){
     //           System.out.println("den innholder: "+ j.getItemName());
            }
        }
    }

    //sorterer reservasjonene og legger de til riktig koie.
    //c.getName().toLowerCase().equals(r.getName().toLowerCase())

    /**
     * Sorts the reservations to the correct cabins
     */

    public void reservationSorting(){
    	for(Cabin c : cabinData){
            c.getReservationList().clear();
			for(Reservation r : reservationData){
			//	System.out.println("cabin name = "+c.getName()+"   reservation name = "+r.getName());
				if(c.getName().toLowerCase().equals(r.getName().toLowerCase())){
					c.addReservation(r);

				}
			}
		}
    }


    //getters for observable listene
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

