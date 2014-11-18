package sqldata;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Received implements MailInterface {
	
	StringProperty subject, body, from;
	String id, type;
	
	public Received(String id, String email, String subject, String body, String type){
		this.from = new SimpleStringProperty(email);
        this.subject = new SimpleStringProperty(subject);
        this.body = new SimpleStringProperty(body);
        this.id = id;
        this.type = type;
	}
	

    @Override
    public StringProperty getDescriptionProperty() {
        return body;
    }

    @Override
    public StringProperty getEmailProperty() {
        return from;
    }

    @Override
    public String getEmail() {
        return from.get();
    }

    @Override
    public String getDescription() {
        return body.get();
    }

    public StringProperty getSubject() {
        return subject;
    }

    @Override
    public String getid() {
        return id;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public StringProperty getNameProperty() {
        return null;
    }
    
    public String getId(){
    	return this.id;
    }
    

	
}
