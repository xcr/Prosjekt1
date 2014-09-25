package huts;

public class Item {
	
	//tror alt av name stuff er unødvendig pga HashMap men lar det bare stå for nå.
	private String name;
	private int amount;

	public Item(String name, int amount){
		this.name = name;
		this.amount = amount;
	}

		public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public void increaseAmount(int amount){
		this.amount += amount;
	}
	public void decreaseAmount(int amount){
		this.amount -= amount;
	}

	@Override
	public String toString() {
		return "This is a " + name + ", amount=" + amount + "]";
	}
	
	//Placement class for now.
	
	

}
