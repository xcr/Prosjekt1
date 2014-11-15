package Cabin;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reservation {
	
	private StringProperty name, email, startDate, endDate, firstname, lastname;
	private int id;
	private ObjectProperty<LocalDate> start, end;
	private HashMap<String, String> changedFields = new HashMap<String, String>();
	
	
	public Reservation(int id, String name, String email, String startDate, String endDate, String firstname, String lastname){
		this.name = new SimpleStringProperty(name); 
		this.email = new SimpleStringProperty(email);
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		this.startDate = new SimpleStringProperty(startDate);
		this.endDate = new SimpleStringProperty(endDate);
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		
		
		//local date stuff om man skal omgj�re
        if(startDate != null){

		String[] temp = startDate.split("-");
		this.start = new SimpleObjectProperty<LocalDate>(
				LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
		temp = endDate.split("-");
		this.end = new SimpleObjectProperty<LocalDate>(
				LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));



		this.id = id;
        }

	}
	public Reservation(){
		this(0,null, null, null, null,null, null);
	}
	
//	public ObjectProperty<LocalDate> getStartProperty(){
//		return this.start;
//	}
//	public ObjectProperty<LocalDate> getEndProperty(){
//		return this.end;
//	}
	
	
//	public void setStart(LocalDate ld){
//		start.set(ld);
//	}
//	public void setEnd(LocalDate ld){
//		end.set(ld);
//	}
//	public LocalDate getStart(){
//		return start.get();
//	}
//	public LocalDate getEnd(){
//		return end.get();
//	}
	
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
	public StringProperty firstname(){
		return this.firstname;
	}
	
	public StringProperty getFirstNameProperty(){
		return this.firstname;
	}
	public StringProperty getLastNameProperty(){
		return this.lastname;
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
	public String getfirstname(){
		return this.firstname.get();
	}
	
	public String getlastname(){
		return this.lastname.get();
	}
	
	//String setters her
	
	public void setName(String name){
		this.name.setValue(name);
		this.changedFields.put("name", name);
	}
	
	public void setEmail(String email){
		this.email.setValue(email);
		this.changedFields.put("email", email);
	}
	
	public void setStartDate(String date){
		this.startDate.setValue(date);
		this.changedFields.put("startdate", date);
	}
	
	public void setEndDate(String date){
		this.endDate.setValue(date);
		this.changedFields.put("enddate", date);
	}
	
	public void setLastName(String name){
		this.lastname.setValue(name);
	}
	public void setFirstName(String name){

		this.firstname.setValue(name);
	}
	public int getId(){
		return this.id;
	}
	public LocalDate getStartLocalDate(){
		return this.start.get();
	}
	public LocalDate getEndLocalDate(){
		return this.end.get();
	}
}
