package application;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Cabin.Cabin;
import Cabin.Sql_data;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;

import javafx.application.Application;
import javafx.collections.ObservableList;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
import netscape.javascript.JSObject;

public class Map extends Application implements MapComponentInitializedListener {

	GoogleMapView mapView;
	GoogleMap map;
	private ObservableList<Cabin> cabins;
	private HashMap<String, LatLong> coor;


	public Map(Stage stage) throws Exception{
		start(stage);
		
	}
	@Override
	public void start(Stage stage) throws Exception {

		//Create the JavaFX component and set this as a listener so we know when 
		//the map has been initialized, at which point we can then begin manipulating it.
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);
		Scene scene = new Scene(mapView);

		stage.setTitle("JavaFX and Google Maps");
		stage.setScene(scene);
		stage.show();
	}


	public void initVars(){

		coor = new HashMap<String, LatLong>();
		coor.put("Fosenkoia", new LatLong(63.56813, 10.29541));
		coor.put("Holvassgamma" ,new LatLong(63.82745, 10.37133));
		coor.put("Stakkslettbua" ,new LatLong(63.14713, 09.11576));
		coor.put("Kaasen", new LatLong(63.12375, 09.44338));
		coor.put("Rindalsloa",new LatLong(63.01999, 09.19786));
		coor.put("Kamtjonnkoia",new LatLong(62.74317, 09.29119));
		coor.put("Vekvessaetra", new LatLong(62.70850, 09.80087));
		coor.put("Telin", new LatLong(62.87615, 09.66196));
		coor.put("Iglbu", new LatLong(62.96119, 10.09028));
		coor.put("Taagaabu", new LatLong(62.82197, 10.60875));
		coor.put("Kvernmovollen", new LatLong(62.92831, 10.97890));
		coor.put("Mortenskaaten", new LatLong(63.01650, 10.95894));
		coor.put("Hognabu", new LatLong(63.10875, 11.53450));
		coor.put("Holmsaakoia", new LatLong( 63.08642, 11.64870));
		coor.put("Stabburet", new LatLong(63.14200, 11.72236));
		coor.put("Kraaklikaaten", new LatLong(63.12292, 10.59167));
		coor.put("Flaakoia", new LatLong(63.15702, 10.36538));
		coor.put("Nicokoia", new LatLong(63.16349, 10.52637));
		coor.put("Lynhogen", new LatLong(63.21028, 10.70875));
		coor.put("Heinfjordstua", new LatLong(63.31492, 10.75206));
		coor.put("Selbukaaten", new LatLong(63.32851, 11.02758));
		coor.put("Sonvasskoia", new LatLong(63.39030, 11.41852));
		coor.put("Ovensenget", new LatLong(62.41219, 11.18655));

		try {
			this.cabins = Sql_data.getCabinData();
		} catch (SQLException e) {
			System.out.println("kunne ikke hente data til å bruke i kartapplikasjon");
			e.printStackTrace();
		}

	}

	@Override
	public void mapInitialized() {

		//Set the initial properties of the map.
		MapOptions mapOptions = new MapOptions();

		mapOptions.center(new LatLong(63, 10))
		.mapType(MapTypeIdEnum.ROADMAP)
		.overviewMapControl(false)
		.panControl(false)
		.rotateControl(false)
		.scaleControl(false)
		.streetViewControl(false)
		.zoomControl(false)
		.zoom(8);

		map = mapView.createMap(mapOptions);

		initVars();

		for(Cabin c : cabins){
			MarkerOptions markerOptions = new MarkerOptions().position(coor.get(c.getName()));
			Marker cabinMarker = new Marker(markerOptions);
			InfoWindowOptions infoOpts = new InfoWindowOptions();
			infoOpts.content("<h3>" + c.getName() + "</h3>" +"<p>"+ "Vedstatus: " + c.getWood()+ "</p>" + "<p>reservasjoner: " + c.getReservationList() + "</p>");
			InfoWindow cabinInfoWindow = new InfoWindow(infoOpts);
			map.addUIEventHandler(cabinMarker, UIEventType.click, (JSObject obj) -> cabinInfoWindow.open(map, cabinMarker));
			map.addMarker(cabinMarker);		
		}

	}
	/*
	public static void main(String[] args) {

		launch(args);
	}
	*/


}