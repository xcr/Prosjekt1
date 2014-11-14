package Cabin;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class Test {
	
	public static void main(String Args[]){
		
		try {
			Sql_data.updateItemInDatabase("Fosenkoia", "fiskeboller", "49", "71");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
