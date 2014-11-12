package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import org.controlsfx.dialog.Dialogs;

import Cabin.Reservation;

public class CabinEditController extends AbstractEditor {
	@FXML
	TextField  cabinName, firstName, lastName, from, to;
	private Reservation res;
	
    @FXML
	protected void initialize() {
    }

    public void setChanges(Reservation res) {
        this.res = res;
        System.out.println(res.getName());
        this.cabinName.setText(res.getName());
        this.from.setText(res.getStartDate());
        this.to.setText(res.getEndDate());
        this.firstName.setText(res.getEmail());
    }

    @FXML
	protected void handleOk() {
        if (isInputValid()) {
           res.setEmail(this.firstName.getText());
           res.setEndDate(this.to.getText());
           res.setStartDate(this.from.getText());
           res.setName(this.cabinName.getText());
           

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
