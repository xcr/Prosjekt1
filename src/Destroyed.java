
public class Destroyed {
	
	private String cabinName, description, email;
	
	public Destroyed(String cabinName, String description, String email){
		
		
		this.cabinName = cabinName;
		this.description = description;
		this.email = email;
	}
	
	
	public String getCabinName(){
		return this.cabinName;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public String getEmail(){
		return this.email;
	}
}