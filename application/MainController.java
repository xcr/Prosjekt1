package application;


import java.util.EventObject;

import org.controlsfx.dialog.Dialogs;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import application.MainApp;
import Cabin.Cabin;

@SuppressWarnings("deprecation")
public class MainController {
    @FXML
    private TableView<Cabin> cabinTable;
    @FXML
    private TableColumn<Cabin, String> cabinNameColumn;
    @FXML
    private Label beds, tables, yearBuilt, terrain, reachableByBike, trip, guitar, waffleIron, hunting, fishing, specialties, woodStatus;
    @FXML
    private TextField to, subject;
    @FXML
    private TextArea body;
    @FXML
    //dottene på kartet
    private ImageView Flaakoia, Fosenkoia, Heinfjordstua, Hognabu, Holmsaakoia, Holvassgamma, Iglbu, 
    Kamtjonnkoia, Kraaklikaaten, Kvernmovollen,	Kaasen, Lynhogen, Mortenskaaten, Nicokoia, Rindalsloa,
    Selbukaaten, Sonvasskoia, Stabburet, Stakkslettbua, Telin, Taagaabu, Vekvessaetra, Ovensenget;
    //skriv om testing pï¿½ forskjellige os osv
    
    // referanse til main classen.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MainController() {
    	
    //	Flaakoia.addEventHandler(MouseEvent.MOUSE_ENTERED, new MyButtonHandler());
    }


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the cabin table with the two columns.
    	cabinNameColumn.setCellValueFactory(
                cellData -> cellData.getValue().getNameProperty());

        // Clear cabin details.
        showCabinDetails(null);

        // Listen for selection changes and show the cabin details when changed.
        cabinTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCabinDetails(newValue));
    }

    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Add observable list data to the table
        cabinTable.setItems(mainApp.getCabinData());
        
    }
    /**
     * Fills all text fields to show details about the cabin.
     * If the specified cabin is null, all text fields are cleared.
     * 
     * @param cabin the cabin or null
     */
    private void showCabinDetails(Cabin cabin) {
        if (cabin != null) {
            // Fill the labels with info from the cabin object.
        	beds.setText(cabin.getBednumber());
        	tables.setText(cabin.getTablenumber());
        	yearBuilt.setText(cabin.getYear());
        	terrain.setText(cabin.getTerrain());
        	reachableByBike.setText(cabin.getBike());     	
        	trip.setText(cabin.getTrip());
        	guitar.setText(cabin.getGuitar());
        	waffleIron.setText(cabin.getWaffleiron());
        	hunting.setText(cabin.getHunting());
        	fishing.setText(cabin.getFishing());
        	specialties.setText(cabin.getSpecialities());
        	woodStatus.setText(cabin.getWood());
        } else {
            // cabin is null, remove all the text.
        	beds.setText("");
        	tables.setText("");
        	yearBuilt.setText("");
        	terrain.setText("");
        	reachableByBike.setText("");
        	trip.setText("");
        	guitar.setText("");
        	waffleIron.setText("");
        	hunting.setText("");
        	fishing.setText("");
        	specialties.setText("");
        	woodStatus.setText("");
        }
    }
    /**
     * Called when the user clicks on the delete button.
     */
	@FXML
    private void handleDeleteCabin() {
        int selectedIndex = cabinTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            cabinTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Dialogs.create()
                .title("No Selection")
                .masthead("No cabin Selected")
                .message("Please select a cabin in the table.")
                .showWarning();
        }
    }
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new cabin.
     */
  
    @FXML
    private void handleNewCabin() {
        Cabin tempCabin = new Cabin();
        boolean okClicked = mainApp.showCabinEditDialog(tempCabin);
        if (okClicked) {
            mainApp.getCabinData().add(tempCabin);
        }
    }
    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected cabin.
     */
	@FXML
    private void handleEditCabin() {
        Cabin selectedCabin = cabinTable.getSelectionModel().getSelectedItem();
        if (selectedCabin != null) {
            boolean okClicked = mainApp.showCabinEditDialog(selectedCabin);
            if (okClicked) {
                showCabinDetails(selectedCabin);
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                .title("No Selection")
                .masthead("No cabin Selected")
                .message("Please select a cabin in the table.")
                .showWarning();
        }
    }
	@FXML
	private void handleMouseOver(Event evt){
		ImageView lol = (ImageView) evt.getSource();
		System.out.println(lol.getId());
	
	}
	@FXML
	private void handleMoveRemoved(Event evt){
		System.out.println("hade");
	}
	@FXML
	private void handleSendMail(){
		SendEmail.sendEmail(to.getText(), subject.getText(), body.getText());
	}




}
