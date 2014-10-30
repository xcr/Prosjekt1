package Cabin;
import javafx.beans.property.StringProperty;


public class Forgotten {
	
	StringProperty name, description, email;
	
	public Forgotten(String name, String description, String email){
		this.name.setValue(name);
		this.description.setValue(description);
		this.email.setValue(email);
	}
	
	//Property getters starts here-----------------
	
	public StringProperty getNameProperty(){
		return this.name;
	}
	
	public StringProperty getDescriptionProperty(){
		return this.description;
	}
	
	public StringProperty getEmailProperty(){
		return this.email;
	}
	
	//String getters starts here-------------------
	
	public String getName(){
		return this.name.toString();
	}
	
	public String getDescription(){
		return this.description.toString();
	}
	
	public String getEmail(){
		return this.email.toString();
	}
}
