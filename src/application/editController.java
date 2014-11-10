package application;

	import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

	import org.controlsfx.dialog.Dialogs;

	import Cabin.Cabin;
import Cabin.Reservation;
import application.DateUtil;

	/**
	 * Dialog to edit details of a cabin.
	 * 
	 * @author Marco Jakob
	 */
	public class editController {
		@FXML
		TextField  cabinName, firstName, lastName, from, to;
		
	    private Stage dialogStage;
	    private Reservation res;
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
	    public void setReservation(Reservation res) {
	        this.res = res;
	        System.out.println(res.getName());
	        this.cabinName.setText(res.getName());
	        this.from.setText(res.getStartDate());
	        this.to.setText(res.getEndDate());
	        this.firstName.setText(res.getEmail());
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
	           res.setEmail(this.firstName.getText());
	           res.setEndDate(this.to.getText());
	           res.setStartDate(this.from.getText());
	           res.setName(this.cabinName.getText());

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

	        if (from.getText() == null || from.getText().length() == 0) {
	            errorMessage += "Did not set a from date\n"; 
	        }
	        if (to.getText() == null || to.getText().length() == 0) {
	            errorMessage += "Did not set a to date\n"; 
	        }
	        if (cabinName.getText() == null || cabinName.getText().length() == 0) {
	            errorMessage += "Did not set a cabin name\n"; 
	        }

	        if (firstName.getText() == null || firstName.getText().length() == 0) {
	            errorMessage += "Did not set a first name\n"; 
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