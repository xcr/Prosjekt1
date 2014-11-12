package application;

import java.time.LocalDate;

import org.controlsfx.dialog.Dialogs;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

//import application.MainApp;
import Cabin.Cabin;
import Cabin.Forgotten;
import Cabin.Item;
import Cabin.ItemType;
import Cabin.Reservation;


@SuppressWarnings("deprecation")
public class MainController{
	
	
	@FXML
	private TableView<ItemType> itemTable;
	@FXML
	private TableColumn<ItemType, String> itemNameColumn, itemCabinNamesColumn, itemAmountColumn;
	@FXML
	private TableView<Item> cabinItemTable;
	@FXML
	private TableColumn<Item, String> cabinItemName, cabinItemAmount;
	@FXML
	private TableView<Cabin> woodTable;
	@FXML
	private TableColumn<Cabin,String> woodCabinName, woodLevel;
	
    @FXML
    private TableView<Cabin> cabinTable,cabinTable2;
    @FXML
    private TableColumn<Cabin, String> cabinNameColumn, cabinNameColumn2;
    
    @FXML
    private Button reservationAdd, reservationRemove, reservationEdit;
    
    @FXML
    private TableView<Forgotten> forgottenTable;
    @FXML
    private TableColumn<Forgotten, String> forgottenMailColumn;
    
    @FXML
    private TableView<Reservation> reservationTable,sendTable,mainResTable;
    @FXML
    private TableColumn<Reservation, String> reservationTo, reservationFrom, reservationFirstName, reservationLastName,
    sendFirstName, sendLastName,mainResName, mainResFirstName, mainResLastName;
    
    @FXML
    private TableColumn<Reservation, String> mainResFrom, mainResTo;
    //cabin lables
    @FXML
    private Label beds, tables, yearBuilt, terrain, reachableByBike, trip, guitar, waffleIron, hunting, fishing, specialties, woodStatus;
    @FXML
    private TextField to, subject;
    @FXML
    private TextArea body, mailBody;
    @FXML
    //dottene p� kartet
    private ImageView Flaakoia, Fosenkoia, Heinfjordstua, Hognabu, Holmsaakoia, Holvassgamma, Iglbu, 
    Kamtjonnkoia, Kraaklikaaten, Kvernmovollen,	Kaasen, Lynhogen, Mortenskaaten, Nicokoia, Rindalsloa,
    Selbukaaten, Sonvasskoia, Stabburet, Stakkslettbua, Telin, Taagaabu, Vekvessaetra, Ovensenget;
    //skriv om testing p� forskjellige os osv
    @FXML
    private DatePicker reservationDateFrom, reservationDateTo;
    
    
    // referanse til main classen.
    private MainApp mainApp;
    
    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MainController() {


    }
    
    public void setMainApp(MainApp mainApp) {
    	this.mainApp = mainApp;
    	
    	// Add observable list data to the table
    	cabinTable.setItems(mainApp.getCabinData());
    	forgottenTable.setItems(mainApp.getForgottenData());
    	cabinTable2.setItems(mainApp.getCabinData());
    	sendTable.setItems(mainApp.getReservationData());
    	mainResTable.setItems(mainApp.getReservationData());
    	woodTable.setItems(mainApp.getCabinData());
    	itemTable.setItems(mainApp.getItemTypeData());
    	
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
        // Initialize the cabin table with the two columns.
    	cabinNameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    	forgottenMailColumn.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
    	//reservationTo.setCellValueFactory(cellData -> cellData.getValue().getReservationList().get);
    	cabinNameColumn2.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    	//change this later to name
    	sendFirstName.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
    	
    	mainResName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    	mainResFrom.setCellValueFactory(cellData -> cellData.getValue().getStartDateProperty());
    	mainResTo.setCellValueFactory(cellData -> cellData.getValue().getEndDateProperty());
    	mainResFirstName.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
    	
    	itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().getItemNameProperty());
    	itemCabinNamesColumn.setCellValueFactory(cellData -> cellData.getValue().getCabinNamesProperty());
    	itemAmountColumn.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
    	

    	woodLevel.setCellValueFactory(cellData -> cellData.getValue().getWoodProperty());
    	woodCabinName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
    	woodLevel.setCellFactory(column -> {
    		return new TableCell<Cabin, String>(){
    			@Override
    			protected void updateItem(String item, boolean empty){
    				super.updateItem(item, empty);
    				setText(item);
    				if(item == null || empty){
    					setText(null);
    					setStyle("");
    				}
    				
    				else{
    					if(item.equals("Full")){
    						setStyle("-fx-background-color: green");
    					}
    					else if(item.equals("High")){
    						setStyle("-fx-background-color: #11CC11");
    					}
    					else if(item.equals("Medium")){
    						setStyle("-fx-background-color: yellow");
    					}
    					else if(item.equals("Empty")){
    						setStyle("-fx-background-color: #FF5555");
    					}
    					else if(item.equals("Low")){
    						setStyle("-fx-background-color: #FFA000");
    					}
    				}
    			}
    		};
    	});


    	
    	
