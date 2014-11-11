package Cabin;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class Test {
	
	public static void main(String Args[]){
		
		Sql_data sql = new Sql_data("jdbc:mysql://mysql.stud.ntnu.no/gabrielb_gruppe2", "gabrielb_guest", "guest");
		sql.connect();
		ObservableList<Reservation> reservations =sql.getReservationData();
		
		for(Reservation r : reservations){
			if(r.getId() == 11 ){
				System.out.println(r.getfirstname());
			}
		}
	}
}
