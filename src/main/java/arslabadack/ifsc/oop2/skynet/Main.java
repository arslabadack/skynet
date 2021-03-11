package arslabadack.ifsc.oop2.skynet;

import arslabadack.ifsc.oop2.skynet.db.UtilDB;
import javafx.application.Application;

public class Main {
	
	public static void main(String[] args) {
		UtilDB.initDB();
		Application.launch(App.class);
		UtilDB.closeConn();
	}

}
