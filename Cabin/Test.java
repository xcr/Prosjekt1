package Cabin;
import java.util.ArrayList;

public class Test {
	
	public static void main(String Args[]){
		
		Sql_data sql = new Sql_data("jdbc:mysql://mysql.stud.ntnu.no/gabrielb_gruppe2", "gabrielb_guest", "guest");
		sql.connect();
	}
}
