package Cabin;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
public class ItemType {

		
	private StringProperty cabinNames, itemName, amount;
	private ObservableList<Item> itemData = FXCollections.observableArrayList();
	private int am = 0;
	private String str = "";
	private String id;
	
	public ItemType(String name){
		this.itemName = new SimpleStringProperty(name);
		this.cabinNames = new SimpleStringProperty("");
		this.amount = new SimpleStringProperty("");
	}
	
	public ItemType(){
		this(null);
	}
	public void addItem(Item i){
		am += Integer.parseInt(i.getAmount());
		str = i.getCabinName()+":"+i.getAmount()+" ";
		itemData.add(i);
		this.amount.set(""+am);
		this.cabinNames.set(cabinNames.get() + str);
	}
	
	
	//setters
	
	public void setCabinNames(String names){
		this.cabinNames.set(names);
	}
	
	public void setItemName(String item){
		this.itemName.set(item);
	}
	
	public void setAmount(String amount){
		this.amount.set(amount);
	}
	
	
	//getters
	public String getCabinNames(){
		return cabinNames.get();
	}
	
	public String getItemName(){
		return itemName.get();
	}
	
	public String getAmount(){
		return amount.get();
	}
	
	
//propertygetters
	public StringProperty getCabinNamesProperty() {
		return cabinNames;
	}

	public StringProperty getItemNameProperty() {
		return itemName;
	}

	public StringProperty getAmountProperty() {
		return amount;
	}

	public ObservableList<Item> getItemData() {
		return itemData;
	}
	


}
