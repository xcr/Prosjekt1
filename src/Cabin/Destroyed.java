package Cabin;

import javafx.beans.property.StringProperty;

public class Destroyed {
	
	private StringProperty cabinName, description, email;
	private int id;
	
	public Destroyed(int id, String cabinName, String description, String email){
		
		
		this.cabinName.setValue(cabinName);
		this.description.setValue(description);
		this.email.setValue(email);
		this.id = id;
	}
	
	//StringProperty getters starts here--------------
	
	public StringProperty getCabinNameProperty(){
		return this.cabinName;
	}
	
	public StringProperty getDescriptionProperty(){
		return this.description;
	}
	
	public StringProperty getEmailProperty(){
		return this.email;
	}
	
	
	//String getters starts here ----------------
	
	public String getCabinName(){
		return this.cabinName.toString();
	}
	
	public String getDescription(){
		return this.description.toString();
	}
	
	public String getEmail(){
		return this.email.toString();
	}
}