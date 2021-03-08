package arslabadack.ifsc.oop2.skynet;

import javafx.fxml.FXML;

public class MainController {
	
	@FXML
	private void logout() {
		App.changeResizable();
		App.setRoot("login");
	}

}
