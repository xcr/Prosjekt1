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
		TextField   firstName, lastName, from, to, email;
        @FXML
        ComboBox cabinName;
        @FXML
        DatePicker datepicker;

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
	        this.from.setText(res.getStartDate());
            this.from.setPromptText("yyyy-mm-dd");
            this.to.setPromptText("yyyy-mm-dd");
	        this.to.setText(res.getEndDate());
	        this.firstName.setText(res.getfirstname());
	        this.lastName.setText(res.getlastname());
            this.email.setText(res.getEmail());
          //  this.datepicker.setValue(res.getStartLocalDate());


	    }

	    @FXML
		protected void handleOk() {
	    
	        if (isInputValid()) {
	           res.setEndDate(this.to.getText());
	           res.setStartDate(this.from.getText());
	           res.setName((String)cabinName.getSelectionModel().getSelectedItem());
	           res.setFirstName(this.firstName.getText());
	           res.setLastName(this.lastName.getText());
               res.setEmail(this.email.getText());
               String[] temp = to.getText().split("-");
               res.setLocalEndDate(LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
               // String[] temp = from.getText().split("-");
                //res.setLocalStartDate(LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
              //  res.setLocalStartDate(datepicker.getValue());

                okClicked = true;
	            dialogStage.close();
	        }
	    }

	    protected boolean isInputValid() {
	        String errorMessage = "";

	        if (from.getText() == null || from.getText().length() != 9) {
	            errorMessage += "Did not set a from date\n"; 
	        }

	        if (to.getText() == null || to.getText().length() == 0) {
	            errorMessage += "Did not set a to date\n"; 
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