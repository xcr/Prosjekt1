package Cabin;
import java.time.LocalDate;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reservation {
	
	private StringProperty name, email, startDate, endDate;
	private int id;
	private ObjectProperty<LocalDate> start, end;
	
	public Reservation(int id, String name, String email, String startDate, String endDate){
		this.name = new SimpleStringProperty(name); 
		this.email = new SimpleStringProperty(email);
		this.startDate = new SimpleStringProperty(startDate);
		this.endDate = new SimpleStringProperty(endDate);
		String[] temp = startDate.split("-");
		this.start = new SimpleObjectProperty<LocalDate>(
				LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
		temp = endDate.split("-");
		this.end = new SimpleObjectProperty<LocalDate>(
				LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
	}
	public Reservation(){
		this(0,null, null, null, null);
		
	}
	
	public ObjectProperty<LocalDate> getStartProperty(){
		return this.start;
	}
	public ObjectProperty<LocalDate> getEndProperty(){
		return this.end;
	}
	public void setStart(LocalDate ld){
		start.set(ld);
	}
	public void setEnd(LocalDate ld){
		end.set(ld);
	}
	public LocalDate getStart(){
		return start.get();
	}
	public LocalDate getEnd(){
		return end.get();
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
