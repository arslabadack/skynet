package arslabadack.ifsc.oop2.skynet;

import arslabadack.ifsc.oop2.skynet.db.UtilDB;
import javafx.application.Application;

public class Main {
	
	public static void main(String[] args) {
		
		Thread connection = new Thread(new FileThread());
		App.setConnection(connection);
		System.out.println("Thread main executando");
		System.out.println("BD inicializado");
		UtilDB.initDB();
		
//		System.out.println("Usuarios do arquivo local lidos e salvos no banco");
//		new UsersInFile().check();
		
		System.out.println("Interface gráfica iniciando");
		Application.launch(App.class);
		
		UtilDB.closeConn();
		
		connection.interrupt();
	}

}
