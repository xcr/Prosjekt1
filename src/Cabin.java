import java.util.HashMap;


public class Cabin {
	
	//name-bednumber-tablenumber-year-terrain-bike-trip-guitar-waffleiron-hunting-fishing-specialities-wood
	
	//private int bednumber, tablenumber, year, bike, trip, guitar, waffleiron, hunting, fishing;
	private String name, terrain, specialities, wood;
	private HashMap<String, String> items;
	
	public Cabin(String name, int bednumber, int tablenumber, int year, String terrain, int bike, int trip,
			int guitar, int waffleiron, int hunting, int fishing, String specialities, String wood ){
		
		this.wood = wood;
		this.name = name;
		this.terrain = terrain;
		this.specialities = specialities;
		
		items = new HashMap<String, String>();
		items.put("bednumber", Integer.toString(bednumber));
		items.put("tablenumber", Integer.toString(tablenumber));
		items.put("year", Integer.toString(year));
		items.put("bike", Integer.toString(bike));
		items.put("trip", Integer.toString(trip));
		items.put("guitar", Integer.toString(guitar));
		items.put("waffleiron", Integer.toString(waffleiron));
		items.put("hunting", Integer.toString(hunting));
		items.put("fishing", Integer.toString(fishing));
		items.put("name", name);
		items.put("terrain", terrain);
		items.put("specialities", specialities);
	}
	
	public String getWood(){
		return this.wood;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getSpecialities(){
		return this.specialities;
	}
	
	public String getTerrain(){
		return this.terrain;
	}
	public HashMap<String, String> getItems(){
		
		return items;
	}
}
