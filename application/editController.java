package application;

	import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

	import org.controlsfx.dialog.Dialogs;

	import Cabin.Cabin;
import application.DateUtil;

	/**
	 * Dialog to edit details of a cabin.
	 * 
	 * @author Marco Jakob
	 */
	public class editController {


	    @FXML
	    private TextField bedsField, tablesField, yearBuiltField, terrainField, reachableByBikeField, tripField;

	    private Stage dialogStage;
	    private Cabin cabin;
	    private boolean okClicked = false;

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {
	    }

	    /**
	     * Sets the stage of this dialog.
	     * 
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	    /**
	     * Sets the cabin to be edited in the dialog.
	     * 
	     * @param cabin
	     */
	    public void setCabin(Cabin cabin) {
	        this.cabin = cabin;

	        bedsField.setText(cabin.getBednumber());
	        tablesField.setText(cabin.getTablenumber());
	        yearBuiltField.setText(cabin.getYear());
	        terrainField.setText(cabin.getTerrain());
	        reachableByBikeField.setText(cabin.getBike());
	        tripField.setText(cabin.getTrip());
	    }

	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     * 
	     * @return
	     */
	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    /**
	     * Called when the user clicks ok.
	     */
	    @FXML
	    private void handleOk() {
	        if (isInputValid()) {
	            cabin.setBedNumber(bedsField.getText());
	            cabin.setTableNumber(tablesField.getText());
	            cabin.setYear(yearBuiltField.getText());
	            cabin.setTerrain(terrainField.getText());
	            cabin.setBike(reachableByBikeField.getText());
	            cabin.setTrip(tripField.getText());

	            okClicked = true;
	            dialogStage.close();
	        }
	    }

	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

	    /**
	     * Validates the user input in the text fields.
	     * 
	     * @return true if the input is valid
	     */
	    private boolean isInputValid() {
	        String errorMessage = "";

	        if (bedsField.getText() == null || bedsField.getText().length() == 0) {
	            errorMessage += "No valid bednr!\n"; 
	        }
	        if (tablesField.getText() == null || tablesField.getText().length() == 0) {
	            errorMessage += "No valid tablesField!\n"; 
	        }
	        if (yearBuiltField.getText() == null || yearBuiltField.getText().length() == 0) {
	            errorMessage += "No valid year!\n"; 
	        }

	        if (terrainField.getText() == null || terrainField.getText().length() == 0) {
	            errorMessage += "No valid teraain????!\n"; 
	        } 

	        if (reachableByBikeField.getText() == null || reachableByBikeField.getText().length() == 0) {
	            errorMessage += "no valid reachableByBikeField\n"; 
	        }

	        if (tripField.getText() == null || tripField.getText().length() == 0) {
	            errorMessage += "No valid birthday!\n";
	        }

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	            Dialogs.create()
	                .title("Invalid Fields")
	                .masthead("Please correct invalid fields")
	                .message(errorMessage)
	                .showError();
	            return false;
	        }
	    }
	}