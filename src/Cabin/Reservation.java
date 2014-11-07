package Cabin;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reservation {
	
	private StringProperty name, email, startDate, endDate;
	private int id;
	
	public Reservation(int id, String name, String email, String startDate, String endDate){
		this.name = new SimpleStringProperty(name); 
		this.email = new SimpleStringProperty(email);
		this.startDate = new SimpleStringProperty(startDate);
		this.endDate = new SimpleStringProperty(endDate);
	}
	
	
	//StringProperty getters starts here ---------
	
	public StringProperty getNameProperty(){
		return name;
	}
	
	public StringProperty getEmailProperty(){
		return email;
	}
	
	public StringProperty getStartDateProperty(){
		return startDate;
	}
	
	public StringProperty getEndDateProperty(){
		return endDate;
	}
	
	//String getters starts here---------------
	
	public String getName(){
		return name.get();
	}
	
	public String getEmail(){
		return email.get();
	}
	
	public String getStartDate(){
		return startDate.get();
	}
	
	public String getEndDate(){
		return endDate.get();
	}
	
	//String setters her
	
	public void setName(String name){
		this.name.setValue(name);
	}
	
	public void setEmail(String email){
		this.email.setValue(email);
	}
	
	public void setStartDate(String date){
		this.startDate.setValue(date);
	}
	
	public void setEndDate(String date){
		this.endDate.setValue(date);
	}
	
}
