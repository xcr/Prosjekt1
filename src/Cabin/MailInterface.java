package Cabin;

import javafx.beans.property.StringProperty;

public interface MailInterface {
	
	public StringProperty getDescriptionProperty();

	public StringProperty getEmailProperty();

	public String getDescription();

    public StringProperty getSubject();
}
