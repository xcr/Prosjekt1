package sqldata;

import javafx.beans.property.StringProperty;

/**
 * Interface for connecting multiple messageclasses 
 *
 */

public interface MailInterface {
	
	public StringProperty getDescriptionProperty();

	public StringProperty getEmailProperty();
	
	public String getEmail();

	public String getDescription();

    public StringProperty getSubject();

    public String getid();

    public String getName();

    public StringProperty getNameProperty();




}
