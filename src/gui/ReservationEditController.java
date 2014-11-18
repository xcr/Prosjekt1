package gui;

	import javafx.fxml.FXML;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.DatePicker;
    import javafx.scene.control.TextField;

    import org.controlsfx.dialog.Dialogs;

    import sqldata.Reservation;


    import java.time.LocalDate;


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

        /**
         * Set the fields to the correct value when the editor is opened.
         * @param res
         */
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
        /**
         * Runs when the user hits ok. It first runs a validation check and if everything is ok it sets the changes.
         */
	    @FXML
		protected void handleOk() {
	    
	        if (isInputValid()) {

	           res.setName((String)cabinName.getSelectionModel().getSelectedItem());
	           res.setFirstName(this.firstName.getText());
	           res.setLastName(this.lastName.getText());
               res.setEmail(this.email.getText());
               res.setLocalEndDate(datePickerEnd.getValue());
               res.setLocalStartDate(datePickerStart.getValue());
               res.setStartDate(datePickerStart.getValue().toString());
               res.setEndDate(datePickerEnd.getValue().toString());
                okClicked = true;
	            dialogStage.close();
	        }
	    }

        /**
         * validates the input from the user.
         * @return
         */
	    protected boolean isInputValid() {
	        String errorMessage = "";

            if(datePickerEnd.getValue() == null){
                errorMessage += "Glemt å sette sluttdato\n";
            }
            if(datePickerStart.getValue() == null){
                errorMessage += "Glemt å sette startdato\n";
            }
            if(cabinName.getValue() == null){
                errorMessage += "Glemt å sette koie\n";
            }
            if(datePickerStart.getValue().compareTo(datePickerEnd.getValue()) > 0){
                errorMessage += "Sluttdato er før startdato.";
            }

	        if (email.getText() == null ||!email.getText().contains("@") || !email.getText().contains(".")) {
	            errorMessage += "Ugyldig email\n";
	        } 

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	            Dialogs.create()
	                .title("Ugyldige felt")
	                .masthead("Venligst se over følgende")
	                .message(errorMessage)
	                .showError();
	            return false;
	        }
	    }
	}