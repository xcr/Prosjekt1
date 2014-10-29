import javafx.beans.property.StringProperty;

public class Reservation {
	
	private StringProperty name, email, startdate, enddate;
	
	public Reservation(String name, String email, String startdate, String enddate){
		this.name.setValue(name); 
		this.email.setValue(email);
		this.startdate.setValue(startdate);
		this.enddate.setValue(enddate);
	}
	
	public StringProperty getName(){
		return name;
	}
	
	public StringProperty getEmail(){
		return email;
	}
	
	public StringProperty getStartdate(){
		return startdate;
	}
	
	public StringProperty getEnddate(){
		return enddate;
	}
}
