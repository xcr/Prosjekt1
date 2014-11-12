package application;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import org.controlsfx.dialog.Dialogs;

import Cabin.Item;

import Cabin.Reservation;

public class ItemEditController extends AbstractEditor {

	@FXML
	private TextField  itemName, amount, cabinName;
	private Item item;
	
    @FXML
	protected void initialize() {
    }

    
    public void setChanges(Item i) {
        this.item = i;
        System.out.println(i.getItemName() + " in editor");
        this.itemName.setText(i.getItemName());
        this.amount.setText(i.getAmount());
        this.cabinName.setText(i.getCabinName());
    }

    @FXML
	protected void handleOk() {
        if (isInputValid()) {
        	item.setAmount(amount.getText());
        	item.setCabinName(cabinName.getText());
        	item.setItemName(itemName.getText());

           

            okClicked = true;
            dialogStage.close();
        }
    }
    
   

    protected boolean isInputValid() {
        String errorMessage = "";

        if (itemName.getText() == null || itemName.getText().length() == 0) {
            errorMessage += "Did not set a itemName\n"; 
        }
        if (amount.getText() == null || amount.getText().length() == 0) {
            errorMessage += "Did not set a amount\n"; 
        }
        if (cabinName.getText() == null || cabinName.getText().length() == 0) {
            errorMessage += "Did not set a cabin name\n"; 
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