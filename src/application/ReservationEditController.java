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
	public class ReservationEditController extends AbstractEditor {
		@FXML
		TextField  cabinName, firstName, lastName, from, to, email;
		private Reservation res;
		
	    @FXML
		protected void initialize() {
	    }

	    public void setChanges(Reservation res) {
	        this.res = res;
	    	System.out.println("jkjkjk\n\n\n\n\n\n");
	        System.out.println(res.getName());
	        this.cabinName.setText(res.getName());
	        this.from.setText(res.getStartDate());
	        this.to.setText(res.getEndDate());
	        this.firstName.setText(res.getfirstname());
	        this.lastName.setText(res.getlastname());
            this.email.setText(res.getEmail());

	    }

	    @FXML
		protected void handleOk() {
	    
	        if (isInputValid()) {
	           res.setEndDate(this.to.getText());
	           res.setStartDate(this.from.getText());
	           res.setName(this.cabinName.getText());
	           res.setFirstName(this.firstName.getText());
	           res.setLastName(this.lastName.getText());
               res.setEmail(this.email.getText());
	   		
	           

	            okClicked = true;
	            dialogStage.close();
	        }
	    }

	    protected boolean isInputValid() {
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