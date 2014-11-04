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
import Cabin.Sql_data;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    

    // ... AFTER THE OTHER VARIABLES ...

    /**
     * The data as an observable list of Cabins.
     */
    private ObservableList<Cabin> cabinData = FXCollections.observableArrayList();
    /**
     * Constructor
     */
    public MainApp() {
        // Add some sample data
    	
    	
    	Sql_data sql = new Sql_data("jdbc:mysql://mysql.stud.ntnu.no/gabrielb_gruppe2", "gabrielb_guest", "guest");
		sql.connect();
		cabinData = sql.getCabinData();
		sql.closeConnection();

    }

    /**
     * Returns the data as an observable list of Cabins. 
     * @return
     */
    public ObservableList<Cabin> getCabinData() {
        return cabinData;
    }

    // ... THE REST OF THE CLASS ...
    
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Koie Admin Client");

        initRootLayout();
        showCabinOverview();
    }

    /**
     * Initializes the root layout.
     */
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

            // Set Cabin overview into the center of root layout.
            rootLayout.setCenter(cabinOverview);
            
            // Give the controller access to the main app.
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
    public boolean showCabinEditDialog(Cabin cabin) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/fxml/EditCabin.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Cabin");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Cabin into the controller.
            editController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCabin(cabin);

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
