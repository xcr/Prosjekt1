package application;


import java.sql.SQLException;
import java.time.LocalDate;

import org.controlsfx.dialog.Dialogs;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
//import application.MainApp;
import Cabin.Cabin;
import Cabin.Forgotten;
import Cabin.Item;
import Cabin.ItemType;
import Cabin.MailInterface;
import Cabin.Reservation;
import Cabin.Sql_data;


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
	private Button reservationAdd, reservationRemove, reservationEdit, map;

	@FXML
	private TableView<MailInterface> forgottenTable;
	@FXML
	private TableColumn<MailInterface, String> forgottenMailColumn;

	@FXML
	private TableView<Reservation> reservationTable,sendTable,mainResTable;
	@FXML
	private TableColumn<Reservation, String> reservationTo, reservationFrom, reservationFirstName, reservationLastName,
	sendFirstName, sendLastName,mainResName, mainResFirstName, mainResLastName;

	@FXML
	private TableColumn<Reservation, String> mainResFrom, mainResTo;
	//cabin lables
	@FXML
	private Label woodLabelLevel1,woodLabelName, woodLabelLevel, woodLevelDugnad, beds, tables, yearBuilt, terrain, reachableByBike, trip, guitar, waffleIron, hunting, fishing, specialties, woodStatus;
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
	private DatePicker reservationDateFrom, reservationDateTo, reservationDateFrom1, reservationDateTo1;


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
		woodLevelAverage();

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
		sendFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
		sendLastName.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());

		mainResName.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
		mainResFrom.setCellValueFactory(cellData -> cellData.getValue().getStartDateProperty());
		mainResTo.setCellValueFactory(cellData -> cellData.getValue().getEndDateProperty());
		mainResFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
		mainResLastName.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());

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
					TableRow currentRow = getTableRow();
					currentRow.getStylesheets().add(getClass().getResource("/css/tableviewgreen.css").toExternalForm());
					currentRow.getStyleClass().clear();
					if(item == null || empty){
						setText(null);
						setStyle("");
					}

					else{
						if(item.equals("Full")){
							//setStyle("-fx-background-color: green");
							//getStylesheets().add(getClass().getResource("/css/tableviewgreen.css").toExternalForm());
							currentRow.getStyleClass().add("test1");
							currentRow.getStyleClass().add("table-view");
							currentRow.getStyleClass().add("table-row-cell");


						}
						else if(item.equals("High")){
							//setStyle("-fx-background-color: #11CC11");
							currentRow.getStyleClass().add("test5");
							currentRow.getStyleClass().add("table-view");
							currentRow.getStyleClass().add("table-row-cell");

						}
						else if(item.equals("Medium")){
							//setStyle("-fx-background-color: yellow");
							currentRow.getStyleClass().add("test3");
							currentRow.getStyleClass().add("table-view");
							currentRow.getStyleClass().add("table-row-cell");

						}
						else if(item.equals("Empty")){
							//setStyle("-fx-background-color: #FF5555");
							currentRow.getStyleClass().add("test2");
							currentRow.getStyleClass().add("table-view");
							currentRow.getStyleClass().add("table-row-cell");

						}
						else if(item.equals("Low")){
							//setStyle("-fx-background-color: #FFA000");
							currentRow.getStyleClass().add("test4");
							currentRow.getStyleClass().add("table-view");
							currentRow.getStyleClass().add("table-row-cell");

						}
					}
				}
			};
		});


		Image img = new Image(getClass().getResourceAsStream("/resources/map.png"));
		map.setGraphic(new ImageView(img));
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


	@FXML
	private void DateReservation(){
		/*System.out.println(reservationDateTo1);
    	System.out.println(reservationDateFrom1);
    	System.out.println(reservationDateTo1.getValue());
    	System.out.println(reservationDateFrom1.getValue());
    	if(!reservationDateTo1.getValue().toString().equals("null") && !reservationDateFrom1.getValue().toString().equals("null")){

    		String[] to = reservationDateTo1.getValue().toString().split("-");
    		String[] from = reservationDateFrom1.getValue().toString().split("-");

    		ObservableList<Reservation> currentRes = FXCollections.observableArrayList();
    		for(Reservation r : mainApp.getReservationData()){
    			r.getStartDate();
    			String[] rTo = r.getEndDate().split("-");
    			String[] rFrom = r.getStartDate().split("-");
    			if(Integer.parseInt(rFrom[0]) >= Integer.parseInt(from[0]) || Integer.parseInt(rFrom[1]) >= Integer.parseInt(from[1]) 
    					|| Integer.parseInt(rFrom[2]) >= Integer.parseInt(from[2]) || Integer.parseInt(rTo[0]) <= Integer.parseInt(to[0]) || 
    					Integer.parseInt(rTo[0]) <= Integer.parseInt(to[0]) || Integer.parseInt(rTo[0]) <= Integer.parseInt(to[0])){
    				currentRes.add(r);
    			}
    		}
    		mainResTable.setItems(currentRes);
    		System.out.println(currentRes);
    	}
		 */
	}


	//showDetails klassene som s�rger for at riktig info vises i tabellene/labelene

	private void showWoodStatus(Cabin cab){
		String wood = cab.getWood();
		if(wood.equals("Full")){
			woodLevelDugnad.setText("40 dager");
		}
		else if(wood.equals("High")){
			woodLevelDugnad.setText("30 dager");
		}
		else if(wood.equals("Medium")){
			woodLevelDugnad.setText("20 dager");
		}
		else if(wood.equals("Empty")){
			woodLevelDugnad.setText("S� fort som mulig");
		}
		else if(wood.equals("Low")){
			woodLevelDugnad.setText("10 dager");
		}


		woodLabelName.setText(cab.getName());
		woodLabelLevel.setText(wood);

	}

	private void showMessagingDetail(Reservation res) {


	}

	private void showReservationDetail(Cabin cab){
		reservationTable.setItems(cab.getReservationList());
		reservationTo.setCellValueFactory(cellData -> cellData.getValue().getEndDateProperty());
		reservationFrom.setCellValueFactory(cellData -> cellData.getValue().getStartDateProperty());
		reservationFirstName.setCellValueFactory(cellData -> cellData.getValue().getFirstNameProperty());
		reservationLastName.setCellValueFactory(cellData -> cellData.getValue().getLastNameProperty());
	}

	private void showForgottenDetails(MailInterface newValue){
		if(newValue != null){
			mailBody.setText(newValue.getDescription());
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
			//guitar.setText(cabin.getGuitar());
			//waffleIron.setText(cabin.getWaffleiron());
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
			//guitar.setText("");
			//waffleIron.setText("");
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

			//Code for deleting cabin from sql database? Is this neccessary?


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
		//creates a new ItemType
		ItemType item = new ItemType();

		boolean okClicked = mainApp.showItemTypeEditDialog(item);
		if(okClicked){
			mainApp.getItemTypeData().add(item);
			for (Cabin c : mainApp.getCabinData()){
				//System.out.println(c.getName());
				if(c.getName().toLowerCase().equals(item.getCabinNames().toLowerCase())){
					for(Item i : c.getItemList()){
						if(i.getItemName().toLowerCase().equals(item.getItemName().toLowerCase())){
							i.setAmount(""+(Integer.parseInt(i.getAmount()) + Integer.parseInt(item.getAmount())));
							System.out.println(i.getItemName().toLowerCase());
						}
						else{						
							c.addItem(new Item(item.getItemName(),item.getAmount(), item.getCabinNames(), null));
							//here
						}					
					}
					System.out.println(c.getItemList());
				}
			}

		}
	}




	@FXML
	private void handleCabinItemEdit(){
		Item selected = cabinItemTable.getSelectionModel().getSelectedItem();
		if(selected != null){
			boolean okClicked = mainApp.showItemEditDialog(selected);
		}
		System.out.println("NULL");
	}


	//TODO OBSOBSOBS HER VET JEG IKKE OM DET STEMMER. FORDI selected.getItemData() er et array,
	//skal det flere verdier inn der som kanskje ikke skal slettes sammen med det som blir fjernet???
	//kanskje det skal komme en feilmelding her? kanskjekanskje
	//Det funker inntil videre, men ha det i bakhodet. 
	@FXML
	private void handleItemRemove(){
		ItemType selected = itemTable.getSelectionModel().getSelectedItem();
		if (selected != null){
			for(Item i : selected.getItemData()){
				try {
					Sql_data.removeItemsFromDatabase(i.getId());
					itemTable.getItems().remove(selected);
				} catch (SQLException e) {
					System.out.println("failed to remove items from database");
					e.printStackTrace();
				}
			}
		}
		else{
			System.out.println("Nothing selected");
		}
	}

	@FXML
	private void handleItemAdd(){
		ItemType item = new ItemType();
		boolean okClicked = mainApp.showItemTypeEditDialog(item);
		if(okClicked){
			mainApp.getItemTypeData().add(item);

			try {
				Sql_data.addItemToDatabase(item.getCabinNames(), item. getItemName(), item.getAmount());

			} catch (SQLException e) {


				Dialogs.create()
				.title("Tilkoblingsfeil")
				.masthead("Tilkoblingsfeil")
				.message("Kunne ikke oppdatere database")
				.showError();

				e.printStackTrace();
			}
			


		for (Cabin c : mainApp.getCabinData()){
			//System.out.println(c.getName());
			if(c.getName().toLowerCase().equals(item.getCabinNames().toLowerCase())){
				for(Item i : c.getItemList()){
					System.out.println(i.getCabinName());
					if(i.getItemName().toLowerCase().equals(item.getItemName().toLowerCase())){
						i.setAmount(""+(Integer.parseInt(i.getAmount()) + Integer.parseInt(item.getAmount())));
						System.out.println(i.getItemName().toLowerCase());
					}
					else{						
						c.addItem(new Item(item.getCabinNames(),item.getItemName(), item.getAmount(), null));

					}
					System.out.println(c.getItemList());
				}
			}
		}
	}
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
				System.out.println("reservationEDIT whawhawha");

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
		System.out.println(res.getlastname());
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

	@FXML
	private void handleOpenMap(){
		System.out.println("lol");

		Stage dialogStage = new Stage();
		try {
			Map lol = new Map(dialogStage);
		} catch (Exception e) {
			System.out.println("FAIL");
			e.printStackTrace();
		}
	}


	private void woodLevelAverage(){
		int lol = 0;
		for(Cabin c : mainApp.getCabinData()){
			String woodLevel = c.getWood();
			if(woodLevel.equals("Full")){
				lol += 4;
			}
			else if(woodLevel.equals("High")){
				lol += 3;
			}
			else if(woodLevel.equals("Medium")){
				lol += 2;
			}
			else if(woodLevel.equals("Low")){
				lol += 1;
			}
			else if(woodLevel.equals("Empty")){
				lol += 0;
			}
		}
		double average = ((double) lol / (double) mainApp.getCabinData().size());
		System.out.println(average);
		if(average > 4){
			woodLabelLevel1.setText("Full");
		}
		else if(average > 3){
			woodLabelLevel1.setText("High");
		}
		else if(average > 2){
			woodLabelLevel1.setText("Medium");
		}
		else if(average > 1){
			woodLabelLevel1.setText("Low");
		}
		else{
			woodLabelLevel1.setText("Empty");
		}
	}



}
