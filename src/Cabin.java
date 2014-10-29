import java.util.HashMap;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import javafx.beans.property.StringProperty;
import javafx.collections.ObservableMap;


public class Cabin {
	
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
	
	public StringProperty getBednumber() {
		return bednumber;
	}

	public StringProperty getTablenumber() {
		return tablenumber;
	}

	public StringProperty getYear() {
		return year;
	}

	public StringProperty getBike() {
		return bike;
	}

	public StringProperty getTrip() {
		return trip;
	}

	public StringProperty getGuitar() {
		return guitar;
	}

	public StringProperty getWaffleiron() {
		return waffleiron;
	}

	public StringProperty getHunting() {
		return hunting;
	}

	public StringProperty getFishing() {
		return fishing;
	}

	public StringProperty getWood(){
		return this.wood;
	}
	
	public StringProperty getName(){
		return this.name;
	}
	
	public StringProperty getSpecialities(){
		return this.specialities;
	}
	
	public StringProperty getTerrain(){
		return this.terrain;
	}
	
}
