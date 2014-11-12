package application;


import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Cabin.Cabin;
import Cabin.Forgotten;
import Cabin.Item;
import Cabin.ItemType;
import Cabin.MailInterface;
import Cabin.Reservation;
import Cabin.Sql_data;



public class MainApp extends Application{

    private Stage primaryStage;
    private BorderPane rootLayout;
    
    
    


/*
 * 2. Administratorer i koiestyret skal kunne ta ut status for utstyr pï¿½ en eller alle koiene, samt legge inn nï¿½r nytt utstyr er innkjï¿½pt.
3. Administrator i koiestyret skal kunne ta ut status for ved pï¿½ en eller alle koiene, og fï¿½ anslag pï¿½ hvor lenge det vil vare fï¿½r det er nï¿½dvendig med veddugnad.
4. Administrator i koiestyret skal kunne melde til brukere som har reservert koie at det er utstyr som mï¿½ fraktes til koia.
5. Administrator i koiestyret skal kunne se et kart hvor koiene er plottet inn, og hvor klikk pï¿½ en koie gir administrativ informasjon.
 
 toDo:
 *lage items klasse
 *integrer item i gui
 *lag kode som håndterer logikken med item amount osv
 *add editorer for de gjennværende feltene.
 *fix kart markers
 *lag wood algorithme
 *add threading til email
 *flytt ting fra about cabin til items(i mysql)
 *add destroyed
 fix reservasjonsbuttons
 fix wood per koie
 fix dato felt greia for reservasjoner
 fix set conditions for datoer
 fix så datoan står riktig vei
 fix wood css
 sort greia til wood
 lagre meldinger
 hente emails fra gmail
 lag undo(hotkey?)
 fix på alle tabellene så de ikke har overflødige felt osv
 save og load funksjoner(david)
 sette cabin reservation og cabin info til samme colonne listener(lav prio)
 embed kart(lav prio)
 add about page(lav prio)
 adde masse throws osv(noe av det siste som kan gjøres)
 comment all koden
 
 

 */
    
    //listene som hentes fra mysql som Observable List
    private ObservableList<Cabin> cabinData = FXCollections.observableArrayList();
    private ObservableList<MailInterface> forgottenData = FXCollections.observableArrayList();
    private ObservableList<Reservation> reservationData = FXCollections.observableArrayList();
    private ObservableList<Item> itemData = FXCollections.observableArrayList();
    private ObservableList<ItemType> itemTypeData = FXCollections.observableArrayList();
    private ObservableList<MailInterface> destroyedData = FXCollections.observableArrayList();

    public MainApp() {
    	
    	//Main constructor, loader data fra mysql inn i programmet.
    	Sql_data sql = new Sql_data("jdbc:mysql://mysql.stud.ntnu.no/gabrielb_gruppe2", "gabrielb_guest", "guest");
		sql.connect();
		cabinData = sql.getCabinData();
		forgottenData = sql.getForgottenData();
		forgottenData.addAll(sql.getDestroyedData());
		
		
		reservationData = sql.getReservationData();
		sql.closeConnection();
		
		//test data
		reservationData.add(new Reservation(1,"Fosenkoia","amail@rofl.copter","2014-10-4","2014-10-4", "David","Bakke"));
		reservationData.add(new Reservation(1,"Heinfjordstua","bmail@rofl.copter","2014-10-4","2014-10-4", "Magnus","Blomlie"));
		reservationData.add(new Reservation(1,"Heinfjordstua","cmail@rofl.copter","2014-10-1","2014-10-4", "Eirik","Bertelsen"));
		reservationData.add(new Reservation(1,"Fosenkoia","dmail@rofl.copter","2014-10-4","2014-10-7", "Gabriel","Et eller annet"));
		reservationData.add(new Reservation(1,"Fosenkoia","email@rofl.copter","2014-11-25","2014-11-28", "Ola","Nordmann"));
		reservationSorting();
		
		itemData.add(new Item("Guitar", "4","Heinfjordstua"));
		itemData.add(new Item("Guitar","5","Fosenkoia"));
		itemData.add(new Item("Grill", "1","Heinfjordstua"));
		itemData.add(new Item("Sykkel", "1", "Heinfjordstua"));
		itemData.add(new Item("Sykkel", "1", "Fosenkoia"));
		itemHandeling();
    }
    
    public void itemHandeling(){

    	for(Cabin c : cabinData){
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
    
    //sorterer reservasjonene og legger de til riktig koie.
    //c.getName().toLowerCase().equals(r.getName().toLowerCase())
    public void reservationSorting(){
    	for(Cabin c : cabinData){
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
    

    // ... THE REST OF THE CLASS ...
    
    
    @Override
    public void start(Stage primaryStage) {

    	
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Koie Admin Client");

        initRootLayout();
        showCabinOverview();
    }

   //initialiserer root layouten
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/Root.fxml"));
            rootLayout = (BorderPane) loader.load();

            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showCabinOverview() {
        try {
            // Load Cabin overview.
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


    public Stage getPrimaryStage() {
        return primaryStage;
    }

    
    
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
    
    public boolean showItemTypeEditDialog(ItemType selected) {
    	 try {
             // Load the fxml file and create a new stage for the popup dialog.
             FXMLLoader loader = new FXMLLoader();
             loader.setLocation(MainApp.class.getResource("/fxml/ItemTypeEditor.fxml"));
             AnchorPane page = (AnchorPane) loader.load();

             // Create the dialog Stage.
             Stage dialogStage = new Stage();
             dialogStage.setTitle("Edit Item");
             dialogStage.initModality(Modality.WINDOW_MODAL);
             dialogStage.initOwner(primaryStage);
             Scene scene = new Scene(page);
             dialogStage.setScene(scene);

             
             ItemTypeEditController controller = loader.getController();
             controller.setDialogStage(dialogStage);
             controller.setChanges(selected);

    
             dialogStage.showAndWait();

             return controller.isOkClicked();
         } catch (IOException e) {
             e.printStackTrace();
             return false;
         }
    }
    
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

            // Set the Cabin into the controller.
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
    

    
    public static void main(String[] args) {
        launch(args);
    }



}
