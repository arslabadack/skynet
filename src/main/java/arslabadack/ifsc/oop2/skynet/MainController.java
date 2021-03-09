package arslabadack.ifsc.oop2.skynet;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
	
	@FXML
	private Button btnMarketplace;

	
	@FXML
	private void logout() {
		App.changeResizable();
		App.setRoot("login");
	}
	
	@FXML
	private void marketplace() {
		App.setRoot("marketplace");
	}

}
