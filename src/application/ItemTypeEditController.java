package application;

import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import org.controlsfx.dialog.Dialogs;

import Cabin.Item;
import Cabin.ItemType;
import Cabin.Reservation;
import Cabin.Sql_data;

public class ItemTypeEditController extends AbstractEditor {

	@FXML
	private TextField  itemName, amount, cabinName;
	private ItemType itemT;
	private String id;

	@FXML
	protected void initialize() {
	}


	public void setChanges(ItemType i) {
		this.itemT = i;
		System.out.println(i.getItemName() + " in editor");
		this.itemName.setText(i.getItemName());
		this.amount.setText(i.getAmount());
		this.cabinName.setText(i.getCabinNames());
		
		for(Item j : i.getItemData()){
			this.id = j.getId();
		}
	}

	@FXML
	protected void handleOk() {
		if (isInputValid()) {

			try {
				Sql_data.updateItemInDatabase(cabinName.getText(), itemName.getText(), amount.getText(), this.id);
				itemT.setAmount(amount.getText());
				itemT.setCabinNames(cabinName.getText());
				itemT.setItemName(itemName.getText() );
			} catch (SQLException e) {
				System.out.println("Failed to update data in database");
				e.printStackTrace();
			}
			
		}

		okClicked = true;
		dialogStage.close();

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
