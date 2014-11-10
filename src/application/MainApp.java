package application;


import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Cabin.Cabin;
import Cabin.Forgotten;
import Cabin.Reservation;
import Cabin.Sql_data;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    
/*
 * 2. Administratorer i koiestyret skal kunne ta ut status for utstyr p� en eller alle koiene, samt legge inn n�r nytt utstyr er innkj�pt.
3. Administrator i koiestyret skal kunne ta ut status for ved p� en eller alle koiene, og f� anslag p� hvor lenge det vil vare f�r det er n�dvendig med veddugnad.
4. Administrator i koiestyret skal kunne melde til brukere som har reservert koie at det er utstyr som m� fraktes til koia.
5. Administrator i koiestyret skal kunne se et kart hvor koiene er plottet inn, og hvor klikk p� en koie gir administrativ informasjon.
 */
    
    //listene som hentes fra mysql som Observable List
    private ObservableList<Cabin> cabinData = FXCollections.observableArrayList();
    private ObservableList<Forgotten> forgottenData = FXCollections.observableArrayList();
    private ObservableList<Reservation> reservationData = FXCollections.observableArrayList();
    
    /**
     * Constructor
     */
    public MainApp() {
    	
    	//Main constructor, loader data fra mysql inn i programmet.
    	Sql_data sql = new Sql_data("jdbc:mysql://mysql.stud.ntnu.no/gabrielb_gruppe2", "gabrielb_guest", "guest");
		sql.connect();
		cabinData = sql.getCabinData();
		forgottenData = sql.getForgottenData();
		reservationData = sql.getReservationData();
		sql.closeConnection();
		
		//test data
		reservationData.add(new Reservation(1,"Fosenkoia","amail@rofl.copter","24.11.14","25.11.14"));
		reservationData.add(new Reservation(1,"Heinfjordstua","bmail@rofl.copter","24.11.14","25.11.14"));
		reservationData.add(new Reservation(1,"Heinfjordstua","cmail@rofl.copter","24.11.14","25.11.14"));
		reservationData.add(new Reservation(1,"Fosenkoia","dmail@rofl.copter","26.11.14","28.11.14"));
		reservationData.add(new Reservation(1,"Fosenkoia","email@rofl.copter","24.11.14","25.11.14"));
		reservationSorting();

		
		
    }
    
    //sorterer reservasjonene og legger de til riktig koie.
    public void reservationSorting(){
    	for(Cabin c : cabinData){
			for(Reservation r : reservationData){
			//	System.out.println("cabin name = "+c.getName()+"   reservation name = "+r.getName());
				if(c.getName().toLowerCase().equals(r.getName().toLowerCase())){
					c.setReservation(r);
				}
			}

		}
    	
    }
    //getters for observable listene
    public ObservableList<Cabin> getCabinData() {
        return cabinData;
    }
    
    public ObservableList<Forgotten> getForgottenData(){
    	return forgottenData;
    }
    
    public ObservableList<Reservation> getReservationData(){
    	return reservationData;
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

    /**
     * Shows the Cabin overview inside the root layout.
     */
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

    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    /**
     * Opens a dialog to edit details for the specified Cabin. If the user
     * clicks OK, the changes are saved into the provided Cabin object and true
     * is returned.
     * 
     * @param Cabin the Cabin object to be edited
     * @return true if the user clicked OK, false otherwise.
     */
    public boolean showCabinEditDialog(Reservation res) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/EditCabin.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Reservation");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Cabin into the controller.
            editController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReservation(res);

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