        // Clear cabin details.
        showCabinDetails(null);
        showForgottenDetails(null);
        // Listen for selection changes and show the cabin details when changed.
        cabinTable.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, newValue) -> showCabinDetails(newValue));

       // reservationTable.getSelectionModel().selectedItemProperty().addListener(
        		//(observable, oldValue, newValue) -> showReservationDetail(newValue));
       cabinTable2.getSelectionModel().selectedItemProperty().addListener(
        		(observable, oldValue, newValue) -> showReservationDetail(newValue));
       sendTable.getSelectionModel().selectedItemProperty().addListener(
       		(observable, oldValue, newValue) -> showMessagingDetail(newValue));
       forgottenTable.getSelectionModel().selectedItemProperty().addListener(
          		(observable, oldValue, newValue) -> showForgottenDetails(newValue));
       woodTable.getSelectionModel().selectedItemProperty().addListener(
         		(observable, oldValue, newValue) -> showWoodStatus(newValue));

       
       
    }


    

    
    //showDetails klassene som s�rger for at riktig info vises i tabellene/labelene
    
    private void showWoodStatus(Cabin cab){
 
    }
    
    private void showMessagingDetail(Reservation res) {
    	to.setText(res.getEmail());
		
	}
    
    private void showReservationDetail(Cabin cab){
    	reservationTable.setItems(cab.getReservationList());
    	reservationTo.setCellValueFactory(cellData -> cellData.getValue().getEndDateProperty());
    	reservationFrom.setCellValueFactory(cellData -> cellData.getValue().getStartDateProperty());
    	reservationFirstName.setCellValueFactory(cellData -> cellData.getValue().getEmailProperty());
    }
    
    private void showForgottenDetails(Forgotten forgotten){
    	if(forgotten != null){
    		mailBody.setText(forgotten.getDescription());
    	}
    	else{
    		mailBody.setText("");
    	}
    }
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
        	cabinItemTable.setItems(cabin.getItemList());
        	cabinItemTable.getSelectionModel().clearSelection();
        	cabinItemName.setCellValueFactory(cellData -> cellData.getValue().getItemNameProperty());
        	cabinItemAmount.setCellValueFactory(cellData -> cellData.getValue().getAmountProperty());
    	
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
    
    
    @FXML
    private void handleRemoveReservation(){
    	int selected = mainResTable.getSelectionModel().getSelectedIndex();
    	if (selected >= 0){
    		
    		mainResTable.getItems().remove(selected);
    		
    		}
    	else{
    		Dialogs.create()
    		.title("No Selection")
    		.masthead("No reservation was selected")
    		.message("Please select a reservation in the table.")
    		.showWarning();
    	}
    }
    


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

	@FXML
	private void handleCabinItemRemove(){
		
	}
	
	@FXML
	private void handleCabinItemAdd(){
		
	}
	
	@FXML
	private void handleCabinItemEdit(){
		Item selected = cabinItemTable.getSelectionModel().getSelectedItem();
		if(selected != null){
			boolean okClicked = mainApp.showItemEditDialog(selected);
		}
		System.out.println("NULL");
	}
	
	
	@FXML
	private void handleItemRemove(){
		
	}
	
	@FXML
	private void handleItemAdd(){
		
	}
	
	@FXML
	private void handleItemEdit(){
		ItemType selected = itemTable.getSelectionModel().getSelectedItem();
		if(selected != null){
			boolean okClicked = mainApp.showItemTypeEditDialog(selected);
		}
	}
	
	@FXML
	private void handleEditReservation(){
		Reservation selected = mainResTable.getSelectionModel().getSelectedItem();
		if (selected != null){
			boolean okClicked = mainApp.showReservationEditDialog(selected);
			if(okClicked){
				
			}
		}else {
			// Nothing selected.
			Dialogs.create()
			.title("No Selection")
			.masthead("No cabin Selected")
			.message("Please select a cabin in the table.")
			.showWarning();
		}
	}
	
	@FXML
	private void handleReservationOk(){
		 System.out.println(reservationDateFrom.getValue());
	}

	@FXML
	private void handleAddReservation(){
		Reservation res = new Reservation();
		boolean okClicked = mainApp.showReservationEditDialog(res);
		if(okClicked){
			mainApp.getReservationData().add(res);
			for (Cabin c : mainApp.getCabinData()){
				System.out.println(c.getName());
				if(c.getName().toLowerCase().equals(res.getName().toLowerCase())){
					c.addReservation(res);
					System.out.println(c.getReservationList());
				}
			}
			
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
		
		SendEmail email = new SendEmail(to.getText(), subject.getText(), body.getText());
		email.start();
		
		
	}




}
