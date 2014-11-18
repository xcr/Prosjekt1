package sqldata;
import java.time.LocalDate;
import java.util.HashMap;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reservation {
	
	private StringProperty name, email, startDate, endDate, firstname, lastname;
	private String reservationId, userId;
	private ObjectProperty<LocalDate> start, end;
	private HashMap<String, String> changedFields = new HashMap<String, String>();
	
	
	public Reservation(String reservationId, String userId, String name, String email, String startDate, String endDate, String firstname, String lastname){
		this.name = new SimpleStringProperty(name); 
		this.email = new SimpleStringProperty(email);
		this.firstname = new SimpleStringProperty(firstname);
		this.lastname = new SimpleStringProperty(lastname);
		this.startDate = new SimpleStringProperty(startDate);
		this.endDate = new SimpleStringProperty(endDate);
		
		this.reservationId = reservationId;
		this.userId = userId;



        if(startDate != null){

		String[] temp = startDate.split("-");
		this.start = new SimpleObjectProperty<LocalDate>(
				LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
		temp = endDate.split("-");
		this.end = new SimpleObjectProperty<LocalDate>(
				LocalDate.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2])));
		
        }
        else{
            this.start = new SimpleObjectProperty<LocalDate>();
            this.end = new SimpleObjectProperty<LocalDate>();
        }

	}
	public Reservation(){
		this(null, null, null, null, null, null,null, null);



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
	public String getReservationId(){
		return this.reservationId;
	}
	public String getUserId(){
		return this.userId;
	}
	public LocalDate getStartLocalDate(){
		return this.start.get();
	}
	public LocalDate getEndLocalDate(){
		return this.end.get();
	}
    public ObjectProperty<LocalDate> getStartLocalDateProperty(){return this.start;}
    public ObjectProperty<LocalDate> getEndLocalDateProperty(){return this.end;}
    public void setLocalStartDate(LocalDate l){this.start.set(l);   }
    public void setLocalEndDate(LocalDate l){this.end.set(l);}
}
