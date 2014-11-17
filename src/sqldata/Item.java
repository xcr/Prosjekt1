package sqldata;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Item {
	
	private IntegerProperty amount;
	private StringProperty cabinName, itemName;
	private String id;
	
	public Item(String cabinName, String itemName, String amount, String id){
		this.cabinName = new SimpleStringProperty(cabinName);
		this.itemName = new SimpleStringProperty(itemName);
		this.amount = new SimpleIntegerProperty(Integer.parseInt(amount));
		this.id = id;
	}
	
	public Item(){
		this(null, null,null,null);
	}
	
	
	//getters and setters here

	public String getCabinName() {
		return cabinName.get();
	}

	public void setCabinName(String cabinName) {
		this.cabinName.set(cabinName);
	}

	public String getItemName() {
		return itemName.get();
	}

	public void setItemName(String itemName) {
		this.itemName.set(itemName);
	}

	public String getAmount() {
		return ""+amount.get();
	}

	public void setAmount(String amount) {
		this.amount.set(Integer.parseInt(amount));
	}
	
	//property getters her
	public StringProperty getItemNameProperty(){
		return this.itemName;
	}
	
	public StringProperty getCabinNameProperty(){
		return this.cabinName;
	}
	
	public IntegerProperty getAmountProperty(){
		return this.amount;
	}
	
	public String getId(){
		return this.id;
	}
}
