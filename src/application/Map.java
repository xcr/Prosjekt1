package application;
 
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
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.stage.Stage;
 
 
public class Map extends Application implements MapComponentInitializedListener {


	GoogleMapView mapView;
	GoogleMap map;

	

	
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

		//Add a marker to the map
		MarkerOptions fosenkoia = new MarkerOptions();
		MarkerOptions holvassgamma = new MarkerOptions();
		MarkerOptions stakkslettbua = new MarkerOptions();
		MarkerOptions kaasen = new MarkerOptions();
		MarkerOptions rindalsloa = new MarkerOptions();
		MarkerOptions  kamtjonnkoia= new MarkerOptions();
		MarkerOptions  vekvessaetra= new MarkerOptions();
		MarkerOptions  telin = new MarkerOptions();
		MarkerOptions  iglbu= new MarkerOptions();
		MarkerOptions  taagaabu = new MarkerOptions();
		MarkerOptions  kvernmovollen = new MarkerOptions();
		MarkerOptions  mortenskaaten = new MarkerOptions();
		MarkerOptions  hognabu = new MarkerOptions();
		MarkerOptions  holmsaakoia = new MarkerOptions();
		MarkerOptions  stabburet = new MarkerOptions();
		MarkerOptions  kraaklikaaten= new MarkerOptions();
		MarkerOptions  flaakoia= new MarkerOptions();
		MarkerOptions  nicokoia= new MarkerOptions();
		MarkerOptions  lynhogen= new MarkerOptions();
		MarkerOptions  heinfjordstua= new MarkerOptions();
		MarkerOptions  selbukaaten= new MarkerOptions();
		MarkerOptions sonvasskoia = new MarkerOptions();
		MarkerOptions ovensenget = new MarkerOptions();

		fosenkoia.position(new LatLong(63.56813, 10.29541))
		.visible(Boolean.TRUE).title("fosenkoia");

		holvassgamma.position(new LatLong(63.82745, 10.37133))
		.visible(Boolean.TRUE).title("Holvassgamma");

		stakkslettbua.position(new LatLong(63.14713, 09.11576))
		.visible(Boolean.TRUE).title("Stakkslettbua");

		kaasen.position(new LatLong(63.12375, 09.44338))
		.visible(Boolean.TRUE).title("Kåsen");

		rindalsloa.position(new LatLong(63.01999, 09.19786))
		.visible(Boolean.TRUE).title("Rindalsløa");

		kamtjonnkoia.position(new LatLong(62.74317, 09.29119))
		.visible(Boolean.TRUE).title("kamtjonnkoia");

		vekvessaetra.position(new LatLong(62.70850, 09.80087))
		.visible(Boolean.TRUE).title("Vekvessætra");

		telin.position(new LatLong(62.87615, 09.66196))
		.visible(Boolean.TRUE).title("Telin");

		iglbu.position(new LatLong(62.96119, 10.09028))
		.visible(Boolean.TRUE).title("Iglbu");

		taagaabu.position(new LatLong(62.82197, 10.60875))
		.visible(Boolean.TRUE).title("Taagaabu");

		kvernmovollen.position(new LatLong(62.92831, 10.97890))
		.visible(Boolean.TRUE).title("kvernmovollen.");

		mortenskaaten.position(new LatLong(63.01650, 10.95894))
		.visible(Boolean.TRUE).title("Mortenskåten");

		hognabu.position(new LatLong(63.10875, 11.53450))
		.visible(Boolean.TRUE).title("Hognabu");

		holmsaakoia.position(new LatLong( 63.08642, 11.64870))
		.visible(Boolean.TRUE).title("Holmsåkoia");

		stabburet.position(new LatLong(63.14200, 11.72236))
		.visible(Boolean.TRUE).title("Stabburet");

		kraaklikaaten.position(new LatLong(63.12292, 10.59167))
		.visible(Boolean.TRUE).title("Kråklikåten");

