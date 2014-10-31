package Cabin;
import javafx.beans.property.StringProperty;

public class Reservation {
	
	private StringProperty name, email, startdate, enddate;
	private int id;
	
	public Reservation(int id, String name, String email, String startdate, String enddate){
		this.name.setValue(name); 
		this.email.setValue(email);
		this.startdate.setValue(startdate);
		this.enddate.setValue(enddate);
	}
	
	//StringProperty getters starts here ---------
	
	public StringProperty getNameProperty(){
		return name;
	}
	
	public StringProperty getEmailProperty(){
		return email;
	}
	
	public StringProperty getStartdateProperty(){
		return startdate;
	}
	
	public StringProperty getEnddateProperty(){
		return enddate;
	}
	
	//String getters starts here---------------
	
	public String getName(){
		return name.toString();
	}
	
	public String getEmail(){
		return email.toString();
	}
	
	public String getStartdate(){
		return startdate.toString();
	}
	
	public String getEnddate(){
		return enddate.toString();
	}
}
