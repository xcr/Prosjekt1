package gui;

	import javafx.fxml.FXML;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.DatePicker;
    import javafx.scene.control.TextField;

    import org.controlsfx.dialog.Dialogs;

    import sqldata.Reservation;


    import java.time.LocalDate;

/**
	 * Dialog to edit details of a cabin.
	 * 
	 * @author Marco Jakob
	 */
	public class ReservationEditController extends AbstractEditor {
		@FXML
		TextField   firstName, lastName, email;
        @FXML
        ComboBox cabinName;
        @FXML
        DatePicker datePickerStart, datePickerEnd;

		private Reservation res;
		
	    @FXML
		protected void initialize() {
	    }

	    public void setChanges(Reservation res) {
            cabinName.setValue(res.getName());
            cabinName.getItems().addAll(MainApp.getCabinNames());
	        this.res = res;
            System.out.println(res.getEndDate());
            System.out.println("jkjkjk\n\n\n\n\n\n");
	        System.out.println(res.getName());

            this.datePickerEnd.setPromptText("yyyy-mm-dd");
            this.datePickerStart.setPromptText("yyyy-mm-dd");

	        this.firstName.setText(res.getfirstname());
	        this.lastName.setText(res.getlastname());
            this.email.setText(res.getEmail());
            this.datePickerStart.setValue(res.getStartLocalDate());
            this.datePickerEnd.setValue(res.getEndLocalDate());


	    }

	    @FXML
		protected void handleOk() {
	    
	        if (isInputValid()) {

	           res.setName((String)cabinName.getSelectionModel().getSelectedItem());
	           res.setFirstName(this.firstName.getText());
	           res.setLastName(this.lastName.getText());
               res.setEmail(this.email.getText());
               res.setLocalEndDate(datePickerEnd.getValue());
               res.setLocalStartDate(datePickerStart.getValue());

                okClicked = true;
	            dialogStage.close();
	        }
	    }

	    protected boolean isInputValid() {
	        String errorMessage = "";


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