		flaakoia.position(new LatLong(63.15702, 10.36538))
		.visible(Boolean.TRUE).title("Flåkoia");

		nicokoia.position(new LatLong(63.16349, 10.52637))
		.visible(Boolean.TRUE).title("Nicokoia");

		lynhogen.position(new LatLong(63.21028, 10.70875))
		.visible(Boolean.TRUE).title("Lynhøgen");

		heinfjordstua.position(new LatLong(63.31492, 10.75206))
		.visible(Boolean.TRUE).title("Heinfjordstua");

		selbukaaten.position(new LatLong(63.32851, 11.02758))
		.visible(Boolean.TRUE).title("Selbukåten");

		sonvasskoia.position(new LatLong(63.39030, 11.41852))
		.visible(Boolean.TRUE).title("Sonvasskoia");

		ovensenget.position(new LatLong(62.41219, 11.18655))
		.visible(Boolean.TRUE).title("Øvensenget");

		Marker fosenkoiaMarker = new Marker(fosenkoia);
		Marker holvassgammaMarker = new Marker(holvassgamma);
		Marker stakkslettbuaMarker = new Marker(stakkslettbua);
		Marker kaasenMarker = new Marker(kaasen); 
		Marker rindalsloaMarker = new Marker(rindalsloa);
		Marker kamtjonnkoiaMarker = new Marker(kamtjonnkoia);
		Marker vekvessaetraMarker = new Marker(vekvessaetra);
		Marker telinMarker = new Marker(telin);
		Marker iglbuMarker = new Marker(iglbu);
		Marker taagaabuMarker = new Marker(taagaabu);
		Marker kvernmovollenMarker = new Marker(kvernmovollen);
		Marker mortenskaatenMarker = new Marker(mortenskaaten);
		Marker hognabuMarker = new Marker(hognabu);
		Marker holmsaakoiaMarker = new Marker(holmsaakoia);
		Marker stabburetMarker = new Marker(stabburet);
		Marker kraaklikaatenMarker = new Marker(kraaklikaaten);
		Marker flaakoiaMarker = new Marker(flaakoia);
		Marker nicokoiaMarker = new Marker(nicokoia);
		Marker lynhogenMarker = new Marker(lynhogen);
		Marker heinfjordstuaMarker = new Marker(heinfjordstua);
		Marker selbukaatenMarker = new Marker(selbukaaten);
		Marker sonvasskoiaMarker = new Marker(sonvasskoia);
		Marker ovensengetMarker = new Marker(ovensenget);

		map.addMarker(fosenkoiaMarker);
		map.addMarker(holvassgammaMarker);
		map.addMarker(stakkslettbuaMarker);
		map.addMarker(kaasenMarker);
		map.addMarker(rindalsloaMarker);
		map.addMarker(vekvessaetraMarker);
		map.addMarker(telinMarker);
		map.addMarker(iglbuMarker);
		map.addMarker(taagaabuMarker);
		map.addMarker(kvernmovollenMarker);
		map.addMarker(mortenskaatenMarker);
		map.addMarker(hognabuMarker);
		map.addMarker(holmsaakoiaMarker);
		map.addMarker(stabburetMarker);
		map.addMarker(kraaklikaatenMarker);
		map.addMarker(flaakoiaMarker);
		map.addMarker(nicokoiaMarker);
		map.addMarker(lynhogenMarker);
		map.addMarker(heinfjordstuaMarker);
		map.addMarker(selbukaatenMarker);
		map.addMarker(sonvasskoiaMarker);
		map.addMarker(ovensengetMarker);
		map.addMarker(kamtjonnkoiaMarker);
		
		InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes");

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, fosenkoiaMarker);
        
       // map.addUIEventHandler(fosenkoiaMarker, UIEventType.click, fredWilkeInfoWindow.open(map, fosenkoiaMarker));
		//map.addMarker(fosenkoiaMarker);
		
	}


 

}