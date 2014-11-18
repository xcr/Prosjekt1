package gui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import org.controlsfx.dialog.Dialogs;

import sqldata.Item;

public class ItemEditController extends AbstractEditor {

	@FXML
	private TextField  itemName, amount;
	private Item item;
    @FXML
    private ComboBox cabinName;
	
    @FXML
	protected void initialize() {
    }

    /**
     * sets the fields to the correct values when the editor is started.
     * @param i
     */
    public void setChanges(Item i) {
        this.item = i;
        cabinName.setValue(i.getCabinName());
        cabinName.getItems().addAll(MainApp.getCabinNames());
        System.out.println(i.getItemName() + " in editor");
        this.itemName.setText(i.getItemName());
        if(i.getAmount().equals("0")){
            this.amount.setText("");
        }else{
        this.amount.setText(i.getAmount());
        }


    }

    /**
     * Runs when the user hits ok. It first runs a validation check and if everything is ok it sets the changes.
     */
    @FXML
	protected void handleOk() {
        if (isInputValid()) {
        	item.setAmount(amount.getText());
        	item.setCabinName((String)cabinName.getSelectionModel().getSelectedItem());
        	item.setItemName(itemName.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * checks if a String s numeric.
     * @param str
     * @return
     */
    public boolean isNumber(String str){
        try{
            int i = Integer.parseInt(str);
        }catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

    /**
     * Checks if the input from the user is valid and displays an message letting him know what is wrong if it is not.
     * @return
     */
    protected boolean isInputValid() {
        String errorMessage = "";

        if (itemName.getText() == null || itemName.getText().length() == 0) {
            errorMessage += "Det ingen utstyrsnavn\n";
        }
        if (amount.getText() == null || amount.getText().length() == 0 || !isNumber(amount.getText()) || Integer.parseInt(amount.getText()) < 1) {
            errorMessage += "Ikke gyldig antall\n";
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
