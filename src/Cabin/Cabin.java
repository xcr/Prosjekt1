package Cabin;
import java.util.HashMap;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableMap;


public class Cabin {
	
	
	
	//Git test
	//name-bednumber-tablenumber-year-terrain-bike-trip-guitar-waffleiron-hunting-fishing-specialities-wood
	
	//private int bednumber, tablenumber, year, bike, trip, guitar, waffleiron, hunting, fishing;
	private  StringProperty name, bednumber,tablenumber,year,terrain,bike,trip,guitar,waffleiron,hunting,fishing,specialities,wood;
	
	public Cabin(String name, int bednumber, int tablenumber, int year, String terrain, int bike, int trip,
			int guitar, int waffleiron, int hunting, int fishing, String specialities, String wood ){
		
		this.name.setValue(name);
		this.bednumber.setValue(Integer.toString(bednumber));
		this.tablenumber.setValue(Integer.toString(tablenumber));
		this.year.setValue(Integer.toString(year));
		this.terrain.setValue(terrain);
		this.bike.setValue(Integer.toString(bike));
		this.trip.setValue(Integer.toString(trip));
		this.guitar.setValue(Integer.toString(guitar));
		this.waffleiron.setValue(Integer.toString(waffleiron));
		this.hunting.setValue(Integer.toString(hunting));
		this.fishing.setValue(Integer.toString(fishing));
		this.specialities.setValue(specialities);
		this.wood.setValue(wood);
		}
	
	//Property getters starts here------------------------->
	
	public StringProperty getBednumberProperty() {
		return bednumber;
	}

	public StringProperty getTablenumberProperty() {
		return tablenumber;
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
		return waffleiron;
	}

	public StringProperty getHuntingProperty() {
		return hunting;
	}

	public StringProperty getFishingProperty() {
		return fishing;
	}

	public StringProperty getWoodProperty(){
		return this.wood;
	}
	
	public StringProperty getNameProperty(){
		return this.name;
	}
	
	public StringProperty getSpecialitiesProperty(){
		return this.specialities;
	}
	
	public StringProperty getTerrainProperty(){
		return this.terrain;
	}
	
	//String getters starts here --------------------------
	
	
	public String getBednumber() {
		return bednumber.toString();
	}

	public String getTablenumber() {
		return tablenumber.toString();
	}

	public String getYear() {
		return year.toString();
	}

	public String getBike() {
		return bike.toString();
	}

	public String getTrip() {
		return trip.toString();
	}

	public String getGuitar() {
		return guitar.toString();
	}

	public String getWaffleiron() {
		return waffleiron.toString();
	}

	public String getHunting() {
		return hunting.toString();
	}

	public String getFishing() {
		return fishing.toString();
	}

	public String getWood(){
		return this.wood.toString();
	}
	
	public String getName(){
		return this.name.toString();
	}
	
	public String getSpecialities(){
		return this.specialities.toString();
	}
	
	public String getTerrain(){
		return this.terrain.toString();
	}

	
}
