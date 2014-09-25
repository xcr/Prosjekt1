package huts;


import java.util.HashMap;

public class Hut {
	
	private HashMap<String,Item> items = new HashMap<String,Item>();
	private int wood;

	
	public int getWood() { //HeHeeeeeeeeeeeeeeee...
		return wood;
	}
	
	public void setWood(int wood) {
		this.wood = wood;
	}
		
	public void addItem(String name, int amount){
		if(items.containsKey(name)){
			items.get(name).increaseAmount(amount);
		}
		else{
			new Item(name,amount);
			items.put(name, new Item(name,amount));
		}
		
	}
	
	public void removeItem(String name, int amount){
		if(items.containsKey(name)){
			if(items.get(name).getAmount() - amount < 1){
				items.remove(name);
			}
			else{
				items.get(name).decreaseAmount(amount);
			}
		}
		else{
			throw new IllegalArgumentException("The item does not exist");
		}
		
	}
	
	@Override
	public String toString() {
		return "";
	
	}
	
	
	public static void main(String[] args) {
		Hut hut = new Hut();
		hut.addItem("Guitar", 1);
		hut.addItem("Guitar", 1);
		hut.addItem("Bæsj", 3);
		System.out.println(hut.items.size());
		System.out.println(hut.items.toString());
		System.out.println(hut.items.keySet().size());
		System.out.println(hut.items.values().size());
	}

}
