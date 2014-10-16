
public class Reservation {
	
	private String name, email, startdate, enddate;
	
	public Reservation(String name, String email, String startdate, String enddate){
		this.name = name;
		this.email = email;
		this.startdate = startdate;
		this.enddate = enddate;
	}
	
	public String getName(){
		return name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public String getStartdate(){
		return startdate;
	}
	
	public String getEnddate(){
		return enddate;
	}

}
