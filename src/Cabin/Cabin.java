package Cabin;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Cabin {

	// Git test
	// name-bednumber-tablenumber-year-terrain-bike-trip-guitar-waffleiron-hunting-fishing-specialities-wood

	// private int bednumber, tablenumber, year, bike, trip, guitar, waffleiron,
	// hunting, fishing;
	private StringProperty name, bedNumber, tableNumber, year, terrain, bike,
			trip, guitar, waffleIron, hunting, fishing, specialities, wood;

	public Cabin(String name, int bedNumber, int tableNumber, int year,
			String terrain, int bike, int trip, int guitar, int waffleIron,
			int hunting, int fishing, String specialities, String wood) {

		this.name = new SimpleStringProperty(name);
		this.bedNumber = new SimpleStringProperty(Integer.toString(bedNumber));
		this.tableNumber = new SimpleStringProperty(Integer.toString(tableNumber));
		this.year = new SimpleStringProperty(Integer.toString(year));
		this.terrain = new SimpleStringProperty(terrain);
		this.bike = new SimpleStringProperty(Integer.toString(bike));
		this.trip = new SimpleStringProperty(Integer.toString(trip));
		this.guitar = new SimpleStringProperty(Integer.toString(guitar));
		this.waffleIron = new SimpleStringProperty(Integer.toString(waffleIron));
		this.hunting = new SimpleStringProperty(Integer.toString(hunting));
		this.fishing = new SimpleStringProperty(Integer.toString(fishing));
		this.specialities = new SimpleStringProperty(specialities);
		this.wood = new SimpleStringProperty(wood);
	}

	public Cabin(){
		this(null,0,0,0,null,0,0,0,0,0,0,null,null);
	}
	// Property getters starts here------------------------->

	public StringProperty getBednumberProperty() {
		return bedNumber;
	}

	public StringProperty getTablenumberProperty() {
		return tableNumber;
	}

	public StringProperty getYearProperty() {
		return year;
	}

	public StringProperty getBikeProperty() {
		return bike;
	}

	public StringProperty getTripProperty() {
		return trip;
	}

	public StringProperty getGuitarProperty() {
		return guitar;
	}

	public StringProperty getWaffleironProperty() {
		return waffleIron;
	}

	public StringProperty getHuntingProperty() {
		return hunting;
	}

	public StringProperty getFishingProperty() {
		return fishing;
	}

	public StringProperty getWoodProperty() {
		return this.wood;
	}

	public StringProperty getNameProperty() {
		return this.name;
	}

	public StringProperty getSpecialitiesProperty() {
		return this.specialities;
	}

	public StringProperty getTerrainProperty() {
		return this.terrain;
	}

	// String getters starts here --------------------------

	public String getBednumber() {
		return bedNumber.get();
	}

	public String getTablenumber() {
		return tableNumber.get();
	}

	public String getYear() {
		return year.get();
	}

	public String getBike() {
		return bike.get();
	}

	public String getTrip() {
		return trip.get();
	}

	public String getGuitar() {
		return guitar.get();
	}

	public String getWaffleiron() {
		return waffleIron.get();
	}

	public String getHunting() {
		return hunting.get();
	}

	public String getFishing() {
		return fishing.get();
	}

	public String getWood() {
		return this.wood.get();
	}

	public String getName() {
		return this.name.get();
	}

	public String getSpecialities() {
		return this.specialities.get();
	}

	public String getTerrain() {
		return this.terrain.get();
	}

	//Setters go here..........................
	public void setName(String name){
		this.name.setValue(name);
	}
	
	public void setBedNumber(String bedNumber){
		this.bedNumber.setValue(bedNumber);		
	}
	
	public void setTableNumber(String tableNumber){		
		this.tableNumber.setValue(tableNumber);
	}
	
	public void setYear(String year){	
		this.year.setValue(year);
	}
	
	public void setTerrain(String terrain){
		this.terrain.setValue(terrain);
	}
	
	public void setBike(String bike){
		this.bike.setValue(bike);
	}
	
	public void setTrip(String trip){
		this.trip.setValue(trip);
	}
	
	public void setGuitar(String guitar){
		this.guitar.setValue(guitar);
	}
	
	public void setWaffleIron(String waffleIron){
		this.waffleIron.setValue(waffleIron);		
	}
	
	public void setHunting(String hunting){
		this.hunting.setValue(hunting);
	}

	public void setFishing(String fishing){
		this.fishing.setValue(fishing);
	}
	public void setSpecialities(String specialities){		
		this.specialities.setValue(specialities);
	}
	
	public void setWood(String wood){		
		this.wood.setValue(wood);
	}

}
