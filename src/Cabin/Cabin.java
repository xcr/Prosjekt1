package Cabin;


import java.util.HashMap;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Cabin {

	// Git test
	// name-bednumber-tablenumber-year-terrain-bike-trip-guitar-waffleiron-hunting-fishing-specialities-wood

	// private int bednumber, tablenumber, year, bike, trip, guitar, waffleiron,
	// hunting, fishing;
	private StringProperty name, bedNumber, tableNumber, year, terrain, bike,
	trip, guitar, waffleIron, hunting, fishing, specialities, wood, cnr;

	private ObservableList<Reservation> reservationData = FXCollections.observableArrayList();
	private ObservableList<Item> itemData = FXCollections.observableArrayList();

	private HashMap<String, String> changedItems;
	private HashMap<String, String> newItems;
	private String id;


	public Cabin(int cnr, String name, String bedNumber, String tableNumber, int year,

			String terrain, String bike, String trip, String guitar, String waffleIron,
			String hunting, String fishing, String specialities, String wood) {

		this.id = Integer.toString(cnr);

		this.name = new SimpleStringProperty(name);
		this.bedNumber = new SimpleStringProperty(bedNumber);
		this.tableNumber = new SimpleStringProperty(tableNumber);
		this.year = new SimpleStringProperty(Integer.toString(year));
		this.terrain = new SimpleStringProperty(terrain);
		this.bike = new SimpleStringProperty(bike);
		this.trip = new SimpleStringProperty(trip);
		this.guitar = new SimpleStringProperty(guitar);
		this.waffleIron = new SimpleStringProperty(waffleIron);
		this.hunting = new SimpleStringProperty(hunting);
		this.fishing = new SimpleStringProperty(fishing);
		this.specialities = new SimpleStringProperty(specialities);
		this.wood = new SimpleStringProperty(wood);

		changedItems = new HashMap<String, String>();
		newItems = new HashMap<String, String>();
	}

	public Cabin(){

		this(0,null,null,null,0,null,null,null,null,null,null,null,null,null);

	}
	// Property getters starts here --->

	public HashMap<String, String> getChangedItems(){
		return this.changedItems;
	}
	public HashMap<String, String> getNewitems(){
		return this.newItems;
	}


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

	// String getters starts here --->

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

	public String getId(){
		return this.id;
	}


	// liste getter

	public ObservableList<Reservation> getReservationList(){
		return reservationData;
	}

	public void addReservation(Reservation r){
		reservationData.add(r);
	}

	public ObservableList<Item> getItemList(){
		return itemData;
	}

	public void addItem(Item i){
		itemData.add(i);
	}
	//Setters go here..........................
	public void setName(String name){
		this.name.setValue(name);
	}

	public void setBedNumber(String bedNumber){
		this.bedNumber.setValue(bedNumber);	
		this.changedItems.put("bednumber", bedNumber);
	}

	public void setTableNumber(String tableNumber){		
		this.tableNumber.setValue(tableNumber);
		this.changedItems.put("tablenumber", tableNumber);
	}

	public void setYear(String year){	
		this.year.setValue(year);
		this.changedItems.put("year", year);
	}

	public void setTerrain(String terrain){
		this.terrain.setValue(terrain);
		this.changedItems.put("terrain", terrain);
	}

	public void setBike(String bike){
		this.bike.setValue(bike);
		this.changedItems.put("bike", bike);
	}

	public void setTrip(String trip){
		this.trip.setValue(trip);
		this.changedItems.put("trip", trip);
	}

	public void setGuitar(String guitar){
		this.guitar.setValue(guitar);
		this.changedItems.put("guitar", guitar);
	}

	public void setWaffleIron(String waffleIron){
		this.waffleIron.setValue(waffleIron);
		this.changedItems.put("waffleiron", waffleIron);
	}

	public void setHunting(String hunting){
		this.hunting.setValue(hunting);
		this.changedItems.put("hunting", hunting);
	}

	public void setFishing(String fishing){
		this.fishing.setValue(fishing);
		this.changedItems.put("fishing", fishing);

	}
	public void setSpecialities(String specialities){		
		this.specialities.setValue(specialities);
		this.changedItems.put("specialities", specialities);
	}

	public void setWood(String wood){		
		this.wood.setValue(wood);
		this.changedItems.put("wood", wood);
	}

	public void addItem(String key, String value){
		this.newItems.put(key, value);
	}
}