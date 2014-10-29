import javafx.beans.property.StringProperty;


public class Forgotten {
	
	StringProperty name, description, email;
	
	public Forgotten(String name, String description, String email){
		this.name.setValue(name);
		this.description.setValue(description);
		this.email.setValue(email);
	}
	
	public StringProperty getName(){
		return this.name;
	}
	
	public StringProperty getDescription(){
		return this.description;
	}
	
	public StringProperty getEmail(){
		return this.email;
	}
}
