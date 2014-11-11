package Cabin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Forgotten {
	
	private StringProperty name, description, email;
	private int id;
	
	public Forgotten(int id, String name, String description, String email){
		
		this.name = new SimpleStringProperty(name);
		this.description = new SimpleStringProperty(description);
		this.email = new SimpleStringProperty(email);
		this.id = id;
	}
	
	//Property getters starts here --->
	
	public StringProperty getNameProperty(){
		return this.name;
	}
	
	public StringProperty getDescriptionProperty(){
		return this.description;
	}
	
	public StringProperty getEmailProperty(){
		return this.email;
	}
	
	//String getters starts here --->
	
	public String getName(){
		return this.name.get();
	}
	
	public String getDescription(){
		return this.description.get();
	}
	
	public String getEmail(){
		return this.email.get();
	}
	
	//String setters start here-------------
	public void setName(String name){
		this.name.setValue(name);
	}
	
	public void setEmail(String email){
		this.email.setValue(email);
	}
	
	public void setDescription(String description){
		this.description.setValue(description);
	}
}