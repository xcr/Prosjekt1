package sqldata;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class for holding information of the destroyed messagetype
 * @author David
 *
 */

public class Destroyed implements MailInterface {
	
	private StringProperty cabinName, description, email;
	private String id;
    private StringProperty subject = new SimpleStringProperty("Ødelagt");
	
	public Destroyed(String id, String cabinName, String description, String email){
		
		this.cabinName = new SimpleStringProperty(cabinName);
		this.description = new SimpleStringProperty(description);
		this.email = new SimpleStringProperty(email);
		this.id = id;
	}
	
	//Property getters starts here --->
	
		public StringProperty getCabinNameProperty(){
			return this.cabinName;
		}
		
		public StringProperty getDescriptionProperty(){
			return this.description;
		}
		
		public StringProperty getEmailProperty(){
			return this.email;
		}
		
		//String getters starts here --->
		
		public String getName(){
			return this.cabinName.get();
		}
		
		public String getDescription(){
			return this.description.get();
		}


    public StringProperty getSubject() {
        return this.subject;
    }

    public StringProperty getNameProperty(){
        return this.cabinName;
    }
    

    public String getEmail(){
			return this.email.get();
		}
		
		//String setters start here-------------
		public void setName(String cabinName){
			this.cabinName.setValue(cabinName);
		}
		
		public void setEmail(String email){
			this.email.setValue(email);
		}
		
		public void setDescription(String description){
			this.description.setValue(description);
		}

		@Override
		public String getid() {
			return this.id;
		}
	}