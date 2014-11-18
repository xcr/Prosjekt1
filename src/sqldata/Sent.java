package sqldata;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class for messages that has been sent by the user.
 */
public class Sent {

    private StringProperty to, subject, body;


    public Sent(String to, String subject, String body){
        this.to = new SimpleStringProperty(to);
        this.subject = new SimpleStringProperty(subject);
        this.body = new SimpleStringProperty(body);


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

}
