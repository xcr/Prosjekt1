package sqldata;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Received {
	
	StringProperty subject, body, date, to;
	String id, type;
	
	Received(String id, String email, String subject, String body, String type){
		this.to = new SimpleStringProperty(email);
        this.subject = new SimpleStringProperty(subject);
        this.body = new SimpleStringProperty(body);
        this.id = id;
        this.type = type;
	}
	
	public String getTo() {
        return to.get();
    }

    public StringProperty getToProperty() {
        return to;
    }

    public void setTo(String to) {
        this.to.set(to);
    }

    public String getSubject() {
        return subject.get();
    }

    public StringProperty getSubjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.set(subject);
    }

    public String getBody() {
        return body.get();
    }

    public StringProperty getBodyProperty() {
        return body;
    }

    public void setBody(String body) {
        this.body.set(body);
    }
    
    public String getId(){
    	return this.id;
    }
    
    public String getType(){
    	return this.type;
    }
	
}
