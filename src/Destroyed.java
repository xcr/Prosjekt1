
public class Destroyed {
	
	private String cnr, cabinName, description, email;
	
	public Destroyed(int cnr, String cabinName, String description, String email){
		
		this.cnr = Integer.toString(cnr);
		this.cabinName = cabinName;
		this.description = description;
		this.email = email;
	}
	
	public String getCnr(){
		return this.cnr;
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
